package ���������;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultQuery;
import �����Ȳ.�����ȲDao;
import �����Ȳ.�����ȲEditAction;

@WebServlet("/���������EditAction")

public class ���������EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	�����ȲDao ��Ȳdao = new �����ȲDao();
	�����ȲEditAction ea = new �����ȲEditAction();
	���������Dao dao = new ���������Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			String �Էºμ� = request.getParameter("�Էºμ�");
			String �а��� = request.getParameter("�а���");
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			int ������ = nullCheck(request.getParameter("������")) ? 0 : Integer.parseInt(request.getParameter("������"));
			int �Դ��� = nullCheck(request.getParameter("�Դ���")) ? 0 : Integer.parseInt(request.getParameter("�Դ���"));
			int ����Ұ����� = nullCheck(request.getParameter("����Ұ�����")) ? 0
					: Integer.parseInt(request.getParameter("����Ұ�����"));
			int �ܱ������л� = nullCheck(request.getParameter("�ܱ������л�")) ? 0
					: Integer.parseInt(request.getParameter("�ܱ������л�"));
			int �ǰ��������尡�����ܴ�� = nullCheck(request.getParameter("�ǰ��������尡�����ܴ��")) ? 0
					: Integer.parseInt(request.getParameter("�ǰ��������尡�����ܴ��"));
			int ���д�ñ������ = nullCheck(request.getParameter("���д�ñ������")) ? 0
					: Integer.parseInt(request.getParameter("���д�ñ������"));

			int �� = ��(������, �Դ���, ����Ұ�����, �ܱ������л�, �ǰ��������尡�����ܴ��, ���д�ñ������);
			��������� ��������� = new ���������();

			���������.set�⵵(�⵵);
			���������.set�а���(�а���);
			���������.set��(��);
			���������.set�Դ���(�Դ���);
			���������.set������(������);
			���������.set����Ұ�����(����Ұ�����);
			���������.set�ܱ������л�(�ܱ������л�);
			���������.set�ǰ��������尡�����ܴ��(�ǰ��������尡�����ܴ��);
			���������.set�Էºμ�(�Էºμ�);
			���������.set���д�ñ������(���д�ñ������);

			PrintWriter out = response.getWriter();

			if (dao.insert(���������) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}
		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String �а��� = request.getParameter("�а���");

			String �а���üũ = defaultQuery.�а���üũ(�а���);
			String �⵵üũ = defaultQuery.�⵵üũ(�⵵);

			if (�а���üũ.equals("0")) {
				out.write("failMajor");
				out.flush();
				out.close();
			} else if (�⵵üũ.equals("0")) {
				out.write("failYear");
				out.flush();
				out.close();
			} else {
				int ���� = Integer.parseInt(request.getParameter("����"));
				int ������ = nullCheck(request.getParameter("������")) ? 0 : Integer.parseInt(request.getParameter("������"));
				int �Դ��� = nullCheck(request.getParameter("�Դ���")) ? 0 : Integer.parseInt(request.getParameter("�Դ���"));
				int ����Ұ����� = nullCheck(request.getParameter("����Ұ�����")) ? 0
						: Integer.parseInt(request.getParameter("����Ұ�����"));
				int �ܱ������л� = nullCheck(request.getParameter("�ܱ������л�")) ? 0
						: Integer.parseInt(request.getParameter("�ܱ������л�"));
				int �ǰ��������尡�����ܴ�� = nullCheck(request.getParameter("�ǰ��������尡�����ܴ��")) ? 0
						: Integer.parseInt(request.getParameter("�ǰ��������尡�����ܴ��"));
				int ���д�ñ������ = nullCheck(request.getParameter("���д�ñ������")) ? 0
						: Integer.parseInt(request.getParameter("���д�ñ������"));
				int �� = ��(������, �Դ���, ����Ұ�����, �ܱ������л�, �ǰ��������尡�����ܴ��, ���д�ñ������);

				��������� ��������� = new ���������();

				���������.set����(����);
				���������.set�а���(�а���);
				���������.set�⵵(�⵵);
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
			ea.��������();
		} else {
			System.out.println("�߸��� ���� �Դϴ�.");
		}
	}

	public boolean nullCheck(String value) {
		if (null == value || value.isEmpty()) {
			return true;
		}
		return false;
	}

	public int ��(int ������, int �Դ���, int ����Ұ�����, int �ܱ������л�, int �ǰ��������尡�����ܴ��, int ���д�ñ������) {
		return ������ + �Դ��� + ����Ұ����� + �ܱ������л� + �ǰ��������尡�����ܴ�� + ���д�ñ������;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
