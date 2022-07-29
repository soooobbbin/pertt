package kr.contents.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;

public class DetailContentsFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		//작품번호 반환
		int c_num = Integer.parseInt(
				        request.getParameter("c_num"));
		ContentsDAO dao = ContentsDAO.getInstance();
		
		//작품상세 정보 반환
		ContentsVO contents = dao.getContents(c_num);
		
		request.setAttribute("contents", contents);
		
		return "/WEB-INF/views/contents/detail.jsp";
	}

}
