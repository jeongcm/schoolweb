package Ư���ϻ�;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import Ư���ϻ�.Ư���ϻ�;

public class Ư���ϻ�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(Ư���ϻ� Ư���ϻ�) {
		String sql = "update Ư���� set �⵵=?,�а���=?,��ǥ�߸���=?,��������=?,���ױ����=? where ����=?";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Ư���ϻ�.get�⵵());
			pstmt.setString(2, Ư���ϻ�.get�а���());
			pstmt.setString(3, Ư���ϻ�.get��ǥ�߸���());
			pstmt.setString(4, Ư���ϻ�.get��������());
			pstmt.setFloat(5, Ư���ϻ�.get���ױ����());
			pstmt.setInt(6, Ư���ϻ�.get����());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(Ư���ϻ� Ư���ϻ�) {
		String sql = "insert into Ư����(�⵵,�а���,��ǥ�߸���,��������,���ױ����,�Էºμ�) values(?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Ư���ϻ�.get�⵵());
			pstmt.setString(2, Ư���ϻ�.get�а���());
			pstmt.setString(3, Ư���ϻ�.get��ǥ�߸���());
			pstmt.setString(4, Ư���ϻ�.get��������());
			pstmt.setFloat(5, Ư���ϻ�.get���ױ����());
			pstmt.setString(6, Ư���ϻ�.get�Էºμ�());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {

		String sql = "delete from Ư���� where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from Ư���� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from Ư����";
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

	public List<Ư���ϻ�> selectƯ���ϻ�(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<Ư���ϻ�> list = new ArrayList<Ư���ϻ�>();

		String sql = "select * from Ư���� order by ���� desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Ư���ϻ� Ư���ϻ� = new Ư���ϻ�();
				Ư���ϻ�.set�⵵(rs.getInt("�⵵"));
				Ư���ϻ�.set�а���(rs.getString("�а���"));
				Ư���ϻ�.set��ǥ�߸���(rs.getString("��ǥ�߸���"));
				Ư���ϻ�.set��������(rs.getString("��������"));
				Ư���ϻ�.set���ױ����(rs.getFloat("���ױ����"));
				Ư���ϻ�.set����(rs.getInt("����"));
				Ư���ϻ�.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(Ư���ϻ�);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
