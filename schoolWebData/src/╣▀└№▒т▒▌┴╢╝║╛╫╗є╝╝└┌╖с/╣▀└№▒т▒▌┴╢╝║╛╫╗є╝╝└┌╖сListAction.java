package 발전기금조성액상세자료;

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

@WebServlet("/발전기금조성액상세자료ListAction")
public class 발전기금조성액상세자료ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		발전기금조성액상세자료Dao dao = new 발전기금조성액상세자료Dao();

		List<발전기금조성액상세자료> list = dao.select발전기금조성액상세자료(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		발전기금조성액상세자료Json 발전기금조성액상세자료Json = new 발전기금조성액상세자료Json();
		발전기금조성액상세자료Json.setTotal(total);
		발전기금조성액상세자료Json.setRecords(records);
		발전기금조성액상세자료Json.setPage(page);
		발전기금조성액상세자료Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(발전기금조성액상세자료Json);

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
