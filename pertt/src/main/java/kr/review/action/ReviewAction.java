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
		//작품 번호 받아서 작품 상세 호출
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		ContentsDAO dao = ContentsDAO.getInstance();
		ContentsVO contents = dao.getContents(c_num);
		//작품에 대한 별점의 평균 구해서 보내주기
		
		//===================리뷰 목록==================================
		/*
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null ) pageNum = "1";
		//String keyfield = request.getParameter("keyfield");(정렬할 때 사용)
		
		//작품에 달린 리뷰 개수 구하기
		ReviewDAO rDao = ReviewDAO.getInstance();
		int count = rDao.getReviewCount(c_num);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, 8, 5, "review.do");
		
		//작품에 달린 리뷰 정보 보내주기 
		List<ReviewVO> list = null;
		if(count > 0) {
			list = rDao.getReviewList(c_num, page.getStartRow(), page.getEndRow());
		}
		*/
		
			request.setCharacterEncoding("utf-8");
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null) {
				pageNum = "1";
			}
			ReviewDAO rdao = ReviewDAO.getInstance();
			int count = rdao.getReviewCount(c_num);
			
			//ajax방식으로 목록을 표기하기 때문에 페이징유틸은 페이지수 표시가 목적이 아니라
			//목록 데이터의 페이지 처리를 위한 rownum 번호를 구하는 것이 목적
			int rowCount = 8;//한번에 리뷰 8개 보여주기
			PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), 
												count, rowCount, 1, null);
			
			List<ReviewVO> reviewList = new ArrayList<ReviewVO>();
			if(count > 0) {
				reviewList = rdao.getReviewList(c_num, page.getStartRow(), page.getEndRow());
			} else {
				reviewList = Collections.emptyList();
			}
			
			HttpSession session = request.getSession();
			Integer user_num = (Integer)session.getAttribute("user_num");
			
			Map<String,Object> mapAjax = 
					new HashMap<String,Object>();
		
			mapAjax.put("count", count); //전체 글 수
			mapAjax.put("rowCount", rowCount); // 페이지처리용
			mapAjax.put("reviewList", reviewList); // 리뷰리스트
			//로그인한 회원번호와 작성자 회원번호 일치 여부를 체크하기 위해 user_num 전송
			mapAjax.put("user_num", user_num);
			
			
		
		//============해당 작품에 별점/리뷰를 작성한 이력이 있는지 확인==========
		/* boolean starCheck = false;
		boolean reviewCheck = false;
		//로그인한 member_num 받아오기
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num !=null) {//로그인한 경우
			//내 별점찾기 목록이 있으면 별점 준 이력 있음(true)
			if(rDao.selectMyStar(user_num, c_num) != null) {
				starCheck = true;
			}
			reviewCheck = rDao.checkReview(user_num, c_num);
		}
		System.out.println(reviewCheck);
		*/
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);	
	
		
		
		/*
		request.setAttribute("contents", contents);//작품 상세
		request.setAttribute("count", count);//전체 글 수 
		request.setAttribute("list", list);//리뷰 목록
		request.setAttribute("page", page.getPage());//페이지
		request.setAttribute("starCheck", starCheck);
		request.setAttribute("reviewCheck", reviewCheck);
		*/
		
		return "/WEB-INF/views/common/ajax_view.jsp";
		
	}
}
