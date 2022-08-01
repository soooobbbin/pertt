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
  
public class UpdateReviewAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//댓글 번호
		int c_review_num = Integer.parseInt(
				request.getParameter("c_review_num"));
		//수정한 내용
		String c_review_content = request.getParameter("c_review_content");
		
		ReviewDAO dao = ReviewDAO.getInstance();
		ReviewVO db_review = dao.getReviewDetail(c_review_num);
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result","logout");
		}else if(user_num!=null 
				&& user_num == db_review.getMember_num()) {
			//로그인한 회원번호와 작성자 회원번호 일치
			dao.modifyReviewContent(0, 0, c_review_num, c_review_content, false);
			mapAjax.put("result", "success");
			
		}else {
			//로그인한 회원번호와 작성자 회원번호 불일치
			mapAjax.put("result", "wrongAccess");
		}
		
		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
