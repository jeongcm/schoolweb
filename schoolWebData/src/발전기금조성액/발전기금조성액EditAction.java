package 발전기금조성액;

import java.io.IOException;

import java.io.PrintWriter;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 발전기금조성액.발전기금조성액Dao;
import 발전기금조성액상세자료.발전기금조성액상세자료EditAction;
import defaultMethod.defaultQuery;

@WebServlet("/발전기금조성액EditAction")
public class 발전기금조성액EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	발전기금조성액Dao dao = new 발전기금조성액Dao();
	발전기금조성액상세자료EditAction ea = new 발전기금조성액상세자료EditAction();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		if (oper.equals("add")) {

			String 학과명 = request.getParameter("학과명");
			int 년도 = Integer.parseInt(request.getParameter("년도"));

			int 지정기부금 = defaultClass.nullCheck(request.getParameter("지정기부금")) ? 0
					: Integer.parseInt(request.getParameter("지정기부금"));

			int 전임교원수 = defaultQuery.전임교원수(년도, 학과명);
			int 발전기금모금액 = dao.발전기금모금액(년도, 학과명);

			String 입력부서 = request.getParameter("입력부서");

			float 발전기금조성액계산 = ea.발전기금조성액(전임교원수, 발전기금모금액, 지정기부금);

			발전기금조성액 발전기금조성액 = new 발전기금조성액();

			발전기금조성액.set년도(년도);
			발전기금조성액.set학과명(학과명);
			발전기금조성액.set지정기부금(지정기부금);
			발전기금조성액.set입력부서(입력부서);
			발전기금조성액.set발전기금조성액계산(발전기금조성액계산);

			if (!dao.insert(발전기금조성액)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		} else if (oper.equals("cal") || oper.equals("editCal")) {

			ea.비율재계산();

		} else if (oper.equals("edit")) {
			int 년도 = Integer.parseInt(request.getParameter("년도"));
			String 학과명 = request.getParameter("학과명");

			int 지정기부금 = defaultClass.nullCheck(request.getParameter("지정기부금")) ? 0
					: Integer.parseInt(request.getParameter("지정기부금"));
			int 전임교원수 = defaultQuery.전임교원수(년도, 학과명);
			int 발전기금모금액 = dao.발전기금모금액(년도, 학과명);
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

				발전기금조성액 발전기금조성액 = new 발전기금조성액();

				float 발전기금조성액계산 = ea.발전기금조성액(전임교원수, 발전기금모금액, 지정기부금);

				발전기금조성액.set지정기부금(지정기부금);
				발전기금조성액.set발전기금조성액계산(발전기금조성액계산);
				발전기금조성액.set년도(년도);
				발전기금조성액.set학과명(학과명);
				발전기금조성액.set연번(연번);

				if (!dao.update(발전기금조성액)) {
					out.write("fail");
					out.flush();
					out.close();
				}
			}
		} else if (oper.equals("del")) {

			int 연번 = Integer.parseInt(request.getParameter("연번"));
			dao.delete(연번);

			ea.비율재계산();

		} else if (oper.equals("delAll")) {
			String 입력부서 = request.getParameter("입력부서");
			dao.dellAll(입력부서);

			ea.비율재계산();

		} else {
			System.out.println("잘못된 접근 입니다.");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}