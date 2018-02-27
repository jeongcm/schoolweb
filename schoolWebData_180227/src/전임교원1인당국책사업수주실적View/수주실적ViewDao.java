package ���ӱ���1�δ籹å������ֽ���View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class ���ֽ���ViewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() { 
		String sql = "select count(*) from ��å������ֽ���view; ";
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

	public List<���ֽ���View> select���ֽ���view(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���ֽ���View> list = new ArrayList<���ֽ���View>();

		String sql = " select * from ��å������ֽ���view limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			
				���ֽ���View ���ֽ���View=new ���ֽ���View();
				���ֽ���View.set�⵵(rs.getInt("�⵵"));
				���ֽ���View.set���ӱ�����(rs.getInt("���ӱ�����"));
				���ֽ���View.set�а���(rs.getString("�а���"));
				���ֽ���View.set�ܰ�����(rs.getString("�ܰ�����"));
				���ֽ���View.setT����(rs.getFloat("T����"));
				���ֽ���View.set���и��Ǳ�å��������Ѿ�(rs.getInt("���и��Ǳ�å��������Ѿ�"));
				���ֽ���View.set���ӱ���1�δ籹å��������Ѿ�(rs.getFloat("���ӱ���1�δ籹å��������Ѿ�"));

				list.add(���ֽ���View);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
