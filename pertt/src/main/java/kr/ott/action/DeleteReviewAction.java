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

public class DeleteReviewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");

		// 전송된 데이터 반환
		int ott_review_num = Integer.parseInt(request.getParameter("ott_review_num"));

		Map<String, String> mapAjax = new HashMap<String, String>();

		OttDAO dao = OttDAO.getInstance();
		OttReviewVO ottReview = dao.getOttReview(ott_review_num);

		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");
		if (user_num == null) {// 로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		} else if (user_num != null && user_num == ottReview.getMember_num()) {
			// 로그인 회원번호와 작성자 회원번호 일치
			dao.deleteOttReview(ott_review_num);

			mapAjax.put("result", "success");

		} else {// 로그인 회원번호와 작성자 회원번호 불일치
			mapAjax.put("result", "wrongAccess");
		}

		// JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
