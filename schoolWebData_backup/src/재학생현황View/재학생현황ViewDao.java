package ���л���ȲView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class ���л���ȲViewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() { 
		String sql = "select count(*) from ���л���Ȳview; ";
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

	public List<���л���ȲView> select���л���ȲView(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���л���ȲView> list = new ArrayList<���л���ȲView>();

		String sql = " select * from ���л���Ȳview limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			
				���л���ȲView ���л���Ȳ = new ���л���ȲView();
				
				���л���Ȳ.set�⵵(rs.getInt("�⵵"));
				���л���Ȳ.set�а���(rs.getString("�а���"));
				���л���Ȳ.set����(rs.getString("����"));
				���л���Ȳ.set�л�����(rs.getInt("�л�����"));
				���л���Ȳ.set��������(rs.getInt("��������"));
				���л���Ȳ.set������(rs.getInt("������"));
				���л���Ȳ.set������(rs.getInt("������"));
				���л���Ȳ.set��(rs.getInt("��"));
				���л���Ȳ.set���л������(rs.getFloat("���л������"));
				���л���Ȳ.set��ü���л������(rs.getFloat("��ü���л������"));
				���л���Ȳ.set���������л������(rs.getFloat("���������л������"));
				���л���Ȳ.setT����(rs.getFloat("T����"));
				���л���Ȳ.set�ܰ�����(rs.getString("�ܰ�����"));
				���л���Ȳ.set�ż�����(rs.getString("�ż�����"));
				���л���Ȳ.setŸ�а�������(rs.getInt("Ÿ�а�������"));

				list.add(���л���Ȳ);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
