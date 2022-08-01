package kr.ott.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ott.vo.OttReviewVO;
import kr.ott.vo.OttStarVO;
import kr.ott.vo.OttVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class OttDAO {
	//싱글턴 패턴
	private static OttDAO instance = new OttDAO();
	
	public static OttDAO getInstance() {
		return instance;
	}
	private OttDAO() {}
	
	//별점 등록
	public void insertStar(OttStarVO ottStar)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO ott_star (ott_star_num, price, usability, quality, ott_num, member_num) "
					+ "VALUES ott_star_seq.nextval, ?,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, ottStar.getPrice());
			pstmt.setInt(2, ottStar.getUsability());
			pstmt.setInt(3, ottStar.getQuality());
			pstmt.setInt(4, ottStar.getOtt_num());
			pstmt.setInt(5, ottStar.getMember_num());
			//JDBC 수행 4단계: SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//회원번호와 OTT 번호를 이용한 별점 정보 (별점 기등록 확인)
	public OttStarVO checkStar(int ott_num, int member_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OttStarVO ottStar = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM ott_star WHERE ott_num=? AND member_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setInt(1, ott_num);
			pstmt.setInt(2, member_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ottStar = new OttStarVO();
				ottStar.setOtt_star_num(rs.getInt("ott_star_num"));
				ottStar.setOtt_num(rs.getInt("ott_num"));
				ottStar.setMember_num(rs.getInt("member_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return ottStar;
	}
	
	//별점 수정
	public void updateStar(OttStarVO ottStar)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE ott_star SET price=?,usability=?,quality=?" + sub_sql
					+ ",member_num=? WHERE ott_star_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, ottStar.getPrice());
			pstmt.setInt(++cnt, ottStar.getUsability());
			pstmt.setInt(++cnt, ottStar.getQuality());
			pstmt.setInt(++cnt, ottStar.getMember_num());
			pstmt.setInt(++cnt, ottStar.getOtt_star_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//별점 삭제
	public void deleteStar(int ott_star_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM ott_star WHERE ott_star_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, ott_star_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//내가 준 OTT 별점 목록
	public List<OttVO> getListOttStar(int start,int end,int member_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OttVO> list = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM ott o JOIN "
			+ "member m USING(member_num) JOIN ott_star s USING(ott_num) WHERE s.member_num=? "
			+ "ORDER BY ott_num DESC)a) WHERE rnum >= ? AND rnum<=?";
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, member_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<OttVO>();
			while(rs.next()) {
			OttVO ott = new OttVO();
			ott.setOtt_num(rs.getInt("ott_num"));
			ott.setOtt_name(StringUtil.useNoHtml(
			         rs.getString("ott_name")));
			ott.setMember_id(rs.getString("member_id"));
			
			list.add(ott);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//ott 별점 결과
	public OttStarVO resultOttStar(int ott_star_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		OttStarVO ottStar = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM ott_rank WHERE ott_rank_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, ott_star_num);
			//JDBC 수행 4단계: SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ottStar = new OttStarVO();
				ottStar.setOtt_star_num(rs.getInt("ott_star_num"));
				ottStar.setPrice(rs.getInt("price"));
				ottStar.setUsability(rs.getInt("usability"));
				ottStar.setQuality(rs.getInt("quality"));
				ottStar.setOtt_num(rs.getInt("ott_num"));
				ottStar.setMember_num(rs.getInt("member_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			 DBUtil.executeClose(rs, pstmt, conn);
		}
		return ottStar;
	}
	
	//ott 한줄평 등록
	public void insertOttReview(OttReviewVO ottReview) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO ott_review (ott_review_num, ott_re_content, ott_star_num, ott_num, member_num) "
					+ "VALUES (ott_review_seq.nextval,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, ottReview.getOtt_re_content());
			pstmt.setInt(2, ottReview.getOtt_star_num());
			pstmt.setInt(3, ottReview.getOtt_num());
			pstmt.setInt(4, ottReview.getMember_num());
			//SQL문 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//Ott 한줄평 개수
	public int getOttReviewCount(int ott_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT COUNT(*) FROM ott_review r "
					+ "JOIN member m ON r.member_num=m.member_num "
					+ "WHERE r.ott_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, ott_num);
			// SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			// 자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;

	}
	
	//ott 한줄평 목록
	public List<OttReviewVO> getOttReviewList(int start, int end, int ott_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<OttReviewVO> list = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
				+ "FROM (SELECT * FROM ott_review r "
				+ "JOIN member m USING (member_num) "
				+ "WHERE r.ott_num=? ORDER BY r.ott_review_num "
				+ "DESC)a) WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, ott_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<OttReviewVO>();
			while(rs.next()) {
				OttReviewVO ottReview = new OttReviewVO();
				ottReview.setOtt_review_num(rs.getInt("ott_review_num"));
				//날짜 -> 1분전, 1시간전, 1일전 형식의 문자열로 변환
				ottReview.setReg_date(
					DurationFromNow.getTimeDiffLabel(
								rs.getString("reg_date")));
				ottReview.setOtt_re_content(
						StringUtil.useBrNoHtml(
							    rs.getString("ott_re_content")));
				ottReview.setOtt_star_num(rs.getInt("ott_star_num"));
				ottReview.setOtt_num(rs.getInt("ott_num"));
				ottReview.setMember_num(rs.getInt("member_num"));
				ottReview.setId(rs.getString("id"));
				
				list.add(ottReview);
			}		
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//ott 한줄평 상세
	public OttReviewVO getOttReview(int ott_review_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OttReviewVO ottReview = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM ott_review WHERE ott_review_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setInt(1, ott_review_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ottReview = new OttReviewVO();
				ottReview.setOtt_review_num(rs.getInt("ott_review_num"));
				ottReview.setMember_num(rs.getInt("member_num"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return ottReview;
	}
	
	//ott 한줄평 삭제
	public void deleteOttReview(int ott_review_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM ott_review WHERE ott_review_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, ott_review_num);
			//SQL문 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}