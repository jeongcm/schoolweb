package �ߵ�Ż����;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class �ߵ�Ż����Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(�ߵ�Ż���� �ߵ�Ż����) {
		String sql = "update �ߵ�Ż���� set �����л���=?,�̵��=?,�̺���=?,����=?,�л���=?,��Ÿ=?,��=?,�ߵ�Ż����=?,T����=?,�⵵=?,�а���=?,Ÿ�а�������=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, �ߵ�Ż����.get�����л���());
			pstmt.setInt(2, �ߵ�Ż����.get�̵��());
			pstmt.setInt(3, �ߵ�Ż����.get�̺���());
			pstmt.setInt(4, �ߵ�Ż����.get����());
			pstmt.setInt(5, �ߵ�Ż����.get�л���());
			pstmt.setInt(6, �ߵ�Ż����.get��Ÿ());
			pstmt.setInt(7, �ߵ�Ż����.get��());
			pstmt.setFloat(8, �ߵ�Ż����.get�ߵ�Ż����());
			pstmt.setFloat(9, �ߵ�Ż����.getT����());
			pstmt.setInt(10, �ߵ�Ż����.get�⵵());
			pstmt.setString(11, �ߵ�Ż����.get�а���());
			pstmt.setInt(12, �ߵ�Ż����.getŸ�а�������());
			pstmt.setInt(13, �ߵ�Ż����.get����());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(�ߵ�Ż���� �ߵ�Ż����) {
		String sql = "insert into �ߵ�Ż����(�⵵,�а���,�����л���,�̵��,�̺���,����,�л���,��Ÿ,��,�ߵ�Ż����,�Էºμ�,Ÿ�а�������) values(?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �ߵ�Ż����.get�⵵());
			pstmt.setString(2, �ߵ�Ż����.get�а���());
			pstmt.setInt(3, �ߵ�Ż����.get�����л���());
			pstmt.setInt(4, �ߵ�Ż����.get�̵��());
			pstmt.setInt(5, �ߵ�Ż����.get�̺���());
			pstmt.setInt(6, �ߵ�Ż����.get����());
			pstmt.setInt(7, �ߵ�Ż����.get�л���());
			pstmt.setInt(8, �ߵ�Ż����.get��Ÿ());
			pstmt.setInt(9, �ߵ�Ż����.get��());
			pstmt.setFloat(10, �ߵ�Ż����.get�ߵ�Ż����());
			pstmt.setString(11, �ߵ�Ż����.get�Էºμ�());
			pstmt.setInt(12, �ߵ�Ż����.getŸ�а�������());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from �ߵ�Ż���� where ����=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from �ߵ�Ż���� where �Էºμ�=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from �ߵ�Ż����";
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

	public List<�ߵ�Ż����> select�ߵ�Ż����(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�ߵ�Ż����> list = new ArrayList<�ߵ�Ż����>();

		String sql = "select * from �ߵ�Ż���� order by ���� desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�ߵ�Ż���� �ߵ�Ż���� = new �ߵ�Ż����();
				�ߵ�Ż����.set����(rs.getInt("����"));
				�ߵ�Ż����.set�⵵(rs.getInt("�⵵"));
				�ߵ�Ż����.set�а���(rs.getString("�а���"));
				�ߵ�Ż����.set��(rs.getInt("��"));
				�ߵ�Ż����.setT����(rs.getFloat("T����"));
				�ߵ�Ż����.set��Ÿ(rs.getInt("��Ÿ"));
				�ߵ�Ż����.set�̵��(rs.getInt("�̵��"));
				�ߵ�Ż����.set�̺���(rs.getInt("�̺���"));
				�ߵ�Ż����.set����(rs.getInt("����"));
				�ߵ�Ż����.setŸ�а�������(rs.getInt("Ÿ�а�������"));
				�ߵ�Ż����.set�����л���(rs.getInt("�����л���"));
				�ߵ�Ż����.set�ߵ�Ż����(rs.getFloat("�ߵ�Ż����"));
				�ߵ�Ż����.set��Ÿ(rs.getInt("��Ÿ"));
				�ߵ�Ż����.set�л���(rs.getInt("�л���"));
				�ߵ�Ż����.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(�ߵ�Ż����);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public float �ߵ�Ż����(int �⵵, String �а���) {
		float �ߵ�Ż���� = 0;

		String sql = "select �ߵ�Ż���� from �ߵ�Ż���� where �⵵=? and �а���=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�ߵ�Ż���� = rs.getFloat("�ߵ�Ż����");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return �ߵ�Ż����;
	}

}