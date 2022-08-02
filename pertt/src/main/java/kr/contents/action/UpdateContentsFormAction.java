package kr.contents.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;

public class UpdateContentsFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않는 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 되어 있는 경우
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_auth < 2) {//관리자로 로그인 되어 있지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//작품번호 반환
		int c_num = Integer.parseInt(
				        request.getParameter("c_num"));
		ContentsDAO dao = ContentsDAO.getInstance();
		
		//작품상세 정보 반환
		ContentsVO contents = dao.getContents(c_num);
		
		request.setAttribute("contents", contents);
				
		return "/WEB-INF/views/contents/updateContentsForm.jsp";
	}

}
