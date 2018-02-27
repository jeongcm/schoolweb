package ���Ի���Ȳ;

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

@WebServlet("/���Ի���ȲListAction")
public class ���Ի���ȲListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		���Ի���ȲDao dao = new ���Ի���ȲDao();

		List<���Ի���Ȳ> list = dao.select���Ի���Ȳ(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		���Ի���ȲJson ���Ի���ȲJson = new ���Ի���ȲJson();
		���Ի���ȲJson.setTotal(total);
		���Ի���ȲJson.setRecords(records);
		���Ի���ȲJson.setPage(page);
		���Ի���ȲJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(���Ի���ȲJson);

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
