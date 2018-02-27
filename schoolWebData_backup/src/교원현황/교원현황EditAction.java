package ������Ȳ;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ������Ȳ.������ȲDao;
import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;

@WebServlet("/������ȲEditAction")
public class ������ȲEditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		
		String oper = request.getParameter("oper");
		
		
		������ȲDao dao = new ������ȲDao();
		try {
			if(oper.equals("add")){
				
				String �Էºμ� = request.getParameter("�Էºμ�");
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
				String �а��� = request.getParameter("�а���");
				
				int ���б� = defaultClass.nullCheck(request.getParameter("���б�"))?0:Integer.parseInt(request.getParameter("���б�"));
				int ���б� = defaultClass.nullCheck(request.getParameter("���б�"))?0:Integer.parseInt(request.getParameter("���б�"));
				
				������Ȳ ������Ȳ = new ������Ȳ();
				
				������Ȳ.set�⵵(�⵵);
				������Ȳ.set�а���(�а���);
				������Ȳ.set���б�(���б�);
				������Ȳ.set���б�(���б�);
				������Ȳ.set�Էºμ�(�Էºμ�);
				
				if (!dao.insert(������Ȳ)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();
				}
				
			} else if (oper.equals("edit")){
				int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
				String �а��� = request.getParameter("�а���");
				int ���б� = Integer.parseInt(request.getParameter("���б�"));
				int ���б� = Integer.parseInt(request.getParameter("���б�"));
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
					
					������Ȳ ������Ȳ=new ������Ȳ();
					
					������Ȳ.set�⵵(�⵵);
					������Ȳ.set�а���(�а���);
					������Ȳ.set���б�(���б�);
					������Ȳ.set���б�(���б�);
					������Ȳ.set����(����);
					
					if (!dao.update(������Ȳ)) {
						out.write("fail");
						out.flush();
						out.close();
					}
				}
				
			} else if(oper.equals("del")){
				
				int ���� = Integer.parseInt(request.getParameter("����"));
				dao.delete(����);
				
			} else if (oper.equals("delAll")) {
				String �Էºμ� = request.getParameter("�Էºμ�");
				dao.dellAll(�Էºμ�);
	
			} else {
				System.out.println("�߸��� �����Դϴ�.");
			}
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		doGet(request,response);
	}
}