package 외국인학생비율view;

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

import 외국인학생비율view.외국인학생비율view;
import 외국인학생비율view.외국인학생비율viewDao;
import 외국인학생비율view.외국인학생비율viewJson;

@WebServlet("/외국인학생비율viewListAction")
public class 외국인학생비율viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		외국인학생비율viewDao dao = new 외국인학생비율viewDao();

		List<외국인학생비율view> list = dao.select외국인학생비율view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		외국인학생비율viewJson 외국인학생비율viewJson = new 외국인학생비율viewJson();
		외국인학생비율viewJson.setTotal(total);
		외국인학생비율viewJson.setRecords(records);
		외국인학생비율viewJson.setPage(page);
		외국인학생비율viewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(외국인학생비율viewJson);

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