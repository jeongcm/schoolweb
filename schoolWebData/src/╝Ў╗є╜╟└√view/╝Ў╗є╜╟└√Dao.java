package 수상실적view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 수상실적view.수상실적;

public class 수상실적Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from 수상실적view";
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

	public List<수상실적> select수상실적(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<수상실적> list = new ArrayList<수상실적>();

		String sql="select * from 수상실적view limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				수상실적 수상실적 = new 수상실적();
				수상실적.set년도(rs.getInt("년도"));
				수상실적.set단과대학(rs.getString("단과대학"));
				수상실적.set학과명(rs.getString("학과명"));
				수상실적.set전임교원수(rs.getInt("전임교원수"));
				수상실적.set교원국제(rs.getFloat("교원국제"));
				수상실적.set교원전국(rs.getFloat("교원전국"));
				수상실적.set교원지역(rs.getFloat("교원지역"));
				수상실적.set재학생수(rs.getInt("재학생수"));
				수상실적.set학생국제(rs.getFloat("학생국제"));
				수상실적.set학생전국(rs.getFloat("학생전국"));
				수상실적.set학생지역(rs.getFloat("학생지역"));
				수상실적.set수상실적(rs.getFloat("수상실적"));
				수상실적.setT점수(rs.getDouble("T점수"));
				
				list.add(수상실적);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
}
