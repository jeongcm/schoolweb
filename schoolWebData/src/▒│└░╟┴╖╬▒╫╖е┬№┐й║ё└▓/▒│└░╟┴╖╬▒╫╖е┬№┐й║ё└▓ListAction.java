package 교육프로그램참여비율;

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

import 교육프로그램참여비율.교육프로그램참여비율;
import 교육프로그램참여비율.교육프로그램참여비율Dao;
import 교육프로그램참여비율.교육프로그램참여비율Json;

@WebServlet("/교육프로그램참여비율ListAction")
public class 교육프로그램참여비율ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		교육프로그램참여비율Dao dao = new 교육프로그램참여비율Dao();

		List<교육프로그램참여비율> list = dao.select교육프로그램참여비율(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		교육프로그램참여비율Json 교육프로그램참여비율Json = new 교육프로그램참여비율Json();
		교육프로그램참여비율Json.setTotal(total);
		교육프로그램참여비율Json.setRecords(records);
		교육프로그램참여비율Json.setPage(page);
		교육프로그램참여비율Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(교육프로그램참여비율Json);

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
