package 鏃機睦;

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

@WebServlet("/鏃機睦ListAction")
public class 鏃機睦ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));
		鏃機睦Dao dao = new 鏃機睦Dao();

		List<鏃機睦> list = dao.select鏃機睦(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		鏃機睦Json 鏃機睦Json = new 鏃機睦Json();
		鏃機睦Json.setTotal(total);
		鏃機睦Json.setRecords(records);
		鏃機睦Json.setPage(page);
		鏃機睦Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(鏃機睦Json);

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
