package B�׷�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class B�׷�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt, pstmt2, pstmt3, pstmt4;

	private ResultSet rs, rs2, rs3, rs4;

	public List<B�׷�> selectB�׷�() {
		List<B�׷�> list = new ArrayList<B�׷�>();

		String sql = " select max(�а�.�⵵) �⵵,�а�.�а���,�а�.�ܰ�����,�а�.�й��迭1,T���� �ߵ�Ż��T,(T����*0.05) �ߵ�Ż��ȯ�� from �ߵ�Ż���� right outer join  �а���Ȳ �а� on �ߵ�Ż����.�а���=�а�.�а���   group by �а�.�а��� order by �а�.���� desc ;";
		String sql2 = "select T���� ���б�T,(T����*0.06) ���б�ȯ�� from �������б� right outer join �а���Ȳ �а� on �������б�.�а���=�а�.�а��� order by �а�.���� desc ;";
		String sql3 = "select T���� �������T,(T����*0.07) �������ȯ�� from ������������� right outer join �а���Ȳ �а� on �������������.�а���=�а�.�а��� order by �а�.���� desc ;";
		String sql4 = "select T���� ����Ȯ��T,(T����*0.08) ����Ȯ��ȯ�� from ���ӱ���Ȯ���� right outer join �а���Ȳ �а� on ���ӱ���Ȯ����.�а���=�а�.�а��� order by �а�.���� desc ;";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt4 = conn.prepareStatement(sql4);

			rs = pstmt.executeQuery();
			rs2 = pstmt2.executeQuery();
			rs3 = pstmt3.executeQuery();
			rs4 = pstmt4.executeQuery();

			while (rs.next() && rs2.next() && rs3.next() && rs4.next()) {
				B�׷� B�׷�= new B�׷�();
				B�׷�.set�⵵(rs.getInt("�⵵"));
				B�׷�.set�а���(rs.getString("�а���"));
				B�׷�.set�ܰ�����(rs.getString("�ܰ�����"));
				B�׷�.set�й��迭(rs.getString("�й��迭1"));

				B�׷�.set�ߵ�Ż��T(rs.getFloat("�ߵ�Ż��T"));
				B�׷�.set�ߵ�Ż��ȯ��((float)(Math.round(rs.getFloat("�ߵ�Ż��ȯ��")*100)/100.0));

				B�׷�.set���б�T(rs2.getFloat("���б�T"));
				B�׷�.set���б�ȯ��((float)(Math.round(rs2.getFloat("���б�ȯ��")*100)/100.0));

				B�׷�.set�������T(rs3.getFloat("�������T"));
				B�׷�.set�������ȯ��((float)(Math.round(rs3.getFloat("�������ȯ��")*100)/100.0));

				B�׷�.set����Ȯ��T(rs4.getFloat("����Ȯ��T"));
				B�׷�.set����Ȯ��ȯ��((float)(Math.round(rs4.getFloat("����Ȯ��ȯ��")*100)/100.0));

				B�׷�.setȯ������((float)(Math.round(rs.getFloat("�ߵ�Ż��ȯ��")+rs2.getFloat("���б�ȯ��")+rs3.getFloat("�������ȯ��")+rs4.getFloat("����Ȯ��ȯ��")*100)/100.0));
				
				list.add(B�׷�);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
