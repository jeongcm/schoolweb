package ÁßµµÅ»¶ô·ü¿ÜºÎ;

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

@WebServlet("/ÁßµµÅ»¶ô·ü¿ÜºÎListAction")
public class ÁßµµÅ»¶ô·ü¿ÜºÎListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		ÁßµµÅ»¶ô·ü¿ÜºÎDao dao = new ÁßµµÅ»¶ô·ü¿ÜºÎDao();

		List<ÁßµµÅ»¶ô·ü¿ÜºÎ> list = dao.selectÁßµµÅ»¶ô·ü¿ÜºÎ(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		ÁßµµÅ»¶ô·ü¿ÜºÎJson ÁßµµÅ»¶ô·üJson = new ÁßµµÅ»¶ô·ü¿ÜºÎJson();
		ÁßµµÅ»¶ô·üJson.setTotal(total);
		ÁßµµÅ»¶ô·üJson.setRecords(records);
		ÁßµµÅ»¶ô·üJson.setPage(page);
		ÁßµµÅ»¶ô·üJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(ÁßµµÅ»¶ô·üJson);

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
