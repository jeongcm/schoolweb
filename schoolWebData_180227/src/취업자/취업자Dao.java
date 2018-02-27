package �����;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import �����.�����;

public class �����Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(����� �����) {
		String sql = "update ����� set �⵵=?,�а���=?,�ǰ�����db���������=?,�ؿ������=?,����������=?,���������=?,��=?,����â��Ȱ�����缭=?,1��â����=?,��������=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, �����.get�⵵());
			pstmt.setString(2, �����.get�а���());
			pstmt.setInt(3, �����.get�ǰ�����DB���������());
			pstmt.setInt(4, �����.get�ؿ������());
			pstmt.setInt(5, �����.get����������());
			pstmt.setInt(6, �����.get���������());
			pstmt.setInt(7, �����.get��());
			pstmt.setInt(8, �����.get����â��Ȱ�����缭());
			pstmt.setInt(9, �����.get����â����());
			pstmt.setInt(10, �����.get��������());
			pstmt.setInt(11, �����.get����());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(����� �����) {
		String sql = "insert into �����(�⵵,�а���,�ǰ�����db���������,�ؿ������,����������,���������,��,�Էºμ�,����â��Ȱ�����缭,1��â����,��������) values(?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �����.get�⵵());
			pstmt.setString(2, �����.get�а���());
			pstmt.setInt(3, �����.get�ǰ�����DB���������());
			pstmt.setInt(4, �����.get�ؿ������());
			pstmt.setInt(5, �����.get����������());
			pstmt.setInt(6, �����.get���������());
			pstmt.setInt(7, �����.get��());
			pstmt.setString(8, �����.get�Էºμ�());
			pstmt.setInt(9, �����.get����â��Ȱ�����缭());
			pstmt.setInt(10, �����.get����â����());
			pstmt.setInt(11, �����.get��������());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from ����� where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from ����� where �Էºμ� =?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from �����";
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

	public List<�����> select�����(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�����> list = new ArrayList<�����>();

		String sql = "select * from ����� order by ���� desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				����� ����� = new �����();
				�����.set�⵵(rs.getInt("�⵵"));
				�����.set�а���(rs.getString("�а���"));
				�����.set��(rs.getInt("��"));
				�����.set�ǰ�����DB���������(rs.getInt("�ǰ�����DB���������"));
				�����.set�ؿ������(rs.getInt("�ؿ������"));
				�����.set����������(rs.getInt("����������"));
				�����.set���������(rs.getInt("���������"));
				�����.set����(rs.getInt("����"));
				�����.set�Էºμ�(rs.getString("�Էºμ�"));
				�����.set����â��Ȱ�����缭(rs.getInt("����â��Ȱ�����缭"));
				�����.set����â����(rs.getInt("1��â����"));
				�����.set��������(rs.getInt("��������"));
				
				list.add(�����);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
