package ����ڿܺ�;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultClass;
import �����Ȳ�ܺ�.�����Ȳ�ܺ�EditAction;

@WebServlet("/����ڿܺ�EditAction")

public class ����ڿܺ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 �����Ȳ�ܺ�EditAction ea=new �����Ȳ�ܺ�EditAction();
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		����ڿܺ�Dao dao = new ����ڿܺ�Dao();

		if (oper.equals("add")) {
			String �а��� = request.getParameter("�а���");
			String ���и� = request.getParameter("���и�");
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
			String �Էºμ� = request.getParameter("�Էºμ�");
			int �ǰ�����DB��������� = defaultClass.nullCheck(request.getParameter("�ǰ�����DB���������")) ? 0
					: Integer.parseInt(request.getParameter("�ǰ�����DB���������"));
			int �ؿ������ = defaultClass.nullCheck(request.getParameter("�ؿ������")) ? 0
					: Integer.parseInt(request.getParameter("�ؿ������"));
			int ���������� = defaultClass.nullCheck(request.getParameter("����������")) ? 0
					: Integer.parseInt(request.getParameter("����������"));
			int ����â��Ȱ�����缭 = defaultClass.nullCheck(request.getParameter("����â��Ȱ�����缭")) ? 0
					: Integer.parseInt(request.getParameter("����â��Ȱ�����缭"));
			int ����â���� = defaultClass.nullCheck(request.getParameter("����â����")) ? 0
					: Integer.parseInt(request.getParameter("����â����"));
			int �������� = defaultClass.nullCheck(request.getParameter("��������")) ? 0
					: Integer.parseInt(request.getParameter("��������"));

			int �� = �ǰ�����DB��������� + �ؿ������ + ���������� + ����â��Ȱ�����缭 + ����â���� + ��������;

			����ڿܺ� ����� = new ����ڿܺ�();

			�����.set���и�(���и�);
			�����.set�а���(�а���);
			�����.set���(���);
			�����.set��(��);
			�����.set�ؿ������(�ؿ������);
			�����.set�ǰ�����DB���������(�ǰ�����DB���������);
			�����.set����������(����������);
			�����.set�Էºμ�(�Էºμ�);
			�����.set����â��Ȱ�����缭(����â��Ȱ�����缭);
			�����.set����â����(����â����);
			�����.set��������(��������);

			PrintWriter out = response.getWriter();

			if (dao.insert(�����) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}

		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();

			String �а��� = request.getParameter("�а���");
			String ���и� = request.getParameter("���и�");
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
			int �ǰ�����DB��������� = defaultClass.nullCheck(request.getParameter("�ǰ�����DB���������")) ? 0
					: Integer.parseInt(request.getParameter("�ǰ�����DB���������"));
			int �ؿ������ = defaultClass.nullCheck(request.getParameter("�ؿ������")) ? 0
					: Integer.parseInt(request.getParameter("�ؿ������"));
			int ���������� = defaultClass.nullCheck(request.getParameter("����������")) ? 0
					: Integer.parseInt(request.getParameter("����������"));
			int �� = �ǰ�����DB��������� + �ؿ������ + ����������;
			int ���� = Integer.parseInt(request.getParameter("����"));
			int ����â��Ȱ�����缭 = defaultClass.nullCheck(request.getParameter("����â��Ȱ�����缭")) ? 0
					: Integer.parseInt(request.getParameter("����â��Ȱ�����缭"));
			int ����â���� = defaultClass.nullCheck(request.getParameter("����â����")) ? 0
					: Integer.parseInt(request.getParameter("����â����"));
			int �������� = defaultClass.nullCheck(request.getParameter("��������")) ? 0
					: Integer.parseInt(request.getParameter("��������"));

			����ڿܺ� ����� = new ����ڿܺ�();
			�����.set���и�(���и�);
			�����.set�а���(�а���);
			�����.set���(���);
			�����.set��(��);
			�����.set�ؿ������(�ؿ������);
			�����.set�ǰ�����DB���������(�ǰ�����DB���������);
			�����.set����������(����������);
			�����.set����(����);
			�����.set����â��Ȱ�����缭(����â��Ȱ�����缭);
			�����.set����â����(����â����);
			�����.set��������(��������);

			if (dao.update(�����) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}
		} else if (oper.equals("cal") || oper.equals("editCal")) {

			ea.��������();

		} else if (oper.equals("del")) {
			int ���� = Integer.parseInt(request.getParameter("����"));

			dao.delete(����);

			ea.��������();

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
