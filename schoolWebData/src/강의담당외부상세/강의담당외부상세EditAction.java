package ���Ǵ��ܺλ�;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ���Ǵ��ܺλ�.���Ǵ��ܺλ�Dao;
import ���Ǵ���.���Ǵ���EditAction;
import defaultMethod.defaultQuery;

@WebServlet("/���Ǵ��ܺλ�EditAction")

public class ���Ǵ��ܺλ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	���Ǵ���EditAction ea = new ���Ǵ���EditAction();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		���Ǵ��ܺλ�Dao dao = new ���Ǵ��ܺλ�Dao();

		try {
			if (oper.equals("add")) {
				String �а��� = request.getParameter("�а���");
				String ���и� = request.getParameter("���и�");
				float ���� = Float.parseFloat(request.getParameter("����"));
				String ���� = request.getParameter("����");
				String �б� = request.getParameter("�б�");
				String ���� = request.getParameter("����");
				String ���� = request.getParameter("����");
				String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
				String �Էºμ� = request.getParameter("�Էºμ�");
				float �������� = dao.��������(���и�);
				float �������ð��� = dao.��������(���и�);
				float ���������ʼ����� = dao.�����ʼ�����(���и�);
				float �� = dao.�����Ѱ�(���и�);
				float set���Ǵ��ܺ� = ���Ǵ��ܺ�(��������, ���������ʼ�����, �������ð���, ��);
				dao.update���Ǵ��ܺ�(set���Ǵ��ܺ�, ���и�);

				���Ǵ��ܺλ� ���Ǵ��ܺλ󼼰�� = new ���Ǵ��ܺλ�();

				���Ǵ��ܺλ󼼰��.set���и�(���и�);
				���Ǵ��ܺλ󼼰��.set�а���(�а���);
				���Ǵ��ܺλ󼼰��.set����(����);
				���Ǵ��ܺλ󼼰��.set�б�(�б�);
				���Ǵ��ܺλ󼼰��.set����(����);
				���Ǵ��ܺλ󼼰��.set����(����);
				���Ǵ��ܺλ󼼰��.set���(���);
				���Ǵ��ܺλ󼼰��.set����(����);
				���Ǵ��ܺλ󼼰��.set�Էºμ�(�Էºμ�);
				PrintWriter out = response.getWriter();

				if (!dao.insert(���Ǵ��ܺλ󼼰��)) {
					out.write("fail");
					out.flush();
					out.close();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				��������();

			} else if (oper.equals("edit")) {

				String �а��� = request.getParameter("�а���");
				String ���и� = request.getParameter("���и�");
				String ���� = request.getParameter("����");
				float ���� = Float.parseFloat(request.getParameter("����"));
				String �б� = request.getParameter("�б�");
				String ���� = request.getParameter("����");
				String ���� = request.getParameter("����");
				String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
				int ���� = Integer.parseInt(request.getParameter("����"));
				PrintWriter out = response.getWriter();
				���Ǵ��ܺλ� ���Ǵ��ܺλ� = new ���Ǵ��ܺλ�();

				float �������� = dao.��������(���и�);
				float �������ð��� = dao.��������(���и�);
				float ���������ʼ����� = dao.�����ʼ�����(���и�);
				float �� = dao.�����Ѱ�(���и�);
				float set���Ǵ��ܺ� = ���Ǵ��ܺ�(��������, ���������ʼ�����, �������ð���, ��);
				dao.update���Ǵ��ܺ�(set���Ǵ��ܺ�, ���и�);

				���Ǵ��ܺλ�.set���и�(���и�);
				���Ǵ��ܺλ�.set�а���(�а���);
				���Ǵ��ܺλ�.set����(����);
				���Ǵ��ܺλ�.set�б�(�б�);
				���Ǵ��ܺλ�.set����(����);
				���Ǵ��ܺλ�.set����(����);
				���Ǵ��ܺλ�.set���(���);
				���Ǵ��ܺλ�.set����(����);
				���Ǵ��ܺλ�.set����(����);

				if (!dao.update(���Ǵ��ܺλ�)) {
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
		} catch (

		NumberFormatException n) {
			n.printStackTrace();
		}
	}

	public float ���Ǵ��ܺ�(float ��������, float ���������ʼ�����, float �������ð���, float ��) {
		BigDecimal B_�������� = new BigDecimal(String.valueOf(��������));
		BigDecimal B_���������ʼ����� = new BigDecimal(String.valueOf(���������ʼ�����));
		BigDecimal B_�������ð��� = new BigDecimal(String.valueOf(�������ð���));
		BigDecimal B_�� = new BigDecimal(String.valueOf(��));

		BigDecimal B_100 = new BigDecimal("100");

		if (�� > 0) {
			return (B_��.divide((B_��������.add(B_���������ʼ�����).add(B_�������ð���)), 2, BigDecimal.ROUND_UP)).multiply(B_100)
					.floatValue();
		} else {
			return 0;
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