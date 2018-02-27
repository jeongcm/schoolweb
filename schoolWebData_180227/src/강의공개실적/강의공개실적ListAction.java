package 강의공개실적;

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

import 강의공개실적.강의공개실적;
import 강의공개실적.강의공개실적Dao;
import 강의공개실적.강의공개실적Json;

@WebServlet("/강의공개실적ListAction")
public class 강의공개실적ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		강의공개실적Dao dao = new 강의공개실적Dao();

		List<강의공개실적> list = dao.select강의공개실적(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		강의공개실적Json 강의공개실적Json = new 강의공개실적Json();
		강의공개실적Json.setTotal(total);
		강의공개실적Json.setRecords(records);
		강의공개실적Json.setPage(page);
		강의공개실적Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(강의공개실적Json);

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