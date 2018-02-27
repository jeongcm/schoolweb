package ������������׻��ڷ�;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultQuery;
import defaultMethod.defaultClass;
import defaultMethod.�⵵;

@WebServlet("/������������׻��ڷ�EditAction")

public class ������������׻��ڷ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	������������׻��ڷ�Dao dao = new ������������׻��ڷ�Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			String �а��� = request.getParameter("�а���");
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String ���� = request.getParameter("����");
			int �ݾ� = Integer.parseInt(request.getParameter("�ݾ�"));
			String �Էºμ� = request.getParameter("�Էºμ�");

			������������׻��ڷ� ������������׻��ڷ� = new ������������׻��ڷ�();

			������������׻��ڷ�.set�⵵(�⵵);
			������������׻��ڷ�.set�а���(�а���);
			������������׻��ڷ�.set�ݾ�(�ݾ�);
			������������׻��ڷ�.set�Էºμ�(�Էºμ�);
			������������׻��ڷ�.set����(����);

			PrintWriter out = response.getWriter();

			if (dao.insert(������������׻��ڷ�) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}
		} else if (oper.equals("cal") || oper.equals("editCal")) {

			��������();

		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();

			String �а��� = request.getParameter("�а���");
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String �а���üũ = defaultQuery.�а���üũ(�а���);
			String �⵵üũ = defaultQuery.�⵵üũ(�⵵);

			if (�а���üũ.equals("0")) {
				out.write("failMajor");
				out.flush();
				out.close();
			} else if (�⵵üũ.equals("0")) {
				out.write("failYear");
				out.flush();
				out.close();
			} else {
				String ���� = request.getParameter("����");
				int �ݾ� = Integer.parseInt(request.getParameter("�ݾ�"));
				int ���� = Integer.parseInt(request.getParameter("����"));

				������������׻��ڷ� ������������׻��ڷ� = new ������������׻��ڷ�();

				������������׻��ڷ�.set�⵵(�⵵);
				������������׻��ڷ�.set�а���(�а���);
				������������׻��ڷ�.set�ݾ�(�ݾ�);
				������������׻��ڷ�.set����(����);
				������������׻��ڷ�.set����(����);

				if (!dao.update(������������׻��ڷ�)) {
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
	}

	public float �������������(int ���ӱ�����, int ������ݸ�ݾ�, int ������α�) {

		/*if (������ݸ�ݾ� == 0 && ������α� == 0) {
			return 0;
		} else {*/
			float ������������� = (float) ((������ݸ�ݾ� - ������α�) / (���ӱ����� * 1.0));
			
			System.out.println("���ӱ����� :" + ���ӱ����� + " ������ݸ�ݾ� :" + ������ݸ�ݾ� + " ������α� :" + ������α� + " ������������� :" + �������������);
			
			return �������������;
		}
	

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();
		String column = "�������������";
		String table = "�������������";

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {

			int ������ݸ�ݾ� = dao.������ݸ�ݾ�(�⵵, �а���);
			int ������α� = dao.������α�(�⵵, �а���);
			int ���ӱ����� = defaultQuery.���ӱ�����(�⵵, �а���);
			float set������������� = �������������(���ӱ�����, ������ݸ�ݾ�, ������α�);

			dao.update�������(�⵵, �а���, set�������������);

		}

		float ��� = defaultQuery.avg(�⵵, column, table);
		float ǥ������ = defaultQuery.std(�⵵, column, table);

		for (String �а��� : �а����) {
			float ������������� = dao.�������������(�⵵, �а���);
			float T���� = defaultClass.T����(�������������, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
