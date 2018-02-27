package ���Ƹ�;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import defaultMethod.�⵵;
import ���Ƹ�.���Ƹ�;
import ���Ƹ�.���Ƹ�Dao;

@WebServlet("/���Ƹ�EditAction")
public class ���Ƹ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	���Ƹ�Dao dao = new ���Ƹ�Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {

			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String �а��� = request.getParameter("�а���");
			String �����μ� = request.getParameter("�����μ�");
			String ���Ƹ��� = request.getParameter("���Ƹ���");
			String ���� = request.getParameter("����");
			String �������� = request.getParameter("��������");
			String �л���ǥ�г� = request.getParameter("�л���ǥ�г�");
			String �л���ǥ�̸� = request.getParameter("�л���ǥ�̸�");
			int ȸ���� = Integer.parseInt(request.getParameter("ȸ����"));
			int ���������� = defaultClass.nullCheck(request.getParameter("����������")) ? 0
					: Integer.parseInt(request.getParameter("ȸ����"));
			String �������� = request.getParameter("��������");
			String �������� = request.getParameter("��������");
			String ��� = request.getParameter("���");
			String �Էºμ� = request.getParameter("�Էºμ�");

			���Ƹ� ���Ƹ� = new ���Ƹ�();

			���Ƹ�.set�⵵(�⵵);
			���Ƹ�.set�����μ�(�����μ�);
			���Ƹ�.set�а���(�а���);
			���Ƹ�.set���Ƹ���(���Ƹ���);
			���Ƹ�.set����(����);
			���Ƹ�.set��������(��������);
			���Ƹ�.set�л���ǥ�г�(�л���ǥ�г�);
			���Ƹ�.set�л���ǥ�̸�(�л���ǥ�̸�);
			���Ƹ�.set����������(����������);
			���Ƹ�.setȸ����(ȸ����);
			���Ƹ�.set��������(��������);
			���Ƹ�.set��������(��������);
			���Ƹ�.set���(���);
			���Ƹ�.set�Էºμ�(�Էºμ�);

			if (!dao.insert(���Ƹ�)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		} else if (oper.equals("cal") || oper.equals("editCal")) {

			��������();

		} else if (oper.equals("edit")) {

			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String �����μ� = request.getParameter("�����μ�");
			String �а��� = request.getParameter("�а���");
			String ���Ƹ��� = request.getParameter("���Ƹ���");
			String ���� = request.getParameter("����");
			String �������� = request.getParameter("��������");
			String �л���ǥ�г� = request.getParameter("�л���ǥ�г�");
			String �л���ǥ�̸� = request.getParameter("�л���ǥ�̸�");
			int ȸ���� = Integer.parseInt(request.getParameter("ȸ����"));
			int ���������� = defaultClass.nullCheck(request.getParameter("����������")) ? 0
					: Integer.parseInt(request.getParameter("ȸ����"));
			String �������� = request.getParameter("��������");
			String �������� = request.getParameter("��������");
			String ��� = request.getParameter("���");
			int ���� = Integer.parseInt(request.getParameter("����"));

			���Ƹ� ���Ƹ� = new ���Ƹ�();

			���Ƹ�.set�⵵(�⵵);
			���Ƹ�.set�����μ�(�����μ�);
			���Ƹ�.set�а���(�а���);
			���Ƹ�.set���Ƹ���(���Ƹ���);
			���Ƹ�.set����(����);
			���Ƹ�.set��������(��������);
			���Ƹ�.set�л���ǥ�г�(�л���ǥ�г�);
			���Ƹ�.set�л���ǥ�̸�(�л���ǥ�̸�);
			���Ƹ�.set����������(����������);
			���Ƹ�.setȸ����(ȸ����);
			���Ƹ�.set��������(��������);
			���Ƹ�.set��������(��������);
			���Ƹ�.set���(���);
			���Ƹ�.set����(����);

			if (!dao.update(���Ƹ�)) {
				PrintWriter out = response.getWriter();

				out.write("fail");
				out.flush();
				out.close();
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
	}

	public float ���Ƹ���������(int ��, int ���л���) {
		float result = (float) (�� / (���л��� * 1.0) * 100);
		System.out.println("�հ� :" + �� + "���л��� :" + ���л��� + " ���Ƹ� �������� ��� ��� :" + result);
		return (float) (Math.round(result * 100) / 100.0);
	}

	public void ��������() {

		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "���Ƹ���������";
		String table = "���Ƹ���������";

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			int �� = dao.��(�⵵, �а���);
			int ���л��� = defaultQuery.���л���(�⵵, �а���);

			float ���Ƹ������������ = ���Ƹ���������(��, ���л���);

			dao.update���Ƹ���������(�⵵, �а���, ���Ƹ������������);

		}

		float ��� = (float) (Math.round(defaultQuery.avg(�⵵, column, table) * 100) / 100.0);
		float ǥ������ = (float) (Math.round(defaultQuery.std(�⵵, column, table) * 100) / 100.0);

		for (String �а��� : �а����) {

			float ���Ƹ��������� = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(���Ƹ���������, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}