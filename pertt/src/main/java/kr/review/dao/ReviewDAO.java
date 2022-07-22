package kr.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.review.vo.ReviewVO;
import kr.util.DBUtil;

public class ReviewDAO {
	//싱글톤 패턴
	private static ReviewDAO instance = new ReviewDAO();
		
	public static ReviewDAO getInstance() {
		return instance;
	}
	
	private ReviewDAO() {}

	//리뷰 등록 
	public void insertReview(ReviewVO review) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql1 = "";
		String sub_sql2 = "";
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			if(review.getC_review_content() != null) {
				//리뷰 내용이 있는 경우
				sub_sql1 = "c_review_content, ";
				sub_sql2 = "?,";
			}
			sql = "insert into c_review (c_review_num, c_review_reg_date, "
					+sub_sql1 +"c_star_num, member_num, c_num) values("
					+ "?,sysdate,?,?,?"+sub_sql2 +")";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, review.getC_review_num());
			if(review.getC_review_content() != null) {
				pstmt.setString(++cnt, review.getC_review_content());
			}
			pstmt.setInt(++cnt, review.getC_star_num());
			pstmt.setInt(++cnt, review.getMember_num());
			pstmt.setInt(++cnt, review.getC_num());
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
	//글 상세 
	
	//글 수정 
	
	//글 삭제
	
	
	//좋아요 등록 
	//좋아요 개수 
	//회원번호와 게시물 번호를 이용한 좋아요 정보 구하기 
	//좋아요 삭제 
	//회원번호에 따른 좋아요 누른 게시글 목록
		
	//댓글 등록
	//댓글 개수
	//댓글 목록
	//댓글 상세
	//댓글 수정 
	//댓글 삭제
}
