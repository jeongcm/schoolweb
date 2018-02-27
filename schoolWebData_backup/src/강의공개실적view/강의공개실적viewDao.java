package ���ǰ�������view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import ���ǰ�������view.���ǰ�������view;

public class ���ǰ�������viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;
	
	public int getCountRow() {
		String sql = "select count(*) from ���ǰ���view";
		
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

	public List<���ǰ�������view> select���ǰ�������view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���ǰ�������view> list = new ArrayList<���ǰ�������view>();

		String sql="select * from ���ǰ���view limit ?,?;";	
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
	
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				���ǰ�������view ���ǰ�������view = new ���ǰ�������view();
				
				���ǰ�������view.set�⵵(rs.getInt("�⵵")); //�а���Ȳ�� �⵵�� �а��� ������������ �ӽ��÷����� ���
				���ǰ�������view.set�а���(rs.getString("�а���"));
				���ǰ�������view.set�ܰ�����(rs.getString("�ܰ�����"));
				���ǰ�������view.set���ӱ�����(rs.getInt("���ӱ�����"));
				���ǰ�������view.set���ǵ�����B(rs.getInt("���ǵ�����B"));
				���ǰ�������view.set�̷��װ���C(rs.getInt("�̷��װ���C"));
				���ǰ�������view.set�����ڷ�D(rs.getInt("�����ڷ�D"));
				���ǰ�������view.set���ǵ�����E(rs.getInt("���ǵ�����E"));
				���ǰ�������view.set�̷��װ���F(rs.getInt("�̷��װ���F"));
				���ǰ�������view.set�����ڷ�G(rs.getInt("�����ڷ�G"));
				���ǰ�������view.set���ǰ�������(rs.getFloat("���ǰ�������"));
				���ǰ�������view.setT����(rs.getDouble("T����"));

				list.add(���ǰ�������view);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}