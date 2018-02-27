package ��������ڿܺ�;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultClass;
import �����Ȳ�ܺ�.�����Ȳ�ܺ�EditAction;

@WebServlet("/��������ڿܺ�EditAction")

public class ��������ڿܺ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	�����Ȳ�ܺ�EditAction ea=new �����Ȳ�ܺ�EditAction();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		��������ڿܺ�Dao dao = new ��������ڿܺ�Dao();

		if (oper.equals("add")) {
			String �а��� = request.getParameter("�а���");
			String ���и� = request.getParameter("���и�");
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
			String �Էºμ� = request.getParameter("�Էºμ�");
			int ������ = defaultClass.nullCheck(request.getParameter("������")) ? 0
					: Integer.parseInt(request.getParameter("������"));
			int �Դ��� = defaultClass.nullCheck(request.getParameter("�Դ���")) ? 0
					: Integer.parseInt(request.getParameter("�Դ���"));
			int ����Ұ����� = defaultClass.nullCheck(request.getParameter("����Ұ�����")) ? 0
					: Integer.parseInt(request.getParameter("����Ұ�����"));
			int �ܱ������л� = defaultClass.nullCheck(request.getParameter("�ܱ������л�")) ? 0
					: Integer.parseInt(request.getParameter("�ܱ������л�"));
			int �ǰ��������尡�����ܴ�� = defaultClass.nullCheck(request.getParameter("�ǰ��������尡�����ܴ��")) ? 0
					: Integer.parseInt(request.getParameter("�ǰ��������尡�����ܴ��"));
			int ���д�ñ������ = defaultClass.nullCheck(request.getParameter("���д�ñ������")) ? 0
					: Integer.parseInt(request.getParameter("���д�ñ������"));

			int �� = ������ + �Դ��� + ����Ұ����� + �ܱ������л� + �ǰ��������尡�����ܴ�� + ���д�ñ������;

			��������ڿܺ� ��������� = new ��������ڿܺ�();

			���������.set���(���);
			���������.set���и�(���и�);
			���������.set�а���(�а���);
			���������.set��(��);
			���������.set�Դ���(�Դ���);
			���������.set������(������);
			���������.set����Ұ�����(����Ұ�����);
			���������.set�ܱ������л�(�ܱ������л�);
			���������.set�ǰ��������尡�����ܴ��(�ǰ��������尡�����ܴ��);
			���������.set���д�ñ������(���д�ñ������);
			���������.set�Էºμ�(�Էºμ�);

			PrintWriter out = response.getWriter();

			if (dao.insert(���������) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}
		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();

			String �а��� = request.getParameter("�а���");
			String ���и� = request.getParameter("���и�");
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
			int ���� = Integer.parseInt(request.getParameter("����"));
			int ������ = defaultClass.nullCheck(request.getParameter("������")) ? 0
					: Integer.parseInt(request.getParameter("������"));
			int �Դ��� = defaultClass.nullCheck(request.getParameter("�Դ���")) ? 0
					: Integer.parseInt(request.getParameter("�Դ���"));
			int ����Ұ����� = defaultClass.nullCheck(request.getParameter("����Ұ�����")) ? 0
					: Integer.parseInt(request.getParameter("����Ұ�����"));
			int �ܱ������л� = defaultClass.nullCheck(request.getParameter("�ܱ������л�")) ? 0
					: Integer.parseInt(request.getParameter("�ܱ������л�"));
			int �ǰ��������尡�����ܴ�� = defaultClass.nullCheck(request.getParameter("�ǰ��������尡�����ܴ��")) ? 0
					: Integer.parseInt(request.getParameter("�ǰ��������尡�����ܴ��"));
			int ���д�ñ������ = defaultClass.nullCheck(request.getParameter("���д�ñ������")) ? 0
					: Integer.parseInt(request.getParameter("���д�ñ������"));

			int �� = ������ + �Դ��� + ����Ұ����� + �ܱ������л� + �ǰ��������尡�����ܴ�� + ���д�ñ������;

			��������ڿܺ� ��������� = new ��������ڿܺ�();

			���������.set����(����);
			���������.set���(���);
			���������.set�а���(�а���);
			���������.set���и�(���и�);
			���������.set��(��);
			���������.set������(������);
			���������.set����Ұ�����(����Ұ�����);
			���������.set�ܱ������л�(�ܱ������л�);
			���������.set�ǰ��������尡�����ܴ��(�ǰ��������尡�����ܴ��);
			���������.set���д�ñ������(���д�ñ������);
			���������.set�Դ���(�Դ���);

			if (dao.update(���������) == false) {
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
