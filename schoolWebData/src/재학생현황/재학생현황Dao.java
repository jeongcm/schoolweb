package ���л���Ȳ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class ���л���ȲDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(���л���Ȳ ���л���Ȳ) {

		String sql = "update ���л���Ȳ set �⵵=?,�а���=?,����=? ,��������=? ,Ÿ�а�������=?,������=?, ������=? ,�л�����=?,��=?,��ü���л������=?,���������л������=?,���л������=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ���л���Ȳ.get�⵵());
			pstmt.setString(2, ���л���Ȳ.get�а���());
			pstmt.setString(3, "04.01");
			pstmt.setInt(4, ���л���Ȳ.get��������());
			pstmt.setInt(5, ���л���Ȳ.getŸ�а�������());
			pstmt.setInt(6, ���л���Ȳ.get������());
			pstmt.setInt(7, ���л���Ȳ.get������());
			pstmt.setInt(8, ���л���Ȳ.get�л�����());
			pstmt.setInt(9, ���л���Ȳ.get��());
			pstmt.setFloat(10, ���л���Ȳ.get��ü���л������());
			pstmt.setFloat(11, ���л���Ȳ.get���������л������());
			pstmt.setFloat(12, ���л���Ȳ.get���л������());
			pstmt.setInt(13, ���л���Ȳ.get����());
			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(���л���Ȳ ���л���Ȳ) {
		String sql = "insert into ���л���Ȳ(�⵵,����,�а���,�л�����,��������,������,������,��,���������л������,��ü���л������,���л������,�Էºμ�,Ÿ�а�������) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ���л���Ȳ.get�⵵());
			pstmt.setString(2, "04.01");
			pstmt.setString(3, ���л���Ȳ.get�а���());
			pstmt.setInt(4, ���л���Ȳ.get�л�����());
			pstmt.setInt(5, ���л���Ȳ.get��������());
			pstmt.setInt(6, ���л���Ȳ.get������());
			pstmt.setInt(7, ���л���Ȳ.get������());
			pstmt.setInt(8, ���л���Ȳ.get��());
			pstmt.setFloat(9, ���л���Ȳ.get���������л������());
			pstmt.setFloat(10, ���л���Ȳ.get��ü���л������());
			pstmt.setFloat(11, ���л���Ȳ.get���л������());
			pstmt.setString(12, ���л���Ȳ.get�Էºμ�());
			pstmt.setInt(13, ���л���Ȳ.getŸ�а�������());

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
		String sql = "delete from ���л���Ȳ  where ���� = '04.01' and �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from ���л���Ȳ where ���� = '04.01'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// ����� ����, ����
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<���л���Ȳ> select���л���Ȳ(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���л���Ȳ> list = new ArrayList<���л���Ȳ>();

		String sql = "select * from ���л���Ȳ where ���� = '04.01' order by ���� desc limit ?,?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���л���Ȳ ���л���Ȳ = new ���л���Ȳ();
				���л���Ȳ.set�⵵(rs.getInt("�⵵"));
				���л���Ȳ.set����(rs.getInt("����"));
				���л���Ȳ.set�а���(rs.getString("�а���"));
				���л���Ȳ.set�Էºμ�(rs.getString("�Էºμ�"));
				���л���Ȳ.set�л�����(rs.getInt("�л�����"));
				���л���Ȳ.set��������(rs.getInt("��������"));
				���л���Ȳ.set������(rs.getInt("������"));
				���л���Ȳ.set������(rs.getInt("������"));
				���л���Ȳ.setŸ�а�������(rs.getInt("Ÿ�а�������"));
				���л���Ȳ.set��(rs.getInt("��"));
				���л���Ȳ.set���л������(rs.getFloat("���л������"));
				���л���Ȳ.set��ü���л������(rs.getFloat("��ü���л������"));
				���л���Ȳ.set���������л������(rs.getFloat("���������л������"));
				���л���Ȳ.setT����(rs.getFloat("T����"));

				list.add(���л���Ȳ);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public float ���л������(int �⵵, String �а���) {
		float ���л������ = 0;

		String sql = "select ���л������ from ���л���Ȳ where �⵵=? and �а���=? and ����=04.01;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���л������ = rs.getFloat("���л������");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ���л������;
	}

	public float avg(int �⵵) {
		float ��� = 0;
		String sql = "select avg(���л������) ��� from ���л���Ȳ  where ����='04.01' and �⵵=?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, �⵵);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				��� = rs.getFloat("���");

			}

			System.out.println("���"+���);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ���;

	}

	public float std(int �⵵) { //���л���Ȳ�� ������ 04.01�� ���� ���,ǥ�������� ���ϹǷ� ���� �����س���
		float ǥ������ = 0;
		String sql = "select stddev_samp(���л������) ǥ������ from ���л���Ȳ  where ����='04.01' and �⵵=?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, �⵵);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ǥ������ = rs.getFloat("ǥ������");

			}
			System.out.println("ǥ������"+ǥ������);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ǥ������;

	}
}