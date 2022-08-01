package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.contents.vo.CategoryVO;
import kr.contents.vo.ContentsVO;
import kr.util.DBUtil;

public class AdminDAO {
	//싱글턴 패턴
	private static AdminDAO instance = new AdminDAO();
	
	public static AdminDAO getInstance() {
		return instance;
	}
	private AdminDAO() {}
	
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
	public List<ContentsVO> getListContentsByAdmin(int start, int end, 
			String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ContentsVO> list = null;
		String sql = null;
		String sub_sql ="";
		int cnt = 0;

		try {
			conn = DBUtil.getConnection();

			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += " WHERE c.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql += " WHERE c.genre LIKE ?";
				else if(keyfield.equals("3")) sub_sql += " WHERE c.produce LIKE ?";
			}

			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+"FROM(SELECT * FROM contents c " + sub_sql+ " ORDER BY c_num DESC)a) "
					+"WHERE rnum >= ? AND rnum <= ?";

			pstmt = conn.prepareStatement(sql);

			
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
				contents.setRelease(rs.getString("release"));
				contents.setCountry(rs.getString("country"));
				contents.setGenre(rs.getString("genre"));
				contents.setTomato(rs.getString("tomato"));
				contents.setPlot(rs.getString("plot"));
				contents.setProduce(rs.getString("produce"));
				contents.setGrade(rs.getString("grade"));
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

	//관리자 헤더 카테고리 - ott 분류
	public List<ContentsVO> getOttGroup(
			           int start,int end,
			           String keyfield, String keyword)
	                        throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ContentsVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("1")) sub_sql = "WHERE ott_name LIKE ? ";
			}
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
				+ "(SELECT * FROM contents c LEFT OUTER JOIN "
				+ "ott o USING (ott_num)  " + sub_sql
				+ " ORDER BY c_num NULLS LAST)a) "
				+ "WHERE rnum >= ? AND rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
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
				contents.setRelease(rs.getString("release"));
				contents.setCountry(rs.getString("country"));
				contents.setGenre(rs.getString("genre"));
				contents.setPlot(rs.getString("plot"));
				contents.setTomato(rs.getString("tomato"));
				contents.setProduce(rs.getString("produce"));
				contents.setGrade(rs.getString("grade"));
				contents.setOtt_num(rs.getInt("ott_num"));
				
				list.add(contents);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
		}
		
		//ott별 레코드 수(검색 레코드 수)
		public int getOttGroupCount(String keyfield,String keyword)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			String sub_sql = "";
			int count = 0;
			
			try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
			//검색 처리
			if(keyfield.equals("1")) sub_sql = "WHERE ott_name LIKE ?";
			}
			
			sql = "SELECT COUNT(*) FROM contents LEFT OUTER JOIN "
			+ "ott USING (ott_num) " + sub_sql;
			
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
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
				return count;
			}
		
		//관리자 헤더 카테고리 - 장르 분류
		public List<ContentsVO> getGenreGroup(
				           int start,int end,
				           String keyfield, String keyword)
		                        throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ContentsVO> list = null;
			String sql = null;
			String sub_sql = "";
			int cnt = 0;
			
			try {
				//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				
				if(keyword!=null && !"".equals(keyword)) {
					//검색 처리
					if(keyfield.equals("1")) sub_sql = "WHERE ott_name LIKE ? ";
				}
				
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM contents c LEFT OUTER JOIN "
					+ "ott o USING (ott_num)  " + sub_sql
					+ " ORDER BY c_num DESC NULLS LAST)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				if(keyword!=null && !"".equals(keyword)) {
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
					contents.setRelease(rs.getString("release"));
					contents.setCountry(rs.getString("country"));
					contents.setGenre(rs.getString("genre"));
					contents.setPlot(rs.getString("plot"));
					contents.setTomato(rs.getString("tomato"));
					contents.setProduce(rs.getString("produce"));
					contents.setGrade(rs.getString("grade"));
					contents.setOtt_num(rs.getInt("ott_num"));
					
					list.add(contents);
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
			}
			
			//장르별 레코드 수(검색 레코드 수)
			public int getGenreGroupCount(String keyfield,String keyword)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = null;
				String sub_sql = "";
				int count = 0;
				
				try {
				//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				
				if(keyword!=null && !"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("1")) sub_sql = "WHERE ott_name LIKE ?";
				}
				
				sql = "SELECT COUNT(*) FROM contents LEFT OUTER JOIN "
				+ "ott USING (ott_num) " + sub_sql;
				
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
					//자원정리
					DBUtil.executeClose(rs, pstmt, conn);
				}
					return count;
				}

			
			//관리자 헤더 카테고리 - 카테고리 분류
			public List<ContentsVO> getCategoryGroup(
					           int start,int end,
					           String keyfield, String keyword,int category_num)
			                        throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<ContentsVO> list = null;
				String sql = null;
				String sub_sql = "";
				int cnt = 0;
				
				try {
					//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					
					if(keyword!=null && !"".equals(keyword)) {
						//검색 처리
						if(keyfield.equals("1")) sub_sql = "WHERE category_num = ?";
						/*
						else if(keyfield.equals("2")) sub_sql += " WHERE category_num = 2";
						else if(keyfield.equals("3")) sub_sql += " WHERE category_num = 3";
						else if(keyfield.equals("4")) sub_sql += " WHERE category_num = 4";
						else if(keyfield.equals("5")) sub_sql += " WHERE category_num = 5";
						
						else if(keyfield.equals("6")) sub_sql += " WHERE category_num = 6";
						else if(keyfield.equals("7")) sub_sql += " WHERE category_num = 7";
						else if(keyfield.equals("8")) sub_sql += " WHERE category_num = 8";
						else if(keyfield.equals("9")) sub_sql += " WHERE category_num = 9";
						else if(keyfield.equals("10")) sub_sql += " WHERE category_num = 10";
						
						else if(keyfield.equals("11")) sub_sql += " WHERE category_num = 11";
						else if(keyfield.equals("12")) sub_sql += " WHERE category_num = 12";
						else if(keyfield.equals("13")) sub_sql += " WHERE category_num = 13";
						else if(keyfield.equals("14")) sub_sql += " WHERE category_num = 14";
						else if(keyfield.equals("15")) sub_sql += " WHERE category_num = 15";
						
						else if(keyfield.equals("16")) sub_sql += " WHERE category_num = 16";
						else if(keyfield.equals("17")) sub_sql += " WHERE category_num = 17";
						else if(keyfield.equals("18")) sub_sql += " WHERE category_num = 18";
						else if(keyfield.equals("19")) sub_sql += " WHERE category_num = 19";
						else if(keyfield.equals("20")) sub_sql += " WHERE category_num = 20";
						
						else if(keyfield.equals("21")) sub_sql += " WHERE category_num = 21";
						else if(keyfield.equals("22")) sub_sql += " WHERE category_num = 22";
						else if(keyfield.equals("23")) sub_sql += " WHERE category_num = 23";
						else if(keyfield.equals("24")) sub_sql += " WHERE category_num = 24";
						else if(keyfield.equals("25")) sub_sql += " WHERE category_num = 25";
						*/
					}
					
					sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
						+ "(SELECT * FROM contents c LEFT OUTER JOIN "
						+ "category g USING (category_num) " + sub_sql + "AND c.title LIKE ?"
						+ " ORDER BY c_num NULLS LAST)a) "
						+ "WHERE rnum >= ? AND rnum <= ?";
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(++cnt, category_num);
					if(keyword!=null && !"".equals(keyword)) {
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
						contents.setRelease(rs.getString("release"));
						contents.setCountry(rs.getString("country"));
						contents.setGenre(rs.getString("genre"));
						contents.setPlot(rs.getString("plot"));
						contents.setTomato(rs.getString("tomato"));
						contents.setProduce(rs.getString("produce"));
						contents.setGrade(rs.getString("grade"));
						contents.setOtt_num(rs.getInt("ott_num"));
						contents.setCategory_num(rs.getInt("category_num"));
						contents.setCategory_name(rs.getString("category_name"));
						
						list.add(contents);
					}
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					//자원정리
					DBUtil.executeClose(rs, pstmt, conn);
				}
				return list;
				}
				
				//카테고리별 레코드 수(검색 레코드 수)
				public int getCategoryGroupCount(String keyfield,String keyword,int category_num)throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					String sql = null;
					String sub_sql = "";
					int count = 0;
					
					try {
					//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당
					conn = DBUtil.getConnection();
					
					if(keyword!=null && !"".equals(keyword)) {
					//검색 처리
					if(keyfield.equals("1")) sub_sql = "WHERE category_num = ?";
					}
					
					sql = "SELECT COUNT(*) FROM contents LEFT OUTER JOIN "
					+ "category USING (category_num) " + sub_sql + "AND title LIKE ?";
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, category_num);
					if(keyword!=null && !"".equals(keyword)) {
					pstmt.setString(2, "%"+keyword+"%");
					}
					
					rs = pstmt.executeQuery();
					if(rs.next()) {
					count = rs.getInt(1);
					}
					
					}catch(Exception e) {
						throw new Exception(e);
					}finally {
						//자원정리
						DBUtil.executeClose(rs, pstmt, conn);
					}
						return count;
					}
			
			//관리자 헤더 카테고리 - 등급 분류
			public List<ContentsVO> getGradeGroup(
					           int start,int end,
					           String keyfield, String keyword)
			                        throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<ContentsVO> list = null;
				String sql = null;
				String sub_sql = "";
				int cnt = 0;
				
				try {
					//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					
					if(keyword!=null && !"".equals(keyword)) {
						//검색 처리
						if(keyfield.equals("1")) sub_sql = "WHERE grade LIKE ? ";
					}
					
					sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
						+ "(SELECT * FROM contents c LEFT OUTER JOIN "
						+ "ott o USING (ott_num)  " + sub_sql
						+ " ORDER BY c_num NULLS LAST)a) "
						+ "WHERE rnum >= ? AND rnum <= ?";
					
					pstmt = conn.prepareStatement(sql);
					if(keyword!=null && !"".equals(keyword)) {
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
						contents.setRelease(rs.getString("release"));
						contents.setCountry(rs.getString("country"));
						contents.setGenre(rs.getString("genre"));
						contents.setPlot(rs.getString("plot"));
						contents.setTomato(rs.getString("tomato"));
						contents.setProduce(rs.getString("produce"));
						contents.setGrade(rs.getString("grade"));
						contents.setOtt_num(rs.getInt("ott_num"));
						
						list.add(contents);
					}
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					//자원정리
					DBUtil.executeClose(rs, pstmt, conn);
				}
				return list;
				}
				
				//등급별 레코드 수(검색 레코드 수)
				public int getGradeGroupCount(String keyfield,String keyword)throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					String sql = null;
					String sub_sql = "";
					int count = 0;
					
					try {
					//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션을 할당
					conn = DBUtil.getConnection();
					
					if(keyword!=null && !"".equals(keyword)) {
					//검색 처리
					if(keyfield.equals("1")) sub_sql = "WHERE grade LIKE ?";
					}
					
					sql = "SELECT COUNT(*) FROM contents LEFT OUTER JOIN "
					+ "ott USING (ott_num) " + sub_sql;
					
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
						//자원정리
						DBUtil.executeClose(rs, pstmt, conn);
					}
						return count;
					}

		
		
}
