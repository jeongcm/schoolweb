package 강의담당외부view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class 강의담당외부viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 전체 행의 수를 리턴하는 메서드
	public int getCountRow() {
		String sql = "select count(*) from 개설강의담당외부비율";
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
	public List<강의담당외부view> select강의담당view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<강의담당외부view> list = new ArrayList<강의담당외부view>();

		String sql = "select * from 강의담당외부view limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			System.out.print(pstmt);
			// 결과물 편집, 리턴
			while (rs.next()) {
				강의담당외부view 강의담당 = new 강의담당외부view();
				강의담당.set대학명(rs.getString("대학명"));
				강의담당.set학과명(rs.getString("학과명"));
				강의담당.setT점수(rs.getFloat("T점수"));
				강의담당.set강의담당비율(rs.getFloat("강의담당비율"));
				강의담당.set개설자유선택과목(rs.getFloat("개설자유선택과목"));
				강의담당.set개설교양필수과목(rs.getFloat("개설교양필수과목"));
				강의담당.set개설전공과목(rs.getFloat("개설전공과목"));
				강의담당.set교원자유선택과목(rs.getFloat("자유선택과목"));
				강의담당.set교원교양필수과목(rs.getFloat("교양필수과목"));
				강의담당.set교원전공과목(rs.getFloat("전공과목"));
				강의담당.set연번(rs.getInt("연번"));

				list.add(강의담당);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
