package ��������;

import java.io.IOException;
import defaultMethod.�⵵;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ��������.��������Dao;
import defaultMethod.defaultQuery;

@WebServlet("/��������EditAction")
public class ��������EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	��������Dao dao = new ��������Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		try {
			if (oper.equals("add")) {

				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));

				int ���ӱ����� = defaultQuery.���ӱ�����(�⵵, �а���);
				float ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
						: Float.parseFloat((request.getParameter("����")));
				float ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
						: Float.parseFloat((request.getParameter("����")));
				float ������ܵ����� = defaultClass.nullCheck(request.getParameter("������ܵ�����")) ? 0
						: Float.parseFloat((request.getParameter("������ܵ�����")));
				float ������ܵ����ĺ� = defaultClass.nullCheck(request.getParameter("������ܵ����ĺ�")) ? 0
						: Float.parseFloat((request.getParameter("������ܵ����ĺ�")));
				float SCI�� = defaultClass.nullCheck(request.getParameter("SCI��")) ? 0
						: Float.parseFloat((request.getParameter("SCI��")));
				float SCOPUS�м��� = defaultClass.nullCheck(request.getParameter("SCOPUS�м���")) ? 0
						: Float.parseFloat((request.getParameter("SCOPUS�м���")));
				String �Էºμ� = request.getParameter("�Էºμ�");

				float ����������� = ��������(���ӱ�����, ����, ����, ������ܵ�����, ������ܵ����ĺ�, SCI��, SCOPUS�м���);

				�������� �������� = new ��������();

				��������.set�⵵(�⵵);
				��������.set�а���(�а���);
				��������.set����(����);
				��������.set����(����);
				��������.set������ܵ�����(������ܵ�����);
				��������.set������ܵ����ĺ�(������ܵ����ĺ�);
				��������.setSCI��(SCI��);
				��������.setSCOPUS�м���(SCOPUS�м���);
				��������.set�Էºμ�(�Էºμ�);
				��������.set��������(�����������);

				if (!dao.insert(��������)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				��������();

			} else if (oper.equals("edit")) {
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
				String �а��� = request.getParameter("�а���");

				PrintWriter out = response.getWriter();

				if (defaultQuery.�а���üũ(�а���).equals("0")) {

					out.write("failMajor");
					out.flush();
					out.close();

				} else if (defaultQuery.�⵵üũ(�⵵).equals("0")) {

					out.write("failYear");
					out.flush();
					out.close();

				} else {
					float ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
							: Float.parseFloat((request.getParameter("����")));
					float ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
							: Float.parseFloat((request.getParameter("����")));
					float ������ܵ����� = defaultClass.nullCheck(request.getParameter("������ܵ�����")) ? 0
							: Float.parseFloat((request.getParameter("������ܵ�����")));
					float ������ܵ����ĺ� = defaultClass.nullCheck(request.getParameter("������ܵ����ĺ�")) ? 0
							: Float.parseFloat((request.getParameter("������ܵ����ĺ�")));
					float SCI�� = defaultClass.nullCheck(request.getParameter("SCI��")) ? 0
							: Float.parseFloat((request.getParameter("SCI��")));
					float SCOPUS�м��� = defaultClass.nullCheck(request.getParameter("SCOPUS�м���")) ? 0
							: Float.parseFloat((request.getParameter("SCOPUS�м���")));
					int ���� = Integer.parseInt(request.getParameter("����"));
					int ���ӱ����� = defaultQuery.���ӱ�����(�⵵, �а���);
					float ����������� = ��������(���ӱ�����, ����, ����, ������ܵ�����, ������ܵ����ĺ�, SCI��, SCOPUS�м���);

					�������� �������� = new ��������();

					��������.set�⵵(�⵵);
					��������.set�а���(�а���);
					��������.set����(����);
					��������.set����(����);
					��������.set������ܵ�����(������ܵ�����);
					��������.set������ܵ����ĺ�(������ܵ����ĺ�);
					��������.setSCI��(SCI��);
					��������.setSCOPUS�м���(SCOPUS�м���);
					��������.set����(����);
					��������.set��������(�����������);

					if (dao.update(��������) == false) {
						out.write("fail");
						out.flush();
						out.close();
					}
				}

			} else if (oper.equals("del")) {

				int ���� = Integer.parseInt(request.getParameter("����"));
				dao.delete(����);

				��������();

			} else if (oper.equals("delAll")) {
				String �Էºμ� = request.getParameter("�Էºμ�");
				dao.dellAll(�Էºμ�);

				��������();

			} else {
				System.out.println("�߸��� ���� �Դϴ�.");
			}
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

	public float ��������(int ���ӱ�����, float ����, float ����, float ������ܵ�����, float ������ܵ����ĺ�, float SCI��, float SCOPUS�м���) {
		BigDecimal B_���ӱ����� = new BigDecimal(String.valueOf(���ӱ�����));
		BigDecimal B_���� = new BigDecimal(String.valueOf(����));
		BigDecimal B_���� = new BigDecimal(String.valueOf(����));
		BigDecimal B_������ܵ����� = new BigDecimal(String.valueOf(������ܵ�����));
		BigDecimal B_������ܵ����ĺ� = new BigDecimal(String.valueOf(������ܵ����ĺ�));
		BigDecimal B_SCI�� = new BigDecimal(String.valueOf(SCI��));
		BigDecimal B_SCOPUS�м��� = new BigDecimal(String.valueOf(SCOPUS�м���));

		BigDecimal B_1_2 = new BigDecimal("1.2");
		BigDecimal B_0_7 = new BigDecimal("0.7");
		BigDecimal B_1 = new BigDecimal("1");
		BigDecimal B_2 = new BigDecimal("2");

		BigDecimal mol1 = B_����.multiply(B_1_2).add(B_����.multiply(B_0_7));
		BigDecimal mol2 = B_������ܵ�����.multiply(B_1_2).add((B_������ܵ����ĺ�.multiply(B_1))).add(B_SCI��.multiply(B_2).add(B_SCOPUS�м���.multiply(B_1_2)));

		System.out.println("����, ���� : " + mol1 + ", ������, �����ĺ� , SCI, SCOPUS : " + mol2);

		if (B_���ӱ�����.compareTo(BigDecimal.ZERO) > 0) {
			if (mol1.compareTo(BigDecimal.ZERO) <= 0)
				mol1 = new BigDecimal("0.0");
			else
				mol1 = mol1.divide(B_���ӱ�����, 15, BigDecimal.ROUND_DOWN);

			if (mol2.compareTo(BigDecimal.ZERO) <= 0)
				mol2 = new BigDecimal("0.0");
			else
				mol2 = mol2.divide(B_���ӱ�����, 15, BigDecimal.ROUND_DOWN);


			float �������� = (mol1.add(mol2)).floatValue();
			
			System.out.println("�������������" + ��������);
			
			return ��������;
		} else {
			return 0;
		}
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "��������";
		String table = "��������";

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			String �й��迭 = defaultQuery.�й��迭(�⵵, �а���);

			float ��� = defaultQuery.�й��迭avg(�⵵, column, table, �й��迭);
			float ǥ������ =defaultQuery.�й��迭std(�⵵, column, table, �й��迭);

			float ����������� = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(�����������, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}