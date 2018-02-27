package �ܱ����л�����;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import �ܱ����л�����.�ܱ����л�����Dao;
import defaultMethod.defaultQuery;
import defaultMethod.�⵵;

@WebServlet("/�ܱ����л�����EditAction")
public class �ܱ����л�����EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	�ܱ����л�����Dao dao = new �ܱ����л�����Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		try {
			if (oper.equals("add")) {

				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));

				int �г� = Integer.parseInt(request.getParameter("�г�"));
				int �й� = Integer.parseInt(request.getParameter("�й�"));
				String ���� = request.getParameter("����");
				String ���� = request.getParameter("����");
				String ���� = request.getParameter("����");
				String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
				String �Էºμ� = request.getParameter("�Էºμ�");

				�ܱ����л����� �ܱ����л���Ȳ = new �ܱ����л�����();

				�ܱ����л���Ȳ.set�⵵(�⵵);
				�ܱ����л���Ȳ.set�а���(�а���);
				�ܱ����л���Ȳ.set�г�(�г�);
				�ܱ����л���Ȳ.set�й�(�й�);
				�ܱ����л���Ȳ.set����(����);
				�ܱ����л���Ȳ.set����(����);
				�ܱ����л���Ȳ.set����(����);
				�ܱ����л���Ȳ.set���(���);
				�ܱ����л���Ȳ.set�Էºμ�(�Էºμ�);

				if (!dao.insert(�ܱ����л���Ȳ)) {
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

					int �г� = Integer.parseInt(request.getParameter("�г�"));
					int �й� = Integer.parseInt(request.getParameter("�й�"));
					String ���� = request.getParameter("����");
					String ���� = request.getParameter("����");
					String ���� = request.getParameter("����");
					String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
					int ���� = Integer.parseInt(request.getParameter("����"));

					�ܱ����л����� �ܱ����л���Ȳ = new �ܱ����л�����();

					�ܱ����л���Ȳ.set�⵵(�⵵);
					�ܱ����л���Ȳ.set�а���(�а���);
					�ܱ����л���Ȳ.set�г�(�г�);
					�ܱ����л���Ȳ.set�й�(�й�);
					�ܱ����л���Ȳ.set����(����);
					�ܱ����л���Ȳ.set����(����);
					�ܱ����л���Ȳ.set����(����);
					�ܱ����л���Ȳ.set���(���);
					�ܱ����л���Ȳ.set����(����);

					if (!dao.update(�ܱ����л���Ȳ)) {
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

	public float �ܱ����л�����(int �ܱ����л���, int ���л���) {
		if (�ܱ����л��� == 0 || ���л��� == 0)
			return 0;
		else {
			float �ܱ����л����� = (float) (�ܱ����л��� / (���л��� * 1.0) * 100.0) ;
			System.out.println("�ܱ����л��� :" + �ܱ����л��� + " ���л���: " + ���л��� + " �ܱ����л����� ��� ��� :" + �ܱ����л�����);
			return �ܱ����л�����;
		}
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "�ܱ����л�����";
		String table = "�ܱ����л�����";

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а� : �а����) {
			int �ܱ����л��� = dao.�ܱ����л���(�⵵, �а�);
			int ���л��� = defaultQuery.���л���(�⵵, �а�);

			float �ܱ����л�������� = �ܱ����л�����(�ܱ����л���, ���л���);

			dao.update�ܱ����л�����(�⵵, �а�, �ܱ����л��������);
		}

		float ��� = (float) defaultQuery.avg(�⵵, column, table);
		float ǥ������ = (float)defaultQuery.std(�⵵, column, table);
		
		for (String �а� : �а����) {
			float �ܱ����л����� = defaultQuery.����(�⵵, �а�, column, table);
			float T���� = defaultClass.T����(�ܱ����л�����, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а�, table);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}