package �������;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import �������.�������;

public class �������Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(������� �������) {
		String sql = "update ������� set �⵵=?,�б�=?,�а���=?,�й�=?,����=?,�̼�����=?,���=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �������.get�⵵());
			pstmt.setString(2, �������.get�б�());
			pstmt.setString(3, �������.get�а���());
			pstmt.setInt(4, �������.get�й�());
			pstmt.setString(5, �������.get����());
			pstmt.setInt(6, �������.get�̼�����());
			pstmt.setString(7, �������.get���());
			pstmt.setInt(8, �������.get����());

			pstmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(������� �������) {
		String sql = "insert into �������(�⵵,�б�,�а���,�й�,����,�̼�����,���,�Էºμ�) values(?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �������.get�⵵());
			pstmt.setString(2, �������.get�б�());
			pstmt.setString(3, �������.get�а���());
			pstmt.setInt(4, �������.get�й�());
			pstmt.setString(5, �������.get����());
			pstmt.setInt(6, �������.get�̼�����());
			pstmt.setString(7, �������.get���());
			pstmt.setString(8, �������.get�Էºμ�());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void dellAll(String �Էºμ�) {
		String sql = "delete from ������� where �Էºμ�=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int ����) {
		String sql = "delete from ������� where ����=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ����);
			pstmt.executeUpdate();

		} catch ( SQLException e) {
			e.printStackTrace();
			
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from �������";
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

	public List<�������> select�������(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�������> list = new ArrayList<�������>();

		String sql = "select * from ������� order by  ���� desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				������� ������� = new �������();
				�������.set�⵵(rs.getInt("�⵵"));
				�������.set�б�(rs.getString("�б�"));
				�������.set�а���(rs.getString("�а���"));
				�������.set�й�(rs.getInt("�й�"));
				�������.set����(rs.getString("����"));
				�������.set�̼�����(rs.getInt("�̼�����"));
				�������.set���(rs.getString("���"));
				�������.set����(rs.getInt("����"));
				�������.set�Էºμ�(rs.getString("�Էºμ�"));

				list.add(�������);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void �����������insert(int �����⵵, String �����а���) {
		String sql = "insert into �����������(�а���,�⵵) values(?,?);";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �����а���);
			pstmt.setInt(2, �����⵵);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int �̼�����(String �����а���, String �б�) {
		int �̼�����=0;
		String sql="select sum(if(�б�='"+�б�+"' and �а���='"+�����а���+"',�̼�����,0)) �̼����� from �������;";
		try{
				
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
	
			
			while(rs.next()){
				�̼�����=rs.getInt("�̼�����");
			}
		} catch (Exception e) {
			�̼�����=0;
			e.printStackTrace();
		}
		return �̼�����;
	}

	public int ���л�2�б�(int �����⵵, String �����а���) {
		int ���л��� = 0;
		String sql = "select �� from ���л���Ȳ  where �а���=? and ����=10.01 and �⵵=?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �����а���);
			pstmt.setInt(2, �����⵵);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				���л��� = rs.getInt("��");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ���л���;
	}

	public void update�����������(int �����⵵, String �����а���, float �̼�����) {
		String sql = "update ����������� set �̼�����=? where �а���=? and �⵵=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, �̼�����);
			pstmt.setString(2, �����а���);
			pstmt.setInt(3, �����⵵);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
