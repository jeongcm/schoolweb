package 연구비view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 연구비viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 전체 행의 수를 리턴하는 메서드
	public int getCountRow() {
		String sql = "select count(*) from 연구비view";
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
	public List<연구비view> select연구비view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<연구비view> list = new ArrayList<연구비view>();

		String sql = "select * from 연구비view limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			// 결과물 편집, 리턴
			while (rs.next()) {
				연구비view 연구비 = new 연구비view();
				연구비.set단과대학(rs.getString("단과대학"));
				연구비.set학문계열(rs.getString("학문계열1"));
				연구비.set전임교원수(rs.getInt("전임교원수"));
				연구비.set년도(rs.getInt("년도"));
				연구비.set계(rs.getInt("소계"));
				연구비.set학과명(rs.getString("학과명"));
				연구비.setT점수(rs.getFloat("T점수"));
				연구비.set민간(rs.getInt("민간"));
				연구비.set연구비(rs.getFloat("연구비"));
				연구비.set외국(rs.getInt("외국"));
				연구비.set중앙정부(rs.getInt("중앙정부"));
				연구비.set지자체(rs.getInt("지자체"));
				연구비.set연번(rs.getInt("연번"));

				list.add(연구비);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
