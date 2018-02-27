package 설문조사view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 설문조사view.설문조사view;


public class 설문조사viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;

	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from 설문조사view";
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

	public List<설문조사view> select설문조사view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<설문조사view> list = new ArrayList<설문조사view>();

		String sql="select * from 설문조사view limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);
			

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				설문조사view 설문조사view = new 설문조사view();
				설문조사view.set년도(rs.getInt("년도"));
				설문조사view.set단과대학(rs.getString("단과대학"));
				설문조사view.set학과명(rs.getString("학과명"));
				설문조사view.set참여학생수(rs.getInt("참여학생수"));
				설문조사view.set설문조사총점(rs.getFloat("설문조사총점"));
				설문조사view.set학생만족도평가(rs.getFloat("학생만족도평가"));
				설문조사view.setT점수(rs.getDouble("T점수"));
				
				list.add(설문조사view);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
}