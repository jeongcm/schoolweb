package �������α׷���������view;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import �������α׷���������view.�������α׷���������view;

public class �������α׷���������viewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;

	
	public int getCountRow() {
		String sql = "select count(*) from �������α׷�view";
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

	public List<�������α׷���������view> select�������α׷���������view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<�������α׷���������view> list = new ArrayList<�������α׷���������view>();

		String sql="select * from �������α׷�view limit ?,?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				�������α׷���������view �������α׷���������view = new �������α׷���������view();
				�������α׷���������view.set�⵵(rs.getInt("�⵵"));
				�������α׷���������view.set�ܰ�����(rs.getString("�ܰ�����"));
				�������α׷���������view.set�а���(rs.getString("�а���"));
				�������α׷���������view.set�л���(rs.getInt("���л���"));
				�������α׷���������view.set������������(rs.getInt("����������"));
				�������α׷���������view.set���â��������(rs.getInt("���â��������"));
				�������α׷���������view.set�����н����߿�(rs.getInt("�����н����߿�"));
				�������α׷���������view.set���л�������(rs.getInt("���а�"));
				�������α׷���������view.set��Ÿ(rs.getInt("��Ÿ"));
				�������α׷���������view.set��(rs.getInt("��"));
				�������α׷���������view.set���л���(rs.getInt("���л���"));
				�������α׷���������view.set�������α׷���������(rs.getFloat("�������α׷���������"));
				�������α׷���������view.setT����(rs.getDouble("T����"));
				

				list.add(�������α׷���������view);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
