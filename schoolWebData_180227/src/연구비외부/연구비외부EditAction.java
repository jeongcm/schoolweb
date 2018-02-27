package 연구비외부;

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
import 연구비외부.연구비외부Dao;
import 연구비.연구비EditAction;

@WebServlet("/연구비외부EditAction")

public class 연구비외부EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	연구비EditAction ea = new 연구비EditAction();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		연구비외부Dao dao = new 연구비외부Dao();

		try {
			if (oper.equals("add")) {
				String 학과명 = request.getParameter("학과명");
				String 대학명 = request.getParameter("대학명");
				int 전임교원수 = defaultClass.nullCheck(request.getParameter("전임교원수")) ? 0
						: Integer.parseInt(request.getParameter("전임교원수"));
				int 중앙정부 = defaultClass.nullCheck(request.getParameter("중앙정부")) ? 0
						: Integer.parseInt(request.getParameter("중앙정부"));
				int 지자체 = defaultClass.nullCheck(request.getParameter("지자체")) ? 0
						: Integer.parseInt(request.getParameter("지자체"));
				int 민간 = defaultClass.nullCheck(request.getParameter("민간")) ? 0
						: Integer.parseInt(request.getParameter("민간"));
				int 외국 = defaultClass.nullCheck(request.getParameter("외국")) ? 0
						: Integer.parseInt(request.getParameter("외국"));
				int 계 = 계(중앙정부, 지자체, 민간, 외국);
				String 입력부서 = request.getParameter("입력부서");
				String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");

				float 연구비 = 연구비외부(전임교원수, 계);

				연구비외부 연구비외부bean = new 연구비외부();

				연구비외부bean.set대학명(대학명);
				연구비외부bean.set학과명(학과명);
				연구비외부bean.set지자체(지자체);
				연구비외부bean.set중앙정부(중앙정부);
				연구비외부bean.set민간(민간);
				연구비외부bean.set외국(외국);
				연구비외부bean.set연구비(연구비);
				연구비외부bean.set계(계);
				연구비외부bean.set입력부서(입력부서);
				연구비외부bean.set비고(비고);
				연구비외부bean.set전임교원수(전임교원수);

				if (!dao.insert(연구비외부bean)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();

				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				비율재계산();

			} else if (oper.equals("edit")) {
				PrintWriter out = response.getWriter();
				int 연번 = Integer.parseInt(request.getParameter("연번"));
				String 학과명 = request.getParameter("학과명");
				String 대학명 = request.getParameter("대학명");
				int 전임교원수 = defaultClass.nullCheck(request.getParameter("전임교원수")) ? 0
						: Integer.parseInt(request.getParameter("전임교원수"));
				int 중앙정부 = defaultClass.nullCheck(request.getParameter("중앙정부")) ? 0
						: Integer.parseInt(request.getParameter("중앙정부"));
				int 지자체 = defaultClass.nullCheck(request.getParameter("지자체")) ? 0
						: Integer.parseInt(request.getParameter("지자체"));
				int 민간 = defaultClass.nullCheck(request.getParameter("민간")) ? 0
						: Integer.parseInt(request.getParameter("민간"));
				int 외국 = defaultClass.nullCheck(request.getParameter("외국")) ? 0
						: Integer.parseInt(request.getParameter("외국"));
				int 계 = 계(중앙정부, 지자체, 민간, 외국);
				String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");

				float 연구비 = 연구비외부(전임교원수, 계);

				연구비외부 연구비외부bean = new 연구비외부();

				연구비외부bean.set연번(연번);
				연구비외부bean.set학과명(학과명);
				연구비외부bean.set대학명(대학명);
				연구비외부bean.set지자체(지자체);
				연구비외부bean.set중앙정부(중앙정부);
				연구비외부bean.set민간(민간);
				연구비외부bean.set외국(외국);
				연구비외부bean.set연구비(연구비);
				연구비외부bean.set전임교원수(전임교원수);
				연구비외부bean.set비고(비고);
				연구비외부bean.set계(계);

				if (!dao.update(연구비외부bean)) {
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
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

	public float 연구비외부(int 전임교원수, int 계) {
		return (float) (계 / (전임교원수 * 1.0));
	}

	public int 계(int 중앙정부, int 지자체, int 민간, int 외국) {
		return 중앙정부 + 지자체 + 민간 + 외국;
	}

	public void 비율재계산() {
		String column = "연구비";
		String table = "연구비외부";

		ArrayList<String> 대학목록 = defaultQuery.외부대학목록(table);

		for (String 대학명 : 대학목록) {
			float 평균 = defaultQuery.외부avg(column, table);
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