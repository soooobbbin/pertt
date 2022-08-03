package kr.review.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.review.dao.ReviewDAO;
import kr.review.vo.CommentVO;
import kr.review.vo.ReviewVO;
import kr.util.PagingUtil;
import kr.util.StringUtil;

public class ReviewListAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 리뷰 리스트 보내주기
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		String sort = request.getParameter("sort");
		ReviewDAO dao = ReviewDAO.getInstance();
		int count = dao.getReviewCount(c_num);

		// ajax방식으로 목록을 표기하기 때문에 페이징유틸은 페이지수 표시가 목적이 아니라
		// 목록 데이터의 페이지 처리를 위한 rownum 번호를 구하는 것이 목적
		int rowCount = 8;// 한번에 리뷰 8개 보여주기
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, rowCount, 1, null);

		List<ReviewVO> reviewList = null;
		if (count > 0) {
			reviewList = dao.getReviewList(c_num, page.getStartRow(), page.getEndRow(), sort);
			for(ReviewVO review : reviewList) {
				if(review.getC_review_content().length() > 106) {
					review.setC_review_content((review.getC_review_content()).substring(0, 106)); 
				}
				review.setC_review_content(review.getC_review_content().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", " "));
			}
		} else {
			reviewList = Collections.emptyList();
		}

		HttpSession session = request.getSession();
		Integer user_num2 = (Integer)session.getAttribute("user_num");

		Map<String, Object> mapAjax = new HashMap<String, Object>();

		mapAjax.put("count", count); // 전체 글 수
		mapAjax.put("rowCount", rowCount); // 페이지처리용
		mapAjax.put("reviewList", reviewList); // 리뷰리스트
		// 로그인한 회원번호와 작성자 회원번호 일치 여부를 체크하기 위해 user_num 전송
		mapAjax.put("user_num2", user_num2);
		mapAjax.put("c_num2", c_num);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
