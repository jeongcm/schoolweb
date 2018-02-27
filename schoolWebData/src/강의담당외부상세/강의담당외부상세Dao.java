package ���Ǵ��ܺλ�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import ���Ǵ��ܺλ�.���Ǵ��ܺλ�;

public class ���Ǵ��ܺλ�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(���Ǵ��ܺλ� ���Ǵ��ܺ�) {
		String sql = "update �������Ǵ��ܺκ��� set ���и�=?,�а���=?,�б�=?,����=? ,����=?,����=?,����=?,���=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ���Ǵ��ܺ�.get���и�());
			pstmt.setString(2, ���Ǵ��ܺ�.get�а���());
			pstmt.setString(3, ���Ǵ��ܺ�.get�б�());
			pstmt.setString(4, ���Ǵ��ܺ�.get����());
			pstmt.setString(5, ���Ǵ��ܺ�.get����());
			pstmt.setString(6, ���Ǵ��ܺ�.get����());
			pstmt.setFloat(7, ���Ǵ��ܺ�.get����());
			pstmt.setString(8, ���Ǵ��ܺ�.get���());
			pstmt.setInt(9, ���Ǵ��ܺ�.get����());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(���Ǵ��ܺλ� ���Ǵ��ܺ�) {
		String sql = "insert into �������Ǵ��ܺκ���(���и�,�а���,�б�,����,����,����,����,���,�Էºμ�) values(?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ���Ǵ��ܺ�.get���и�());
			pstmt.setString(2, ���Ǵ��ܺ�.get�а���());
			pstmt.setString(3, ���Ǵ��ܺ�.get�б�());
			pstmt.setString(4, ���Ǵ��ܺ�.get����());
			pstmt.setString(5, ���Ǵ��ܺ�.get����());
			pstmt.setString(6, ���Ǵ��ܺ�.get����());
			pstmt.setFloat(7, ���Ǵ��ܺ�.get����());
			pstmt.setString(8, ���Ǵ��ܺ�.get���());
			pstmt.setString(9, ���Ǵ��ܺ�.get�Էºμ�());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from �������Ǵ��ܺκ��� where ����=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from �������Ǵ��ܺκ��� where �Էºμ� =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from �������Ǵ��ܺκ���";
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

	public List<���Ǵ��ܺλ�> select���Ǵ��ܺ�(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���Ǵ��ܺλ�> list = new ArrayList<���Ǵ��ܺλ�>();

		String sql = "select * from �������Ǵ��ܺκ��� order by ���� desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				���Ǵ��ܺλ� ���Ǵ��ܺ� = new ���Ǵ��ܺλ�();
				���Ǵ��ܺ�.set����(rs.getInt("����"));
				���Ǵ��ܺ�.set���и�(rs.getString("���и�"));
				���Ǵ��ܺ�.set�а���(rs.getString("�а���"));
				���Ǵ��ܺ�.set����(rs.getString("����"));
				���Ǵ��ܺ�.set����(rs.getString("����"));
				���Ǵ��ܺ�.set���(rs.getString("���"));
				���Ǵ��ܺ�.set����(rs.getString("����"));
				���Ǵ��ܺ�.set�б�(rs.getString("�б�"));
				���Ǵ��ܺ�.set����(rs.getFloat("����"));
				���Ǵ��ܺ�.set�Էºμ�(rs.getString("�Էºμ�"));
				list.add(���Ǵ��ܺ�);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public float ���Ǵ��ܺ�(String ���и�) {
		float ���Ǵ��ܺ� = 0;

		String sql = "select ���Ǵ��ܺκ��� from �������Ǵ��ܺκ��� where ���и�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ���и�);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���Ǵ��ܺ� = rs.getFloat("���Ǵ��ܺκ���");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ���Ǵ��ܺ�;
	}

	public float �����Ѱ�(String ���и�) {
		float �����Ѱ� = 0;
		String sql = "select sum(����) �Ѱ� from �������Ǵ��ܺκ��� where ���и�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ���и�);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�����Ѱ� = rs.getFloat("�Ѱ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return �����Ѱ�;
	}

	public float �����ʼ�����(String ���и�) {
		float �����ʼ����� = 0;
		String sql = "select sum(����) �����ʼ� from �������Ǵ��ܺκ��� where ���и�=? and ����='���� �ʼ�';";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ���и�);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�����ʼ����� = rs.getFloat("�����ʼ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return �����ʼ�����;

	}

	public float ��������(String ���и�) {
		float �������� = 0;
		String sql = "select ������������ from �������Ǵ��ܺκ��� where ���и�=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ���и�);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�������� = rs.getFloat("������������");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ��������;
	}

	public float ��������(String ���и�) {
		float �������� = 0;
		String sql = "select �����������ð��� from �������Ǵ��ܺκ��� where ���и�=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ���и�);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�������� = rs.getFloat("�����������ð���");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ��������;
	}

	public void update���Ǵ��ܺ�(float ���Ǵ��ܺ�, String ���и�) {
		String sql = "update �������Ǵ��ܺκ��� set ���Ǵ�����=? where ���и�=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, ���Ǵ��ܺ�);
			pstmt.setString(2, ���и�);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}