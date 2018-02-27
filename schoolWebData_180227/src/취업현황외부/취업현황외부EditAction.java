package �����Ȳ�ܺ�;

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

@WebServlet("/�����Ȳ�ܺ�EditAction")

public class �����Ȳ�ܺ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	�����Ȳ�ܺ�Dao dao = new �����Ȳ�ܺ�Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			String �а��� = request.getParameter("�а���");
			String ���и� = request.getParameter("���и�");
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
			int ������ = defaultClass.nullCheck(request.getParameter("������")) ? 0
					: Integer.parseInt(request.getParameter("������"));
			float ������������� = defaultClass.nullCheck(request.getParameter("�������������")) ? 0
					: Float.parseFloat(request.getParameter("�������������"));
			
			String �Էºμ� = request.getParameter("�Էºμ�");

			�����Ȳ�ܺ� �����Ȳ = new �����Ȳ�ܺ�();

			�����Ȳ.set���и�(���и�);
			�����Ȳ.set�а���(�а���);
			�����Ȳ.set���(���);
			�����Ȳ.set������(������);
			�����Ȳ.set�Էºμ�(�Էºμ�);

			�����Ȳ.set�������������(�������������);

			PrintWriter out = response.getWriter();

			if (dao.insert(�����Ȳ) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}

		} else if (oper.equals("cal") || oper.equals("editCal")) {

			��������();

		} else if (oper.equals("edit")) {
			String ���и� = request.getParameter("���и�");
			String �а��� = request.getParameter("�а���");
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? null : request.getParameter("���");
			int ���� = Integer.parseInt(request.getParameter("����"));
			int ������ = defaultClass.nullCheck(request.getParameter("������")) ? 0
					: Integer.parseInt(request.getParameter("������"));
			float ������������� = defaultClass.nullCheck(request.getParameter("�������������")) ? 0
					: Float.parseFloat(request.getParameter("�������������"));
			�����Ȳ�ܺ� �����Ȳ = new �����Ȳ�ܺ�();

			�����Ȳ.set���и�(���и�);
			�����Ȳ.set�а���(�а���);
			�����Ȳ.set���(���);
			�����Ȳ.set������(������);
			�����Ȳ.set�������������(�������������);
			�����Ȳ.set����(����);

			PrintWriter out = response.getWriter();

			if (dao.update(�����Ȳ) == false) {
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
	}

	public float �����(String ���и�) {
		BigDecimal B_����� = new BigDecimal(String.valueOf(dao.�����(���и�))); // �����+���������-���д�ñ������
		BigDecimal B_��������� = new BigDecimal(String.valueOf(dao.���������(���и�))); // ������-������-���д�ñ������

		BigDecimal B_0_5 = new BigDecimal("0.5");
		BigDecimal B_100 = new BigDecimal("100");

		System.out.println("����� :" + B_����� + "��������� :" + B_���������);

		BigDecimal result = new BigDecimal('0');

		if (B_�����.compareTo(BigDecimal.ZERO) != 0 && B_���������.compareTo(BigDecimal.ZERO) != 0)
			result = (B_�����.divide(B_���������, 12, BigDecimal.ROUND_DOWN)).multiply(B_0_5);

		System.out.println("result����� :" + result);

		BigDecimal B_2����������� = new BigDecimal(String.valueOf(dao.�������������(���и�)));

		System.out.println("2�� ���������" + B_2�����������);

		float ����� = (result.add(B_0_5.multiply(B_2�����������))).multiply(B_100).floatValue();

		System.out.println("����� ��� ���" + �����);

		return �����;

	}

	public void ��������() {

		String column = "�����";
		String table = "�����Ȳ";

		ArrayList<String> ���и�� = defaultQuery.�ܺδ��и��(table);

		for (String ���и� : ���и��) {
			float ��� = defaultQuery.�ܺ�avg(column, table);
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
