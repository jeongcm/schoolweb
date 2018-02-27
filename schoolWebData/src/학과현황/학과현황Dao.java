package �а���Ȳ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class �а���ȲDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(�а���Ȳ �а���Ȳ) {

		String sql = "update �а���Ȳ set �ܰ�����=?,_5��迭=?,�й��迭1=?,�ż�����=?,���=?, �а���=?,�⵵=? where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �а���Ȳ.get�ܰ�����());
			pstmt.setString(2, �а���Ȳ.get_5��迭());
			pstmt.setString(3, �а���Ȳ.get�й��迭1());
			pstmt.setString(4, �а���Ȳ.get�ż�����());
			pstmt.setString(5, �а���Ȳ.get���());
			pstmt.setString(6, �а���Ȳ.get�а���());
			pstmt.setInt(7, �а���Ȳ.get�⵵());
			pstmt.setInt(8, �а���Ȳ.get����());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(�а���Ȳ �а���Ȳ) {

		String sql = "insert into �а���Ȳ(�⵵,�ܰ�����,�а���,_5��迭,�й��迭1,�ż�����,���,�Էºμ�) values(?,?,?,?,?,?,?,?);";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �а���Ȳ.get�⵵());
			pstmt.setString(2, �а���Ȳ.get�ܰ�����());
			pstmt.setString(3, �а���Ȳ.get�а���());
			pstmt.setString(4, �а���Ȳ.get_5��迭());
			pstmt.setString(5, �а���Ȳ.get�й��迭1());
			pstmt.setString(6, �а���Ȳ.get�ż�����());
			pstmt.setString(7, �а���Ȳ.get���());
			pstmt.setString(8, �а���Ȳ.get�Էºμ�());
			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {

		String sql = "delete from �а���Ȳ where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from �а���Ȳ where �Էºμ�=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from �а���Ȳ";
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

	public List<�а���Ȳ> select�а���Ȳ(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�а���Ȳ> list = new ArrayList<�а���Ȳ>();
		String sql = "select * from �а���Ȳ order by ���� desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�а���Ȳ �а���Ȳ = new �а���Ȳ();
				�а���Ȳ.set����(rs.getInt("����"));
				�а���Ȳ.set�⵵(rs.getInt("�⵵"));
				�а���Ȳ.set�ܰ�����(rs.getString("�ܰ�����"));
				�а���Ȳ.set�а���(rs.getString("�а���"));
				�а���Ȳ.set_5��迭(rs.getString("_5��迭"));
				�а���Ȳ.set�й��迭1(rs.getString("�й��迭1"));
				�а���Ȳ.set�ż�����(rs.getString("�ż�����"));
				�а���Ȳ.set���(rs.getString("���"));
				�а���Ȳ.set�Էºμ�(rs.getString("�Էºμ�"));
				list.add(�а���Ȳ);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void �����а���update(int �⵵, String �а���) {

		PreparedStatement pstmt2, pstmt3, pstmt4, pstmt5;

		String sql = "insert into �ܱ����л�����(�⵵,�а���) values(?,?);";
		String sql2 = "insert into �����������(�⵵,�а���) values(?,?);";
		String sql3 = "insert into �������α׷�����(�⵵,�а���) values(?,?);";
		String sql4 = "insert into �����������(�⵵,�а���) values(?,?);";
		String sql5 = "insert into ���Ƹ���������(�⵵,�а���) values(?,?);";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt4 = conn.prepareStatement(sql4);
			pstmt5 = conn.prepareStatement(sql5);

			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);

			pstmt2.setInt(1, �⵵);
			pstmt2.setString(2, �а���);

			pstmt3.setInt(1, �⵵);
			pstmt3.setString(2, �а���);

			pstmt4.setInt(1, �⵵);
			pstmt4.setString(2, �а���);

			pstmt5.setInt(1, �⵵);
			pstmt5.setString(2, �а���);

			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			pstmt3.executeUpdate();
			pstmt4.executeUpdate();
			pstmt5.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}