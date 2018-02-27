package �������б�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import �������б�.�������б�;

public class �������б�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	
	private ResultSet rs;

	public boolean update(�������б� �������б�) {
		String sql = "update �������б� set �⵵=?,�а���=?,�������б�=?,1�δ米�����б�=?  where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, �������б�.get�⵵());
			pstmt.setString(2, �������б�.get�а���());
			pstmt.setInt(3, �������б�.get�������б�());
			pstmt.setFloat(4, �������б�.get���δ米�����б�());
			pstmt.setInt(5, �������б�.get����());

			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(�������б� �������б�) {
		String sql = "insert into �������б�(�⵵,�а���,�������б�,1�δ米�����б�,�Էºμ�) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, �������б�.get�⵵());
			pstmt.setString(2, �������б�.get�а���());
			pstmt.setInt(3, �������б�.get�������б�());
			pstmt.setFloat(4, �������б�.get���δ米�����б�());
			pstmt.setString(5, �������б�.get�Էºμ�());
			
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from �������б� where ����=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from �������б� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from �������б�";
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

	public List<�������б�> select�������б�(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�������б�> list = new ArrayList<�������б�>();

		String sql = "select * from �������б� order by ���� desc limit ?,? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�������б� �������б� = new �������б�();
				
				�������б�.set�⵵(rs.getInt("�⵵"));
				�������б�.set�а���(rs.getString("�а���"));
				�������б�.set�������б�(rs.getInt("�������б�"));
				�������б�.set����(rs.getInt("����"));
				�������б�.set�Էºμ�(rs.getString("�Էºμ�"));
				
				list.add(�������б�);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public float ���л����(int �⵵, String �а���) {
		float ���л���� = 0;
		String sql = "select avg(��) ���л����  from ���л���Ȳ  where �⵵=? and �а���=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				���л���� = rs.getFloat("���л����");
			}

			return ���л����;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public float ���δ米�����б�(int �⵵, String �а���) {
		String sql = "select 1�δ米�����б� from �������б� where �⵵=? and �а���=?;";
		float ���δ米�����б� = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				���δ米�����б� = rs.getInt("1�δ米�����б�");
			}
			return ���δ米�����б�;

		} catch (SQLException e) {
			return 0;
		}
	}

}