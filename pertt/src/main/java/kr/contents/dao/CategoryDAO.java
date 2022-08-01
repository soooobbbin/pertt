package kr.contents.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.contents.vo.CategoryVO;
import kr.contents.vo.ContentsVO;
import kr.util.DBUtil;

public class CategoryDAO {
	//싱글턴 패턴
	private static CategoryDAO instance = new CategoryDAO();

	public static CategoryDAO getInstance() {
		return instance;
	}

	private CategoryDAO() {}

	//작품 목록(카테고리 이름)
	public List<CategoryVO> getListCategory(int ott_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<CategoryVO> list = null;
		CategoryVO category = null;

		try {
			conn = DBUtil.getConnection();

			sql = "SELECT * FROM category WHERE ott_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ott_num);
			rs = pstmt.executeQuery();
			list = new ArrayList<CategoryVO>();
			while(rs.next()) {
				category =new CategoryVO();
				category.setCategory_num(rs.getInt("category_num"));
				category.setCategory_name(rs.getString("category_name"));
				category.setOtt_num(rs.getInt("ott_num"));
			
				list.add(category);
			}

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	

}
