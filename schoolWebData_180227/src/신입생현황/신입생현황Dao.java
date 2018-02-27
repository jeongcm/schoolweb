package 신입생현황;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 신입생현황Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(신입생현황 신입생현황) {

		String sql = "update 신입생현황 set 년도=?,학과명=?,정원내입학자수=?,정원내모집인원=?,신입생충원율=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 신입생현황.get년도());
			pstmt.setString(2, 신입생현황.get학과명());
			pstmt.setInt(3, 신입생현황.get입학자수());
			pstmt.setInt(4, 신입생현황.get모집인원());
			pstmt.setFloat(5, 신입생현황.get신입생충원율());
			pstmt.setInt(6, 신입생현황.get연번());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(신입생현황 신입생현황) {
		String sql = "insert into 신입생현황(년도,학과명,정원내입학자수,정원내모집인원,신입생충원율,입력부서) values(?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 신입생현황.get년도());
			pstmt.setString(2, 신입생현황.get학과명());
			pstmt.setInt(3, 신입생현황.get입학자수());
			pstmt.setInt(4, 신입생현황.get모집인원());
			pstmt.setFloat(5, 신입생현황.get신입생충원율());
			pstmt.setString(6, 신입생현황.get입력부서());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 신입생현황 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 신입생현황 where 입력부서=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public int getCountRow() {
		String sql = "select count(*) from 신입생현황";
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

	public List<신입생현황> select신입생현황(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<신입생현황> list = new ArrayList<신입생현황>();

		String sql = "select 신입생현황.* from 신입생현황 order by 연번 desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				신입생현황 신입생현황 = new 신입생현황();
				신입생현황.set년도(rs.getInt("년도"));
				신입생현황.set학과명(rs.getString("학과명"));
				신입생현황.set모집인원(rs.getInt("정원내모집인원"));
				신입생현황.set입학자수(rs.getInt("정원내입학자수"));
				신입생현황.set신입생충원율(rs.getFloat("신입생충원율"));
				신입생현황.setT점수(rs.getFloat("T점수"));
				신입생현황.set연번(rs.getInt("연번"));
				신입생현황.set입력부서(rs.getString("입력부서"));
				
				list.add(신입생현황);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public float 신입생충원율(int 년도, String 학과명) {
		float 신입생충원율 = 0;

		String sql = "select 신입생충원율 from 신입생현황 where 년도=? and 학과명=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				신입생충원율 = rs.getFloat("신입생충원율");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 신입생충원율;
	}

}