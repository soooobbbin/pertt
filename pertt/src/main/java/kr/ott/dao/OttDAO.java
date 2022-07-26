package kr.ott.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.contents.vo.ContentsVO;
import kr.ott.vo.OttRankVO;
import kr.ott.vo.OttVO;
import kr.review.vo.ReviewVO;
import kr.util.DBUtil;

public class OttDAO {
	//싱글턴 패턴
	private static OttDAO instance = new OttDAO();
	
	public static OttDAO getInstance() {
		return instance;
	}
	private OttDAO() {}
	
	//ott 별점 작성
	public void insertStar(int ott_num, int price, int usability, int quality, int member_num)throws Exception{
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
			pstmt.setInt(1, price);
			pstmt.setInt(2, usability);
			pstmt.setInt(3, quality);
			pstmt.setInt(4, ott_num);
			pstmt.setInt(5, member_num);
			//JDBC 수행 4단계: SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//ott 상세
	public OttRankVO getOttRank(int ott_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		OttRankVO ottRank = null;

		try {
			conn = DBUtil.getConnection();

			sql = "SELECT * FROM ott_rank WHERE ott_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ott_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ottRank = new OttRankVO();
				ottRank.setOtt_num(rs.getInt("ott_num"));
				ottRank.setPrice(rs.getInt("price"));
				ottRank.setUsability(rs.getInt("usability"));
				ottRank.setQuality(rs.getInt("quality"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs,pstmt,conn);
		}
		return ottRank;
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
	public List<OttRankVO> getOttReviewList(int ott_num, int startRow, int endRow) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		List<OttRankVO> list = null;
		OttRankVO ottRank= null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from (select a.*, rownum rnum from "
					+ "(select * from ott_review where ott_num = ?)a) "
					+ "where rnum >= ? and rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ott_num);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			list = new ArrayList<OttRankVO>();
			while(rs.next()) {
				ottRank = new OttRankVO();
				ottRank.setOtt_num(rs.getInt("ott_num"));
				ottRank.setOtt_review(rs.getString("ott_review"));
				ottRank.setReg_date(rs.getDate("Reg_date"));
				ottRank.setMember_num(rs.getInt("member_num"));
				list.add(ottRank);
			}		
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//각 ott 한줄평 수
	public int getOttReviewCount(int ott_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "select count(*) from ott_review where ott_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ott_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}

	
	//ott 한줄평 수정
	//ott 한줄평 삭제
	
	
	
	
	
	
	
	
	
	
	
}