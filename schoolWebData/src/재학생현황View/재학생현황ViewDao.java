package 재학생현황View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 재학생현황ViewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() { 
		String sql = "select count(*) from 재학생현황view; ";
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

	public List<재학생현황View> select재학생현황View(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<재학생현황View> list = new ArrayList<재학생현황View>();

		String sql = " select * from 재학생현황view limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			
				재학생현황View 재학생현황 = new 재학생현황View();
				
				재학생현황.set년도(rs.getInt("년도"));
				재학생현황.set학과명(rs.getString("학과명"));
				재학생현황.set기준(rs.getString("기준"));
				재학생현황.set학생정원(rs.getInt("학생정원"));
				재학생현황.set군휴학자(rs.getInt("군휴학자"));
				재학생현황.set정원내(rs.getInt("정원내"));
				재학생현황.set정원외(rs.getInt("정원외"));
				재학생현황.set계(rs.getInt("계"));
				재학생현황.set재학생충원율(rs.getFloat("재학생충원율"));
				재학생현황.set전체재학생충원율(rs.getFloat("전체재학생충원율"));
				재학생현황.set정원내재학생충원율(rs.getFloat("정원내재학생충원율"));
				재학생현황.setT점수(rs.getFloat("T점수"));
				재학생현황.set단과대학(rs.getString("단과대학"));
				재학생현황.set신설연도(rs.getString("신설연도"));
				재학생현황.set타학과전과자(rs.getInt("타학과전과자"));

				list.add(재학생현황);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
