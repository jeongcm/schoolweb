package 교외장학금view;

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

@WebServlet("/교외장학금viewListAction")
public class 교외장학금viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		교외장학금viewDao dao = new 교외장학금viewDao();

		List<교외장학금view> list = dao.select교외장학금view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		교외장학금viewJson 교외장학금Json = new 교외장학금viewJson();
		교외장학금Json.setTotal(total);
		교외장학금Json.setRecords(records);
		교외장학금Json.setPage(page);
		교외장학금Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(교외장학금Json);

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
