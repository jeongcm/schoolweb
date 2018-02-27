package ���������ܺ�view;

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

import ���������ܺ�view.���������ܺ�view;
import ���������ܺ�view.���������ܺ�viewDao;
import ���������ܺ�view.���������ܺ�viewJson;

@WebServlet("/���������ܺ�viewListAction")
public class ���������ܺ�viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		���������ܺ�viewDao dao = new ���������ܺ�viewDao();

		List<���������ܺ�view> list = dao.select���ӱ���1�δ翬������dview(page, perPageRow);
	
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		���������ܺ�viewJson ���ӱ���1�δ翬������dviewJson = new ���������ܺ�viewJson();
		���ӱ���1�δ翬������dviewJson.setTotal(total);
		���ӱ���1�δ翬������dviewJson.setRecords(records);
		���ӱ���1�δ翬������dviewJson.setPage(page);
		���ӱ���1�δ翬������dviewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(���ӱ���1�δ翬������dviewJson);

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