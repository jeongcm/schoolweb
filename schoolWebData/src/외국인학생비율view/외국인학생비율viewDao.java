package 외국인학생비율view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 외국인학생비율view.외국인학생비율view;

public class 외국인학생비율viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from 외국인학생view;";
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

	public List<외국인학생비율view> select외국인학생비율view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<외국인학생비율view> list = new ArrayList<외국인학생비율view>();

		String sql = "select * from 외국인학생view limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				외국인학생비율view 외국인학생비율view = new 외국인학생비율view();
				외국인학생비율view.set년도(rs.getInt("년도"));
				외국인학생비율view.set단과대학(rs.getString("단과대학"));
				외국인학생비율view.set학과명(rs.getString("학과명"));
				외국인학생비율view.set외국인학생수(rs.getInt("외국인학생수"));
				외국인학생비율view.set재학생수(rs.getInt("재학생수"));
				외국인학생비율view.set외국인학생비율(rs.getFloat("외국인학생비율"));
				외국인학생비율view.setT점수(rs.getDouble("T점수"));
				list.add(외국인학생비율view);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}