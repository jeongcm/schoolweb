package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/changePw")

public class changePw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	loginDao dao = new loginDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		System.out.println("system::id´Â"+id);
		String pw = request.getParameter("pw");

		boolean result = dao.changePw(id, pw);

		if (result) {
			PrintWriter out = response.getWriter();
			out.write("change");
			out.flush();
			out.close();

		} else {
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
