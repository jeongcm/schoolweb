package 연구실적외부;

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

@WebServlet("/연구실적외부ListAction")
public class 연구실적외부ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));
	
		연구실적외부Dao dao = new 연구실적외부Dao();

		List<연구실적외부> list = dao.select연구실적외부(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		연구실적외부Json 전임교원1인당연구실적dJson = new 연구실적외부Json();
		전임교원1인당연구실적dJson.setTotal(total);
		전임교원1인당연구실적dJson.setRecords(records);
		전임교원1인당연구실적dJson.setPage(page);
		전임교원1인당연구실적dJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(전임교원1인당연구실적dJson);

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
