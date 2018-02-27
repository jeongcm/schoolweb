package 입력판단;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/입력판단EditAction")
public class 입력판단EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	입력판단Dao dao = new 입력판단Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		
			String 입력부서 = request.getParameter("입력부서");
			
			System.out.println(입력부서);
			
			if (dao.check(입력부서).equals("입력완료")) { 
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