package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/changeState")

public class changeState extends HttpServlet {
	private static final long serialVersionUID = 1L;
	loginDao dao = new loginDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		request.setCharacterEncoding("utf-8");
		String major = request.getParameter("major");
		if (dao.부서체크(major).equals("0")) {
			
			out.write("fail");
			out.flush();
			out.close();

		} else {
			boolean result = dao.changeState(major);
			if (result) {
				out.write("change");
				out.flush();
				out.close();
			} else {
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
