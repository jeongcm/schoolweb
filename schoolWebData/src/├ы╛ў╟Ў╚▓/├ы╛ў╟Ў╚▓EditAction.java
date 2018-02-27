package �����Ȳ;

import java.io.IOException;
import defaultMethod.�⵵;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import �����Ȳ.�����ȲDao;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;

@WebServlet("/�����ȲEditAction")

public class �����ȲEditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	�����ȲDao dao = new �����ȲDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");
		try {
			if (oper.equals("add")) {
				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
				int ������ = defaultClass.nullCheck(request.getParameter("������")) ? 0
						: Integer.parseInt(request.getParameter("������"));
				float ������������� = defaultClass.nullCheck(request.getParameter("�������������")) ? 0
						: Float.parseFloat(request.getParameter("�������������"));
				String �Էºμ� = request.getParameter("�Էºμ�");
				
				�����Ȳ �����Ȳ = new �����Ȳ();

				�����Ȳ.set�⵵(�⵵);
				�����Ȳ.set�а���(�а���);
				�����Ȳ.set������(������);
				�����Ȳ.set�������������(�������������);
				�����Ȳ.set�Էºμ�(�Էºμ�);
				PrintWriter out = response.getWriter();

				if (!dao.insert(�����Ȳ)) {
					out.write("fail");
					out.flush();
					out.close();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {
			
				 ��������();
			
			} else if (oper.equals("edit")) {
				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
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
					�а��� = request.getParameter("�а���");
					�⵵ = Integer.parseInt(request.getParameter("�⵵"));
					int ������ = defaultClass.nullCheck(request.getParameter("������")) ? 0
							: Integer.parseInt(request.getParameter("������"));
					float ������������� = defaultClass.nullCheck(request.getParameter("�������������")) ? 0
							: Float.parseFloat(request.getParameter("�������������"));
					�����Ȳ �����Ȳ = new �����Ȳ();

					�����Ȳ.set����(����);
					�����Ȳ.set�⵵(�⵵);
					�����Ȳ.set�а���(�а���);
					�����Ȳ.set������(������);
					�����Ȳ.set�������������(�������������);

					if (!dao.update(�����Ȳ)) {
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

	public float �����(String �а���, int �⵵) {
		BigDecimal B_����� = new BigDecimal(String.valueOf(dao.�����(�а���, �⵵))); // �����+���������-���д�ñ������
		BigDecimal B_��������� = new BigDecimal(String.valueOf(dao.���������(�а���, �⵵))); //������-������-���д�ñ������

		BigDecimal B_0_5 = new BigDecimal("0.5");
		BigDecimal B_100 = new BigDecimal("100");

		System.out.println("����� :" + B_����� + "��������� :" + B_���������);
		
		BigDecimal result=new BigDecimal('0');
		
		if (B_�����.compareTo(BigDecimal.ZERO) !=0 && B_���������.compareTo(BigDecimal.ZERO) !=0)
			result = (B_�����.divide(B_���������, 12,BigDecimal.ROUND_DOWN)).multiply(B_0_5); //((0.5*�����/���������)+(2�������*0.5))*100�� �����̸� result�� �տ� �����/���������*100�κ��� ����

		System.out.println("result����� :"+result);
		
		BigDecimal B_2����������� = new BigDecimal(String.valueOf(dao.�������������(�а���, �⵵)));//������� ����ɰ��̾� 12.19�� ���

		System.out.println("2�� ���������" + B_2�����������);

		float ����� = (result.add(B_0_5.multiply(B_2�����������))).multiply(B_100).floatValue();

		System.out.println("����� ��� ���" + �����);

		return �����;

	}

	public void ��������() {

		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "�����";
		String table = "�����Ȳ";

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			float �������� = �����(�а���, �⵵);
			dao.�����update(��������, �а���, �⵵);

			String �й��迭 = defaultQuery.�й��迭(�⵵, �а���);

			float ��� = defaultQuery.�й��迭avg(�⵵, column, table, �й��迭);
			float ǥ������ =defaultQuery.�й��迭std(�⵵, column, table, �й��迭);

			float ����� = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(�����, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}