package ���Ǵ��ܺ�view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class ���Ǵ��ܺ�viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	// ��ü ���� ���� �����ϴ� �޼���
	public int getCountRow() {
		String sql = "select count(*) from �������Ǵ��ܺκ���";
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

	// ��ü ���� ������ ����Ʈ�� �����ϴ� �޼���
	public List<���Ǵ��ܺ�view> select���Ǵ��view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���Ǵ��ܺ�view> list = new ArrayList<���Ǵ��ܺ�view>();

		String sql = "select * from ���Ǵ��ܺ�view limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			System.out.print(pstmt);
			// ����� ����, ����
			while (rs.next()) {
				���Ǵ��ܺ�view ���Ǵ�� = new ���Ǵ��ܺ�view();
				���Ǵ��.set���и�(rs.getString("���и�"));
				���Ǵ��.set�а���(rs.getString("�а���"));
				���Ǵ��.setT����(rs.getFloat("T����"));
				���Ǵ��.set���Ǵ�����(rs.getFloat("���Ǵ�����"));
				���Ǵ��.set�����������ð���(rs.getFloat("�����������ð���"));
				���Ǵ��.set���������ʼ�����(rs.getFloat("���������ʼ�����"));
				���Ǵ��.set������������(rs.getFloat("������������"));
				���Ǵ��.set�����������ð���(rs.getFloat("�������ð���"));
				���Ǵ��.set���������ʼ�����(rs.getFloat("�����ʼ�����"));
				���Ǵ��.set������������(rs.getFloat("��������"));
				���Ǵ��.set����(rs.getInt("����"));

				list.add(���Ǵ��);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
