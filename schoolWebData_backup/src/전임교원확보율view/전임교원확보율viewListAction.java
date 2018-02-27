package 전임교원확보율view;

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

@WebServlet("/전임교원확보율viewListAction")
public class 전임교원확보율viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		전임교원확보율viewDao dao = new 전임교원확보율viewDao();

		List<전임교원확보율view> list = dao.select전임교원확보율(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		전임교원확보율viewJson 전임교원확보율Json = new 전임교원확보율viewJson();
		전임교원확보율Json.setTotal(total);
		전임교원확보율Json.setRecords(records);
		전임교원확보율Json.setPage(page);
		전임교원확보율Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(전임교원확보율Json);

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
