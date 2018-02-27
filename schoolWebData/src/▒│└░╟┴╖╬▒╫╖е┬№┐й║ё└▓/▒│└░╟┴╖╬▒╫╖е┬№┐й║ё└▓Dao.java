package �������α׷���������;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import �������α׷���������.�������α׷���������;

public class �������α׷���������Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;

	public boolean update(�������α׷��������� �������α׷���������) {
		String sql = "update �������α׷������ set �⵵=?,��μ���=?,���α׷���=?,�а���=?,�г�=?,�й�=?,����=?,���=? where ����=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, �������α׷���������.get�⵵());
			pstmt.setString(2, �������α׷���������.get��μ���());
			pstmt.setString(3, �������α׷���������.get���α׷���());
			pstmt.setString(4, �������α׷���������.get�а���());
			pstmt.setInt(5, �������α׷���������.get�г�());
			pstmt.setString(6, �������α׷���������.get�й�());
			pstmt.setString(7, �������α׷���������.get����());
			pstmt.setString(8, �������α׷���������.get���());
			pstmt.setInt(9, �������α׷���������.get����());
		
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {		
			e.printStackTrace();
			return false;
		}

	}
	public boolean insert(�������α׷��������� �������α׷���������){
		String sql = "insert into �������α׷������(�⵵,��μ���,���α׷���,�а���,�г�,�й�,����,���,�Էºμ�) values(?,?,?,?,?,?,?,?,?);";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, �������α׷���������.get�⵵());
			pstmt.setString(2, �������α׷���������.get��μ���());
			pstmt.setString(3, �������α׷���������.get���α׷���());
			pstmt.setString(4, �������α׷���������.get�а���());
			pstmt.setInt(5, �������α׷���������.get�г�());
			pstmt.setString(6, �������α׷���������.get�й�());
			pstmt.setString(7, �������α׷���������.get����());
			pstmt.setString(8, �������α׷���������.get���());
			pstmt.setString(9, �������α׷���������.get�Էºμ�());
		
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
        }
	}
	public void delete(int ����) {
        String sql = "delete from �������α׷������ where ����=?;";
        try {     
            pstmt = conn.prepareStatement(sql);     
            pstmt.setInt(1, ����);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } 
    }
	 public void dellAll(String �Էºμ�) {
			String sql = "delete from �������α׷������ where �Էºμ� =?;";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, �Էºμ�);
				pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	 public int getCountRow() {
		String sql = "select count(*) from �������α׷������";
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

	public List<�������α׷���������> select�������α׷���������(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�������α׷���������> list = new ArrayList<�������α׷���������>();

		String sql="select * from �������α׷������ order by ���� desc limit ?,?;";
		
		try {
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				�������α׷��������� �������α׷��������� = new �������α׷���������();
				�������α׷���������.set�⵵(rs.getInt("�⵵"));
				�������α׷���������.set��μ���(rs.getString("��μ���"));
				�������α׷���������.set���α׷���(rs.getString("���α׷���"));
				�������α׷���������.set�а���(rs.getString("�а���"));
				�������α׷���������.set�г�(rs.getInt("�г�"));
				�������α׷���������.set�й�(rs.getString("�й�"));
				�������α׷���������.set����(rs.getString("����"));
				�������α׷���������.set���(rs.getString("���"));
				�������α׷���������.set����(rs.getInt("����"));
				�������α׷���������.set�Էºμ�(rs.getString("�Էºμ�"));
				list.add(�������α׷���������);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void ���Ƹ���������insert(int �����⵵, String �����а���) {
		String sql = "insert into �������α׷�����(�а���,�⵵) values(?,?);";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �����а���);
			pstmt.setInt(2, �����⵵);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int ��(int �����⵵, String �����а���) {
		int �� = 0;
		String sql = "select �� from �������α׷�view where �⵵=? and �а���=?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, �����⵵);
			pstmt.setString(2, �����а���);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				�� = rs.getInt("��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ��;
	}
	public void update�������α׷�����(int �����⵵, String �����а���, float �������α׷��������) {
		String sql = "update �������α׷����� set �������α׷���������=? where �а���=? and �⵵=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, �������α׷��������);
			pstmt.setString(2, �����а���);
			pstmt.setInt(3, �����⵵);

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
}