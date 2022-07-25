package kr.contents.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.contents.vo.CategoryVO;
import kr.contents.vo.ContentsVO;
import kr.util.DBUtil;

public class ContentsDAO {
	//싱글턴 패턴
	private static ContentsDAO instance = new ContentsDAO();

	public static ContentsDAO getInstance() {
		return instance;
	}

	private ContentsDAO() {}

	//작품 등록
	public void insertContents(ContentsVO contents) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO contents (c_num,title,poster,release,country,genre,tomato,"
					+ "plot,produce,grade,category_num,ott_num) "
					+ "VALUES (contents_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)";
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, contents.getTitle());
			pstmt.setString(2, contents.getPoster());
			pstmt.setDate(3, contents.getRelease());
			pstmt.setString(4, contents.getCountry());
			pstmt.setString(5, contents.getGenre());
			pstmt.setInt(6, contents.getTomato());
			pstmt.setString(7, contents.getPlot());
			pstmt.setString(8, contents.getProduce());
			pstmt.setInt(9, contents.getGrade());
			pstmt.setInt(10, contents.getCategory_num());
			pstmt.setInt(11, contents.getOtt_num());

			//JDBC 수행 4단계 : SQL문 실행
			pstmt.executeUpdate();

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//총 레코드 수(검색 레코드 수)
	public int getContentsCount(String keyfield,String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;

		try {
			conn = DBUtil.getConnection();

			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE c.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE c.genre LIKE ?";
				else if(keyfield.equals("3")) sub_sql = "WHERE c.produce LIKE ?";
			}
			sql = "SELECT COUNT(*) FROM contents c "+sub_sql;

			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(1, "%"+keyword+"%");
			}

			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return count;
	}
	//작품 목록(검색글 목록)
	public List<ContentsVO> getListContents(int start, int end, 
			String keyfield, String keyword, int category_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ContentsVO> list = null;
		String sql = null;
		String sub_sql = "WHERE category_num = ?";
		int cnt = 0;

		try {
			conn = DBUtil.getConnection();
			
			
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += " AND c.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql += " AND c.genre LIKE ?";
				else if(keyfield.equals("3")) sub_sql += " AND c.produce LIKE ?";
				else if(keyfield.equals("4")) sub_sql += " AND c.category_num LIKE ?";
			}

			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+"FROM(SELECT * FROM contents c " + sub_sql+ " ORDER BY c_num DESC)a) "
					+"WHERE rnum >= ? AND rnum <= ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(++cnt, category_num);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);

			rs = pstmt.executeQuery();
			list = new ArrayList<ContentsVO>();
			while(rs.next()) {
				ContentsVO contents = new ContentsVO();
				contents.setC_num(rs.getInt("c_num"));
				contents.setTitle(rs.getString("title"));
				contents.setPoster(rs.getString("poster"));
				contents.setRelease(rs.getDate("release"));
				contents.setCountry(rs.getString("country"));
				contents.setGenre(rs.getString("genre"));
				contents.setTomato(rs.getInt("tomato"));
				contents.setPlot(rs.getString("plot"));
				contents.setProduce(rs.getString("produce"));
				contents.setGrade(rs.getInt("grade"));
				contents.setCategory_num(rs.getInt("category_num"));
				contents.setOtt_num(rs.getInt("ott_num"));

				list.add(contents);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//작품 목록(카테고리별)
	public List<ContentsVO> getContents2(int category_num,int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<ContentsVO> list = null;
		ContentsVO contents = null;
		int cnt= 0;

		try {
			conn = DBUtil.getConnection();

			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+"FROM(SELECT * FROM contents c WHERE category_num=? ORDER BY c_num DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, category_num);
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<ContentsVO>();
			if(rs.next()) {
				contents = new ContentsVO();
				contents.setC_num(rs.getInt("c_num"));
				//contents.setTitle(rs.getString("title"));
				contents.setPoster(rs.getString("poster"));
				//contents.setCategory_name(rs.getString("category_name"));
				contents.setCategory_num(rs.getInt("category_num"));
				
				list.add(contents);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs,pstmt,conn);
		}
		return list;
	}
	//작품 상세
	public ContentsVO getContents(int c_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ContentsVO contents = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM contents WHERE c_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				contents = new ContentsVO();
				contents.setC_num(rs.getInt("c_num"));
				contents.setTitle(rs.getString("title"));
				contents.setPoster(rs.getString("poster"));
				contents.setRelease(rs.getDate("release"));
				contents.setCountry(rs.getString("country"));
				contents.setGenre(rs.getString("genre"));
				contents.setTomato(rs.getInt("tomato"));
				contents.setPlot(rs.getString("plot"));
				contents.setProduce(rs.getString("produce"));
				contents.setGrade(rs.getInt("grade"));
				contents.setCategory_num(rs.getInt("category_num"));
				contents.setOtt_num(rs.getInt("ott_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs,pstmt,conn);
		}
		return contents;
	}

	//작품 수정
	public void updateContents(ContentsVO contentsVO) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();

			sql = "UPDATE mvBoard SET title=?,poster=?,release=?,country=?,genre=?,"
					+ "tomato=?,plot=?,produce=?,grade=?,category_num=?,ott_num=? WHERE c_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, contentsVO.getTitle());
			pstmt.setString(2, contentsVO.getPoster());
			pstmt.setDate(3, contentsVO.getRelease());
			pstmt.setString(4, contentsVO.getCountry());
			pstmt.setString(5, contentsVO.getGenre());
			pstmt.setInt(6, contentsVO.getTomato());
			pstmt.setString(7, contentsVO.getPlot());
			pstmt.setString(8, contentsVO.getProduce());
			pstmt.setInt(9, contentsVO.getGrade());
			pstmt.setInt(10, contentsVO.getCategory_num());
			pstmt.setInt(11, contentsVO.getOtt_num());
			pstmt.setInt(12, contentsVO.getC_num());

			pstmt.executeUpdate();

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null,pstmt,conn);
		}
	}

	//작품 삭제
	public void deleteContents(int c_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();

			sql = "DELETE FROM contents WHERE c_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_num);

			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null,pstmt,conn);
		}
	}



}