package 특허등록;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 특허등록.특허등록;
import 특허등록.특허등록Dao;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import java.math.BigDecimal;
import defaultMethod.년도;

@WebServlet("/특허등록EditAction")
public class 특허등록EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	특허등록Dao dao = new 특허등록Dao();


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {

			int 년도 = Integer.parseInt(request.getParameter("년도"));
			String 학과명 = request.getParameter("학과명");

			int 국제 = defaultClass.nullCheck(request.getParameter("국제")) ? 0
					: Integer.parseInt(request.getParameter("국제"));
			int 국내 = defaultClass.nullCheck(request.getParameter("국내")) ? 0
					: Integer.parseInt(request.getParameter("국내"));

			int 전임교원수 = defaultQuery.전임교원수(년도, 학과명);

			String 입력부서 = request.getParameter("입력부서");
			특허등록 특허등록 = new 특허등록();

			float 특허이전료기술이전 = dao.특허이전료기술이전(년도, 학과명);
			float 특허등록및기술이전수입료 = 특허등록및기술이전수입료(국제, 국내, 특허이전료기술이전, 전임교원수);

			특허등록.set년도(년도);
			특허등록.set학과명(학과명);
			특허등록.set국제(국제);
			특허등록.set국내(국내);
			특허등록.set특허이전료기술이전(특허이전료기술이전);
			특허등록.set특허등록및기술이전수입료(특허등록및기술이전수입료);
			특허등록.set입력부서(입력부서);

			if (!dao.insert(특허등록)) {
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

				int 국제 = defaultClass.nullCheck(request.getParameter("국제")) ? 0
						: Integer.parseInt(request.getParameter("국제"));
				int 국내 = defaultClass.nullCheck(request.getParameter("국제")) ? 0
						: Integer.parseInt(request.getParameter("국내"));

				float 특허이전료기술이전 = dao.특허이전료기술이전(년도, 학과명);
				int 연번 = Integer.parseInt(request.getParameter("연번"));

				특허등록 특허등록 = new 특허등록();

				특허등록.set년도(년도);
				특허등록.set학과명(학과명);
				특허등록.set국제(국제);
				특허등록.set국내(국내);
				특허등록.set특허이전료기술이전(특허이전료기술이전);
				특허등록.set연번(연번);

				if (!dao.update(특허등록)) {
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
			System.out.println("잘못된 접근입니다.");
		}
	}

	public float 특허등록및기술이전수입료(int 국제, int 국내, float 특허이전료기술이전, int 전임교원수) {

		BigDecimal B_국제 = new BigDecimal(String.valueOf(국제));
		BigDecimal B_국내 = new BigDecimal(String.valueOf(국내));
		BigDecimal B_특허이전료기술이전 = new BigDecimal(String.valueOf(특허이전료기술이전));
		BigDecimal B_전임교원수 = new BigDecimal(String.valueOf(전임교원수));

		BigDecimal B_200 = new BigDecimal("200");
		BigDecimal B_100 = new BigDecimal("100");
		BigDecimal B_20 = new BigDecimal("20");

		BigDecimal mol = (B_국제.multiply(B_200)).add(B_국내.multiply(B_100)).add((B_특허이전료기술이전).multiply(B_20));

		if (mol.compareTo(BigDecimal.ZERO) > 0 && B_전임교원수.compareTo(BigDecimal.ZERO) > 0) {
			float 특허득롱및기술이전수입료 = (mol.divide(B_전임교원수, 12, BigDecimal.ROUND_DOWN)).floatValue();
			System.out.println("국제 :" + 국제 + " 국내 :" + 국내 + " 특허이전료기술이전 :" + 특허이전료기술이전 + " 전임교원수:" + 전임교원수
					+ " 특허등록및기술이전수입료 :" + 특허득롱및기술이전수입료); //특허이전료기술이전이 계산하기전 그냥 sum()한것이고 실제로 계산결과가 특허등록및기술이전수입료임
			return 특허득롱및기술이전수입료;
		} else
			return 0;
	}

	public void 비율재계산() {

		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "수입료";
		String table = "특허등록및기술이전수입료";

		float 평균 = (float) defaultQuery.avg(년도, column, table);
		float 표준편차 = (float) defaultQuery.std(년도, column, table);

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			float 수입료 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(수입료, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
