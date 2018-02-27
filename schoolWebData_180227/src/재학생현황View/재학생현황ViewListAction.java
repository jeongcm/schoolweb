package ���л���ȲView;

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

@WebServlet("/���л���ȲViewListAction")
public class ���л���ȲViewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));
		���л���ȲViewDao dao = new ���л���ȲViewDao();

		List<���л���ȲView> list = dao.select���л���ȲView(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		���л���ȲViewJson ���л���ȲViewJson = new ���л���ȲViewJson();
		���л���ȲViewJson.setTotal(total);
		���л���ȲViewJson.setRecords(records);
		���л���ȲViewJson.setPage(page);
		���л���ȲViewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(���л���ȲViewJson);

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
