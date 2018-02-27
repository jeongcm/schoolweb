package ���Ի���ȲView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class ���Ի���ȲViewDao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from ���Ի�view";
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

	public List<���Ի���ȲView> select���Ի���ȲView(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<���Ի���ȲView> list = new ArrayList<���Ի���ȲView>();

		String sql = " select * from ���Ի�view limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				���Ի���ȲView ���Ի���Ȳ = new ���Ի���ȲView();
				���Ի���Ȳ.set�⵵(rs.getInt("�⵵"));
				���Ի���Ȳ.set�а���(rs.getString("�а���"));
				���Ի���Ȳ.set�����ο�(rs.getInt("�����������ο�"));
				���Ի���Ȳ.set�����ڼ�(rs.getInt("�����������ڼ�"));
				���Ի���Ȳ.set���Ի������(rs.getFloat("���Ի������"));
				���Ի���Ȳ.setT����(rs.getFloat("T����"));
				���Ի���Ȳ.set�ܰ�����(rs.getString("�ܰ�����"));

				list.add(���Ի���Ȳ);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
