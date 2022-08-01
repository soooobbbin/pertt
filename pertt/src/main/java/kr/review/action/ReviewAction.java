package kr.review.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;
import kr.util.PagingUtil;
   
public class ReviewAction implements Action{
	//작품 포스터를 누르면 get 방식으로 작품 번호c_num를 넘겨받음 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//===================작품 상세 정보 ===============================
		// 작품 번호 받아서 작품 상세 호출
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		ContentsDAO dao = ContentsDAO.getInstance();
		ContentsVO contents = dao.getContents(c_num);
		
		//작품에 대한 별점의 평균 구해서 보내주기
		ReviewDAO rDao = ReviewDAO.getInstance();
		double starAvg = rDao.getStarAvg(c_num);		
		starAvg = Math.round(starAvg*10)/10.0;
		//============해당 작품에 별점/리뷰를 작성한 이력이 있는지 확인==========
		int starCheck = -1;
		int reviewCheck = 0;
		//로그인한 member_num 받아오기
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		ReviewVO reviewVO = new ReviewVO();
		if(user_num !=null) {//로그인한 경우
			//내 별점찾기 목록이 있으면 별점 준 이력 있음(1)
			reviewVO = rDao.selectMyStar(user_num, c_num);
			if( reviewVO != null) {
				starCheck = 1;
				request.setAttribute("review", reviewVO);
			} else {
				starCheck = 0;
			}
			if(rDao.checkReview(user_num, c_num)) {
				reviewCheck = 1;
			};
		} else user_num = -1;
		
		request.setAttribute("u_num", user_num);
		request.setAttribute("contents", contents);//작품 상세
		request.setAttribute("starAvg", starAvg); // 별점 평균
		request.setAttribute("starCheck", starCheck); //별점 여부 확인
		request.setAttribute("reviewCheck", reviewCheck); // 리뷰 여부 확인
		
		return "/WEB-INF/views/review/review.jsp";
		
	}
}
