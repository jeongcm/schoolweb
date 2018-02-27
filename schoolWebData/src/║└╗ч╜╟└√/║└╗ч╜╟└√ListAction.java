package 봉사실적;

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

import 봉사실적.봉사실적;
import 봉사실적.봉사실적Dao;
import 봉사실적.봉사실적Json;

@WebServlet("/봉사실적ListAction")
public class 봉사실적ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		봉사실적Dao dao = new 봉사실적Dao();

		List<봉사실적> list = dao.select봉사실적(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		봉사실적Json 봉사실적Json = new 봉사실적Json();
		봉사실적Json.setTotal(total);
		봉사실적Json.setRecords(records);
		봉사실적Json.setPage(page);
		봉사실적Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(봉사실적Json);

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