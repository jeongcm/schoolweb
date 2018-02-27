package 연구실적;

import java.io.IOException;
import defaultMethod.년도;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import defaultMethod.defaultClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 연구실적.연구실적Dao;
import defaultMethod.defaultQuery;

@WebServlet("/연구실적EditAction")
public class 연구실적EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	연구실적Dao dao = new 연구실적Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		try {
			if (oper.equals("add")) {

				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));

				int 전임교원수 = defaultQuery.전임교원수(년도, 학과명);
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

				float 연구실적계산 = 연구실적(전임교원수, 저서, 역서, 연구재단등재지, 연구재단등재후보, SCI급, SCOPUS학술지);

				연구실적 연구실적 = new 연구실적();

				연구실적.set년도(년도);
				연구실적.set학과명(학과명);
				연구실적.set저서(저서);
				연구실적.set역서(역서);
				연구실적.set연구재단등재지(연구재단등재지);
				연구실적.set연구재단등재후보(연구재단등재후보);
				연구실적.setSCI급(SCI급);
				연구실적.setSCOPUS학술지(SCOPUS학술지);
				연구실적.set입력부서(입력부서);
				연구실적.set연구실적(연구실적계산);

				if (!dao.insert(연구실적)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				비율재계산();

			} else if (oper.equals("edit")) {
				int 년도 = Integer.parseInt(request.getParameter("년도"));
				String 학과명 = request.getParameter("학과명");

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
					int 연번 = Integer.parseInt(request.getParameter("연번"));
					int 전임교원수 = defaultQuery.전임교원수(년도, 학과명);
					float 연구실적계산 = 연구실적(전임교원수, 저서, 역서, 연구재단등재지, 연구재단등재후보, SCI급, SCOPUS학술지);

					연구실적 연구실적 = new 연구실적();

					연구실적.set년도(년도);
					연구실적.set학과명(학과명);
					연구실적.set저서(저서);
					연구실적.set역서(역서);
					연구실적.set연구재단등재지(연구재단등재지);
					연구실적.set연구재단등재후보(연구재단등재후보);
					연구실적.setSCI급(SCI급);
					연구실적.setSCOPUS학술지(SCOPUS학술지);
					연구실적.set연번(연번);
					연구실적.set연구실적(연구실적계산);

					if (dao.update(연구실적) == false) {
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
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

	public float 연구실적(int 전임교원수, float 저서, float 역서, float 연구재단등재지, float 연구재단등재후보, float SCI급, float SCOPUS학술지) {
		BigDecimal B_전임교원수 = new BigDecimal(String.valueOf(전임교원수));
		BigDecimal B_저서 = new BigDecimal(String.valueOf(저서));
		BigDecimal B_역서 = new BigDecimal(String.valueOf(역서));
		BigDecimal B_연구재단등재지 = new BigDecimal(String.valueOf(연구재단등재지));
		BigDecimal B_연구재단등재후보 = new BigDecimal(String.valueOf(연구재단등재후보));
		BigDecimal B_SCI급 = new BigDecimal(String.valueOf(SCI급));
		BigDecimal B_SCOPUS학술지 = new BigDecimal(String.valueOf(SCOPUS학술지));

		BigDecimal B_1_2 = new BigDecimal("1.2");
		BigDecimal B_0_7 = new BigDecimal("0.7");
		BigDecimal B_1 = new BigDecimal("1");
		BigDecimal B_2 = new BigDecimal("2");

		BigDecimal mol1 = B_저서.multiply(B_1_2).add(B_역서.multiply(B_0_7));
		BigDecimal mol2 = B_연구재단등재지.multiply(B_1_2).add((B_연구재단등재후보.multiply(B_1))).add(B_SCI급.multiply(B_2).add(B_SCOPUS학술지.multiply(B_1_2)));

		System.out.println("역서, 저서 : " + mol1 + ", 등재지, 등재후보 , SCI, SCOPUS : " + mol2);

		if (B_전임교원수.compareTo(BigDecimal.ZERO) > 0) {
			if (mol1.compareTo(BigDecimal.ZERO) <= 0)
				mol1 = new BigDecimal("0.0");
			else
				mol1 = mol1.divide(B_전임교원수, 15, BigDecimal.ROUND_DOWN);

			if (mol2.compareTo(BigDecimal.ZERO) <= 0)
				mol2 = new BigDecimal("0.0");
			else
				mol2 = mol2.divide(B_전임교원수, 15, BigDecimal.ROUND_DOWN);


			float 연구실적 = (mol1.add(mol2)).floatValue();
			
			System.out.println("연구실적계산결과" + 연구실적);
			
			return 연구실적;
		} else {
			return 0;
		}
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "연구실적";
		String table = "연구실적";

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			String 학문계열 = defaultQuery.학문계열(년도, 학과명);

			float 평균 = defaultQuery.학문계열avg(년도, column, table, 학문계열);
			float 표준편차 =defaultQuery.학문계열std(년도, column, table, 학문계열);

			float 연구실적계산 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(연구실적계산, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}