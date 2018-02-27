package ���������ܺ�;

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

@WebServlet("/���������ܺ�ListAction")
public class ���������ܺ�ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));
	
		���������ܺ�Dao dao = new ���������ܺ�Dao();

		List<���������ܺ�> list = dao.select���������ܺ�(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		���������ܺ�Json ���ӱ���1�δ翬������dJson = new ���������ܺ�Json();
		���ӱ���1�δ翬������dJson.setTotal(total);
		���ӱ���1�δ翬������dJson.setRecords(records);
		���ӱ���1�δ翬������dJson.setPage(page);
		���ӱ���1�δ翬������dJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(���ӱ���1�δ翬������dJson);

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
