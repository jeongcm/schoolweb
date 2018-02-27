package ĸ���������;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ĸ���������.ĸ���������;
import ĸ���������.ĸ���������Dao;
import defaultMethod.*;

@WebServlet("/ĸ���������EditAction")
public class ĸ���������EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		ĸ���������Dao dao = new ĸ���������Dao();

		try {
			if (oper.equals("add")) {
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
				String �а��� = request.getParameter("�а���");
				int �̼�1�б� = defaultClass.nullCheck(request.getParameter("�̼�1�б�")) ? 0
						: Integer.parseInt(request.getParameter("�̼�1�б�"));
				int �̼�2�б� = defaultClass.nullCheck(request.getParameter("�̼�2�б�")) ? 0
						: Integer.parseInt(request.getParameter("�̼�2�б�"));
				int ���1�б� = defaultClass.nullCheck(request.getParameter("���1�б�")) ? 0
						: Integer.parseInt(request.getParameter("���1�б�"));
				int ���2�б� = defaultClass.nullCheck(request.getParameter("���2�б�")) ? 0
						: Integer.parseInt(request.getParameter("���2�б�"));
				String �Էºμ� = request.getParameter("�Էºμ�");

				int �̼��հ� = �̼�1�б� + �̼�2�б�;
				int ����հ� = ���1�б� + ���2�б�;

				float �̼��л����� = �̼��л�����(�̼��հ�, ����հ�);

				ĸ��������� ĸ��������� = new ĸ���������();

				ĸ���������.set�⵵(�⵵);
				ĸ���������.set�а���(�а���);
				ĸ���������.set�̼�1�б�(�̼�1�б�);
				ĸ���������.set�̼�2�б�(�̼�2�б�);
				ĸ���������.set���1�б�(���1�б�);
				ĸ���������.set���2�б�(���2�б�);
				ĸ���������.set�̼��л�����(�̼��л�����);
				ĸ���������.set�Էºμ�(�Էºμ�);

				if (!dao.insert(ĸ���������)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();
				}

			} else if (oper.equals("cal")||oper.equals("editCal")) {
				
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

					int �̼�1�б� = defaultClass.nullCheck(request.getParameter("�̼�1�б�")) ? 0
							: Integer.parseInt(request.getParameter("�̼�1�б�"));
					int �̼�2�б� = defaultClass.nullCheck(request.getParameter("�̼�2�б�")) ? 0
							: Integer.parseInt(request.getParameter("�̼�2�б�"));
					int ���1�б� = defaultClass.nullCheck(request.getParameter("���1�б�")) ? 0
							: Integer.parseInt(request.getParameter("���1�б�"));
					int ���2�б� = defaultClass.nullCheck(request.getParameter("���2�б�")) ? 0
							: Integer.parseInt(request.getParameter("���2�б�"));

					int ���� = Integer.parseInt(request.getParameter("����"));

					int �̼��հ� = �̼�1�б� + �̼�2�б�;
					int ����հ� = ���1�б� + ���2�б�;

					float �̼��л����� = �̼��л�����(�̼��հ�, ����հ�);

					ĸ��������� ĸ��������� = new ĸ���������();

					ĸ���������.set�⵵(�⵵);
					ĸ���������.set�а���(�а���);
					ĸ���������.set�̼�1�б�(�̼�1�б�);
					ĸ���������.set�̼�2�б�(�̼�2�б�);
					ĸ���������.set���1�б�(���1�б�);
					ĸ���������.set���2�б�(���2�б�);
					ĸ���������.set�̼��л�����(�̼��л�����);
					ĸ���������.set����(����);

					if (!dao.update(ĸ���������)) {
						out.write("fail");
						out.flush();
						out.close();
					}
				}
			}else if (oper.equals("del")) {

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

	public float �̼��л�����(int �̼��հ�, int ����հ�) {
		System.out.println("�̼��հ�"+�̼��հ�+"����հ�"+����հ�);
		float result;
		if( �̼��հ�==0 && ����հ�==0){
			result =0;
		}else
			result = (float) (�̼��հ� / (����հ� * 1.0) * 100);
		float �̼��л�����= result;
		System.out.println("�̼� �հ� :"+�̼��հ�+" ����հ� :"+����հ�+" �̼��л����� ��� ��� :"+result);
		return result;
	}

	public void ��������(){
		�⵵  �⵵class=new �⵵();
		int �⵵=�⵵class.�⵵();
		
		String column = "�̼��л�����";
		String table = "ĸ���������";

		float ��� = (float) defaultQuery.avg(�⵵, column, table);
		float ǥ������ = (float)defaultQuery.std(�⵵, column, table);

		ArrayList<String> �а���� = defaultQuery.�а����(�⵵, table);

		for (String �а��� : �а����) {
			float �̼��л����� = defaultQuery.����(�⵵, �а���, column, table);
			float T���� = defaultClass.T����(�̼��л�����, ���, ǥ������);
			defaultQuery.updateT(T����, �⵵, �а���, table);
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}