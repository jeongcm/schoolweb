package �ܱ����л�����;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import �ܱ����л�����.�ܱ����л�����;

public class �ܱ����л�����Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;

	private ResultSet rs;

	public boolean update(�ܱ����л����� �ܱ����л�����) {
		String sql = "update �ܱ����л���Ȳ set �⵵=?,�а���=?,�г�=?,�й�=?,����=?,����=?,����=?,���=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, �ܱ����л�����.get�⵵());
			pstmt.setString(2, �ܱ����л�����.get�а���());
			pstmt.setInt(3, �ܱ����л�����.get�г�());
			pstmt.setInt(4, �ܱ����л�����.get�й�());

			pstmt.setString(5, �ܱ����л�����.get����());
			pstmt.setString(6, �ܱ����л�����.get����());
			pstmt.setString(7, �ܱ����л�����.get����());
			pstmt.setString(8, �ܱ����л�����.get���());
			pstmt.setInt(9, �ܱ����л�����.get����());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(�ܱ����л����� �ܱ����л�����) {
		String sql = "insert into �ܱ����л���Ȳ(�⵵,�а���,�г�,�й�,����,����,����,���,�Էºμ�) values(?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, �ܱ����л�����.get�⵵());
			pstmt.setString(2, �ܱ����л�����.get�а���());
			pstmt.setInt(3, �ܱ����л�����.get�г�());
			pstmt.setInt(4, �ܱ����л�����.get�й�());
			pstmt.setString(5, �ܱ����л�����.get����());
			pstmt.setString(6, �ܱ����л�����.get����());
			pstmt.setString(7, �ܱ����л�����.get����());
			pstmt.setString(8, �ܱ����л�����.get���());
			pstmt.setString(9, �ܱ����л�����.get�Էºμ�());

			pstmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from �ܱ����л���Ȳ where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from �ܱ����л���Ȳ where �Էºμ�=?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from �ܱ����л���Ȳ";
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

	public List<�ܱ����л�����> select�ܱ����л�����(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�ܱ����л�����> list = new ArrayList<�ܱ����л�����>();

		String sql = "select * from �ܱ����л���Ȳ order by ���� desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				�ܱ����л����� �ܱ����л����� = new �ܱ����л�����();
				�ܱ����л�����.set�⵵(rs.getInt("�⵵"));
				�ܱ����л�����.set�а���(rs.getString("�а���"));
				�ܱ����л�����.set�г�(rs.getInt("�г�"));
				�ܱ����л�����.set�й�(rs.getInt("�й�"));
				�ܱ����л�����.set����(rs.getString("����"));
				�ܱ����л�����.set����(rs.getString("����"));
				�ܱ����л�����.set����(rs.getString("����"));
				�ܱ����л�����.set���(rs.getString("���"));
				�ܱ����л�����.set����(rs.getInt("����"));
				�ܱ����л�����.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(�ܱ����л�����);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int �ܱ����л���(int �⵵, String �а���) {
		int �л��� = 0;
		String sql = "select count(*) �л��� from �ܱ����л���Ȳ where �а���=? and �⵵=?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �а���);
			pstmt.setInt(2, �⵵);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				�л��� = rs.getInt("�л���");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return �л���;
	}


	public void update�ܱ����л�����(int �����⵵, String �����а���, float �ܱ����л�����) {
		String sql = "update �ܱ����л����� set �ܱ����л�����=? where �а���=? and �⵵=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, �ܱ����л�����);
			pstmt.setString(2, �����а���);
			pstmt.setInt(3, �����⵵);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}