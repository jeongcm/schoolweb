package ���л���Ȳ2;

import java.io.IOException;

import java.io.PrintWriter;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ���л���Ȳ2.���л���ȲDao2;
import defaultMethod.defaultQuery;

@WebServlet("/���л���ȲEditAction2")
public class ���л���ȲEditAction2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int �����⵵ = 0;
	String �����а��� = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		���л���ȲDao2 dao = new ���л���ȲDao2();
		if (oper.equals("add")) {

			�����а��� = request.getParameter("�а���");
			�����⵵ = Integer.parseInt(request.getParameter("�⵵"));

			int �л��� = defaultClass.nullCheck(request.getParameter("�л���")) ? 0
					: Integer.parseInt(request.getParameter("�л���"));
			String �Էºμ� = request.getParameter("�Էºμ�");

			���л���Ȳ2 ���л���Ȳ = new ���л���Ȳ2();

			���л���Ȳ.set�а���(�����а���);
			���л���Ȳ.set�⵵(�����⵵);
			���л���Ȳ.set�л���(�л���);
			���л���Ȳ.set�Էºμ�(�Էºμ�);

			if (!dao.insert(���л���Ȳ)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		} else if (oper.equals("edit")) {
			�����а��� = request.getParameter("�а���");
			�����⵵ = Integer.parseInt(request.getParameter("�⵵"));

			int �л��� = defaultClass.nullCheck(request.getParameter("�л���")) ? 0
					: Integer.parseInt(request.getParameter("�л���"));
			int ���� = Integer.parseInt(request.getParameter("����"));

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

				���л���Ȳ2 ���л���Ȳ = new ���л���Ȳ2();

				���л���Ȳ.set�⵵(�����⵵);
				���л���Ȳ.set�а���(�����а���);
				���л���Ȳ.set�л���(�л���);
				���л���Ȳ.set����(����);

				if (!dao.update(���л���Ȳ)) {
					out.write("fail");
					out.flush();
					out.close();
				}
			}
		} else if (oper.equals("del")) {
			int ���� = Integer.parseInt(request.getParameter("����"));
			dao.delete(����);

		} else if (oper.equals("delAll")) {
			String �Էºμ� = request.getParameter("�Էºμ�");
			dao.dellAll(�Էºμ�);

		} else {
			System.out.println("�߸��� ���� �Դϴ�.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}