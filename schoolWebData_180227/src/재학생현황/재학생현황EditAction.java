package 재학생현황;

import defaultMethod.defaultClass;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import 재학생현황.재학생현황Dao;
import defaultMethod.defaultQuery;
import defaultMethod.년도;

@WebServlet("/재학생현황EditAction")

public class 재학생현황EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	재학생현황Dao dao = new 재학생현황Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			String 학과명 = request.getParameter("학과명");
			int 년도 = Integer.parseInt(request.getParameter("년도"));

			int 군휴학자 = defaultClass.nullCheck(request.getParameter("군휴학자")) ? 0
					: Integer.parseInt(request.getParameter("군휴학자"));
			int 타학과전과자 = defaultClass.nullCheck(request.getParameter("타학과전과자")) ? 0
					: Integer.parseInt(request.getParameter("타학과전과자"));

			int 학생정원 = Integer.parseInt(request.getParameter("학생정원"));
			int 정원내 = Integer.parseInt(request.getParameter("정원내"));
			int 정원외 = Integer.parseInt(request.getParameter("정원외"));
			String 입력부서 = request.getParameter("입력부서");

			int 계 = 정원내 + 정원외;
			float 전체재학생충원율 = 전체재학생충원율(계, 학생정원, 군휴학자, 타학과전과자);
			float 정원내재학생충원율 = 정원내재학생충원율(정원내, 학생정원, 군휴학자, 타학과전과자);
			float 재학생충원율 = 재학생충원율(전체재학생충원율, 정원내재학생충원율);

			재학생현황 재학생현황 = new 재학생현황();

			재학생현황.set전체재학생충원율(전체재학생충원율);
			재학생현황.set정원내재학생충원율(정원내재학생충원율);
			재학생현황.set재학생충원율(재학생충원율);
			재학생현황.set군휴학자(군휴학자);
			재학생현황.set타학과전과자(타학과전과자);
			재학생현황.set학과명(학과명);
			재학생현황.set년도(년도);
			재학생현황.set계(계);
			재학생현황.set정원내(정원내);
			재학생현황.set정원외(정원외);
			재학생현황.set학생정원(학생정원);
			재학생현황.set입력부서(입력부서);

			PrintWriter out = response.getWriter();

			if (!dao.insert(재학생현황)) {
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

				int 군휴학자 = defaultClass.nullCheck(request.getParameter("군휴학자")) ? 0
						: Integer.parseInt(request.getParameter("군휴학자"));
				int 타학과전과자 = defaultClass.nullCheck(request.getParameter("타학과전과자")) ? 0
						: Integer.parseInt(request.getParameter("타학과전과자"));
				int 학생정원 = Integer.parseInt(request.getParameter("학생정원"));
				int 정원내 = Integer.parseInt(request.getParameter("정원내"));
				int 정원외 = Integer.parseInt(request.getParameter("정원외"));

				int 계 = 정원내 + 정원외;

				float 전체재학생충원율 = 전체재학생충원율(계, 학생정원, 군휴학자, 타학과전과자);
				float 정원내재학생충원율 = 정원내재학생충원율(정원내, 학생정원, 군휴학자, 타학과전과자);
				float 재학생충원율 = 재학생충원율(전체재학생충원율, 정원내재학생충원율);

				재학생현황 재학생현황 = new 재학생현황();

				재학생현황.set학과명(학과명);
				재학생현황.set연번(연번);
				재학생현황.set년도(년도);
				재학생현황.set군휴학자(군휴학자);
				재학생현황.set타학과전과자(타학과전과자);
				재학생현황.set정원내(정원내);
				재학생현황.set정원외(정원외);
				재학생현황.set학생정원(학생정원);
				재학생현황.set계(계);
				재학생현황.set전체재학생충원율(전체재학생충원율);
				재학생현황.set정원내재학생충원율(정원내재학생충원율);
				재학생현황.set재학생충원율(재학생충원율);

				if (!dao.update(재학생현황)) {
					out.write("fail");
					out.flush();
					out.close();
				} else {
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

	public float 전체재학생충원율(int 계, int 학생정원, int 군휴학자, int 타학과전과자) {
		float result = (float) (계 / ((학생정원 - (군휴학자 + 타학과전과자)) *1.0));
		float 전체재학생충원율 = result * 100;
		System.out.println("전체 재학생 충원율[ 계 :" + 계 + " 학생정원 :" + 학생정원 + " 군휴학자 :" + 군휴학자 + " 타학과전과자 :" + 타학과전과자
				+ " 계산결과 :" + 전체재학생충원율 + "]");
		return 전체재학생충원율;
	}

	public float 정원내재학생충원율(int 정원내, int 학생정원, int 군휴학자, int 타학과전과자) {
		float result = (float) (정원내 / ((학생정원 - (군휴학자 + 타학과전과자)) * 1.0));
		float 정원내재학생충원율 =result * 100;
		System.out.println("정원내 재학생 충원율[ 정원내 :" + 정원내 + " 학생정원 :" + 학생정원 + " 군휴학자 :" + 군휴학자 + " 타학과전과자 :" + 타학과전과자
				+ " 계산결과 :" + 정원내재학생충원율 + "]");
		return 정원내재학생충원율;
	}

	public float 재학생충원율(float 전체재학생충원율, float 정원내재학생충원율) {
		BigDecimal B_전체재학생충원율 = new BigDecimal(String.valueOf(Float.toString(전체재학생충원율)));
		BigDecimal B_정원내재학생충원율 = new BigDecimal(String.valueOf(Float.toString(정원내재학생충원율)));

		BigDecimal B_0_4 = new BigDecimal("0.4");
		BigDecimal B_0_6 = new BigDecimal("0.6");

		BigDecimal B_재학생충원율=(B_0_4.multiply(B_전체재학생충원율)).add((B_0_6.multiply(B_정원내재학생충원율)));
		float 재학생충원율=B_재학생충원율.floatValue();
		
		
		System.out.println("전체재학생충원율 :"+전체재학생충원율+"정원내재학생충원율 :"+정원내재학생충원율+"재학생충원율 :"+재학생충원율);
		
		return 재학생충원율;
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String table = "재학생현황";
		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			System.out.println(학과명);
			float 평균 =dao.avg(년도);
			float 표준편차 =dao.std(년도);

			float 재학생충원율 = dao.재학생충원율(년도, 학과명);
			float T점수 = defaultClass.T점수(재학생충원율, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
