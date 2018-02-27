package 현장실습view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


import dbConnection.dbConnection;
import 현장실습view.현장실습view;

public class 현장실습viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;


	public int getCountRow() {
		String sql = "select count(*) from 현장실습view;";
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

	public List<현장실습view> select현장실습view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<현장실습view> list = new ArrayList<현장실습view>();

		String sql="select * from 현장실습view  limit ?,?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				
				현장실습view 현장실습view = new 현장실습view();
				
				현장실습view.set년도(rs.getInt("년도"));
				현장실습view.set단과대학(rs.getString("단과대학"));
				현장실습view.set학과명(rs.getString("학과명"));
				현장실습view.set_1학기(rs.getInt("1학기"));
				현장실습view.set_2학기(rs.getInt("2학기"));
				현장실습view.set합계(rs.getInt("합계"));
				현장실습view.set장기1학기(rs.getInt("장기1학기"));
				현장실습view.set장기2학기(rs.getInt("장기2학기"));
				현장실습view.set장기합계(rs.getInt("장기합계"));
				현장실습view.set이수학생비율(rs.getFloat("이수학생비율"));
				현장실습view.set대상학생수(rs.getInt("대상학생수"));
				현장실습view.setT점수(rs.getDouble("T점수"));

				list.add(현장실습view);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}