package kr.util;

public class StringUtil {
//사용자가 쓴 html 태그가 사이트에 적용되지 않게 하고 \n을 <br>로 바꿔줌
	//html 허용하면서 줄바꿈 처리
	public static String useBrHtml(String str) {
		if(str == null) return null;
		//줄바꿈이 \r\n \r \n 세 경우가 있음 replaceAll은 여러번 사용 가능
		return str.replaceAll("\r\n", "<br>")
				.replaceAll("\r", "<br>")
				.replaceAll("\n", "<br>");
	}
	//html 불허하면서 줄바꿈
	public static String useBrNoHtml(String str) {
		if(str == null) return null;
		//태그를 무력화하기위해 <>를 기호 표시로 바꿈
		return str.replace("<", "&lt;")
				.replace("<", "&gt;")
				.replaceAll("\r\n", "<br>")
				.replaceAll("\r", "<br>")
				.replaceAll("\n", "<br>");
	}
	
	//줄바꿈없이 html 불허
	public static String useNoHtml(String str) {
		if(str == null) return null;
		//태그를 무력화하기위해 <>를 기호 표시로 바꿈
		return str.replace("<", "&lt;")
				.replace("<", "&gt;");
	}
	
	//지정한 문자 개수 이후에 ... 처리 
	public static String shortWord(int length, String content) {
		if(content == null) return null;
		//태그를 무력화하기위해 <>를 기호 표시로 바꿈
		if(content.length() > length) {
			return content.substring(0, length) + " ... ";
		}
		return content;
	}
}
