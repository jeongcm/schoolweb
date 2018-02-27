package Ư����view;

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

import Ư����view.Ư����view;
import Ư����view.Ư����viewDao;
import Ư����view.Ư����viewJson;

@WebServlet("/Ư����viewListAction")
public class Ư����viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		Ư����viewDao dao = new Ư����viewDao();

		List<Ư����view> list = dao.selectƯ����view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		Ư����viewJson Ư����viewJson = new Ư����viewJson();
		Ư����viewJson.setTotal(total);
		Ư����viewJson.setRecords(records);
		Ư����viewJson.setPage(page);
		Ư����viewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(Ư����viewJson);

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