package kr.ott.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.ott.dao.OttDAO;
import kr.ott.vo.OttRankVO;
import kr.util.PagingUtil;

public class OttStarFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		//OTT 번호 받아서 별점 표시 호출
		OttVO
		OttDAO dao = OttDAO.getInstance();
		OttRankVO ottRank = dao.getOttRank(ott_num);

		//한줄평 목록
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		//각 OTT에 달린 한줄평 개수 구하기
		OttDAO dao1 = OttDAO.getInstance();
		int count = dao1.getOttReviewCount(ott_num);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, 8, 5, "ottReview.do");
		
		//각 OTT에 달린 별점 정보 보내 주기
		if(count > 0) {
			int ott_rank_num = 0;
			dao.resultOttStar(ott_rank_num);
		}
		
		//각 OTT에 달린 한줄평 정보 보내 주기
		List<OttRankVO> list = null;
		if(count > 0) {
			list = dao.getOttReviewList(ott_num, page.getStartRow(), page.getEndRow());
		}
		
		//request에 담아서 ottReview.jsp로 보내기
		request.setAttribute("ottRank", ottRank);
		request.setAttribute("count", count);//전체 글 수 
		request.setAttribute("list", list);//리뷰 목록
		request.setAttribute("page", page.getPage());//페이지
		*/
		return "/WEB-INF/views/ott/ottReview.jsp";
	}
}
