package 봉사실적;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import defaultMethod.년도;
import 봉사실적.봉사실적;
import 봉사실적.봉사실적Dao;

@WebServlet("/봉사실적EditAction")
public class 봉사실적EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	봉사실적Dao dao = new 봉사실적Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			int 년도 = Integer.parseInt(request.getParameter("년도"));
			String 학과명 = request.getParameter("학과명");

			String 학기 = request.getParameter("학기");
			String 성명 = request.getParameter("성명");
			int 이수학점 = Integer.parseInt(request.getParameter("이수학점"));
			String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
			String 입력부서 = request.getParameter("입력부서");

			int 학번 = Integer.parseInt(request.getParameter("학번"));

			봉사실적 봉사실적 = new 봉사실적();
			봉사실적.set년도(년도);
			봉사실적.set학기(학기);
			봉사실적.set학과명(학과명);
			봉사실적.set학번(학번);
			봉사실적.set성명(성명);
			봉사실적.set이수학점(이수학점);
			봉사실적.set비고(비고);
			봉사실적.set입력부서(입력부서);

			if (!dao.insert(봉사실적)) {
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

			String 학기 = request.getParameter("학기");
			String 성명 = request.getParameter("성명");
			int 이수학점 = Integer.parseInt(request.getParameter("이수학점"));
			String 비고 = request.getParameter("비고");
			int 학번 = Integer.parseInt(request.getParameter("학번"));
			int 연번 = Integer.parseInt(request.getParameter("연번"));

			봉사실적 봉사실적 = new 봉사실적();

			봉사실적.set년도(년도);
			봉사실적.set학기(학기);
			봉사실적.set학과명(학과명);
			봉사실적.set학번(학번);
			봉사실적.set성명(성명);
			봉사실적.set이수학점(이수학점);
			봉사실적.set비고(비고);
			봉사실적.set연번(연번);

			if (!dao.update(봉사실적)) {
				PrintWriter out = response.getWriter();

				out.write("fail");
				out.flush();
				out.close();
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

	public float 이수학점(int 이수학점1학기, int 이수학점2학기, int 재학생1학기, int 재학생2학기) {
		float result = (float) (((이수학점1학기 / (재학생1학기 * 1.0)) + (이수학점2학기 / (재학생2학기 * 1.0))) / 2.0);
		float 이수학점 =   result;
		System.out.println("이수학점 1학기 :" + 이수학점1학기 + " 이수학점2학기 :" + 이수학점2학기 + " 재학생1학기 :" + 재학생1학기 + " 재학생2학기 :" + 재학생2학기
				+ " 이수학점 :" + 이수학점);
		return 이수학점;
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "이수학점";
		String table = "봉사실적비율";

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			int 이수학점1학기 = dao.이수학점(학과명, "1학기");
			int 이수학점2학기 = dao.이수학점(학과명, "2학기");
			int 재학생1학기 = defaultQuery.재학생수(년도, 학과명);
			int 재학생2학기 = dao.재학생2학기(년도, 학과명);

			float 이수학점 = 이수학점(이수학점1학기, 이수학점2학기, 재학생1학기, 재학생2학기);

			dao.update봉사실적비율(년도, 학과명, 이수학점);
		}

		float 평균 = (float)defaultQuery.avg(년도, column, table);
		float 표준편차 = (float)defaultQuery.std(년도, column, table);

		for (String 학과명 : 학과목록) {

			float 봉사실적비율 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(봉사실적비율, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
