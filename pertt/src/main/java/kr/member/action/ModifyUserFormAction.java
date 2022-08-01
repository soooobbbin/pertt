package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class ModifyUserFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(user_num);
		
		/*String[] birthArray = member.getBirth().split("-");
		for(int i=0;i<birthArray.length;i++) {
			if(i==1) member.setBirth1(birthArray[i]);
			else if(i==2) member.setBirth2(birthArray[i]);
			else member.setBirth3(birthArray[i]);
		}*/
		
		String[] phoneArray = member.getPhone().split("-");
		for(int i=0;i<phoneArray.length;i++) {
			if(i==1) member.setPhone1(phoneArray[i]);
			else if(i==2) member.setPhone2(phoneArray[i]);
			else member.setPhone3(phoneArray[i]);
		}
		
		String[] emailArray = member.getEmail().split("@");
		for(int i=0;i<emailArray.length;i++) {
			if(i==1) member.setEmail(emailArray[i]);
			else if(i==2) member.setEmail2(emailArray[i]);
			else member.setEmail3(emailArray[i]);
		}
		
		request.setAttribute("member", member);
		
		
		//로그인이 된 경우
		return "/WEB-INF/views/member/modifyUserForm.jsp";
		
		
	}

}
