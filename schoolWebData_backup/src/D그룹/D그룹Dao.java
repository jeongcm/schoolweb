package D�׷�;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class D�׷�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt, pstmt2, pstmt3, pstmt4, pstmt5, pstmt6;

	private ResultSet rs, rs2, rs3, rs4, rs5, rs6;

	public List<D�׷�> selectD�׷�() {

		List<D�׷�> list = new ArrayList<D�׷�>();

		String sql = "select max(�а�.�⵵) �⵵,�ܰ�����,�а�.�а���,�й��迭1,T���� �ߵ�Ż����T,(T����*0.04) �ߵ�Ż����ȯ�� from �а���Ȳ �а� left outer join �ߵ�Ż�����ܺ� �ߵ�Ż���� on �а�.�а���=�ߵ�Ż����.�а��� group by �а�.�а��� order by �а�.���� desc ;";
		String sql2 = "select �а�.�а���,T���� ���Ի�T,(T����*0.02) ���Ի�ȯ�� from ���Ի���Ȳ�ܺ� right outer join �а���Ȳ �а� on ���Ի���Ȳ�ܺ�.�а���=�а�.�а��� order by �а�.���� desc ;";
		String sql3 = "select �а�.�а���,T���� �����T,(T����*0.04) �����ȯ�� from �����Ȳ�ܺ� �����Ȳ right outer join �а���Ȳ �а� on �����Ȳ.�а���=�а�.�а��� order by �а�.���� desc;";
		String sql4 = "select �а�.�а���,T���� ��������T,(T����*0.04) ��������ȯ�� from �����Ȳ�ܺ� �����Ȳ right outer join �а���Ȳ �а� on �����Ȳ.�а���=�а�.�а��� order by �а�.���� desc;";
		String sql5 = "select �а�.�а���,T���� ������T,(T����*0.04) ������ȯ�� from �����Ȳ�ܺ� �����Ȳ right outer join �а���Ȳ �а� on �����Ȳ.�а���=�а�.�а��� order by �а�.���� desc;";
		String sql6 = "select �а�.�а���,T���� ����T,(T����*0.04) ����ȯ�� from �����Ȳ�ܺ� �����Ȳ right outer join �а���Ȳ �а� on �����Ȳ.�а���=�а�.�а��� order by �а�.���� desc;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt4 = conn.prepareStatement(sql4);
			pstmt5 = conn.prepareStatement(sql5);
			pstmt6 = conn.prepareStatement(sql6);

			rs = pstmt.executeQuery();
			rs2 = pstmt2.executeQuery();
			rs3 = pstmt3.executeQuery();
			rs4 = pstmt4.executeQuery();
			rs5 = pstmt5.executeQuery();
			rs6 = pstmt6.executeQuery();

			while (rs.next() && rs2.next() && rs3.next() && rs4.next() && rs5.next() && rs6.next()) {
				D�׷� D�׷� = new D�׷�();
				D�׷�.set�⵵(rs.getInt("�⵵"));
				D�׷�.set�а���(rs.getString("�а���"));
				D�׷�.set�ܰ�����(rs.getString("�ܰ�����"));
				D�׷�.set�й��迭(rs.getString("�й��迭1"));

				D�׷�.set�ߵ�Ż����T(rs.getFloat("�ߵ�Ż����T"));
				D�׷�.set�ߵ�Ż����ȯ��((float)(Math.round(rs.getFloat("�ߵ�Ż����ȯ��")*100)/100.0));

				D�׷�.set���Ի�T(rs2.getFloat("���Ի�T"));
				D�׷�.set���Ի�ȯ��((float)(Math.round(rs2.getFloat("���Ի�ȯ��")*100)/100.0));

				D�׷�.set�����T(rs3.getFloat("�����T"));
				D�׷�.set�����ȯ��((float)(Math.round(rs3.getFloat("�����ȯ��")*100)/100.0));

				D�׷�.set��������T(rs4.getFloat("��������T"));
				D�׷�.set��������ȯ��((float)(Math.round(rs4.getFloat("��������ȯ��")*100)/100.0));

				D�׷�.set������T(rs5.getFloat("������T"));
				D�׷�.set������ȯ��((float)(Math.round(rs5.getFloat("������ȯ��")*100)/100.0));

				D�׷�.set����T(rs6.getFloat("����T"));
				D�׷�.set����ȯ��((float)(Math.round(rs6.getFloat("����ȯ��")*100)/100.0));

				D�׷�.setȯ������((float)(Math.round(rs.getFloat("�ߵ�Ż����ȯ��") + rs2.getFloat("���Ի�ȯ��") + rs6.getFloat("����ȯ��") + rs4.getFloat("��������ȯ��")
						+ rs5.getFloat("������ȯ��") + rs3.getFloat("�����ȯ��")*100)/100.0));

				list.add(D�׷�);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


}
