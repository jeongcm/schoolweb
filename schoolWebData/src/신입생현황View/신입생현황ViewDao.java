package 신입생현황View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 신입생현황ViewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from 신입생view";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 결과물 편집, 리턴
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<신입생현황View> select신입생현황View(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<신입생현황View> list = new ArrayList<신입생현황View>();

		String sql = " select * from 신입생view limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				신입생현황View 신입생현황 = new 신입생현황View();
				신입생현황.set년도(rs.getInt("년도"));
				신입생현황.set학과명(rs.getString("학과명"));
				신입생현황.set모집인원(rs.getInt("정원내모집인원"));
				신입생현황.set입학자수(rs.getInt("정원내입학자수"));
				신입생현황.set신입생충원율(rs.getFloat("신입생충원율"));
				신입생현황.setT점수(rs.getFloat("T점수"));
				신입생현황.set단과대학(rs.getString("단과대학"));

				list.add(신입생현황);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
