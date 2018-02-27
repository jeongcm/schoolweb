package �ܱ����л�����view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import �ܱ����л�����view.�ܱ����л�����view;

public class �ܱ����л�����viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from �ܱ����л�view;";
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

	public List<�ܱ����л�����view> select�ܱ����л�����view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�ܱ����л�����view> list = new ArrayList<�ܱ����л�����view>();

		String sql = "select * from �ܱ����л�view limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				�ܱ����л�����view �ܱ����л�����view = new �ܱ����л�����view();
				�ܱ����л�����view.set�⵵(rs.getInt("�⵵"));
				�ܱ����л�����view.set�ܰ�����(rs.getString("�ܰ�����"));
				�ܱ����л�����view.set�а���(rs.getString("�а���"));
				�ܱ����л�����view.set�ܱ����л���(rs.getInt("�ܱ����л���"));
				�ܱ����л�����view.set���л���(rs.getInt("���л���"));
				�ܱ����л�����view.set�ܱ����л�����(rs.getFloat("�ܱ����л�����"));
				�ܱ����л�����view.setT����(rs.getDouble("T����"));
				list.add(�ܱ����л�����view);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}