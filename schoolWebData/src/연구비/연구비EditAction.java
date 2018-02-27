package 연구비;

import java.io.IOException;
import defaultMethod.년도;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import 연구비.연구비Dao;

@WebServlet("/연구비EditAction")

public class 연구비EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");


		연구비Dao dao = new 연구비Dao();

		try {
			if (oper.equals("add")) {
				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));

				int 전임교원수 = defaultQuery.전임교원수(년도, 학과명);
				int 중앙정부 = defaultClass.nullCheck(request.getParameter("중앙정부")) ? 0
						: Integer.parseInt(request.getParameter("중앙정부"));
				int 지자체 = defaultClass.nullCheck(request.getParameter("지자체")) ? 0
						: Integer.parseInt(request.getParameter("지자체"));
				int 민간 = defaultClass.nullCheck(request.getParameter("민간")) ? 0
						: Integer.parseInt(request.getParameter("민간"));
				int 외국 = defaultClass.nullCheck(request.getParameter("외국")) ? 0
						: Integer.parseInt(request.getParameter("외국"));
				int 계 = 계(중앙정부, 지자체, 민간, 외국);
				String 입력부서 = request.getParameter("입력부서");

				float 연구비 = 연구비(전임교원수, 계);

				연구비 연구비bean = new 연구비();

				연구비bean.set년도(년도);
				연구비bean.set학과명(학과명);
				연구비bean.set지자체(지자체);
				연구비bean.set중앙정부(중앙정부);
				연구비bean.set민간(민간);
				연구비bean.set외국(외국);
				연구비bean.set연구비(연구비);
				연구비bean.set계(계);
				연구비bean.set입력부서(입력부서);

				if (!dao.insert(연구비bean)) {
					PrintWriter out = response.getWriter();
					out.write("fail");
					out.close();
					out.flush();

				}

			} else if (oper.equals("cal") || oper.equals("editCal")) {
				
				비율재계산();
				
			} else if (oper.equals("edit")) {
				PrintWriter out = response.getWriter();
				int 연번 = Integer.parseInt(request.getParameter("연번"));
				String 학과명 = request.getParameter("학과명");
				int 년도 = Integer.parseInt(request.getParameter("년도"));
				int 전임교원수 = defaultQuery.전임교원수(년도, 학과명);
				int 중앙정부 = defaultClass.nullCheck(request.getParameter("중앙정부")) ? 0
						: Integer.parseInt(request.getParameter("중앙정부"));
				int 지자체 = defaultClass.nullCheck(request.getParameter("지자체")) ? 0
						: Integer.parseInt(request.getParameter("지자체"));
				int 민간 = defaultClass.nullCheck(request.getParameter("민간")) ? 0
						: Integer.parseInt(request.getParameter("민간"));
				int 외국 = defaultClass.nullCheck(request.getParameter("외국")) ? 0
						: Integer.parseInt(request.getParameter("외국"));
				int 계 = 계(중앙정부, 지자체, 민간, 외국);

				if (defaultQuery.학과명체크(학과명).equals("0")) {
					out.write("failMajor");
					out.flush();
					out.close();
				} else if (defaultQuery.년도체크(년도).equals("0")) {
					out.write("failYear");
					out.flush();
					out.close();
				} else {

					float 연구비 = 연구비(전임교원수, 계);

					연구비 연구비bean = new 연구비();

					연구비bean.set연번(연번);
					연구비bean.set년도(년도);
					연구비bean.set학과명(학과명);
					연구비bean.set지자체(지자체);
					연구비bean.set중앙정부(중앙정부);
					연구비bean.set민간(민간);
					연구비bean.set외국(외국);
					연구비bean.set연구비(연구비);
					연구비bean.set계(계);

					if (!dao.update(연구비bean)) {
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

	public float 연구비(int 전임교원수, int 계) {
		float 연구비=(float)(계 / (전임교원수 * 1.0));
		System.out.println("전임교원수 :"+전임교원수+" 합계 :"+계);
		return 연구비;
	}

	public int 계(int 중앙정부, int 지자체, int 민간, int 외국) {
		return 중앙정부 + 지자체 + 민간 + 외국;
	}
	
	public void 비율재계산(){
		년도 년도class=new 년도();
		int 년도=년도class.년도();
		
		String column = "연구비";
		String table = "연구비";

		
		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			String 학문계열 = defaultQuery.학문계열(년도, 학과명);
			
			float 평균 = defaultQuery.학문계열avg(년도, column, table, 학문계열);
			float 표준편차 =defaultQuery.학문계열std(년도, column, table, 학문계열);
			float 연구비 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(연구비, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}