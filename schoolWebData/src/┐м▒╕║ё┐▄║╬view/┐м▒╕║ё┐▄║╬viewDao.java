package 연구비외부view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


import dbConnection.dbConnection;
import 연구비외부view.연구비외부view;

public class 연구비외부viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;


	// 전체 행의 수를 리턴하는 메서드
	public int getCountRow() {
		String sql = "select count(*) from 전임교원1인당교외연구비dview";
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
	public List<연구비외부view> select전임교원1인당교외연구비dview(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<연구비외부view> list = new ArrayList<연구비외부view>();

		
		String sql="select * from 전임교원1인당교외연구비dview order by 기준년도  desc,학교 asc limit ?,?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			
			rs = pstmt.executeQuery();
	
			 //결과물 편집, 리턴
			while (rs.next()) {
				연구비외부view 전임교원1인당교외연구비dview = new 연구비외부view();
				
				전임교원1인당교외연구비dview.set기준년도(rs.getString("기준년도"));
				전임교원1인당교외연구비dview.set학교(rs.getString("학교"));
				전임교원1인당교외연구비dview.set학과(rs.getString("학과"));
				전임교원1인당교외연구비dview.set전임교원수(rs.getInt("전임교원수"));
				전임교원1인당교외연구비dview.set소계(rs.getInt("소계"));
				전임교원1인당교외연구비dview.set연구비C(rs.getInt("연구비C"));
				전임교원1인당교외연구비dview.set연구비D(rs.getInt("연구비D"));
				전임교원1인당교외연구비dview.set연구비E(rs.getInt("연구비E"));
				전임교원1인당교외연구비dview.set연구비F(rs.getInt("연구비F"));
				전임교원1인당교외연구비dview.set전임교원1인당교외연구비(rs.getFloat("전임교원1인당교외연구비"));
				전임교원1인당교외연구비dview.setT점수(rs.getDouble("T점수"));
				

				list.add(전임교원1인당교외연구비dview);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}