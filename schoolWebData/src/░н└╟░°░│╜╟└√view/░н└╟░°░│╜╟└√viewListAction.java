package ���ǰ�������view;

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

import ���ǰ�������view.���ǰ�������view;
import ���ǰ�������view.���ǰ�������viewDao;
import ���ǰ�������view.���ǰ�������viewJson;

@WebServlet("/���ǰ�������viewListAction")
public class ���ǰ�������viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		���ǰ�������viewDao dao = new ���ǰ�������viewDao();

		List<���ǰ�������view> list = dao.select���ǰ�������view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		���ǰ�������viewJson ���ǰ�������viewJson = new ���ǰ�������viewJson();
		���ǰ�������viewJson.setTotal(total);
		���ǰ�������viewJson.setRecords(records);
		���ǰ�������viewJson.setPage(page);
		���ǰ�������viewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(���ǰ�������viewJson);

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