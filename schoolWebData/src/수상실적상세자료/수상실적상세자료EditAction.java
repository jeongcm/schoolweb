package ����������ڷ�;

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
import ����������ڷ�.����������ڷ�;
import ����������ڷ�.����������ڷ�Dao;
import defaultMethod.�⵵;

@WebServlet("/����������ڷ�EditAction")
public class ����������ڷ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	����������ڷ�Dao dao = new ����������ڷ�Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String �а��� = request.getParameter("�а���");
			String ���� = request.getParameter("����");
			String �������� = request.getParameter("��������");
			String ��ȸ�� = request.getParameter("��ȸ��");
			String ���󳻿� = request.getParameter("���󳻿�");
			String �������� = request.getParameter("��������");
			String �������� = request.getParameter("��������");
			String �Էºμ� = request.getParameter("�Էºμ�");

			����������ڷ� ����������ڷ� = new ����������ڷ�();

			����������ڷ�.set�⵵(�⵵);
			����������ڷ�.set�а���(�а���);
			����������ڷ�.set��ȸ�Ը�Ժ�(����);
			����������ڷ�.set��������(��������);
			����������ڷ�.set��ȸ��(��ȸ��);
			����������ڷ�.set���󳻿�(���󳻿�);
			����������ڷ�.set��������(��������);
			����������ڷ�.set��������(��������);
			����������ڷ�.set�Էºμ�(�Էºμ�);

			if (!dao.insert(����������ڷ�)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		} else if (oper.equals("edit")) {

			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String �а��� = request.getParameter("�а���");

			String ���� = request.getParameter("����");
			String �������� = request.getParameter("��������");
			String ��ȸ�� = request.getParameter("��ȸ��");
			String ���󳻿� = request.getParameter("���󳻿�");
			String �������� = request.getParameter("��������");
			String �������� = request.getParameter("��������");

			int ���� = Integer.parseInt(request.getParameter("����"));

			����������ڷ� ����������ڷ� = new ����������ڷ�();

			����������ڷ�.set�⵵(�⵵);
			����������ڷ�.set�а���(�а���);
			����������ڷ�.set��ȸ�Ը�Ժ�(����);
			����������ڷ�.set��������(��������);
			����������ڷ�.set��ȸ��(��ȸ��);
			����������ڷ�.set���󳻿�(���󳻿�);
			����������ڷ�.set��������(��������);
			����������ڷ�.set��������(��������);
			����������ڷ�.set����(����);

			if (!dao.update(����������ڷ�)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.flush();
				out.close();

			}
		} else if (oper.equals("cal") || oper.equals("editCal")) {

			��������();

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

	private float �������(float ���ӱ����������, float ���л��������, int ���л���, int ���ӱ�����) {

		if(���л���==0 || ���ӱ�����==0)
			return 0;
		
		float result = (float) ((���ӱ���������� / (���ӱ����� * 1.0)) + (���л�������� / (���л��� * 1.0)));
		float ������� = (float) result;
		System.out.println("���ӱ���������� :" + ���ӱ���������� + " ���л�������� :" + ���л�������� + " ���л��� :" + ���л��� + " ���ӱ����� :" + ���ӱ�����
				+ " ������� :" + �������);
		return �������;
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "�������";
		String table = "�����������";

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {

			float ���ӱ���������� = dao.���ӱ����������(�⵵, �а���);
			float ���л�������� = dao.���л��������(�⵵, �а���);
			int ���л��� = defaultQuery.���л���(�⵵, �а���);
			int ���ӱ����� = defaultQuery.���ӱ�����(�⵵, �а���);

			float ���������� = �������(���ӱ����������, ���л��������, ���л���, ���ӱ�����);

			dao.update�������(�⵵, �а���, ����������);
		}

		float ��� = defaultQuery.avg(�⵵, column, table);
		float ǥ������ = defaultQuery.std(�⵵, column, table);
		
		for (String �а��� : �а����) {
			float ������� = dao.���л��������(�⵵, �а���);
			float T���� = defaultClass.T����(���, ǥ������, �������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
