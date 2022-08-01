package kr.review.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.review.dao.ReviewDAO;
import kr.review.vo.CommentVO;

public class WriteCommentAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax  = new HashMap<String, String>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			mapAjax.put("result", "logout");
		} else{
			request.setCharacterEncoding("utf-8");
			CommentVO comment = new CommentVO();
			comment.setMember_num(user_num);
			comment.setCom_content(request.getParameter("com_content"));
			comment.setC_review_num(Integer.parseInt(request.getParameter("c_review_num")));
			comment.setC_num(Integer.parseInt(request.getParameter("c_num")));
			//
			ReviewDAO dao = ReviewDAO.getInstance();
			dao.insertComment(comment);
			
			mapAjax.put("result", "success");
		}
	ObjectMapper mapper = new ObjectMapper();
	String ajaxData = mapper.writeValueAsString(mapAjax);
	request.setAttribute("ajaxData", ajaxData);
	return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
