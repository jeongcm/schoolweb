package 현장실습;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 현장실습.현장실습;

public class 현장실습Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(현장실습 현장실습) {
		String sql = "update 현장실습 set 년도=?,학과명=?,1학기=?,2학기=?,장기1학기=?,장기2학기=?,대상학생수=?, 이수학생비율=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 현장실습.get년도());
			pstmt.setString(2, 현장실습.get학과명());
			pstmt.setInt(3, 현장실습.get_1학기());
			pstmt.setInt(4, 현장실습.get_2학기());
			pstmt.setInt(5, 현장실습.get장기1학기());
			pstmt.setInt(6, 현장실습.get장기2학기());
			pstmt.setInt(7, 현장실습.get대상학생수());
			pstmt.setFloat(8, 현장실습.get이수학생비율());
			pstmt.setInt(9, 현장실습.get연번());
			pstmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insert(현장실습 현장실습) {
		String sql = "insert into 현장실습(년도,학과명,1학기,2학기,장기1학기,장기2학기,대상학생수,이수학생비율,입력부서) values(?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);

			
			pstmt.setInt(1, 현장실습.get년도());
			pstmt.setString(2, 현장실습.get학과명());
			pstmt.setInt(3, 현장실습.get_1학기());
			pstmt.setInt(4, 현장실습.get_2학기());
			pstmt.setInt(5, 현장실습.get장기1학기());
			pstmt.setInt(6, 현장실습.get장기2학기());
			pstmt.setInt(7, 현장실습.get대상학생수());
			pstmt.setFloat(8, 현장실습.get이수학생비율());
			pstmt.setString(9, 현장실습.get입력부서());

			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
				return false;
	}
}

	public void delete(int 연번) {

		String sql = "delete from 현장실습 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 현장실습 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 현장실습";
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

	public List<현장실습> select현장실습(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<현장실습> list = new ArrayList<현장실습>();

		String sql = "select * from 현장실습 order by 연번 limit ?,?;";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				현장실습 현장실습 = new 현장실습();

				현장실습.set년도(rs.getInt("년도"));
				현장실습.set학과명(rs.getString("학과명"));
				현장실습.set_1학기(rs.getInt("1학기"));
				현장실습.set_2학기(rs.getInt("2학기"));
				현장실습.set장기1학기(rs.getInt("장기1학기"));
				현장실습.set장기2학기(rs.getInt("장기2학기"));
				현장실습.set연번(rs.getInt("연번"));
				현장실습.set대상학생수(rs.getInt("대상학생수"));
				현장실습.set입력부서(rs.getString("입력부서"));

				list.add(현장실습);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
