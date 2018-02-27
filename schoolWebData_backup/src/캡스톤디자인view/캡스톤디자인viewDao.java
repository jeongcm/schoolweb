package 캡스톤디자인view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 캡스톤디자인view.캡스톤디자인view;

public class 캡스톤디자인viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from 캡스톤view";
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

	public List<캡스톤디자인view> select캡스톤디자인view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<캡스톤디자인view> list = new ArrayList<캡스톤디자인view>();

		String sql="select * from 캡스톤view limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				캡스톤디자인view 캡스톤디자인view = new 캡스톤디자인view();
			
				캡스톤디자인view.set년도(rs.getInt("년도"));
				캡스톤디자인view.set단과대학(rs.getString("단과대학"));
				캡스톤디자인view.set학과명(rs.getString("학과명"));
				캡스톤디자인view.set이수1학기(rs.getInt("이수1학기"));
				캡스톤디자인view.set이수2학기(rs.getInt("이수2학기"));
				캡스톤디자인view.set대상1학기(rs.getInt("대상1학기"));
				캡스톤디자인view.set대상2학기(rs.getInt("대상2학기"));
				캡스톤디자인view.set이수합계(rs.getInt("이수합계"));
				캡스톤디자인view.set대상합계(rs.getInt("대상합계"));
				캡스톤디자인view.set이수학생비율(rs.getFloat("이수학생비율"));
				캡스톤디자인view.setT점수(rs.getDouble("T점수"));

				list.add(캡스톤디자인view);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}