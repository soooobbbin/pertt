package kr.contents.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.contents.dao.CategoryDAO;
import kr.contents.dao.ContentsDAO;
import kr.contents.vo.CategoryVO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;

public class OttContentsAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		int ott_num = Integer.parseInt(request.getParameter("ott_num"));
		
		CategoryDAO categoryDao = CategoryDAO.getInstance();
		
		List<CategoryVO> categoryList = categoryDao.getListCategory(ott_num);
		
		ContentsDAO contentsDao = ContentsDAO.getInstance();
		int count = contentsDao.getContentsCount(keyfield, keyword);
		if(count>0) {
		for(int i=0;i<categoryList.size();i++) {
			List<ContentsVO> contentsList = contentsDao.getListContents(1, 10, keyfield, keyword, categoryList.get(i).getCategory_num());
			request.setAttribute("contents"+i, contentsList);
		}
		}
		request.setAttribute("count", count);
		request.setAttribute("categoryList", categoryList);

		return "/WEB-INF/views/contents/ottContents.jsp";
	}

}
