package 발전기금조성액view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class 발전기금조성액viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 전체 행의 수를 리턴하는 메서드
	public int getCountRow() {
		String sql = "select count(*) from 발전기금view";
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

	// 전체 행의 데이터 리스트를 리턴하는 메서드
	public List<발전기금조성액view> select발전기금조성액view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<발전기금조성액view> list = new ArrayList<발전기금조성액view>();

		String sql = "select * from 발전기금view limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			// 결과물 편집, 리턴
			while (rs.next()) {
				발전기금조성액view 발전기금조성액 = new 발전기금조성액view();
				발전기금조성액.set년도(rs.getInt("년도"));
				발전기금조성액.set학과명(rs.getString("학과명"));
				발전기금조성액.setT점수(rs.getFloat("T점수"));
				발전기금조성액.set지정기부금(rs.getInt("지정기부금"));
				발전기금조성액.set발전기금조성액(rs.getFloat("발전기금조성액"));
				발전기금조성액.set연번(rs.getInt("연번"));
				발전기금조성액.set전임교원수(rs.getInt("전임교원수"));
				발전기금조성액.set단과대학(rs.getString("단과대학"));
				발전기금조성액.set발전기금모금액(rs.getInt("발전기금모금액"));

				list.add(발전기금조성액);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}