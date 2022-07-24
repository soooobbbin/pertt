package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;

public class ReviewWriteAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//json으로 처리!!!
		//로그인 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.jsp";
		}
		
		//로그인 된 경우
		request.setCharacterEncoding("utf-8");
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		String content = request.getParameter("content");
		ReviewDAO dao = ReviewDAO.getInstance();
		dao.updateReviewContent(user_num, c_num, content);
		
		//
		
		return "/WEB-INF/views/board/write.jsp";
	}
}
