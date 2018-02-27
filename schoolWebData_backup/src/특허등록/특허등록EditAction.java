package Ư����;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Ư����.Ư����;
import Ư����.Ư����Dao;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import java.math.BigDecimal;
import defaultMethod.�⵵;

@WebServlet("/Ư����EditAction")
public class Ư����EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Ư����Dao dao = new Ư����Dao();


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {

			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String �а��� = request.getParameter("�а���");

			int ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
					: Integer.parseInt(request.getParameter("����"));
			int ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
					: Integer.parseInt(request.getParameter("����"));

			int ���ӱ����� = defaultQuery.���ӱ�����(�⵵, �а���);

			String �Էºμ� = request.getParameter("�Էºμ�");
			Ư���� Ư���� = new Ư����();

			float Ư�������������� = dao.Ư��������������(�⵵, �а���);
			float Ư���Ϲױ���������Է� = Ư���Ϲױ���������Է�(����, ����, Ư��������������, ���ӱ�����);

			Ư����.set�⵵(�⵵);
			Ư����.set�а���(�а���);
			Ư����.set����(����);
			Ư����.set����(����);
			Ư����.setƯ��������������(Ư��������������);
			Ư����.setƯ���Ϲױ���������Է�(Ư���Ϲױ���������Է�);
			Ư����.set�Էºμ�(�Էºμ�);

			if (!dao.insert(Ư����)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		} else if (oper.equals("cal") || oper.equals("editCal")) {

			��������();

		} else if (oper.equals("edit")) {
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String �а��� = request.getParameter("�а���");

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

				int ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
						: Integer.parseInt(request.getParameter("����"));
				int ���� = defaultClass.nullCheck(request.getParameter("����")) ? 0
						: Integer.parseInt(request.getParameter("����"));

				float Ư�������������� = dao.Ư��������������(�⵵, �а���);
				int ���� = Integer.parseInt(request.getParameter("����"));

				Ư���� Ư���� = new Ư����();

				Ư����.set�⵵(�⵵);
				Ư����.set�а���(�а���);
				Ư����.set����(����);
				Ư����.set����(����);
				Ư����.setƯ��������������(Ư��������������);
				Ư����.set����(����);

				if (!dao.update(Ư����)) {
					out.write("fail");
					out.flush();
					out.close();
				}
			}
		} else if (oper.equals("del")) {
			int ���� = Integer.parseInt(request.getParameter("����"));
			dao.delete(����);

			��������();

		} else if (oper.equals("delAll")) {
			String �Էºμ� = request.getParameter("�Էºμ�");
			dao.dellAll(�Էºμ�);

			��������();
		} else {
			System.out.println("�߸��� �����Դϴ�.");
		}
	}

	public float Ư���Ϲױ���������Է�(int ����, int ����, float Ư��������������, int ���ӱ�����) {

		BigDecimal B_���� = new BigDecimal(String.valueOf(����));
		BigDecimal B_���� = new BigDecimal(String.valueOf(����));
		BigDecimal B_Ư�������������� = new BigDecimal(String.valueOf(Ư��������������));
		BigDecimal B_���ӱ����� = new BigDecimal(String.valueOf(���ӱ�����));

		BigDecimal B_200 = new BigDecimal("200");
		BigDecimal B_100 = new BigDecimal("100");
		BigDecimal B_20 = new BigDecimal("20");

		BigDecimal mol = (B_����.multiply(B_200)).add(B_����.multiply(B_100)).add((B_Ư��������������).multiply(B_20));

		if (mol.compareTo(BigDecimal.ZERO) > 0 && B_���ӱ�����.compareTo(BigDecimal.ZERO) > 0) {
			float Ư���չױ���������Է� = (mol.divide(B_���ӱ�����, 12, BigDecimal.ROUND_DOWN)).floatValue();
			System.out.println("���� :" + ���� + " ���� :" + ���� + " Ư�������������� :" + Ư�������������� + " ���ӱ�����:" + ���ӱ�����
					+ " Ư���Ϲױ���������Է� :" + Ư���չױ���������Է�); //Ư���������������� ����ϱ��� �׳� sum()�Ѱ��̰� ������ ������� Ư���Ϲױ���������Է���
			return Ư���չױ���������Է�;
		} else
			return 0;
	}

	public void ��������() {

		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "���Է�";
		String table = "Ư���Ϲױ���������Է�";

		float ��� = (float) defaultQuery.avg(�⵵, column, table);
		float ǥ������ = (float) defaultQuery.std(�⵵, column, table);

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			float ���Է� = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(���Է�, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
