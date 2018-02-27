package ��������;

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

import ��������.��������Dao;
import defaultMethod.defaultQuery;
import defaultMethod.�⵵;

@WebServlet("/��������EditAction")
public class ��������EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		��������Dao dao = new ��������Dao();

		try {
			if (oper.equals("add")) {
				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
				int �����л��� = Integer.parseInt(request.getParameter("�����л���"));
				float ������������ = Float.parseFloat(request.getParameter("������������"));
				String �Էºμ� = request.getParameter("�Էºμ�");

				float �л��������� = �л���������(������������, �����л���);

				�������� ��������bean = new ��������();

				��������bean.set�⵵(�⵵);
				��������bean.set�а���(�а���);
				��������bean.set�����л���(�����л���);
				��������bean.set������������(������������);
				��������bean.set�л���������(�л���������);
				��������bean.set�Էºμ�(�Էºμ�);

				if (!dao.insert(��������bean)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.flush();
					out.close();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				��������();

			} else if (oper.equals("edit")) {
				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
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

					int �����л��� = Integer.parseInt(request.getParameter("�����л���"));
					float ������������ = Float.parseFloat(request.getParameter("������������"));

					float �л��������� = �л���������(������������, �����л���);

					�������� ��������bean = new ��������();
					��������bean.set����(����);
					��������bean.set�⵵(�⵵);
					��������bean.set�а���(�а���);
					��������bean.set�����л���(�����л���);
					��������bean.set������������(������������);
					��������bean.set�л���������(�л���������);

					if (!dao.update(��������bean)) {
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
				System.out.println("�߸��� �����Դϴ�.");
			}
		} catch (NumberFormatException n) {
			n.printStackTrace();

		}
	}

	public float �л���������(float ������������, int �����л���) {
		if (������������ == 0 || �����л��� == 0)
			return 0;
		else {
		//	BigDecimal B_������������ = new BigDecimal(String.valueOf(������������));
		//	BigDecimal B_�����л��� = new BigDecimal(String.valueOf(�����л���));
			float ���������� = ������������/�����л���;
			System.out.println("�������� ���� :"+������������+" �����л��� :"+�����л���+" ������ ���� ��� ��� :" + ����������);
			return ����������;

		}
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "�л���������";
		String table = "��������";

		float ��� = (float) defaultQuery.avg(�⵵, column, table);
		float ǥ������ = (float) defaultQuery.std(�⵵, column, table);

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			float �л��������� = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(�л���������, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}