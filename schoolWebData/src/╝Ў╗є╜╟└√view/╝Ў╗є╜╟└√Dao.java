package �������view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import �������view.�������;

public class �������Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from �������view";
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

		String sql="select * from �������view limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				������� ������� = new �������();
				�������.set�⵵(rs.getInt("�⵵"));
				�������.set�ܰ�����(rs.getString("�ܰ�����"));
				�������.set�а���(rs.getString("�а���"));
				�������.set���ӱ�����(rs.getInt("���ӱ�����"));
				�������.set��������(rs.getFloat("��������"));
				�������.set��������(rs.getFloat("��������"));
				�������.set��������(rs.getFloat("��������"));
				�������.set���л���(rs.getInt("���л���"));
				�������.set�л�����(rs.getFloat("�л�����"));
				�������.set�л�����(rs.getFloat("�л�����"));
				�������.set�л�����(rs.getFloat("�л�����"));
				�������.set�������(rs.getFloat("�������"));
				�������.setT����(rs.getDouble("T����"));
				
				list.add(�������);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
}
