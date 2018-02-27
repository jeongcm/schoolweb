package 연구실적외부view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


import dbConnection.dbConnection;
import 연구실적외부view.연구실적외부view;

public class 연구실적외부viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;


	// 전체 행의 수를 리턴하는 메서드
	public int getCountRow() {
		String sql = "select count(*) from 전임교원1인당연구실적dview";
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
	public List<연구실적외부view> select전임교원1인당연구실적dview(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<연구실적외부view> list = new ArrayList<연구실적외부view>();

		
		String sql="select * from 전임교원1인당연구실적dview order by 기준년도  desc,학과 asc limit ?,?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			
			rs = pstmt.executeQuery();
	
			 //결과물 편집, 리턴
			while (rs.next()) {
				연구실적외부view 전임교원1인당연구실적dview = new 연구실적외부view();
				
				전임교원1인당연구실적dview.set기준년도(rs.getString("기준년도"));
				전임교원1인당연구실적dview.set학교(rs.getString("학교"));
				전임교원1인당연구실적dview.set학과(rs.getString("학과"));
				전임교원1인당연구실적dview.set전임교원(rs.getInt("전임교원"));
				전임교원1인당연구실적dview.set저서(rs.getFloat("저서"));
				전임교원1인당연구실적dview.set역서(rs.getFloat("역서"));
				전임교원1인당연구실적dview.set연구재단B(rs.getFloat("연구재단B"));
				전임교원1인당연구실적dview.setSCI급E(rs.getFloat("SCI급E"));
				전임교원1인당연구실적dview.set전임교원1인당연구실적(rs.getDouble("전임교원1인당연구실적"));
				전임교원1인당연구실적dview.setT점수(rs.getDouble("T점수"));
				
				
				list.add(전임교원1인당연구실적dview);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}