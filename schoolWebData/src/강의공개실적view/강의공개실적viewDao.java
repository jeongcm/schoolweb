package 강의공개실적view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 강의공개실적view.강의공개실적view;

public class 강의공개실적viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;
	
	public int getCountRow() {
		String sql = "select count(*) from 강의공개view";
		
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

	public List<강의공개실적view> select강의공개실적view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<강의공개실적view> list = new ArrayList<강의공개실적view>();

		String sql="select * from 강의공개view limit ?,?;";	
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
	
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				강의공개실적view 강의공개실적view = new 강의공개실적view();
				
				강의공개실적view.set년도(rs.getInt("년도")); //학과현황의 년도와 학과를 가져오기위해 임시컬럼명을 사용
				강의공개실적view.set학과명(rs.getString("학과명"));
				강의공개실적view.set단과대학(rs.getString("단과대학"));
				강의공개실적view.set전임교원수(rs.getInt("전임교원수"));
				강의공개실적view.set강의동영상B(rs.getInt("강의동영상B"));
				강의공개실적view.set이러닝강의C(rs.getInt("이러닝강의C"));
				강의공개실적view.set강의자료D(rs.getInt("강의자료D"));
				강의공개실적view.set강의동영상E(rs.getInt("강의동영상E"));
				강의공개실적view.set이러닝강의F(rs.getInt("이러닝강의F"));
				강의공개실적view.set강의자료G(rs.getInt("강의자료G"));
				강의공개실적view.set강의공개실적(rs.getFloat("강의공개실적"));
				강의공개실적view.setT점수(rs.getDouble("T점수"));

				list.add(강의공개실적view);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}