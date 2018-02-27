package C�׷�;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import C�׷�.C�׷�;
import C�׷�.C�׷�Dao;

@WebServlet("/C�׷�ListAction")
public class C�׷�ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			C�׷�Dao dao = new C�׷�Dao();
			List<C�׷�> list = dao.selectC�׷�();
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(list);

			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();

			out.write(json);
			out.flush();
			out.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}