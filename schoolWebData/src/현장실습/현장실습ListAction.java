package ����ǽ�;

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

import ����ǽ�.����ǽ�;
import ����ǽ�.����ǽ�Dao;
import ����ǽ�.����ǽ�Json;

@WebServlet("/����ǽ�ListAction")
public class ����ǽ�ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		����ǽ�Dao dao = new ����ǽ�Dao();

		List<����ǽ�> list = dao.select����ǽ�(page, perPageRow);
	
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		����ǽ�Json ����ǽ�Json = new ����ǽ�Json();
		����ǽ�Json.setTotal(total);
		����ǽ�Json.setRecords(records);
		����ǽ�Json.setPage(page);
		����ǽ�Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(����ǽ�Json);

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