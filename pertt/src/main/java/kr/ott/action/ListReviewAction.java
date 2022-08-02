package kr.ott.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.ott.dao.OttDAO;
import kr.ott.vo.OttReviewVO;
import kr.util.PagingUtil;

public class ListReviewAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 리뷰 리스트 보내주기
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int ott_num = Integer.parseInt(request.getParameter("ott_num"));
		String sort = request.getParameter("sort");
		OttDAO dao = OttDAO.getInstance();
		int count = dao.getOttReviewCount(ott_num);

		// ajax방식으로 목록을 표기하기 때문에 페이징유틸은 페이지수 표시가 목적이 아니라
		// 목록 데이터의 페이지 처리를 위한 rownum 번호를 구하는 것이 목적
		int rowCount = 10;// 한번에 리뷰 8개 보여주기
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, rowCount, 1, null);

		List<OttReviewVO> reviewList = null;
		if (count > 0) {
			reviewList = dao.getReviewList(ott_num, page.getStartRow(), page.getEndRow(), sort);
		} else {
			reviewList = Collections.emptyList();
		}

		HttpSession session = request.getSession();
		Integer user_num2 = (Integer) session.getAttribute("user_num");

		Map<String, Object> mapAjax = new HashMap<String, Object>();

		mapAjax.put("count", count); // 전체 글 수
		mapAjax.put("rowCount", rowCount); // 페이지처리용
		mapAjax.put("reviewList", reviewList); // 리뷰리스트
		// 로그인한 회원번호와 작성자 회원번호 일치 여부를 체크하기 위해 user_num 전송
		mapAjax.put("user_num2", user_num2);
		mapAjax.put("ott_num2", ott_num);

		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
