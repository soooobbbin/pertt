package kr.contents.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class OttContentsAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		ContentsDAO dao = ContentsDAO.getInstance();
		//int count = dao.getContentsCount(keyfield, keyword);
		
		//페이지 처리
//		PagingUtil page = new PagingUtil(keyfield,keyword,
//						Integer.parseInt(pageNum),count,20,10,"list.do");
    	List<ContentsVO> list1 = null;
    	List<ContentsVO> list2 = null;
    	List<ContentsVO> list3 = null;
    	List<ContentsVO> list4 = null;
		
		list1 = dao.getListContents(1, 10, keyfield, keyword);
		list2 = dao.getListContents(1, 10, keyfield, keyword);
		list3 = dao.getListContents(1, 10, keyfield, keyword);
		list4 = dao.getListContents(1, 10, keyfield, keyword);
		
//		request.setAttribute("count", count);
//		request.setAttribute("page", page.getPage());
		request.setAttribute("list1", list1);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		request.setAttribute("list4", list4);
		
		
		return "/WEB-INF/views/contents/ottContents.jsp";
	}

}
