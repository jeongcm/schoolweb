package 전임교원1인당국책사업수주실적View;

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

@WebServlet("/전임교원1인당국책사업수주실적ViewListAction")
public class 수주실적ViewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));
		수주실적ViewDao dao = new 수주실적ViewDao();

		List<수주실적View> list = dao.select수주실적view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		수주실적ViewJson 수주실적ViewJson = new 수주실적ViewJson();
		수주실적ViewJson.setTotal(total);
		수주실적ViewJson.setRecords(records);
		수주실적ViewJson.setPage(page);
		수주실적ViewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(수주실적ViewJson);

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
