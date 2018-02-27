package �ߵ�Ż�����ܺ�;

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

@WebServlet("/�ߵ�Ż�����ܺ�ListAction")
public class �ߵ�Ż�����ܺ�ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		�ߵ�Ż�����ܺ�Dao dao = new �ߵ�Ż�����ܺ�Dao();

		List<�ߵ�Ż�����ܺ�> list = dao.select�ߵ�Ż�����ܺ�(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		�ߵ�Ż�����ܺ�Json �ߵ�Ż����Json = new �ߵ�Ż�����ܺ�Json();
		�ߵ�Ż����Json.setTotal(total);
		�ߵ�Ż����Json.setRecords(records);
		�ߵ�Ż����Json.setPage(page);
		�ߵ�Ż����Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(�ߵ�Ż����Json);

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
