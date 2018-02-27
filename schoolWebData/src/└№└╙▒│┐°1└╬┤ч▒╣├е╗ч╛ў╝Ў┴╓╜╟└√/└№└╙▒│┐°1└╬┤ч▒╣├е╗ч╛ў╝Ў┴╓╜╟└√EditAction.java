package ���ӱ���1�δ籹å������ֽ���;

import java.io.IOException;
import defaultMethod.�⵵;

import java.io.PrintWriter;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ���ӱ���1�δ籹å������ֽ���.���ӱ���1�δ籹å������ֽ���Dao;
import defaultMethod.defaultQuery;

@WebServlet("/���ӱ���1�δ籹å������ֽ���EditAction")
public class ���ӱ���1�δ籹å������ֽ���EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	���ӱ���1�δ籹å������ֽ���Dao dao = new ���ӱ���1�δ籹å������ֽ���Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		if (oper.equals("add")) {

			String �а��� = request.getParameter("�а���");
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));

			int ���и��Ǳ�å��������Ѿ� = defaultClass.nullCheck(request.getParameter("���и��Ǳ�å��������Ѿ�")) ? 0
					: Integer.parseInt(request.getParameter("���и��Ǳ�å��������Ѿ�"));

			int ���ӱ����� = defaultQuery.���ӱ�����(�⵵, �а���);

			String �Էºμ� = request.getParameter("�Էºμ�");

			Float ���ӱ���1�δ籹å��������Ѿװ�� = ���ӱ���1�δ籹å��������Ѿ�(���ӱ�����, ���и��Ǳ�å��������Ѿ�);

			���ӱ���1�δ籹å������ֽ��� ���ӱ���1�δ籹å������ֽ��� = new ���ӱ���1�δ籹å������ֽ���();

			���ӱ���1�δ籹å������ֽ���.set�⵵(�⵵);
			���ӱ���1�δ籹å������ֽ���.set�а���(�а���);
			���ӱ���1�δ籹å������ֽ���.set���и��Ǳ�å��������Ѿ�(���и��Ǳ�å��������Ѿ�);
			���ӱ���1�δ籹å������ֽ���.set�Էºμ�(�Էºμ�);
			���ӱ���1�δ籹å������ֽ���.set���ӱ���1�δ籹å��������Ѿ�(���ӱ���1�δ籹å��������Ѿװ��);

			if (!dao.insert(���ӱ���1�δ籹å������ֽ���)) {
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

			int ���и��Ǳ�å��������Ѿ� = defaultClass.nullCheck(request.getParameter("���и��Ǳ�å��������Ѿ�")) ? 0
					: Integer.parseInt(request.getParameter("���и��Ǳ�å��������Ѿ�"));
			int ���ӱ����� = defaultQuery.���ӱ�����(�⵵, �а���);
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

				���ӱ���1�δ籹å������ֽ��� ���ӱ���1�δ籹å������ֽ��� = new ���ӱ���1�δ籹å������ֽ���();

				Float ���ӱ���1�δ籹å��������Ѿװ�� = ���ӱ���1�δ籹å��������Ѿ�(���ӱ�����, ���и��Ǳ�å��������Ѿ�);

				���ӱ���1�δ籹å������ֽ���.set���и��Ǳ�å��������Ѿ�(���и��Ǳ�å��������Ѿ�);
				���ӱ���1�δ籹å������ֽ���.set���ӱ���1�δ籹å��������Ѿ�(���ӱ���1�δ籹å��������Ѿװ��);
				���ӱ���1�δ籹å������ֽ���.set�⵵(�⵵);
				���ӱ���1�δ籹å������ֽ���.set�а���(�а���);
				���ӱ���1�δ籹å������ֽ���.set����(����);

				if (!dao.update(���ӱ���1�δ籹å������ֽ���)) {
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
	}

	public float ���ӱ���1�δ籹å��������Ѿ�(int ���ӱ�����, int ���и��Ǳ�å��������Ѿ�) {
		if (���ӱ����� > 0 && ���и��Ǳ�å��������Ѿ� > 0)
			return ���и��Ǳ�å��������Ѿ� / (���ӱ����� * 1.0F);
		else
			return 0;
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();
		
		String column = "���ӱ���1�δ籹å��������Ѿ�";
		String table = "���ӱ���1�δ籹å������ֽ���";

		float ��� = defaultQuery.avg(�⵵, column, table);
		float ǥ������ = defaultQuery.std(�⵵, column, table);

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			float ���ӱ���1�δ籹å��������Ѿװ�� = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(���ӱ���1�δ籹å��������Ѿװ��, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}