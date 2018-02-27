package 신입생현황외부;

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

@WebServlet("/신입생현황외부EditAction")

public class 신입생현황외부EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	신입생현황외부Dao dao = new 신입생현황외부Dao();
	String 수정대학명 = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			수정대학명 = request.getParameter("대학명");
			String 학과명 = request.getParameter("학과명");
			String 입력부서 = request.getParameter("입력부서");
			String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
			int 입학자수 = Integer.parseInt(request.getParameter("입학자수"));
			int 모집인원 = Integer.parseInt(request.getParameter("모집인원"));

			float 신입생충원율 = 신입생충원율(입학자수, 모집인원);

			신입생현황외부 신입생현황외부 = new 신입생현황외부();
			신입생현황외부.set입력부서(입력부서);
			신입생현황외부.set대학명(수정대학명);
			신입생현황외부.set비고(비고);
			신입생현황외부.set학과명(학과명);
			신입생현황외부.set모집인원(모집인원);
			신입생현황외부.set입학자수(입학자수);
			신입생현황외부.set신입생충원율(신입생충원율);
			
			PrintWriter out = response.getWriter();

			if (dao.insert(신입생현황외부) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}
			
		} else if (oper.equals("cal") || oper.equals("editCal")) {
			String column = "신입생충원율";
			String table = "신입생현황외부";
			float 평균 = defaultQuery.외부avg(column, table);
			float 표준편차 =defaultQuery.외부std(column, table);

			ArrayList<String> 대학목록 = defaultQuery.외부대학목록(table);
			for (String 대학명 : 대학목록) {
				float 신입생충원율 = defaultQuery.외부비율(대학명, column, table);
				float T점수 = defaultClass.T점수(신입생충원율, 평균, 표준편차);
				defaultQuery.외부updateT(T점수, 대학명, table);
			}
		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();
			수정대학명 = request.getParameter("대학명");
			String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
			String 학과명 = request.getParameter("학과명");
			int 연번 = Integer.parseInt(request.getParameter("연번"));
			int 입학자수 = Integer.parseInt(request.getParameter("입학자수"));
			int 모집인원 = Integer.parseInt(request.getParameter("모집인원"));

			float 신입생충원율 = 신입생충원율(입학자수, 모집인원);
			신입생현황외부 신입생현황외부 = new 신입생현황외부();

			신입생현황외부.set학과명(학과명);
			신입생현황외부.set비고(비고);
			신입생현황외부.set대학명(수정대학명);
			신입생현황외부.set입학자수(입학자수);
			신입생현황외부.set모집인원(모집인원);
			신입생현황외부.set신입생충원율(신입생충원율);
			신입생현황외부.set연번(연번);

			if (dao.update(신입생현황외부) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}

		} else if (oper.equals("del")) {
			int 연번 = Integer.parseInt(request.getParameter("연번"));
			dao.delete(연번);

			String column = "신입생충원율";
			String table = "신입생현황외부";
			float 평균 =defaultQuery.외부avg(column, table);
			float 표준편차 =defaultQuery.외부std(column, table);

			ArrayList<String> 대학목록 = defaultQuery.외부대학목록(table);
			for (String 대학명 : 대학목록) {
				float 신입생충원율 = defaultQuery.외부비율(대학명, column, table);
				float T점수 = defaultClass.T점수(신입생충원율, 평균, 표준편차);
				defaultQuery.외부updateT(T점수, 대학명, table);
			}

		} else if (oper.equals("delAll")) {
			String 입력부서 = request.getParameter("입력부서");
			dao.dellAll(입력부서);

		} else {

			System.out.println("잘못된 접근 입니다.");

		}
	}

	public float 신입생충원율(int 입학자수, int 모집인원) {
		return (float) (입학자수 / (모집인원 * 1.0) * 100);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
