package C�׷�;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import dbConnection.dbConnection;

public class C�׷�Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt, pstmt2, pstmt3, pstmt4, pstmt5, pstmt6, pstmt7, pstmt8, pstmt9, pstmt10;
	private ResultSet rs, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10;

	public List<C�׷�> selectC�׷�() throws SQLException {
		List<C�׷�> list = new ArrayList<C�׷�>();

		String sql = "select max(�а�.�⵵) �⵵,�ܰ�����,�а�.�а���,�й��迭1,T���� �ܱ���T,(T����*0.02) �ܱ���ȯ�� from �а���Ȳ �а� left outer join �ܱ����л���Ȳ ��Ȳ on �а�.�а���=��Ȳ.�а��� left outer join �ܱ����л����� ���� on ��Ȳ.�а���=����.�а��� group by �а�.�а��� order by �а�.���� desc ;";
		String sql2 = "select �а�.�а���,T���� ������T,(T����*0.02) ������ȯ�� from �������� right outer join �а���Ȳ �а� on ��������.�а���=�а�.�а��� order by �а�.���� desc ;";
		String sql3 = "select �а�.�а���,T���� ����ǽ�T,(T����*0.02) ����ǽ�ȯ�� from ����ǽ� right outer join �а���Ȳ �а� on ����ǽ�.�а���=�а�.�а��� order by �а�.���� desc ;";
		String sql4 = "select �а�.�а���,T���� ĸ����T,(T����*0.02) ĸ����ȯ�� from ĸ��������� right outer join �а���Ȳ �а� on ĸ���������.�а���=�а�.�а��� order by �а�.���� desc ;";
		String sql5 = "select �а�.�а���,T���� �������α׷�T,(T����*0.02) �������α׷�ȯ�� from �������α׷������ ���� right outer join �а���Ȳ �а� on ����.�а���=�а�.�а��� left outer join �������α׷����� ���� on ����.�а���=����.�а��� order by �а�.���� desc ;";
		String sql6 = "select �а�.�а���,T���� ���Ƹ�T,(T����*0.02) ���Ƹ�ȯ�� from ���Ƹ� right outer join �а���Ȳ �а� on ���Ƹ�.�а���=�а�.�а��� left outer join ���Ƹ��������� ���� on ���Ƹ�.�а���=����.�а��� order by �а�.���� desc ;";
		String sql7 = "select �а�.�а���,T���� Ư����T,(T����*0.02) Ư����ȯ�� from Ư����view right outer join �а���Ȳ �а� on Ư����view.�а���=�а�.�а��� order by �а�.���� desc ;";
		String sql8 = "select �а�.�а���,T���� �������T,(T����*0.02) �������ȯ�� from �������  right outer join �а���Ȳ �а� on �������.�а���=�а�.�а��� left outer join ����������� ���� on �������.�а���=����.�а��� order by �а�.���� desc ;";
		String sql9 = "select �а�.�а���,T���� �������T,(T����*0.02) �������ȯ�� from ������� right outer join �а���Ȳ �а� on �������.�а���=�а�.�а��� left outer join ����������� ���� on �������.�а���=����.�а��� order by �а�.���� desc ;";
		String sql10 = "select �а�.�а���,T���� ���ǰ���T,(T����*0.02) ���ǰ���ȯ�� from ���ǰ������� right outer join �а���Ȳ �а� on ���ǰ�������.�а���=�а�.�а��� order by �а�.���� desc ;";
		

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt4 = conn.prepareStatement(sql4);
			pstmt5 = conn.prepareStatement(sql5);
			pstmt6 = conn.prepareStatement(sql6);
			pstmt7 = conn.prepareStatement(sql7);
			pstmt8 = conn.prepareStatement(sql8);
			pstmt9 = conn.prepareStatement(sql9);
			pstmt10 = conn.prepareStatement(sql10);

			rs = pstmt.executeQuery();
			rs2 = pstmt2.executeQuery();
			rs3 = pstmt3.executeQuery();
			rs4 = pstmt4.executeQuery();
			rs5 = pstmt5.executeQuery();
			rs6 = pstmt6.executeQuery();
			rs7 = pstmt7.executeQuery();
			rs8 = pstmt8.executeQuery();
			rs9 = pstmt9.executeQuery();
			rs10 = pstmt10.executeQuery();


			while (rs.next() && rs2.next() && rs3.next() && rs4.next() && rs5.next() && rs6.next() && rs7.next()
					&& rs8.next() && rs9.next() && rs10.next()) {
				C�׷� C�׷� = new C�׷�();

				C�׷�.set�⵵(rs.getInt("�⵵"));
				C�׷�.set�а���(rs.getString("�а���"));
				C�׷�.set�ܰ�����(rs.getString("�ܰ�����"));
				C�׷�.set�й��迭(rs.getString("�й��迭1"));

				C�׷�.set�ܱ���T(rs.getFloat("�ܱ���T"));
				C�׷�.set�ܱ���ȯ��((float) (Math.round(rs.getFloat("�ܱ���ȯ��") * 100) / 100.0));

				C�׷�.set������T(rs2.getFloat("������T"));
				C�׷�.set������ȯ��((float) (Math.round(rs2.getFloat("������ȯ��") * 100) / 100.0));

				C�׷�.set����ǽ�T(rs3.getFloat("����ǽ�T"));
				C�׷�.set����ǽ�ȯ��((float) (Math.round(rs3.getFloat("����ǽ�ȯ��") * 100) / 100.0));

				C�׷�.setĸ����T(rs4.getFloat("ĸ����T"));
				C�׷�.setĸ����ȯ��((float) (Math.round(rs4.getFloat("ĸ����ȯ��") * 100) / 100.0));

				C�׷�.set�������α׷�T(rs5.getFloat("�������α׷�T"));
				C�׷�.set�������α׷�ȯ��((float) (Math.round(rs5.getFloat("�������α׷�ȯ��") * 100) / 100.0));

				C�׷�.set���Ƹ�T(rs6.getFloat("���Ƹ�T"));
				C�׷�.set���Ƹ�ȯ��((float) (Math.round(rs6.getFloat("���Ƹ�ȯ��") * 100) / 100.0));
				
				C�׷�.setƯ����T(rs7.getFloat("Ư����T"));
				C�׷�.setƯ����ȯ��((float) (Math.round(rs7.getFloat("Ư����ȯ��") * 100) / 100.0));

				C�׷�.set�������T(rs8.getFloat("�������T"));
				C�׷�.set�������ȯ��((float) (Math.round(rs8.getFloat("�������ȯ��") * 100) / 100.0));

				C�׷�.set�������T(rs9.getFloat("�������T"));
				C�׷�.set�������ȯ��((float) (Math.round(rs9.getFloat("�������ȯ��") * 100) / 100.0));

				C�׷�.set���ǰ���T(rs10.getFloat("���ǰ���T"));
				C�׷�.set���ǰ���ȯ��((float) (Math.round(rs10.getFloat("���ǰ���ȯ��") * 100) / 100.0));

				
				C�׷�.setȯ������((float) (Math.round(rs.getFloat("�ܱ���ȯ��") + rs2.getFloat("������ȯ��") + rs3.getFloat("����ǽ�ȯ��")
						+ rs4.getFloat("ĸ����ȯ��") + rs5.getFloat("�������α׷�ȯ��") + rs6.getFloat("���Ƹ�ȯ��") + rs7.getFloat("Ư����ȯ��")
						+ rs8.getFloat("�������ȯ��") + rs9.getFloat("�������ȯ��") + rs10.getFloat("���ǰ���ȯ��") * 100) / 100.0));

				list.add(C�׷�);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}