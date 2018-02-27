package ���Ƹ�view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import ���Ƹ�view.���Ƹ�view;

public class ���Ƹ�viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from ���Ƹ�view";
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

	public List<���Ƹ�view> select���Ƹ�view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���Ƹ�view> list = new ArrayList<���Ƹ�view>();

		String sql = "select * from ���Ƹ�view limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���Ƹ�view ���Ƹ�view = new ���Ƹ�view();
				���Ƹ�view.set�⵵(rs.getInt("�⵵"));
				���Ƹ�view.set�ܰ�����(rs.getString("�ܰ�����"));
				���Ƹ�view.set�а���(rs.getString("�а���"));
				���Ƹ�view.set�н�(rs.getInt("�н�"));
				���Ƹ�view.set���(rs.getInt("���"));
				���Ƹ�view.setâ��(rs.getInt("â��"));
				���Ƹ�view.set����(rs.getInt("����"));
				���Ƹ�view.set����(rs.getInt("����"));
				���Ƹ�view.set���(rs.getInt("���"));
				���Ƹ�view.set��Ÿ(rs.getInt("��Ÿ"));
				���Ƹ�view.set��(rs.getFloat("��"));
				���Ƹ�view.set���л���(rs.getInt("���л���"));
				���Ƹ�view.set���Ƹ���������(rs.getFloat("���Ƹ���������"));
				���Ƹ�view.setT����(rs.getDouble("T����"));

				list.add(���Ƹ�view);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
