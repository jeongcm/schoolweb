package 특허등록;

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

import 특허등록.특허등록;
import 특허등록.특허등록Dao;
import 특허등록.특허등록Json;

@WebServlet("/특허등록ListAction")
public class 특허등록ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		특허등록Dao dao = new 특허등록Dao();

		List<특허등록> list = dao.select특허등록(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		특허등록Json 특허등록Json = new 특허등록Json();
		특허등록Json.setTotal(total);
		특허등록Json.setRecords(records);
		특허등록Json.setPage(page);
		특허등록Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(특허등록Json);

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
