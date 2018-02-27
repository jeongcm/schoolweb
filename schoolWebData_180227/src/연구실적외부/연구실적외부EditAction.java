package 연구실적외부;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 연구실적외부.연구실적외부Dao;
import 연구실적.연구실적EditAction;
import defaultMethod.defaultQuery;

@WebServlet("/연구실적외부EditAction")
public class 연구실적외부EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	연구실적EditAction ea=new 연구실적EditAction();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		연구실적외부Dao dao = new 연구실적외부Dao();
		try {
			if (oper.equals("add")) {

				String 학과명 = request.getParameter("학과명");
				String 대학명 = request.getParameter("대학명");
				int 전임교원수 = defaultClass.nullCheck(request.getParameter("전임교원수")) ? 0
						: Integer.parseInt(request.getParameter("전임교원수"));
				float 저서 = defaultClass.nullCheck(request.getParameter("저서")) ? 0
						: Float.parseFloat((request.getParameter("저서")));
				float 역서 = defaultClass.nullCheck(request.getParameter("역서")) ? 0
						: Float.parseFloat((request.getParameter("역서")));
				float 연구재단등재지 = defaultClass.nullCheck(request.getParameter("연구재단등재지")) ? 0
						: Float.parseFloat((request.getParameter("연구재단등재지")));
				float 연구재단등재후보 = defaultClass.nullCheck(request.getParameter("연구재단등재후보")) ? 0
						: Float.parseFloat((request.getParameter("연구재단등재후보")));
				float SCI급 = defaultClass.nullCheck(request.getParameter("SCI급")) ? 0
						: Float.parseFloat((request.getParameter("SCI급")));
				float SCOPUS학술지 = defaultClass.nullCheck(request.getParameter("SCOPUS학술지")) ? 0
						: Float.parseFloat((request.getParameter("SCOPUS학술지")));
				String 입력부서 = request.getParameter("입력부서");
				String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");

				float 연구실적외부계산 = ea.연구실적(전임교원수, 저서, 역서, 연구재단등재지, 연구재단등재후보, SCI급, SCOPUS학술지);

				연구실적외부 연구실적외부 = new 연구실적외부();

				연구실적외부.set학과명(학과명);
				연구실적외부.set대학명(대학명);
				연구실적외부.set저서(저서);
				연구실적외부.set역서(역서);
				연구실적외부.set연구재단등재지(연구재단등재지);
				연구실적외부.set연구재단등재후보(연구재단등재후보);
				연구실적외부.setSCI급(SCI급);
				연구실적외부.setSCOPUS학술지(SCOPUS학술지);
				연구실적외부.set입력부서(입력부서);
				연구실적외부.set연구실적(연구실적외부계산);
				연구실적외부.set비고(비고);

				if (!dao.insert(연구실적외부)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				비율재계산();

			} else if (oper.equals("edit")) {
				PrintWriter out = response.getWriter();
				
				String 학과명 = request.getParameter("학과명");
				String 대학명 = request.getParameter("대학명");
				int 전임교원수 = defaultClass.nullCheck(request.getParameter("전임교원수")) ? 0
						: Integer.parseInt(request.getParameter("전임교원수"));
				float 저서 = defaultClass.nullCheck(request.getParameter("저서")) ? 0
						: Float.parseFloat((request.getParameter("저서")));
				float 역서 = defaultClass.nullCheck(request.getParameter("역서")) ? 0
						: Float.parseFloat((request.getParameter("역서")));
				float 연구재단등재지 = defaultClass.nullCheck(request.getParameter("연구재단등재지")) ? 0
						: Float.parseFloat((request.getParameter("연구재단등재지")));
				float 연구재단등재후보 = defaultClass.nullCheck(request.getParameter("연구재단등재후보")) ? 0
						: Float.parseFloat((request.getParameter("연구재단등재후보")));
				float SCI급 = defaultClass.nullCheck(request.getParameter("SCI급")) ? 0
						: Float.parseFloat((request.getParameter("SCI급")));
				float SCOPUS학술지 = defaultClass.nullCheck(request.getParameter("SCOPUS학술지")) ? 0
						: Float.parseFloat((request.getParameter("SCOPUS학술지")));
				String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
				int 연번 = Integer.parseInt(request.getParameter("연번"));
				
				float 연구실적외부계산 = ea.연구실적(전임교원수, 저서, 역서, 연구재단등재지, 연구재단등재후보, SCI급, SCOPUS학술지);

				연구실적외부 연구실적외부 = new 연구실적외부();

				연구실적외부.set학과명(학과명);
				연구실적외부.set저서(저서);
				연구실적외부.set대학명(대학명);
				연구실적외부.set비고(비고);
				연구실적외부.set역서(역서);
				연구실적외부.set연구재단등재지(연구재단등재지);
				연구실적외부.set연구재단등재후보(연구재단등재후보);
				연구실적외부.setSCI급(SCI급);
				연구실적외부.setSCOPUS학술지(SCOPUS학술지);
				연구실적외부.set연번(연번);
				연구실적외부.set연구실적(연구실적외부계산);

				if (dao.update(연구실적외부) == false) {
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
		String column = "연구실적";
		String table = "연구실적외부";

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