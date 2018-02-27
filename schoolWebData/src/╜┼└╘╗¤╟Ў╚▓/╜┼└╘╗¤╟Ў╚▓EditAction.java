package ���Ի���Ȳ;

import java.io.IOException;
import defaultMethod.�⵵;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import ���Ի���Ȳ.���Ի���ȲDao;

@WebServlet("/���Ի���ȲEditAction")

public class ���Ի���ȲEditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	���Ի���ȲDao dao = new ���Ի���ȲDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String �а��� = request.getParameter("�а���");
			int �����ڼ� = Integer.parseInt(request.getParameter("�����ڼ�"));
			int �����ο� = Integer.parseInt(request.getParameter("�����ο�"));
			String �Էºμ� = request.getParameter("�Էºμ�");
			float ���Ի������ = ���Ի������(�����ڼ�, �����ο�);

			���Ի���Ȳ ���Ի���Ȳbean = new ���Ի���Ȳ();

			���Ի���Ȳbean.set�⵵(�⵵);
			���Ի���Ȳbean.set�а���(�а���);
			���Ի���Ȳbean.set�����ο�(�����ο�);
			���Ի���Ȳbean.set�����ڼ�(�����ڼ�);
			���Ի���Ȳbean.set���Ի������(���Ի������);
			���Ի���Ȳbean.set�Էºμ�(�Էºμ�);
			PrintWriter out = response.getWriter();

			if (!dao.insert(���Ի���Ȳbean)) {
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
			int �����ڼ� = Integer.parseInt(request.getParameter("�����ڼ�"));
			int �����ο� = Integer.parseInt(request.getParameter("�����ο�"));
			int ���� = Integer.parseInt(request.getParameter("����"));
			if (defaultQuery.�а���üũ(�а���).equals("0")) {
				out.write("failMajor");
				out.flush();
				out.close();
			} else if (defaultQuery.�⵵üũ(�⵵).equals("0")) {
				out.write("failYear");
				out.flush();
				out.close();
			} else {

				float ���Ի������ = ���Ի������(�����ڼ�, �����ο�);
				���Ի���Ȳ ���Ի���Ȳ = new ���Ի���Ȳ();

				���Ի���Ȳ.set�а���(�а���);
				���Ի���Ȳ.set�⵵(�⵵);
				���Ի���Ȳ.set�����ڼ�(�����ڼ�);
				���Ի���Ȳ.set�����ο�(�����ο�);
				���Ի���Ȳ.set���Ի������(���Ի������);
				���Ի���Ȳ.set����(����);

				if (!dao.update(���Ի���Ȳ)) {
					out.write("fail");
					out.flush();
					out.close();
				} else {
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

	public float ���Ի������(int �����ڼ�, int �����ο�) {
		float result = (float) (�����ڼ� / (�����ο�*1.0));
		float ���Ի������ = result*100;
		System.out.println("�����ڼ� :" + �����ڼ� + " �����Կ� :" + �����ο�);
		return ���Ի������;
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "���Ի������";
		String table = "���Ի���Ȳ";
		
		float ��� = defaultQuery.avg(�⵵, column, table);
		float ǥ������ =defaultQuery.std(�⵵, column, table);

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);
		for (String �а��� : �а����) {
			float ���Ի������ = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(���Ի������, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}