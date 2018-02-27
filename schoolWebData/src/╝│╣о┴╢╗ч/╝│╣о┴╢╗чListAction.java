package 설문조사;

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

import 설문조사.설문조사;
import 설문조사.설문조사Dao;
import 설문조사.설문조사Json;

@WebServlet("/설문조사ListAction")
public class 설문조사ListAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        설문조사Dao dao = new 설문조사Dao();
        List<설문조사> list = dao.select설문조사(page, perPageRow);
        int records = dao.getCountRow();
        int total = (int)Math.ceil((double)records/(double)perPageRow);
        
        설문조사Json 설문조사Json = new 설문조사Json();
        설문조사Json.setTotal(total);
        설문조사Json.setRecords(records);
        설문조사Json.setPage(page);
        설문조사Json.setRows(list);
       
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(설문조사Json);
       
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
       
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
        
        
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}