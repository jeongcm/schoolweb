package 입력판단;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dbConnection.dbConnection;

public class 입력판단Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public String check(String 입력부서) {
		String 결과 = null;
		String sql = "select 상태 from 입력확인 where 부서=?  ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				결과 = rs.getString("상태");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 결과;
	}

	public String per(int 연번, String 입력부서, String 테이블) {
		String 결과 = null;
		String sql = "select IF(입력부서='"+입력부서 + "','same','diff') result from " + 테이블 + " where 연번="+연번+";";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(sql);
			while (rs.next()) {
				결과 = rs.getString("result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 결과;
	}

}