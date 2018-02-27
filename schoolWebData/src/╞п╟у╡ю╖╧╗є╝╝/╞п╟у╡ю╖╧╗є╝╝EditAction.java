package Ư���ϻ�;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Ư���ϻ�.Ư���ϻ�;
import Ư���ϻ�.Ư���ϻ�Dao;
import Ư����.Ư����EditAction;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;

@WebServlet("/Ư���ϻ�EditAction")
public class Ư���ϻ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		Ư���ϻ�Dao dao = new Ư���ϻ�Dao();
		Ư����EditAction ea=new Ư����EditAction();
		
		if (oper.equals("add")) {

			int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String �а��� = request.getParameter("�а���");

			String ��ǥ�߸���=request.getParameter("��ǥ�߸���");
			String ��������=request.getParameter("��������");
			String �Էºμ�=request.getParameter("�Էºμ�");
			float ���ױ����=defaultClass.nullCheck(request.getParameter("���ױ����"))?0:Float.parseFloat(request.getParameter("���ױ����"));
			
			
			Ư���ϻ� Ư���ϻ� = new Ư���ϻ�();

			Ư���ϻ�.set�⵵(�⵵);
			Ư���ϻ�.set�а���(�а���);
			Ư���ϻ�.set��ǥ�߸���(��ǥ�߸���);
			Ư���ϻ�.set��������(��������);
			Ư���ϻ�.set���ױ����(���ױ����);
			Ư���ϻ�.set�Էºμ�(�Էºμ�);
			
			if (!dao.insert(Ư���ϻ�)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		}else if (oper.equals("edit")) {
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

				String ��ǥ�߸���=request.getParameter("��ǥ�߸���");
				String ��������=request.getParameter("��������");
				float ���ױ����=defaultClass.nullCheck(request.getParameter("���ױ����"))?0:Float.parseFloat(request.getParameter("���ױ����"));
				
				int ���� = Integer.parseInt(request.getParameter("����"));

				Ư���ϻ� Ư���ϻ� = new Ư���ϻ�();

				Ư���ϻ�.set�⵵(�⵵);
				Ư���ϻ�.set�а���(�а���);
				Ư���ϻ�.set��ǥ�߸���(��ǥ�߸���);
				Ư���ϻ�.set��������(��������);
				Ư���ϻ�.set���ױ����(���ױ����);
				Ư���ϻ�.set����(����);
				
				if(!dao.update(Ư���ϻ�)){
					out.write("fail");
					out.flush();
					out.close();
				}
			}
		} else if (oper.equals("del")) {
			int ���� = Integer.parseInt(request.getParameter("����"));
			dao.delete(����);
			
			ea.��������();
			
		} else if (oper.equals("delAll")) {
			String �Էºμ� = request.getParameter("�Էºμ�");
			dao.dellAll(�Էºμ�);

			ea.��������();
		} else {
			System.out.println("�߸��� �����Դϴ�.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
