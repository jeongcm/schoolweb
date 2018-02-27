package defaultMethod;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import defaultMethod.defaultQuery;

@WebServlet("/defaultEditAction")

public class defaultEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	defaultQuery dao = new defaultQuery();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int �⵵ = Integer.parseInt(request.getParameter("�⵵"));
		String �ܰ����� = request.getParameter("�ܰ�����");

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(defaultQuery.getMajor(�⵵, �ܰ�����));
		

		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}