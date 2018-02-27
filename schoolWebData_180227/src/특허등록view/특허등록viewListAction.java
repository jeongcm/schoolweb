package 특허등록view;

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

import 특허등록view.특허등록view;
import 특허등록view.특허등록viewDao;
import 특허등록view.특허등록viewJson;

@WebServlet("/특허등록viewListAction")
public class 특허등록viewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		특허등록viewDao dao = new 특허등록viewDao();

		List<특허등록view> list = dao.select특허등록view(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		특허등록viewJson 특허등록viewJson = new 특허등록viewJson();
		특허등록viewJson.setTotal(total);
		특허등록viewJson.setRecords(records);
		특허등록viewJson.setPage(page);
		특허등록viewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(특허등록viewJson);

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