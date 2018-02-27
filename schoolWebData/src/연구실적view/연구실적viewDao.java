package ��������view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class ��������viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	// ��ü ���� ���� �����ϴ� �޼���
	public int getCountRow() {
		String sql = "select count(*) from ��������view";
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

	// ��ü ���� ������ ����Ʈ�� �����ϴ� �޼���
	public List<��������view> select��������(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<��������view> list = new ArrayList<��������view>();

		String sql = "select * from ��������view  limit ?,?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();

			
			while (rs.next()) {
				��������view �������� = new ��������view();
				��������.set�ܰ�����(rs.getString("�ܰ�����"));
				��������.set�й��迭1(rs.getString("�й��迭1"));
				��������.set���ӱ�����(rs.getInt("���ӱ�����"));
				��������.set_5��迭(rs.getString("_5��迭"));
				��������.set����(rs.getInt("����"));
				��������.set�⵵(rs.getInt("�⵵"));
				��������.set�а���(rs.getString("�а���"));
				��������.setSCI��(rs.getFloat("SCI��"));
				��������.setSCOPUS�м���(rs.getFloat("SCOPUS�м���"));
				��������.setT����(rs.getFloat("T����"));
				��������.set������ܵ�����(rs.getFloat("������ܵ�����"));
				��������.set������ܵ����ĺ�(rs.getFloat("������ܵ����ĺ�"));
				��������.set��������(rs.getFloat("��������"));
				��������.set����(rs.getFloat("����"));
				��������.set����(rs.getFloat("����"));

				list.add(��������);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}