package ��������ڿܺ�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class ��������ڿܺ�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(��������ڿܺ� ��������ڿܺ�) {
		String sql = "update ��������ڿܺ� set ���и�=?,�а���=?,������=?,�Դ���=?,����Ұ�����=?,�ܱ������л�=?,�ǰ��������尡�����ܴ��=?,���д�ñ������=?,��=?,���=?  where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ��������ڿܺ�.get���и�());
			pstmt.setString(2, ��������ڿܺ�.get�а���());
			pstmt.setInt(3, ��������ڿܺ�.get������());
			pstmt.setInt(4, ��������ڿܺ�.get�Դ���());
			pstmt.setInt(5, ��������ڿܺ�.get����Ұ�����());
			pstmt.setInt(6, ��������ڿܺ�.get�ܱ������л�());
			pstmt.setInt(7, ��������ڿܺ�.get�ǰ��������尡�����ܴ��());
			pstmt.setInt(8, ��������ڿܺ�.get���д�ñ������());
			pstmt.setInt(9, ��������ڿܺ�.get��());
			pstmt.setString(10, ��������ڿܺ�.get���());
			pstmt.setInt(11, ��������ڿܺ�.get����());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(��������ڿܺ� ��������ڿܺ�) {
		String sql = "insert into ��������ڿܺ�(���и�,�а���,������,�Դ���,����Ұ�����,�ܱ������л�,�ǰ��������尡�����ܴ��,��,���,�Էºμ�) values(?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ��������ڿܺ�.get���и�());
			pstmt.setString(2, ��������ڿܺ�.get�а���());
			pstmt.setInt(3, ��������ڿܺ�.get������());
			pstmt.setInt(4, ��������ڿܺ�.get�Դ���());
			pstmt.setInt(5, ��������ڿܺ�.get����Ұ�����());
			pstmt.setInt(6, ��������ڿܺ�.get�ܱ������л�());
			pstmt.setInt(7, ��������ڿܺ�.get�ǰ��������尡�����ܴ��());
			pstmt.setInt(8, ��������ڿܺ�.get��());
			pstmt.setString(9, ��������ڿܺ�.get���());
			pstmt.setString(10, ��������ڿܺ�.get�Էºμ�());


			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from ��������ڿܺ� where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�)  {
		String sql = "delete from ��������ڿܺ� where �Էºμ� =?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from ��������ڿܺ�";
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

	public List<��������ڿܺ�> select��������ڿܺ�(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<��������ڿܺ�> list = new ArrayList<��������ڿܺ�>();

		String sql = "select * from ��������ڿܺ� order by ���� desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				��������ڿܺ� ��������ڿܺ� = new ��������ڿܺ�();
				��������ڿܺ�.set����(rs.getInt("����"));
				��������ڿܺ�.set���и�(rs.getString("���и�"));
				��������ڿܺ�.set�а���(rs.getString("�а���"));
				��������ڿܺ�.set��(rs.getInt("��"));
				��������ڿܺ�.set������(rs.getInt("������"));
				��������ڿܺ�.set�Դ���(rs.getInt("�Դ���"));
				��������ڿܺ�.set����Ұ�����(rs.getInt("����Ұ�����"));
				��������ڿܺ�.set�ܱ������л�(rs.getInt("�ܱ������л�"));
				��������ڿܺ�.set�ǰ��������尡�����ܴ��(rs.getInt("�ǰ��������尡�����ܴ��"));
				��������ڿܺ�.set���д�ñ������(rs.getInt("���д�ñ������"));
				��������ڿܺ�.set���(rs.getString("���"));
				��������ڿܺ�.set�Էºμ�(rs.getString("�Էºμ�"));


				list.add(��������ڿܺ�);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
