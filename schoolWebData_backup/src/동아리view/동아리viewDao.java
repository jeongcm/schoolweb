package 동아리view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 동아리view.동아리view;

public class 동아리viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from 동아리view";
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

	public List<동아리view> select동아리view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<동아리view> list = new ArrayList<동아리view>();

		String sql = "select * from 동아리view limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				동아리view 동아리view = new 동아리view();
				동아리view.set년도(rs.getInt("년도"));
				동아리view.set단과대학(rs.getString("단과대학"));
				동아리view.set학과명(rs.getString("학과명"));
				동아리view.set학습(rs.getInt("학습"));
				동아리view.set취업(rs.getInt("취업"));
				동아리view.set창업(rs.getInt("창업"));
				동아리view.set문예(rs.getInt("문예"));
				동아리view.set봉사(rs.getInt("봉사"));
				동아리view.set취미(rs.getInt("취미"));
				동아리view.set기타(rs.getInt("기타"));
				동아리view.set계(rs.getFloat("계"));
				동아리view.set재학생수(rs.getInt("재학생수"));
				동아리view.set동아리참여비율(rs.getFloat("동아리참여비율"));
				동아리view.setT점수(rs.getDouble("T점수"));

				list.add(동아리view);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
