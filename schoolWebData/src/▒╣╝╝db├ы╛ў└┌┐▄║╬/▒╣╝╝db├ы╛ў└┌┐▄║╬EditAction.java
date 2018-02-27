package ����db����ڿܺ�;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import �����Ȳ�ܺ�.�����Ȳ�ܺ�Dao;

@WebServlet("/����db����ڿܺ�EditAction")

public class ����db����ڿܺ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String �������и� = null;
	�����Ȳ�ܺ�Dao �����Ȳdao = new �����Ȳ�ܺ�Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");
		����db����ڿܺ�Dao dao = new ����db����ڿܺ�Dao();
		if (oper.equals("add")) {
			String �� = request.getParameter("��");
			String �а��� = request.getParameter("�а���");
			�������и� = request.getParameter("���и�");
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
			String �Էºμ� = request.getParameter("�Էºμ�");
			int ����â��Ȱ�����缭 = defaultClass.nullCheck(request.getParameter("����â��Ȱ�����缭")) ? 0
					: Integer.parseInt(request.getParameter("����â��Ȱ�����缭"));
			int ����â���� = defaultClass.nullCheck(request.getParameter("����â����")) ? 0
					: Integer.parseInt(request.getParameter("����â����"));
			int �������� = defaultClass.nullCheck(request.getParameter("��������")) ? 0
					: Integer.parseInt(request.getParameter("��������"));

			int �� = ����â��Ȱ�����缭 + ����â���� + ��������;
			��������ڿܺ� ����db����ڿܺ� = new ��������ڿܺ�();

			����db����ڿܺ�.set��(��);
			����db����ڿܺ�.set���и�(�������и�);
			����db����ڿܺ�.set�а���(�а���);
			����db����ڿܺ�.set���(���);
			����db����ڿܺ�.set��(��);
			����db����ڿܺ�.set����â��Ȱ�����缭(����â��Ȱ�����缭);
			����db����ڿܺ�.set����â����(����â����);
			����db����ڿܺ�.set��������(��������);
			����db����ڿܺ�.set�Էºμ�(�Էºμ�);

			PrintWriter out = response.getWriter();

			if (dao.insert(����db����ڿܺ�) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}

		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();

			String �а��� = request.getParameter("�а���");
			�������и� = request.getParameter("���и�");
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
			int ���� = Integer.parseInt(request.getParameter("����"));
			int ����â��Ȱ�����缭 = defaultClass.nullCheck(request.getParameter("����â��Ȱ�����缭")) ? 0
					: Integer.parseInt(request.getParameter("����â��Ȱ�����缭"));
			int ����â���� = defaultClass.nullCheck(request.getParameter("����â����")) ? 0
					: Integer.parseInt(request.getParameter("����â����"));
			int �������� = defaultClass.nullCheck(request.getParameter("��������")) ? 0
					: Integer.parseInt(request.getParameter("��������"));

			int �� = ����â��Ȱ�����缭 + ����â���� + ��������;

			��������ڿܺ� ����db����ڿܺ� = new ��������ڿܺ�();

			����db����ڿܺ�.set����(����);
			����db����ڿܺ�.set���(���);
			����db����ڿܺ�.set�а���(�а���);
			����db����ڿܺ�.set���и�(�������и�);
			����db����ڿܺ�.set��(��);
			����db����ڿܺ�.set����â��Ȱ�����缭(����â��Ȱ�����缭);
			����db����ڿܺ�.set����â����(����â����);
			����db����ڿܺ�.set��������(��������);

			if (dao.update(����db����ڿܺ�) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}
		} else if (oper.equals("cal") || oper.equals("editCal")) {
			float �������� = �����(�������и�);
			�����Ȳdao.�����update(��������, �������и�);

			String column = "�����";
			String table = "�����Ȳ�ܺ�";

			float ��� = (float) (Math.round(defaultQuery.�ܺ�avg(column, table) * 100) / 100.0);
			float ǥ������ = (float) (Math.round(defaultQuery.�ܺ�std(column, table) * 100) / 100.0);

			ArrayList<String> ���и�� = defaultQuery.�ܺδ��и��(table);

			for (String ���и� : ���и��) {
				float ����� = defaultQuery.�ܺκ���(���и�, column, table);
				float T���� = defaultClass.T����(�����, ���, ǥ������);
				defaultQuery.�ܺ�updateT(T����, ���и�, table);
			}
		} else if (oper.equals("del")) {
			int ���� = Integer.parseInt(request.getParameter("����"));
			dao.delete(����);
			float �������� = �����(�������и�);
			�����Ȳdao.�����update(��������, �������и�);

			String column = "�����";
			String table = "�����Ȳ�ܺ�";

			float ��� = (float) (Math.round(defaultQuery.�ܺ�avg(column, table) * 100) / 100.0);
			float ǥ������ = (float) (Math.round(defaultQuery.�ܺ�std(column, table) * 100) / 100.0);

			ArrayList<String> ���и�� = defaultQuery.�ܺδ��и��(table);

			for (String ���и� : ���и��) {
				float ����� = defaultQuery.�ܺκ���(���и�, column, table);
				float T���� = defaultClass.T����(�����, ���, ǥ������);
				defaultQuery.�ܺ�updateT(T����, ���и�, table);
			}
		} else if (oper.equals("delAll")) {
			String �Էºμ� = request.getParameter("�Էºμ�");
			dao.dellAll(�Էºμ�);

		} else {

			System.out.println("�߸��� ���� �Դϴ�.");

		}
	}

	public float �����(String ���и�) {
		BigDecimal B_6������� = new BigDecimal(String.valueOf(�����Ȳdao._6�������(���и�)));
		BigDecimal B_6����������� = new BigDecimal(String.valueOf(�����Ȳdao._6�����������(���и�)));
		BigDecimal B_12������� = new BigDecimal(String.valueOf(�����Ȳdao._12�����������(���и�)));
		BigDecimal B_12����������� = new BigDecimal(String.valueOf(�����Ȳdao._12�����������(���и�)));

		BigDecimal B_0_2 = new BigDecimal("0.2");
		BigDecimal B_0_4 = new BigDecimal("0.4");
		BigDecimal B_100 = new BigDecimal("100");

		BigDecimal mol = B_0_4.multiply(B_6�������);

		if (mol.compareTo(BigDecimal.ZERO) > 0)
			mol = mol.divide(B_6�����������);

		BigDecimal mol2 = B_12�������.add(B_0_2).multiply(B_12�����������);

		BigDecimal B_2����������� = new BigDecimal(String.valueOf(�����Ȳdao.�������������(���и�)));

		return ((mol.add(mol2).add(B_0_4.multiply(B_2�����������))).multiply(B_100)).floatValue();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
