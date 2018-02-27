package �ߵ�Ż����view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class �ߵ�Ż����viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	// ��ü ���� ���� �����ϴ� �޼���
	public int getCountRow() {
		String sql = "select count(*) from �ߵ�Ż����view";
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
	public List<�ߵ�Ż����view> select�ߵ�Ż����view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�ߵ�Ż����view> list = new ArrayList<�ߵ�Ż����view>();

		String sql = " select * from �ߵ�Ż����view limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			// ����� ����, ����
			while (rs.next()) {
				�ߵ�Ż����view �ߵ�Ż���� = new �ߵ�Ż����view();
				�ߵ�Ż����.set����(rs.getInt("����"));
				�ߵ�Ż����.set�⵵(rs.getInt("�⵵"));
				�ߵ�Ż����.set�а���(rs.getString("�а���"));
				�ߵ�Ż����.set��(rs.getInt("��"));
				�ߵ�Ż����.setT����(rs.getFloat("T����"));
				�ߵ�Ż����.set��Ÿ(rs.getInt("��Ÿ"));
				�ߵ�Ż����.set�̵��(rs.getInt("�̵��"));
				�ߵ�Ż����.set�̺���(rs.getInt("�̺���"));
				�ߵ�Ż����.set����(rs.getInt("����"));
				�ߵ�Ż����.set�����л���(rs.getInt("�����л���"));
				�ߵ�Ż����.set�ߵ�Ż����(rs.getFloat("�ߵ�Ż����"));
				�ߵ�Ż����.set��Ÿ(rs.getInt("��Ÿ"));
				�ߵ�Ż����.set�л���(rs.getInt("�л���"));
				�ߵ�Ż����.setŸ�а�������(rs.getInt("Ÿ�а�������"));
				�ߵ�Ż����.set�ܰ�����(rs.getString("�ܰ�����"));

				list.add(�ߵ�Ż����);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
