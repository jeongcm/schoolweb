package ���Ǵ���;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ���Ǵ���.���Ǵ���Dao;
import defaultMethod.defaultQuery;
import defaultMethod.�⵵;

@WebServlet("/���Ǵ���EditAction")

public class ���Ǵ���EditAction extends HttpServlet {
	���Ǵ���Dao dao = new ���Ǵ���Dao();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		try {
			if (oper.equals("add")) {
				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));

				float ���� = Float.parseFloat(request.getParameter("����"));
				String ���� = request.getParameter("����");
				String �б� = request.getParameter("�б�");
				String ���� = request.getParameter("����");
				String ���� = request.getParameter("����");
				String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
				String �Էºμ� = request.getParameter("�Էºμ�");
				float �������� = dao.��������(�⵵, �а���);
				float �������ð��� = dao.��������(�⵵, �а���);
				float ���������ʼ����� = dao.�����ʼ�����(�⵵, �а���);
				float �� = dao.�����Ѱ�(�⵵, �а���);
				float set���Ǵ�� = ���Ǵ��(��������, ���������ʼ�����, �������ð���, ��);
				dao.update���Ǵ��(set���Ǵ��, �⵵, �а���);

				���Ǵ��� ���Ǵ��󼼰�� = new ���Ǵ���();

				���Ǵ��󼼰��.set�⵵(�⵵);
				���Ǵ��󼼰��.set�а���(�а���);
				���Ǵ��󼼰��.set����(����);
				���Ǵ��󼼰��.set�б�(�б�);
				���Ǵ��󼼰��.set����(����);
				���Ǵ��󼼰��.set����(����);
				���Ǵ��󼼰��.set���(���);
				���Ǵ��󼼰��.set����(����);
				���Ǵ��󼼰��.set�Էºμ�(�Էºμ�);
				PrintWriter out = response.getWriter();

				if (!dao.insert(���Ǵ��󼼰��)) {
					out.write("fail");
					out.flush();
					out.close();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				��������();

			} else if (oper.equals("edit")) {

				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
				String ���� = request.getParameter("����");
				String �б� = request.getParameter("�б�");
				String ���� = request.getParameter("����");
				String ���� = request.getParameter("����");
				String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
				int ���� = Integer.parseInt(request.getParameter("����"));
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
					���Ǵ��� ���Ǵ��� = new ���Ǵ���();

					float ���� = Float.parseFloat(request.getParameter("����"));
					float �������� = dao.��������(�⵵, �а���);
					float �������ð��� = dao.��������(�⵵, �а���);
					float ���������ʼ����� = dao.�����ʼ�����(�⵵, �а���);
					float �� = dao.�����Ѱ�(�⵵, �а���);
					float set���Ǵ�� = ���Ǵ��(��������, ���������ʼ�����, �������ð���, ��);
					dao.update���Ǵ��(set���Ǵ��, �⵵, �а���);

					���Ǵ���.set�⵵(�⵵);
					���Ǵ���.set�а���(�а���);
					���Ǵ���.set����(����);
					���Ǵ���.set�б�(�б�);
					���Ǵ���.set����(����);
					���Ǵ���.set����(����);
					���Ǵ���.set���(���);
					���Ǵ���.set����(����);
					���Ǵ���.set����(����);

					if (!dao.update(���Ǵ���)) {
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

	public float ���Ǵ��(float ��������, float ���������ʼ�����, float �������ð���, float ��) {
		BigDecimal B_�������� = new BigDecimal(String.valueOf(��������));
		BigDecimal B_���������ʼ����� = new BigDecimal(String.valueOf(���������ʼ�����));
		BigDecimal B_�������ð��� = new BigDecimal(String.valueOf(�������ð���));
		BigDecimal B_�� = new BigDecimal(String.valueOf(��));

		BigDecimal B_100 = new BigDecimal("100");

		System.out.println("�������� :" + �������� + " ���������ʼ����� :" + ���������ʼ����� + " �������ð���:" + �������ð��� + " �հ� :" + ��);

		BigDecimal mol = B_��������.add(B_���������ʼ�����).add(B_�������ð���);
		if (�� > 0 && mol.compareTo(BigDecimal.ZERO) > 0) {
			float ���Ǵ�� = (B_��.divide(mol, 12, BigDecimal.ROUND_DOWN)).multiply(B_100).floatValue();
			System.out.println("���Ǵ�� ����� :" + ���Ǵ��);
			return ���Ǵ��;
		} else {
			return 0;
		}
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "���Ǵ�����";
		String table = "�������Ǵ�����";

		float ��� = defaultQuery.avg(�⵵, column, table);
		float ǥ������ =defaultQuery.std(�⵵, column, table);

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			float ���Ǵ��󼼺��� = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(���Ǵ��󼼺���, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}