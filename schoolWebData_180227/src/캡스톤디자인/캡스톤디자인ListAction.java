package ĸ���������;

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

import ĸ���������.ĸ���������;
import ĸ���������.ĸ���������Dao;
import ĸ���������.ĸ���������Json;

@WebServlet("/ĸ���������ListAction")
public class ĸ���������ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		ĸ���������Dao dao = new ĸ���������Dao();

		List<ĸ���������> list = dao.selectĸ���������(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		ĸ���������Json ĸ���������Json = new ĸ���������Json();
		ĸ���������Json.setTotal(total);
		ĸ���������Json.setRecords(records);
		ĸ���������Json.setPage(page);
		ĸ���������Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(ĸ���������Json);

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
