package kr.contents.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.oreilly.servlet.MultipartRequest;

import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class UpdatePosterAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {
			ContentsDAO dao = ContentsDAO.getInstance();
			//이미 저장한 프로필 사진이 있는지 정보를 읽어옴
			//ContentsVO db_member = dao.getMember(user_num);
			
			//전송된 파일 업로드 처리
			MultipartRequest multi = FileUtil.createFile(request);
			
			//서버에 저장된 파일명 반환
			String poster = multi.getFilesystemName("poster");
			
			//프로필 사진 수정
			dao.updatePoster(poster, user_num);
			
			//세션에 저장된 프로필 사진 정보 갱신
			session.setAttribute("poster", poster);
			
			//이전 프로필 이미지 삭제
			FileUtil.removeFile(request,db_member.getPhoto());
			
			mapAjax.put("result", "success");
			
		}
		
		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
