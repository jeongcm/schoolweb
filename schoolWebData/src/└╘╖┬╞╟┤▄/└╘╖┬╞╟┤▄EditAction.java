package �Է��Ǵ�;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/�Է��Ǵ�EditAction")
public class �Է��Ǵ�EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	�Է��Ǵ�Dao dao = new �Է��Ǵ�Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		
			String �Էºμ� = request.getParameter("�Էºμ�");
			
			System.out.println(�Էºμ�);
			
			if (dao.check(�Էºμ�).equals("�Է¿Ϸ�")) { 
				out.write("fail");
				out.flush();
				out.close();
			} 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}