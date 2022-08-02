package kr.ott.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.ott.dao.OttDAO;
import kr.ott.vo.OttStarVO;
import kr.util.PagingUtil;

public class OttStarFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		OttDAO dao = OttDAO.getInstance();
		int ott_num = Integer.parseInt(request.getParameter("ott_num"));
		int price = Integer.parseInt(request.getParameter("price"));
		int usability = Integer.parseInt(request.getParameter("usability"));
		int quality = Integer.parseInt(request.getParameter("quality"));
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		boolean starCheck = false;
		
		return "/WEB-INF/views/ott/ottReview.jsp";
	}
}
