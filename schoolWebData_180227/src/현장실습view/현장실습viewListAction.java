package 현장실습view;

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

import 현장실습view.현장실습view;
import 현장실습view.현장실습viewDao;
import 현장실습view.현장실습viewJson;

@WebServlet("/현장실습viewListAction")
public class 현장실습viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		현장실습viewDao dao = new 현장실습viewDao();

		List<현장실습view> list = dao.select현장실습view(page, perPageRow);
	
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		현장실습viewJson 현장실습viewJson = new 현장실습viewJson();
		현장실습viewJson.setTotal(total);
		현장실습viewJson.setRecords(records);
		현장실습viewJson.setPage(page);
		현장실습viewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(현장실습viewJson);

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