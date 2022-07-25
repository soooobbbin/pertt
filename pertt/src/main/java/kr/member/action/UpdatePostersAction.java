package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.FileUtil;

public class UpdatePostersAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer member_num = (Integer)session.getAttribute("member_num");
		if(member_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {
			MemberDAO dao = MemberDAO.getInstance();
			//이미 저장한 프로필 사진이 있는지 정보를 읽어옴
			MemberVO db_member = dao.getMember(member_num);
			
			//전송된 파일 업로드 처리
			MultipartRequest multi = FileUtil.createFile(request);
			
			//서버에 저장된 파일명 반환
			String poster = multi.getFilesystemName("poster");
			
			//프로필 사진 수정
			//dao.updateMyPhoto(poster, member_num);
			
			//세션에 저장된 프로필 사진 정보 갱신
			session.setAttribute("member_photo", poster);
			
			//이전 프로필 이미지 삭제
			//FileUtil.removeFile(request,db_member.getPhoto());
			
			mapAjax.put("result", "success");
			
		}
		
		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
		
	}

}
