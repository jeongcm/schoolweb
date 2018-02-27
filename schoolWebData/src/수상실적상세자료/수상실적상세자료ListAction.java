package 수상실적상세자료;

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

import 수상실적상세자료.수상실적상세자료;
import 수상실적상세자료.수상실적상세자료Dao;
import 수상실적상세자료.수상실적상세자료Json;

@WebServlet("/수상실적상세자료ListAction")
public class 수상실적상세자료ListAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        수상실적상세자료Dao dao = new 수상실적상세자료Dao();
        List<수상실적상세자료> list = dao.select수상실적상세자료(page, perPageRow);
        int records = dao.getCountRow();
        int total = (int)Math.ceil((double)records/(double)perPageRow);
        
        수상실적상세자료Json 수상실적상세자료Json = new 수상실적상세자료Json();
        수상실적상세자료Json.setTotal(total);
        수상실적상세자료Json.setRecords(records);
        수상실적상세자료Json.setPage(page);
        수상실적상세자료Json.setRows(list);
       
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(수상실적상세자료Json);
       
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
