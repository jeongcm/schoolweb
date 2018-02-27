package ������ܺ�;

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
import ������ܺ�.������ܺ�Dao;
import ������.������EditAction;

@WebServlet("/������ܺ�EditAction")

public class ������ܺ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	������EditAction ea = new ������EditAction();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		������ܺ�Dao dao = new ������ܺ�Dao();

		try {
			if (oper.equals("add")) {
				String �а��� = request.getParameter("�а���");
				String ���и� = request.getParameter("���и�");
				int ���ӱ����� = defaultClass.nullCheck(request.getParameter("���ӱ�����")) ? 0
						: Integer.parseInt(request.getParameter("���ӱ�����"));
				int �߾����� = defaultClass.nullCheck(request.getParameter("�߾�����")) ? 0
						: Integer.parseInt(request.getParameter("�߾�����"));
				int ����ü = defaultClass.nullCheck(request.getParameter("����ü")) ? 0
						: Integer.parseInt(request.getParameter("����ü"));
				int �ΰ� = defaultClass.nullCheck(request.getParameter("�ΰ�")) ? 0
						: Integer.parseInt(request.getParameter("�ΰ�"));
				int �ܱ� = defaultClass.nullCheck(request.getParameter("�ܱ�")) ? 0
						: Integer.parseInt(request.getParameter("�ܱ�"));
				int �� = ��(�߾�����, ����ü, �ΰ�, �ܱ�);
				String �Էºμ� = request.getParameter("�Էºμ�");
				String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");

				float ������ = ������ܺ�(���ӱ�����, ��);

				������ܺ� ������ܺ�bean = new ������ܺ�();

				������ܺ�bean.set���и�(���и�);
				������ܺ�bean.set�а���(�а���);
				������ܺ�bean.set����ü(����ü);
				������ܺ�bean.set�߾�����(�߾�����);
				������ܺ�bean.set�ΰ�(�ΰ�);
				������ܺ�bean.set�ܱ�(�ܱ�);
				������ܺ�bean.set������(������);
				������ܺ�bean.set��(��);
				������ܺ�bean.set�Էºμ�(�Էºμ�);
				������ܺ�bean.set���(���);
				������ܺ�bean.set���ӱ�����(���ӱ�����);

				if (!dao.insert(������ܺ�bean)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();

				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				��������();

			} else if (oper.equals("edit")) {
				PrintWriter out = response.getWriter();
				int ���� = Integer.parseInt(request.getParameter("����"));
				String �а��� = request.getParameter("�а���");
				String ���и� = request.getParameter("���и�");
				int ���ӱ����� = defaultClass.nullCheck(request.getParameter("���ӱ�����")) ? 0
						: Integer.parseInt(request.getParameter("���ӱ�����"));
				int �߾����� = defaultClass.nullCheck(request.getParameter("�߾�����")) ? 0
						: Integer.parseInt(request.getParameter("�߾�����"));
				int ����ü = defaultClass.nullCheck(request.getParameter("����ü")) ? 0
						: Integer.parseInt(request.getParameter("����ü"));
				int �ΰ� = defaultClass.nullCheck(request.getParameter("�ΰ�")) ? 0
						: Integer.parseInt(request.getParameter("�ΰ�"));
				int �ܱ� = defaultClass.nullCheck(request.getParameter("�ܱ�")) ? 0
						: Integer.parseInt(request.getParameter("�ܱ�"));
				int �� = ��(�߾�����, ����ü, �ΰ�, �ܱ�);
				String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");

				float ������ = ������ܺ�(���ӱ�����, ��);

				������ܺ� ������ܺ�bean = new ������ܺ�();

				������ܺ�bean.set����(����);
				������ܺ�bean.set�а���(�а���);
				������ܺ�bean.set���и�(���и�);
				������ܺ�bean.set����ü(����ü);
				������ܺ�bean.set�߾�����(�߾�����);
				������ܺ�bean.set�ΰ�(�ΰ�);
				������ܺ�bean.set�ܱ�(�ܱ�);
				������ܺ�bean.set������(������);
				������ܺ�bean.set���ӱ�����(���ӱ�����);
				������ܺ�bean.set���(���);
				������ܺ�bean.set��(��);

				if (!dao.update(������ܺ�bean)) {
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

			} else {

				System.out.println("�߸��� ���� �Դϴ�.");

			}
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

	public float ������ܺ�(int ���ӱ�����, int ��) {
		return (float) (�� / (���ӱ����� * 1.0));
	}

	public int ��(int �߾�����, int ����ü, int �ΰ�, int �ܱ�) {
		return �߾����� + ����ü + �ΰ� + �ܱ�;
	}

	public void ��������() {
		String column = "������";
		String table = "������ܺ�";

		ArrayList<String> ���и�� = defaultQuery.�ܺδ��и��(table);

		for (String ���и� : ���и��) {
			float ��� = defaultQuery.�ܺ�avg(column, table);
			float ǥ������ = defaultQuery.�ܺ�std(column, table);

			float ����������� = defaultQuery.�ܺκ���(���и�, column, table);
			float T���� = defaultClass.T����(�����������, ���, ǥ������);
			defaultQuery.�ܺ�updateT(T����, ���и�, table);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}