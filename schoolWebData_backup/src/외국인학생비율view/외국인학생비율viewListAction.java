package �ܱ����л�����view;

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

import �ܱ����л�����view.�ܱ����л�����view;
import �ܱ����л�����view.�ܱ����л�����viewDao;
import �ܱ����л�����view.�ܱ����л�����viewJson;

@WebServlet("/�ܱ����л�����viewListAction")
public class �ܱ����л�����viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		�ܱ����л�����viewDao dao = new �ܱ����л�����viewDao();

		List<�ܱ����л�����view> list = dao.select�ܱ����л�����view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		�ܱ����л�����viewJson �ܱ����л�����viewJson = new �ܱ����л�����viewJson();
		�ܱ����л�����viewJson.setTotal(total);
		�ܱ����л�����viewJson.setRecords(records);
		�ܱ����л�����viewJson.setPage(page);
		�ܱ����л�����viewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(�ܱ����л�����viewJson);

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