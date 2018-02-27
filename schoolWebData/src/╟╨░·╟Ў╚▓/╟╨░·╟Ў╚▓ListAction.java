package �а���Ȳ;

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

@WebServlet("/�а���ȲListAction")
public class �а���ȲListAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        int perPageRow = Integer.parseInt(request.getParameter("rows"));
       
        �а���ȲDao dao = new �а���ȲDao();
        List<�а���Ȳ> list = dao.select�а���Ȳ(page, perPageRow);
        int records = dao.getCountRow();
        int total = (int)Math.ceil((double)records/(double)perPageRow);
       
        �а���ȲJson �а���ȲJson = new �а���ȲJson();
        �а���ȲJson.setTotal(total);
        �а���ȲJson.setRecords(records);
        �а���ȲJson.setPage(page);
        �а���ȲJson.setRows(list);
       
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(�а���ȲJson);
       
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

