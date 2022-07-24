package kr.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.review.vo.CommentVO;
import kr.review.vo.ReviewVO;
import kr.util.DBUtil;

public class ReviewDAO {
	//싱글톤 패턴
	private static ReviewDAO instance = new ReviewDAO();
		
	public static ReviewDAO getInstance() {
		return instance;
	}
	
	private ReviewDAO() {}

	//별점 등록 (star + reivew content 제외) 
	public void insertStar(ReviewVO review) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		int c_star_num = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			//시퀀스에서 c_star_num 구하기
			sql = "select c_star_seq.nextval from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				c_star_num = rs.getInt(1);
			}
			
			//star 테이블에 저장
			sql = "insert into c_star (c_star_num,star,c_num,member_num) "
					+ "values(?,?,?,?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, c_star_num);
			pstmt2.setInt(2, review.getStar());
			pstmt2.setInt(3, review.getC_num());
			pstmt2.setInt(4, review.getMember_num());
			pstmt2.executeUpdate();
			
			//review 테이블에 저장
			sql = "insert into c_review (c_review_num, c_review_reg_date, "
					+"c_star_num, member_num, c_num) values("
					+ "c_review_seq.nextval,sysdate,?,?,?)";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, c_star_num);
			pstmt3.setInt(2, review.getMember_num());
			pstmt3.setInt(3, review.getC_num());
			pstmt3.executeUpdate();	
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt3, null);
		}
	}
	
	//리뷰 등록 (reivew에 content update) 
	public void updateReviewContent(int member_num, int c_num, String content) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "update c_review set c_review_content=? "
					+ "where member_num=? and c_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, member_num);
			pstmt.setInt(3, c_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
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
			if(rs.next()) {
				id = rs.getString(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return id;
	}
	
	//한 작품에 담긴 총 리뷰 수
	public int getReviewCount(int c_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "select count(*) from c_review where c_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_num);
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
	
	//글 목록(최신순(1) 추천순(2) 댓글순(3) 정렬)정렬은 추후에
	public List<ReviewVO> getReviewList(int c_num, int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		List<ReviewVO> list = null;
		ReviewVO review= null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from (select a.*, rownum rnum from "
					+ "(select * from c_review where c_num = ?)a) "
					+ "where rnum >= ? and rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<ReviewVO>();
			while(rs.next()) {
				review = new ReviewVO();
				review.setC_num(rs.getInt("c_num"));
				review.setC_review_content(rs.getString("c_review_content"));
				review.setC_review_mod_date(rs.getDate("c_review_mod_date"));
				review.setC_review_num(rs.getInt("c_review_num"));
				review.setC_review_reg_date(rs.getDate("c_review_reg_date"));
				review.setC_star_num(rs.getInt("c_star_num"));
				review.setMember_num(rs.getInt("member_num"));
				review.setId(getIdByMemberNum(rs.getInt("member_num")));
				list.add(review);
			}		
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//리뷰 상세
	public ReviewVO getReviewDetail(int c_review_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ReviewVO review = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from c_review join c_star using (star_num) "
					+ "where c_review_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_review_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				review = new ReviewVO();
				review.setC_num(rs.getInt("c_num"));
				review.setC_review_content(rs.getString("c_review_content"));
				review.setC_review_num(rs.getInt("c_review_num"));
				review.setC_review_reg_date(rs.getDate("reg_date"));
				review.setC_star_num(rs.getInt("star_num"));
				review.setMember_num(rs.getInt("member_num"));
				//메소드로 id 받아오기
				review.setId(getIdByMemberNum(rs.getInt("member_num")));
				//star테이블 join
				review.setStar(rs.getInt("star"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return review;
	}
	
	//글 수정 
	public void updateReview(String star, String content) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			//star 테이블의 star(별점값) 수정
			//review 테이블의 mod_date, content 수정
			sql = "";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글 삭제
	public void deleteReview(int c_review_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			//오토커밋 해제
			conn.setAutoCommit(false);
			//댓글 삭제
			//좋아요 삭제
			sql = "delete from r_like where c_review_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, c_review_num);
			pstmt2.executeUpdate();
			
			sql = "delete from c_review where c_review_num = ?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, c_review_num);
			pstmt3.executeUpdate();
			//예외발생없이 정상실행된경우 commit
			conn.commit();
		} catch (Exception e) {
			conn.rollback(); // 예외 발생시 rollback
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//내 별점 검색 
	
	
	//내 리뷰 검색 (해당 작품에 리뷰 쓴 적 있는지 확인)
	public ReviewVO selectMyReview(int member_num, int c_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ReviewVO review = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from c_review "
					+ "where member_num=? and c_num=? "
					+ "and c_review_content is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.setInt(2, c_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				review = new ReviewVO();
				review.setC_num(rs.getInt("c_num"));
				review.setC_review_content(rs.getString("c_review_content"));
				review.setC_review_num(rs.getInt("c_review_num"));
				review.setC_review_reg_date(rs.getDate("c_review_reg_date"));
				review.setC_review_mod_date(rs.getDate("c_review_mod_date"));
				review.setC_star_num(rs.getInt("c_star_num"));
				review.setMember_num(rs.getInt("member_num"));
				review.setId(getIdByMemberNum(rs.getInt("member_num")));
				//star값만 없음
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return review;
	}
	
	
	//좋아요 등록 
	//좋아요 개수 
	//회원번호와 게시물 번호를 이용한 좋아요 정보 구하기 
	//좋아요 삭제 
	//회원번호에 따른 좋아요 누른 게시글 목록
		
	//댓글 등록
	//댓글 개수
	//댓글 목록
	
	//댓글 상세 
	public CommentVO getCommentDetail(int com_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		CommentVO comment = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from c_review_com where com_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, com_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				comment = new CommentVO();
				comment.setC_num(rs.getInt("c_num"));
				comment.setC_review_num(rs.getInt("c_review_num"));
				comment.setCom_contents(rs.getString("com_contents"));
				comment.setCom_num(rs.getInt("com_num"));
				comment.setCom_reg_date(rs.getDate("com_reg_date"));
				comment.setMember_num(rs.getInt("member_num"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return comment;
	}
		
	//댓글 수정 
	//댓글 삭제
}
