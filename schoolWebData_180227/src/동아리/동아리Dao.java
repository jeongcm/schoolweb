package ���Ƹ�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import ���Ƹ�.���Ƹ�;

public class ���Ƹ�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(���Ƹ� ���Ƹ�) {
		String sql = "update ���Ƹ� set �⵵=?,�����μ�=?,�а���=?,���Ƹ���=?,����=?,��������=?,�л���ǥ�г�=?,�л���ǥ�̸�=?,ȸ����=?,��������=?,����������=?,��������=?,���=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ���Ƹ�.get�⵵());
			pstmt.setString(2, ���Ƹ�.get�����μ�());
			pstmt.setString(3, ���Ƹ�.get�а���());
			pstmt.setString(4, ���Ƹ�.get���Ƹ���());
			pstmt.setString(5, ���Ƹ�.get����());
			pstmt.setString(6, ���Ƹ�.get��������());
			pstmt.setString(7, ���Ƹ�.get�л���ǥ�г�());
			pstmt.setString(8, ���Ƹ�.get�л���ǥ�̸�());
			pstmt.setInt(9, ���Ƹ�.getȸ����());
			pstmt.setString(10, ���Ƹ�.get��������());
			pstmt.setInt(11, ���Ƹ�.get����������());
			pstmt.setString(12, ���Ƹ�.get��������());
			pstmt.setString(13, ���Ƹ�.get���());
			pstmt.setInt(14, ���Ƹ�.get����());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(���Ƹ� ���Ƹ�) {
		String sql = "insert into ���Ƹ�(�⵵,�����μ�,�а���,���Ƹ���,����,��������,�л���ǥ�г�,�л���ǥ�̸�,ȸ����,��������,����������,��������,���,�Էºμ�) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ���Ƹ�.get�⵵());
			pstmt.setString(2, ���Ƹ�.get�����μ�());
			pstmt.setString(3, ���Ƹ�.get�а���());
			pstmt.setString(4, ���Ƹ�.get���Ƹ���());
			pstmt.setString(5, ���Ƹ�.get����());
			pstmt.setString(6, ���Ƹ�.get��������());
			pstmt.setString(7, ���Ƹ�.get�л���ǥ�г�());
			pstmt.setString(8, ���Ƹ�.get�л���ǥ�̸�());
			pstmt.setInt(9, ���Ƹ�.getȸ����());
			pstmt.setString(10, ���Ƹ�.get��������());
			pstmt.setInt(11, ���Ƹ�.get����������());
			pstmt.setString(12, ���Ƹ�.get��������());
			pstmt.setString(13, ���Ƹ�.get���());
			pstmt.setString(14, ���Ƹ�.get�Էºμ�());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {

		String sql = "delete from ���Ƹ� where ����=? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from ���Ƹ� where �Էºμ�=?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from ���Ƹ�";
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

	public List<���Ƹ�> select���Ƹ�(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���Ƹ�> list = new ArrayList<���Ƹ�>();

		String sql = "select * from ���Ƹ� order by ���� desc limit ?,?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				���Ƹ� ���Ƹ� = new ���Ƹ�();
				���Ƹ�.set�⵵(rs.getInt("�⵵"));
				���Ƹ�.set�����μ�(rs.getString("�����μ�"));
				���Ƹ�.set�а���(rs.getString("�а���"));
				���Ƹ�.set���Ƹ���(rs.getString("���Ƹ���"));
				���Ƹ�.set����(rs.getString("����"));
				���Ƹ�.set��������(rs.getString("��������"));
				���Ƹ�.set�л���ǥ�г�(rs.getString("�л���ǥ�г�"));
				���Ƹ�.set�л���ǥ�̸�(rs.getString("�л���ǥ�̸�"));
				���Ƹ�.setȸ����(rs.getInt("ȸ����"));
				���Ƹ�.set��������(rs.getString("��������"));
				���Ƹ�.set��������(rs.getString("��������"));
				���Ƹ�.set����������(rs.getInt("����������"));
				���Ƹ�.set���(rs.getString("���"));
				���Ƹ�.set����(rs.getInt("����"));
				���Ƹ�.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(���Ƹ�);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int ��(int �����⵵, String �����а���) {
		int �� = 0;
		String sql = "select sum(ȸ����) �� from ���Ƹ� where �⵵=? and �а���=?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, �����⵵);
			pstmt.setString(2, �����а���);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				�� = rs.getInt("��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ��;
	}

	public void update���Ƹ���������(int �����⵵, String �����а���, float ���Ƹ������������) {
		String sql = "update ���Ƹ��������� set ���Ƹ���������=? where �а���=? and �⵵=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, ���Ƹ������������);
			pstmt.setString(2, �����а���);
			pstmt.setInt(3, �����⵵);
			
		
			pstmt.executeUpdate();
			
			System.out.println(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ���Ƹ���������insert(int �����⵵, String �����а���) {
		String sql = "insert into ���Ƹ���������(�а���,�⵵) values(?,?);";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �����а���);
			pstmt.setInt(2, �����⵵);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}