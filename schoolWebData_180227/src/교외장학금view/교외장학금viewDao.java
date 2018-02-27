package �������б�view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class �������б�viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	// ��ü ���� ���� �����ϴ� �޼���
	public int getCountRow() {
		String sql = "select count(*) from �������б�view";
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
	public List<�������б�view> select�������б�view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�������б�view> list = new ArrayList<�������б�view>();

		String sql = "select * from �������б�view limit ?,?;;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			// ����� ����, ����
			while (rs.next()) {
				�������б�view �������б� = new �������б�view();
				�������б�.set�⵵(rs.getInt("�⵵"));
				�������б�.set�а���(rs.getString("�а���"));
				�������б�.setT����(rs.getFloat("T����"));
				�������б�.set�������б�(rs.getInt("�������б�"));
				�������б�.set���δ米�����б�(rs.getFloat("1�δ米�����б�"));
				�������б�.set����(rs.getInt("����"));
				�������б�.set�ܰ�����(rs.getString("�ܰ�����"));
				�������б�.set���(rs.getInt("���"));
				�������б�.set���б�(rs.getInt("1�б�"));
				�������б�.set���б�(rs.getInt("2�б�"));

				list.add(�������б�);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
