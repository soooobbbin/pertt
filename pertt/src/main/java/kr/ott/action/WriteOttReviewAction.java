package kr.ott.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.ott.dao.OttDAO;

public class WriteOttReviewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// json으로 content insert 처리!!!
		Map<String, String> mapAjax = new HashMap<String, String>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");
		if (user_num == null) {
			mapAjax.put("result", "logout");
		} else {// 로그인된 경우
			request.setCharacterEncoding("utf-8");
			int ott_num = Integer.parseInt(request.getParameter("ott_num2"));
			OttDAO dao = OttDAO.getInstance();

			// 리뷰를 쓴 적 없는 회원인지 확인. 리뷰 이미 썼으면 true로 반환
			if (dao.checkReview(user_num, ott_num)) {
				mapAjax.put("result", "duplicated");
			} else {
				String content = request.getParameter("content");
				dao.modifyReviewContent(ott_num, user_num, 0, content, true);
				mapAjax.put("result", "success");
			}
		}

		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
