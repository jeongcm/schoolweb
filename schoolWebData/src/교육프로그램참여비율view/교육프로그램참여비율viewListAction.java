package 교육프로그램참여비율view;

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

import 교육프로그램참여비율view.교육프로그램참여비율view;
import 교육프로그램참여비율view.교육프로그램참여비율viewDao;
import 교육프로그램참여비율view.교육프로그램참여비율viewJson;

@WebServlet("/교육프로그램참여비율viewListAction")
public class 교육프로그램참여비율viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		교육프로그램참여비율viewDao dao = new 교육프로그램참여비율viewDao();

		List<교육프로그램참여비율view> list = dao.select교육프로그램참여비율view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		교육프로그램참여비율viewJson 교육프로그램참여비율viewJson = new 교육프로그램참여비율viewJson();
		교육프로그램참여비율viewJson.setTotal(total);
		교육프로그램참여비율viewJson.setRecords(records);
		교육프로그램참여비율viewJson.setPage(page);
		교육프로그램참여비율viewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(교육프로그램참여비율viewJson);

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
