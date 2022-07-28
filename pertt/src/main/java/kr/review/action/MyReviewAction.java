package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;

public class MyReviewAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		System.out.println(user_num);
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		ReviewDAO dao = ReviewDAO.getInstance();
		ReviewVO review = dao.selectMyStar(user_num, c_num);
		if(review.getC_review_content() == null) {
			request.setAttribute("accessMsg", "해당 작품에 리뷰가 존재하지 않습니다.");
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		request.setAttribute("c_num", c_num);
		request.setAttribute("c_review_num", review.getC_review_num());
		
		return "/WEB-INF/views/review/reviewDetail.jsp";
	}
}
