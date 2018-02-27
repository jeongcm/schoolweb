package 전임교원1인당국책사업수주실적;

import java.io.IOException;
import defaultMethod.년도;

import java.io.PrintWriter;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 전임교원1인당국책사업수주실적.전임교원1인당국책사업수주실적Dao;
import defaultMethod.defaultQuery;

@WebServlet("/전임교원1인당국책사업수주실적EditAction")
public class 전임교원1인당국책사업수주실적EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	전임교원1인당국책사업수주실적Dao dao = new 전임교원1인당국책사업수주실적Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		if (oper.equals("add")) {

			String 학과명 = request.getParameter("학과명");
			int 년도 = Integer.parseInt(request.getParameter("년도"));

			int 대학명의국책사업수주총액 = defaultClass.nullCheck(request.getParameter("대학명의국책사업수주총액")) ? 0
					: Integer.parseInt(request.getParameter("대학명의국책사업수주총액"));

			int 전임교원수 = defaultQuery.전임교원수(년도, 학과명);

			String 입력부서 = request.getParameter("입력부서");

			Float 전임교원1인당국책사업수주총액계산 = 전임교원1인당국책사업수주총액(전임교원수, 대학명의국책사업수주총액);

			전임교원1인당국책사업수주실적 전임교원1인당국책사업수주실적 = new 전임교원1인당국책사업수주실적();

			전임교원1인당국책사업수주실적.set년도(년도);
			전임교원1인당국책사업수주실적.set학과명(학과명);
			전임교원1인당국책사업수주실적.set대학명의국책사업수주총액(대학명의국책사업수주총액);
			전임교원1인당국책사업수주실적.set입력부서(입력부서);
			전임교원1인당국책사업수주실적.set전임교원1인당국책사업수주총액(전임교원1인당국책사업수주총액계산);

			if (!dao.insert(전임교원1인당국책사업수주실적)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		} else if (oper.equals("cal") || oper.equals("editCal")) {

			비율재계산();

		} else if (oper.equals("edit")) {
			int 년도 = Integer.parseInt(request.getParameter("년도"));
			String 학과명 = request.getParameter("학과명");

			int 대학명의국책사업수주총액 = defaultClass.nullCheck(request.getParameter("대학명의국책사업수주총액")) ? 0
					: Integer.parseInt(request.getParameter("대학명의국책사업수주총액"));
			int 전임교원수 = defaultQuery.전임교원수(년도, 학과명);
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

				전임교원1인당국책사업수주실적 전임교원1인당국책사업수주실적 = new 전임교원1인당국책사업수주실적();

				Float 전임교원1인당국책사업수주총액계산 = 전임교원1인당국책사업수주총액(전임교원수, 대학명의국책사업수주총액);

				전임교원1인당국책사업수주실적.set대학명의국책사업수주총액(대학명의국책사업수주총액);
				전임교원1인당국책사업수주실적.set전임교원1인당국책사업수주총액(전임교원1인당국책사업수주총액계산);
				전임교원1인당국책사업수주실적.set년도(년도);
				전임교원1인당국책사업수주실적.set학과명(학과명);
				전임교원1인당국책사업수주실적.set연번(연번);

				if (!dao.update(전임교원1인당국책사업수주실적)) {
					out.write("fail");
					out.flush();
					out.close();
				}
			}
		} else if (oper.equals("del")) {

			int 연번 = Integer.parseInt(request.getParameter("연번"));
			dao.delete(연번);

			비율재계산();

		} else if (oper.equals("delAll")) {
			String 입력부서 = request.getParameter("입력부서");
			dao.dellAll(입력부서);

			비율재계산();

		} else {
			System.out.println("잘못된 접근 입니다.");
		}
	}

	public float 전임교원1인당국책사업수주총액(int 전임교원수, int 대학명의국책사업수주총액) {
		if (전임교원수 > 0 && 대학명의국책사업수주총액 > 0)
			return 대학명의국책사업수주총액 / (전임교원수 * 1.0F);
		else
			return 0;
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();
		
		String column = "전임교원1인당국책사업수주총액";
		String table = "전임교원1인당국책사업수주실적";

		float 평균 = defaultQuery.avg(년도, column, table);
		float 표준편차 = defaultQuery.std(년도, column, table);

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			float 전임교원1인당국책사업수주총액계산 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(전임교원1인당국책사업수주총액계산, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}