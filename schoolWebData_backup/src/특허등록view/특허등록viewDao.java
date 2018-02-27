package Ư����view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import Ư����view.Ư����view;

public class Ư����viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;
	
	
	public int getCountRow() {
		String sql = "select count(*) from Ư����view";
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

	public List<Ư����view> selectƯ����view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<Ư����view> list = new ArrayList<Ư����view>();

		String sql="select * from Ư����view limit ?,?;";
	
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Ư����view Ư����view = new Ư����view();
				
				Ư����view.set�ܰ�����(rs.getString("�ܰ�����"));
				Ư����view.set�⵵(rs.getInt("�⵵"));
				Ư����view.set�а���(rs.getString("�а���"));
				Ư����view.set����(rs.getInt("����"));
				Ư����view.set����(rs.getInt("����"));
				Ư����view.set�������(rs.getFloat("�������"));
				Ư����view.set���ӱ�����(rs.getInt("���б�"));
				Ư����view.set���Է�(rs.getFloat("���Է�"));
				Ư����view.setT����(rs.getFloat("T����"));
				

				list.add(Ư����view);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}