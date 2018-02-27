package 동아리;

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

import 동아리.동아리;
import 동아리.동아리Dao;
import 동아리.동아리Json;

@WebServlet("/동아리ListAction")
public class 동아리ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	
		
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageRow = Integer.parseInt(request.getParameter("rows"));

		
		동아리Dao dao = new 동아리Dao();

		List<동아리> list = dao.select동아리(page, perPageRow);
		int records = dao.getCountRow();
		int total = (int) Math.ceil((double) records / (double) perPageRow);

		동아리Json 동아리Json = new 동아리Json();
		동아리Json.setTotal(total);
		동아리Json.setRecords(records);
		동아리Json.setPage(page);
		동아리Json.setRows(list);

		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(동아리Json);

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