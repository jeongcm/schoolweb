package ºÀ»ç½ÇÀûview;

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

import ºÀ»ç½ÇÀûview.ºÀ»ç½ÇÀûview;
import ºÀ»ç½ÇÀûview.ºÀ»ç½ÇÀûviewDao;
import ºÀ»ç½ÇÀûview.ºÀ»ç½ÇÀûviewJson;

@WebServlet("/ºÀ»ç½ÇÀûviewListAction")
public class ºÀ»ç½ÇÀûviewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		ºÀ»ç½ÇÀûviewDao dao = new ºÀ»ç½ÇÀûviewDao();

		List<ºÀ»ç½ÇÀûview> list = dao.selectºÀ»ç½ÇÀûview(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		ºÀ»ç½ÇÀûviewJson ºÀ»ç½ÇÀûviewJson = new ºÀ»ç½ÇÀûviewJson();
		ºÀ»ç½ÇÀûviewJson.setTotal(total);
		ºÀ»ç½ÇÀûviewJson.setRecords(records);
		ºÀ»ç½ÇÀûviewJson.setPage(page);
		ºÀ»ç½ÇÀûviewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(ºÀ»ç½ÇÀûviewJson);

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
