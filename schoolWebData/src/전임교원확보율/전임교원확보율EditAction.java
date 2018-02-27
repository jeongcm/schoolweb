package 전임교원확보율;

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
import 전임교원확보율.전임교원확보율Dao;

@WebServlet("/전임교원확보율EditAction")

public class 전임교원확보율EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	전임교원확보율Dao dao = new 전임교원확보율Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {

			String 학과명 = request.getParameter("학과명");
			int 년도 = Integer.parseInt(request.getParameter("년도"));

			int 대학원생정원 = defaultClass.nullCheck(request.getParameter("대학원생정원")) ? 0
					: Integer.parseInt(request.getParameter("대학원생정원"));
			int 대학원재학생 = defaultClass.nullCheck(request.getParameter("대학원재학생")) ? 0
					: Integer.parseInt(request.getParameter("대학원재학생"));
			int 학생정원기준전임교원 = defaultClass.nullCheck(request.getParameter("학생정원기준전임교원")) ? 0
					: Integer.parseInt(request.getParameter("학생정원기준전임교원"));
			int 재학생기준전임교원 = defaultClass.nullCheck(request.getParameter("재학생기준전임교원")) ? 0
					: Integer.parseInt(request.getParameter("재학생기준전임교원"));

			int 학생정원 = dao.학생정원(년도, 학과명);
			int 군휴학자 = dao.군휴학자(년도, 학과명);
			int 재학생 = dao.재학생(년도, 학과명);

			String 학문계열 = dao._5대계열(년도, 학과명);
			String 입력부서 = request.getParameter("입력부서");

			int 인정학생정원 = 학생정원 - 군휴학자;

			int 학생정원계 = 인정학생정원 + 대학원생정원;
			int 재학생계 = 재학생 + 대학원재학생;

			int 교원_학부_정원 = 교원법정정원(학문계열, 인정학생정원);
			int 교원_학부_재학생 = 교원법정정원(학문계열, 재학생);
			int 교원_대학원_정원 = 교원법정정원(학문계열, 대학원생정원);
			int 교원_대학원_재학생 = 교원법정정원(학문계열, 대학원재학생);
			int 교원_계_정원 = 교원법정정원(학문계열, 학생정원계);
			int 교원_계_재학생 = 교원법정정원(학문계열, 재학생계);

			float 학생수_정원 = 학생수_정원(학생정원계, 학생정원기준전임교원);
			float 학생수_재학생 = 학생수_재학생(재학생계, 재학생기준전임교원);
			float 확보율_정원 = 확보율_정원(학생정원기준전임교원, 교원_계_정원);
			float 확보율_재학생 = 확보율_재학생(재학생기준전임교원, 교원_계_재학생);

			float 전임교원확보율값 = Math.min(확보율_정원, 확보율_재학생);

			전임교원확보율 전임교원확보율 = new 전임교원확보율();

			전임교원확보율.set년도(년도);
			전임교원확보율.set학과명(학과명);
			전임교원확보율.set교원_계_재학생(교원_계_재학생);
			전임교원확보율.set교원_계_정원(교원_계_정원);
			전임교원확보율.set교원_대학원_재학생(교원_대학원_재학생);
			전임교원확보율.set교원_대학원_정원(교원_대학원_정원);
			전임교원확보율.set교원_학부_재학생(교원_학부_재학생);
			전임교원확보율.set교원_학부_정원(교원_학부_정원);
			전임교원확보율.set대학원생정원(대학원생정원);
			전임교원확보율.set대학원재학생(대학원재학생);
			전임교원확보율.set인정학생정원(인정학생정원);
			전임교원확보율.set확보율_정원(확보율_정원);
			전임교원확보율.set확보율_재학생(확보율_재학생);
			전임교원확보율.set학생정원기준전임교원(학생정원기준전임교원);
			전임교원확보율.set재학생기준전임교원(재학생기준전임교원);
			전임교원확보율.set학생수_정원(학생수_정원);
			전임교원확보율.set학생수_재학생(학생수_재학생);
			전임교원확보율.set전임교원확보율(전임교원확보율값);
			전임교원확보율.set입력부서(입력부서);

			PrintWriter out = response.getWriter();

