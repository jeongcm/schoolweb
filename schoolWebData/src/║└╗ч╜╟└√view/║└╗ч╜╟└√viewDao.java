package �������view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import �������view.�������view;

public class �������viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from �������view";
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

	public List<�������view> select�������view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�������view> list = new ArrayList<�������view>();

		String sql = "select * from �������view limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�������view �������view = new �������view();
				�������view.set�⵵(rs.getInt("�⵵"));
				�������view.set�ܰ�����(rs.getString("�ܰ�����"));
				�������view.set�а���(rs.getString("�а���"));
				�������view.set�̼�1�б�(rs.getInt("�̼�1�б�"));
				�������view.set���л�1�б�(rs.getInt("���л�1�б�"));
				�������view.set�̼�2�б�(rs.getInt("�̼�2�б�"));
				�������view.set���л�2�б�(rs.getInt("���л�2�б�"));
				�������view.set�̼�����(rs.getFloat("�̼�����"));
				�������view.setT����(rs.getDouble("T����"));

				list.add(�������view);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}