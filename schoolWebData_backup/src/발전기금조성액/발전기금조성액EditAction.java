package �������������;

import java.io.IOException;

import java.io.PrintWriter;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import �������������.�������������Dao;
import ������������׻��ڷ�.������������׻��ڷ�EditAction;
import defaultMethod.defaultQuery;

@WebServlet("/�������������EditAction")
public class �������������EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	�������������Dao dao = new �������������Dao();
	������������׻��ڷ�EditAction ea = new ������������׻��ڷ�EditAction();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		if (oper.equals("add")) {

			String �а��� = request.getParameter("�а���");
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));

			int ������α� = defaultClass.nullCheck(request.getParameter("������α�")) ? 0
					: Integer.parseInt(request.getParameter("������α�"));

			int ���ӱ����� = defaultQuery.���ӱ�����(�⵵, �а���);
			int ������ݸ�ݾ� = dao.������ݸ�ݾ�(�⵵, �а���);

			String �Էºμ� = request.getParameter("�Էºμ�");

			float ������������װ�� = ea.�������������(���ӱ�����, ������ݸ�ݾ�, ������α�);

			������������� ������������� = new �������������();

			�������������.set�⵵(�⵵);
			�������������.set�а���(�а���);
			�������������.set������α�(������α�);
			�������������.set�Էºμ�(�Էºμ�);
			�������������.set������������װ��(������������װ��);

			if (!dao.insert(�������������)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		} else if (oper.equals("cal") || oper.equals("editCal")) {

			ea.��������();

		} else if (oper.equals("edit")) {
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String �а��� = request.getParameter("�а���");

			int ������α� = defaultClass.nullCheck(request.getParameter("������α�")) ? 0
					: Integer.parseInt(request.getParameter("������α�"));
			int ���ӱ����� = defaultQuery.���ӱ�����(�⵵, �а���);
			int ������ݸ�ݾ� = dao.������ݸ�ݾ�(�⵵, �а���);
			int ���� = Integer.parseInt(request.getParameter("����"));

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

				������������� ������������� = new �������������();

				float ������������װ�� = ea.�������������(���ӱ�����, ������ݸ�ݾ�, ������α�);

				�������������.set������α�(������α�);
				�������������.set������������װ��(������������װ��);
				�������������.set�⵵(�⵵);
				�������������.set�а���(�а���);
				�������������.set����(����);

				if (!dao.update(�������������)) {
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
			System.out.println("�߸��� ���� �Դϴ�.");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}