package �������������view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class �������������viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	// ��ü ���� ���� �����ϴ� �޼���
	public int getCountRow() {
		String sql = "select count(*) from �������view";
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
	public List<�������������view> select�������������view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�������������view> list = new ArrayList<�������������view>();

		String sql = "select * from �������view limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			// ����� ����, ����
			while (rs.next()) {
				�������������view ������������� = new �������������view();
				�������������.set�⵵(rs.getInt("�⵵"));
				�������������.set�а���(rs.getString("�а���"));
				�������������.setT����(rs.getFloat("T����"));
				�������������.set������α�(rs.getInt("������α�"));
				�������������.set�������������(rs.getFloat("�������������"));
				�������������.set����(rs.getInt("����"));
				�������������.set���ӱ�����(rs.getInt("���ӱ�����"));
				�������������.set�ܰ�����(rs.getString("�ܰ�����"));
				�������������.set������ݸ�ݾ�(rs.getInt("������ݸ�ݾ�"));

				list.add(�������������);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}