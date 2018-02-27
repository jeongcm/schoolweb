package Ư����;

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

import Ư����.Ư����;
import Ư����.Ư����Dao;
import Ư����.Ư����Json;

@WebServlet("/Ư����ListAction")
public class Ư����ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		Ư����Dao dao = new Ư����Dao();

		List<Ư����> list = dao.selectƯ����(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		Ư����Json Ư����Json = new Ư����Json();
		Ư����Json.setTotal(total);
		Ư����Json.setRecords(records);
		Ư����Json.setPage(page);
		Ư����Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(Ư����Json);

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
