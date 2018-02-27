package ÁßµµÅ»¶ô·üview;

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

@WebServlet("/ÁßµµÅ»¶ô·üviewListAction")
public class ÁßµµÅ»¶ô·üviewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		ÁßµµÅ»¶ô·üviewDao dao = new ÁßµµÅ»¶ô·üviewDao();

		List<ÁßµµÅ»¶ô·üview> list = dao.selectÁßµµÅ»¶ô·üview(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		ÁßµµÅ»¶ô·üviewJson ÁßµµÅ»¶ô·üviewJson = new ÁßµµÅ»¶ô·üviewJson();
		ÁßµµÅ»¶ô·üviewJson.setTotal(total);
		ÁßµµÅ»¶ô·üviewJson.setRecords(records);
		ÁßµµÅ»¶ô·üviewJson.setPage(page);
		ÁßµµÅ»¶ô·üviewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(ÁßµµÅ»¶ô·üviewJson);

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
