package 동아리;

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
import 동아리.동아리;
import 동아리.동아리Dao;

@WebServlet("/동아리EditAction")
public class 동아리EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	동아리Dao dao = new 동아리Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {

			int 년도 = Integer.parseInt(request.getParameter("년도"));
			String 학과명 = request.getParameter("학과명");
			String 관리부서 = request.getParameter("관리부서");
			String 동아리명 = request.getParameter("동아리명");
			String 구분 = request.getParameter("구분");
			String 지도교수 = request.getParameter("지도교수");
			String 학생대표학년 = request.getParameter("학생대표학년");
			String 학생대표이름 = request.getParameter("학생대표이름");
			int 회원수 = Integer.parseInt(request.getParameter("회원수"));
			int 예산지원액 = defaultClass.nullCheck(request.getParameter("예산지원액")) ? 0
					: Integer.parseInt(request.getParameter("회원수"));
			String 인정여부 = request.getParameter("인정여부");
			String 실적서류 = request.getParameter("실적서류");
			String 비고 = request.getParameter("비고");
			String 입력부서 = request.getParameter("입력부서");

			동아리 동아리 = new 동아리();

			동아리.set년도(년도);
			동아리.set관리부서(관리부서);
			동아리.set학과명(학과명);
			동아리.set동아리명(동아리명);
			동아리.set구분(구분);
			동아리.set지도교수(지도교수);
			동아리.set학생대표학년(학생대표학년);
			동아리.set학생대표이름(학생대표이름);
			동아리.set예산지원액(예산지원액);
			동아리.set회원수(회원수);
			동아리.set인정여부(인정여부);
			동아리.set실적서류(실적서류);
			동아리.set비고(비고);
			동아리.set입력부서(입력부서);

			if (!dao.insert(동아리)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		} else if (oper.equals("cal") || oper.equals("editCal")) {

			비율재계산();

		} else if (oper.equals("edit")) {

			int 년도 = Integer.parseInt(request.getParameter("년도"));
			String 관리부서 = request.getParameter("관리부서");
			String 학과명 = request.getParameter("학과명");
			String 동아리명 = request.getParameter("동아리명");
			String 구분 = request.getParameter("구분");
			String 지도교수 = request.getParameter("지도교수");
			String 학생대표학년 = request.getParameter("학생대표학년");
			String 학생대표이름 = request.getParameter("학생대표이름");
			int 회원수 = Integer.parseInt(request.getParameter("회원수"));
			int 예산지원액 = defaultClass.nullCheck(request.getParameter("예산지원액")) ? 0
					: Integer.parseInt(request.getParameter("회원수"));
			String 인정여부 = request.getParameter("인정여부");
			String 실적서류 = request.getParameter("실적서류");
			String 비고 = request.getParameter("비고");
			int 연번 = Integer.parseInt(request.getParameter("연번"));

			동아리 동아리 = new 동아리();

			동아리.set년도(년도);
			동아리.set관리부서(관리부서);
			동아리.set학과명(학과명);
			동아리.set동아리명(동아리명);
			동아리.set구분(구분);
			동아리.set지도교수(지도교수);
			동아리.set학생대표학년(학생대표학년);
			동아리.set학생대표이름(학생대표이름);
			동아리.set예산지원액(예산지원액);
			동아리.set회원수(회원수);
			동아리.set인정여부(인정여부);
			동아리.set실적서류(실적서류);
			동아리.set비고(비고);
			동아리.set연번(연번);

			if (!dao.update(동아리)) {
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

	public float 동아리참여비율(int 계, int 재학생수) {
		float result = (float) (계 / (재학생수 * 1.0) * 100);
		System.out.println("합계 :" + 계 + "재학생수 :" + 재학생수 + " 동아리 참여비율 계산 결과 :" + result);
		return (float) (Math.round(result * 100) / 100.0);
	}

	public void 비율재계산() {

		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "동아리참여비율";
		String table = "동아리참여비율";

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			int 계 = dao.계(년도, 학과명);
			int 재학생수 = defaultQuery.재학생수(년도, 학과명);

			float 동아리참여비율계산 = 동아리참여비율(계, 재학생수);

			dao.update동아리참여비율(년도, 학과명, 동아리참여비율계산);

		}

		float 평균 = (float) (Math.round(defaultQuery.avg(년도, column, table) * 100) / 100.0);
		float 표준편차 = (float) (Math.round(defaultQuery.std(년도, column, table) * 100) / 100.0);

		for (String 학과명 : 학과목록) {

			float 동아리참여비율 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(동아리참여비율, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}