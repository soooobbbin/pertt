package kr.member.action;

//import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class MyPageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인 된 경우
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(user_num);
		
		request.setAttribute("member", member);
		
		//내 글 목록
		/*//좋아요를 클릭한 게시물 -좋아요는 없지만 OTT리뷰글 게시글, 작품리뷰게시글, 댓글쓴 게시글이 있어야함
		BoardDAO boardDao = BoardDAO.getInstance();
		List<BoardVO> boardList = 
				 boardDao.getListBoardFav(1, 5, member_num);
		
		request.setAttribute("member", member);
		request.setAttribute("boardList", boardList);
		*/
		
		//내 OTT추천 목록
		
		return "/WEB-INF/views/member/myPage.jsp";
	}

}
