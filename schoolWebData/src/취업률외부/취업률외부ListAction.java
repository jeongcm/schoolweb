package Ãë¾÷·ü¿ÜºÎ;

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

@WebServlet("/Ãë¾÷·ü¿ÜºÎListAction")
public class Ãë¾÷·ü¿ÜºÎListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));
		Ãë¾÷·ü¿ÜºÎDao dao = new Ãë¾÷·ü¿ÜºÎDao();

		List<Ãë¾÷·ü¿ÜºÎ> list = dao.selectÃë¾÷·ü¿ÜºÎ(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		Ãë¾÷·ü¿ÜºÎJson Ãë¾÷·üJson = new Ãë¾÷·ü¿ÜºÎJson();
		Ãë¾÷·üJson.setTotal(total);
		Ãë¾÷·üJson.setRecords(records);
		Ãë¾÷·üJson.setPage(page);
		Ãë¾÷·üJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(Ãë¾÷·üJson);

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
