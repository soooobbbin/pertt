package kr.contents.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.contents.dao.CategoryDAO;
import kr.contents.dao.ContentsDAO;
import kr.contents.vo.CategoryVO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class OttContentsAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");

		ContentsDAO dao = ContentsDAO.getInstance();
		CategoryDAO dao2 = CategoryDAO.getInstance();
		//글번호 반환
		int ott_num = Integer.parseInt(request.getParameter("ott_num"));
		//int category_num = Integer.parseInt(request.getParameter("category_num"));
		int cnt =  0;
		
		if(ott_num == 1) {
			cnt=0;
			
			
		}else if(ott_num == 2) {
			cnt = 5;
		}else if(ott_num == 3) {
			cnt = 10;
		}else if(ott_num == 4) {
			cnt = 15;
		}else if(ott_num == 5) {
			cnt = 20;
		}
		//int count = dao.getContentsCount(keyfield, keyword);
		//request.setAttribute("count", count);

	
		List<ContentsVO> list1 = null;
		list1 = dao.getContents2(cnt+1);
		request.setAttribute("list1", list1);
		
		List<ContentsVO> list2 = null;
		list2 = dao.getContents2(cnt+2);
		request.setAttribute("list2", list2);
		
		List<ContentsVO> list3 = null;
		list3 = dao.getContents2(cnt+3);
		request.setAttribute("list3", list3);
		
		List<ContentsVO> list4 = null;
		list4 = dao.getContents2(cnt+4);
		request.setAttribute("list4", list4);
		
		List<ContentsVO> list5 = null;
		list5 = dao.getContents2(cnt+5);
		request.setAttribute("list5", list5);
		
		
//		List<ContentsVO> list1 = null;
//		List<ContentsVO> list2 = null;
//		List<ContentsVO> list3 = null;
//		List<ContentsVO> list4 = null;
//		
//		list1 = dao.getListContents(1, 10, keyfield, keyword);
//		list2 = dao.getListContents(1, 10, keyfield, keyword);
//		list3 = dao.getListContents(1, 10, keyfield, keyword);
//		list4 = dao.getListContents(1, 10, keyfield, keyword);
//		
//		request.setAttribute("list1", list1);
//		request.setAttribute("list2", list2);
//		request.setAttribute("list3", list3);
//		request.setAttribute("list4", list4);

		//카테고리 이름 불러오기
		List<CategoryVO> categorys = null;
		categorys = dao2.getListCategory(ott_num);
		request.setAttribute("categorys", categorys);
		

		//HTML를 허용하지 않음
		//board.setTitle(StringUtil.useNoHtml(board.getTitle()));
		//HTML를 허용하지 않으면서 줄바꿈 처리
		//board.setContent(StringUtil.useBrNoHtml(board.getContent()));

//		contents.setC_num(contents.getC_num());
//		contents.setCategory_num(contents.getCategory_num());
//		contents.setOtt_num(contents.getOtt_num());
//
//		request.setAttribute("contents", contents);



		return "/WEB-INF/views/contents/ottContents.jsp";
	}

}
