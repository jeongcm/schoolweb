package ���л���Ȳ;

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

@WebServlet("/���л���ȲListAction")
public class ���л���ȲListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		���л���ȲDao dao = new ���л���ȲDao();

		List<���л���Ȳ> list = dao.select���л���Ȳ(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		���л���ȲJson ���л���ȲJson = new ���л���ȲJson();
		���л���ȲJson.setTotal(total);
		���л���ȲJson.setRecords(records);
		���л���ȲJson.setPage(page);
		���л���ȲJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(���л���ȲJson);

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
