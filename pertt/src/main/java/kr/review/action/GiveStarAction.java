package kr.review.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;

public class GiveStarAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ReviewDAO dao = ReviewDAO.getInstance();
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		int star = Integer.parseInt(request.getParameter("star"));
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		boolean starCheck = false;
		
		Map<String,Object> mapAjax = 
		          new HashMap<String,Object>();
		double starAvg = -1;
		if(user_num == null) {
			mapAjax.put("result", "logout");
		} else {
			if(dao.selectMyStar(user_num, c_num) != null) starCheck = true;
			ReviewVO review = new ReviewVO();
			review.setC_num(c_num);
			review.setMember_num(user_num);
			review.setStar(star);
			if(starCheck) {
				dao.updateStar(user_num, c_num, star);
			} else {
				dao.insertStar(review);
			}
			starAvg = dao.getStarAvg(c_num);
			starAvg = Math.round(starAvg*10)/10.0;
			mapAjax.put("result", "success");
			mapAjax.put("starAvg", starAvg);
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
