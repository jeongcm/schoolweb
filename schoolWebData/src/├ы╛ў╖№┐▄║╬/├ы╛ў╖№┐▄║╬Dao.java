package ������ܺ�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class ������ܺ�Dao {
	private Connection conn = dbConnection.getConnection();

	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from �����Ȳ�ܺ�";
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

	public List<������ܺ�> select������ܺ�(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<������ܺ�> list = new ArrayList<������ܺ�>();

		String sql = " select * from ������ܺ�view limit ?,?;";

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				������ܺ� ����� = new ������ܺ�();
				�����.set���и�(rs.getString("���и�"));
				�����.set�а���(rs.getString("�а���"));
				�����.set�����(rs.getInt("����հ�"));
				�����.set���������(rs.getInt("�������հ�"));
				�����.set�ǰ�����db���������(rs.getInt("�ǰ�����db���������"));
				�����.set�ؿ������(rs.getInt("�ؿ������"));
				�����.set����������(rs.getInt("����������"));
				�����.set������(rs.getInt("������"));
				�����.set�Դ���(rs.getInt("�Դ���"));
				�����.set����Ұ�����(rs.getInt("����Ұ�����"));
				�����.set�ܱ������л�(rs.getInt("�ܱ������л�"));
				�����.set�ǰ��������尡�����ܴ��(rs.getInt("�ǰ��������尡�����ܴ��"));
				�����.set���д�ñ������(rs.getInt("���д�ñ������"));

				�����.set������(rs.getInt("������"));
				�����.set�������������(rs.getFloat("2�����������"));
				�����.setT����(rs.getFloat("T����"));
				�����.set�����(rs.getFloat("�����"));

				�����.set����â��Ȱ�����缭(rs.getInt("����â��Ȱ�����缭"));
				�����.set����â����(rs.getInt("1��â����"));
				�����.set��������(rs.getInt("��������"));

				list.add(�����);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
