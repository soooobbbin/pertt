package kr.ott.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class OttIntroAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//JSP 경로 반환
		return "/WEB-INF/views/ott/intro.jsp";
	}
}
