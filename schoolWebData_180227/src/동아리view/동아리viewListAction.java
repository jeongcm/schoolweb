package 동아리view;

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

import 동아리view.동아리view;
import 동아리view.동아리viewDao;
import 동아리view.동아리viewJson;

@WebServlet("/동아리viewListAction")
public class 동아리viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		동아리viewDao dao = new 동아리viewDao();

		List<동아리view> list = dao.select동아리view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		동아리viewJson 동아리viewJson = new 동아리viewJson();
		동아리viewJson.setTotal(total);
		동아리viewJson.setRecords(records);
		동아리viewJson.setPage(page);
		동아리viewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(동아리viewJson);

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