package kr.ott.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ott.vo.OttReviewVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class OttDAO {
	//싱글턴 패턴
	private static OttDAO instance = new OttDAO();
	
	public static OttDAO getInstance() {
		return instance;
	}
	private OttDAO() {}
	
	//별점 등록
	public void insertStar(OttReviewVO ottReview)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			//star 테이블에 저장
			sql = "INSERT INTO ott_star (ott_star_num, price, usability, quality, ott_num, member_num) "
					+ "VALUES(ott_star_seq.nextval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ottReview.getPrice());
			pstmt.setInt(2, ottReview.getUsability());
			pstmt.setInt(3, ottReview.getQuality());
			pstmt.setInt(4, ottReview.getOtt_num());
			pstmt.setInt(5, ottReview.getMember_num());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//회원번호와 OTT 번호를 이용한 별점 정보 (별점 기등록 확인)
	public OttReviewVO checkStar(int ott_num, int member_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OttReviewVO ottReview = null;
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
				ottReview = new OttReviewVO();
				ottReview.setOtt_star_num(rs.getInt("ott_star_num"));
				ottReview.setOtt_num(rs.getInt("ott_num"));
				ottReview.setMember_num(rs.getInt("member_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return ottReview;
	}
	
	//별점 수정
	public void updateStar(int member_num, int ott_num, int price, int usability, int quality)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE ott_star SET price=? and usability=? and quality=? WHERE member_num=? and ott_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, price);
			pstmt.setInt(2, usability);
			pstmt.setInt(3, quality);
			pstmt.setInt(4, member_num);
			pstmt.setInt(5, ott_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//OTT별 가성비(별점) 평균 구하기
	public double getPriceAvg(int ott_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		double priceAvg = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "select avg(price), count(*) from ott_star "
					+ "where ott_num=? and price is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ott_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(2) == 0) {
					priceAvg = -1;
				} else priceAvg = rs.getDouble(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return priceAvg;
	}
	
	//OTT별 사용성(별점) 평균 구하기
	public double getUsabilityAvg(int ott_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		double usabilityAvg = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "select avg(usability), count(*) from ott_star "
					+ "where ott_num=? and usability is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ott_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(2) == 0) {
					usabilityAvg = -1;
				} else usabilityAvg = rs.getDouble(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return usabilityAvg;
	}
	
	//OTT별 콘텐츠(별점) 평균 구하기
	public double getQualityAvg(int ott_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		double qualityAvg = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "select avg(quality), count(*) from ott_star "
					+ "where ott_num=? and quality is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ott_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(2) == 0) {
					qualityAvg = -1;
				} else qualityAvg = rs.getDouble(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return qualityAvg;
	}
	
	//OTT별 전체 별점 평균 구하기
	public double getStarAvg(int ott_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		double starAvg = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "select round((price+usability+quality)/3) from ott_star where ott_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ott_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
					starAvg = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return starAvg;
	}
	
	// ott_star_num으로 star_avg 구하기
	public int getStarAvgByOtt_star_num(int ott_star_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int star_avg = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "select avg(price+usability+quality) from ott_star where ott_star_num =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ott_star_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				star_avg = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return star_avg;
	}
	
	// 내 별점과 리뷰 검색 (리뷰 없으면 content null로 반환됨)
	public OttReviewVO selectMyStar(int member_num, int ott_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		OttReviewVO ottReview = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from ott_star where member_num=? and ott_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.setInt(2, ott_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ottReview = new OttReviewVO();
				ottReview.setOtt_num(rs.getInt("ott_num"));
				ottReview.setOtt_star_num(rs.getInt("ott_star_num"));
				ottReview.setMember_num(rs.getInt("member_num"));
				ottReview.setId(getIdByMemberNum(rs.getInt("member_num")));
				ottReview.setPrice(rs.getInt("price"));
				ottReview.setUsability(rs.getInt("usability"));
				ottReview.setQuality(rs.getInt("quality"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return ottReview;
	}
	
	/*
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
	*/
	
	//=============별점 끝===================//
	
	//member_num으로 id 구하기
	public String getIdByMemberNum(int member_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String id = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select member_id from member where member_num =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getString(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return id;
	}
	
	
	// 리뷰 등록&수정
	public void modifyReviewContent(int ott_num, int member_num, int ott_review_num, String ott_re_content,
			boolean isFirst) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		String sub_sql2 = "";
		int cnt = 0;
		try {
			if (isFirst) {
				sub_sql2 = "member_num=? and ott_num=?";
			} else {
				sub_sql = ", ott_re_mod_date=sysdate";
				sub_sql2 = "ott_review_num = ?";
			}
			conn = DBUtil.getConnection();
			sql = "update ott_review set ott_re_content=?" + sub_sql + " where " + sub_sql2;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, ott_re_content);
			if (isFirst) {
				pstmt.setInt(++cnt, member_num);
				pstmt.setInt(++cnt, ott_num);
			} else {
				pstmt.setInt(++cnt, ott_review_num);
			}
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
	public List<OttReviewVO> getReviewList(int ott_num, int start, int end, String sort) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<OttReviewVO> list = null;
		OttReviewVO ottReview = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from (select a.*, rownum rnum from (select * from ott_review "
				+ "where ott_num=? and ott_re_content is not null "
				+ "ORDER BY DESC NULLS LAST)a) where rnum >= ? and rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ott_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<OttReviewVO>();
			while (rs.next()) {
				ottReview = new OttReviewVO();
				ottReview.setOtt_num(rs.getInt("ott_num"));
				ottReview.setOtt_re_content(StringUtil.useBrNoHtml(rs.getString("ott_re_content")));
				ottReview.setOtt_review_num(rs.getInt("ott_review_num"));
				ottReview.setOtt_re_reg_date(rs.getDate("ott_re_reg_date"));
				ottReview.setOtt_re_mod_date(rs.getDate("ott_re_mod_date"));
				ottReview.setOtt_star_num(rs.getInt("ott_star_num"));
				ottReview.setMember_num(rs.getInt("member_num"));
				ottReview.setId(getIdByMemberNum(rs.getInt("member_num")));
				ottReview.setStar_avg(getStarAvgByOtt_star_num(rs.getInt("ott_star_num")));
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
	
	// 내 리뷰 검색 (해당 작품에 리뷰 쓴 적 있는지 확인)
	public boolean checkReview(int member_num, int ott_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean check = false;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from ott_review " + "where member_num=? and ott_num=? "
					+ "and ott_re_content is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.setInt(2, ott_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				check = true;
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return check;
	}
}