package 설문조사;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 설문조사.설문조사Dao;
import defaultMethod.defaultQuery;
import defaultMethod.년도;

@WebServlet("/설문조사EditAction")
public class 설문조사EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		설문조사Dao dao = new 설문조사Dao();

		try {
			if (oper.equals("add")) {
				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));
				int 참여학생수 = Integer.parseInt(request.getParameter("참여학생수"));
				float 설문조사총점 = Float.parseFloat(request.getParameter("설문조사총점"));
				String 입력부서 = request.getParameter("입력부서");

				float 학생만족도평가 = 학생만족도평가(설문조사총점, 참여학생수);

				설문조사 설문조사bean = new 설문조사();

				설문조사bean.set년도(년도);
				설문조사bean.set학과명(학과명);
				설문조사bean.set참여학생수(참여학생수);
				설문조사bean.set설문조사총점(설문조사총점);
				설문조사bean.set학생만족도평가(학생만족도평가);
				설문조사bean.set입력부서(입력부서);

				if (!dao.insert(설문조사bean)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.flush();
					out.close();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				비율재계산();

			} else if (oper.equals("edit")) {
				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));
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

					int 참여학생수 = Integer.parseInt(request.getParameter("참여학생수"));
					float 설문조사총점 = Float.parseFloat(request.getParameter("설문조사총점"));

					float 학생만족도평가 = 학생만족도평가(설문조사총점, 참여학생수);

					설문조사 설문조사bean = new 설문조사();
					설문조사bean.set연번(연번);
					설문조사bean.set년도(년도);
					설문조사bean.set학과명(학과명);
					설문조사bean.set참여학생수(참여학생수);
					설문조사bean.set설문조사총점(설문조사총점);
					설문조사bean.set학생만족도평가(학생만족도평가);

					if (!dao.update(설문조사bean)) {
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

	public float 학생만족도평가(float 설문조사총점, int 참여학생수) {
		if (설문조사총점 == 0 || 참여학생수 == 0)
			return 0;
		else {
		//	BigDecimal B_설문조사총점 = new BigDecimal(String.valueOf(설문조사총점));
		//	BigDecimal B_참여학생수 = new BigDecimal(String.valueOf(참여학생수));
			float 만족도비율 = 설문조사총점/참여학생수;
			System.out.println("설문조사 총점 :"+설문조사총점+" 참여학생수 :"+참여학생수+" 만족도 비율 계산 결과 :" + 만족도비율);
			return 만족도비율;

		}
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "학생만족도평가";
		String table = "설문조사";

		float 평균 = (float) defaultQuery.avg(년도, column, table);
		float 표준편차 = (float) defaultQuery.std(년도, column, table);

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			float 학생만족도평가 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(학생만족도평가, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}