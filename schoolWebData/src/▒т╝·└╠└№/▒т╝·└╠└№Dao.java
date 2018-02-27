package �������;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import �������.�������;

public class �������Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(������� �������) {
		String sql = "update ������� set �⵵=?,�а���=?,��ǥ�߸���=?,��������=?,���ױ����=? where ����=?";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, �������.get�⵵());
			pstmt.setString(2, �������.get�а���());
			pstmt.setString(3, �������.get��ǥ�߸���());
			pstmt.setString(4, �������.get��������());
			pstmt.setFloat(5, �������.get���ױ����());
			pstmt.setInt(6, �������.get����());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(������� �������) {
		String sql = "insert into �������(�⵵,�а���,��ǥ�߸���,��������,���ױ����,�Էºμ�) values(?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �������.get�⵵());
			pstmt.setString(2, �������.get�а���());
			pstmt.setString(3, �������.get��ǥ�߸���());
			pstmt.setString(4, �������.get��������());
			pstmt.setFloat(5, �������.get���ױ����());
			pstmt.setString(6, �������.get�Էºμ�());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {

		String sql = "delete from ������� where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from ������� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from �������";
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

	public List<�������> select�������(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�������> list = new ArrayList<�������>();

		String sql = "select * from ������� order by ���� desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				������� ������� = new �������();
				�������.set�⵵(rs.getInt("�⵵"));
				�������.set�а���(rs.getString("�а���"));
				�������.set��ǥ�߸���(rs.getString("��ǥ�߸���"));
				�������.set��������(rs.getString("��������"));
				�������.set���ױ����(rs.getFloat("���ױ����"));
				�������.set����(rs.getInt("����"));
				�������.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(�������);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int ����(int �����⵵, String �����а���) {
		int ���� = 0;
		String sql = "select ���� from Ư���Ϲױ���������Է� where �⵵=?,�а���=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, �����⵵);
			pstmt.setString(2, �����а���);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				���� = rs.getInt("����");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ����;
	}

	public int ����(int �����⵵, String �����а���) {
		int ���� = 0;
		String sql = "select ���� from Ư���Ϲױ���������Է� where �⵵=?,�а���=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, �����⵵);
			pstmt.setString(2, �����а���);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				���� = rs.getInt("����");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ����;
	}
}
