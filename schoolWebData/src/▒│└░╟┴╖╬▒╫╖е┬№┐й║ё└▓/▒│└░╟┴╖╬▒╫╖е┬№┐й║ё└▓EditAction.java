package 교육프로그램참여비율;

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
import 교육프로그램참여비율.교육프로그램참여비율Dao;

@WebServlet("/교육프로그램참여비율EditAction")
public class 교육프로그램참여비율EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	교육프로그램참여비율Dao dao = new 교육프로그램참여비율Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		try {
			if (oper.equals("add")) {
				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));
				String 운영부서명 = request.getParameter("운영부서명");
				String 프로그램명 = request.getParameter("프로그램명");
				String 입력부서 = request.getParameter("입력부서");
				int 학년 = Integer.parseInt(request.getParameter("학년"));
				String 학번 = request.getParameter("학번");
				String 성명 = request.getParameter("성명");
				String 비고 = request.getParameter("비고");

				교육프로그램참여비율 교육프로그램참여비율계산 = new 교육프로그램참여비율();

				교육프로그램참여비율계산.set년도(년도);
				교육프로그램참여비율계산.set학과명(학과명);

				교육프로그램참여비율계산.set운영부서명(운영부서명);
				교육프로그램참여비율계산.set프로그램명(프로그램명);
				교육프로그램참여비율계산.set학년(학년);
				교육프로그램참여비율계산.set학번(학번);
				교육프로그램참여비율계산.set성명(성명);
				교육프로그램참여비율계산.set비고(비고);
				교육프로그램참여비율계산.set입력부서(입력부서);

				if (!dao.insert(교육프로그램참여비율계산)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.flush();
					out.close();
				} else {
					dao.동아리참여비율insert(년도, 학과명);
				}
			} else if (oper.equals("edit")) {
				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));

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
					String 운영부서명 = request.getParameter("운영부서명");
					String 프로그램명 = request.getParameter("프로그램명");
					int 학년 = Integer.parseInt(request.getParameter("학년"));
					String 학번 = request.getParameter("학번");
					String 성명 = request.getParameter("성명");
					String 비고 = request.getParameter("비고");
					int 연번 = Integer.parseInt(request.getParameter("연번"));

					교육프로그램참여비율 교육프로그램참여비율 = new 교육프로그램참여비율();

					교육프로그램참여비율.set년도(년도);
					교육프로그램참여비율.set학과명(학과명);
					교육프로그램참여비율.set운영부서명(운영부서명);
					교육프로그램참여비율.set프로그램명(프로그램명);
					교육프로그램참여비율.set학년(학년);
					교육프로그램참여비율.set학번(학번);
					교육프로그램참여비율.set성명(성명);
					교육프로그램참여비율.set비고(비고);
					교육프로그램참여비율.set연번(연번);

					if (!dao.update(교육프로그램참여비율)) {
						out.write("fail");
						out.flush();
						out.close();
					}
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
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

	public float 교육프로그램비율(int 계, int 재학생수) {
		float result = (float) 계 / 재학생수;

		System.out.println("합계 :" + 계 + " 재학생수: " + 재학생수 + " 교육 프로그램 비율 계산 결과:" + result);

		return (float) ((result) * 100) ;
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "교육프로그램참여비율";
		String table = "교육프로그램비율";

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {

			int 계 = dao.계(년도, 학과명);
			int 재학생수 = defaultQuery.재학생수(년도, 학과명);

			float 교육프로그램비율계산 = 교육프로그램비율(계, 재학생수);

			dao.update교육프로그램비율(년도, 학과명, 교육프로그램비율계산);
		}
		float 평균 = (float) defaultQuery.avg(년도, column, table);
		float 표준편차 = (float) defaultQuery.std(년도, column, table);

		for (String 학과명 : 학과목록) {
			float 교육프로그램참여비율 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(교육프로그램참여비율, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}