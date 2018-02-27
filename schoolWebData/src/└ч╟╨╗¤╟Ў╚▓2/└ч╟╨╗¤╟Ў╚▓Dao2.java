package ���л���Ȳ2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import ���л���Ȳ2.���л���Ȳ2;

public class ���л���ȲDao2 {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	
	private ResultSet rs;

	public boolean update(���л���Ȳ2 ���л���Ȳ2) {

		String sql = "update ���л���Ȳ set �⵵=?,�а���=?,����=? ,��=?  where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ���л���Ȳ2.get�⵵());
			pstmt.setString(2, ���л���Ȳ2.get�а���());
			pstmt.setString(3, "10.01");
			pstmt.setInt(4, ���л���Ȳ2.get�л���());
			pstmt.setInt(5, ���л���Ȳ2.get����());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}


	public boolean insert(���л���Ȳ2 ���л���Ȳ2) {
		String sql = "insert into ���л���Ȳ(�⵵,����,�а���,��,�Էºμ�) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ���л���Ȳ2.get�⵵());
			pstmt.setString(2,"10.01");
			pstmt.setString(3, ���л���Ȳ2.get�а���());
			pstmt.setInt(4, ���л���Ȳ2.get�л���());
			pstmt.setString(5, ���л���Ȳ2.get�Էºμ�());

			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public void delete(int ����) {
		String sql = "delete from ���л���Ȳ where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = " delete from ���л���Ȳ  where ����='10.01' and �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from ���л���Ȳ where ����='10.01';";
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

	public List<���л���Ȳ2> select���л���Ȳ(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���л���Ȳ2> list = new ArrayList<���л���Ȳ2>();

		String sql = "select * from ���л���Ȳ  where ���� = '10.01' order by ���� desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���л���Ȳ2 ���л���Ȳ2 = new ���л���Ȳ2();
				
				���л���Ȳ2.set�⵵(rs.getInt("�⵵"));
				���л���Ȳ2.set�а���(rs.getString("�а���"));
				���л���Ȳ2.set�л���(rs.getInt("��"));
				���л���Ȳ2.set����(rs.getInt("����"));
				���л���Ȳ2.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(���л���Ȳ2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}