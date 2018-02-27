package 교원현황;

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


@WebServlet("/교원현황ListAction")
public class 교원현황ListAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
        
        교원현황Dao dao = new 교원현황Dao();
        List<교원현황> list = dao.select교원현황(page, perPageRow);
        int records = dao.getCountRow();
        int total = (int)Math.ceil((double)records/(double)perPageRow);
        
        교원현황Json 교원현황Json = new 교원현황Json();
        교원현황Json.setTotal(total);
        교원현황Json.setRecords(records);
        교원현황Json.setPage(page);
        교원현황Json.setRows(list);
       
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(교원현황Json);
       
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
