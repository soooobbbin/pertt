package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.PagingUtil;

public class ResingedMemberListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {//로그인 되어 있지 않은 경우
			return "redirect:/member/loginForm.do";
			
		}
		//로그인 되어 있는 경우
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_auth < 2) {//관리자로 로그인 되어 있지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//관리자로 로그인한 경우
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		MemberDAO dao = MemberDAO.getInstance();
		int count = dao.getResignCountByAdmin(
				                        keyfield, keyword);
		//페이지 처리
		//keyfield,keyword,currentPage,count,rowCount,pageCount,url
		PagingUtil page = new PagingUtil(keyfield,keyword,
				    Integer.parseInt(pageNum),count,20,10,
				                          "resignedMemberList.do");
		List<MemberVO> list = null;
		if(count > 0) {
			list = dao.getResignedMemberByAdmin(
					       page.getStartRow(),
					       page.getEndRow(),
					       keyfield,keyword);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/member/resignedMemberList.jsp";
	}

}
