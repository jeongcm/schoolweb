package �����Ȳ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class �����ȲDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(�����Ȳ �����Ȳ) {
		String sql = "update �����Ȳ set �⵵=?,�а���=?,������=?,2�����������=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �����Ȳ.get�⵵());
			pstmt.setString(2, �����Ȳ.get�а���());
			pstmt.setInt(3, �����Ȳ.get������());
			pstmt.setFloat(4, �����Ȳ.get�������������());
			pstmt.setInt(5, �����Ȳ.get����());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(�����Ȳ �����Ȳ) {
		String sql = "insert into �����Ȳ(�⵵,�а���,������,2�����������,�Էºμ�) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �����Ȳ.get�⵵());
			pstmt.setString(2, �����Ȳ.get�а���());
			pstmt.setInt(3, �����Ȳ.get������());
			pstmt.setFloat(4, �����Ȳ.get�������������());
			pstmt.setString(5, �����Ȳ.get�Էºμ�());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int ����) {
		String sql = "delete from �����Ȳ where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from �����Ȳ where �Էºμ� =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from �����Ȳ";
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

	public List<�����Ȳ> select�����Ȳ(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�����Ȳ> list = new ArrayList<�����Ȳ>();

		String sql = "select �⵵,�а���,2�����������,������,T����,����,�Էºμ�,round(�����,2) ����� from �����Ȳ order by ���� desc limit ?,? ;"; //ȭ�鿡 �ݿø��ؼ� �����ֱ����� �̷��� ��
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�����Ȳ �����Ȳ = new �����Ȳ();
				�����Ȳ.set�⵵(rs.getInt("�⵵"));
				�����Ȳ.set�а���(rs.getString("�а���"));
				�����Ȳ.set�������������(rs.getFloat("2�����������"));
				�����Ȳ.set������(rs.getInt("������"));
				�����Ȳ.setT����(rs.getFloat("T����"));
				�����Ȳ.set�����(rs.getFloat("�����"));
				�����Ȳ.set����(rs.getInt("����"));
				�����Ȳ.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(�����Ȳ);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean �����update(float �����, String �а���, int �⵵) {
		String sql = "update �����Ȳ set �����=? where �⵵=? and �а���=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, �����);
			pstmt.setInt(2, �⵵);
			pstmt.setString(3, �а���);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public int �����(String �а���, int �⵵) {
		String sql = "select �����.��-���д�ñ������ �� from ����� join ��������� on �����.�а���=���������.�а���  where �����.�а���=? and �����.�⵵=?;";
		int ����� = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �а���);
			pstmt.setInt(2, �⵵);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				����� = rs.getInt("��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return �����;
	}

	public int ���������(String �а���, int �⵵) {
		String sql = "select ���������.��-���д�ñ������ �� from �����Ȳ join ��������� on �����Ȳ.�а���=���������.�а���  where �����Ȳ.�а���=? and �����Ȳ.�⵵=?;";
		int ��������� = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �а���);
			pstmt.setInt(2, �⵵);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				��������� = rs.getInt("��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ���������;
	}

	public float �������������(String �а���, int �⵵) {
		String sql = " select 2�����������  from �����Ȳ  where �а���=? and �⵵=?;";
		float ������������� = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �а���);
			pstmt.setInt(2, �⵵);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				������������� = rs.getFloat("2�����������");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return �������������;
	}

	public float �����(int �⵵, String �а���) {
		String sql = "select ����� from �����Ȳ  where �⵵=? and �а���=?; ";
		float ����� = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				����� = rs.getFloat("�����");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return �����;
	}


}