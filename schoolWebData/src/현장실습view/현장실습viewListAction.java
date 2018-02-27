package ����ǽ�view;

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

import ����ǽ�view.����ǽ�view;
import ����ǽ�view.����ǽ�viewDao;
import ����ǽ�view.����ǽ�viewJson;

@WebServlet("/����ǽ�viewListAction")
public class ����ǽ�viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		����ǽ�viewDao dao = new ����ǽ�viewDao();

		List<����ǽ�view> list = dao.select����ǽ�view(page, perPageRow);
	
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		����ǽ�viewJson ����ǽ�viewJson = new ����ǽ�viewJson();
		����ǽ�viewJson.setTotal(total);
		����ǽ�viewJson.setRecords(records);
		����ǽ�viewJson.setPage(page);
		����ǽ�viewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(����ǽ�viewJson);

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