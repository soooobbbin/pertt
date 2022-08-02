package kr.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.contents.dao.ContentsDAO;
import kr.contents.vo.ContentsVO;
import kr.review.vo.CommentVO;
import kr.review.vo.LikeVO;
import kr.review.vo.ReviewVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class ReviewDAO {
	//  싱글톤 패턴 
	private static ReviewDAO instance = new ReviewDAO();

	public static ReviewDAO getInstance() {
		return instance;
	}

	private ReviewDAO() {
	}

	// 별점 등록 (star + reivew content 제외)
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

			// 시퀀스에서 c_star_num 구하기
			sql = "select c_star_seq.nextval from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				c_star_num = rs.getInt(1);
			}

			// star 테이블에 저장
			sql = "insert into c_star (c_star_num,star,c_num,member_num) " + "values(?,?,?,?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, c_star_num);
			pstmt2.setInt(2, review.getStar());
			pstmt2.setInt(3, review.getC_num());
			pstmt2.setInt(4, review.getMember_num());
			pstmt2.executeUpdate();

			// review 테이블에 저장
			sql = "insert into c_review (c_review_num, c_review_reg_date, " + "c_star_num, member_num, c_num) values("
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
	
	//별점 수정
	public void updateStar(int member_num, int c_num, int star) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "update c_star set star=? where member_num=? and c_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, star);
			pstmt.setInt(2, member_num);
			pstmt.setInt(3, c_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//작품별 별점 평균 구하기
	public double getStarAvg(int c_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		double starAvg = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "select avg(star), count(*) from c_star where c_num =? and star is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(2) == 0) {
					starAvg = -1;
				} else starAvg = rs.getDouble(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return starAvg;
	}
	
	//리뷰 등록&수정
	public void modifyReviewContent(int c_num, int member_num, int c_review_num, String c_review_content, boolean isFirst) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		String sub_sql2 = "";
		int cnt = 0;
		try {
			if(isFirst) {
				sub_sql2 = "member_num=? and c_num=?";
			} else {
				sub_sql=", c_review_mod_date=sysdate";
				sub_sql2 = "c_review_num = ?";
				}
			conn = DBUtil.getConnection();
			sql = "update c_review set c_review_content=?" 
					+sub_sql + " where "+sub_sql2;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, c_review_content);
			if(isFirst) {
				pstmt.setInt(++cnt, member_num);
				pstmt.setInt(++cnt, c_num);
			} else {
				pstmt.setInt(++cnt, c_review_num);
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// member_num으로 id 구하기
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

	// 한 작품에 담긴 총 리뷰 수
	public int getReviewCount(int c_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "select count(*) from c_review where c_num=? and c_review_content is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}

	// 글 목록(최신순(1) 추천순(2) 댓글순(3) 정렬)정렬은 추후에
	public List<ReviewVO> getReviewList(int c_num, int start, int end, String sort) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		List<ReviewVO> list = null;
		ReviewVO review = null;
		try {
			if(sort.equals("1")) {
				sub_sql = "c_review_num";
			} else if(sort.equals("2")) {
				sub_sql = "like_cnt";
			} else if(sort.equals("3")) {
				sub_sql = "com_cnt";
			}
			conn = DBUtil.getConnection();
			sql = "select * from (select a.*, rownum rnum from (select * from c_review "
				+ "LEFT OUTER JOIN (SELECT c_review_num, COUNT(*) com_cnt FROM "
				+ "c_review_com GROUP BY c_review_num) USING(c_review_num) "
				+ "LEFT OUTER JOIN (SELECT c_review_num, COUNT(*) like_cnt "
				+ "FROM r_like GROUP BY c_review_num) USING(c_review_num) "
				+ "where c_num = ? and c_review_content is not null "
				+ "ORDER BY " +sub_sql +" DESC NULLS LAST)a) where rnum >= ? and rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<ReviewVO>();
			while (rs.next()) {
				review = new ReviewVO();
				review.setC_num(rs.getInt("c_num"));
				review.setC_review_content(StringUtil.useBrNoHtml(rs.getString("c_review_content")));
				review.setC_review_mod_date(rs.getDate("c_review_mod_date"));
				review.setC_review_num(rs.getInt("c_review_num"));
				review.setC_review_reg_date(rs.getDate("c_review_reg_date"));
				review.setC_star_num(rs.getInt("c_star_num"));
				review.setMember_num(rs.getInt("member_num"));
				review.setId(getIdByMemberNum(rs.getInt("member_num")));
				review.setLcount(selectLikeCount(rs.getInt("c_review_num")));
				review.setStar(getStarByC_star_num(rs.getInt("c_star_num")));
				list.add(review);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	// star_num으로 star 구하기
	public int getStarByC_star_num(int c_star_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int star = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "select star from c_star where c_star_num =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_star_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				 star = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return star;
	}
	
	// 리뷰 상세
	public ReviewVO getReviewDetail(int c_review_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ReviewVO review = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from c_review join c_star using (c_star_num) " + "where c_review_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_review_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				review = new ReviewVO();
				review.setC_num(rs.getInt("c_num"));
				review.setC_review_content(rs.getString("c_review_content"));
				review.setC_review_num(rs.getInt("c_review_num"));
				review.setC_review_reg_date(rs.getDate("c_review_reg_date"));
				review.setC_star_num(rs.getInt("c_star_num"));
				review.setMember_num(rs.getInt("member_num"));
				if(rs.getDate("c_review_mod_date") != null) {
					review.setC_review_mod_date(rs.getDate("c_review_mod_date"));
				}
				// 메소드로 id 받아오기
				review.setId(getIdByMemberNum(rs.getInt("member_num")));
				// star테이블 join
				review.setStar(rs.getInt("star"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return review;
	}

	// 글 삭제
	public void deleteReview(int c_review_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			// 오토커밋 해제
			conn.setAutoCommit(false);
			// 댓글 삭제
			sql = "delete from c_review_com where c_review_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_review_num);
			pstmt.executeUpdate();
			
			// 좋아요 삭제
			sql = "delete from r_like where c_review_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, c_review_num);
			pstmt2.executeUpdate();

			sql = "update c_review set c_review_content=null where c_review_num = ?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, c_review_num);
			pstmt3.executeUpdate();
			// 예외발생없이 정상실행된경우 commit
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
	
	// 한 작품에 담긴 총 리뷰 수
	public int getMyReviewCount(int member_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "select count(*) from c_review where member_num=? and c_review_content is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
		
	//내가 쓴 리뷰 리스트 반환 (sort=1(별점순) sort=2(최신순))
	public List<ReviewVO> selectMyReview(int member_num, int start, int end, String sort) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		List<ReviewVO> list = null;
		ReviewVO review = null;
		try {
			if(sort.equals("1")) {
				sub_sql = "s.star";
			} else if(sort.equals("2")) {
				sub_sql = "r.c_review_num";
			}
			
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum " 
					+ "FROM (SELECT * FROM c_review r JOIN c_star s "
					+ "USING (c_star_num) where s.member_num=? and r.c_review_content is not null "
					+ "order by " + sub_sql +" DESC)a) " 
					+ "WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<ReviewVO>();
			while (rs.next()) {
				review = new ReviewVO();
				review.setC_num(rs.getInt("c_num"));
				review.setC_review_content(StringUtil.useBrNoHtml(rs.getString("c_review_content")));
				review.setC_review_mod_date(rs.getDate("c_review_mod_date"));
				review.setC_review_num(rs.getInt("c_review_num"));
				review.setC_review_reg_date(rs.getDate("c_review_reg_date"));
				review.setC_star_num(rs.getInt("c_star_num"));
				review.setMember_num(rs.getInt("member_num"));
				review.setId(getIdByMemberNum(rs.getInt("member_num")));
				review.setLcount(selectLikeCount(rs.getInt("c_review_num")));
				review.setStar(getStarByC_star_num(rs.getInt("c_star_num")));
				//작품 번호로 작품 포스터/ott_num 받아오기
				ContentsDAO cDao = ContentsDAO.getInstance();
				ContentsVO contents = cDao.getContents(rs.getInt("c_num"));
				review.setPoster(contents.getPoster());
				review.setOtt_num(contents.getOtt_num());
				list.add(review);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
		
	//내가 쓴 댓글 리스트 반환 (정렬 없음)
	public List<CommentVO> selectMyComment(int member_num, int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<CommentVO> list = null;
		CommentVO comment = null;
		try {	
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum " 
					+ "FROM (SELECT * FROM c_review_com "
					+ "where member_num =? order by com_num DESC)a) " 
					+ "WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<CommentVO>();
			while (rs.next()) {
				comment = new CommentVO();
				comment.setCom_num(rs.getInt("com_num"));
				comment.setCom_content(StringUtil.useBrNoHtml(rs.getString("com_content")));
				// 날짜 -> 1분전, 1시간전, 1일전 형식의 문자열로 변환
				comment.setCom_reg_date(DurationFromNow.getTimeDiffLabel(rs.getString("com_reg_date")));
				comment.setC_review_num(rs.getInt("c_review_num"));
				comment.setMember_num(rs.getInt("member_num"));
				comment.setC_num(rs.getInt("c_num"));
				comment.setId(getIdByMemberNum(rs.getInt("member_num")));
				ReviewVO reivew = getReviewDetail(rs.getInt("c_review_num"));
				comment.setReview(reivew);
				list.add(comment);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	
	// 내 별점과 리뷰 검색 (리뷰 없으면 content null로 반환됨)
	public ReviewVO selectMyStar(int member_num, int c_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ReviewVO review = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from c_star s join c_review r using (c_star_num) " + "where s.member_num=? and s.c_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.setInt(2, c_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				review = new ReviewVO();
				review.setC_num(rs.getInt("c_num"));
				if (rs.getString("c_review_content") != null) {
					review.setC_review_content(StringUtil.useBrNoHtml(rs.getString("c_review_content")));
				}
				review.setC_review_num(rs.getInt("c_review_num"));
				review.setC_review_reg_date(rs.getDate("c_review_reg_date"));
				review.setC_review_mod_date(rs.getDate("c_review_mod_date"));
				review.setC_star_num(rs.getInt("c_star_num"));
				review.setMember_num(rs.getInt("member_num"));
				review.setId(getIdByMemberNum(rs.getInt("member_num")));
				review.setStar(rs.getInt("star"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return review;
	}

	// 내 리뷰 검색 (해당 작품에 리뷰 쓴 적 있는지 확인)
	public boolean checkReview(int member_num, int c_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean check = false;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from c_review " + "where member_num=? and c_num=? " + "and c_review_content is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.setInt(2, c_num);
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

	// =================좋아요=====================
	// 좋아요 등록
	public void insertLike(int c_review_num, int member_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO r_like (r_like_num,c_review_num," + "member_num) VALUES (r_like_seq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_review_num);
			pstmt.setInt(2, member_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 좋아요 개수
	public int selectLikeCount(int c_review_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM r_like WHERE c_review_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_review_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}

	// 회원번호와 게시물 번호를 이용한 좋아요 정보
	public LikeVO selectLike(int r_review_num, int member_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LikeVO like = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM r_like WHERE c_review_num=? AND member_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r_review_num);
			pstmt.setInt(2, member_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				like = new LikeVO();
				like.setR_like_num(rs.getInt("r_like_num"));
				like.setC_review_num(rs.getInt("c_review_num"));
				like.setMember_num(rs.getInt("member_num"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return like;
	}

	// 좋아요 삭제
	public void deleteLike(int r_like_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM r_like WHERE r_like_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r_like_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 내가 선택한 좋아요 목록
	public List<ReviewVO> getListBoardLike(int start, int end, int member_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReviewVO> list = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum " + "FROM (SELECT * FROM c_review b JOIN "
					+ "member m USING(member_num) JOIN r_like l " + "USING(c_review_num) WHERE l.member_num=? "
					+ "ORDER BY c_review_num DESC)a) " + "WHERE rnum >= ? AND rnum<=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();
			list = new ArrayList<ReviewVO>();
			while (rs.next()) {
				ReviewVO review = new ReviewVO();
				review.setC_review_num(rs.getInt("c_review_num"));
				review.setC_num(rs.getInt("c_num"));
				review.setC_star_num(rs.getInt("c_star_num"));
				review.setC_review_reg_date(rs.getDate("c_review_reg_date"));
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

	// 댓글 등록
	public void insertComment(CommentVO comment) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO c_review_com (com_num,com_reg_date," 
					+ "com_content,c_review_num,member_num,c_num) "
					+ "VALUES (c_review_com_seq.nextval,sysdate,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getCom_content());
			pstmt.setInt(2, comment.getC_review_num());
			pstmt.setInt(3, comment.getMember_num());
			pstmt.setInt(4, comment.getC_num());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 댓글 개수
	public int getCommentCount(int c_review_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM c_review_com b " 
					+ "WHERE b.c_review_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_review_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}

	// 댓글 목록
	public List<CommentVO> getListComment(int start, int end, int c_review_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CommentVO> list = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum " 
					+ "FROM (SELECT * FROM c_review_com b "
					+ "WHERE b.c_review_num=? ORDER BY b.com_num " 
					+ "DESC)a) WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_review_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<CommentVO>();
			while (rs.next()) {
				CommentVO comment = new CommentVO();
				comment.setCom_num(rs.getInt("com_num"));
				comment.setCom_content(StringUtil.useBrNoHtml(rs.getString("com_content")));
				// 날짜 -> 1분전, 1시간전, 1일전 형식의 문자열로 변환
				comment.setCom_reg_date(DurationFromNow.getTimeDiffLabel(rs.getString("com_reg_date")));
				comment.setC_review_num(rs.getInt("c_review_num"));
				comment.setMember_num(rs.getInt("member_num"));
				comment.setC_num(rs.getInt("c_num"));
				comment.setId(getIdByMemberNum(rs.getInt("member_num")));
				list.add(comment);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return list;
	}

	// 댓글 상세
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
			if (rs.next()) {
				comment = new CommentVO();
				comment.setCom_num(rs.getInt("com_num"));
				comment.setCom_content(StringUtil.useBrNoHtml(rs.getString("com_content")));
				// 날짜 -> 1분전, 1시간전, 1일전 형식의 문자열로 변환
				comment.setCom_reg_date(DurationFromNow.getTimeDiffLabel(rs.getString("com_reg_date")));
				comment.setC_review_num(rs.getInt("c_review_num"));
				comment.setMember_num(rs.getInt("member_num"));
				comment.setC_num(rs.getInt("c_num"));
				comment.setId(getIdByMemberNum(rs.getInt("member_num")));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return comment;
	}

	// 댓글 삭제
	public void deleteComment(int com_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM c_review_com WHERE com_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, com_num);
			pstmt.executeUpdate();

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
