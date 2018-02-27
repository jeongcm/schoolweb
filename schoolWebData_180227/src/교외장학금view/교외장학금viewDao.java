package 교외장학금view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class 교외장학금viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 전체 행의 수를 리턴하는 메서드
	public int getCountRow() {
		String sql = "select count(*) from 교외장학금view";
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
	public List<교외장학금view> select교외장학금view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<교외장학금view> list = new ArrayList<교외장학금view>();

		String sql = "select * from 교외장학금view limit ?,?;;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			// 결과물 편집, 리턴
			while (rs.next()) {
				교외장학금view 교외장학금 = new 교외장학금view();
				교외장학금.set년도(rs.getInt("년도"));
				교외장학금.set학과명(rs.getString("학과명"));
				교외장학금.setT점수(rs.getFloat("T점수"));
				교외장학금.set교외장학금(rs.getInt("교외장학금"));
				교외장학금.set일인당교외장학금(rs.getFloat("1인당교외장학금"));
				교외장학금.set연번(rs.getInt("연번"));
				교외장학금.set단과대학(rs.getString("단과대학"));
				교외장학금.set평균(rs.getInt("평균"));
				교외장학금.set일학기(rs.getInt("1학기"));
				교외장학금.set이학기(rs.getInt("2학기"));

				list.add(교외장학금);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
