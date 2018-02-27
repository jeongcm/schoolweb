package ���л���Ȳ2;

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

@WebServlet("/���л���ȲListAction2")
public class ���л���ȲListAction2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		���л���ȲDao2 dao = new ���л���ȲDao2();

		List<���л���Ȳ2> list = dao.select���л���Ȳ(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		���л���ȲJson2 ���л���ȲJson = new ���л���ȲJson2();
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
