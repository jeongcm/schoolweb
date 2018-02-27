package 강의공개실적;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import defaultMethod.defaultClass;
import defaultMethod.년도;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 강의공개실적.강의공개실적Dao;
import defaultMethod.defaultQuery;
import java.text.DecimalFormat;

@WebServlet("/강의공개실적EditAction")
public class 강의공개실적EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	강의공개실적Dao dao = new 강의공개실적Dao();
	DecimalFormat format = new DecimalFormat("0.00");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String oper = request.getParameter("oper");

		try {
			if (oper.equals("add")) {

				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));

				int 전임교원수 = defaultQuery.전임교원수(년도, 학과명);
				int 강의동영상B = defaultClass.nullCheck(request.getParameter("강의동영상B")) ? 0
						: Integer.parseInt(request.getParameter("강의동영상B"));
				int 이러닝강의C = defaultClass.nullCheck(request.getParameter("이러닝강의C")) ? 0
						: Integer.parseInt(request.getParameter("이러닝강의C"));
				int 강의자료D = defaultClass.nullCheck(request.getParameter("강의자료D")) ? 0
						: Integer.parseInt(request.getParameter("강의자료D"));
				int 강의동영상E = defaultClass.nullCheck(request.getParameter("강의동영상E")) ? 0
						: Integer.parseInt(request.getParameter("강의동영상E"));
				int 이러닝강의F = defaultClass.nullCheck(request.getParameter("이러닝강의F")) ? 0
						: Integer.parseInt(request.getParameter("이러닝강의F"));
				int 강의자료G = defaultClass.nullCheck(request.getParameter("강의자료G")) ? 0
						: Integer.parseInt(request.getParameter("강의자료G"));

				String 입력부서 = request.getParameter("입력부서");

				float 강의공개실적계산 = 강의공개실적계산(전임교원수, 강의동영상B, 이러닝강의C, 강의자료D, 강의동영상E, 이러닝강의F, 강의자료G);

				강의공개실적 강의공개실적 = new 강의공개실적();

				강의공개실적.set년도(년도);
				강의공개실적.set학과명(학과명);
				강의공개실적.set강의동영상B(강의동영상B);
				강의공개실적.set이러닝강의C(이러닝강의C);
				강의공개실적.set강의자료D(강의자료D);
				강의공개실적.set강의동영상E(강의동영상E);
				강의공개실적.set이러닝강의F(이러닝강의F);
				강의공개실적.set강의자료G(강의자료G);
				강의공개실적.set입력부서(입력부서);
				강의공개실적.set강의공개실적계산(강의공개실적계산);

				if (!dao.insert(강의공개실적)) {
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

				int 전임교원수 = defaultQuery.전임교원수(년도, 학과명);

				int 강의동영상B = defaultClass.nullCheck(request.getParameter("강의동영상B")) ? 0
						: Integer.parseInt(request.getParameter("강의동영상B"));
				int 이러닝강의C = defaultClass.nullCheck(request.getParameter("이러닝강의C")) ? 0
						: Integer.parseInt(request.getParameter("이러닝강의C"));
				int 강의자료D = defaultClass.nullCheck(request.getParameter("강의자료D")) ? 0
						: Integer.parseInt(request.getParameter("강의자료D"));
				int 강의동영상E = defaultClass.nullCheck(request.getParameter("강의동영상E")) ? 0
						: Integer.parseInt(request.getParameter("강의동영상E"));
				int 이러닝강의F = defaultClass.nullCheck(request.getParameter("이러닝강의F")) ? 0
						: Integer.parseInt(request.getParameter("이러닝강의F"));
				int 강의자료G = defaultClass.nullCheck(request.getParameter("강의자료G")) ? 0
						: Integer.parseInt(request.getParameter("강의자료G"));
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

					강의공개실적 강의공개실적 = new 강의공개실적();

					float 강의공개실적계산 = 강의공개실적계산(전임교원수, 강의동영상B, 이러닝강의C, 강의자료D, 강의동영상E, 이러닝강의F, 강의자료G);

					강의공개실적.set년도(년도);
					강의공개실적.set학과명(학과명);
					강의공개실적.set강의동영상B(강의동영상B);
					강의공개실적.set이러닝강의C(이러닝강의C);
					강의공개실적.set강의자료D(강의자료D);
					강의공개실적.set강의동영상E(강의동영상E);
					강의공개실적.set이러닝강의F(이러닝강의F);
					강의공개실적.set강의자료G(강의자료G);
					강의공개실적.set강의공개실적계산(강의공개실적계산);
					강의공개실적.set연번(연번);

					if (!dao.update(강의공개실적)) {
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
				System.out.println("잘못된 접근입니다.");
			}
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

	public float 강의공개실적계산(int 전임교원수A, int 강의동영상B, int 이러닝강의C, int 강의자료D, int 강의동영상E, int 이러닝강의F, int 강의자료G) {
		if ((강의동영상B + 강의동영상E) * 2 + (이러닝강의C + 이러닝강의F) * 2 + (강의자료D + 강의자료G) < 0 || 전임교원수A < 0)
			return 0;
		else {
			float result = (float) ((((강의동영상B + 강의동영상E) * 2 + (이러닝강의C + 이러닝강의F) * 2 + (강의자료D + 강의자료G))
					/ (전임교원수A * 1.0)));
			float 강의공개실적 = (float)result;
			System.out.println("전임교원수 :" + 전임교원수A + " 강의동영상B :" + 강의동영상B + " 이러닝강의C :" + 이러닝강의C + " 강의자료D :" + 강의자료D
					+ " 강의동영상E :" + 강의동영상E + " 이런닝강의F :" + 이러닝강의F + " 강의자료G :" + 강의자료G + " 강의공개실적 :" + 강의공개실적);
			return 강의공개실적;
		}
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();

		String column = "강의공개실적";
		String table = "강의공개실적";

		float 평균 = (float) defaultQuery.avg(년도, column, table);
		float 표준편차 = (float) defaultQuery.std(년도, column, table);

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			float 강의공개실적계산 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(강의공개실적계산, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}