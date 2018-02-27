package 캡스톤디자인;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 캡스톤디자인.캡스톤디자인;
import 캡스톤디자인.캡스톤디자인Dao;
import defaultMethod.*;

@WebServlet("/캡스톤디자인EditAction")
public class 캡스톤디자인EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		캡스톤디자인Dao dao = new 캡스톤디자인Dao();

		try {
			if (oper.equals("add")) {
				int 년도 = Integer.parseInt(request.getParameter("년도"));
				String 학과명 = request.getParameter("학과명");
				int 이수1학기 = defaultClass.nullCheck(request.getParameter("이수1학기")) ? 0
						: Integer.parseInt(request.getParameter("이수1학기"));
				int 이수2학기 = defaultClass.nullCheck(request.getParameter("이수2학기")) ? 0
						: Integer.parseInt(request.getParameter("이수2학기"));
				int 대상1학기 = defaultClass.nullCheck(request.getParameter("대상1학기")) ? 0
						: Integer.parseInt(request.getParameter("대상1학기"));
				int 대상2학기 = defaultClass.nullCheck(request.getParameter("대상2학기")) ? 0
						: Integer.parseInt(request.getParameter("대상2학기"));
				String 입력부서 = request.getParameter("입력부서");

				int 이수합계 = 이수1학기 + 이수2학기;
				int 대상합계 = 대상1학기 + 대상2학기;

				float 이수학생비율 = 이수학생비율(이수합계, 대상합계);

				캡스톤디자인 캡스톤디자인 = new 캡스톤디자인();

				캡스톤디자인.set년도(년도);
				캡스톤디자인.set학과명(학과명);
				캡스톤디자인.set이수1학기(이수1학기);
				캡스톤디자인.set이수2학기(이수2학기);
				캡스톤디자인.set대상1학기(대상1학기);
				캡스톤디자인.set대상2학기(대상2학기);
				캡스톤디자인.set이수학생비율(이수학생비율);
				캡스톤디자인.set입력부서(입력부서);

				if (!dao.insert(캡스톤디자인)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();
				}

			} else if (oper.equals("cal")||oper.equals("editCal")) {
				
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

					int 이수1학기 = defaultClass.nullCheck(request.getParameter("이수1학기")) ? 0
							: Integer.parseInt(request.getParameter("이수1학기"));
					int 이수2학기 = defaultClass.nullCheck(request.getParameter("이수2학기")) ? 0
							: Integer.parseInt(request.getParameter("이수2학기"));
					int 대상1학기 = defaultClass.nullCheck(request.getParameter("대상1학기")) ? 0
							: Integer.parseInt(request.getParameter("대상1학기"));
					int 대상2학기 = defaultClass.nullCheck(request.getParameter("대상2학기")) ? 0
							: Integer.parseInt(request.getParameter("대상2학기"));

					int 연번 = Integer.parseInt(request.getParameter("연번"));

					int 이수합계 = 이수1학기 + 이수2학기;
					int 대상합계 = 대상1학기 + 대상2학기;

					float 이수학생비율 = 이수학생비율(이수합계, 대상합계);

					캡스톤디자인 캡스톤디자인 = new 캡스톤디자인();

					캡스톤디자인.set년도(년도);
					캡스톤디자인.set학과명(학과명);
					캡스톤디자인.set이수1학기(이수1학기);
					캡스톤디자인.set이수2학기(이수2학기);
					캡스톤디자인.set대상1학기(대상1학기);
					캡스톤디자인.set대상2학기(대상2학기);
					캡스톤디자인.set이수학생비율(이수학생비율);
					캡스톤디자인.set연번(연번);

					if (!dao.update(캡스톤디자인)) {
						out.write("fail");
						out.flush();
						out.close();
					}
				}
			}else if (oper.equals("del")) {

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

	public float 이수학생비율(int 이수합계, int 대상합계) {
		System.out.println("이수합계"+이수합계+"대사합계"+대상합계);
		float result;
		if( 이수합계==0 && 대상합계==0){
			result =0;
		}else
			result = (float) (이수합계 / (대상합계 * 1.0) * 100);
		float 이수학생비율= result;
		System.out.println("이수 합계 :"+이수합계+" 대상합계 :"+대상합계+" 이수학생비율 계산 결과 :"+result);
		return result;
	}

	public void 비율재계산(){
		년도  년도class=new 년도();
		int 년도=년도class.년도();
		
		String column = "이수학생비율";
		String table = "캡스톤디자인";

		float 평균 = (float) defaultQuery.avg(년도, column, table);
		float 표준편차 = (float)defaultQuery.std(년도, column, table);

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			float 이수학생비율 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(이수학생비율, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}