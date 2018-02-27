package ĸ���������;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import ĸ���������.ĸ���������;

public class ĸ���������Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	
	private ResultSet rs;


	public boolean update(ĸ��������� ĸ���������) {
		String sql = "update ĸ��������� set �⵵=?,�а���=?,�̼�1�б�=?,�̼�2�б�=?,���1�б�=?,���2�б�=?,�̼��л�����=? where ����=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ĸ���������.get�⵵());
			pstmt.setString(2, ĸ���������.get�а���());
			pstmt.setInt(3, ĸ���������.get�̼�1�б�());
			pstmt.setInt(4, ĸ���������.get�̼�2�б�());
			pstmt.setInt(5, ĸ���������.get���1�б�());
			pstmt.setInt(6, ĸ���������.get���2�б�());
			pstmt.setFloat(7, ĸ���������.get�̼��л�����());
			pstmt.setInt(8, ĸ���������.get����());

			pstmt.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	public boolean insert(ĸ��������� ĸ���������){
		String sql = "insert into ĸ���������(�⵵,�а���,�̼�1�б�,�̼�2�б�,���1�б�,���2�б�,�̼��л�����,�Էºμ�) values(?,?,?,?,?,?,?,?);";
		try{
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, ĸ���������.get�⵵());
			pstmt.setString(2, ĸ���������.get�а���());
			pstmt.setInt(3, ĸ���������.get�̼�1�б�());
			pstmt.setInt(4, ĸ���������.get�̼�2�б�());
			pstmt.setInt(5, ĸ���������.get���1�б�());
			pstmt.setInt(6, ĸ���������.get���2�б�());
			pstmt.setFloat(7, ĸ���������.get�̼��л�����());
			pstmt.setString(8, ĸ���������.get�Էºμ�());
		
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
      e.printStackTrace();
      return false;
	}
}
	
	public void delete(int ����) {
        String sql = "delete from ĸ��������� where ����=?;";
        try {
  
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ����);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
   }
	
 public void dellAll(String �Էºμ�) {
		String sql = "delete from ĸ��������� where �Էºμ�=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, �Էºμ�);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public int getCountRow() {
		String sql = "select count(*) from ĸ���������";
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
	
	public List<ĸ���������> selectĸ���������(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<ĸ���������> list = new ArrayList<ĸ���������>();

		String sql="select *,���1�б�+���2�б� ����հ�,�̼�1�б�+�̼�2�б� �̼��հ� from ĸ��������� order by �а��� limit ?,?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ĸ��������� ĸ��������� = new ĸ���������();
				ĸ���������.set�⵵(rs.getInt("�⵵"));
				ĸ���������.set�а���(rs.getString("�а���"));
				ĸ���������.set���1�б�(rs.getInt("���1�б�"));
				ĸ���������.set���2�б�(rs.getInt("���2�б�"));
				ĸ���������.set�̼�1�б�(rs.getInt("�̼�1�б�"));
				ĸ���������.set�̼�2�б�(rs.getInt("�̼�2�б�"));
				ĸ���������.set�Էºμ�(rs.getString("�Էºμ�"));
				ĸ���������.set����(rs.getInt("����"));
			

				list.add(ĸ���������);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
