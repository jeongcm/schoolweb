package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbConnection.dbConnection;

public class loginDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean logincheck(String id, String pw) {
		boolean result = false;
		try {
			String sql = "select * from admin where id=? and pw=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
			return result;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean changePw(String id, String pw) {
		try {
			String sql = "update admin set pw=? where id=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean changeState(String major) {
		try {
			String sql = "update 입력확인 set 상태='입력중' where 부서=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, major);
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public String 부서체크(String 부서) {
		String sql = "select exists(select * from 입력확인  where 부서=? ) result;";
		String result = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 부서);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
