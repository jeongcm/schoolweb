package ����ǽ�;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ����ǽ�.����ǽ�;
import ����ǽ�.����ǽ�Dao;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import defaultMethod.�⵵;

@WebServlet("/����ǽ�EditAction")
public class ����ǽ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		����ǽ�Dao dao = new ����ǽ�Dao();

		try {
			if (oper.equals("add")) {
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
				String �а��� = request.getParameter("�а���");

				int _1�б� = defaultClass.nullCheck(request.getParameter("_1�б�")) ? 0
						: Integer.parseInt(request.getParameter("_1�б�"));
				int _2�б� = defaultClass.nullCheck(request.getParameter("_2�б�")) ? 0
						: Integer.parseInt(request.getParameter("_2�б�"));
				int ���1�б� = defaultClass.nullCheck(request.getParameter("���1�б�")) ? 0
						: Integer.parseInt(request.getParameter("���1�б�"));
				int ���2�б� = defaultClass.nullCheck(request.getParameter("���2�б�")) ? 0
						: Integer.parseInt(request.getParameter("���2�б�"));
				String �Էºμ� = request.getParameter("�Էºμ�");
				int ����л��� = Integer.parseInt(request.getParameter("����л���"));

				int �հ� = _1�б� + _2�б�;
				int ����հ� = ���1�б� + ���2�б�;

				float �̼��л����� = �̼��л�����(�հ�, ����հ�, ����л���);

				����ǽ� ����ǽ� = new ����ǽ�();

				����ǽ�.set�⵵(�⵵);
				����ǽ�.set�а���(�а���);
				����ǽ�.set_1�б�(_1�б�);
				����ǽ�.set_2�б�(_2�б�);
				����ǽ�.set���1�б�(���1�б�);
				����ǽ�.set���2�б�(���2�б�);
				����ǽ�.set����л���(����л���);
				����ǽ�.set�̼��л�����(�̼��л�����);
				����ǽ�.set�Էºμ�(�Էºμ�);

				if (!dao.insert(����ǽ�)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				��������();

			}

			else if (oper.equals("edit")) {
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

					int _1�б� = defaultClass.nullCheck(request.getParameter("_1�б�")) ? 0
							: Integer.parseInt(request.getParameter("_1�б�"));
					int _2�б� = defaultClass.nullCheck(request.getParameter("_2�б�")) ? 0
							: Integer.parseInt(request.getParameter("_2�б�"));
					int ���1�б� = defaultClass.nullCheck(request.getParameter("���1�б�")) ? 0
							: Integer.parseInt(request.getParameter("���1�б�"));
					int ���2�б� = defaultClass.nullCheck(request.getParameter("���2�б�")) ? 0
							: Integer.parseInt(request.getParameter("���2�б�"));
					int ����л��� = Integer.parseInt(request.getParameter("����л���"));
					int ���� = Integer.parseInt(request.getParameter("����"));

					int �հ� = _1�б� + _2�б�;
					int ����հ� = ���1�б� + ���2�б�;

					float �̼��л����� = �̼��л�����(�հ�, ����հ�, ����л���);

					����ǽ� ����ǽ� = new ����ǽ�();
					����ǽ�.set�⵵(�⵵);
					����ǽ�.set�а���(�а���);
					����ǽ�.set_1�б�(_1�б�);
					����ǽ�.set_2�б�(_2�б�);
					����ǽ�.set���1�б�(���1�б�);
					����ǽ�.set���2�б�(���2�б�);
					����ǽ�.set����л���(����л���);
					����ǽ�.set�հ�(�հ�);
					����ǽ�.set����հ�(����հ�);
					����ǽ�.set�̼��л�����(�̼��л�����);
					����ǽ�.set����(����);

					if (!dao.update(����ǽ�)) {
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

	public float �̼��л�����(int �հ�, int ����հ�, int ����л���) {
		float result = (float) (((�հ� + (����հ� * 2)) / (����л��� * 1.0)) * 100);
		float �̼��л����� = (float)result;
		System.out.println("����հ� :"+����հ�+" ����л��� :"+����л���+" �̼��л� ����� :" + �̼��л�����);
		return result;
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "�̼��л�����";
		String table = "����ǽ�";

		float ��� = (float) defaultQuery.avg(�⵵, column, table);
		float ǥ������ = (float)defaultQuery.std(�⵵, column, table);
		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			float �̼��л����� = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(�̼��л�����, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}