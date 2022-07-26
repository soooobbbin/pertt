package kr.member.action;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;


import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class AdminRegisterContentsAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
	
		//로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		ContentsVO contents = new ContentsVO();
		contents.setTitle(multi.getParameter("title"));
		contents.setPoster(multi.getFilesystemName("poster"));
		contents.setRelease(multi.getParameter("release"));
		contents.setCountry(multi.getParameter("country"));
		contents.setGenre(multi.getParameter("genre"));
		contents.setTomato(multi.getParameter("tomato"));
		contents.setPlot(multi.getParameter("plot"));
		contents.setProduce(multi.getParameter("produce"));
		contents.setGrade(multi.getParameter("grade"));
		contents.setCategory_num(Integer.parseInt(multi.getParameter("category_num")));
		contents.setOtt_num(Integer.parseInt(multi.getParameter("ott_num")));
		
		ContentsDAO dao = ContentsDAO.getInstance();
		dao.insertContents(contents);
		
		return "/WEB-INF/views/member/adminRegisterContents.jsp";
	}

}
