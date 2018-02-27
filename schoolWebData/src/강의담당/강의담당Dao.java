package ���Ǵ��;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import ���Ǵ��.���Ǵ��;

public class ���Ǵ��Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(���Ǵ�� ���Ǵ��) {
		String sql = "update �������Ǵ����� set �⵵=?,�а���=?,������������=?,���������ʼ�����=?,�����������ð���=? ,���Ǵ�����=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ���Ǵ��.get�⵵());
			pstmt.setString(2, ���Ǵ��.get�а���());
			pstmt.setFloat(3, ���Ǵ��.get��������());
			pstmt.setFloat(4, ���Ǵ��.get�����ʼ�����());
			pstmt.setFloat(5, ���Ǵ��.get�������ð���());
			pstmt.setFloat(6, ���Ǵ��.get���Ǵ�����());
			pstmt.setInt(7, ���Ǵ��.get����());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(���Ǵ�� ���Ǵ��) {
		String sql = "insert into �������Ǵ�����(�⵵,�а���,������������,���������ʼ�����,�����������ð���,���Ǵ�����,�Էºμ�) values(?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ���Ǵ��.get�⵵());
			pstmt.setString(2, ���Ǵ��.get�а���());
			pstmt.setFloat(3, ���Ǵ��.get��������());
			pstmt.setFloat(4, ���Ǵ��.get�����ʼ�����());
			pstmt.setFloat(5, ���Ǵ��.get�������ð���());
			pstmt.setFloat(6, ���Ǵ��.get���Ǵ�����());
			pstmt.setString(7, ���Ǵ��.get�Էºμ�());
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from �������Ǵ����� where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from �������Ǵ����� where �Էºμ� =?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public int getCountRow() {
		String sql = "select count(*) from �������Ǵ�����";
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
	


	public List<���Ǵ��> select���Ǵ��(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���Ǵ��> list = new ArrayList<���Ǵ��>();

		String sql = "select * from �������Ǵ����� order by ���� desc limit ?,? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���Ǵ�� ���Ǵ�� = new ���Ǵ��();
				���Ǵ��.set�⵵(rs.getInt("�⵵"));
				���Ǵ��.set�а���(rs.getString("�а���"));
				���Ǵ��.setT����(rs.getFloat("T����"));
				���Ǵ��.set���Ǵ�����(rs.getFloat("���Ǵ�����"));
				���Ǵ��.set�������ð���(rs.getFloat("�����������ð���"));
				���Ǵ��.set�����ʼ�����(rs.getFloat("���������ʼ�����"));
				���Ǵ��.set��������(rs.getFloat("������������"));
				���Ǵ��.set����(rs.getInt("����"));
				���Ǵ��.set�Էºμ�(rs.getString("�Էºμ�"));
				list.add(���Ǵ��);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public float �����Ѱ�(int �⵵, String �а���) {
		float �����Ѱ� = 0;
		String sql = "select sum(����) �Ѱ� from �������Ǵ����� where �⵵=? and �а���=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�����Ѱ� = rs.getFloat("�Ѱ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return �����Ѱ�;
	}

	public float �����ʼ�����(int �⵵, String �а���) {
		float �����ʼ����� = 0;
		String sql = "select sum(����) �����ʼ� from �������Ǵ����� where �⵵=? and �а���=? and ����='�����ʼ�����';";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�����ʼ����� = rs.getFloat("�����ʼ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return �����ʼ�����;

	}

	
	
}