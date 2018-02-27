package 수상실적view;

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

import 수상실적view.수상실적;
import 수상실적view.수상실적Dao;
import 수상실적view.수상실적Json;

@WebServlet("/수상실적ListAction")
public class 수상실적ListAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        수상실적Dao dao = new 수상실적Dao();
        List<수상실적> list = dao.select수상실적(page, perPageRow);
        int records = dao.getCountRow();
        int total = (int)Math.ceil((double)records/(double)perPageRow);
        
        수상실적Json 수상실적Json = new 수상실적Json();
        수상실적Json.setTotal(total);
        수상실적Json.setRecords(records);
        수상실적Json.setPage(page);
        수상실적Json.setRows(list);
       
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(수상실적Json);
       
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
