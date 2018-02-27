package ����������ڷ�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import ����������ڷ�.����������ڷ�;

public class ����������ڷ�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(����������ڷ� ����������ڷ�) {

		String sql = "update ������� set �⵵=?,�а���=?,����=?,��������=?,��ȸ��=?,���󳻿�=?,��������=?,��������=? where ����=?;";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����������ڷ�.get�⵵());
			pstmt.setString(2, ����������ڷ�.get�а���());
			pstmt.setString(3, ����������ڷ�.get����());
			pstmt.setString(4, ����������ڷ�.get��������());
			pstmt.setString(5, ����������ڷ�.get��ȸ��());
			pstmt.setString(6, ����������ڷ�.get���󳻿�());
			pstmt.setString(7, ����������ڷ�.get��������());
			pstmt.setString(8, ����������ڷ�.get��������());

			pstmt.setInt(9, ����������ڷ�.get����());
			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(����������ڷ� ����������ڷ�) {
		String sql = "insert into �������(�⵵,�а���,����,��������,��ȸ��,���󳻿�,��������,��������,�Էºμ�) values(?,?,?,?,?,?,?,?,?);";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����������ڷ�.get�⵵());
			pstmt.setString(2, ����������ڷ�.get�а���());
			pstmt.setString(3, ����������ڷ�.get����());
			pstmt.setString(4, ����������ڷ�.get��������());
			pstmt.setString(5, ����������ڷ�.get��ȸ��());
			pstmt.setString(6, ����������ڷ�.get���󳻿�());
			pstmt.setString(7, ����������ڷ�.get��������());
			pstmt.setString(8, ����������ڷ�.get��������());
			pstmt.setString(9, ����������ڷ�.get�Էºμ�());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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

	public List<����������ڷ�> select����������ڷ�(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<����������ڷ�> list = new ArrayList<����������ڷ�>();
		ResultSet rs = null;
		try {
			String sql = "select * from ������� order by  ���� desc limit ?,? ;";
			Class.forName("com.mysql.jdbc.Driver");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				����������ڷ� ����������ڷ� = new ����������ڷ�();

				����������ڷ�.set�⵵(rs.getInt("�⵵"));
				����������ڷ�.set�а���(rs.getString("�а���"));
				����������ڷ�.set��ȸ�Ը�Ժ�(rs.getString("����"));
				����������ڷ�.set��������(rs.getString("��������"));
				����������ڷ�.set��ȸ��(rs.getString("��ȸ��"));
				����������ڷ�.set���󳻿�(rs.getString("���󳻿�"));
				����������ڷ�.set��������(rs.getString("��������"));
				����������ڷ�.set��������(rs.getString("��������"));
				����������ڷ�.set����(rs.getInt("����"));
				����������ڷ�.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(����������ڷ�);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void �����������insert(int �����⵵, String �����а���) {
		String sql = "insert into �����������(�а���,�⵵) values(?,?);";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �����а���);
			pstmt.setInt(2, �����⵵);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update�������(int �����⵵, String �����а���, float ����������) {
		String sql = "update ����������� set �������=? where �а���=? and �⵵=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, ����������);
			pstmt.setString(2, �����а���);
			pstmt.setInt(3, �����⵵);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public float ���ӱ����������(int �����⵵, String �����а���) {
		int �� = 0;
		String sql = "SELECT ��������*3+��������*2+��������*1 �� FROM schoolData.�������view where �⵵=? and �а���=?;";
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

	public float ���л��������(int �����⵵, String �����а���) {
		int �� = 0;
		String sql = "SELECT �л�����*3+�л�����*2+�л�����*1 �� FROM schoolData.�������view where �⵵=? and �а���=?;";
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

}
