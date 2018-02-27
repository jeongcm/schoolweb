package �������α׷���������;

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
import �������α׷���������.�������α׷���������Dao;

@WebServlet("/�������α׷���������EditAction")
public class �������α׷���������EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	�������α׷���������Dao dao = new �������α׷���������Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		try {
			if (oper.equals("add")) {
				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
				String ��μ��� = request.getParameter("��μ���");
				String ���α׷��� = request.getParameter("���α׷���");
				String �Էºμ� = request.getParameter("�Էºμ�");
				int �г� = Integer.parseInt(request.getParameter("�г�"));
				String �й� = request.getParameter("�й�");
				String ���� = request.getParameter("����");
				String ��� = request.getParameter("���");

				�������α׷��������� �������α׷������������ = new �������α׷���������();

				�������α׷������������.set�⵵(�⵵);
				�������α׷������������.set�а���(�а���);

				�������α׷������������.set��μ���(��μ���);
				�������α׷������������.set���α׷���(���α׷���);
				�������α׷������������.set�г�(�г�);
				�������α׷������������.set�й�(�й�);
				�������α׷������������.set����(����);
				�������α׷������������.set���(���);
				�������α׷������������.set�Էºμ�(�Էºμ�);

				if (!dao.insert(�������α׷������������)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.flush();
					out.close();
				} else {
					dao.���Ƹ���������insert(�⵵, �а���);
				}
			} else if (oper.equals("edit")) {
				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));

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
					String ��μ��� = request.getParameter("��μ���");
					String ���α׷��� = request.getParameter("���α׷���");
					int �г� = Integer.parseInt(request.getParameter("�г�"));
					String �й� = request.getParameter("�й�");
					String ���� = request.getParameter("����");
					String ��� = request.getParameter("���");
					int ���� = Integer.parseInt(request.getParameter("����"));

					�������α׷��������� �������α׷��������� = new �������α׷���������();

					�������α׷���������.set�⵵(�⵵);
					�������α׷���������.set�а���(�а���);
					�������α׷���������.set��μ���(��μ���);
					�������α׷���������.set���α׷���(���α׷���);
					�������α׷���������.set�г�(�г�);
					�������α׷���������.set�й�(�й�);
					�������α׷���������.set����(����);
					�������α׷���������.set���(���);
					�������α׷���������.set����(����);

					if (!dao.update(�������α׷���������)) {
						out.write("fail");
						out.flush();
						out.close();
					}
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
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

	public float �������α׷�����(int ��, int ���л���) {
		float result = (float) �� / ���л���;

		System.out.println("�հ� :" + �� + " ���л���: " + ���л��� + " ���� ���α׷� ���� ��� ���:" + result);

		return (float) ((result) * 100) ;
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "�������α׷���������";
		String table = "�������α׷�����";

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {

			int �� = dao.��(�⵵, �а���);
			int ���л��� = defaultQuery.���л���(�⵵, �а���);

			float �������α׷�������� = �������α׷�����(��, ���л���);

			dao.update�������α׷�����(�⵵, �а���, �������α׷��������);
		}
		float ��� = (float) defaultQuery.avg(�⵵, column, table);
		float ǥ������ = (float) defaultQuery.std(�⵵, column, table);

		for (String �а��� : �а����) {
			float �������α׷��������� = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(�������α׷���������, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}