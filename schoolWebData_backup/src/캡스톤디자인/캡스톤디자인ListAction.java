package Ä¸½ºÅæµğÀÚÀÎ;

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

import Ä¸½ºÅæµğÀÚÀÎ.Ä¸½ºÅæµğÀÚÀÎ;
import Ä¸½ºÅæµğÀÚÀÎ.Ä¸½ºÅæµğÀÚÀÎDao;
import Ä¸½ºÅæµğÀÚÀÎ.Ä¸½ºÅæµğÀÚÀÎJson;

@WebServlet("/Ä¸½ºÅæµğÀÚÀÎListAction")
public class Ä¸½ºÅæµğÀÚÀÎListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		Ä¸½ºÅæµğÀÚÀÎDao dao = new Ä¸½ºÅæµğÀÚÀÎDao();

		List<Ä¸½ºÅæµğÀÚÀÎ> list = dao.selectÄ¸½ºÅæµğÀÚÀÎ(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		Ä¸½ºÅæµğÀÚÀÎJson Ä¸½ºÅæµğÀÚÀÎJson = new Ä¸½ºÅæµğÀÚÀÎJson();
		Ä¸½ºÅæµğÀÚÀÎJson.setTotal(total);
		Ä¸½ºÅæµğÀÚÀÎJson.setRecords(records);
		Ä¸½ºÅæµğÀÚÀÎJson.setPage(page);
		Ä¸½ºÅæµğÀÚÀÎJson.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(Ä¸½ºÅæµğÀÚÀÎJson);

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
