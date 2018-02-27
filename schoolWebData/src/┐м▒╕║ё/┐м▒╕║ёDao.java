package ������;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class ������Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(������ ������) {
		String sql = "update ������ set �⵵=?,�а���=?,�߾�����=?,����ü=?,�ΰ�=?,�ܱ�=?,������=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ������.get�⵵());
			pstmt.setString(2, ������.get�а���());
			pstmt.setInt(3, ������.get�߾�����());
			pstmt.setInt(4, ������.get����ü());
			pstmt.setInt(5, ������.get�ΰ�());
			pstmt.setInt(6, ������.get�ܱ�());
			pstmt.setFloat(7, ������.get������());
			pstmt.setInt(8, ������.get����());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(������ ������) {
		String sql = "insert into ������(�⵵,�а���,�߾�����,����ü,�ΰ�,�ܱ�,������,�Էºμ�) values(?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ������.get�⵵());
			pstmt.setString(2, ������.get�а���());
			pstmt.setInt(3, ������.get�߾�����());
			pstmt.setInt(4, ������.get����ü());
			pstmt.setInt(5, ������.get�ΰ�());
			pstmt.setInt(6, ������.get�ܱ�());
			pstmt.setFloat(7, ������.get������());
			pstmt.setString(8, ������.get�Էºμ�());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from ������ where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from ������ where �Էºμ� =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from ������";
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

	public List<������> select������(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<������> list = new ArrayList<������>();

		String sql = "select * from ������ order by ���� desc limit ?,? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				������ ������ = new ������();
				������.set�⵵(rs.getInt("�⵵"));
				������.set�а���(rs.getString("�а���"));
				������.setT����(rs.getFloat("T����"));
				������.set�ΰ�(rs.getInt("�ΰ�"));
				������.set������(rs.getFloat("������"));
				������.set�ܱ�(rs.getInt("�ܱ�"));
				������.set�߾�����(rs.getInt("�߾�����"));
				������.set����ü(rs.getInt("����ü"));
				������.set����(rs.getInt("����"));
				������.set�Էºμ�(rs.getString("�Էºμ�"));
				list.add(������);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}




	public float ������(int �⵵, String �а���) {
		float ������ = 0;

		String sql = "select ������ from ������ where �⵵=? and �а���=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				������ = rs.getFloat("������");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ������;
	}


}