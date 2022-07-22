package kr.review.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;
import kr.util.PagingUtil;

public class ReviewFormAction implements Action{
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
		
		//request에 담아서 review.jsp로 보내기
		request.setAttribute("contents", contents);//작품 상세
		request.setAttribute("count", count);//전체 글 수 
		request.setAttribute("list", list);//리뷰 목록
		request.setAttribute("page", page.getPage());//페이지
		return "/WEB-INF/views/review/review.jsp";
		
	}
}
