package 기술이전;

import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 기술이전.기술이전;
import 기술이전.기술이전Dao;

import 특허등록.특허등록EditAction;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;

@WebServlet("/기술이전EditAction")
public class 기술이전EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		기술이전Dao dao = new 기술이전Dao();
		특허등록EditAction ea = new 특허등록EditAction();

		int 수정년도 = 0;
		String 수정학과명 = null;

		if (oper.equals("add")) {

			수정년도 = Integer.parseInt(request.getParameter("년도"));
			수정학과명 = request.getParameter("학과명");

			String 대표발명자 = request.getParameter("대표발명자");
			String 지식재산권 = request.getParameter("지식재산권");
			String 입력부서 = request.getParameter("입력부서");
			float 정액기술료 = defaultClass.nullCheck(request.getParameter("정액기술료")) ? 0
					: Float.parseFloat(request.getParameter("정액기술료"));

			기술이전 기술이전 = new 기술이전();

			기술이전.set년도(수정년도);
			기술이전.set학과명(수정학과명);
			기술이전.set대표발명자(대표발명자);
			기술이전.set지식재산권(지식재산권);
			기술이전.set정액기술료(정액기술료);
			기술이전.set입력부서(입력부서);

			if (!dao.insert(기술이전)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		} else if (oper.equals("cal") || oper.equals("editCal")) {

			ea.비율재계산();

		} else if (oper.equals("edit")) {
			수정년도 = Integer.parseInt(request.getParameter("년도"));
			수정학과명 = request.getParameter("학과명");

			PrintWriter out = response.getWriter();

			if (defaultQuery.학과명체크(수정학과명).equals("0")) {

				out.write("failMajor");
				out.flush();
				out.close();

			} else if (defaultQuery.년도체크(수정년도).equals("0")) {

				out.write("failYear");
				out.flush();
				out.close();
			} else {

				String 대표발명자 = request.getParameter("대표발명자");
				String 지식재산권 = request.getParameter("지식재산권");
				float 정액기술료 = defaultClass.nullCheck(request.getParameter("정액기술료")) ? 0
						: Float.parseFloat(request.getParameter("정액기술료"));
				int 연번 = Integer.parseInt(request.getParameter("연번"));

				기술이전 기술이전 = new 기술이전();

				기술이전.set년도(수정년도);
				기술이전.set학과명(수정학과명);
				기술이전.set대표발명자(대표발명자);
				기술이전.set지식재산권(지식재산권);
				기술이전.set정액기술료(정액기술료);
				기술이전.set연번(연번);

				if (!dao.update(기술이전)) {
					out.write("fail");
					out.flush();
					out.close();
				}
			}
		} else if (oper.equals("del")) {
			int 연번 = Integer.parseInt(request.getParameter("연번"));
			dao.delete(연번);

			ea.비율재계산();
		} else if (oper.equals("delAll")) {
			String 입력부서 = request.getParameter("입력부서");
			dao.dellAll(입력부서);

			ea.비율재계산();
		} else {
			System.out.println("잘못된 접근입니다.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
