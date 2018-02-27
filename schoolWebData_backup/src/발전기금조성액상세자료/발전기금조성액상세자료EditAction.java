package 발전기금조성액상세자료;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultQuery;
import defaultMethod.defaultClass;
import defaultMethod.년도;

@WebServlet("/발전기금조성액상세자료EditAction")

public class 발전기금조성액상세자료EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	발전기금조성액상세자료Dao dao = new 발전기금조성액상세자료Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			String 학과명 = request.getParameter("학과명");
			int 년도 = Integer.parseInt(request.getParameter("년도"));
			String 구분 = request.getParameter("구분");
			int 금액 = Integer.parseInt(request.getParameter("금액"));
			String 입력부서 = request.getParameter("입력부서");

			발전기금조성액상세자료 발전기금조성액상세자료 = new 발전기금조성액상세자료();

			발전기금조성액상세자료.set년도(년도);
			발전기금조성액상세자료.set학과명(학과명);
			발전기금조성액상세자료.set금액(금액);
			발전기금조성액상세자료.set입력부서(입력부서);
			발전기금조성액상세자료.set구분(구분);

			PrintWriter out = response.getWriter();

			if (dao.insert(발전기금조성액상세자료) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}
		} else if (oper.equals("cal") || oper.equals("editCal")) {

			비율재계산();

		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();

			String 학과명 = request.getParameter("학과명");
			int 년도 = Integer.parseInt(request.getParameter("년도"));
			String 학과명체크 = defaultQuery.학과명체크(학과명);
			String 년도체크 = defaultQuery.년도체크(년도);

			if (학과명체크.equals("0")) {
				out.write("failMajor");
				out.flush();
				out.close();
			} else if (년도체크.equals("0")) {
				out.write("failYear");
				out.flush();
				out.close();
			} else {
				String 구분 = request.getParameter("구분");
				int 금액 = Integer.parseInt(request.getParameter("금액"));
				int 연번 = Integer.parseInt(request.getParameter("연번"));

				발전기금조성액상세자료 발전기금조성액상세자료 = new 발전기금조성액상세자료();

				발전기금조성액상세자료.set년도(년도);
				발전기금조성액상세자료.set학과명(학과명);
				발전기금조성액상세자료.set금액(금액);
				발전기금조성액상세자료.set구분(구분);
				발전기금조성액상세자료.set연번(연번);

				if (!dao.update(발전기금조성액상세자료)) {
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

	public float 발전기금조성액(int 전임교원수, int 발전기금모금액, int 지정기부금) {

		/*if (발전기금모금액 == 0 && 지정기부금 == 0) {
			return 0;
		} else {*/
			float 발전기금조성액 = (float) ((발전기금모금액 - 지정기부금) / (전임교원수 * 1.0));
			
			System.out.println("전임교원수 :" + 전임교원수 + " 발전기금모금액 :" + 발전기금모금액 + " 지정기부금 :" + 지정기부금 + " 발전기금조성액 :" + 발전기금조성액);
			
			return 발전기금조성액;
		}
	

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();
		String column = "발전기금조성액";
		String table = "발전기금조성액";

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {

			int 발전기금모금액 = dao.발전기금모금액(년도, 학과명);
			int 지정기부금 = dao.지정기부금(년도, 학과명);
			int 전임교원수 = defaultQuery.전임교원수(년도, 학과명);
			float set발전기금조성액 = 발전기금조성액(전임교원수, 발전기금모금액, 지정기부금);

			dao.update발전기금(년도, 학과명, set발전기금조성액);

		}

		float 평균 = defaultQuery.avg(년도, column, table);
		float 표준편차 = defaultQuery.std(년도, column, table);

		for (String 학과명 : 학과목록) {
			float 발전기금조성액 = dao.발전기금조성액(년도, 학과명);
			float T점수 = defaultClass.T점수(발전기금조성액, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
