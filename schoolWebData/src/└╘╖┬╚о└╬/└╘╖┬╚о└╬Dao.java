package �Է�Ȯ��;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class �Է�Ȯ��Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from �Է�Ȯ�� where �μ� not like '%�а�' and �μ� not like '%����' and �μ� not like '%�к�' and not �μ�='�߱����' ";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// ����� ����, ����
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<�Է�Ȯ��> select�Է�Ȯ��(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�Է�Ȯ��> list = new ArrayList<�Է�Ȯ��>();

		String sql = "select * from �Է�Ȯ�� where �μ� not like '%�а�' and �μ� not like '%����' and �μ� not like '%�к�' and not �μ�='�߱����' order by �μ� desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				�Է�Ȯ�� �Է�Ȯ�� = new �Է�Ȯ��();
				�Է�Ȯ��.set�μ�(rs.getString("�μ�"));
				�Է�Ȯ��.set����(rs.getString("����"));

				list.add(�Է�Ȯ��);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	



	public void saveAll(String �μ�) {
		String sql = "update �Է�Ȯ�� set ����='�Է¿Ϸ�' where �μ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �μ�);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
