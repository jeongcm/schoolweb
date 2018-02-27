package 현장실습;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 현장실습.현장실습;
import 현장실습.현장실습Dao;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import defaultMethod.년도;

@WebServlet("/현장실습EditAction")
public class 현장실습EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		현장실습Dao dao = new 현장실습Dao();

		try {
			if (oper.equals("add")) {
				int 년도 = Integer.parseInt(request.getParameter("년도"));
				String 학과명 = request.getParameter("학과명");

				int _1학기 = defaultClass.nullCheck(request.getParameter("_1학기")) ? 0
						: Integer.parseInt(request.getParameter("_1학기"));
				int _2학기 = defaultClass.nullCheck(request.getParameter("_2학기")) ? 0
						: Integer.parseInt(request.getParameter("_2학기"));
				int 장기1학기 = defaultClass.nullCheck(request.getParameter("장기1학기")) ? 0
						: Integer.parseInt(request.getParameter("장기1학기"));
				int 장기2학기 = defaultClass.nullCheck(request.getParameter("장기2학기")) ? 0
						: Integer.parseInt(request.getParameter("장기2학기"));
				String 입력부서 = request.getParameter("입력부서");
				int 대상학생수 = Integer.parseInt(request.getParameter("대상학생수"));

				int 합계 = _1학기 + _2학기;
				int 장기합계 = 장기1학기 + 장기2학기;

				float 이수학생비율 = 이수학생비율(합계, 장기합계, 대상학생수);

				현장실습 현장실습 = new 현장실습();

				현장실습.set년도(년도);
				현장실습.set학과명(학과명);
				현장실습.set_1학기(_1학기);
				현장실습.set_2학기(_2학기);
				현장실습.set장기1학기(장기1학기);
				현장실습.set장기2학기(장기2학기);
				현장실습.set대상학생수(대상학생수);
				현장실습.set이수학생비율(이수학생비율);
				현장실습.set입력부서(입력부서);

				if (!dao.insert(현장실습)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				비율재계산();

			}

			else if (oper.equals("edit")) {
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

					int _1학기 = defaultClass.nullCheck(request.getParameter("_1학기")) ? 0
							: Integer.parseInt(request.getParameter("_1학기"));
					int _2학기 = defaultClass.nullCheck(request.getParameter("_2학기")) ? 0
							: Integer.parseInt(request.getParameter("_2학기"));
					int 장기1학기 = defaultClass.nullCheck(request.getParameter("장기1학기")) ? 0
							: Integer.parseInt(request.getParameter("장기1학기"));
					int 장기2학기 = defaultClass.nullCheck(request.getParameter("장기2학기")) ? 0
							: Integer.parseInt(request.getParameter("장기2학기"));
					int 대상학생수 = Integer.parseInt(request.getParameter("대상학생수"));
					int 연번 = Integer.parseInt(request.getParameter("연번"));

					int 합계 = _1학기 + _2학기;
					int 장기합계 = 장기1학기 + 장기2학기;

					float 이수학생비율 = 이수학생비율(합계, 장기합계, 대상학생수);

					현장실습 현장실습 = new 현장실습();
					현장실습.set년도(년도);
					현장실습.set학과명(학과명);
					현장실습.set_1학기(_1학기);
					현장실습.set_2학기(_2학기);
					현장실습.set장기1학기(장기1학기);
					현장실습.set장기2학기(장기2학기);
					현장실습.set대상학생수(대상학생수);
					현장실습.set합계(합계);
					현장실습.set장기합계(장기합계);
					현장실습.set이수학생비율(이수학생비율);
					현장실습.set연번(연번);

					if (!dao.update(현장실습)) {
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
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

	public float 이수학생비율(int 합계, int 장기합계, int 대상학생수) {
		float result = (float) (((합계 + (장기합계 * 2)) / (대상학생수 * 1.0)) * 100);
		float 이수학생비율 = (float)result;
		System.out.println("장기합계 :"+장기합계+" 대상학생수 :"+대상학생수+" 이수학생 계산결과 :" + 이수학생비율);
		return result;
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "이수학생비율";
		String table = "현장실습";

		float 평균 = (float) defaultQuery.avg(년도, column, table);
		float 표준편차 = (float)defaultQuery.std(년도, column, table);
		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			float 이수학생비율 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(이수학생비율, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}