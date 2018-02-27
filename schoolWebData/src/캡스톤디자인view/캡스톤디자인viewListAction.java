package Ä¸½ºÅæµğÀÚÀÎview;

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

import Ä¸½ºÅæµğÀÚÀÎview.Ä¸½ºÅæµğÀÚÀÎview;
import Ä¸½ºÅæµğÀÚÀÎview.Ä¸½ºÅæµğÀÚÀÎviewDao;
import Ä¸½ºÅæµğÀÚÀÎview.Ä¸½ºÅæµğÀÚÀÎviewJson;

@WebServlet("/Ä¸½ºÅæµğÀÚÀÎviewListAction")
public class Ä¸½ºÅæµğÀÚÀÎviewListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		Ä¸½ºÅæµğÀÚÀÎviewDao dao = new Ä¸½ºÅæµğÀÚÀÎviewDao();

		List<Ä¸½ºÅæµğÀÚÀÎview> list = dao.selectÄ¸½ºÅæµğÀÚÀÎview(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		Ä¸½ºÅæµğÀÚÀÎviewJson Ä¸½ºÅæµğÀÚÀÎviewJson = new Ä¸½ºÅæµğÀÚÀÎviewJson();
		Ä¸½ºÅæµğÀÚÀÎviewJson.setTotal(total);
		Ä¸½ºÅæµğÀÚÀÎviewJson.setRecords(records);
		Ä¸½ºÅæµğÀÚÀÎviewJson.setPage(page);
		Ä¸½ºÅæµğÀÚÀÎviewJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(Ä¸½ºÅæµğÀÚÀÎviewJson);

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