package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;
import kr.util.StringUtil;

public class ReviewDetailAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//작품 상세
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		ContentsDAO dao = ContentsDAO.getInstance();
		ContentsVO contents = dao.getContents(c_num);
		
		int c_review_num = Integer.parseInt(request.getParameter("c_review_num"));
		ReviewDAO rdao = ReviewDAO.getInstance();
		ReviewVO review = rdao.getReviewDetail(c_review_num);
		//html불허와 줄바꿈 처리
		review.setC_review_content(StringUtil.useBrNoHtml(review.getC_review_content()));
		
		request.setAttribute("contents", contents);
		request.setAttribute("review", review);
		return "/WEB-INF/views/review/reviewDetail.jsp";
	}
}
