package ����ǽ�view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


import dbConnection.dbConnection;
import ����ǽ�view.����ǽ�view;

public class ����ǽ�viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;


	public int getCountRow() {
		String sql = "select count(*) from ����ǽ�view;";
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

	public List<����ǽ�view> select����ǽ�view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<����ǽ�view> list = new ArrayList<����ǽ�view>();

		String sql="select * from ����ǽ�view  limit ?,?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				
				����ǽ�view ����ǽ�view = new ����ǽ�view();
				
				����ǽ�view.set�⵵(rs.getInt("�⵵"));
				����ǽ�view.set�ܰ�����(rs.getString("�ܰ�����"));
				����ǽ�view.set�а���(rs.getString("�а���"));
				����ǽ�view.set_1�б�(rs.getInt("1�б�"));
				����ǽ�view.set_2�б�(rs.getInt("2�б�"));
				����ǽ�view.set�հ�(rs.getInt("�հ�"));
				����ǽ�view.set���1�б�(rs.getInt("���1�б�"));
				����ǽ�view.set���2�б�(rs.getInt("���2�б�"));
				����ǽ�view.set����հ�(rs.getInt("����հ�"));
				����ǽ�view.set�̼��л�����(rs.getFloat("�̼��л�����"));
				����ǽ�view.set����л���(rs.getInt("����л���"));
				����ǽ�view.setT����(rs.getDouble("T����"));

				list.add(����ǽ�view);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}