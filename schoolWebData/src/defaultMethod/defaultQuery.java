package defaultMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dbConnection.dbConnection;

public class defaultQuery {
	private static Connection conn = dbConnection.getConnection();
	private static PreparedStatement pstmt = null;

	private static ResultSet rs;
	static Log log = new Log();

	public static String �а���üũ(String �а���) {
		String sql = "select exists(select * from �а���Ȳ where �а���=? ) result;";
		String result = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �а���);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String �й��迭(int �⵵, String �а���) { // �������ǥ���� ���
		String �й��迭 = null;

		String sql = "select �й��迭1 from �а���Ȳ where �⵵=? and �а���=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�й��迭 = rs.getString("�й��迭1");
			}
			System.out.println("�а��� :"+�а���+" �й��迭 :"+�й��迭);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return �й��迭;
	}

	public static String �⵵üũ(int �⵵) {
		String sql = "select exists(select * from �а���Ȳ where �⵵=? ) result;";
		String result = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static float avg(int �⵵, String column, String table) {
		float ��� = 0;
		String sql = "select avg(" + column + ") ��� from " + table + " where �⵵=" + �⵵ + ";";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			log.debug("�Ϲ� ��� ����" + pstmt);

			while (rs.next()) {
				��� = rs.getFloat("���");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.debug("���: " + ���);
		System.out.println("���: " + ���);
		return ���;

	}

	public static float �ܺ�avg(String column, String table) {
		float ��� = 0;
		String sql = "select avg(" + column + ") ��� from " + table + ";";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			log.debug("�ܺ� ��� ����" + pstmt);

			while (rs.next()) {
				��� = rs.getFloat("���");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ���;

	}

	public static float �й��迭std(int �⵵, String column, String table, String �й��迭) { // ǥ�������� ���ϴ� �Լ� stddev_samp�� ������ ǥ�������� ǥ�������� ���ϴ� stddev�� ��ġ
		float ǥ������ = 0;
		String sql = "select stddev_samp(" + column + ") ǥ������ from " + table + " join " + " �а���Ȳ on " + table
				+ ".�а���=�а���Ȳ.�а��� where �а���Ȳ.�⵵=" + �⵵ + " and �й��迭1= '" + �й��迭 + "';";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			log.debug("�й� �迭 ǥ������ ����" + pstmt);

			while (rs.next()) {

				ǥ������ = rs.getFloat("ǥ������");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ǥ������;
	}

	public static float �й��迭avg(int �⵵, String column, String table, String �й��迭) {
		float ��� = 0;
		String sql = "select avg(" + column + ") ��� from " + table + " join " + " �а���Ȳ on " + table
				+ ".�а���=�а���Ȳ.�а��� where �а���Ȳ.�⵵=" + �⵵ + " and �й��迭1= '" + �й��迭 + "';";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			log.debug("�й��迭 ��� ����" + pstmt);

			while (rs.next()) {
				��� = rs.getFloat("���");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ���;

	}


	public static float std(int �⵵, String column, String table) {
		float ǥ������ = 0;
		String sql = "select stddev_samp(" + column + ") ǥ������ from " + table + " where �⵵=" + �⵵ + ";";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			log.debug("�Ϲ� ǥ������ ����" + pstmt);

			while (rs.next()) {

				ǥ������ = rs.getFloat("ǥ������");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.debug("ǥ������ : " + ǥ������);
		System.out.println("ǥ������"+ǥ������);
		return ǥ������;
	}

	public static float �ܺ�std(String column, String table) {
		float ǥ������ = 0;
		String sql = "select stddev_samp(" + column + ") ǥ������ from " + table + ";";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			log.debug("�ܺ� ǥ������ ����" + pstmt);

			while (rs.next()) {

				ǥ������ = rs.getFloat("ǥ������");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ǥ������;
	}

	public static ArrayList<String> getMajor(int �⵵, String �ܰ�����) {

		ArrayList<String> �а��� = new ArrayList<String>();

		String sql = "select �а��� from �а���Ȳ where �⵵=? and �ܰ�����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �ܰ�����);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				�а���.add(rs.getString("�а���"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return �а���;

	}

	public static void updateT(float T����, int �⵵, String �а���, String table) {

		String sql = "update " + table + " set T����=? where �⵵=? and �а���=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, T����);
			pstmt.setInt(2, �⵵);
			pstmt.setString(3, �а���);

			pstmt.executeUpdate();

			log.debug("T���� ���� ����" + pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void �ܺ�updateT(float T����, String ���и�, String table) {

		String sql = "update " + table + " set T����=? where ���и�=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, T����);
			pstmt.setString(2, ���и�);

			pstmt.executeUpdate();

			log.debug("�ܺ� T���� ���� ����" + pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> �а����(int �⵵, String table) {

		ArrayList<String> �а��� = new ArrayList<String>();

		String sql = "select �а��� from " + table + " where �⵵=" + �⵵ + ";";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				�а���.add(rs.getString("�а���"));
			}
			return �а���;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<String> �ܺδ��и��(String table) {

		ArrayList<String> ���и� = new ArrayList<String>();

		String sql = "select ���и� from " + table + ";";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				���и�.add(rs.getString("���и�"));
			}
			return ���и�;

		} catch (SQLException e) {
			return null;
		}
	}

	public static float ����(int �⵵, String �а���, String column, String table) {
		float ���� = 0;
		
		System.out.println(�а���);
		
		String sql = "select " + column + " from " + table + " where �⵵=? and �а���=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();

			log.debug("�Ϲ� ���� ����" + pstmt);

			while (rs.next()) {
				���� = rs.getFloat(column);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.debug("������ " + ����);
		return ����;
	}

	public static float �ܺκ���(String ���и�, String column, String table) {
		float ���� = 0;

		String sql = "select " + column + " from " + table + " where ���и�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ���и�);
			rs = pstmt.executeQuery();

			log.debug("�ܺ� ���� ����" + pstmt);

			while (rs.next()) {
				���� = rs.getFloat(column);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ����;
	}

	public static int ���ӱ�����(int �⵵, String �а���) {
		String sql = "select ���б� ���ӱ����� from ������Ȳ where �⵵=? and �а���=?";
		int ���ӱ����� = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				���ӱ����� = rs.getInt("���ӱ�����");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ���ӱ�����;
	}

	public static int ���л���(int �⵵, String �а���) {
		int ���л��� = 0;
		String sql = "select �� from ���л���Ȳ  where �а���=? and ����=4.01 and �⵵=?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �а���);
			pstmt.setInt(2, �⵵);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				���л��� = rs.getInt("��");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ���л���;
	}

}
