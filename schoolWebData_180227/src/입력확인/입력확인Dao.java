package 입력확인;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 입력확인Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from 입력확인 where 부서 not like '%학과' and 부서 not like '%전공' and 부서 not like '%학부' and not 부서='중국어과' ";
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

	public List<입력확인> select입력확인(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<입력확인> list = new ArrayList<입력확인>();

		String sql = "select * from 입력확인 where 부서 not like '%학과' and 부서 not like '%전공' and 부서 not like '%학부' and not 부서='중국어과' order by 부서 desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				입력확인 입력확인 = new 입력확인();
				입력확인.set부서(rs.getString("부서"));
				입력확인.set상태(rs.getString("상태"));

				list.add(입력확인);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	



	public void saveAll(String 부서) {
		String sql = "update 입력확인 set 상태='입력완료' where 부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 부서);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
