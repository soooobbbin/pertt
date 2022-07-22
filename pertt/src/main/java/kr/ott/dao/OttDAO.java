package kr.ott.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ott.vo.OttRankVO;
import kr.util.DBUtil;

public class OttDAO {
	//싱글턴 패턴
	private static OttDAO instance = new OttDAO();
	
	public static OttDAO getInstance() {
		return instance;
	}
	private OttDAO() {}
	
	//ott 별점 작성
	public void insertStar(OttRankVO ottRank)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO ott_rank (ott_rank_num, price, usability, quality, ott_num, member_num)"
					+ "VALUES ott_rank_seq.nextval, ?,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, ottRank.getPrice());
			pstmt.setInt(2, ottRank.getUsability());
			pstmt.setInt(3, ottRank.getQuality());
			pstmt.setInt(4, ottRank.getOtt_num());
			pstmt.setInt(5, ottRank.getMember_num());
			//JDBC 수행 4단계: SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//ott 별점 결과
	public OttRankVO resultOttStar(int ott_rank_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		OttRankVO ottRank = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM ott_rank WHERE ott_rank_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, ott_rank_num);
			//JDBC 수행 4단계: SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ottRank = new OttRankVO();
				ottRank.setOtt_rank_num(rs.getInt("ott_rank_num"));
				ottRank.setPrice(rs.getInt("price"));
				ottRank.setUsability(rs.getInt("usability"));
				ottRank.setQuality(rs.getInt("quality"));
				ottRank.setOtt_num(rs.getInt("ott_num"));
				ottRank.setMember_num(rs.getInt("member_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			 DBUtil.executeClose(rs, pstmt, conn);
		}
		return ottRank;
	}
	
	//ott 한줄평 등록
	//ott 한줄평 목록
	//ott 한줄평 수정
	//ott 한줄평 삭제
	
	
	
	
	
	
	
	
	
	
	
}