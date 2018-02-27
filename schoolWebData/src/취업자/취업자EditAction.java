package 취업자;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import 취업현황.취업현황Dao;
import 취업현황.취업현황EditAction;

@WebServlet("/취업자EditAction")

public class 취업자EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	취업현황Dao 현황dao = new 취업현황Dao();
	취업현황EditAction ea = new 취업현황EditAction();
	취업자Dao dao = new 취업자Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			String 학과명 = request.getParameter("학과명");
			int 년도 = Integer.parseInt(request.getParameter("년도"));
			int 건강보험DB연계취업자 = defaultClass.nullCheck(request.getParameter("건강보험DB연계취업자")) ? 0
					: Integer.parseInt(request.getParameter("건강보험DB연계취업자"));
			int 해외취업자 = defaultClass.nullCheck(request.getParameter("해외취업자")) ? 0
					: Integer.parseInt(request.getParameter("해외취업자"));
			int 영농업취업자 = defaultClass.nullCheck(request.getParameter("영농업취업자")) ? 0
					: Integer.parseInt(request.getParameter("영농업취업자"));
			int 취업인정자 = defaultClass.nullCheck(request.getParameter("취업인정자")) ? 0
					: Integer.parseInt(request.getParameter("취업인정자"));
			int 개인창작활동조사서 = defaultClass.nullCheck(request.getParameter("개인창작활동조사서")) ? 0
					: Integer.parseInt(request.getParameter("개인창작활동조사서"));
			int 일인창업자 = defaultClass.nullCheck(request.getParameter("일인창업자")) ? 0
					: Integer.parseInt(request.getParameter("일인창업자"));
			int 프리랜서 = defaultClass.nullCheck(request.getParameter("프리랜서")) ? 0
					: Integer.parseInt(request.getParameter("프리랜서"));
			int 계 = 건강보험DB연계취업자 + 해외취업자 + 영농업취업자+개인창작활동조사서+일인창업자+프리랜서;
			String 입력부서 = request.getParameter("입력부서");

			취업자 취업자 = new 취업자();

			취업자.set년도(년도);
			취업자.set학과명(학과명);
			취업자.set계(계);
			취업자.set해외취업자(해외취업자);
			취업자.set건강보험DB연계취업자(건강보험DB연계취업자);
			취업자.set영농업취업자(영농업취업자);
			취업자.set취업인정자(취업인정자);
			취업자.set입력부서(입력부서);
			취업자.set개인창작활동조사서(개인창작활동조사서);
			취업자.set일인창업자(일인창업자);
			취업자.set프리랜서(프리랜서);

			PrintWriter out = response.getWriter();

			if (dao.insert(취업자) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}
		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();

			String 학과명 = request.getParameter("학과명");
			int 년도 = Integer.parseInt(request.getParameter("년도"));

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

				int 건강보험DB연계취업자 = defaultClass.nullCheck(request.getParameter("건강보험DB연계취업자")) ? 0
						: Integer.parseInt(request.getParameter("건강보험DB연계취업자"));
				int 해외취업자 = defaultClass.nullCheck(request.getParameter("해외취업자")) ? 0
						: Integer.parseInt(request.getParameter("해외취업자"));
				int 영농업취업자 = defaultClass.nullCheck(request.getParameter("영농업취업자")) ? 0
						: Integer.parseInt(request.getParameter("영농업취업자"));
				int 취업인정자 = defaultClass.nullCheck(request.getParameter("취업인정자")) ? 0
						: Integer.parseInt(request.getParameter("취업인정자"));
				int 연번 = Integer.parseInt(request.getParameter("연번"));
				int 개인창작활동조사서 = defaultClass.nullCheck(request.getParameter("개인창작활동조사서")) ? 0
						: Integer.parseInt(request.getParameter("개인창작활동조사서"));
				int 일인창업자 = defaultClass.nullCheck(request.getParameter("일인창업자")) ? 0
						: Integer.parseInt(request.getParameter("일인창업자"));
				int 프리랜서 = defaultClass.nullCheck(request.getParameter("프리랜서")) ? 0
						: Integer.parseInt(request.getParameter("프리랜서"));
				int 계 = 건강보험DB연계취업자 + 해외취업자 + 영농업취업자+개인창작활동조사서+일인창업자+프리랜서;
				
				취업자 취업자 = new 취업자();
				취업자.set년도(년도);
				취업자.set학과명(학과명);
				취업자.set계(계);
				취업자.set해외취업자(해외취업자);
				취업자.set건강보험DB연계취업자(건강보험DB연계취업자);
				취업자.set영농업취업자(영농업취업자);
				취업자.set취업인정자(취업인정자);
				취업자.set연번(연번);
				취업자.set개인창작활동조사서(개인창작활동조사서);
				취업자.set일인창업자(일인창업자);
				취업자.set프리랜서(프리랜서);

				
				if (dao.update(취업자) == false) {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}