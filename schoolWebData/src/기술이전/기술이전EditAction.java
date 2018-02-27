package �������;

import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import �������.�������;
import �������.�������Dao;

import Ư����.Ư����EditAction;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;

@WebServlet("/�������EditAction")
public class �������EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		�������Dao dao = new �������Dao();
		Ư����EditAction ea = new Ư����EditAction();

		int �����⵵ = 0;
		String �����а��� = null;

		if (oper.equals("add")) {

			�����⵵ = Integer.parseInt(request.getParameter("�⵵"));
			�����а��� = request.getParameter("�а���");

			String ��ǥ�߸��� = request.getParameter("��ǥ�߸���");
			String �������� = request.getParameter("��������");
			String �Էºμ� = request.getParameter("�Էºμ�");
			float ���ױ���� = defaultClass.nullCheck(request.getParameter("���ױ����")) ? 0
					: Float.parseFloat(request.getParameter("���ױ����"));

			������� ������� = new �������();

			�������.set�⵵(�����⵵);
			�������.set�а���(�����а���);
			�������.set��ǥ�߸���(��ǥ�߸���);
			�������.set��������(��������);
			�������.set���ױ����(���ױ����);
			�������.set�Էºμ�(�Էºμ�);

			if (!dao.insert(�������)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		} else if (oper.equals("cal") || oper.equals("editCal")) {

			ea.��������();

		} else if (oper.equals("edit")) {
			�����⵵ = Integer.parseInt(request.getParameter("�⵵"));
			�����а��� = request.getParameter("�а���");

			PrintWriter out = response.getWriter();

			if (defaultQuery.�а���üũ(�����а���).equals("0")) {

				out.write("failMajor");
				out.flush();
				out.close();

			} else if (defaultQuery.�⵵üũ(�����⵵).equals("0")) {

				out.write("failYear");
				out.flush();
				out.close();
			} else {

				String ��ǥ�߸��� = request.getParameter("��ǥ�߸���");
				String �������� = request.getParameter("��������");
				float ���ױ���� = defaultClass.nullCheck(request.getParameter("���ױ����")) ? 0
						: Float.parseFloat(request.getParameter("���ױ����"));
				int ���� = Integer.parseInt(request.getParameter("����"));

				������� ������� = new �������();

				�������.set�⵵(�����⵵);
				�������.set�а���(�����а���);
				�������.set��ǥ�߸���(��ǥ�߸���);
				�������.set��������(��������);
				�������.set���ױ����(���ױ����);
				�������.set����(����);

				if (!dao.update(�������)) {
					out.write("fail");
					out.flush();
					out.close();
				}
			}
		} else if (oper.equals("del")) {
			int ���� = Integer.parseInt(request.getParameter("����"));
			dao.delete(����);

			ea.��������();
		} else if (oper.equals("delAll")) {
			String �Էºμ� = request.getParameter("�Էºμ�");
			dao.dellAll(�Էºμ�);

			ea.��������();
		} else {
			System.out.println("�߸��� �����Դϴ�.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
