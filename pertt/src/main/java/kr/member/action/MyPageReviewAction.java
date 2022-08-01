package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class MyPageReviewAction implements Action{
	//내 리뷰 불러오기 dao 실행
	//member_num / sort(String)타입
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String,Object> mapAjax = 
		          new HashMap<String,Object>();
		//작픔리뷰 //내 댓글목록
		//컨텐츠 정보 가져오기
		/*
		ReviewDAO rDao = ReviewDAO.getInstance();
		
		//내 글 조회  - 내가 쓴 리뷰 리스트 반환 (sort=1(별점순) sort=2(최신순)) //별점순 기본으로
		List<ReviewVO> myReview = rDao.selectMyReview(user_num, 1, 3, "1");
		
		
		//내가 쓴 댓글 리스트 반환 
	    List<CommentVO> comment =rDao.selectMyComment(user_num, 1, 2);
		
		//댓글을 쓴 게시글
		 
		 
	   request.setAttribute("comment",comment);
		request.setAttribute("myReview", myReview); 
		mapAjax.put("result","success");
		
		//내 OTT 리뷰
		//내 OTT 추천 목록*/
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
	
}
