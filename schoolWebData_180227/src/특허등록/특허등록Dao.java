package Ư����;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import Ư����.Ư����;

public class Ư����Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(Ư���� Ư����) {
		String sql = "update Ư���Ϲױ���������Է� set �⵵=?,�а���=?,����=?,����=?  where ����=?";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Ư����.get�⵵());
			pstmt.setString(2, Ư����.get�а���());
			pstmt.setInt(3, Ư����.get����());
			pstmt.setInt(4, Ư����.get����());
			pstmt.setInt(5, Ư����.get����());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(Ư���� Ư����) {
		String sql = "insert into Ư���Ϲױ���������Է�(�⵵,�а���,����,����,�Էºμ�) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Ư����.get�⵵());
			pstmt.setString(2, Ư����.get�а���());
			pstmt.setInt(3, Ư����.get����());
			pstmt.setInt(4, Ư����.get����());
			pstmt.setString(5, Ư����.get�Էºμ�());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {

		String sql = "delete from Ư���Ϲױ���������Է� where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from Ư���Ϲױ���������Է� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from Ư���Ϲױ���������Է�";
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

	public List<Ư����> selectƯ����(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<Ư����> list = new ArrayList<Ư����>();

		String sql = "select * from Ư���Ϲױ���������Է� order by ���� desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Ư���� Ư���� = new Ư����();
				Ư����.set�⵵(rs.getInt("�⵵"));
				Ư����.set�а���(rs.getString("�а���"));
				Ư����.set����(rs.getInt("����"));
				Ư����.set����(rs.getInt("����"));
				Ư����.set����(rs.getInt("����"));
				Ư����.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(Ư����);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public float Ư��������������(int �����⵵, String �����а���) {
		float ���ױ���� = 0;

		String sql = "select sum(���ױ����) ���ױ���� from ������� where �⵵=? and �а���=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �����⵵);
			pstmt.setString(2, �����а���);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				���ױ���� = rs.getFloat("���ױ����");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ���ױ����;
	}
}
