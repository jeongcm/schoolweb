package 신입생현황;

import java.io.IOException;
import defaultMethod.년도;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import 신입생현황.신입생현황Dao;

@WebServlet("/신입생현황EditAction")

public class 신입생현황EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	신입생현황Dao dao = new 신입생현황Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			int 년도 = Integer.parseInt(request.getParameter("년도"));
			String 학과명 = request.getParameter("학과명");
			int 입학자수 = Integer.parseInt(request.getParameter("입학자수"));
			int 모집인원 = Integer.parseInt(request.getParameter("모집인원"));
			String 입력부서 = request.getParameter("입력부서");
			float 신입생충원율 = 신입생충원율(입학자수, 모집인원);

			신입생현황 신입생현황bean = new 신입생현황();

			신입생현황bean.set년도(년도);
			신입생현황bean.set학과명(학과명);
			신입생현황bean.set모집인원(모집인원);
			신입생현황bean.set입학자수(입학자수);
			신입생현황bean.set신입생충원율(신입생충원율);
			신입생현황bean.set입력부서(입력부서);
			PrintWriter out = response.getWriter();

			if (!dao.insert(신입생현황bean)) {
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
			int 입학자수 = Integer.parseInt(request.getParameter("입학자수"));
			int 모집인원 = Integer.parseInt(request.getParameter("모집인원"));
			int 연번 = Integer.parseInt(request.getParameter("연번"));
			if (defaultQuery.학과명체크(학과명).equals("0")) {
				out.write("failMajor");
				out.flush();
				out.close();
			} else if (defaultQuery.년도체크(년도).equals("0")) {
				out.write("failYear");
				out.flush();
				out.close();
			} else {

				float 신입생충원율 = 신입생충원율(입학자수, 모집인원);
				신입생현황 신입생현황 = new 신입생현황();

				신입생현황.set학과명(학과명);
				신입생현황.set년도(년도);
				신입생현황.set입학자수(입학자수);
				신입생현황.set모집인원(모집인원);
				신입생현황.set신입생충원율(신입생충원율);
				신입생현황.set연번(연번);

				if (!dao.update(신입생현황)) {
					out.write("fail");
					out.flush();
					out.close();
				} else {
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

	public float 신입생충원율(int 입학자수, int 모집인원) {
		float result = (float) (입학자수 / (모집인원*1.0));
		float 신입생충원율 = result*100;
		System.out.println("입학자수 :" + 입학자수 + " 모진입원 :" + 모집인원);
		return 신입생충원율;
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "신입생충원율";
		String table = "신입생현황";
		
		float 평균 = defaultQuery.avg(년도, column, table);
		float 표준편차 =defaultQuery.std(년도, column, table);

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);
		for (String 학과명 : 학과목록) {
			float 신입생충원율 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(신입생충원율, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}