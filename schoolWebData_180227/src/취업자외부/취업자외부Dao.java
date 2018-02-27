package ����ڿܺ�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class ����ڿܺ�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(����ڿܺ� ����ڿܺ�) {
		String sql = "update ����ڿܺ� set ���и�=?,�а���=?,�ǰ�����db���������=?,�ؿ������=?,����������=?,��=? ,���=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ����ڿܺ�.get���и�());
			pstmt.setString(2, ����ڿܺ�.get�а���());
			pstmt.setInt(3, ����ڿܺ�.get�ǰ�����DB���������());
			pstmt.setInt(4, ����ڿܺ�.get�ؿ������());
			pstmt.setInt(5, ����ڿܺ�.get����������());
			pstmt.setInt(6, ����ڿܺ�.get��());
			pstmt.setString(7, ����ڿܺ�.get���());
			pstmt.setInt(8, ����ڿܺ�.get����());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(����ڿܺ� ����ڿܺ�) {
		String sql = "insert into ����ڿܺ�(���и�,�а���,�ǰ�����db���������,�ؿ������,����������,��,���,�Էºμ�) values(?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ����ڿܺ�.get���и�());
			pstmt.setString(2, ����ڿܺ�.get�а���());
			pstmt.setInt(3, ����ڿܺ�.get�ǰ�����DB���������());
			pstmt.setInt(4, ����ڿܺ�.get�ؿ������());
			pstmt.setInt(5, ����ڿܺ�.get����������());
			pstmt.setInt(6, ����ڿܺ�.get��());
			pstmt.setString(7, ����ڿܺ�.get���());
			pstmt.setString(8, ����ڿܺ�.get�Էºμ�());
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from ����ڿܺ� where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void dellAll(String �Էºμ�) {
		String sql = "delete from ����ڿܺ� where �Էºμ� =?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from ����ڿܺ�";
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

	public List<����ڿܺ�> select����ڿܺ�(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<����ڿܺ�> list = new ArrayList<����ڿܺ�>();

		String sql = "select * from ����ڿܺ� order by ���� desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				����ڿܺ� ����ڿܺ� = new ����ڿܺ�();
				����ڿܺ�.set���и�(rs.getString("���и�"));
				����ڿܺ�.set�а���(rs.getString("�а���"));
				����ڿܺ�.set��(rs.getInt("��"));
				����ڿܺ�.set�ǰ�����DB���������(rs.getInt("�ǰ�����DB���������"));
				����ڿܺ�.set�ؿ������(rs.getInt("�ؿ������"));
				����ڿܺ�.set����������(rs.getInt("����������"));
				����ڿܺ�.set����(rs.getInt("����"));
				����ڿܺ�.set���(rs.getString("���"));
				����ڿܺ�.set�Էºμ�(rs.getString("�Էºμ�"));
				list.add(����ڿܺ�);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
