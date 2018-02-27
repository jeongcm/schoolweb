package ���Ƹ�view;

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

import ���Ƹ�view.���Ƹ�view;
import ���Ƹ�view.���Ƹ�viewDao;
import ���Ƹ�view.���Ƹ�viewJson;

@WebServlet("/���Ƹ�viewListAction")
public class ���Ƹ�viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		���Ƹ�viewDao dao = new ���Ƹ�viewDao();

		List<���Ƹ�view> list = dao.select���Ƹ�view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		���Ƹ�viewJson ���Ƹ�viewJson = new ���Ƹ�viewJson();
		���Ƹ�viewJson.setTotal(total);
		���Ƹ�viewJson.setRecords(records);
		���Ƹ�viewJson.setPage(page);
		���Ƹ�viewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(���Ƹ�viewJson);

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