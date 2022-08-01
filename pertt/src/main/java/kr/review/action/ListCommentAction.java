package kr.review.action;

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
import kr.util.PagingUtil;

public class ListCommentAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		int c_review_num = Integer.parseInt(request.getParameter("c_review_num"));
		ReviewDAO dao = ReviewDAO.getInstance();
		int count = dao.getCommentCount(c_review_num);
		
		//  ajax방식으로 목록을 표기하기 때문에 페이징유틸은 페이지수 표시가 목적이 아니라
		//목록 데이터의 페이지 처리를 위한 rownum  번호를 구하는 것이 목적
		int rowCount = 10;//한번에 댓글 10개 보여주기
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), 
											count, rowCount, 1, null);
		
		List<CommentVO> list = null;
		if(count > 0) {
			list = dao.getListComment(page.getStartRow(), page.getEndRow(), c_review_num);
		} else {
			list = Collections.emptyList();
		}
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		Map<String,Object> mapAjax = 
				new HashMap<String,Object>();
		mapAjax.put("count", count);
		mapAjax.put("rowCount", rowCount);
		mapAjax.put("list", list);
		//로그인한 회원번호와 작성자 회원번호 일치 여부를 체크하기 위해 user_num 전송
		mapAjax.put("user_num", user_num);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";

	}
}
