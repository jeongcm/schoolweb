package 기술이전;

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

import 기술이전.기술이전;
import 기술이전.기술이전Dao;
import 기술이전.기술이전Json;

@WebServlet("/기술이전ListAction")
public class 기술이전ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		기술이전Dao dao = new 기술이전Dao();

		List<기술이전> list = dao.select기술이전(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		기술이전Json 기술이전Json = new 기술이전Json();
		기술이전Json.setTotal(total);
		기술이전Json.setRecords(records);
		기술이전Json.setPage(page);
		기술이전Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(기술이전Json);

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
