package kr.ott.dao;

public class OttDAO {
	//싱글턴 패턴
	private static OttDAO instance = new OttDAO();
	
	public static OttDAO getInstance() {
		return instance;
	}
	private OttDAO() {}
	
	//ott 별점
	//ott 별점 작성
	//ott 별점 결과
	
	//ott 한줄평 등록
	//ott 한줄평 목록
	//ott 한줄평 수정
	//ott 한줄평 삭제
}