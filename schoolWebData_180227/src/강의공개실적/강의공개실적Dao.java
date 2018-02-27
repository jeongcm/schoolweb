package ���ǰ�������;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import ���ǰ�������.���ǰ�������;

public class ���ǰ�������Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;

	private ResultSet rs;

	public boolean update(���ǰ������� ���ǰ�������) {
		String sql = "update ���ǰ������� set �⵵=?,�а���=?,���ǵ�����B=?,�̷��װ���C=?,�����ڷ�D=?,���ǵ�����E=?,�̷��װ���F=?,�����ڷ�G=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ���ǰ�������.get�⵵());
			pstmt.setString(2, ���ǰ�������.get�а���());
			pstmt.setInt(3, ���ǰ�������.get���ǵ�����B());
			pstmt.setInt(4, ���ǰ�������.get�̷��װ���C());
			pstmt.setInt(5, ���ǰ�������.get�����ڷ�D());
			pstmt.setInt(6, ���ǰ�������.get���ǵ�����E());
			pstmt.setInt(7, ���ǰ�������.get�̷��װ���F());
			pstmt.setInt(8, ���ǰ�������.get�����ڷ�G());
			pstmt.setInt(9, ���ǰ�������.get����());

			pstmt.executeUpdate();

			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(���ǰ������� ���ǰ�������) {
		String sql = "insert into ���ǰ�������(�⵵,�а���,���ǵ�����B,�̷��װ���C,�����ڷ�D,���ǵ�����E,�̷��װ���F,�����ڷ�G,���ǰ�������,�Էºμ�) values(?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ���ǰ�������.get�⵵());
			pstmt.setString(2, ���ǰ�������.get�а���());
			pstmt.setInt(3, ���ǰ�������.get���ǵ�����B());
			pstmt.setInt(4, ���ǰ�������.get�̷��װ���C());
			pstmt.setInt(5, ���ǰ�������.get�����ڷ�D());
			pstmt.setInt(6, ���ǰ�������.get���ǵ�����E());
			pstmt.setInt(7, ���ǰ�������.get�̷��װ���F());
			pstmt.setInt(8, ���ǰ�������.get�����ڷ�G());
			pstmt.setFloat(9, ���ǰ�������.get���ǰ����������());
			pstmt.setString(10, ���ǰ�������.get�Էºμ�());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from ���ǰ������� where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from ���ǰ������� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from ���ǰ�������";
		try {
			rs = pstmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<���ǰ�������> select���ǰ�������(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���ǰ�������> list = new ArrayList<���ǰ�������>();

		String sql = "select * from ���ǰ������� order by ���� desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				���ǰ������� ���ǰ������� = new ���ǰ�������();

				���ǰ�������.set�а���(rs.getString("�а���"));
				���ǰ�������.set���ǵ�����B(rs.getInt("���ǵ�����B"));
				���ǰ�������.set�̷��װ���C(rs.getInt("�̷��װ���C"));
				���ǰ�������.set�����ڷ�D(rs.getInt("�����ڷ�D"));
				���ǰ�������.set���ǵ�����E(rs.getInt("���ǵ�����E"));
				���ǰ�������.set�̷��װ���F(rs.getInt("�̷��װ���F"));
				���ǰ�������.set�����ڷ�G(rs.getInt("�����ڷ�G"));
				���ǰ�������.set����(rs.getInt("����"));
				���ǰ�������.set�Էºμ�(rs.getString("�Էºμ�"));
				���ǰ�������.set�⵵(rs.getInt("�⵵"));

				list.add(���ǰ�������);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}