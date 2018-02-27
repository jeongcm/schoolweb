package 재학생현황2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import 재학생현황2.재학생현황2;

public class 재학생현황Dao2 {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	
	private ResultSet rs;

	public boolean update(재학생현황2 재학생현황2) {

		String sql = "update 재학생현황 set 년도=?,학과명=?,기준=? ,계=?  where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 재학생현황2.get년도());
			pstmt.setString(2, 재학생현황2.get학과명());
			pstmt.setString(3, "10.01");
			pstmt.setInt(4, 재학생현황2.get학생수());
			pstmt.setInt(5, 재학생현황2.get연번());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}


	public boolean insert(재학생현황2 재학생현황2) {
		String sql = "insert into 재학생현황(년도,기준,학과명,계,입력부서) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 재학생현황2.get년도());
			pstmt.setString(2,"10.01");
			pstmt.setString(3, 재학생현황2.get학과명());
			pstmt.setInt(4, 재학생현황2.get학생수());
			pstmt.setString(5, 재학생현황2.get입력부서());

			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public void delete(int 연번) {
		String sql = "delete from 재학생현황 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = " delete from 재학생현황  where 기준='10.01' and 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 재학생현황 where 기준='10.01';";
		try {
			rs = pstmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<재학생현황2> select재학생현황(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<재학생현황2> list = new ArrayList<재학생현황2>();

		String sql = "select * from 재학생현황  where 기준 = '10.01' order by 연번 desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				재학생현황2 재학생현황2 = new 재학생현황2();
				
				재학생현황2.set년도(rs.getInt("년도"));
				재학생현황2.set학과명(rs.getString("학과명"));
				재학생현황2.set학생수(rs.getInt("계"));
				재학생현황2.set연번(rs.getInt("연번"));
				재학생현황2.set입력부서(rs.getString("입력부서"));

				list.add(재학생현황2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}