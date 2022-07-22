package kr.ott.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.ott.dao.OttDAO;

public class OttStarInsertAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		int ott_num = Integer.parseInt(request.getParameter("ott_num"));
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Integer member_num = (Integer)session.getAttribute("member_num");
		OttDAO dao = OttDAO.getInstance();
		
		if(member_num == null) { //로그인되지 않은 경우
			mapAjax.put(null, dao);
		}
		
		
		return null;
	}
}
