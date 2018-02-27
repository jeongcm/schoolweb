package ���ǰ�������;

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

import ���ǰ�������.���ǰ�������;
import ���ǰ�������.���ǰ�������Dao;
import ���ǰ�������.���ǰ�������Json;

@WebServlet("/���ǰ�������ListAction")
public class ���ǰ�������ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		���ǰ�������Dao dao = new ���ǰ�������Dao();

		List<���ǰ�������> list = dao.select���ǰ�������(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		���ǰ�������Json ���ǰ�������Json = new ���ǰ�������Json();
		���ǰ�������Json.setTotal(total);
		���ǰ�������Json.setRecords(records);
		���ǰ�������Json.setPage(page);
		���ǰ�������Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(���ǰ�������Json);

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