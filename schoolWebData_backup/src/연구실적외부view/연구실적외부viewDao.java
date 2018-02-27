package ���������ܺ�view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


import dbConnection.dbConnection;
import ���������ܺ�view.���������ܺ�view;

public class ���������ܺ�viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;


	// ��ü ���� ���� �����ϴ� �޼���
	public int getCountRow() {
		String sql = "select count(*) from ���ӱ���1�δ翬������dview";
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

	// ��ü ���� ������ ����Ʈ�� �����ϴ� �޼���
	public List<���������ܺ�view> select���ӱ���1�δ翬������dview(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���������ܺ�view> list = new ArrayList<���������ܺ�view>();

		
		String sql="select * from ���ӱ���1�δ翬������dview order by ���س⵵  desc,�а� asc limit ?,?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			
			rs = pstmt.executeQuery();
	
			 //����� ����, ����
			while (rs.next()) {
				���������ܺ�view ���ӱ���1�δ翬������dview = new ���������ܺ�view();
				
				���ӱ���1�δ翬������dview.set���س⵵(rs.getString("���س⵵"));
				���ӱ���1�δ翬������dview.set�б�(rs.getString("�б�"));
				���ӱ���1�δ翬������dview.set�а�(rs.getString("�а�"));
				���ӱ���1�δ翬������dview.set���ӱ���(rs.getInt("���ӱ���"));
				���ӱ���1�δ翬������dview.set����(rs.getFloat("����"));
				���ӱ���1�δ翬������dview.set����(rs.getFloat("����"));
				���ӱ���1�δ翬������dview.set�������B(rs.getFloat("�������B"));
				���ӱ���1�δ翬������dview.setSCI��E(rs.getFloat("SCI��E"));
				���ӱ���1�δ翬������dview.set���ӱ���1�δ翬������(rs.getDouble("���ӱ���1�δ翬������"));
				���ӱ���1�δ翬������dview.setT����(rs.getDouble("T����"));
				
				
				list.add(���ӱ���1�δ翬������dview);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}