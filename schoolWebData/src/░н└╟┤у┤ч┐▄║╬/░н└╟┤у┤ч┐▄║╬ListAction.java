package ���Ǵ��ܺ�;

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

@WebServlet("/���Ǵ��ܺ�ListAction")
public class ���Ǵ��ܺ�ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		���Ǵ��ܺ�Dao dao = new ���Ǵ��ܺ�Dao();

		List<���Ǵ��ܺ�> list = dao.select���Ǵ��ܺ�(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		���Ǵ��ܺ�Json ���Ǵ��ܺ�Json = new ���Ǵ��ܺ�Json();
		���Ǵ��ܺ�Json.setTotal(total);
		���Ǵ��ܺ�Json.setRecords(records);
		���Ǵ��ܺ�Json.setPage(page);
		���Ǵ��ܺ�Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(���Ǵ��ܺ�Json);

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
