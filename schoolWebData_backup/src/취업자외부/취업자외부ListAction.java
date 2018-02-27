package ����ڿܺ�;

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

@WebServlet("/����ڿܺ�ListAction")
public class ����ڿܺ�ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		����ڿܺ�Dao dao = new ����ڿܺ�Dao();

		List<����ڿܺ�> list = dao.select����ڿܺ�(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		����ڿܺ�Json �����Json = new ����ڿܺ�Json();
		�����Json.setTotal(total);
		�����Json.setRecords(records);
		�����Json.setPage(page);
		�����Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(�����Json);

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
