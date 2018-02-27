package �ߵ�Ż�����ܺ�;

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
import �ߵ�Ż�����ܺ�.�ߵ�Ż�����ܺ�Dao;

@WebServlet("/�ߵ�Ż�����ܺ�EditAction")

public class �ߵ�Ż�����ܺ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	�ߵ�Ż�����ܺ�Dao dao = new �ߵ�Ż�����ܺ�Dao();
	String �������и� = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			String �а��� = request.getParameter("�а���");
			String �Էºμ� = request.getParameter("�Էºμ�");
			�������и� = request.getParameter("���и�");
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
			int �����л��� = defaultClass.nullCheck(request.getParameter("�����л���")) ? 0
					: Integer.parseInt(request.getParameter("�����л���"));
			int Ÿ�а������� = defaultClass.nullCheck(request.getParameter("Ÿ�а�������")) ? 0
					: Integer.parseInt(request.getParameter("Ÿ�а�������"));
			int �̵�� = defaultClass.nullCheck(request.getParameter("�̵��")) ? 0
					: Integer.parseInt(request.getParameter("�̵��"));
			int �̺��� = defaultClass.nullCheck(request.getParameter("�̺���")) ? 0
					: Integer.parseInt(request.getParameter("�̺���"));
			int ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
					: Integer.parseInt(request.getParameter("����"));
			int �л��� = defaultClass.nullCheck(request.getParameter("�л���")) ? 0
					: Integer.parseInt(request.getParameter("�л���"));
			int ��Ÿ = defaultClass.nullCheck(request.getParameter("��Ÿ")) ? 0
					: Integer.parseInt(request.getParameter("��Ÿ"));

			int �� = ��(�̵��, �̺���, ����, �л���, ��Ÿ);

			float �ߵ�Ż�����ܺΰ� = �ߵ�Ż�����ܺ�(�����л���, ��);

			�ߵ�Ż�����ܺ� �ߵ�Ż�����ܺ� = new �ߵ�Ż�����ܺ�();

			�ߵ�Ż�����ܺ�.set�а���(�а���);
			�ߵ�Ż�����ܺ�.set���и�(�������и�);
			�ߵ�Ż�����ܺ�.set��(��);
			�ߵ�Ż�����ܺ�.set�����л���(�����л���);
			�ߵ�Ż�����ܺ�.set�̵��(�̵��);
			�ߵ�Ż�����ܺ�.set�̺���(�̺���);
			�ߵ�Ż�����ܺ�.set����(����);
			�ߵ�Ż�����ܺ�.setŸ�а�������(Ÿ�а�������);
			�ߵ�Ż�����ܺ�.set�л���(�л���);
			�ߵ�Ż�����ܺ�.set�Էºμ�(�Էºμ�);
			�ߵ�Ż�����ܺ�.set��Ÿ(��Ÿ);
			�ߵ�Ż�����ܺ�.set���(���);
			�ߵ�Ż�����ܺ�.set�ߵ�Ż����(�ߵ�Ż�����ܺΰ�);

			PrintWriter out = response.getWriter();

			if (dao.insert(�ߵ�Ż�����ܺ�) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}

		} else if (oper.equals("cal") || (oper.equals("editCal"))) {
			String column = "�ߵ�Ż����";
			String table = "�ߵ�Ż�����ܺ�";

			float ��� = defaultQuery.�ܺ�avg(column, table);
			float ǥ������ = defaultQuery.�ܺ�std(column, table);

			ArrayList<String> ���и�� = defaultQuery.�ܺδ��и��(table);
			for (String ���и� : ���и��) {
				float �ߵ�Ż���� = defaultQuery.�ܺκ���(���и�, column, table);
				float T���� = defaultClass.�ߵ�Ż����T����(�ߵ�Ż����, ���, ǥ������);
				defaultQuery.�ܺ�updateT(T����, ���и�, table);
			}
		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();

			String ���и� = request.getParameter("���и�");
			String �а��� = request.getParameter("�а���");
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
			int ���� = Integer.parseInt(request.getParameter("����"));
			int �����л��� = defaultClass.nullCheck(request.getParameter("�����л���")) ? 0
					: Integer.parseInt(request.getParameter("�����л���"));
			int Ÿ�а������� = defaultClass.nullCheck(request.getParameter("Ÿ�а�������")) ? 0
					: Integer.parseInt(request.getParameter("Ÿ�а�������"));
			int �̵�� = defaultClass.nullCheck(request.getParameter("�̵��")) ? 0
					: Integer.parseInt(request.getParameter("�̵��"));
			int �̺��� = defaultClass.nullCheck(request.getParameter("�̺���")) ? 0
					: Integer.parseInt(request.getParameter("�̺���"));
			int ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
					: Integer.parseInt(request.getParameter("����"));
			int �л��� = defaultClass.nullCheck(request.getParameter("�л���")) ? 0
					: Integer.parseInt(request.getParameter("�л���"));
			int ��Ÿ = defaultClass.nullCheck(request.getParameter("��Ÿ")) ? 0
					: Integer.parseInt(request.getParameter("��Ÿ"));

			int �� = ��(�̵��, �̺���, ����, �л���, ��Ÿ);
			float �ߵ�Ż�����ܺΰ� = �ߵ�Ż�����ܺ�(�����л���, ��);

			�ߵ�Ż�����ܺ� �ߵ�Ż�����ܺ� = new �ߵ�Ż�����ܺ�();

			�ߵ�Ż�����ܺ�.set���и�(���и�);
			�ߵ�Ż�����ܺ�.set�а���(�а���);
			�ߵ�Ż�����ܺ�.set����(����);
			�ߵ�Ż�����ܺ�.set��(��);
			�ߵ�Ż�����ܺ�.set���(���);
			�ߵ�Ż�����ܺ�.set�����л���(�����л���);
			�ߵ�Ż�����ܺ�.set�̵��(�̵��);
			�ߵ�Ż�����ܺ�.setŸ�а�������(Ÿ�а�������);
			�ߵ�Ż�����ܺ�.set�̺���(�̺���);
			�ߵ�Ż�����ܺ�.set����(����);
			�ߵ�Ż�����ܺ�.set�л���(�л���);
			�ߵ�Ż�����ܺ�.set��Ÿ(��Ÿ);
			�ߵ�Ż�����ܺ�.set�ߵ�Ż����(�ߵ�Ż�����ܺΰ�);

			if (dao.update(�ߵ�Ż�����ܺ�) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}

		} else if (oper.equals("del")) {
			int ���� = Integer.parseInt(request.getParameter("����"));
			dao.delete(����);

			String column = "�ߵ�Ż����";
			String table = "�ߵ�Ż�����ܺ�";

			float ��� = defaultQuery.�ܺ�avg(column, table);
			float ǥ������ = defaultQuery.�ܺ�std(column, table);

			ArrayList<String> ���и�� = defaultQuery.�ܺδ��и��(table);
			for (String ���и� : ���и��) {
				float �ߵ�Ż���� = defaultQuery.�ܺκ���(���и�, column, table);
				float T���� = defaultClass.�ߵ�Ż����T����(�ߵ�Ż����, ���, ǥ������);
				defaultQuery.�ܺ�updateT(T����, ���и�, table);
			}

		} else if (oper.equals("delAll")) {
			String �Էºμ� = request.getParameter("�Էºμ�");
			dao.dellAll(�Էºμ�);

		} else {
			System.out.println("�߸��� ���� �Դϴ�.");

		}
	}

	public float �ߵ�Ż�����ܺ�(int �����л���, int ��) {
		return (float) (�� / (�����л��� * 1.0) * 100);
	}

	public int ��(int �̵��, int �̺���, int ����, int �л���, int ��Ÿ) {
		return �̵�� + �̺��� + ���� + �л��� + ��Ÿ;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
