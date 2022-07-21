package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;

public class ReviewFormAction implements Action{
	//작품 포스터를 누르면 get 방식으로 작품 번호c_num를 넘겨받음 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//작품 번호 받아서 작품 상세 호출
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		ContentsDAO dao = ContentsDAO.getInstance();
		ContentsVO contents = dao.getContents(c_num);
		
		//작품에 대한 별점의 평균 구해서 보내주기
		
		
		
		//작품상세 request에 담아서 review.jsp로 보내기
		request.setAttribute("contents", contents);
		return "/WEB-INF/views/review/review.jsp";
	}
}
