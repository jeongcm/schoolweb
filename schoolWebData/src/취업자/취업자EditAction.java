package �����;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import �����Ȳ.�����ȲDao;
import �����Ȳ.�����ȲEditAction;

@WebServlet("/�����EditAction")

public class �����EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	�����ȲDao ��Ȳdao = new �����ȲDao();
	�����ȲEditAction ea = new �����ȲEditAction();
	�����Dao dao = new �����Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			String �а��� = request.getParameter("�а���");
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			int �ǰ�����DB��������� = defaultClass.nullCheck(request.getParameter("�ǰ�����DB���������")) ? 0
					: Integer.parseInt(request.getParameter("�ǰ�����DB���������"));
			int �ؿ������ = defaultClass.nullCheck(request.getParameter("�ؿ������")) ? 0
					: Integer.parseInt(request.getParameter("�ؿ������"));
			int ���������� = defaultClass.nullCheck(request.getParameter("����������")) ? 0
					: Integer.parseInt(request.getParameter("����������"));
			int ��������� = defaultClass.nullCheck(request.getParameter("���������")) ? 0
					: Integer.parseInt(request.getParameter("���������"));
			int ����â��Ȱ�����缭 = defaultClass.nullCheck(request.getParameter("����â��Ȱ�����缭")) ? 0
					: Integer.parseInt(request.getParameter("����â��Ȱ�����缭"));
			int ����â���� = defaultClass.nullCheck(request.getParameter("����â����")) ? 0
					: Integer.parseInt(request.getParameter("����â����"));
			int �������� = defaultClass.nullCheck(request.getParameter("��������")) ? 0
					: Integer.parseInt(request.getParameter("��������"));
			int �� = �ǰ�����DB��������� + �ؿ������ + ����������+����â��Ȱ�����缭+����â����+��������;
			String �Էºμ� = request.getParameter("�Էºμ�");

			����� ����� = new �����();

			�����.set�⵵(�⵵);
			�����.set�а���(�а���);
			�����.set��(��);
			�����.set�ؿ������(�ؿ������);
			�����.set�ǰ�����DB���������(�ǰ�����DB���������);
			�����.set����������(����������);
			�����.set���������(���������);
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
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));

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

				int �ǰ�����DB��������� = defaultClass.nullCheck(request.getParameter("�ǰ�����DB���������")) ? 0
						: Integer.parseInt(request.getParameter("�ǰ�����DB���������"));
				int �ؿ������ = defaultClass.nullCheck(request.getParameter("�ؿ������")) ? 0
						: Integer.parseInt(request.getParameter("�ؿ������"));
				int ���������� = defaultClass.nullCheck(request.getParameter("����������")) ? 0
						: Integer.parseInt(request.getParameter("����������"));
				int ��������� = defaultClass.nullCheck(request.getParameter("���������")) ? 0
						: Integer.parseInt(request.getParameter("���������"));
				int ���� = Integer.parseInt(request.getParameter("����"));
				int ����â��Ȱ�����缭 = defaultClass.nullCheck(request.getParameter("����â��Ȱ�����缭")) ? 0
						: Integer.parseInt(request.getParameter("����â��Ȱ�����缭"));
				int ����â���� = defaultClass.nullCheck(request.getParameter("����â����")) ? 0
						: Integer.parseInt(request.getParameter("����â����"));
				int �������� = defaultClass.nullCheck(request.getParameter("��������")) ? 0
						: Integer.parseInt(request.getParameter("��������"));
				int �� = �ǰ�����DB��������� + �ؿ������ + ����������+����â��Ȱ�����缭+����â����+��������;
				
				����� ����� = new �����();
				�����.set�⵵(�⵵);
				�����.set�а���(�а���);
				�����.set��(��);
				�����.set�ؿ������(�ؿ������);
				�����.set�ǰ�����DB���������(�ǰ�����DB���������);
				�����.set����������(����������);
				�����.set���������(���������);
				�����.set����(����);
				�����.set����â��Ȱ�����缭(����â��Ȱ�����缭);
				�����.set����â����(����â����);
				�����.set��������(��������);

				
				if (dao.update(�����) == false) {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}