package ���Ǵ��ܺ�;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ���Ǵ��ܺ�.���Ǵ��ܺ�Dao;
import ���Ǵ��.���Ǵ��EditAction;
import defaultMethod.defaultQuery;

@WebServlet("/���Ǵ��ܺ�EditAction")

public class ���Ǵ��ܺ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	���Ǵ��EditAction ea = new ���Ǵ��EditAction();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		���Ǵ��ܺ�Dao dao = new ���Ǵ��ܺ�Dao();

		try {
			if (oper.equals("add")) {
				String �а��� = request.getParameter("�а���");
				String ���и� = request.getParameter("���и�");
				float �������� = defaultClass.nullCheck(request.getParameter("��������")) ? 0
						: Float.parseFloat(request.getParameter("��������"));
				float �����ʼ����� = defaultClass.nullCheck(request.getParameter("�����ʼ�����")) ? 0
						: Float.parseFloat(request.getParameter("�����ʼ�����"));
				float �������ð��� = defaultClass.nullCheck(request.getParameter("�������ð���")) ? 0
						: Float.parseFloat(request.getParameter("�������ð���"));
				String �Էºμ� = request.getParameter("�Էºμ�");
				String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
				float ���������ʼ����� = dao.�����ʼ�����(���и�);
				float �� = dao.�����Ѱ�(���и�);
				float ���Ǵ����� = ea.���Ǵ��(��������, ���������ʼ�����, �������ð���, ��);

				���Ǵ��ܺ� ���Ǵ��ܺΰ�� = new ���Ǵ��ܺ�();

				���Ǵ��ܺΰ��.set���и�(���и�);
				���Ǵ��ܺΰ��.set�а���(�а���);
				���Ǵ��ܺΰ��.set�����ʼ�����(�����ʼ�����);
				���Ǵ��ܺΰ��.set���(���);
				���Ǵ��ܺΰ��.set�������ð���(�������ð���);
				���Ǵ��ܺΰ��.set��������(��������);
				���Ǵ��ܺΰ��.set���Ǵ�����(���Ǵ�����);
				���Ǵ��ܺΰ��.set�Էºμ�(�Էºμ�);

				if (!dao.insert(���Ǵ��ܺΰ��)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.flush();
					out.close();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {
			
				��������();
				
			} else if (oper.equals("edit")) {

				String �а��� = request.getParameter("�а���");
				String ���и� = request.getParameter("���и�");
				float �������� = defaultClass.nullCheck(request.getParameter("��������")) ? 0
						: Float.parseFloat(request.getParameter("��������"));
				float �����ʼ����� = defaultClass.nullCheck(request.getParameter("�����ʼ�����")) ? 0
						: Float.parseFloat(request.getParameter("�����ʼ�����"));
				String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
				float �������ð��� = defaultClass.nullCheck(request.getParameter("�������ð���")) ? 0
						: Float.parseFloat(request.getParameter("�������ð���"));
				int ���� = Integer.parseInt(request.getParameter("����"));
				PrintWriter out = response.getWriter();

				���Ǵ��ܺ� ���Ǵ��ܺ� = new ���Ǵ��ܺ�();

				float ���������ʼ����� = dao.�����ʼ�����(���и�);
				float �� = dao.�����Ѱ�(���и�);
				float ���Ǵ����� = ea.���Ǵ��(��������, ���������ʼ�����, �������ð���, ��);

				���Ǵ��ܺ�.set���и�(���и�);
				���Ǵ��ܺ�.set�а���(�а���);
				���Ǵ��ܺ�.set���(���);
				���Ǵ��ܺ�.set�����ʼ�����(�����ʼ�����);
				���Ǵ��ܺ�.set��������(��������);
				���Ǵ��ܺ�.set�������ð���(�������ð���);
				���Ǵ��ܺ�.set���Ǵ�����(���Ǵ�����);
				���Ǵ��ܺ�.set����(����);

				if (!dao.update(���Ǵ��ܺ�)) {
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

	public void ��������() {
		String column = "���Ǵ�����";
		String table = "�������Ǵ��ܺκ���";

		ArrayList<String> ���и�� = defaultQuery.�ܺδ��и��(table);

		for (String ���и� : ���и��) {
			float ��� = defaultQuery.�ܺ�avg(column, table); // �ܺ���ǥ�� �й��迭�� �������
															// �ʴ´ٰ��Ѵ�.
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