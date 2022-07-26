package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.util.StringUtil;

public class ReviewDetailAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//html불허와 줄바꿈 처리 하기!
		//board.setTitle(StringUtil.useNoHtml(board.getTitle()));
		//board.setContent(StringUtil.useBrNoHtml(board.getContent()));
		
		return "/WEB-INF/views/review/reviewDetail.jsp";
	}
}
