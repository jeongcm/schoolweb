package 전임교원1인당국책사업수주실적;

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

@WebServlet("/전임교원1인당국책사업수주실적ListAction")
public class 전임교원1인당국책사업수주실적ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		전임교원1인당국책사업수주실적Dao dao = new 전임교원1인당국책사업수주실적Dao();

		List<전임교원1인당국책사업수주실적> list = dao.select전임교원1인당국책사업수주실적(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		전임교원1인당국책사업수주실적Json 전임교원1인당국책사업수주실적Json = new 전임교원1인당국책사업수주실적Json();
		전임교원1인당국책사업수주실적Json.setTotal(total);
		전임교원1인당국책사업수주실적Json.setRecords(records);
		전임교원1인당국책사업수주실적Json.setPage(page);
		전임교원1인당국책사업수주실적Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(전임교원1인당국책사업수주실적Json);

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
