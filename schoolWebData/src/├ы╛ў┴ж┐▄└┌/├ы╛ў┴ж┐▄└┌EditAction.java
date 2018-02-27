package 취업제외자;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultQuery;
import 취업현황.취업현황Dao;
import 취업현황.취업현황EditAction;

@WebServlet("/취업제외자EditAction")

public class 취업제외자EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	취업현황Dao 현황dao = new 취업현황Dao();
	취업현황EditAction ea = new 취업현황EditAction();
	취업제외자Dao dao = new 취업제외자Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			String 입력부서 = request.getParameter("입력부서");
			String 학과명 = request.getParameter("학과명");
			int 년도 = Integer.parseInt(request.getParameter("년도"));
			int 진학자 = nullCheck(request.getParameter("진학자")) ? 0 : Integer.parseInt(request.getParameter("진학자"));
			int 입대자 = nullCheck(request.getParameter("입대자")) ? 0 : Integer.parseInt(request.getParameter("입대자"));
			int 취업불가능자 = nullCheck(request.getParameter("취업불가능자")) ? 0
					: Integer.parseInt(request.getParameter("취업불가능자"));
			int 외국인유학생 = nullCheck(request.getParameter("외국인유학생")) ? 0
					: Integer.parseInt(request.getParameter("외국인유학생"));
			int 건강보험직장가입제외대상 = nullCheck(request.getParameter("건강보험직장가입제외대상")) ? 0
					: Integer.parseInt(request.getParameter("건강보험직장가입제외대상"));
			int 입학당시기취업자 = nullCheck(request.getParameter("입학당시기취업자")) ? 0
					: Integer.parseInt(request.getParameter("입학당시기취업자"));

			int 계 = 계(진학자, 입대자, 취업불가능자, 외국인유학생, 건강보험직장가입제외대상, 입학당시기취업자);
			취업제외자 취업제외자 = new 취업제외자();

			취업제외자.set년도(년도);
			취업제외자.set학과명(학과명);
			취업제외자.set계(계);
			취업제외자.set입대자(입대자);
			취업제외자.set진학자(진학자);
			취업제외자.set취업불가능자(취업불가능자);
			취업제외자.set외국인유학생(외국인유학생);
			취업제외자.set건강보험직장가입제외대상(건강보험직장가입제외대상);
			취업제외자.set입력부서(입력부서);
			취업제외자.set입학당시기취업자(입학당시기취업자);

			PrintWriter out = response.getWriter();

			if (dao.insert(취업제외자) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}
		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();
			int 년도 = Integer.parseInt(request.getParameter("년도"));
			String 학과명 = request.getParameter("학과명");

			String 학과명체크 = defaultQuery.학과명체크(학과명);
			String 년도체크 = defaultQuery.년도체크(년도);

			if (학과명체크.equals("0")) {
				out.write("failMajor");
				out.flush();
				out.close();
			} else if (년도체크.equals("0")) {
				out.write("failYear");
				out.flush();
				out.close();
			} else {
				int 연번 = Integer.parseInt(request.getParameter("연번"));
				int 진학자 = nullCheck(request.getParameter("진학자")) ? 0 : Integer.parseInt(request.getParameter("진학자"));
				int 입대자 = nullCheck(request.getParameter("입대자")) ? 0 : Integer.parseInt(request.getParameter("입대자"));
				int 취업불가능자 = nullCheck(request.getParameter("취업불가능자")) ? 0
						: Integer.parseInt(request.getParameter("취업불가능자"));
				int 외국인유학생 = nullCheck(request.getParameter("외국인유학생")) ? 0
						: Integer.parseInt(request.getParameter("외국인유학생"));
				int 건강보험직장가입제외대상 = nullCheck(request.getParameter("건강보험직장가입제외대상")) ? 0
						: Integer.parseInt(request.getParameter("건강보험직장가입제외대상"));
				int 입학당시기취업자 = nullCheck(request.getParameter("입학당시기취업자")) ? 0
						: Integer.parseInt(request.getParameter("입학당시기취업자"));
				int 계 = 계(진학자, 입대자, 취업불가능자, 외국인유학생, 건강보험직장가입제외대상, 입학당시기취업자);

				취업제외자 취업제외자 = new 취업제외자();

				취업제외자.set연번(연번);
				취업제외자.set학과명(학과명);
				취업제외자.set년도(년도);
				취업제외자.set계(계);
				취업제외자.set진학자(진학자);
				취업제외자.set취업불가능자(취업불가능자);
				취업제외자.set외국인유학생(외국인유학생);
				취업제외자.set건강보험직장가입제외대상(건강보험직장가입제외대상);
				취업제외자.set입학당시기취업자(입학당시기취업자);
				취업제외자.set입대자(입대자);

				if (dao.update(취업제외자) == false) {
					out.write("fail");
					out.flush();
					out.close();
				}
			}
		} else if (oper.equals("cal") || oper.equals("editCal")) {

			ea.비율재계산();
			
		} else if (oper.equals("del")) {
			int 연번 = Integer.parseInt(request.getParameter("연번"));
			dao.delete(연번);

			ea.비율재계산();
			
		} else if (oper.equals("delAll")) {
			String 입력부서 = request.getParameter("입력부서");
			dao.dellAll(입력부서);
			ea.비율재계산();
		} else {
			System.out.println("잘못된 접근 입니다.");
		}
	}

	public boolean nullCheck(String value) {
		if (null == value || value.isEmpty()) {
			return true;
		}
		return false;
	}

	public int 계(int 진학자, int 입대자, int 취업불가능자, int 외국인유학생, int 건강보험직장가입제외대상, int 입학당시기취업자) {
		return 진학자 + 입대자 + 취업불가능자 + 외국인유학생 + 건강보험직장가입제외대상 + 입학당시기취업자;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
