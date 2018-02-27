package 교외장학금;

import java.io.IOException;
import defaultMethod.년도;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 교외장학금.교외장학금Dao;
import defaultMethod.defaultQuery;

@WebServlet("/교외장학금EditAction")
public class 교외장학금EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	교외장학금Dao dao = new 교외장학금Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		try {
			if (oper.equals("add")) {

				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));

				int 교외장학금 = defaultClass.nullCheck(request.getParameter("교외장학금")) ? 0
						: Integer.parseInt(request.getParameter("교외장학금"));
				String 입력부서 = request.getParameter("입력부서");

				float 재학생평균 = dao.재학생평균(년도, 학과명);
				float 일인당교외장학금 = 교외장학금(재학생평균, 교외장학금);

				교외장학금 교외장학금bean = new 교외장학금();

				교외장학금bean.set년도(년도);
				교외장학금bean.set학과명(학과명);
				교외장학금bean.set교외장학금(교외장학금);
				교외장학금bean.set일인당교외장학금(일인당교외장학금);
				교외장학금bean.set입력부서(입력부서);

				if (!dao.insert(교외장학금bean)) {
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
					int 교외장학금 = defaultClass.nullCheck(request.getParameter("교외장학금")) ? 0
							: Integer.parseInt(request.getParameter("교외장학금"));
					int 연번 = Integer.parseInt(request.getParameter("연번"));

					float 재학생평균 = dao.재학생평균(년도, 학과명);
					float 일인당교외장학금 = 교외장학금(재학생평균, 교외장학금);

					교외장학금 교외장학금bean = new 교외장학금();

					교외장학금bean.set년도(년도);
					교외장학금bean.set학과명(학과명);
					교외장학금bean.set교외장학금(교외장학금);
					교외장학금bean.set일인당교외장학금(일인당교외장학금);
					교외장학금bean.set연번(연번);

					if (!dao.update(교외장학금bean)) {
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
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

	public float 교외장학금(float 재학생평균, int 교외장학금) {

		BigDecimal B_재학생평균 = new BigDecimal(String.valueOf(재학생평균));
		BigDecimal B_교외장학금 = new BigDecimal(String.valueOf(교외장학금));
		if (B_교외장학금.compareTo(BigDecimal.ZERO) > 0 && B_재학생평균.compareTo(BigDecimal.ZERO) > 0) {
			float 교외장학금결과 = (B_교외장학금.divide(B_재학생평균, 12, BigDecimal.ROUND_DOWN)).floatValue();
			System.out.println("재학생평균 :" + 재학생평균 + " 교외장학금 :" + 교외장학금 + " 교외장학금 결과 :" + 교외장학금결과);
			return 교외장학금결과;
		} else
			return 0;
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "1인당교외장학금";
		String table = "교외장학금";

		float 평균 = defaultQuery.avg(년도, column, table);
		float 표준편차 =defaultQuery.std(년도, column, table);

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);
		for (String 학과명 : 학과목록) {
			float 일인당교외장학금 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(일인당교외장학금, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}