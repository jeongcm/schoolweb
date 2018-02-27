package 봉사실적view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 봉사실적view.봉사실적view;

public class 봉사실적viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from 봉사실적view";
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

	public List<봉사실적view> select봉사실적view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<봉사실적view> list = new ArrayList<봉사실적view>();

		String sql = "select * from 봉사실적view limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				봉사실적view 봉사실적view = new 봉사실적view();
				봉사실적view.set년도(rs.getInt("년도"));
				봉사실적view.set단과대학(rs.getString("단과대학"));
				봉사실적view.set학과명(rs.getString("학과명"));
				봉사실적view.set이수1학기(rs.getInt("이수1학기"));
				봉사실적view.set재학생1학기(rs.getInt("재학생1학기"));
				봉사실적view.set이수2학기(rs.getInt("이수2학기"));
				봉사실적view.set재학생2학기(rs.getInt("재학생2학기"));
				봉사실적view.set이수학점(rs.getFloat("이수학점"));
				봉사실적view.setT점수(rs.getDouble("T점수"));

				list.add(봉사실적view);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}