package kr.member.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.review.action.*;
import kr.review.vo.ReviewVO;
import kr.util.StringUtil;
import kr.review.vo.CommentVO;
import kr.review.dao.ReviewDAO;

import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
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
	
		ReviewDAO rDao = ReviewDAO.getInstance();
		//내 글 조회  - 내가 쓴 리뷰 리스트 반환 (sort=1(별점순) sort=2(최신순)) //별점순 기본으로
		List<ReviewVO> review = rDao.selectMyReview(user_num, 1, 3, "1");
		request.setAttribute("review", review);

		return "/WEB-INF/views/member/myPage.jsp";
	}

}
