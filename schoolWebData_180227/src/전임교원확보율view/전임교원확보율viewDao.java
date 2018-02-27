package 전임교원확보율view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class 전임교원확보율viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from 전임교원확보율view;";
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

	public List<전임교원확보율view> select전임교원확보율(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<전임교원확보율view> list = new ArrayList<전임교원확보율view>();

		String sql = "select * from 전임교원확보율view limit ?,?  ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				전임교원확보율view 전임교원확보율 = new 전임교원확보율view();
				전임교원확보율.set년도(rs.getInt("년도"));
				전임교원확보율.set연번(rs.getInt("연번"));
				전임교원확보율.set학과명(rs.getString("학과명"));
				전임교원확보율.set전임교원확보율(rs.getFloat("전임교원확보율"));
				전임교원확보율.setT점수(rs.getFloat("T점수"));
				전임교원확보율.set대학원생정원(rs.getInt("대학원생정원"));
				전임교원확보율.set대학원재학생(rs.getInt("대학원재학생"));
				전임교원확보율.set재학생기준전임교원(rs.getInt("재학생기준전임교원"));
				전임교원확보율.set학생정원기준전임교원(rs.getInt("학생정원기준전임교원"));
				전임교원확보율.set인정학생정원(rs.getInt("인정학생정원"));
				전임교원확보율.set교원_학부_정원(rs.getInt("교원_학부_정원"));
				전임교원확보율.set교원_학부_재학생(rs.getInt("교원_학부_재학생"));
				전임교원확보율.set교원_대학원_정원(rs.getInt("교원_대학원_정원"));
				전임교원확보율.set교원_대학원_재학생(rs.getInt("교원_대학원_재학생"));
				전임교원확보율.set교원_계_정원(rs.getInt("교원_계_정원"));
				전임교원확보율.set교원_계_재학생(rs.getInt("교원_계_재학생"));
				전임교원확보율.set학생수_정원(rs.getFloat("학생수_정원"));
				전임교원확보율.set학생수_재학생(rs.getFloat("학생수_재학생"));
				전임교원확보율.set확보율_정원(rs.getFloat("확보율_정원"));
				전임교원확보율.set확보율_재학생(rs.getFloat("확보율_재학생"));
				전임교원확보율.set단과대학(rs.getString("단과대학"));
				전임교원확보율.set신설연도(rs.getString("신설연도"));
				전임교원확보율.set학생정원(rs.getInt("학생정원"));
				전임교원확보율.set군휴학자(rs.getInt("군휴학자"));
				전임교원확보율.set재학생(rs.getInt("재학생"));
				전임교원확보율.set학생정원계(rs.getInt("학생정원계"));
				전임교원확보율.set재학생계(rs.getInt("재학생계"));
				전임교원확보율.set계열(rs.getString("_5대계열"));
				
				list.add(전임교원확보율);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
