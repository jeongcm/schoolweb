package ������;

import java.io.IOException;
import defaultMethod.�⵵;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import ������.������Dao;

@WebServlet("/������EditAction")

public class ������EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");


		������Dao dao = new ������Dao();

		try {
			if (oper.equals("add")) {
				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));

				int ���ӱ����� = defaultQuery.���ӱ�����(�⵵, �а���);
				int �߾����� = defaultClass.nullCheck(request.getParameter("�߾�����")) ? 0
						: Integer.parseInt(request.getParameter("�߾�����"));
				int ����ü = defaultClass.nullCheck(request.getParameter("����ü")) ? 0
						: Integer.parseInt(request.getParameter("����ü"));
				int �ΰ� = defaultClass.nullCheck(request.getParameter("�ΰ�")) ? 0
						: Integer.parseInt(request.getParameter("�ΰ�"));
				int �ܱ� = defaultClass.nullCheck(request.getParameter("�ܱ�")) ? 0
						: Integer.parseInt(request.getParameter("�ܱ�"));
				int �� = ��(�߾�����, ����ü, �ΰ�, �ܱ�);
				String �Էºμ� = request.getParameter("�Էºμ�");

				float ������ = ������(���ӱ�����, ��);

				������ ������bean = new ������();

				������bean.set�⵵(�⵵);
				������bean.set�а���(�а���);
				������bean.set����ü(����ü);
				������bean.set�߾�����(�߾�����);
				������bean.set�ΰ�(�ΰ�);
				������bean.set�ܱ�(�ܱ�);
				������bean.set������(������);
				������bean.set��(��);
				������bean.set�Էºμ�(�Էºμ�);

				if (!dao.insert(������bean)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();

				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {
				
				��������();
				
			} else if (oper.equals("edit")) {
				PrintWriter out = response.getWriter();
				int ���� = Integer.parseInt(request.getParameter("����"));
				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
				int ���ӱ����� = defaultQuery.���ӱ�����(�⵵, �а���);
				int �߾����� = defaultClass.nullCheck(request.getParameter("�߾�����")) ? 0
						: Integer.parseInt(request.getParameter("�߾�����"));
				int ����ü = defaultClass.nullCheck(request.getParameter("����ü")) ? 0
						: Integer.parseInt(request.getParameter("����ü"));
				int �ΰ� = defaultClass.nullCheck(request.getParameter("�ΰ�")) ? 0
						: Integer.parseInt(request.getParameter("�ΰ�"));
				int �ܱ� = defaultClass.nullCheck(request.getParameter("�ܱ�")) ? 0
						: Integer.parseInt(request.getParameter("�ܱ�"));
				int �� = ��(�߾�����, ����ü, �ΰ�, �ܱ�);

				if (defaultQuery.�а���üũ(�а���).equals("0")) {
					out.write("failMajor");
					out.flush();
					out.close();
				} else if (defaultQuery.�⵵üũ(�⵵).equals("0")) {
					out.write("failYear");
					out.flush();
					out.close();
				} else {

					float ������ = ������(���ӱ�����, ��);

					������ ������bean = new ������();

					������bean.set����(����);
					������bean.set�⵵(�⵵);
					������bean.set�а���(�а���);
					������bean.set����ü(����ü);
					������bean.set�߾�����(�߾�����);
					������bean.set�ΰ�(�ΰ�);
					������bean.set�ܱ�(�ܱ�);
					������bean.set������(������);
					������bean.set��(��);

					if (!dao.update(������bean)) {
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

	public float ������(int ���ӱ�����, int ��) {
		float ������=(float)(�� / (���ӱ����� * 1.0));
		System.out.println("���ӱ����� :"+���ӱ�����+" �հ� :"+��);
		return ������;
	}

	public int ��(int �߾�����, int ����ü, int �ΰ�, int �ܱ�) {
		return �߾����� + ����ü + �ΰ� + �ܱ�;
	}
	
	public void ��������(){
		�⵵ �⵵class=new �⵵();
		int �⵵=�⵵class.�⵵();
		
		String column = "������";
		String table = "������";

		
		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			String �й��迭 = defaultQuery.�й��迭(�⵵, �а���);
			
			float ��� = defaultQuery.�й��迭avg(�⵵, column, table, �й��迭);
			float ǥ������ =defaultQuery.�й��迭std(�⵵, column, table, �й��迭);
			float ������ = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(������, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}