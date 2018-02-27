package 국세db취업자외부;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import 취업현황외부.취업현황외부Dao;

@WebServlet("/국세db취업자외부EditAction")

public class 국세db취업자외부EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String 수정대학명 = null;
	취업현황외부Dao 취업현황dao = new 취업현황외부Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");
		국세db취업자외부Dao dao = new 국세db취업자외부Dao();
		if (oper.equals("add")) {
			String 월 = request.getParameter("월");
			String 학과명 = request.getParameter("학과명");
			수정대학명 = request.getParameter("대학명");
			String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
			String 입력부서 = request.getParameter("입력부서");
			int 개인창작활동조사서 = defaultClass.nullCheck(request.getParameter("개인창작활동조사서")) ? 0
					: Integer.parseInt(request.getParameter("개인창작활동조사서"));
			int 일인창업자 = defaultClass.nullCheck(request.getParameter("일인창업자")) ? 0
					: Integer.parseInt(request.getParameter("일인창업자"));
			int 프리랜서 = defaultClass.nullCheck(request.getParameter("프리랜서")) ? 0
					: Integer.parseInt(request.getParameter("프리랜서"));

			int 계 = 개인창작활동조사서 + 일인창업자 + 프리랜서;
			국세취업자외부 국세db취업자외부 = new 국세취업자외부();

			국세db취업자외부.set월(월);
			국세db취업자외부.set대학명(수정대학명);
			국세db취업자외부.set학과명(학과명);
			국세db취업자외부.set비고(비고);
			국세db취업자외부.set계(계);
			국세db취업자외부.set개인창작활동조사서(개인창작활동조사서);
			국세db취업자외부.set일인창업자(일인창업자);
			국세db취업자외부.set프리랜서(프리랜서);
			국세db취업자외부.set입력부서(입력부서);

			PrintWriter out = response.getWriter();

			if (dao.insert(국세db취업자외부) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}

		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();

			String 학과명 = request.getParameter("학과명");
			수정대학명 = request.getParameter("대학명");
			String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
			int 연번 = Integer.parseInt(request.getParameter("연번"));
			int 개인창작활동조사서 = defaultClass.nullCheck(request.getParameter("개인창작활동조사서")) ? 0
					: Integer.parseInt(request.getParameter("개인창작활동조사서"));
			int 일인창업자 = defaultClass.nullCheck(request.getParameter("일인창업자")) ? 0
					: Integer.parseInt(request.getParameter("일인창업자"));
			int 프리랜서 = defaultClass.nullCheck(request.getParameter("프리랜서")) ? 0
					: Integer.parseInt(request.getParameter("프리랜서"));

			int 계 = 개인창작활동조사서 + 일인창업자 + 프리랜서;

			국세취업자외부 국세db취업자외부 = new 국세취업자외부();

			국세db취업자외부.set연번(연번);
			국세db취업자외부.set비고(비고);
			국세db취업자외부.set학과명(학과명);
			국세db취업자외부.set대학명(수정대학명);
			국세db취업자외부.set계(계);
			국세db취업자외부.set개인창작활동조사서(개인창작활동조사서);
			국세db취업자외부.set일인창업자(일인창업자);
			국세db취업자외부.set프리랜서(프리랜서);

			if (dao.update(국세db취업자외부) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}
		} else if (oper.equals("cal") || oper.equals("editCal")) {
			float 취업률계산 = 취업률(수정대학명);
			취업현황dao.취업률update(취업률계산, 수정대학명);

			String column = "취업률";
			String table = "취업현황외부";

			float 평균 = (float) (Math.round(defaultQuery.외부avg(column, table) * 100) / 100.0);
			float 표준편차 = (float) (Math.round(defaultQuery.외부std(column, table) * 100) / 100.0);

			ArrayList<String> 대학목록 = defaultQuery.외부대학목록(table);

			for (String 대학명 : 대학목록) {
				float 취업률 = defaultQuery.외부비율(대학명, column, table);
				float T점수 = defaultClass.T점수(취업률, 평균, 표준편차);
				defaultQuery.외부updateT(T점수, 대학명, table);
			}
		} else if (oper.equals("del")) {
			int 연번 = Integer.parseInt(request.getParameter("연번"));
			dao.delete(연번);
			float 취업률계산 = 취업률(수정대학명);
			취업현황dao.취업률update(취업률계산, 수정대학명);

			String column = "취업률";
			String table = "취업현황외부";

			float 평균 = (float) (Math.round(defaultQuery.외부avg(column, table) * 100) / 100.0);
			float 표준편차 = (float) (Math.round(defaultQuery.외부std(column, table) * 100) / 100.0);

			ArrayList<String> 대학목록 = defaultQuery.외부대학목록(table);

			for (String 대학명 : 대학목록) {
				float 취업률 = defaultQuery.외부비율(대학명, column, table);
				float T점수 = defaultClass.T점수(취업률, 평균, 표준편차);
				defaultQuery.외부updateT(T점수, 대학명, table);
			}
		} else if (oper.equals("delAll")) {
			String 입력부서 = request.getParameter("입력부서");
			dao.dellAll(입력부서);

		} else {

			System.out.println("잘못된 접근 입니다.");

		}
	}

	public float 취업률(String 대학명) {
		BigDecimal B_6월취업자 = new BigDecimal(String.valueOf(취업현황dao._6월취업자(대학명)));
		BigDecimal B_6월취업제외자 = new BigDecimal(String.valueOf(취업현황dao._6월취업제외자(대학명)));
		BigDecimal B_12월취업자 = new BigDecimal(String.valueOf(취업현황dao._12월취업제외자(대학명)));
		BigDecimal B_12월취업제외자 = new BigDecimal(String.valueOf(취업현황dao._12월취업제외자(대학명)));

		BigDecimal B_0_2 = new BigDecimal("0.2");
		BigDecimal B_0_4 = new BigDecimal("0.4");
		BigDecimal B_100 = new BigDecimal("100");

		BigDecimal mol = B_0_4.multiply(B_6월취업자);

		if (mol.compareTo(BigDecimal.ZERO) > 0)
			mol = mol.divide(B_6월취업제외자);

		BigDecimal mol2 = B_12월취업자.add(B_0_2).multiply(B_12월취업제외자);

		BigDecimal B_2차유지취업률 = new BigDecimal(String.valueOf(취업현황dao.이차유지취업률(대학명)));

		return ((mol.add(mol2).add(B_0_4.multiply(B_2차유지취업률))).multiply(B_100)).floatValue();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
