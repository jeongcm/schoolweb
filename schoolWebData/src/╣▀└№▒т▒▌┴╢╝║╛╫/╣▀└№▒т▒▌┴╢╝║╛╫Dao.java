package �������������;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import �������������.�������������;

public class �������������Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	
	private ResultSet rs;

	public boolean update(������������� �������������) {
		String sql = "update ������������� set �⵵=?,�а���=?,������α�=?,�������������=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, �������������.get�⵵());
			pstmt.setString(2, �������������.get�а���());
			pstmt.setInt(3, �������������.get������α�());
			pstmt.setFloat(4, �������������.get������������װ��());
			pstmt.setInt(5, �������������.get����());

			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(������������� �������������) {
		String sql = "insert into �������������(�⵵,�а���,������α�,�������������,�Էºμ�) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, �������������.get�⵵());
			pstmt.setString(2, �������������.get�а���());
			pstmt.setInt(3, �������������.get������α�());
			pstmt.setFloat(4, �������������.get������������װ��());
			pstmt.setString(5, �������������.get�Էºμ�());
			
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from ������������� where ����=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from ������������� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from �������������";
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

	public List<�������������> select�������������(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�������������> list = new ArrayList<�������������>();

		String sql = "select * from ������������� order by ���� desc limit ?,? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				������������� ������������� = new �������������();
				�������������.set�⵵(rs.getInt("�⵵"));
				�������������.set�а���(rs.getString("�а���"));
				�������������.setT����(rs.getFloat("T����"));
				�������������.set������α�(rs.getInt("������α�"));
				�������������.set������������װ��(rs.getFloat("�������������"));
				�������������.set����(rs.getInt("����"));
				�������������.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(�������������);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int ������ݸ�ݾ�(int �⵵, String �а���) {
		String sql = "select sum(�ݾ�) ������ݸ�ݾ� from ������������׻��ڷ�  where �а���=? and �⵵=?;";
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
	
	public float �������������(int �⵵, String �а���) {
		String sql = "select ������������� from ������������� where �⵵=? and �а���=?";
		float ������������� = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				������������� = rs.getFloat("�������������");
			}
		} catch (SQLException e) {
			return 0;
		}
		return �������������;
	}
}