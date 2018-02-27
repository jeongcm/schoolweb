package ���ӱ���Ȯ����view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class ���ӱ���Ȯ����viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from ���ӱ���Ȯ����view;";
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

	public List<���ӱ���Ȯ����view> select���ӱ���Ȯ����(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���ӱ���Ȯ����view> list = new ArrayList<���ӱ���Ȯ����view>();

		String sql = "select * from ���ӱ���Ȯ����view limit ?,?  ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���ӱ���Ȯ����view ���ӱ���Ȯ���� = new ���ӱ���Ȯ����view();
				���ӱ���Ȯ����.set�⵵(rs.getInt("�⵵"));
				���ӱ���Ȯ����.set����(rs.getInt("����"));
				���ӱ���Ȯ����.set�а���(rs.getString("�а���"));
				���ӱ���Ȯ����.set���ӱ���Ȯ����(rs.getFloat("���ӱ���Ȯ����"));
				���ӱ���Ȯ����.setT����(rs.getFloat("T����"));
				���ӱ���Ȯ����.set���п�������(rs.getInt("���п�������"));
				���ӱ���Ȯ����.set���п����л�(rs.getInt("���п����л�"));
				���ӱ���Ȯ����.set���л��������ӱ���(rs.getInt("���л��������ӱ���"));
				���ӱ���Ȯ����.set�л������������ӱ���(rs.getInt("�л������������ӱ���"));
				���ӱ���Ȯ����.set�����л�����(rs.getInt("�����л�����"));
				���ӱ���Ȯ����.set����_�к�_����(rs.getInt("����_�к�_����"));
				���ӱ���Ȯ����.set����_�к�_���л�(rs.getInt("����_�к�_���л�"));
				���ӱ���Ȯ����.set����_���п�_����(rs.getInt("����_���п�_����"));
				���ӱ���Ȯ����.set����_���п�_���л�(rs.getInt("����_���п�_���л�"));
				���ӱ���Ȯ����.set����_��_����(rs.getInt("����_��_����"));
				���ӱ���Ȯ����.set����_��_���л�(rs.getInt("����_��_���л�"));
				���ӱ���Ȯ����.set�л���_����(rs.getFloat("�л���_����"));
				���ӱ���Ȯ����.set�л���_���л�(rs.getFloat("�л���_���л�"));
				���ӱ���Ȯ����.setȮ����_����(rs.getFloat("Ȯ����_����"));
				���ӱ���Ȯ����.setȮ����_���л�(rs.getFloat("Ȯ����_���л�"));
				���ӱ���Ȯ����.set�ܰ�����(rs.getString("�ܰ�����"));
				���ӱ���Ȯ����.set�ż�����(rs.getString("�ż�����"));
				���ӱ���Ȯ����.set�л�����(rs.getInt("�л�����"));
				���ӱ���Ȯ����.set��������(rs.getInt("��������"));
				���ӱ���Ȯ����.set���л�(rs.getInt("���л�"));
				���ӱ���Ȯ����.set�л�������(rs.getInt("�л�������"));
				���ӱ���Ȯ����.set���л���(rs.getInt("���л���"));
				���ӱ���Ȯ����.set�迭(rs.getString("_5��迭"));
				
				list.add(���ӱ���Ȯ����);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
