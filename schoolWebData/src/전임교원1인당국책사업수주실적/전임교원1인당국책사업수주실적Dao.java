package ���ӱ���1�δ籹å������ֽ���;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import ���ӱ���1�δ籹å������ֽ���.���ӱ���1�δ籹å������ֽ���;

public class ���ӱ���1�δ籹å������ֽ���Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	
	private ResultSet rs;
	
	public boolean update(���ӱ���1�δ籹å������ֽ��� ���ӱ���1�δ籹å������ֽ���) {
		String sql = "update ���ӱ���1�δ籹å������ֽ��� set �⵵=?,�а���=?,���и��Ǳ�å��������Ѿ�=?,���ӱ���1�δ籹å��������Ѿ�=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ���ӱ���1�δ籹å������ֽ���.get�⵵());
			pstmt.setString(2, ���ӱ���1�δ籹å������ֽ���.get�а���());
			pstmt.setInt(3, ���ӱ���1�δ籹å������ֽ���.get���и��Ǳ�å��������Ѿ�());
			pstmt.setFloat(4, ���ӱ���1�δ籹å������ֽ���.get���ӱ���1�δ籹å��������Ѿ�());
			pstmt.setInt(5, ���ӱ���1�δ籹å������ֽ���.get����());

			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean insert(���ӱ���1�δ籹å������ֽ��� ���ӱ���1�δ籹å������ֽ���) {
		String sql = "insert into ���ӱ���1�δ籹å������ֽ���(�⵵,�а���,���и��Ǳ�å��������Ѿ�,���ӱ���1�δ籹å��������Ѿ�,�Էºμ�) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ���ӱ���1�δ籹å������ֽ���.get�⵵());
			pstmt.setString(2, ���ӱ���1�δ籹å������ֽ���.get�а���());
			pstmt.setInt(3, ���ӱ���1�δ籹å������ֽ���.get���и��Ǳ�å��������Ѿ�());
			pstmt.setFloat(4, ���ӱ���1�δ籹å������ֽ���.get���ӱ���1�δ籹å��������Ѿ�());
			pstmt.setString(5, ���ӱ���1�δ籹å������ֽ���.get�Էºμ�());
			
			System.out.print(pstmt);
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void delete(int ����) {
		String sql = "delete from ���ӱ���1�δ籹å������ֽ��� where ����=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from ���ӱ���1�δ籹å������ֽ��� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getCountRow() {
		String sql = "select count(*) from ���ӱ���1�δ籹å������ֽ���";
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

	public List<���ӱ���1�δ籹å������ֽ���> select���ӱ���1�δ籹å������ֽ���(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���ӱ���1�δ籹å������ֽ���> list = new ArrayList<���ӱ���1�δ籹å������ֽ���>();

		String sql = "select * from ���ӱ���1�δ籹å������ֽ��� order by ���� desc limit ?,? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���ӱ���1�δ籹å������ֽ��� ���ӱ���1�δ籹å������ֽ��� = new ���ӱ���1�δ籹å������ֽ���();
				���ӱ���1�δ籹å������ֽ���.set�⵵(rs.getInt("�⵵"));
				���ӱ���1�δ籹å������ֽ���.set�а���(rs.getString("�а���"));
				���ӱ���1�δ籹å������ֽ���.setT����(rs.getFloat("T����"));
				���ӱ���1�δ籹å������ֽ���.set���и��Ǳ�å��������Ѿ�(rs.getInt("���и��Ǳ�å��������Ѿ�"));
				���ӱ���1�δ籹å������ֽ���.set���ӱ���1�δ籹å��������Ѿ�(rs.getFloat("���ӱ���1�δ籹å��������Ѿ�"));
				���ӱ���1�δ籹å������ֽ���.set����(rs.getInt("����"));
				���ӱ���1�δ籹å������ֽ���.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(���ӱ���1�δ籹å������ֽ���);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Float ���ӱ���1�δ籹å������ֽ���(int �⵵, String �а���) {
		String sql = "select ���ӱ���1�δ籹å������ֽ��� from ���ӱ���1�δ籹å��������Ѿ� where �⵵=? and �а���=?";
		Float ���ӱ���1�δ籹å��������Ѿ� = 0.0F;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���ӱ���1�δ籹å��������Ѿ� = rs.getFloat("���ӱ���1�δ籹å��������Ѿ�");
			}
		} catch (SQLException e) {
			return 0.0F;
		}
		return ���ӱ���1�δ籹å��������Ѿ�;
	}
}
