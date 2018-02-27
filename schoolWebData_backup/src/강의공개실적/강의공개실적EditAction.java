package ���ǰ�������;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import defaultMethod.defaultClass;
import defaultMethod.�⵵;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ���ǰ�������.���ǰ�������Dao;
import defaultMethod.defaultQuery;
import java.text.DecimalFormat;

@WebServlet("/���ǰ�������EditAction")
public class ���ǰ�������EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	���ǰ�������Dao dao = new ���ǰ�������Dao();
	DecimalFormat format = new DecimalFormat("0.00");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		try {
			if (oper.equals("add")) {

				String �а��� = request.getParameter("�а���");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));

				int ���ӱ����� = defaultQuery.���ӱ�����(�⵵, �а���);
				int ���ǵ�����B = defaultClass.nullCheck(request.getParameter("���ǵ�����B")) ? 0
						: Integer.parseInt(request.getParameter("���ǵ�����B"));
				int �̷��װ���C = defaultClass.nullCheck(request.getParameter("�̷��װ���C")) ? 0
						: Integer.parseInt(request.getParameter("�̷��װ���C"));
				int �����ڷ�D = defaultClass.nullCheck(request.getParameter("�����ڷ�D")) ? 0
						: Integer.parseInt(request.getParameter("�����ڷ�D"));
				int ���ǵ�����E = defaultClass.nullCheck(request.getParameter("���ǵ�����E")) ? 0
						: Integer.parseInt(request.getParameter("���ǵ�����E"));
				int �̷��װ���F = defaultClass.nullCheck(request.getParameter("�̷��װ���F")) ? 0
						: Integer.parseInt(request.getParameter("�̷��װ���F"));
				int �����ڷ�G = defaultClass.nullCheck(request.getParameter("�����ڷ�G")) ? 0
						: Integer.parseInt(request.getParameter("�����ڷ�G"));

				String �Էºμ� = request.getParameter("�Էºμ�");

				float ���ǰ���������� = ���ǰ����������(���ӱ�����, ���ǵ�����B, �̷��װ���C, �����ڷ�D, ���ǵ�����E, �̷��װ���F, �����ڷ�G);

				���ǰ������� ���ǰ������� = new ���ǰ�������();

				���ǰ�������.set�⵵(�⵵);
				���ǰ�������.set�а���(�а���);
				���ǰ�������.set���ǵ�����B(���ǵ�����B);
				���ǰ�������.set�̷��װ���C(�̷��װ���C);
				���ǰ�������.set�����ڷ�D(�����ڷ�D);
				���ǰ�������.set���ǵ�����E(���ǵ�����E);
				���ǰ�������.set�̷��װ���F(�̷��װ���F);
				���ǰ�������.set�����ڷ�G(�����ڷ�G);
				���ǰ�������.set�Էºμ�(�Էºμ�);
				���ǰ�������.set���ǰ����������(���ǰ����������);

				if (!dao.insert(���ǰ�������)) {
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

				int ���ӱ����� = defaultQuery.���ӱ�����(�⵵, �а���);

				int ���ǵ�����B = defaultClass.nullCheck(request.getParameter("���ǵ�����B")) ? 0
						: Integer.parseInt(request.getParameter("���ǵ�����B"));
				int �̷��װ���C = defaultClass.nullCheck(request.getParameter("�̷��װ���C")) ? 0
						: Integer.parseInt(request.getParameter("�̷��װ���C"));
				int �����ڷ�D = defaultClass.nullCheck(request.getParameter("�����ڷ�D")) ? 0
						: Integer.parseInt(request.getParameter("�����ڷ�D"));
				int ���ǵ�����E = defaultClass.nullCheck(request.getParameter("���ǵ�����E")) ? 0
						: Integer.parseInt(request.getParameter("���ǵ�����E"));
				int �̷��װ���F = defaultClass.nullCheck(request.getParameter("�̷��װ���F")) ? 0
						: Integer.parseInt(request.getParameter("�̷��װ���F"));
				int �����ڷ�G = defaultClass.nullCheck(request.getParameter("�����ڷ�G")) ? 0
						: Integer.parseInt(request.getParameter("�����ڷ�G"));
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

					���ǰ������� ���ǰ������� = new ���ǰ�������();

					float ���ǰ���������� = ���ǰ����������(���ӱ�����, ���ǵ�����B, �̷��װ���C, �����ڷ�D, ���ǵ�����E, �̷��װ���F, �����ڷ�G);

					���ǰ�������.set�⵵(�⵵);
					���ǰ�������.set�а���(�а���);
					���ǰ�������.set���ǵ�����B(���ǵ�����B);
					���ǰ�������.set�̷��װ���C(�̷��װ���C);
					���ǰ�������.set�����ڷ�D(�����ڷ�D);
					���ǰ�������.set���ǵ�����E(���ǵ�����E);
					���ǰ�������.set�̷��װ���F(�̷��װ���F);
					���ǰ�������.set�����ڷ�G(�����ڷ�G);
					���ǰ�������.set���ǰ����������(���ǰ����������);
					���ǰ�������.set����(����);

					if (!dao.update(���ǰ�������)) {
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
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

	public float ���ǰ����������(int ���ӱ�����A, int ���ǵ�����B, int �̷��װ���C, int �����ڷ�D, int ���ǵ�����E, int �̷��װ���F, int �����ڷ�G) {
		if ((���ǵ�����B + ���ǵ�����E) * 2 + (�̷��װ���C + �̷��װ���F) * 2 + (�����ڷ�D + �����ڷ�G) < 0 || ���ӱ�����A < 0)
			return 0;
		else {
			float result = (float) ((((���ǵ�����B + ���ǵ�����E) * 2 + (�̷��װ���C + �̷��װ���F) * 2 + (�����ڷ�D + �����ڷ�G))
					/ (���ӱ�����A * 1.0)));
			float ���ǰ������� = (float)result;
			System.out.println("���ӱ����� :" + ���ӱ�����A + " ���ǵ�����B :" + ���ǵ�����B + " �̷��װ���C :" + �̷��װ���C + " �����ڷ�D :" + �����ڷ�D
					+ " ���ǵ�����E :" + ���ǵ�����E + " �̷��װ���F :" + �̷��װ���F + " �����ڷ�G :" + �����ڷ�G + " ���ǰ������� :" + ���ǰ�������);
			return ���ǰ�������;
		}
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "���ǰ�������";
		String table = "���ǰ�������";

		float ��� = (float) defaultQuery.avg(�⵵, column, table);
		float ǥ������ = (float) defaultQuery.std(�⵵, column, table);

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			float ���ǰ���������� = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(���ǰ����������, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}