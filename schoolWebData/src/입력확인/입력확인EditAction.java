package �Է�Ȯ��;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/�Է�Ȯ��EditAction")

public class �Է�Ȯ��EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	�Է�Ȯ��Dao dao = new �Է�Ȯ��Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String �μ� = request.getParameter("�μ�");
		dao.saveAll(�μ�);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
