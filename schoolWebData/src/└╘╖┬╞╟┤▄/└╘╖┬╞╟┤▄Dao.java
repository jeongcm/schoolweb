package �Է��Ǵ�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dbConnection.dbConnection;

public class �Է��Ǵ�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public String check(String �Էºμ�) {
		String ��� = null;
		String sql = "select ���� from �Է�Ȯ�� where �μ�=?  ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				��� = rs.getString("����");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ���;
	}

	public String per(int ����, String �Էºμ�, String ���̺�) {
		String ��� = null;
		String sql = "select IF(�Էºμ�='"+�Էºμ� + "','same','diff') result from " + ���̺� + " where ����="+����+";";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(sql);
			while (rs.next()) {
				��� = rs.getString("result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ���;
	}

}