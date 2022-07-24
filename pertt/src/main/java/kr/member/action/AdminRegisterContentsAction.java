package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;

public class AdminRegisterContentsAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//자바빈(VO) 생성
		ContentsVO contents =  new ContentsVO();
		//전송된 데이터를 자바빈에 저장
		contents.setTitle(request.getParameter("title"));
		//contents.setRelease(request.getParameter("release"));
		contents.setCountry(request.getParameter("country"));
		contents.setGenre(request.getParameter("genre"));
		//contents.setTomato(request.getParameter("tomato"));
		contents.setPlot(request.getParameter("plot"));
		contents.setProduce(request.getParameter("produce"));
		contents.setGrade(0);
		contents.setCategory_num(0);
		contents.setOtt_num(0);
		
		ContentsDAO dao = ContentsDAO.getInstance();
		dao.insertContents(contents);
		
		return "/WEB-INF/views/member/adminRegisterContents.jsp";
	}

}
