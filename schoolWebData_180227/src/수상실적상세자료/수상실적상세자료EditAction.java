package 수상실적상세자료;

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
import 수상실적상세자료.수상실적상세자료;
import 수상실적상세자료.수상실적상세자료Dao;
import defaultMethod.년도;

@WebServlet("/수상실적상세자료EditAction")
public class 수상실적상세자료EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	수상실적상세자료Dao dao = new 수상실적상세자료Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			int 년도 = Integer.parseInt(request.getParameter("년도"));
			String 학과명 = request.getParameter("학과명");
			String 구분 = request.getParameter("구분");
			String 수상일자 = request.getParameter("수상일자");
			String 대회명 = request.getParameter("대회명");
			String 수상내용 = request.getParameter("수상내용");
			String 인정여부 = request.getParameter("인정여부");
			String 수상대상자 = request.getParameter("수상대상자");
			String 입력부서 = request.getParameter("입력부서");

			수상실적상세자료 수상실적상세자료 = new 수상실적상세자료();

			수상실적상세자료.set년도(년도);
			수상실적상세자료.set학과명(학과명);
			수상실적상세자료.set대회규모규분(구분);
			수상실적상세자료.set수상일자(수상일자);
			수상실적상세자료.set대회명(대회명);
			수상실적상세자료.set수상내용(수상내용);
			수상실적상세자료.set인정여부(인정여부);
			수상실적상세자료.set수상대상자(수상대상자);
			수상실적상세자료.set입력부서(입력부서);

			if (!dao.insert(수상실적상세자료)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		} else if (oper.equals("edit")) {

			int 년도 = Integer.parseInt(request.getParameter("년도"));
			String 학과명 = request.getParameter("학과명");

			String 구분 = request.getParameter("구분");
			String 수상일자 = request.getParameter("수상일자");
			String 대회명 = request.getParameter("대회명");
			String 수상내용 = request.getParameter("수상내용");
			String 인정여부 = request.getParameter("인정여부");
			String 수상대상자 = request.getParameter("수상대상자");

			int 연번 = Integer.parseInt(request.getParameter("연번"));

			수상실적상세자료 수상실적상세자료 = new 수상실적상세자료();

			수상실적상세자료.set년도(년도);
			수상실적상세자료.set학과명(학과명);
			수상실적상세자료.set대회규모규분(구분);
			수상실적상세자료.set수상일자(수상일자);
			수상실적상세자료.set대회명(대회명);
			수상실적상세자료.set수상내용(수상내용);
			수상실적상세자료.set인정여부(인정여부);
			수상실적상세자료.set수상대상자(수상대상자);
			수상실적상세자료.set연번(연번);

			if (!dao.update(수상실적상세자료)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.flush();
				out.close();

			}
		} else if (oper.equals("cal") || oper.equals("editCal")) {

			비율재계산();

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

	private float 수상실적(float 전임교원수상실적, float 재학생수상실적, int 재학생수, int 전임교원수) {

		if(재학생수==0 || 전임교원수==0)
			return 0;
		
		float result = (float) ((전임교원수상실적 / (전임교원수 * 1.0)) + (재학생수상실적 / (재학생수 * 1.0)));
		float 수상실적 = (float) result;
		System.out.println("전임교원수상실적 :" + 전임교원수상실적 + " 재학생수상실적 :" + 재학생수상실적 + " 재학생수 :" + 재학생수 + " 전임교원수 :" + 전임교원수
				+ " 수상실적 :" + 수상실적);
		return 수상실적;
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "수상실적";
		String table = "수상실적비율";

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {

			float 전임교원수상실적 = dao.전임교원수상실적(년도, 학과명);
			float 재학생수상실적 = dao.재학생수상실적(년도, 학과명);
			int 재학생수 = defaultQuery.재학생수(년도, 학과명);
			int 전임교원수 = defaultQuery.전임교원수(년도, 학과명);

			float 수상실적계산 = 수상실적(전임교원수상실적, 재학생수상실적, 재학생수, 전임교원수);

			dao.update수상실적(년도, 학과명, 수상실적계산);
		}

		float 평균 = defaultQuery.avg(년도, column, table);
		float 표준편차 = defaultQuery.std(년도, column, table);
		
		for (String 학과명 : 학과목록) {
			float 수상실적 = dao.재학생수상실적(년도, 학과명);
			float T점수 = defaultClass.T점수(평균, 표준편차, 수상실적);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