			if (!dao.insert(전임교원확보율)) {
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
			if (defaultQuery.학과명체크(학과명).equals("0")) {
				out.write("failMajor");
				out.flush();
				out.close();
			} else if (defaultQuery.년도체크(년도).equals("0")) {
				out.write("failYear");
				out.flush();
				out.close();
			} else {
				int 연번 = Integer.parseInt(request.getParameter("연번"));
				int 대학원생정원 = defaultClass.nullCheck(request.getParameter("대학원생정원")) ? 0
						: Integer.parseInt(request.getParameter("대학원생정원"));
				int 대학원재학생 = defaultClass.nullCheck(request.getParameter("대학원재학생")) ? 0
						: Integer.parseInt(request.getParameter("대학원재학생"));
				int 학생정원기준전임교원 = defaultClass.nullCheck(request.getParameter("학생정원기준전임교원")) ? 0
						: Integer.parseInt(request.getParameter("학생정원기준전임교원"));
				int 재학생기준전임교원 = defaultClass.nullCheck(request.getParameter("재학생기준전임교원")) ? 0
						: Integer.parseInt(request.getParameter("재학생기준전임교원"));

				int 학생정원 = dao.학생정원(년도, 학과명);
				int 군휴학자 = dao.군휴학자(년도, 학과명);
				int 재학생 = dao.재학생(년도, 학과명);
				String 학문계열 = defaultQuery.학문계열(년도, 학과명);
				int 인정학생정원 = 학생정원 - 군휴학자;

				int 학생정원계 = 인정학생정원 + 대학원생정원;
				int 재학생계 = 재학생 + 대학원재학생;

				int 교원_학부_정원 = 교원법정정원(학문계열, 인정학생정원);
				int 교원_학부_재학생 = 교원법정정원(학문계열, 재학생);
				int 교원_대학원_정원 = 교원법정정원(학문계열, 대학원생정원);
				int 교원_대학원_재학생 = 교원법정정원(학문계열, 대학원재학생);
				int 교원_계_정원 = 교원법정정원(학문계열, 학생정원계);
				int 교원_계_재학생 = 교원법정정원(학문계열, 재학생계);

				float 학생수_정원 = 학생수_정원(학생정원계, 학생정원기준전임교원);
				float 학생수_재학생 = 학생수_재학생(재학생계, 재학생기준전임교원);
				float 확보율_정원 = 확보율_정원(학생정원기준전임교원, 교원_계_정원);
				float 확보율_재학생 = 확보율_재학생(재학생기준전임교원, 교원_계_재학생);

				float 전임교원확보율값 = Math.min(확보율_정원, 확보율_재학생);

				전임교원확보율 전임교원확보율 = new 전임교원확보율();
				전임교원확보율.set연번(연번);
				전임교원확보율.set년도(년도);
				전임교원확보율.set학과명(학과명);
				전임교원확보율.set교원_계_재학생(교원_계_재학생);
				전임교원확보율.set교원_계_정원(교원_계_정원);
				전임교원확보율.set교원_대학원_재학생(교원_대학원_재학생);
				전임교원확보율.set교원_대학원_정원(교원_대학원_정원);
				전임교원확보율.set교원_학부_재학생(교원_학부_재학생);
				전임교원확보율.set교원_학부_정원(교원_학부_정원);
				전임교원확보율.set대학원생정원(대학원생정원);
				전임교원확보율.set대학원재학생(대학원재학생);
				전임교원확보율.set인정학생정원(인정학생정원);
				전임교원확보율.set확보율_정원(확보율_정원);
				전임교원확보율.set확보율_재학생(확보율_재학생);
				전임교원확보율.set학생정원기준전임교원(학생정원기준전임교원);
				전임교원확보율.set재학생기준전임교원(재학생기준전임교원);
				전임교원확보율.set학생수_정원(학생수_정원);
				전임교원확보율.set학생수_재학생(학생수_재학생);
				전임교원확보율.set전임교원확보율(전임교원확보율값);

				if (!dao.update(전임교원확보율)) {
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

	public float 확보율_정원(int 학생정원기준전임교원, int 교원_계_정원) { //학생수와 확보율은 반올림안함
		float 확보율정원 = (float)(학생정원기준전임교원 / (교원_계_정원 * 1.0) * 100);
		System.out.println("교원계정원 :" + 교원_계_정원 + " 학생정원기준전임교원 :" + 학생정원기준전임교원);
		return 확보율정원;
	}

	public float 확보율_재학생(int 재학생기준전임교원, int 교원_계_재학생) {
		float 확보율재학생 = (float) (재학생기준전임교원 / (교원_계_재학생 * 1.0) * 100);
		System.out.println("교원계재학생 :" + 교원_계_재학생 + "재학생기준전임교원 :" + 재학생기준전임교원);
		return 확보율재학생;
	}

	public float 학생수_재학생(int 재학생계, int 재학생기준전임교원) {
		float 학생수재학생 = (float) (재학생계 / (재학생기준전임교원 * 1.0));
		System.out.println("재학생계 :" + 재학생계 + " 재학생기준전임교원 :" + 재학생기준전임교원);
		return 학생수재학생;
	}

	public float 학생수_정원(int 학생정원계, int 학생정원기준전임교원) {
		float 학생수정원 = (float) (학생정원계 / (학생정원기준전임교원 * 1.0));
		System.out.println("학생정원계 :" + 학생정원계 + " 학생정원기준전임교원 :" + 학생정원기준전임교원);
		return 학생수정원;
	}

	public int 교원법정정원(String 학문계열, int 값) {
		
		int 결과값 = 0;
		if (학문계열.equals("인문사회")) {
			결과값 = (int) Math.ceil(값 / 25.0);  //절상 함수
			System.out.println("교원 법정 정원 결과값 :" + 결과값);
		} else {
			결과값 = (int) Math.ceil(값 / 20.0);
			System.out.println("교원 법정 정원결과값 :" + 결과값);
		}
		return 결과값;
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "전임교원확보율";
		String table = "전임교원확보율";
		float 평균 = defaultQuery.avg(년도, column, table);
		float 표준편차 = defaultQuery.std(년도, column, table);

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);
		for (String 학과명 : 학과목록) {
			float 전임교원확보율값 = defaultQuery.비율(년도, 학과명, column, table);

			float T점수 = defaultClass.T점수(전임교원확보율값, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}