package 학과현황;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultClass;
import 학과현황.학과현황Dao;
@WebServlet("/학과현황EditAction")

public class 학과현황EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int 수정년도 = 0;
	String 수정학과명 = null;
	학과현황Dao dao = new 학과현황Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");
		
		if (oper.equals("add")) {
			String _5대계열 = request.getParameter("_5대계열");
			수정년도 = Integer.parseInt(request.getParameter("년도"));
			String 단과대학 = request.getParameter("단과대학");
			String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? "" : request.getParameter("비고");
			String 신설연도 =  defaultClass.nullCheck(request.getParameter("신설연도")) ? "" : request.getParameter("신설연도");
			수정학과명 = request.getParameter("학과명");
			String 학문계열1 = request.getParameter("학문계열1");
			String 입력부서 = request.getParameter("입력부서");

			학과현황 학과현황 = new 학과현황();
			학과현황.set_5대계열(_5대계열);
			학과현황.set년도(수정년도);
			학과현황.set단과대학(단과대학);
			학과현황.set비고(비고);
			학과현황.set신설연도(신설연도);
			학과현황.set학과명(수정학과명);
			학과현황.set학문계열1(학문계열1);
			학과현황.set입력부서(입력부서);
			PrintWriter out = response.getWriter();
			if (!dao.insert(학과현황)) {
				out.write("fail");
				out.flush();
				out.close();
			}else{
				dao.비율학과명update(수정년도,수정학과명);
			}

		}  else if (oper.equals("edit")) {
			String _5대계열 = request.getParameter("_5대계열");
			String 단과대학 = request.getParameter("단과대학");
			String 비고 = request.getParameter("비고");
			String 신설연도 = request.getParameter("신설연도");
			String 학문계열1 = request.getParameter("학문계열1");
			수정년도 = Integer.parseInt(request.getParameter("년도"));
			수정학과명 = request.getParameter("학과명");
			int 연번 = Integer.parseInt(request.getParameter("연번"));
			
			학과현황 학과현황 = new 학과현황();
			
			학과현황.set_5대계열(_5대계열);
			학과현황.set단과대학(단과대학);
			학과현황.set비고(비고);
			학과현황.set신설연도(신설연도);
			학과현황.set학문계열1(학문계열1);
			학과현황.set년도(수정년도);
			학과현황.set학과명(수정학과명);
			학과현황.set연번(연번);
			
			dao.update(학과현황);

		} else if (oper.equals("del")) {
			int 연번 = Integer.parseInt(request.getParameter("연번"));
			dao.delete(연번);
		} else if (oper.equals("delAll")) {
			String 입력부서 = request.getParameter("입력부서");
			dao.dellAll(입력부서);

		} else {
			System.out.println("잘못된 접근 입니다.");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}