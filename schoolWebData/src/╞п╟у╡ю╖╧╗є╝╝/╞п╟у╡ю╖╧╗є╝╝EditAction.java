package 특허등록상세;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import 특허등록상세.특허등록상세;
import 특허등록상세.특허등록상세Dao;
import 특허등록.특허등록EditAction;

import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;

@WebServlet("/특허등록상세EditAction")
public class 특허등록상세EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		특허등록상세Dao dao = new 특허등록상세Dao();
		특허등록EditAction ea=new 특허등록EditAction();
		
		if (oper.equals("add")) {

			int 년도 = Integer.parseInt(request.getParameter("년도"));
			String 학과명 = request.getParameter("학과명");

			String 대표발명자=request.getParameter("대표발명자");
			String 지식재산권=request.getParameter("지식재산권");
			String 입력부서=request.getParameter("입력부서");
			float 정액기술료=defaultClass.nullCheck(request.getParameter("정액기술료"))?0:Float.parseFloat(request.getParameter("정액기술료"));
			
			
			특허등록상세 특허등록상세 = new 특허등록상세();

			특허등록상세.set년도(년도);
			특허등록상세.set학과명(학과명);
			특허등록상세.set대표발명자(대표발명자);
			특허등록상세.set지식재산권(지식재산권);
			특허등록상세.set정액기술료(정액기술료);
			특허등록상세.set입력부서(입력부서);
			
			if (!dao.insert(특허등록상세)) {
				PrintWriter out = response.getWriter();
				out.write("fail");
				out.close();
				out.flush();
			}

		}else if (oper.equals("edit")) {
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

				String 대표발명자=request.getParameter("대표발명자");
				String 지식재산권=request.getParameter("지식재산권");
				float 정액기술료=defaultClass.nullCheck(request.getParameter("정액기술료"))?0:Float.parseFloat(request.getParameter("정액기술료"));
				
				int 연번 = Integer.parseInt(request.getParameter("연번"));

				특허등록상세 특허등록상세 = new 특허등록상세();

				특허등록상세.set년도(년도);
				특허등록상세.set학과명(학과명);
				특허등록상세.set대표발명자(대표발명자);
				특허등록상세.set지식재산권(지식재산권);
				특허등록상세.set정액기술료(정액기술료);
				특허등록상세.set연번(연번);
				
				if(!dao.update(특허등록상세)){
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
