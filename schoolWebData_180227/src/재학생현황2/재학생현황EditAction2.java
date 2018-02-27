package 재학생현황2;

import java.io.IOException;

import java.io.PrintWriter;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 재학생현황2.재학생현황Dao2;
import defaultMethod.defaultQuery;

@WebServlet("/재학생현황EditAction2")
public class 재학생현황EditAction2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int 수정년도 = 0;
	String 수정학과명 = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		재학생현황Dao2 dao = new 재학생현황Dao2();
		if (oper.equals("add")) {

			수정학과명 = request.getParameter("학과명");
			수정년도 = Integer.parseInt(request.getParameter("년도"));

			int 학생수 = defaultClass.nullCheck(request.getParameter("학생수")) ? 0
					: Integer.parseInt(request.getParameter("학생수"));
			String 입력부서 = request.getParameter("입력부서");

			재학생현황2 재학생현황 = new 재학생현황2();

			재학생현황.set학과명(수정학과명);
			재학생현황.set년도(수정년도);
			재학생현황.set학생수(학생수);
			재학생현황.set입력부서(입력부서);

			if (!dao.insert(재학생현황)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		} else if (oper.equals("edit")) {
			수정학과명 = request.getParameter("학과명");
			수정년도 = Integer.parseInt(request.getParameter("년도"));

			int 학생수 = defaultClass.nullCheck(request.getParameter("학생수")) ? 0
					: Integer.parseInt(request.getParameter("학생수"));
			int 연번 = Integer.parseInt(request.getParameter("연번"));

			PrintWriter out = response.getWriter();

			if (defaultQuery.학과명체크(수정학과명).equals("0")) {

				out.write("failMajor");
				out.flush();
				out.close();

			} else if (defaultQuery.년도체크(수정년도).equals("0")) {

				out.write("failYear");
				out.flush();
				out.close();
			} else {

				재학생현황2 재학생현황 = new 재학생현황2();

				재학생현황.set년도(수정년도);
				재학생현황.set학과명(수정학과명);
				재학생현황.set학생수(학생수);
				재학생현황.set연번(연번);

				if (!dao.update(재학생현황)) {
					out.write("fail");
					out.flush();
					out.close();
				}
			}
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