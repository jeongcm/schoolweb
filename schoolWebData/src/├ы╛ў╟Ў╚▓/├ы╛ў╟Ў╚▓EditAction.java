package 취업현황;

import java.io.IOException;
import defaultMethod.년도;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 취업현황.취업현황Dao;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;

@WebServlet("/취업현황EditAction")

public class 취업현황EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	취업현황Dao dao = new 취업현황Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");
		try {
			if (oper.equals("add")) {
				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));
				int 졸업자 = defaultClass.nullCheck(request.getParameter("졸업자")) ? 0
						: Integer.parseInt(request.getParameter("졸업자"));
				float 이차유지취업률 = defaultClass.nullCheck(request.getParameter("이차유지취업률")) ? 0
						: Float.parseFloat(request.getParameter("이차유지취업률"));
				String 입력부서 = request.getParameter("입력부서");
				
				취업현황 취업현황 = new 취업현황();

				취업현황.set년도(년도);
				취업현황.set학과명(학과명);
				취업현황.set졸업자(졸업자);
				취업현황.set이차유지취업률(이차유지취업률);
				취업현황.set입력부서(입력부서);
				PrintWriter out = response.getWriter();

				if (!dao.insert(취업현황)) {
					out.write("fail");
					out.flush();
					out.close();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {
			
				 비율재계산();
			
			} else if (oper.equals("edit")) {
				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));
				int 연번 = Integer.parseInt(request.getParameter("연번"));
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
					학과명 = request.getParameter("학과명");
					년도 = Integer.parseInt(request.getParameter("년도"));
					int 졸업자 = defaultClass.nullCheck(request.getParameter("졸업자")) ? 0
							: Integer.parseInt(request.getParameter("졸업자"));
					float 이차유지취업률 = defaultClass.nullCheck(request.getParameter("이차유지취업률")) ? 0
							: Float.parseFloat(request.getParameter("이차유지취업률"));
					취업현황 취업현황 = new 취업현황();

					취업현황.set연번(연번);
					취업현황.set년도(년도);
					취업현황.set학과명(학과명);
					취업현황.set졸업자(졸업자);
					취업현황.set이차유지취업률(이차유지취업률);

					if (!dao.update(취업현황)) {
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

	public float 취업률(String 학과명, int 년도) {
		BigDecimal B_취업자 = new BigDecimal(String.valueOf(dao.취업자(학과명, 년도))); // 취업자+취업인정자-입학당시기취업자
		BigDecimal B_취업제외자 = new BigDecimal(String.valueOf(dao.취업제외자(학과명, 년도))); //졸업자-제외자-입학당시기취업자

		BigDecimal B_0_5 = new BigDecimal("0.5");
		BigDecimal B_100 = new BigDecimal("100");

		System.out.println("취업자 :" + B_취업자 + "취업제외자 :" + B_취업제외자);
		
		BigDecimal result=new BigDecimal('0');
		
		if (B_취업자.compareTo(BigDecimal.ZERO) !=0 && B_취업제외자.compareTo(BigDecimal.ZERO) !=0)
			result = (B_취업자.divide(B_취업제외자, 12,BigDecimal.ROUND_DOWN)).multiply(B_0_5); //((0.5*취업자/취업제외자)+(2차취업률*0.5))*100이 공식이며 result는 앞에 취업자/취업제외자*100부분을 말함

		System.out.println("result계산결과 :"+result);
		
		BigDecimal B_2차유지취업률 = new BigDecimal(String.valueOf(dao.이차유지취업률(학과명, 년도)));//산출식이 변경될것이야 12.19일 기록

		System.out.println("2차 유지취업률" + B_2차유지취업률);

		float 취업률 = (result.add(B_0_5.multiply(B_2차유지취업률))).multiply(B_100).floatValue();

		System.out.println("취업률 계산 결과" + 취업률);

		return 취업률;

	}

	public void 비율재계산() {

		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "취업률";
		String table = "취업현황";

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			float 취업률계산 = 취업률(학과명, 년도);
			dao.취업률update(취업률계산, 학과명, 년도);

			String 학문계열 = defaultQuery.학문계열(년도, 학과명);

			float 평균 = defaultQuery.학문계열avg(년도, column, table, 학문계열);
			float 표준편차 =defaultQuery.학문계열std(년도, column, table, 학문계열);

			float 취업률 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(취업률, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}