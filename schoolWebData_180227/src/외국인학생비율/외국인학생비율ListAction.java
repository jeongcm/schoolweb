package �ܱ����л�����;

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

import �ܱ����л�����.�ܱ����л�����;
import �ܱ����л�����.�ܱ����л�����Dao;
import �ܱ����л�����.�ܱ����л�����Json;

@WebServlet("/�ܱ����л�����ListAction")
public class �ܱ����л�����ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		�ܱ����л�����Dao dao = new �ܱ����л�����Dao();

		List<�ܱ����л�����> list = dao.select�ܱ����л�����(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		�ܱ����л�����Json �ܱ����л�����Json = new �ܱ����л�����Json();
		�ܱ����л�����Json.setTotal(total);
		�ܱ����л�����Json.setRecords(records);
		�ܱ����л�����Json.setPage(page);
		�ܱ����л�����Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(�ܱ����л�����Json);

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