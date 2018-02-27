package ���Ƹ�;

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

import ���Ƹ�.���Ƹ�;
import ���Ƹ�.���Ƹ�Dao;
import ���Ƹ�.���Ƹ�Json;

@WebServlet("/���Ƹ�ListAction")
public class ���Ƹ�ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	
		
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		
		���Ƹ�Dao dao = new ���Ƹ�Dao();

		List<���Ƹ�> list = dao.select���Ƹ�(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		���Ƹ�Json ���Ƹ�Json = new ���Ƹ�Json();
		���Ƹ�Json.setTotal(total);
		���Ƹ�Json.setRecords(records);
		���Ƹ�Json.setPage(page);
		���Ƹ�Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(���Ƹ�Json);

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