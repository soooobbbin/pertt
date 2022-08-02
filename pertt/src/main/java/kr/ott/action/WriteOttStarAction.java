package kr.ott.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.ott.dao.OttDAO;
import kr.ott.vo.OttReviewVO;

public class WriteOttStarAction implements Action{

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
		
		Map<String,Object> mapAjax = 
		          new HashMap<String,Object>();
		double starAvg = -1;
		if(user_num == null) {
			mapAjax.put("result", "logout");
		} else {
			if(dao.selectMyStar(user_num, ott_num) != null) starCheck = true;
			OttReviewVO ottReview = new OttReviewVO();
			ottReview.setOtt_num(ott_num);
			ottReview.setMember_num(user_num);
			ottReview.setPrice(price);
			ottReview.setUsability(usability);
			ottReview.setQuality(quality);
			if(starCheck) {
				dao.updateStar(user_num, ott_num, price, usability, quality);
			} else {
				dao.insertStar(ottReview);
			}
			starAvg = dao.getStarAvg(ott_num);
			starAvg = Math.round((starAvg*10)/10.0);
			mapAjax.put("result", "success");
			mapAjax.put("starAvg", starAvg);
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
