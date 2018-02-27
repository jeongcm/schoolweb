package ���ӱ���Ȯ����;

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

@WebServlet("/���ӱ���Ȯ����ListAction")
public class ���ӱ���Ȯ����ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		���ӱ���Ȯ����Dao dao = new ���ӱ���Ȯ����Dao();

		List<���ӱ���Ȯ����> list = dao.select���ӱ���Ȯ����(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		���ӱ���Ȯ����Json ���ӱ���Ȯ����Json = new ���ӱ���Ȯ����Json();
		���ӱ���Ȯ����Json.setTotal(total);
		���ӱ���Ȯ����Json.setRecords(records);
		���ӱ���Ȯ����Json.setPage(page);
		���ӱ���Ȯ����Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(���ӱ���Ȯ����Json);

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
