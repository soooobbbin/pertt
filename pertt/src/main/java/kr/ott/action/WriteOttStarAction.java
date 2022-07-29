package kr.ott.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.ott.dao.OttStarDAO;
import kr.ott.vo.OttStarVO;

public class WriteOttStarAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인이 된 경우
			//전송된 데이터 인코딩 처리
			request.setCharacterEncoding("utf-8");
			
			int ott_num = Integer.parseInt(request.getParameter("ott_num"));
			
			OttStarDAO dao = OttStarDAO.getInstance();
			OttStarVO ottStar = dao.checkStar(ott_num, user_num);
			if(ottStar!=null) { //별점 기록 있으면 update
				dao.updateStar(ottStar);
				mapAjax.put("result", "success2");
			}else { //별점 기록 없으면 insert
				dao.insertStar(ottStar);
				mapAjax.put("result","success");
				mapAjax.put("status", "yesStar");
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
