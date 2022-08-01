package kr.contents.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class DeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 되지 않은 경우
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_auth < 2) {//관리자로 로그인 되어 있지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		ContentsDAO dao = ContentsDAO.getInstance();
		ContentsVO db_contents = dao.getContents(c_num);
		if(user_auth < 2) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		dao.deleteContents(c_num);
		//파일 삭제
		FileUtil.removeFile(request, db_contents.getPoster());
		
		return "redirect:/contents/list.do";
	}

}
