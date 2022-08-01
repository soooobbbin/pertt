package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.contents.vo.ContentsVO;
import kr.member.vo.MemberVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

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
		public int getOttGrounpCount(String keyfield,String keyword)throws Exception{
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
			public int getGenreGrounpCount(String keyfield,String keyword)throws Exception{
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
				public int getGradeGrounpCount(String keyfield,String keyword)throws Exception{
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
