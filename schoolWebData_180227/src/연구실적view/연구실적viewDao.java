package 연구실적view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 연구실적viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 전체 행의 수를 리턴하는 메서드
	public int getCountRow() {
		String sql = "select count(*) from 연구실적view";
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

	// 전체 행의 데이터 리스트를 리턴하는 메서드
	public List<연구실적view> select연구실적(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<연구실적view> list = new ArrayList<연구실적view>();

		String sql = "select * from 연구실적view  limit ?,?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();

			
			while (rs.next()) {
				연구실적view 연구실적 = new 연구실적view();
				연구실적.set단과대학(rs.getString("단과대학"));
				연구실적.set학문계열1(rs.getString("학문계열1"));
				연구실적.set전임교원수(rs.getInt("전임교원수"));
				연구실적.set_5대계열(rs.getString("_5대계열"));
				연구실적.set연번(rs.getInt("연번"));
				연구실적.set년도(rs.getInt("년도"));
				연구실적.set학과명(rs.getString("학과명"));
				연구실적.setSCI급(rs.getFloat("SCI급"));
				연구실적.setSCOPUS학술지(rs.getFloat("SCOPUS학술지"));
				연구실적.setT점수(rs.getFloat("T점수"));
				연구실적.set연구재단등재지(rs.getFloat("연구재단등재지"));
				연구실적.set연구재단등재후보(rs.getFloat("연구재단등재후보"));
				연구실적.set연구실적(rs.getFloat("연구실적"));
				연구실적.set역서(rs.getFloat("역서"));
				연구실적.set저서(rs.getFloat("저서"));

				list.add(연구실적);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}