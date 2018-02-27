package 강의담당외부view;

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

@WebServlet("/강의담당외부viewListAction")
public class 강의담당외부viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		강의담당외부viewDao dao = new 강의담당외부viewDao();

		List<강의담당외부view> list = dao.select강의담당view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		강의담당외부viewJson 강의담당Json = new 강의담당외부viewJson();
		강의담당Json.setTotal(total);
		강의담당Json.setRecords(records);
		강의담당Json.setPage(page);
		강의담당Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(강의담당Json);

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
