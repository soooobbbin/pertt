package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class ModifyUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");
		if (user_num == null) {// 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}

		// 로그인 된 경우
		// 전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");

		// 자바빈(VO) 생성
		MemberVO member = new MemberVO();
		member.setMember_num(user_num);
		
		member.setPasswd(request.getParameter("passwd"));
		member.setName(request.getParameter("name"));
		member.setBirth(request.getParameter("birth1") + "-" + request.getParameter("birth2") + "-"
				+ request.getParameter("birth3"));
		member.setPhone(request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-"
				+ request.getParameter("phone3"));
		member.setEmail(request.getParameter("email"));
		
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.updateMember(member);
		
		

		return "/WEB-INF/views/member/modifyUser.jsp";
		
		

		//전송된 데이터 반환
		
		
	}

}
