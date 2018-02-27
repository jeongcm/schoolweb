package ���Ի���Ȳ�ܺ�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class ���Ի���Ȳ�ܺ�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(���Ի���Ȳ�ܺ� ���Ի���Ȳ�ܺ�) {

		String sql = "update ���Ի���Ȳ�ܺ� set ���и�=?,�а���=?,�����������ڼ�=?,�����������ο�=?,���Ի������=?,���=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ���Ի���Ȳ�ܺ�.get���и�());
			pstmt.setString(2, ���Ի���Ȳ�ܺ�.get�а���());
			pstmt.setInt(3, ���Ի���Ȳ�ܺ�.get�����ڼ�());
			pstmt.setInt(4, ���Ի���Ȳ�ܺ�.get�����ο�());
			pstmt.setFloat(5, ���Ի���Ȳ�ܺ�.get���Ի������());
			pstmt.setString(6, ���Ի���Ȳ�ܺ�.get���());
			pstmt.setInt(7, ���Ի���Ȳ�ܺ�.get����());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}


	public boolean insert(���Ի���Ȳ�ܺ� ���Ի���Ȳ�ܺ�) {
		String sql = "insert into ���Ի���Ȳ�ܺ�(���и�,�а���,�����������ڼ�,�����������ο�,���Ի������,���,�Էºμ�) values(?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ���Ի���Ȳ�ܺ�.get���и�());
			pstmt.setString(2, ���Ի���Ȳ�ܺ�.get�а���());
			pstmt.setInt(3, ���Ի���Ȳ�ܺ�.get�����ڼ�());
			pstmt.setInt(4, ���Ի���Ȳ�ܺ�.get�����ο�());
			pstmt.setFloat(5, ���Ի���Ȳ�ܺ�.get���Ի������());
			pstmt.setString(6, ���Ի���Ȳ�ܺ�.get���());
			pstmt.setString(7, ���Ի���Ȳ�ܺ�.get�Էºμ�());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from ���Ի���Ȳ�ܺ� where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from ���Ի���Ȳ�ܺ� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from ���Ի���Ȳ�ܺ�";
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

	public List<���Ի���Ȳ�ܺ�> select���Ի���Ȳ�ܺ�(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���Ի���Ȳ�ܺ�> list = new ArrayList<���Ի���Ȳ�ܺ�>();

		String sql = "select ���Ի���Ȳ�ܺ�.* from ���Ի���Ȳ�ܺ� order by ���� desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���Ի���Ȳ�ܺ� ���Ի���Ȳ�ܺ� = new ���Ի���Ȳ�ܺ�();
				���Ի���Ȳ�ܺ�.set���и�(rs.getString("���и�"));
				���Ի���Ȳ�ܺ�.set�а���(rs.getString("�а���"));
				���Ի���Ȳ�ܺ�.set�����ο�(rs.getInt("�����������ο�"));
				���Ի���Ȳ�ܺ�.set�����ڼ�(rs.getInt("�����������ڼ�"));
				���Ի���Ȳ�ܺ�.set���Ի������(rs.getFloat("���Ի������"));
				���Ի���Ȳ�ܺ�.setT����(rs.getFloat("T����"));
				���Ի���Ȳ�ܺ�.set����(rs.getInt("����"));
				���Ի���Ȳ�ܺ�.set���(rs.getString("���"));
				���Ի���Ȳ�ܺ�.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(���Ի���Ȳ�ܺ�);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public float ���Ի������(String ���и�) {
		float ���Ի������ = 0;

		String sql = "select ���Ի������ from ���Ի���Ȳ�ܺ� where ���и�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ���и�);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���Ի������ = rs.getFloat("���Ի������");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ���Ի������;
	}

}
