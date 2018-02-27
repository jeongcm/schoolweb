package ���Ի���Ȳ�ܺ�;

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

@WebServlet("/���Ի���Ȳ�ܺ�EditAction")

public class ���Ի���Ȳ�ܺ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	���Ի���Ȳ�ܺ�Dao dao = new ���Ի���Ȳ�ܺ�Dao();
	String �������и� = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			�������и� = request.getParameter("���и�");
			String �а��� = request.getParameter("�а���");
			String �Էºμ� = request.getParameter("�Էºμ�");
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
			int �����ڼ� = Integer.parseInt(request.getParameter("�����ڼ�"));
			int �����ο� = Integer.parseInt(request.getParameter("�����ο�"));

			float ���Ի������ = ���Ի������(�����ڼ�, �����ο�);

			���Ի���Ȳ�ܺ� ���Ի���Ȳ�ܺ� = new ���Ի���Ȳ�ܺ�();
			���Ի���Ȳ�ܺ�.set�Էºμ�(�Էºμ�);
			���Ի���Ȳ�ܺ�.set���и�(�������и�);
			���Ի���Ȳ�ܺ�.set���(���);
			���Ի���Ȳ�ܺ�.set�а���(�а���);
			���Ի���Ȳ�ܺ�.set�����ο�(�����ο�);
			���Ի���Ȳ�ܺ�.set�����ڼ�(�����ڼ�);
			���Ի���Ȳ�ܺ�.set���Ի������(���Ի������);
			
			PrintWriter out = response.getWriter();

			if (dao.insert(���Ի���Ȳ�ܺ�) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}
			
		} else if (oper.equals("cal") || oper.equals("editCal")) {
			String column = "���Ի������";
			String table = "���Ի���Ȳ�ܺ�";
			float ��� = defaultQuery.�ܺ�avg(column, table);
			float ǥ������ =defaultQuery.�ܺ�std(column, table);

			ArrayList<String> ���и�� = defaultQuery.�ܺδ��и��(table);
			for (String ���и� : ���и��) {
				float ���Ի������ = defaultQuery.�ܺκ���(���и�, column, table);
				float T���� = defaultClass.T����(���Ի������, ���, ǥ������);
				defaultQuery.�ܺ�updateT(T����, ���и�, table);
			}
		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();
			�������и� = request.getParameter("���и�");
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
			String �а��� = request.getParameter("�а���");
			int ���� = Integer.parseInt(request.getParameter("����"));
			int �����ڼ� = Integer.parseInt(request.getParameter("�����ڼ�"));
			int �����ο� = Integer.parseInt(request.getParameter("�����ο�"));

			float ���Ի������ = ���Ի������(�����ڼ�, �����ο�);
			���Ի���Ȳ�ܺ� ���Ի���Ȳ�ܺ� = new ���Ի���Ȳ�ܺ�();

			���Ի���Ȳ�ܺ�.set�а���(�а���);
			���Ի���Ȳ�ܺ�.set���(���);
			���Ի���Ȳ�ܺ�.set���и�(�������и�);
			���Ի���Ȳ�ܺ�.set�����ڼ�(�����ڼ�);
			���Ի���Ȳ�ܺ�.set�����ο�(�����ο�);
			���Ի���Ȳ�ܺ�.set���Ի������(���Ի������);
			���Ի���Ȳ�ܺ�.set����(����);

			if (dao.update(���Ի���Ȳ�ܺ�) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}

		} else if (oper.equals("del")) {
			int ���� = Integer.parseInt(request.getParameter("����"));
			dao.delete(����);

			String column = "���Ի������";
			String table = "���Ի���Ȳ�ܺ�";
			float ��� =defaultQuery.�ܺ�avg(column, table);
			float ǥ������ =defaultQuery.�ܺ�std(column, table);

			ArrayList<String> ���и�� = defaultQuery.�ܺδ��и��(table);
			for (String ���и� : ���и��) {
				float ���Ի������ = defaultQuery.�ܺκ���(���и�, column, table);
				float T���� = defaultClass.T����(���Ի������, ���, ǥ������);
				defaultQuery.�ܺ�updateT(T����, ���и�, table);
			}

		} else if (oper.equals("delAll")) {
			String �Էºμ� = request.getParameter("�Էºμ�");
			dao.dellAll(�Էºμ�);

		} else {

			System.out.println("�߸��� ���� �Դϴ�.");

		}
	}

	public float ���Ի������(int �����ڼ�, int �����ο�) {
		return (float) (�����ڼ� / (�����ο� * 1.0) * 100);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
