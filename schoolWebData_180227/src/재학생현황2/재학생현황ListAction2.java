package 재학생현황2;

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

@WebServlet("/재학생현황ListAction2")
public class 재학생현황ListAction2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		재학생현황Dao2 dao = new 재학생현황Dao2();

		List<재학생현황2> list = dao.select재학생현황(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		재학생현황Json2 재학생현황Json = new 재학생현황Json2();
		재학생현황Json.setTotal(total);
		재학생현황Json.setRecords(records);
		재학생현황Json.setPage(page);
		재학생현황Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(재학생현황Json);

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
