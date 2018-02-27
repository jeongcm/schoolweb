package 외국인학생비율;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 외국인학생비율.외국인학생비율Dao;
import defaultMethod.defaultQuery;
import defaultMethod.년도;

@WebServlet("/외국인학생비율EditAction")
public class 외국인학생비율EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	외국인학생비율Dao dao = new 외국인학생비율Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		try {
			if (oper.equals("add")) {

				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));

				int 학년 = Integer.parseInt(request.getParameter("학년"));
				int 학번 = Integer.parseInt(request.getParameter("학번"));
				String 성명 = request.getParameter("성명");
				String 국적 = request.getParameter("국적");
				String 성별 = request.getParameter("성별");
				String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
				String 입력부서 = request.getParameter("입력부서");

				외국인학생비율 외국인학생현황 = new 외국인학생비율();

				외국인학생현황.set년도(년도);
				외국인학생현황.set학과명(학과명);
				외국인학생현황.set학년(학년);
				외국인학생현황.set학번(학번);
				외국인학생현황.set성명(성명);
				외국인학생현황.set국적(국적);
				외국인학생현황.set성별(성별);
				외국인학생현황.set비고(비고);
				외국인학생현황.set입력부서(입력부서);

				if (!dao.insert(외국인학생현황)) {
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

					int 학년 = Integer.parseInt(request.getParameter("학년"));
					int 학번 = Integer.parseInt(request.getParameter("학번"));
					String 성명 = request.getParameter("성명");
					String 국적 = request.getParameter("국적");
					String 성별 = request.getParameter("성별");
					String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
					int 연번 = Integer.parseInt(request.getParameter("연번"));

					외국인학생비율 외국인학생현황 = new 외국인학생비율();

					외국인학생현황.set년도(년도);
					외국인학생현황.set학과명(학과명);
					외국인학생현황.set학년(학년);
					외국인학생현황.set학번(학번);
					외국인학생현황.set성명(성명);
					외국인학생현황.set국적(국적);
					외국인학생현황.set성별(성별);
					외국인학생현황.set비고(비고);
					외국인학생현황.set연번(연번);

					if (!dao.update(외국인학생현황)) {
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

	public float 외국인학생비율(int 외국인학생수, int 재학생수) {
		if (외국인학생수 == 0 || 재학생수 == 0)
			return 0;
		else {
			float 외국인학생비율 = (float) (외국인학생수 / (재학생수 * 1.0) * 100.0) ;
			System.out.println("외국인학생수 :" + 외국인학생수 + " 재학생수: " + 재학생수 + " 외국인학생비율 계산 결과 :" + 외국인학생비율);
			return 외국인학생비율;
		}
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "외국인학생비율";
		String table = "외국인학생비율";

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과 : 학과목록) {
			int 외국인학생수 = dao.외국인학생수(년도, 학과);
			int 재학생수 = defaultQuery.재학생수(년도, 학과);

			float 외국인학생비율계산 = 외국인학생비율(외국인학생수, 재학생수);

			dao.update외국인학생비율(년도, 학과, 외국인학생비율계산);
		}

		float 평균 = (float) defaultQuery.avg(년도, column, table);
		float 표준편차 = (float)defaultQuery.std(년도, column, table);
		
		for (String 학과 : 학과목록) {
			float 외국인학생비율 = defaultQuery.비율(년도, 학과, column, table);
			float T점수 = defaultClass.T점수(외국인학생비율, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과, table);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}