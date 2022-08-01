package kr.review.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.review.dao.ReviewDAO;
import kr.review.vo.LikeVO;

public class WriteLikeAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> mapAjax = 
				new HashMap<String, Object>();
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {
			mapAjax.put("result", "logout");
		} else {// 로그인 된 경우
			request.setCharacterEncoding("utf-8");
			
			int c_review_num = Integer.parseInt(
					request.getParameter("c_review_num"));

			ReviewDAO dao = ReviewDAO.getInstance();
			LikeVO like = 
					dao.selectLike(c_review_num, user_num);
			
			if(like != null) { //좋아요 누른 적 있는 경우
				dao.deleteLike(like.getR_like_num());
				
				mapAjax.put("result", "success"); //작업 완료
				mapAjax.put("status", "noLike"); //결과적으로 좋아요 없음
				mapAjax.put("count", dao.selectLikeCount(c_review_num)); //전체 좋아요 수
			
			} else { // 좋아요 누른 적 없는 경우
				dao.insertLike(c_review_num, user_num);
				mapAjax.put("result", "success"); //작업 완료
				mapAjax.put("status", "yesLike"); //결과적으로 좋아요 있음"
				mapAjax.put("count", dao.selectLikeCount(c_review_num)); //전체 좋아요 수
			}
		}
		//json 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
