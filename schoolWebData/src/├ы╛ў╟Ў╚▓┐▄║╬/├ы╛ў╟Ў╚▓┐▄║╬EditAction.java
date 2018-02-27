package 취업현황외부;

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

@WebServlet("/취업현황외부EditAction")

public class 취업현황외부EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	취업현황외부Dao dao = new 취업현황외부Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			String 학과명 = request.getParameter("학과명");
			String 대학명 = request.getParameter("대학명");
			String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
			int 졸업자 = defaultClass.nullCheck(request.getParameter("졸업자")) ? 0
					: Integer.parseInt(request.getParameter("졸업자"));
			float 이차유지취업률 = defaultClass.nullCheck(request.getParameter("이차유지취업률")) ? 0
					: Float.parseFloat(request.getParameter("이차유지취업률"));
			
			String 입력부서 = request.getParameter("입력부서");

			취업현황외부 취업현황 = new 취업현황외부();

			취업현황.set대학명(대학명);
			취업현황.set학과명(학과명);
			취업현황.set비고(비고);
			취업현황.set졸업자(졸업자);
			취업현황.set입력부서(입력부서);

			취업현황.set이차유지취업률(이차유지취업률);

			PrintWriter out = response.getWriter();

			if (dao.insert(취업현황) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}

		} else if (oper.equals("cal") || oper.equals("editCal")) {

			비율재계산();

		} else if (oper.equals("edit")) {
			String 대학명 = request.getParameter("대학명");
			String 학과명 = request.getParameter("학과명");
			String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
			int 연번 = Integer.parseInt(request.getParameter("연번"));
			int 졸업자 = defaultClass.nullCheck(request.getParameter("졸업자")) ? 0
					: Integer.parseInt(request.getParameter("졸업자"));
			float 이차유지취업률 = defaultClass.nullCheck(request.getParameter("이차유지취업률")) ? 0
					: Float.parseFloat(request.getParameter("이차유지취업률"));
			취업현황외부 취업현황 = new 취업현황외부();

			취업현황.set대학명(대학명);
			취업현황.set학과명(학과명);
			취업현황.set비고(비고);
			취업현황.set졸업자(졸업자);
			취업현황.set이차유지취업률(이차유지취업률);
			취업현황.set연번(연번);

			PrintWriter out = response.getWriter();

			if (dao.update(취업현황) == false) {
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
	}

	public float 취업률(String 대학명) {
		BigDecimal B_취업자 = new BigDecimal(String.valueOf(dao.취업자(대학명))); // 취업자+취업인정자-입학당시기취업자
		BigDecimal B_취업제외자 = new BigDecimal(String.valueOf(dao.취업제외자(대학명))); // 졸업자-제외자-입학당시기취업자

		BigDecimal B_0_5 = new BigDecimal("0.5");
		BigDecimal B_100 = new BigDecimal("100");

		System.out.println("취업자 :" + B_취업자 + "취업제외자 :" + B_취업제외자);

		BigDecimal result = new BigDecimal('0');

		if (B_취업자.compareTo(BigDecimal.ZERO) != 0 && B_취업제외자.compareTo(BigDecimal.ZERO) != 0)
			result = (B_취업자.divide(B_취업제외자, 12, BigDecimal.ROUND_DOWN)).multiply(B_0_5);

		System.out.println("result계산결과 :" + result);

		BigDecimal B_2차유지취업률 = new BigDecimal(String.valueOf(dao.이차유지취업률(대학명)));

		System.out.println("2차 유지취업률" + B_2차유지취업률);

		float 취업률 = (result.add(B_0_5.multiply(B_2차유지취업률))).multiply(B_100).floatValue();

		System.out.println("취업률 계산 결과" + 취업률);

		return 취업률;

	}

	public void 비율재계산() {

		String column = "취업률";
		String table = "취업현황";

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
