package �а���Ȳ;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultClass;
import �а���Ȳ.�а���ȲDao;
@WebServlet("/�а���ȲEditAction")

public class �а���ȲEditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int �����⵵ = 0;
	String �����а��� = null;
	�а���ȲDao dao = new �а���ȲDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");
		
		if (oper.equals("add")) {
			String _5��迭 = request.getParameter("_5��迭");
			�����⵵ = Integer.parseInt(request.getParameter("�⵵"));
			String �ܰ����� = request.getParameter("�ܰ�����");
			String ��� = defaultClass.nullCheck(request.getParameter("���")) ? "" : request.getParameter("���");
			String �ż����� =  defaultClass.nullCheck(request.getParameter("�ż�����")) ? "" : request.getParameter("�ż�����");
			�����а��� = request.getParameter("�а���");
			String �й��迭1 = request.getParameter("�й��迭1");
			String �Էºμ� = request.getParameter("�Էºμ�");

			�а���Ȳ �а���Ȳ = new �а���Ȳ();
			�а���Ȳ.set_5��迭(_5��迭);
			�а���Ȳ.set�⵵(�����⵵);
			�а���Ȳ.set�ܰ�����(�ܰ�����);
			�а���Ȳ.set���(���);
			�а���Ȳ.set�ż�����(�ż�����);
			�а���Ȳ.set�а���(�����а���);
			�а���Ȳ.set�й��迭1(�й��迭1);
			�а���Ȳ.set�Էºμ�(�Էºμ�);
			PrintWriter out = response.getWriter();
			if (!dao.insert(�а���Ȳ)) {
				out.write("fail");
				out.flush();
				out.close();
			}else{
				dao.�����а���update(�����⵵,�����а���);
			}

		}  else if (oper.equals("edit")) {
			String _5��迭 = request.getParameter("_5��迭");
			String �ܰ����� = request.getParameter("�ܰ�����");
			String ��� = request.getParameter("���");
			String �ż����� = request.getParameter("�ż�����");
			String �й��迭1 = request.getParameter("�й��迭1");
			�����⵵ = Integer.parseInt(request.getParameter("�⵵"));
			�����а��� = request.getParameter("�а���");
			int ���� = Integer.parseInt(request.getParameter("����"));
			
			�а���Ȳ �а���Ȳ = new �а���Ȳ();
			
			�а���Ȳ.set_5��迭(_5��迭);
			�а���Ȳ.set�ܰ�����(�ܰ�����);
			�а���Ȳ.set���(���);
			�а���Ȳ.set�ż�����(�ż�����);
			�а���Ȳ.set�й��迭1(�й��迭1);
			�а���Ȳ.set�⵵(�����⵵);
			�а���Ȳ.set�а���(�����а���);
			�а���Ȳ.set����(����);
			
			dao.update(�а���Ȳ);

		} else if (oper.equals("del")) {
			int ���� = Integer.parseInt(request.getParameter("����"));
			dao.delete(����);
		} else if (oper.equals("delAll")) {
			String �Էºμ� = request.getParameter("�Էºμ�");
			dao.dellAll(�Էºμ�);

		} else {
			System.out.println("�߸��� ���� �Դϴ�.");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}