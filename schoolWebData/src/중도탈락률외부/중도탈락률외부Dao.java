package �ߵ�Ż�����ܺ�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class �ߵ�Ż�����ܺ�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(�ߵ�Ż�����ܺ� �ߵ�Ż�����ܺ�) {
		String sql = "update �ߵ�Ż�����ܺ� set �����л���=?,�̵��=?,�̺���=?,����=?,�л���=?,��Ÿ=?,��=?,�ߵ�Ż����=?,T����=?,���и�=?,�а���=?,���=?,Ÿ�а�������=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, �ߵ�Ż�����ܺ�.get�����л���());
			pstmt.setInt(2, �ߵ�Ż�����ܺ�.get�̵��());
			pstmt.setInt(3, �ߵ�Ż�����ܺ�.get�̺���());
			pstmt.setInt(4, �ߵ�Ż�����ܺ�.get����());
			pstmt.setInt(5, �ߵ�Ż�����ܺ�.get�л���());
			pstmt.setInt(6, �ߵ�Ż�����ܺ�.get��Ÿ());
			pstmt.setInt(7, �ߵ�Ż�����ܺ�.get��());
			pstmt.setFloat(8, �ߵ�Ż�����ܺ�.get�ߵ�Ż����());
			pstmt.setFloat(9, �ߵ�Ż�����ܺ�.getT����());
			pstmt.setString(10, �ߵ�Ż�����ܺ�.get���и�());
			pstmt.setString(11, �ߵ�Ż�����ܺ�.get�а���());
			pstmt.setString(12, �ߵ�Ż�����ܺ�.get���());
			pstmt.setInt(13, �ߵ�Ż�����ܺ�.getŸ�а�������());
			pstmt.setInt(14, �ߵ�Ż�����ܺ�.get����());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(�ߵ�Ż�����ܺ� �ߵ�Ż�����ܺ�) {
		String sql = "insert into �ߵ�Ż�����ܺ�(���и�,�а���,�����л���,�̵��,�̺���,����,�л���,��Ÿ,��,�ߵ�Ż����,���,Ÿ�а�������,�Էºμ�) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �ߵ�Ż�����ܺ�.get���и�());
			pstmt.setString(2, �ߵ�Ż�����ܺ�.get�а���());
			pstmt.setInt(3, �ߵ�Ż�����ܺ�.get�����л���());
			pstmt.setInt(4, �ߵ�Ż�����ܺ�.get�̵��());
			pstmt.setInt(5, �ߵ�Ż�����ܺ�.get�̺���());
			pstmt.setInt(6, �ߵ�Ż�����ܺ�.get����());
			pstmt.setInt(7, �ߵ�Ż�����ܺ�.get�л���());
			pstmt.setInt(8, �ߵ�Ż�����ܺ�.get��Ÿ());
			pstmt.setInt(9, �ߵ�Ż�����ܺ�.get��());
			pstmt.setFloat(10, �ߵ�Ż�����ܺ�.get�ߵ�Ż����());
			pstmt.setString(11, �ߵ�Ż�����ܺ�.get���());
			pstmt.setInt(12, �ߵ�Ż�����ܺ�.getŸ�а�������());
			pstmt.setString(13, �ߵ�Ż�����ܺ�.get�Էºμ�());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from �ߵ�Ż�����ܺ� where ����=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from �ߵ�Ż�����ܺ� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from �ߵ�Ż�����ܺ�";
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

	public List<�ߵ�Ż�����ܺ�> select�ߵ�Ż�����ܺ�(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�ߵ�Ż�����ܺ�> list = new ArrayList<�ߵ�Ż�����ܺ�>();

		String sql = "select * from �ߵ�Ż�����ܺ� order by ���� desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			// ����� ����, ����
			while (rs.next()) {
				�ߵ�Ż�����ܺ� �ߵ�Ż�����ܺ� = new �ߵ�Ż�����ܺ�();
				�ߵ�Ż�����ܺ�.set����(rs.getInt("����"));
				�ߵ�Ż�����ܺ�.set���и�(rs.getString("���и�"));
				�ߵ�Ż�����ܺ�.set�а���(rs.getString("�а���"));
				�ߵ�Ż�����ܺ�.set��(rs.getInt("��"));
				�ߵ�Ż�����ܺ�.setT����(rs.getFloat("T����"));
				�ߵ�Ż�����ܺ�.set��Ÿ(rs.getInt("��Ÿ"));
				�ߵ�Ż�����ܺ�.set�̵��(rs.getInt("�̵��"));
				�ߵ�Ż�����ܺ�.set�̺���(rs.getInt("�̺���"));
				�ߵ�Ż�����ܺ�.set����(rs.getInt("����"));
				�ߵ�Ż�����ܺ�.set�����л���(rs.getInt("�����л���"));
				�ߵ�Ż�����ܺ�.set�ߵ�Ż����(rs.getFloat("�ߵ�Ż����"));
				�ߵ�Ż�����ܺ�.set��Ÿ(rs.getInt("��Ÿ"));
				�ߵ�Ż�����ܺ�.set�л���(rs.getInt("�л���"));
				�ߵ�Ż�����ܺ�.set���(rs.getString("���"));
				�ߵ�Ż�����ܺ�.setŸ�а�������(rs.getInt("Ÿ�а�������"));
				�ߵ�Ż�����ܺ�.set�Էºμ�(rs.getString("�Էºμ�"));
				
				list.add(�ߵ�Ż�����ܺ�);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public float �ߵ�Ż����(String ���и�) {
		float �ߵ�Ż���� = 0;

		String sql = "select �ߵ�Ż���� from �ߵ�Ż�����ܺ� where ���и�=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ���и�);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�ߵ�Ż���� = rs.getFloat("�ߵ�Ż����");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return �ߵ�Ż����;
	}

	public void updateT(float T����, String ���и�) {

		String sql = "update �ߵ�Ż�����ܺ� set T����=? where ���и�=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, T����);
			pstmt.setString(2, ���и�);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
