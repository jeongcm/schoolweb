package 특허등록view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 특허등록view.특허등록view;

public class 특허등록viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;
	
	
	public int getCountRow() {
		String sql = "select count(*) from 특허등록view";
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

	public List<특허등록view> select특허등록view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<특허등록view> list = new ArrayList<특허등록view>();

		String sql="select * from 특허등록view limit ?,?;";
	
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				특허등록view 특허등록view = new 특허등록view();
				
				특허등록view.set단과대학(rs.getString("단과대학"));
				특허등록view.set년도(rs.getInt("년도"));
				특허등록view.set학과명(rs.getString("학과명"));
				특허등록view.set국제(rs.getInt("국제"));
				특허등록view.set국내(rs.getInt("국내"));
				특허등록view.set기술이전(rs.getFloat("기술이전"));
				특허등록view.set전임교원수(rs.getInt("일학기"));
				특허등록view.set수입료(rs.getFloat("수입료"));
				특허등록view.setT점수(rs.getFloat("T점수"));
				

				list.add(특허등록view);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}