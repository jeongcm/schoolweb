package ���ӱ���Ȯ����;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class ���ӱ���Ȯ����Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(���ӱ���Ȯ���� ���ӱ���Ȯ����) {

		String sql = "update ���ӱ���Ȯ���� set �⵵=?,�а���=?,���п�������=?,���п����л�=?,�л������������ӱ���=?,���л��������ӱ���=?,���ӱ���Ȯ����=?,�����л�����=?,����_�к�_����=?,����_�к�_���л�=?,����_���п�_����=?,����_���п�_���л�=?,����_��_����=?,����_��_���л�=?,�л���_����=?,�л���_���л�=?,Ȯ����_����=?,Ȯ����_���л�=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ���ӱ���Ȯ����.get�⵵());
			pstmt.setString(2, ���ӱ���Ȯ����.get�а���());
			pstmt.setInt(3, ���ӱ���Ȯ����.get���п�������());
			pstmt.setInt(4, ���ӱ���Ȯ����.get���п����л�());
			pstmt.setInt(5, ���ӱ���Ȯ����.get�л������������ӱ���());
			pstmt.setInt(6, ���ӱ���Ȯ����.get���л��������ӱ���());
			pstmt.setFloat(7, ���ӱ���Ȯ����.get���ӱ���Ȯ����());
			pstmt.setInt(8, ���ӱ���Ȯ����.get�����л�����());
			pstmt.setInt(9, ���ӱ���Ȯ����.get����_�к�_����());
			pstmt.setInt(10, ���ӱ���Ȯ����.get����_�к�_���л�());
			pstmt.setInt(11, ���ӱ���Ȯ����.get����_���п�_����());
			pstmt.setInt(12, ���ӱ���Ȯ����.get����_���п�_���л�());
			pstmt.setInt(13, ���ӱ���Ȯ����.get����_��_����());
			pstmt.setInt(14, ���ӱ���Ȯ����.get����_��_���л�());
			pstmt.setFloat(15, ���ӱ���Ȯ����.get�л���_����());
			pstmt.setFloat(16, ���ӱ���Ȯ����.get�л���_���л�());
			pstmt.setFloat(17, ���ӱ���Ȯ����.getȮ����_����());
			pstmt.setFloat(18, ���ӱ���Ȯ����.getȮ����_���л�());
			pstmt.setFloat(19, ���ӱ���Ȯ����.get����());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(���ӱ���Ȯ���� ���ӱ���Ȯ����) {
		String sql = "insert into ���ӱ���Ȯ����(�⵵,�а���,���п�������,���п����л�,�л������������ӱ���,���л��������ӱ���,���ӱ���Ȯ����,�����л�����,����_�к�_����,����_�к�_���л�,����_���п�_����,����_���п�_���л�,����_��_����,����_��_���л�,�л���_����,�л���_���л�,Ȯ����_����,Ȯ����_���л�,�Էºμ�) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ���ӱ���Ȯ����.get�⵵());
			pstmt.setString(2, ���ӱ���Ȯ����.get�а���());
			pstmt.setInt(3, ���ӱ���Ȯ����.get���п�������());
			pstmt.setInt(4, ���ӱ���Ȯ����.get���п����л�());
			pstmt.setInt(5, ���ӱ���Ȯ����.get�л������������ӱ���());
			pstmt.setInt(6, ���ӱ���Ȯ����.get���л��������ӱ���());
			pstmt.setFloat(7, ���ӱ���Ȯ����.get���ӱ���Ȯ����());
			pstmt.setInt(8, ���ӱ���Ȯ����.get�����л�����());
			pstmt.setInt(9, ���ӱ���Ȯ����.get����_�к�_����());
			pstmt.setInt(10, ���ӱ���Ȯ����.get����_�к�_���л�());
			pstmt.setInt(11, ���ӱ���Ȯ����.get����_���п�_����());
			pstmt.setInt(12, ���ӱ���Ȯ����.get����_���п�_���л�());
			pstmt.setInt(13, ���ӱ���Ȯ����.get����_��_����());
			pstmt.setInt(14, ���ӱ���Ȯ����.get����_��_���л�());
			pstmt.setFloat(15, ���ӱ���Ȯ����.get�л���_����());
			pstmt.setFloat(16, ���ӱ���Ȯ����.get�л���_���л�());
			pstmt.setFloat(17, ���ӱ���Ȯ����.getȮ����_����());
			pstmt.setFloat(18, ���ӱ���Ȯ����.getȮ����_���л�());
			pstmt.setString(19, ���ӱ���Ȯ����.get�Էºμ�());

			System.out.println(pstmt);

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public void delete(int ����) {
		String sql = "delete from ���ӱ���Ȯ���� where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from ���ӱ���Ȯ���� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from ���ӱ���Ȯ����";
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

	public List<���ӱ���Ȯ����> select���ӱ���Ȯ����(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���ӱ���Ȯ����> list = new ArrayList<���ӱ���Ȯ����>();

		String sql = "select ���ӱ���Ȯ����.* from ���ӱ���Ȯ���� order by ���� desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���ӱ���Ȯ���� ���ӱ���Ȯ���� = new ���ӱ���Ȯ����();
				���ӱ���Ȯ����.set�⵵(rs.getInt("�⵵"));
				���ӱ���Ȯ����.set����(rs.getInt("����"));
				���ӱ���Ȯ����.set�а���(rs.getString("�а���"));
				���ӱ���Ȯ����.set���п�������(rs.getInt("���п�������"));
				���ӱ���Ȯ����.set���п����л�(rs.getInt("���п����л�"));
				���ӱ���Ȯ����.set���л��������ӱ���(rs.getInt("���л��������ӱ���"));
				���ӱ���Ȯ����.set�л������������ӱ���(rs.getInt("�л������������ӱ���"));
				���ӱ���Ȯ����.set�����л�����(rs.getInt("�����л�����"));
				���ӱ���Ȯ����.set����_�к�_����(rs.getInt("����_�к�_����"));
				���ӱ���Ȯ����.set����_�к�_���л�(rs.getInt("����_�к�_���л�"));
				���ӱ���Ȯ����.set����_���п�_����(rs.getInt("����_���п�_����"));
				���ӱ���Ȯ����.set����_���п�_���л�(rs.getInt("����_���п�_���л�"));
				���ӱ���Ȯ����.set����_��_����(rs.getInt("����_��_����"));
				���ӱ���Ȯ����.set����_��_���л�(rs.getInt("����_��_���л�"));
				���ӱ���Ȯ����.set�л���_����(rs.getFloat("�л���_����"));
				���ӱ���Ȯ����.set�л���_���л�(rs.getFloat("�л���_���л�"));
				���ӱ���Ȯ����.set�Էºμ�(rs.getString("�Էºμ�"));
				list.add(���ӱ���Ȯ����);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public float ���ӱ���Ȯ����(int �⵵, String �а���) {
		float ���ӱ���Ȯ���� = 0;

		String sql = "select ���ӱ���Ȯ���� from ���ӱ���Ȯ���� where �⵵=? and �а���=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���ӱ���Ȯ���� = rs.getFloat("���ӱ���Ȯ����");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ���ӱ���Ȯ����;
	}

	public int �л�����(int �⵵, String �а���) {
		int �л����� = 0;
		String sql = "select �л����� from ���л���Ȳ where ����=04.01 and �⵵=? and �а���=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�л����� = rs.getInt("�л�����");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return �л�����;
	}

	public int ��������(int �⵵, String �а���) {
		String sql = "select �ż����� from �а���Ȳ where  �⵵=? and �а���=?;";
		String �ż����� = null;
		int �������� = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�ż����� = rs.getString("�ż�����");
			}
			if (�ż����� != null) {
				sql = "select �������� from ���л���Ȳ where  �⵵=? and �а���=? and ����='04.01';";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, �⵵);
					pstmt.setString(2, �а���);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						�������� = rs.getInt("��������");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				return 0;
			}
			return ��������;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int ���л�(int �⵵, String �а���) {
		String sql = "select �� from ���л���Ȳ where ����=04.01 and �⵵=? and �а���=?;";
		int �� = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				�� = rs.getInt("��");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ��;
	}

	public String _5��迭(int �⵵, String �а���) {
		String _5��迭 = null;

		String sql = "select _5��迭 from �а���Ȳ where �⵵=? and �а���=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				_5��迭 = rs.getString("_5��迭");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _5��迭;
	}
}
