package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class RegisterUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//자바빈(VO) 생성
		MemberVO member = new MemberVO();
		//전송된 데이터를 자바빈에 저장
		member.setMember_id(request.getParameter("Member_id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setName(request.getParameter("name"));
		member.setBirth(request.getParameter("birth"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
				  
		MemberDAO dao = MemberDAO.getInstance();
		dao.insertMember(member);
		
		return "/WEB-INF/views/member/registerUser.jsp" ;
	}

}



