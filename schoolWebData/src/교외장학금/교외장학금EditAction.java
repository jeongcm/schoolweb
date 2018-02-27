package �������б�;

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

import �������б�.�������б�Dao;
import defaultMethod.defaultQuery;

@WebServlet("/�������б�EditAction")
public class �������б�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	�������б�Dao dao = new �������б�Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		try {
			if (oper.equals("add")) {

				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));

				int �������б� = defaultClass.nullCheck(request.getParameter("�������б�")) ? 0
						: Integer.parseInt(request.getParameter("�������б�"));
				String �Էºμ� = request.getParameter("�Էºμ�");

				float ���л���� = dao.���л����(�⵵, �а���);
				float ���δ米�����б� = �������б�(���л����, �������б�);

				�������б� �������б�bean = new �������б�();

				�������б�bean.set�⵵(�⵵);
				�������б�bean.set�а���(�а���);
				�������б�bean.set�������б�(�������б�);
				�������б�bean.set���δ米�����б�(���δ米�����б�);
				�������б�bean.set�Էºμ�(�Էºμ�);

				if (!dao.insert(�������б�bean)) {
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
					int �������б� = defaultClass.nullCheck(request.getParameter("�������б�")) ? 0
							: Integer.parseInt(request.getParameter("�������б�"));
					int ���� = Integer.parseInt(request.getParameter("����"));

					float ���л���� = dao.���л����(�⵵, �а���);
					float ���δ米�����б� = �������б�(���л����, �������б�);

					�������б� �������б�bean = new �������б�();

					�������б�bean.set�⵵(�⵵);
					�������б�bean.set�а���(�а���);
					�������б�bean.set�������б�(�������б�);
					�������б�bean.set���δ米�����б�(���δ米�����б�);
					�������б�bean.set����(����);

					if (!dao.update(�������б�bean)) {
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

	public float �������б�(float ���л����, int �������б�) {

		BigDecimal B_���л���� = new BigDecimal(String.valueOf(���л����));
		BigDecimal B_�������б� = new BigDecimal(String.valueOf(�������б�));
		if (B_�������б�.compareTo(BigDecimal.ZERO) > 0 && B_���л����.compareTo(BigDecimal.ZERO) > 0) {
			float �������бݰ�� = (B_�������б�.divide(B_���л����, 12, BigDecimal.ROUND_DOWN)).floatValue();
			System.out.println("���л���� :" + ���л���� + " �������б� :" + �������б� + " �������б� ��� :" + �������бݰ��);
			return �������бݰ��;
		} else
			return 0;
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "1�δ米�����б�";
		String table = "�������б�";

		float ��� = defaultQuery.avg(�⵵, column, table);
		float ǥ������ =defaultQuery.std(�⵵, column, table);

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);
		for (String �а��� : �а����) {
			float ���δ米�����б� = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(���δ米�����б�, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}