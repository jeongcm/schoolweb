package 강의담당;

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

import 강의담당.강의담당Dao;
import defaultMethod.defaultQuery;

@WebServlet("/강의담당EditAction")

public class 강의담당EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	강의담당Dao dao = new 강의담당Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		try {
			if (oper.equals("add")) {
				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));
				float 전공과목 = defaultClass.nullCheck(request.getParameter("전공과목")) ? 0
						: Float.parseFloat(request.getParameter("전공과목"));
				float 교양필수과목 = defaultClass.nullCheck(request.getParameter("교양필수과목")) ? 0
						: Float.parseFloat(request.getParameter("교양필수과목"));
				float 자유선택과목 = defaultClass.nullCheck(request.getParameter("자유선택과목")) ? 0
						: Float.parseFloat(request.getParameter("자유선택과목"));
				String 입력부서 = request.getParameter("입력부서");
				float 교원교양필수과목 = dao.교양필수과목(년도, 학과명);
				float 계 = dao.과목총계(년도, 학과명);
				float 강의담당비율 = 강의담당(전공과목, 교원교양필수과목, 자유선택과목, 계);

				강의담당 강의담당계산 = new 강의담당();

				강의담당계산.set년도(년도);
				강의담당계산.set학과명(학과명);
				강의담당계산.set교양필수과목(교양필수과목);
				강의담당계산.set자유선택과목(자유선택과목);
				강의담당계산.set전공과목(전공과목);
				강의담당계산.set강의담당비율(강의담당비율);
				강의담당계산.set입력부서(입력부서);

				if (!dao.insert(강의담당계산)) {
					System.out.println("여기서 error?");
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.flush();
					out.close();
				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {

				비율재계산();

			} else if (oper.equals("edit")) {

				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));
				float 전공과목 = defaultClass.nullCheck(request.getParameter("전공과목")) ? 0
						: Float.parseFloat(request.getParameter("전공과목"));
				float 교양필수과목 = defaultClass.nullCheck(request.getParameter("교양필수과목")) ? 0
						: Float.parseFloat(request.getParameter("교양필수과목"));
				float 자유선택과목 = defaultClass.nullCheck(request.getParameter("자유선택과목")) ? 0
						: Float.parseFloat(request.getParameter("자유선택과목"));
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
					강의담당 강의담당 = new 강의담당();

					float 교원교양필수과목 = dao.교양필수과목(년도, 학과명);
					float 계 = dao.과목총계(년도, 학과명);
					float 강의담당비율 = 강의담당(전공과목, 교원교양필수과목, 자유선택과목, 계);

					강의담당.set년도(년도);
					강의담당.set학과명(학과명);
					강의담당.set교양필수과목(교양필수과목);
					강의담당.set전공과목(전공과목);
					강의담당.set자유선택과목(자유선택과목);
					강의담당.set강의담당비율(강의담당비율);
					강의담당.set연번(연번);

					if (!dao.update(강의담당)) {
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

	public float 강의담당(float 전공과목, float 교원교양필수과목, float 자유선택과목, float 계) {
		BigDecimal B_전공과목 = new BigDecimal(String.valueOf(전공과목));
		BigDecimal B_교원교양필수과목 = new BigDecimal(String.valueOf(교원교양필수과목));
		BigDecimal B_자유선택과목 = new BigDecimal(String.valueOf(자유선택과목));
		BigDecimal B_계 = new BigDecimal(String.valueOf(계)); // 계는 교원강의의 학점들을 합친값

		BigDecimal B_100 = new BigDecimal("100");

		System.out.println("전공과목 :" + 전공과목 + " 교원교양필수과목 :" + 교원교양필수과목 + " 자유선택과목:" + 자유선택과목 + " 합계 :" + 계);
		if (계 > 0) {
			float 강의담당 = (B_계.divide((B_전공과목.add(B_교원교양필수과목).add(B_자유선택과목)), 12, BigDecimal.ROUND_DOWN)).multiply(B_100)
					.floatValue();
			System.out.println("강의담당계산결과" + 강의담당);
			return 강의담당;
		} else {
			return 0;
		}
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "강의담당비율";
		String table = "개설강의담당비율";

		float 평균 = defaultQuery.avg(년도, column, table);
		float 표준편차 = defaultQuery.std(년도, column, table);

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			float 강의담당비율 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(강의담당비율, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}