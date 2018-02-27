package 설문조사view;

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

import 설문조사view.설문조사view;
import 설문조사view.설문조사viewDao;
import 설문조사view.설문조사viewJson;

@WebServlet("/설문조사viewListAction")
public class 설문조사viewListAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        설문조사viewDao dao = new 설문조사viewDao();
        List<설문조사view> list = dao.select설문조사view(page, perPageRow);
        int records = dao.getCountRow();
        int total = (int)Math.ceil((double)records/(double)perPageRow);
        
        설문조사viewJson 설문조사viewJson = new 설문조사viewJson();
        설문조사viewJson.setTotal(total);
        설문조사viewJson.setRecords(records);
        설문조사viewJson.setPage(page);
        설문조사viewJson.setRows(list);
       
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(설문조사viewJson);
       
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