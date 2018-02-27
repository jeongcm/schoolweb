package 교육프로그램참여비율view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 교육프로그램참여비율view.교육프로그램참여비율view;

public class 교육프로그램참여비율viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;

	
	public int getCountRow() {
		String sql = "select count(*) from 교육프로그램view";
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

	public List<교육프로그램참여비율view> select교육프로그램참여비율view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<교육프로그램참여비율view> list = new ArrayList<교육프로그램참여비율view>();

		String sql="select * from 교육프로그램view limit ?,?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				교육프로그램참여비율view 교육프로그램참여비율view = new 교육프로그램참여비율view();
				교육프로그램참여비율view.set년도(rs.getInt("년도"));
				교육프로그램참여비율view.set단과대학(rs.getString("단과대학"));
				교육프로그램참여비율view.set학과명(rs.getString("학과명"));
				교육프로그램참여비율view.set학생과(rs.getInt("총학생과"));
				교육프로그램참여비율view.set국제교류본부(rs.getInt("국제교류팀"));
				교육프로그램참여비율view.set취업창업지원과(rs.getInt("취업창업지원과"));
				교육프로그램참여비율view.set교수학습개발원(rs.getInt("교수학습개발원"));
				교육프로그램참여비율view.set입학사정관실(rs.getInt("입학과"));
				교육프로그램참여비율view.set기타(rs.getInt("기타"));
				교육프로그램참여비율view.set계(rs.getInt("계"));
				교육프로그램참여비율view.set재학생수(rs.getInt("재학생수"));
				교육프로그램참여비율view.set교육프로그램참여비율(rs.getFloat("교육프로그램참여비율"));
				교육프로그램참여비율view.setT점수(rs.getDouble("T점수"));
				

				list.add(교육프로그램참여비율view);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
