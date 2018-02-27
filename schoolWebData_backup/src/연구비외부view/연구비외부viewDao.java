package ������ܺ�view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


import dbConnection.dbConnection;
import ������ܺ�view.������ܺ�view;

public class ������ܺ�viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;


	// ��ü ���� ���� �����ϴ� �޼���
	public int getCountRow() {
		String sql = "select count(*) from ���ӱ���1�δ米�ܿ�����dview";
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
	public List<������ܺ�view> select���ӱ���1�δ米�ܿ�����dview(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<������ܺ�view> list = new ArrayList<������ܺ�view>();

		
		String sql="select * from ���ӱ���1�δ米�ܿ�����dview order by ���س⵵  desc,�б� asc limit ?,?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			
			rs = pstmt.executeQuery();
	
			 //����� ����, ����
			while (rs.next()) {
				������ܺ�view ���ӱ���1�δ米�ܿ�����dview = new ������ܺ�view();
				
				���ӱ���1�δ米�ܿ�����dview.set���س⵵(rs.getString("���س⵵"));
				���ӱ���1�δ米�ܿ�����dview.set�б�(rs.getString("�б�"));
				���ӱ���1�δ米�ܿ�����dview.set�а�(rs.getString("�а�"));
				���ӱ���1�δ米�ܿ�����dview.set���ӱ�����(rs.getInt("���ӱ�����"));
				���ӱ���1�δ米�ܿ�����dview.set�Ұ�(rs.getInt("�Ұ�"));
				���ӱ���1�δ米�ܿ�����dview.set������C(rs.getInt("������C"));
				���ӱ���1�δ米�ܿ�����dview.set������D(rs.getInt("������D"));
				���ӱ���1�δ米�ܿ�����dview.set������E(rs.getInt("������E"));
				���ӱ���1�δ米�ܿ�����dview.set������F(rs.getInt("������F"));
				���ӱ���1�δ米�ܿ�����dview.set���ӱ���1�δ米�ܿ�����(rs.getFloat("���ӱ���1�δ米�ܿ�����"));
				���ӱ���1�δ米�ܿ�����dview.setT����(rs.getDouble("T����"));
				

				list.add(���ӱ���1�δ米�ܿ�����dview);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}