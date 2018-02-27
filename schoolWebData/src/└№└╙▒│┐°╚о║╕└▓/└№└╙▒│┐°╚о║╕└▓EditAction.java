package ���ӱ���Ȯ����;

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
import ���ӱ���Ȯ����.���ӱ���Ȯ����Dao;

@WebServlet("/���ӱ���Ȯ����EditAction")

public class ���ӱ���Ȯ����EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	���ӱ���Ȯ����Dao dao = new ���ӱ���Ȯ����Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {

			String �а��� = request.getParameter("�а���");
			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));

			int ���п������� = defaultClass.nullCheck(request.getParameter("���п�������")) ? 0
					: Integer.parseInt(request.getParameter("���п�������"));
			int ���п����л� = defaultClass.nullCheck(request.getParameter("���п����л�")) ? 0
					: Integer.parseInt(request.getParameter("���п����л�"));
			int �л������������ӱ��� = defaultClass.nullCheck(request.getParameter("�л������������ӱ���")) ? 0
					: Integer.parseInt(request.getParameter("�л������������ӱ���"));
			int ���л��������ӱ��� = defaultClass.nullCheck(request.getParameter("���л��������ӱ���")) ? 0
					: Integer.parseInt(request.getParameter("���л��������ӱ���"));

			int �л����� = dao.�л�����(�⵵, �а���);
			int �������� = dao.��������(�⵵, �а���);
			int ���л� = dao.���л�(�⵵, �а���);

			String �й��迭 = dao._5��迭(�⵵, �а���);
			String �Էºμ� = request.getParameter("�Էºμ�");

			int �����л����� = �л����� - ��������;

			int �л������� = �����л����� + ���п�������;
			int ���л��� = ���л� + ���п����л�;

			int ����_�к�_���� = ������������(�й��迭, �����л�����);
			int ����_�к�_���л� = ������������(�й��迭, ���л�);
			int ����_���п�_���� = ������������(�й��迭, ���п�������);
			int ����_���п�_���л� = ������������(�й��迭, ���п����л�);
			int ����_��_���� = ������������(�й��迭, �л�������);
			int ����_��_���л� = ������������(�й��迭, ���л���);

			float �л���_���� = �л���_����(�л�������, �л������������ӱ���);
			float �л���_���л� = �л���_���л�(���л���, ���л��������ӱ���);
			float Ȯ����_���� = Ȯ����_����(�л������������ӱ���, ����_��_����);
			float Ȯ����_���л� = Ȯ����_���л�(���л��������ӱ���, ����_��_���л�);

			float ���ӱ���Ȯ������ = Math.min(Ȯ����_����, Ȯ����_���л�);

			���ӱ���Ȯ���� ���ӱ���Ȯ���� = new ���ӱ���Ȯ����();

			���ӱ���Ȯ����.set�⵵(�⵵);
			���ӱ���Ȯ����.set�а���(�а���);
			���ӱ���Ȯ����.set����_��_���л�(����_��_���л�);
			���ӱ���Ȯ����.set����_��_����(����_��_����);
			���ӱ���Ȯ����.set����_���п�_���л�(����_���п�_���л�);
			���ӱ���Ȯ����.set����_���п�_����(����_���п�_����);
			���ӱ���Ȯ����.set����_�к�_���л�(����_�к�_���л�);
			���ӱ���Ȯ����.set����_�к�_����(����_�к�_����);
			���ӱ���Ȯ����.set���п�������(���п�������);
			���ӱ���Ȯ����.set���п����л�(���п����л�);
			���ӱ���Ȯ����.set�����л�����(�����л�����);
			���ӱ���Ȯ����.setȮ����_����(Ȯ����_����);
			���ӱ���Ȯ����.setȮ����_���л�(Ȯ����_���л�);
			���ӱ���Ȯ����.set�л������������ӱ���(�л������������ӱ���);
			���ӱ���Ȯ����.set���л��������ӱ���(���л��������ӱ���);
			���ӱ���Ȯ����.set�л���_����(�л���_����);
			���ӱ���Ȯ����.set�л���_���л�(�л���_���л�);
			���ӱ���Ȯ����.set���ӱ���Ȯ����(���ӱ���Ȯ������);
			���ӱ���Ȯ����.set�Էºμ�(�Էºμ�);

			PrintWriter out = response.getWriter();

			if (!dao.insert(���ӱ���Ȯ����)) {
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
				int ���п������� = defaultClass.nullCheck(request.getParameter("���п�������")) ? 0
						: Integer.parseInt(request.getParameter("���п�������"));
				int ���п����л� = defaultClass.nullCheck(request.getParameter("���п����л�")) ? 0
						: Integer.parseInt(request.getParameter("���п����л�"));
				int �л������������ӱ��� = defaultClass.nullCheck(request.getParameter("�л������������ӱ���")) ? 0
						: Integer.parseInt(request.getParameter("�л������������ӱ���"));
				int ���л��������ӱ��� = defaultClass.nullCheck(request.getParameter("���л��������ӱ���")) ? 0
						: Integer.parseInt(request.getParameter("���л��������ӱ���"));

				int �л����� = dao.�л�����(�⵵, �а���);
				int �������� = dao.��������(�⵵, �а���);
				int ���л� = dao.���л�(�⵵, �а���);
				String �й��迭 = defaultQuery.�й��迭(�⵵, �а���);
				int �����л����� = �л����� - ��������;

				int �л������� = �����л����� + ���п�������;
				int ���л��� = ���л� + ���п����л�;

				int ����_�к�_���� = ������������(�й��迭, �����л�����);
				int ����_�к�_���л� = ������������(�й��迭, ���л�);
				int ����_���п�_���� = ������������(�й��迭, ���п�������);
				int ����_���п�_���л� = ������������(�й��迭, ���п����л�);
				int ����_��_���� = ������������(�й��迭, �л�������);
				int ����_��_���л� = ������������(�й��迭, ���л���);

				float �л���_���� = �л���_����(�л�������, �л������������ӱ���);
				float �л���_���л� = �л���_���л�(���л���, ���л��������ӱ���);
				float Ȯ����_���� = Ȯ����_����(�л������������ӱ���, ����_��_����);
				float Ȯ����_���л� = Ȯ����_���л�(���л��������ӱ���, ����_��_���л�);

				float ���ӱ���Ȯ������ = Math.min(Ȯ����_����, Ȯ����_���л�);

				���ӱ���Ȯ���� ���ӱ���Ȯ���� = new ���ӱ���Ȯ����();
				���ӱ���Ȯ����.set����(����);
				���ӱ���Ȯ����.set�⵵(�⵵);
				���ӱ���Ȯ����.set�а���(�а���);
				���ӱ���Ȯ����.set����_��_���л�(����_��_���л�);
				���ӱ���Ȯ����.set����_��_����(����_��_����);
				���ӱ���Ȯ����.set����_���п�_���л�(����_���п�_���л�);
				���ӱ���Ȯ����.set����_���п�_����(����_���п�_����);
				���ӱ���Ȯ����.set����_�к�_���л�(����_�к�_���л�);
				���ӱ���Ȯ����.set����_�к�_����(����_�к�_����);
				���ӱ���Ȯ����.set���п�������(���п�������);
				���ӱ���Ȯ����.set���п����л�(���п����л�);
				���ӱ���Ȯ����.set�����л�����(�����л�����);
				���ӱ���Ȯ����.setȮ����_����(Ȯ����_����);
				���ӱ���Ȯ����.setȮ����_���л�(Ȯ����_���л�);
				���ӱ���Ȯ����.set�л������������ӱ���(�л������������ӱ���);
				���ӱ���Ȯ����.set���л��������ӱ���(���л��������ӱ���);
				���ӱ���Ȯ����.set�л���_����(�л���_����);
				���ӱ���Ȯ����.set�л���_���л�(�л���_���л�);
				���ӱ���Ȯ����.set���ӱ���Ȯ����(���ӱ���Ȯ������);

				if (!dao.update(���ӱ���Ȯ����)) {
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

	public float Ȯ����_����(int �л������������ӱ���, int ����_��_����) { //�л����� Ȯ������ �ݿø�����
		float Ȯ�������� = (float)(�л������������ӱ��� / (����_��_���� * 1.0) * 100);
		System.out.println("���������� :" + ����_��_���� + " �л������������ӱ��� :" + �л������������ӱ���);
		return Ȯ��������;
	}

	public float Ȯ����_���л�(int ���л��������ӱ���, int ����_��_���л�) {
		float Ȯ�������л� = (float) (���л��������ӱ��� / (����_��_���л� * 1.0) * 100);
		System.out.println("���������л� :" + ����_��_���л� + "���л��������ӱ��� :" + ���л��������ӱ���);
		return Ȯ�������л�;
	}

	public float �л���_���л�(int ���л���, int ���л��������ӱ���) {
		float �л������л� = (float) (���л��� / (���л��������ӱ��� * 1.0));
		System.out.println("���л��� :" + ���л��� + " ���л��������ӱ��� :" + ���л��������ӱ���);
		return �л������л�;
	}

	public float �л���_����(int �л�������, int �л������������ӱ���) {
		float �л������� = (float) (�л������� / (�л������������ӱ��� * 1.0));
		System.out.println("�л������� :" + �л������� + " �л������������ӱ��� :" + �л������������ӱ���);
		return �л�������;
	}

	public int ������������(String �й��迭, int ��) {
		
		int ����� = 0;
		if (�й��迭.equals("�ι���ȸ")) {
			����� = (int) Math.ceil(�� / 25.0);  //���� �Լ�
			System.out.println("���� ���� ���� ����� :" + �����);
		} else {
			����� = (int) Math.ceil(�� / 20.0);
			System.out.println("���� ���� ��������� :" + �����);
		}
		return �����;
	}

	public void ��������() {
		�⵵ �⵵class = new �⵵();
		int �⵵ = �⵵class.�⵵();

		String column = "���ӱ���Ȯ����";
		String table = "���ӱ���Ȯ����";
		float ��� = defaultQuery.avg(�⵵, column, table);
		float ǥ������ = defaultQuery.std(�⵵, column, table);

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);
		for (String �а��� : �а����) {
			float ���ӱ���Ȯ������ = defaultQuery.����(�⵵, �а���, column, table);

			float T���� = defaultClass.T����(���ӱ���Ȯ������, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}