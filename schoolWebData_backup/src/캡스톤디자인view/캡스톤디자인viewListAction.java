package ĸ���������view;

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

import ĸ���������view.ĸ���������view;
import ĸ���������view.ĸ���������viewDao;
import ĸ���������view.ĸ���������viewJson;

@WebServlet("/ĸ���������viewListAction")
public class ĸ���������viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		ĸ���������viewDao dao = new ĸ���������viewDao();

		List<ĸ���������view> list = dao.selectĸ���������view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		ĸ���������viewJson ĸ���������viewJson = new ĸ���������viewJson();
		ĸ���������viewJson.setTotal(total);
		ĸ���������viewJson.setRecords(records);
		ĸ���������viewJson.setPage(page);
		ĸ���������viewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(ĸ���������viewJson);

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