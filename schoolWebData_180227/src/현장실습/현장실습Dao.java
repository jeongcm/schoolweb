package ����ǽ�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import ����ǽ�.����ǽ�;

public class ����ǽ�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(����ǽ� ����ǽ�) {
		String sql = "update ����ǽ� set �⵵=?,�а���=?,1�б�=?,2�б�=?,���1�б�=?,���2�б�=?,����л���=?, �̼��л�����=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����ǽ�.get�⵵());
			pstmt.setString(2, ����ǽ�.get�а���());
			pstmt.setInt(3, ����ǽ�.get_1�б�());
			pstmt.setInt(4, ����ǽ�.get_2�б�());
			pstmt.setInt(5, ����ǽ�.get���1�б�());
			pstmt.setInt(6, ����ǽ�.get���2�б�());
			pstmt.setInt(7, ����ǽ�.get����л���());
			pstmt.setFloat(8, ����ǽ�.get�̼��л�����());
			pstmt.setInt(9, ����ǽ�.get����());
			pstmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insert(����ǽ� ����ǽ�) {
		String sql = "insert into ����ǽ�(�⵵,�а���,1�б�,2�б�,���1�б�,���2�б�,����л���,�̼��л�����,�Էºμ�) values(?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);

			
			pstmt.setInt(1, ����ǽ�.get�⵵());
			pstmt.setString(2, ����ǽ�.get�а���());
			pstmt.setInt(3, ����ǽ�.get_1�б�());
			pstmt.setInt(4, ����ǽ�.get_2�б�());
			pstmt.setInt(5, ����ǽ�.get���1�б�());
			pstmt.setInt(6, ����ǽ�.get���2�б�());
			pstmt.setInt(7, ����ǽ�.get����л���());
			pstmt.setFloat(8, ����ǽ�.get�̼��л�����());
			pstmt.setString(9, ����ǽ�.get�Էºμ�());

			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
				return false;
	}
}

	public void delete(int ����) {

		String sql = "delete from ����ǽ� where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from ����ǽ� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from ����ǽ�";
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

	public List<����ǽ�> select����ǽ�(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<����ǽ�> list = new ArrayList<����ǽ�>();

		String sql = "select * from ����ǽ� order by ���� limit ?,?;";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				����ǽ� ����ǽ� = new ����ǽ�();

				����ǽ�.set�⵵(rs.getInt("�⵵"));
				����ǽ�.set�а���(rs.getString("�а���"));
				����ǽ�.set_1�б�(rs.getInt("1�б�"));
				����ǽ�.set_2�б�(rs.getInt("2�б�"));
				����ǽ�.set���1�б�(rs.getInt("���1�б�"));
				����ǽ�.set���2�б�(rs.getInt("���2�б�"));
				����ǽ�.set����(rs.getInt("����"));
				����ǽ�.set����л���(rs.getInt("����л���"));
				����ǽ�.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(����ǽ�);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
