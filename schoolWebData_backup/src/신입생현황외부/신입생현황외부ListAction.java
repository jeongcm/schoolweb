package 신입생현황외부;

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

@WebServlet("/신입생현황외부ListAction")
public class 신입생현황외부ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		신입생현황외부Dao dao = new 신입생현황외부Dao();

		List<신입생현황외부> list = dao.select신입생현황외부(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		신입생현황외부Json 신입생현황외부Json = new 신입생현황외부Json();
		신입생현황외부Json.setTotal(total);
		신입생현황외부Json.setRecords(records);
		신입생현황외부Json.setPage(page);
		신입생현황외부Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(신입생현황외부Json);

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
