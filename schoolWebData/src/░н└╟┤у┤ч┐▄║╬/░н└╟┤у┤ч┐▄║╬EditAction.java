package 강의담당외부;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 강의담당외부.강의담당외부Dao;
import 강의담당.강의담당EditAction;
import defaultMethod.defaultQuery;

@WebServlet("/강의담당외부EditAction")

public class 강의담당외부EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	강의담당EditAction ea = new 강의담당EditAction();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		강의담당외부Dao dao = new 강의담당외부Dao();

		try {
			if (oper.equals("add")) {
				String 학과명 = request.getParameter("학과명");
				String 대학명 = request.getParameter("대학명");
				float 전공과목 = defaultClass.nullCheck(request.getParameter("전공과목")) ? 0
						: Float.parseFloat(request.getParameter("전공과목"));
				float 교양필수과목 = defaultClass.nullCheck(request.getParameter("교양필수과목")) ? 0
						: Float.parseFloat(request.getParameter("교양필수과목"));
				float 자유선택과목 = defaultClass.nullCheck(request.getParameter("자유선택과목")) ? 0
						: Float.parseFloat(request.getParameter("자유선택과목"));
				String 입력부서 = request.getParameter("입력부서");
				String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
				float 교원교양필수과목 = dao.교양필수과목(대학명);
				float 계 = dao.과목총계(대학명);
				float 강의담당비율 = ea.강의담당(전공과목, 교원교양필수과목, 자유선택과목, 계);

				강의담당외부 강의담당외부계산 = new 강의담당외부();

				강의담당외부계산.set대학명(대학명);
				강의담당외부계산.set학과명(학과명);
				강의담당외부계산.set교양필수과목(교양필수과목);
				강의담당외부계산.set비고(비고);
				강의담당외부계산.set자유선택과목(자유선택과목);
				강의담당외부계산.set전공과목(전공과목);
				강의담당외부계산.set강의담당비율(강의담당비율);
				강의담당외부계산.set입력부서(입력부서);

				if (!dao.insert(강의담당외부계산)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.flush();
					out.close();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {
			
				비율재계산();
				
			} else if (oper.equals("edit")) {

				String 학과명 = request.getParameter("학과명");
				String 대학명 = request.getParameter("대학명");
				float 전공과목 = defaultClass.nullCheck(request.getParameter("전공과목")) ? 0
						: Float.parseFloat(request.getParameter("전공과목"));
				float 교양필수과목 = defaultClass.nullCheck(request.getParameter("교양필수과목")) ? 0
						: Float.parseFloat(request.getParameter("교양필수과목"));
				String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
				float 자유선택과목 = defaultClass.nullCheck(request.getParameter("자유선택과목")) ? 0
						: Float.parseFloat(request.getParameter("자유선택과목"));
				int 연번 = Integer.parseInt(request.getParameter("연번"));
				PrintWriter out = response.getWriter();

				강의담당외부 강의담당외부 = new 강의담당외부();

				float 교원교양필수과목 = dao.교양필수과목(대학명);
				float 계 = dao.과목총계(대학명);
				float 강의담당비율 = ea.강의담당(전공과목, 교원교양필수과목, 자유선택과목, 계);

				강의담당외부.set대학명(대학명);
				강의담당외부.set학과명(학과명);
				강의담당외부.set비고(비고);
				강의담당외부.set교양필수과목(교양필수과목);
				강의담당외부.set전공과목(전공과목);
				강의담당외부.set자유선택과목(자유선택과목);
				강의담당외부.set강의담당비율(강의담당비율);
				강의담당외부.set연번(연번);

				if (!dao.update(강의담당외부)) {
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