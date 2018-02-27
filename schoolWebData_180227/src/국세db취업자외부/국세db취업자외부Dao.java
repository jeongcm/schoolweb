package ����db����ڿܺ�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class ����db����ڿܺ�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(��������ڿܺ� ����db����ڿܺ�) {
		String sql = "update ����db����ڿܺ� set ����â��Ȱ�����缭=?,1��â����=?,��������=?,��=?,���и�=?,�а���=?,���=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ����db����ڿܺ�.get����â��Ȱ�����缭());
			pstmt.setInt(2, ����db����ڿܺ�.get����â����());
			pstmt.setInt(3, ����db����ڿܺ�.get��������());
			pstmt.setInt(4, ����db����ڿܺ�.get��());
			pstmt.setString(5, ����db����ڿܺ�.get���и�());
			pstmt.setString(6, ����db����ڿܺ�.get�а���());
			pstmt.setString(7, ����db����ڿܺ�.get���());
			pstmt.setInt(8, ����db����ڿܺ�.get����());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(��������ڿܺ� ����db����ڿܺ�) {
		String sql = "insert into ����db����ڿܺ�(���и�,��,�а���,����â��Ȱ�����缭,1��â����,��������,��,���,�Էºμ�) values(?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ����db����ڿܺ�.get���и�());
			pstmt.setString(2, ����db����ڿܺ�.get��());
			pstmt.setString(3, ����db����ڿܺ�.get�а���());
			pstmt.setInt(4, ����db����ڿܺ�.get����â��Ȱ�����缭());
			pstmt.setInt(5, ����db����ڿܺ�.get����â����());
			pstmt.setInt(6, ����db����ڿܺ�.get��������());
			pstmt.setInt(7, ����db����ڿܺ�.get��());
			pstmt.setString(8, ����db����ڿܺ�.get���());
			pstmt.setString(9, ����db����ڿܺ�.get�Էºμ�());
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}

	public void delete(int ����) {
		String sql = "delete from ����db����ڿܺ� where ����=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from ����db����ڿܺ�  where �Էºμ� =?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from ����db����ڿܺ�";
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

	// ��ü ���� ������ ����Ʈ�� �����ϴ� �޼���
	public List<��������ڿܺ�> select����db����ڿܺ�(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<��������ڿܺ�> list = new ArrayList<��������ڿܺ�>();

		String sql = "select * from ����db����ڿܺ� order by ���� desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			// ����� ����, ����
			while (rs.next()) {
				��������ڿܺ� ����db����ڿܺ� = new ��������ڿܺ�();
				����db����ڿܺ�.set����(rs.getInt("����"));
				����db����ڿܺ�.set���и�(rs.getString("���и�"));
				����db����ڿܺ�.set�а���(rs.getString("�а���"));
				����db����ڿܺ�.set��(rs.getInt("��"));
				����db����ڿܺ�.set��(rs.getString("��"));
				����db����ڿܺ�.set����â��Ȱ�����缭(rs.getInt("����â��Ȱ�����缭"));
				����db����ڿܺ�.set����â����(rs.getInt("1��â����"));
				����db����ڿܺ�.set��������(rs.getInt("��������"));
				����db����ڿܺ�.set���(rs.getString("���"));
				����db����ڿܺ�.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(����db����ڿܺ�);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
