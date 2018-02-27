package ��������;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import ��������.��������;

public class ��������Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	
	private ResultSet rs;

	public boolean update(�������� ��������) {
		String sql = "update �������� set �⵵=?,�а���=?,����=?,����=?,������ܵ�����=?,������ܵ����ĺ�=?,SCI��=?,SCOPUS�м���=?,��������=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ��������.get�⵵());
			pstmt.setString(2, ��������.get�а���());
			pstmt.setFloat(3, ��������.get����());
			pstmt.setFloat(4, ��������.get����());
			pstmt.setFloat(5, ��������.get������ܵ�����());
			pstmt.setFloat(6, ��������.get������ܵ����ĺ�());
			pstmt.setFloat(7, ��������.getSCI��());
			pstmt.setFloat(8, ��������.getSCOPUS�м���());
			pstmt.setFloat(9, ��������.get��������());
			pstmt.setInt(10, ��������.get����());
			
			pstmt.executeUpdate();

			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(�������� ��������) {
		String sql = "insert into ��������(�⵵,�а���,����,����,������ܵ�����,������ܵ����ĺ�,SCI��,SCOPUS�м���,��������,�Էºμ�) values(?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ��������.get�⵵());
			pstmt.setString(2, ��������.get�а���());
			pstmt.setFloat(3, ��������.get����());
			pstmt.setFloat(4, ��������.get����());
			pstmt.setFloat(5, ��������.get������ܵ�����());
			pstmt.setFloat(6, ��������.get������ܵ����ĺ�());
			pstmt.setFloat(7, ��������.getSCI��());
			pstmt.setFloat(8, ��������.getSCOPUS�м���());
			pstmt.setFloat(9, ��������.get��������());
			pstmt.setString(10, ��������.get�Էºμ�());
			
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from �������� where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from �������� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from ��������";
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

	public List<��������> select��������(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<��������> list = new ArrayList<��������>();

		String sql = "select * from �������� order by ���� desc limit ?,? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�������� �������� = new ��������();
				
				��������.set����(rs.getInt("����"));
				��������.set�⵵(rs.getInt("�⵵"));
				��������.set�а���(rs.getString("�а���"));
				��������.setSCI��(rs.getFloat("SCI��"));
				��������.setSCOPUS�м���(rs.getFloat("SCOPUS�м���"));
				��������.setT����(rs.getFloat("T����"));
				��������.set������ܵ�����(rs.getFloat("������ܵ�����"));
				��������.set������ܵ����ĺ�(rs.getFloat("������ܵ����ĺ�"));
				��������.set����(rs.getFloat("����"));
				��������.set����(rs.getFloat("����"));
				��������.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(��������);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String �й��迭(int �⵵, String �а���) {
		String sql = "select �й��迭1 �й��迭 from �а���Ȳ where �а���Ȳ.�⵵=? and �а���Ȳ.�а���=?";
		String �й��迭 = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				�й��迭 = rs.getString("�й��迭");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return �й��迭;
	}

	public float ��������(int �⵵, String �а���) {
		float �������� = 0;

		String sql = "select �������� from �������� where �⵵=? and �а���=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�������� = rs.getFloat("��������");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ��������;
	}
}