package 학과현황;

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

@WebServlet("/학과현황ListAction")
public class 학과현황ListAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
       
        학과현황Dao dao = new 학과현황Dao();
        List<학과현황> list = dao.select학과현황(page, perPageRow);
        int records = dao.getCountRow();
        int total = (int)Math.ceil((double)records/(double)perPageRow);
       
        학과현황Json 학과현황Json = new 학과현황Json();
        학과현황Json.setTotal(total);
        학과현황Json.setRecords(records);
        학과현황Json.setPage(page);
        학과현황Json.setRows(list);
       
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(학과현황Json);
       
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

