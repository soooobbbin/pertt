package kr.contents.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		int category_num = Integer.parseInt(request.getParameter("category_num"));
		
		
		ContentsDAO dao = ContentsDAO.getInstance();
		int count = dao.getContentsCount(keyfield, keyword);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,
				Integer.parseInt(pageNum),count,20,10,"list.do");
		
		List<ContentsVO> list = null;
		if(count > 0) {
			list = dao.getListContents(page.getStartRow(),
					       page.getEndRow(), keyfield, keyword,category_num);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/contents/list.jsp";
	}

}
