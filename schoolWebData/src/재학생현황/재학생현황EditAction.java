package ���л���Ȳ;

import defaultMethod.defaultClass;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ���л���Ȳ.���л���ȲDao;
import defaultMethod.defaultQuery;
import defaultMethod.�⵵;

@WebServlet("/���л���ȲEditAction")

public class ���л���ȲEditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	���л���ȲDao dao = new ���л���ȲDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			String �а��� = request.getParameter("�а���");
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));

			int �������� = defaultClass.nullCheck(request.getParameter("��������")) ? 0
					: Integer.parseInt(request.getParameter("��������"));
			int Ÿ�а������� = defaultClass.nullCheck(request.getParameter("Ÿ�а�������")) ? 0
					: Integer.parseInt(request.getParameter("Ÿ�а�������"));

			int �л����� = Integer.parseInt(request.getParameter("�л�����"));
			int ������ = Integer.parseInt(request.getParameter("������"));
			int ������ = Integer.parseInt(request.getParameter("������"));
			String �Էºμ� = request.getParameter("�Էºμ�");

			int �� = ������ + ������;
			float ��ü���л������ = ��ü���л������(��, �л�����, ��������, Ÿ�а�������);
			float ���������л������ = ���������л������(������, �л�����, ��������, Ÿ�а�������);
			float ���л������ = ���л������(��ü���л������, ���������л������);

			���л���Ȳ ���л���Ȳ = new ���л���Ȳ();

			���л���Ȳ.set��ü���л������(��ü���л������);
			���л���Ȳ.set���������л������(���������л������);
			���л���Ȳ.set���л������(���л������);
			���л���Ȳ.set��������(��������);
			���л���Ȳ.setŸ�а�������(Ÿ�а�������);
			���л���Ȳ.set�а���(�а���);
			���л���Ȳ.set�⵵(�⵵);
			���л���Ȳ.set��(��);
			���л���Ȳ.set������(������);
			���л���Ȳ.set������(������);
			���л���Ȳ.set�л�����(�л�����);
			���л���Ȳ.set�Էºμ�(�Էºμ�);

			PrintWriter out = response.getWriter();

			if (!dao.insert(���л���Ȳ)) {
				out.write("fail");
				out.flush();
				out.close();
			}

		} else if (oper.equals("cal") || oper.equals("editCal")) {

			��������();

		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();

			String �а��� = request.getParameter("�а���");
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));

			if (defaultQuery.�а���üũ(�а���).equals("0")) {
				out.write("failMajor");
				out.flush();
				out.close();
			} else if (defaultQuery.�⵵üũ(�⵵).equals("0")) {
				out.write("failYear");
				out.flush();
				out.close();
			} else {

				int ���� = Integer.parseInt(request.getParameter("����"));

				int �������� = defaultClass.nullCheck(request.getParameter("��������")) ? 0
						: Integer.parseInt(request.getParameter("��������"));
				int Ÿ�а������� = defaultClass.nullCheck(request.getParameter("Ÿ�а�������")) ? 0
						: Integer.parseInt(request.getParameter("Ÿ�а�������"));
				int �л����� = Integer.parseInt(request.getParameter("�л�����"));
				int ������ = Integer.parseInt(request.getParameter("������"));
				int ������ = Integer.parseInt(request.getParameter("������"));

				int �� = ������ + ������;

				float ��ü���л������ = ��ü���л������(��, �л�����, ��������, Ÿ�а�������);
				float ���������л������ = ���������л������(������, �л�����, ��������, Ÿ�а�������);
				float ���л������ = ���л������(��ü���л������, ���������л������);

				���л���Ȳ ���л���Ȳ = new ���л���Ȳ();

				���л���Ȳ.set�а���(�а���);
				���л���Ȳ.set����(����);
				���л���Ȳ.set�⵵(�⵵);
				���л���Ȳ.set��������(��������);
				���л���Ȳ.setŸ�а�������(Ÿ�а�������);
				���л���Ȳ.set������(������);
				���л���Ȳ.set������(������);
				���л���Ȳ.set�л�����(�л�����);
				���л���Ȳ.set��(��);
				���л���Ȳ.set��ü���л������(��ü���л������);
				���л���Ȳ.set���������л������(���������л������);
				���л���Ȳ.set���л������(���л������);

				if (!dao.update(���л���Ȳ)) {
					out.write("fail");
					out.flush();
					out.close();
				} else {
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

			System.out.println("�߸��� ���� �Դϴ�.");

		}
	}

	public float ��ü���л������(int ��, int �л�����, int ��������, int Ÿ�а�������) {
		float result = (float) (�� / ((�л����� - (�������� + Ÿ�а�������)) *1.0));
		float ��ü���л������ = result * 100;
		System.out.println("��ü ���л� �����[ �� :" + �� + " �л����� :" + �л����� + " �������� :" + �������� + " Ÿ�а������� :" + Ÿ�а�������
				+ " ����� :" + ��ü���л������ + "]");
		return ��ü���л������;
	}

	public float ���������л������(int ������, int �л�����, int ��������, int Ÿ�а�������) {
		float result = (float) (������ / ((�л����� - (�������� + Ÿ�а�������)) * 1.0));
		float ���������л������ =result * 100;
		System.out.println("������ ���л� �����[ ������ :" + ������ + " �л����� :" + �л����� + " �������� :" + �������� + " Ÿ�а������� :" + Ÿ�а�������
				+ " ����� :" + ���������л������ + "]");
		return ���������л������;
	}

	public float ���л������(float ��ü���л������, float ���������л������) {
		BigDecimal B_��ü���л������ = new BigDecimal(String.valueOf(Float.toString(��ü���л������)));
		BigDecimal B_���������л������ = new BigDecimal(String.valueOf(Float.toString(���������л������)));

		BigDecimal B_0_4 = new BigDecimal("0.4");
		BigDecimal B_0_6 = new BigDecimal("0.6");

		BigDecimal B_���л������=(B_0_4.multiply(B_��ü���л������)).add((B_0_6.multiply(B_���������л������)));
		float ���л������=B_���л������.floatValue();
		
		
		System.out.println("��ü���л������ :"+��ü���л������+"���������л������ :"+���������л������+"���л������ :"+���л������);
		
		return ���л������;
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String table = "���л���Ȳ";
		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			System.out.println(�а���);
			float ��� =dao.avg(�⵵);
			float ǥ������ =dao.std(�⵵);

			float ���л������ = dao.���л������(�⵵, �а���);
			float T���� = defaultClass.T����(���л������, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
