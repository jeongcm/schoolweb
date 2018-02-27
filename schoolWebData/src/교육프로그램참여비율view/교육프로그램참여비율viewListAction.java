package �������α׷���������view;

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

import �������α׷���������view.�������α׷���������view;
import �������α׷���������view.�������α׷���������viewDao;
import �������α׷���������view.�������α׷���������viewJson;

@WebServlet("/�������α׷���������viewListAction")
public class �������α׷���������viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		�������α׷���������viewDao dao = new �������α׷���������viewDao();

		List<�������α׷���������view> list = dao.select�������α׷���������view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		�������α׷���������viewJson �������α׷���������viewJson = new �������α׷���������viewJson();
		�������α׷���������viewJson.setTotal(total);
		�������α׷���������viewJson.setRecords(records);
		�������α׷���������viewJson.setPage(page);
		�������α׷���������viewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(�������α׷���������viewJson);

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
