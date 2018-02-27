package 전임교원1인당국책사업수주실적View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 수주실적ViewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() { 
		String sql = "select count(*) from 국책사업수주실적view; ";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<수주실적View> select수주실적view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<수주실적View> list = new ArrayList<수주실적View>();

		String sql = " select * from 국책사업수주실적view limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			
				수주실적View 수주실적View=new 수주실적View();
				수주실적View.set년도(rs.getInt("년도"));
				수주실적View.set전임교원수(rs.getInt("전임교원수"));
				수주실적View.set학과명(rs.getString("학과명"));
				수주실적View.set단과대학(rs.getString("단과대학"));
				수주실적View.setT점수(rs.getFloat("T점수"));
				수주실적View.set대학명의국책사업수주총액(rs.getInt("대학명의국책사업수주총액"));
				수주실적View.set전임교원1인당국책사업수주총액(rs.getFloat("전임교원1인당국책사업수주총액"));

				list.add(수주실적View);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
