package �Է�Ȯ��;

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

@WebServlet("/�Է�Ȯ��ListAction")
public class �Է�Ȯ��ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));
		�Է�Ȯ��Dao dao = new �Է�Ȯ��Dao();

		List<�Է�Ȯ��> list = dao.select�Է�Ȯ��(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		�Է�Ȯ��Json �Է�Ȯ��Json = new �Է�Ȯ��Json();
		�Է�Ȯ��Json.setTotal(total);
		�Է�Ȯ��Json.setRecords(records);
		�Է�Ȯ��Json.setPage(page);
		�Է�Ȯ��Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(�Է�Ȯ��Json);

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
