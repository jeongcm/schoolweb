package ���Ǵ��;

import java.io.IOException;
import defaultMethod.�⵵;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ���Ǵ��.���Ǵ��Dao;
import defaultMethod.defaultQuery;

@WebServlet("/���Ǵ��EditAction")

public class ���Ǵ��EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	���Ǵ��Dao dao = new ���Ǵ��Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		try {
			if (oper.equals("add")) {
				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
				float �������� = defaultClass.nullCheck(request.getParameter("��������")) ? 0
						: Float.parseFloat(request.getParameter("��������"));
				float �����ʼ����� = defaultClass.nullCheck(request.getParameter("�����ʼ�����")) ? 0
						: Float.parseFloat(request.getParameter("�����ʼ�����"));
				float �������ð��� = defaultClass.nullCheck(request.getParameter("�������ð���")) ? 0
						: Float.parseFloat(request.getParameter("�������ð���"));
				String �Էºμ� = request.getParameter("�Էºμ�");
				float ���������ʼ����� = dao.�����ʼ�����(�⵵, �а���);
				float �� = dao.�����Ѱ�(�⵵, �а���);
				float ���Ǵ����� = ���Ǵ��(��������, ���������ʼ�����, �������ð���, ��);

				���Ǵ�� ���Ǵ���� = new ���Ǵ��();

				���Ǵ����.set�⵵(�⵵);
				���Ǵ����.set�а���(�а���);
				���Ǵ����.set�����ʼ�����(�����ʼ�����);
				���Ǵ����.set�������ð���(�������ð���);
				���Ǵ����.set��������(��������);
				���Ǵ����.set���Ǵ�����(���Ǵ�����);
				���Ǵ����.set�Էºμ�(�Էºμ�);

				if (!dao.insert(���Ǵ����)) {
					System.out.println("���⼭ error?");
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.flush();
					out.close();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				��������();

			} else if (oper.equals("edit")) {

				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
				float �������� = defaultClass.nullCheck(request.getParameter("��������")) ? 0
						: Float.parseFloat(request.getParameter("��������"));
				float �����ʼ����� = defaultClass.nullCheck(request.getParameter("�����ʼ�����")) ? 0
						: Float.parseFloat(request.getParameter("�����ʼ�����"));
				float �������ð��� = defaultClass.nullCheck(request.getParameter("�������ð���")) ? 0
						: Float.parseFloat(request.getParameter("�������ð���"));
				int ���� = Integer.parseInt(request.getParameter("����"));
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
					���Ǵ�� ���Ǵ�� = new ���Ǵ��();

					float ���������ʼ����� = dao.�����ʼ�����(�⵵, �а���);
					float �� = dao.�����Ѱ�(�⵵, �а���);
					float ���Ǵ����� = ���Ǵ��(��������, ���������ʼ�����, �������ð���, ��);

					���Ǵ��.set�⵵(�⵵);
					���Ǵ��.set�а���(�а���);
					���Ǵ��.set�����ʼ�����(�����ʼ�����);
					���Ǵ��.set��������(��������);
					���Ǵ��.set�������ð���(�������ð���);
					���Ǵ��.set���Ǵ�����(���Ǵ�����);
					���Ǵ��.set����(����);

					if (!dao.update(���Ǵ��)) {
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

				System.out.println("�߸��� ���� �Դϴ�.");
			}
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

	public float ���Ǵ��(float ��������, float ���������ʼ�����, float �������ð���, float ��) {
		BigDecimal B_�������� = new BigDecimal(String.valueOf(��������));
		BigDecimal B_���������ʼ����� = new BigDecimal(String.valueOf(���������ʼ�����));
		BigDecimal B_�������ð��� = new BigDecimal(String.valueOf(�������ð���));
		BigDecimal B_�� = new BigDecimal(String.valueOf(��)); // ��� ���������� �������� ��ģ��

		BigDecimal B_100 = new BigDecimal("100");

		System.out.println("�������� :" + �������� + " ���������ʼ����� :" + ���������ʼ����� + " �������ð���:" + �������ð��� + " �հ� :" + ��);
		if (�� > 0) {
			float ���Ǵ�� = (B_��.divide((B_��������.add(B_���������ʼ�����).add(B_�������ð���)), 12, BigDecimal.ROUND_DOWN)).multiply(B_100)
					.floatValue();
			System.out.println("���Ǵ������" + ���Ǵ��);
			return ���Ǵ��;
		} else {
			return 0;
		}
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "���Ǵ�����";
		String table = "�������Ǵ�����";

		float ��� = defaultQuery.avg(�⵵, column, table);
		float ǥ������ = defaultQuery.std(�⵵, column, table);

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			float ���Ǵ����� = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(���Ǵ�����, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}