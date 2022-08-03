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

public class WriteOttReviewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String,String> mapAjax  = new HashMap<String, String>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			mapAjax.put("result", "logout");
		} else{
			request.setCharacterEncoding("utf-8");
			OttReviewVO ottReview = new OttReviewVO();
			ottReview.setMember_num(user_num);
			ottReview.setOtt_re_content(request.getParameter("ott_re_content"));
			ottReview.setOtt_star_num(Integer.parseInt(request.getParameter("ott_star_num")));
			ottReview.setOtt_num(Integer.parseInt(request.getParameter("ott_num")));
			//
			OttDAO dao = OttDAO.getInstance();
			dao.insertReview(ottReview);
			
			mapAjax.put("result", "success");
		}
	ObjectMapper mapper = new ObjectMapper();
	String ajaxData = mapper.writeValueAsString(mapAjax);
	request.setAttribute("ajaxData", ajaxData);
	return "/WEB-INF/views/common/ajax_view.jsp";
	}

}

