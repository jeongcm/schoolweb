package 현장실습;

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

import 현장실습.현장실습;
import 현장실습.현장실습Dao;
import 현장실습.현장실습Json;

@WebServlet("/현장실습ListAction")
public class 현장실습ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		현장실습Dao dao = new 현장실습Dao();

		List<현장실습> list = dao.select현장실습(page, perPageRow);
	
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		현장실습Json 현장실습Json = new 현장실습Json();
		현장실습Json.setTotal(total);
		현장실습Json.setRecords(records);
		현장실습Json.setPage(page);
		현장실습Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(현장실습Json);

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