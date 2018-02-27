package �������;

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
import �������.�������;
import �������.�������Dao;

@WebServlet("/�������EditAction")
public class �������EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	�������Dao dao = new �������Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String �а��� = request.getParameter("�а���");

			String �б� = request.getParameter("�б�");
			String ���� = request.getParameter("����");
			int �̼����� = Integer.parseInt(request.getParameter("�̼�����"));
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
			String �Էºμ� = request.getParameter("�Էºμ�");

			int �й� = Integer.parseInt(request.getParameter("�й�"));

			������� ������� = new �������();
			�������.set�⵵(�⵵);
			�������.set�б�(�б�);
			�������.set�а���(�а���);
			�������.set�й�(�й�);
			�������.set����(����);
			�������.set�̼�����(�̼�����);
			�������.set���(���);
			�������.set�Էºμ�(�Էºμ�);

			if (!dao.insert(�������)) {
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

			String �б� = request.getParameter("�б�");
			String ���� = request.getParameter("����");
			int �̼����� = Integer.parseInt(request.getParameter("�̼�����"));
			String ��� = request.getParameter("���");
			int �й� = Integer.parseInt(request.getParameter("�й�"));
			int ���� = Integer.parseInt(request.getParameter("����"));

			������� ������� = new �������();

			�������.set�⵵(�⵵);
			�������.set�б�(�б�);
			�������.set�а���(�а���);
			�������.set�й�(�й�);
			�������.set����(����);
			�������.set�̼�����(�̼�����);
			�������.set���(���);
			�������.set����(����);

			if (!dao.update(�������)) {
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

	public float �̼�����(int �̼�����1�б�, int �̼�����2�б�, int ���л�1�б�, int ���л�2�б�) {
		float result = (float) (((�̼�����1�б� / (���л�1�б� * 1.0)) + (�̼�����2�б� / (���л�2�б� * 1.0))) / 2.0);
		float �̼����� =   result;
		System.out.println("�̼����� 1�б� :" + �̼�����1�б� + " �̼�����2�б� :" + �̼�����2�б� + " ���л�1�б� :" + ���л�1�б� + " ���л�2�б� :" + ���л�2�б�
				+ " �̼����� :" + �̼�����);
		return �̼�����;
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "�̼�����";
		String table = "�����������";

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			int �̼�����1�б� = dao.�̼�����(�а���, "1�б�");
			int �̼�����2�б� = dao.�̼�����(�а���, "2�б�");
			int ���л�1�б� = defaultQuery.���л���(�⵵, �а���);
			int ���л�2�б� = dao.���л�2�б�(�⵵, �а���);

			float �̼����� = �̼�����(�̼�����1�б�, �̼�����2�б�, ���л�1�б�, ���л�2�б�);

			dao.update�����������(�⵵, �а���, �̼�����);
		}

		float ��� = (float)defaultQuery.avg(�⵵, column, table);
		float ǥ������ = (float)defaultQuery.std(�⵵, column, table);

		for (String �а��� : �а����) {

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
