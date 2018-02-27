package ĸ���������view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import ĸ���������view.ĸ���������view;

public class ĸ���������viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from ĸ����view";
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

	public List<ĸ���������view> selectĸ���������view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<ĸ���������view> list = new ArrayList<ĸ���������view>();

		String sql="select * from ĸ����view limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ĸ���������view ĸ���������view = new ĸ���������view();
			
				ĸ���������view.set�⵵(rs.getInt("�⵵"));
				ĸ���������view.set�ܰ�����(rs.getString("�ܰ�����"));
				ĸ���������view.set�а���(rs.getString("�а���"));
				ĸ���������view.set�̼�1�б�(rs.getInt("�̼�1�б�"));
				ĸ���������view.set�̼�2�б�(rs.getInt("�̼�2�б�"));
				ĸ���������view.set���1�б�(rs.getInt("���1�б�"));
				ĸ���������view.set���2�б�(rs.getInt("���2�б�"));
				ĸ���������view.set�̼��հ�(rs.getInt("�̼��հ�"));
				ĸ���������view.set����հ�(rs.getInt("����հ�"));
				ĸ���������view.set�̼��л�����(rs.getFloat("�̼��л�����"));
				ĸ���������view.setT����(rs.getDouble("T����"));

				list.add(ĸ���������view);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}