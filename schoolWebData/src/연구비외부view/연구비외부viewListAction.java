package 연구비외부view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/연구비외부viewListAction")
public class 연구비외부viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));
	
		연구비외부viewDao dao = new 연구비외부viewDao();

		List<연구비외부view> list = dao.select전임교원1인당교외연구비dview(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		연구비외부viewJson 전임교원1인당교외연구비dviewJson = new 연구비외부viewJson();
		전임교원1인당교외연구비dviewJson.setTotal(total);
		전임교원1인당교외연구비dviewJson.setRecords(records);
		전임교원1인당교외연구비dviewJson.setPage(page);
		전임교원1인당교외연구비dviewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(전임교원1인당교외연구비dviewJson);

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

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
