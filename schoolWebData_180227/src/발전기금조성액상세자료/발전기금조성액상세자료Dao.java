package ������������׻��ڷ�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class ������������׻��ڷ�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(������������׻��ڷ� ������������׻��ڷ�) {
		String sql = "update ������������׻��ڷ� set �⵵=?,�а���=?,����=?,�ݾ�=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ������������׻��ڷ�.get�⵵());
			pstmt.setString(2, ������������׻��ڷ�.get�а���());
			pstmt.setString(3, ������������׻��ڷ�.get����());
			pstmt.setInt(4, ������������׻��ڷ�.get�ݾ�());
			pstmt.setInt(5, ������������׻��ڷ�.get����());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insert(������������׻��ڷ� ������������׻��ڷ�) {
		String sql = "insert into ������������׻��ڷ�(�⵵,�а���,����,�ݾ�,�Էºμ�) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ������������׻��ڷ�.get�⵵());
			pstmt.setString(2, ������������׻��ڷ�.get�а���());
			pstmt.setString(3, ������������׻��ڷ�.get����());
			pstmt.setInt(4, ������������׻��ڷ�.get�ݾ�());
			pstmt.setString(5, ������������׻��ڷ�.get�Էºμ�());
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from ������������׻��ڷ� where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from ������������׻��ڷ� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from ������������׻��ڷ�";
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

	public List<������������׻��ڷ�> select������������׻��ڷ�(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<������������׻��ڷ�> list = new ArrayList<������������׻��ڷ�>();

		String sql = "select * from ������������׻��ڷ� order by ���� desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				������������׻��ڷ� ������������׻��ڷ� = new ������������׻��ڷ�();
				������������׻��ڷ�.set�⵵(rs.getInt("�⵵"));
				������������׻��ڷ�.set�а���(rs.getString("�а���"));
				������������׻��ڷ�.set����(rs.getString("����"));
				������������׻��ڷ�.set�ݾ�(rs.getInt("�ݾ�"));
				������������׻��ڷ�.set����(rs.getInt("����"));
				������������׻��ڷ�.set�Էºμ�(rs.getString("�Էºμ�"));
				

				list.add(������������׻��ڷ�);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public int ������α�(int �⵵, String �а���) {
		int ������α� = 0;

		String sql = "select ������α� from ������������� where �⵵=? and �а���=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				������α� = rs.getInt("������α�");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ������α�;
	}

	public Float �������������(int �⵵, String �а���) {
		float ������������� = 0;

		String sql = "select ������������� from ������������� where �⵵=? and �а���=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				������������� = rs.getFloat("�������������");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return �������������;
	}

	public void update�������(int �⵵, String �а���, float �������������) {
		String sql = "update ������������� set �������������=? where �⵵=? and �а���=?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, �������������);
			pstmt.setInt(2, �⵵);
			pstmt.setString(3, �а���);
			pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int ������ݸ�ݾ�(int �⵵, String �а���) {
		String sql = "select sum(�ݾ�) div 1000 ������ݸ�ݾ� from ������������׻��ڷ�  where �а���=? and �⵵=?;";
		int ������ݸ�ݾ� = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �а���);
			pstmt.setInt(2, �⵵);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				������ݸ�ݾ� = rs.getInt("������ݸ�ݾ�");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ������ݸ�ݾ�;
	}


}
