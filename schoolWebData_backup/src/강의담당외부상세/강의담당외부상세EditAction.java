package 강의담당외부상세;

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

import 강의담당외부상세.강의담당외부상세Dao;
import 강의담당상세.강의담당상세EditAction;
import defaultMethod.defaultQuery;

@WebServlet("/강의담당외부상세EditAction")

public class 강의담당외부상세EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	강의담당상세EditAction ea = new 강의담당상세EditAction();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		강의담당외부상세Dao dao = new 강의담당외부상세Dao();

		try {
			if (oper.equals("add")) {
				String 학과명 = request.getParameter("학과명");
				String 대학명 = request.getParameter("대학명");
				float 학점 = Float.parseFloat(request.getParameter("학점"));
				String 구분 = request.getParameter("구분");
				String 학기 = request.getParameter("학기");
				String 성명 = request.getParameter("성명");
				String 과목 = request.getParameter("과목");
				String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
				String 입력부서 = request.getParameter("입력부서");
				float 전공과목 = dao.전공과목(대학명);
				float 자유선택과목 = dao.자유선택(대학명);
				float 교원교양필수과목 = dao.교양필수과목(대학명);
				float 계 = dao.과목총계(대학명);
				float set강의담당외부 = 강의담당외부(전공과목, 교원교양필수과목, 자유선택과목, 계);
				dao.update강의담당외부(set강의담당외부, 대학명);

				강의담당외부상세 강의담당외부상세계산 = new 강의담당외부상세();

				강의담당외부상세계산.set대학명(대학명);
				강의담당외부상세계산.set학과명(학과명);
				강의담당외부상세계산.set구분(구분);
				강의담당외부상세계산.set학기(학기);
				강의담당외부상세계산.set성명(성명);
				강의담당외부상세계산.set과목(과목);
				강의담당외부상세계산.set비고(비고);
				강의담당외부상세계산.set학점(학점);
				강의담당외부상세계산.set입력부서(입력부서);
				PrintWriter out = response.getWriter();

				if (!dao.insert(강의담당외부상세계산)) {
					out.write("fail");
					out.flush();
					out.close();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				비율재계산();

			} else if (oper.equals("edit")) {

				String 학과명 = request.getParameter("학과명");
				String 대학명 = request.getParameter("대학명");
				String 구분 = request.getParameter("구분");
				float 학점 = Float.parseFloat(request.getParameter("학점"));
				String 학기 = request.getParameter("학기");
				String 성명 = request.getParameter("성명");
				String 과목 = request.getParameter("과목");
				String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
				int 연번 = Integer.parseInt(request.getParameter("연번"));
				PrintWriter out = response.getWriter();
				강의담당외부상세 강의담당외부상세 = new 강의담당외부상세();

				float 전공과목 = dao.전공과목(대학명);
				float 자유선택과목 = dao.자유선택(대학명);
				float 교원교양필수과목 = dao.교양필수과목(대학명);
				float 계 = dao.과목총계(대학명);
				float set강의담당외부 = 강의담당외부(전공과목, 교원교양필수과목, 자유선택과목, 계);
				dao.update강의담당외부(set강의담당외부, 대학명);

				강의담당외부상세.set대학명(대학명);
				강의담당외부상세.set학과명(학과명);
				강의담당외부상세.set구분(구분);
				강의담당외부상세.set학기(학기);
				강의담당외부상세.set성명(성명);
				강의담당외부상세.set과목(과목);
				강의담당외부상세.set비고(비고);
				강의담당외부상세.set학점(학점);
				강의담당외부상세.set연번(연번);

				if (!dao.update(강의담당외부상세)) {
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

			} else {

				System.out.println("잘못된 접근 입니다.");
			}
		} catch (

		NumberFormatException n) {
			n.printStackTrace();
		}
	}

	public float 강의담당외부(float 전공과목, float 교원교양필수과목, float 자유선택과목, float 계) {
		BigDecimal B_전공과목 = new BigDecimal(String.valueOf(전공과목));
		BigDecimal B_교원교양필수과목 = new BigDecimal(String.valueOf(교원교양필수과목));
		BigDecimal B_자유선택과목 = new BigDecimal(String.valueOf(자유선택과목));
		BigDecimal B_계 = new BigDecimal(String.valueOf(계));

		BigDecimal B_100 = new BigDecimal("100");

		if (계 > 0) {
			return (B_계.divide((B_전공과목.add(B_교원교양필수과목).add(B_자유선택과목)), 2, BigDecimal.ROUND_UP)).multiply(B_100)
					.floatValue();
		} else {
			return 0;
		}
	}

	public void 비율재계산() {
		String column = "강의담당비율";
		String table = "개설강의담당외부비율";

		ArrayList<String> 대학목록 = defaultQuery.외부대학목록(table);

		for (String 대학명 : 대학목록) {
			float 평균 = defaultQuery.외부avg(column, table); // 외부지표는 학문계열로 계산하지
															// 않는다고한다.
			float 표준편차 = defaultQuery.외부std(column, table);

			float 연구실적계산 = defaultQuery.외부비율(대학명, column, table);
			float T점수 = defaultClass.T점수(연구실적계산, 평균, 표준편차);
			defaultQuery.외부updateT(T점수, 대학명, table);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}