package kr.contents.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.oreilly.servlet.MultipartRequest;


import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class UpdateContentsAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		
		
		//관리자로 로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		int c_num = Integer.parseInt(multi.getParameter("c_num"));
		String poster = multi.getFilesystemName("poster");
		
		ContentsDAO dao = ContentsDAO.getInstance();
		
		//수정전 데이터
		ContentsVO db_contents = dao.getContents(c_num);
		
		if(user_auth < 2) {
			//관리자로 로그인 안됨
			
			//업로드된 파일이 있으면 파일 삭제
			FileUtil.removeFile(request, poster);
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		
		ContentsVO contents = new ContentsVO();
		contents.setC_num(c_num);
		contents.setPoster(poster);
		contents.setTitle(multi.getParameter("title"));
		contents.setRelease(multi.getParameter("release"));
		contents.setCountry(multi.getParameter("country"));
		contents.setGenre(multi.getParameter("genre"));
		contents.setPlot(multi.getParameter("plot"));
		contents.setTomato(multi.getParameter("tomato"));
		contents.setProduce(multi.getParameter("produce"));
		contents.setGrade(multi.getParameter("grade"));
		contents.setCategory_num(Integer.parseInt(multi.getParameter("category_num")));
		contents.setOtt_num(Integer.parseInt(multi.getParameter("ott_num")));
		
		
		dao.updateContents(contents);
		
		if(poster!=null) {
			//새 파일로 교체할 때 원래 파일 제거
			FileUtil.removeFile(request, 
					        db_contents.getPoster());
		}
		
		return "redirect:/contents/detail.do?c_num="+c_num;
	}

}
