package ���������ܺ�;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ���������ܺ�.���������ܺ�Dao;
import ��������.��������EditAction;
import defaultMethod.defaultQuery;

@WebServlet("/���������ܺ�EditAction")
public class ���������ܺ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	��������EditAction ea=new ��������EditAction();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		���������ܺ�Dao dao = new ���������ܺ�Dao();
		try {
			if (oper.equals("add")) {

				String �а��� = request.getParameter("�а���");
				String ���и� = request.getParameter("���и�");
				int ���ӱ����� = defaultClass.nullCheck(request.getParameter("���ӱ�����")) ? 0
						: Integer.parseInt(request.getParameter("���ӱ�����"));
				float ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
						: Float.parseFloat((request.getParameter("����")));
				float ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
						: Float.parseFloat((request.getParameter("����")));
				float ������ܵ����� = defaultClass.nullCheck(request.getParameter("������ܵ�����")) ? 0
						: Float.parseFloat((request.getParameter("������ܵ�����")));
				float ������ܵ����ĺ� = defaultClass.nullCheck(request.getParameter("������ܵ����ĺ�")) ? 0
						: Float.parseFloat((request.getParameter("������ܵ����ĺ�")));
				float SCI�� = defaultClass.nullCheck(request.getParameter("SCI��")) ? 0
						: Float.parseFloat((request.getParameter("SCI��")));
				float SCOPUS�м��� = defaultClass.nullCheck(request.getParameter("SCOPUS�м���")) ? 0
						: Float.parseFloat((request.getParameter("SCOPUS�м���")));
				String �Էºμ� = request.getParameter("�Էºμ�");
				String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");

				float ���������ܺΰ�� = ea.��������(���ӱ�����, ����, ����, ������ܵ�����, ������ܵ����ĺ�, SCI��, SCOPUS�м���);

				���������ܺ� ���������ܺ� = new ���������ܺ�();

				���������ܺ�.set�а���(�а���);
				���������ܺ�.set���и�(���и�);
				���������ܺ�.set����(����);
				���������ܺ�.set����(����);
				���������ܺ�.set������ܵ�����(������ܵ�����);
				���������ܺ�.set������ܵ����ĺ�(������ܵ����ĺ�);
				���������ܺ�.setSCI��(SCI��);
				���������ܺ�.setSCOPUS�м���(SCOPUS�м���);
				���������ܺ�.set�Էºμ�(�Էºμ�);
				���������ܺ�.set��������(���������ܺΰ��);
				���������ܺ�.set���(���);

				if (!dao.insert(���������ܺ�)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				��������();

			} else if (oper.equals("edit")) {
				PrintWriter out = response.getWriter();
				
				String �а��� = request.getParameter("�а���");
				String ���и� = request.getParameter("���и�");
				int ���ӱ����� = defaultClass.nullCheck(request.getParameter("���ӱ�����")) ? 0
						: Integer.parseInt(request.getParameter("���ӱ�����"));
				float ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
						: Float.parseFloat((request.getParameter("����")));
				float ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
						: Float.parseFloat((request.getParameter("����")));
				float ������ܵ����� = defaultClass.nullCheck(request.getParameter("������ܵ�����")) ? 0
						: Float.parseFloat((request.getParameter("������ܵ�����")));
				float ������ܵ����ĺ� = defaultClass.nullCheck(request.getParameter("������ܵ����ĺ�")) ? 0
						: Float.parseFloat((request.getParameter("������ܵ����ĺ�")));
				float SCI�� = defaultClass.nullCheck(request.getParameter("SCI��")) ? 0
						: Float.parseFloat((request.getParameter("SCI��")));
				float SCOPUS�м��� = defaultClass.nullCheck(request.getParameter("SCOPUS�м���")) ? 0
						: Float.parseFloat((request.getParameter("SCOPUS�м���")));
				String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
				int ���� = Integer.parseInt(request.getParameter("����"));
				
				float ���������ܺΰ�� = ea.��������(���ӱ�����, ����, ����, ������ܵ�����, ������ܵ����ĺ�, SCI��, SCOPUS�м���);

				���������ܺ� ���������ܺ� = new ���������ܺ�();

				���������ܺ�.set�а���(�а���);
				���������ܺ�.set����(����);
				���������ܺ�.set���и�(���и�);
				���������ܺ�.set���(���);
				���������ܺ�.set����(����);
				���������ܺ�.set������ܵ�����(������ܵ�����);
				���������ܺ�.set������ܵ����ĺ�(������ܵ����ĺ�);
				���������ܺ�.setSCI��(SCI��);
				���������ܺ�.setSCOPUS�м���(SCOPUS�м���);
				���������ܺ�.set����(����);
				���������ܺ�.set��������(���������ܺΰ��);

				if (dao.update(���������ܺ�) == false) {
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
		String column = "��������";
		String table = "���������ܺ�";

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