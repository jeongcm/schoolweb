package 중도탈락률view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class 중도탈락률viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 전체 행의 수를 리턴하는 메서드
	public int getCountRow() {
		String sql = "select count(*) from 중도탈락률view";
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
	public List<중도탈락률view> select중도탈락률view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<중도탈락률view> list = new ArrayList<중도탈락률view>();

		String sql = " select * from 중도탈락률view limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			// 결과물 편집, 리턴
			while (rs.next()) {
				중도탈락률view 중도탈락률 = new 중도탈락률view();
				중도탈락률.set연번(rs.getInt("연번"));
				중도탈락률.set년도(rs.getInt("년도"));
				중도탈락률.set학과명(rs.getString("학과명"));
				중도탈락률.set계(rs.getInt("계"));
				중도탈락률.setT점수(rs.getFloat("T점수"));
				중도탈락률.set기타(rs.getInt("기타"));
				중도탈락률.set미등록(rs.getInt("미등록"));
				중도탈락률.set미복학(rs.getInt("미복학"));
				중도탈락률.set자퇴(rs.getInt("자퇴"));
				중도탈락률.set재적학생수(rs.getInt("재적학생수"));
				중도탈락률.set중도탈락률(rs.getFloat("중도탈락률"));
				중도탈락률.set기타(rs.getInt("기타"));
				중도탈락률.set학사경고(rs.getInt("학사경고"));
				중도탈락률.set타학과전과자(rs.getInt("타학과전과자"));
				중도탈락률.set단과대학(rs.getString("단과대학"));

				list.add(중도탈락률);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
