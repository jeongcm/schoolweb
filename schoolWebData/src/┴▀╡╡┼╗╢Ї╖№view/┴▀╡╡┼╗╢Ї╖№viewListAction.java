package �ߵ�Ż����view;

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

@WebServlet("/�ߵ�Ż����viewListAction")
public class �ߵ�Ż����viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		�ߵ�Ż����viewDao dao = new �ߵ�Ż����viewDao();

		List<�ߵ�Ż����view> list = dao.select�ߵ�Ż����view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		�ߵ�Ż����viewJson �ߵ�Ż����viewJson = new �ߵ�Ż����viewJson();
		�ߵ�Ż����viewJson.setTotal(total);
		�ߵ�Ż����viewJson.setRecords(records);
		�ߵ�Ż����viewJson.setPage(page);
		�ߵ�Ż����viewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(�ߵ�Ż����viewJson);

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
