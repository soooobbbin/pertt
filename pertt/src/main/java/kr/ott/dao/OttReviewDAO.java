package kr.ott.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.util.DBUtil;

public class OttReviewDAO {
	//싱글턴 패턴
	private static OttReviewDAO instance = new OttReviewDAO();
		
	public static OttReviewDAO getInstance() {
		return instance;
	}
	private OttReviewDAO() {}
	
	// 리뷰 등록 (reivew에 content update)
	public void updateOttReview(int member_num, int ott_num, String ott_review) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE ott_review SET c_review_content=? " + "where member_num=? and c_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ott_review);
			pstmt.setInt(2, member_num);
			pstmt.setInt(3, ott_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
