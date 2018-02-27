package ���ӱ���1�δ籹å������ֽ���;

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

@WebServlet("/���ӱ���1�δ籹å������ֽ���ListAction")
public class ���ӱ���1�δ籹å������ֽ���ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		���ӱ���1�δ籹å������ֽ���Dao dao = new ���ӱ���1�δ籹å������ֽ���Dao();

		List<���ӱ���1�δ籹å������ֽ���> list = dao.select���ӱ���1�δ籹å������ֽ���(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		���ӱ���1�δ籹å������ֽ���Json ���ӱ���1�δ籹å������ֽ���Json = new ���ӱ���1�δ籹å������ֽ���Json();
		���ӱ���1�δ籹å������ֽ���Json.setTotal(total);
		���ӱ���1�δ籹å������ֽ���Json.setRecords(records);
		���ӱ���1�δ籹å������ֽ���Json.setPage(page);
		���ӱ���1�δ籹å������ֽ���Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(���ӱ���1�δ籹å������ֽ���Json);

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
