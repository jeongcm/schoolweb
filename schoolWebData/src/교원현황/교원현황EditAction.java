package 교원현황;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 교원현황.교원현황Dao;
import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;

@WebServlet("/교원현황EditAction")
public class 교원현황EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		
		String oper = request.getParameter("oper");
		
		
		교원현황Dao dao = new 교원현황Dao();
		try {
			if(oper.equals("add")){
				
				String 입력부서 = request.getParameter("입력부서");
				int 년도 = Integer.parseInt(request.getParameter("년도"));
				String 학과명 = request.getParameter("학과명");
				
				int 일학기 = defaultClass.nullCheck(request.getParameter("일학기"))?0:Integer.parseInt(request.getParameter("일학기"));
				int 이학기 = defaultClass.nullCheck(request.getParameter("이학기"))?0:Integer.parseInt(request.getParameter("이학기"));
				
				교원현황 교원현황 = new 교원현황();
				
				교원현황.set년도(년도);
				교원현황.set학과명(학과명);
				교원현황.set일학기(일학기);
				교원현황.set이학기(이학기);
				교원현황.set입력부서(입력부서);
				
				if (!dao.insert(교원현황)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();
				}
				
			} else if (oper.equals("edit")){
				int 년도 = Integer.parseInt(request.getParameter("년도"));
				String 학과명 = request.getParameter("학과명");
				int 일학기 = Integer.parseInt(request.getParameter("일학기"));
				int 이학기 = Integer.parseInt(request.getParameter("이학기"));
				int 연번 = Integer.parseInt(request.getParameter("연번"));
				
				PrintWriter out = response.getWriter();
				
				if (defaultQuery.학과명체크(학과명).equals("0")) {
					
					out.write("failMajor");
					out.flush();
					out.close();
	
				} else if (defaultQuery.년도체크(년도).equals("0")) {
	
					out.write("failYear");
					out.flush();
					out.close();
				} else {
					
					교원현황 교원현황=new 교원현황();
					
					교원현황.set년도(년도);
					교원현황.set학과명(학과명);
					교원현황.set일학기(일학기);
					교원현황.set이학기(이학기);
					교원현황.set연번(연번);
					
					if (!dao.update(교원현황)) {
						out.write("fail");
						out.flush();
						out.close();
					}
				}
				
			} else if(oper.equals("del")){
				
				int 연번 = Integer.parseInt(request.getParameter("연번"));
				dao.delete(연번);
				
			} else if (oper.equals("delAll")) {
				String 입력부서 = request.getParameter("입력부서");
				dao.dellAll(입력부서);
	
			} else {
				System.out.println("잘못된 접근입니다.");
			}
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		doGet(request,response);
	}
}