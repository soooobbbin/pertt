package kr.ott.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.ott.dao.OttDAO;
import kr.ott.vo.OttReviewVO;

public class OttStarFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// ott 번호 받아서 ott 별점 상세 호출
		int ott_num = Integer.parseInt(request.getParameter("ott_num"));

		OttDAO dao = OttDAO.getInstance();

		//============해당 작품에 별점/리뷰를 작성한 이력이 있는지 확인==========
		int starCheck = -1;
		int reviewCheck = 0;
		//로그인한 member_num 받아오기
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		OttReviewVO ottReview = new OttReviewVO();
		if(user_num !=null) {//로그인한 경우
			//내 별점찾기 목록이 있으면 별점 준 이력 있음(1)
			ottReview = dao.selectMyStar(user_num, ott_num);
			if( ottReview != null) {
				starCheck = 1;
				System.out.println(ottReview.getPrice());
				request.setAttribute("ottReview", ottReview);
			} else {
				starCheck = 0;
			}
		} else user_num = -1;
		
		request.setAttribute("u_num", user_num);
		request.setAttribute("starCheck", starCheck); //별점 여부 확인
		request.setAttribute("reviewCheck", reviewCheck); // 리뷰 여부 확인
		
		return "/WEB-INF/views/ott/ottReview.jsp";
	}
}
