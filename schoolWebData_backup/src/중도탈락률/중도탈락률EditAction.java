package 중도탈락률;

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
import 중도탈락률.중도탈락률Dao;

@WebServlet("/중도탈락률EditAction")

public class 중도탈락률EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	중도탈락률Dao dao = new 중도탈락률Dao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			String 학과명 = request.getParameter("학과명");
			int 년도 = Integer.parseInt(request.getParameter("년도"));
			int 재적학생수 = defaultClass.nullCheck(request.getParameter("재적학생수")) ? 0
					: Integer.parseInt(request.getParameter("재적학생수"));
			int 타학과전과자 = defaultClass.nullCheck(request.getParameter("타학과전과자")) ? 0
					: Integer.parseInt(request.getParameter("타학과전과자"));
			int 미등록 = defaultClass.nullCheck(request.getParameter("미등록")) ? 0
					: Integer.parseInt(request.getParameter("미등록"));
			int 미복학 = defaultClass.nullCheck(request.getParameter("미복학")) ? 0
					: Integer.parseInt(request.getParameter("미복학"));
			int 자퇴 = defaultClass.nullCheck(request.getParameter("자퇴")) ? 0
					: Integer.parseInt(request.getParameter("자퇴"));
			int 학사경고 = defaultClass.nullCheck(request.getParameter("학사경고")) ? 0
					: Integer.parseInt(request.getParameter("학사경고"));
			int 기타 = defaultClass.nullCheck(request.getParameter("기타")) ? 0
					: Integer.parseInt(request.getParameter("기타"));
			int 계 = 계(미등록, 미복학, 자퇴, 학사경고, 기타);
			String 입력부서 = request.getParameter("입력부서");
			float 중도탈락률계산 = 중도탈락률(재적학생수, 계, 타학과전과자);

			중도탈락률 중도탈락률bean = new 중도탈락률();

			중도탈락률bean.set년도(년도);
			중도탈락률bean.set학과명(학과명);
			중도탈락률bean.set계(계);
			중도탈락률bean.set재적학생수(재적학생수);
			중도탈락률bean.set미등록(미등록);
			중도탈락률bean.set미복학(미복학);
			중도탈락률bean.set자퇴(자퇴);
			중도탈락률bean.set학사경고(학사경고);
			중도탈락률bean.set타학과전과자(타학과전과자);
			중도탈락률bean.set기타(기타);
			중도탈락률bean.set중도탈락률(중도탈락률계산);
			중도탈락률bean.set입력부서(입력부서);

			PrintWriter out = response.getWriter();

			if (!dao.insert(중도탈락률bean)) {
				out.write("fail");
				out.flush();
				out.close();
			}

		} else if (oper.equals("cal") || oper.equals("editCal")) {

			비율재계산();

		} else if (oper.equals("edit")) {
			String 학과명 = request.getParameter("학과명");
			int 년도 = Integer.parseInt(request.getParameter("년도"));
			int 재적학생수 = defaultClass.nullCheck(request.getParameter("재적학생수")) ? 0
					: Integer.parseInt(request.getParameter("재적학생수"));
			int 타학과전과자 = defaultClass.nullCheck(request.getParameter("타학과전과자")) ? 0
					: Integer.parseInt(request.getParameter("타학과전과자"));
			int 미등록 = defaultClass.nullCheck(request.getParameter("미등록")) ? 0
					: Integer.parseInt(request.getParameter("미등록"));
			int 미복학 = defaultClass.nullCheck(request.getParameter("미복학")) ? 0
					: Integer.parseInt(request.getParameter("미복학"));
			int 자퇴 = defaultClass.nullCheck(request.getParameter("자퇴")) ? 0
					: Integer.parseInt(request.getParameter("자퇴"));
			int 학사경고 = defaultClass.nullCheck(request.getParameter("학사경고")) ? 0
					: Integer.parseInt(request.getParameter("학사경고"));
			int 기타 = defaultClass.nullCheck(request.getParameter("기타")) ? 0
					: Integer.parseInt(request.getParameter("기타"));
			int 연번 = Integer.parseInt(request.getParameter("연번"));
			int 계 = 계(미등록, 미복학, 자퇴, 학사경고, 기타);
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

				float 중도탈락률계산 = 중도탈락률(재적학생수, 계, 타학과전과자);

				중도탈락률 중도탈락률bean = new 중도탈락률();

				중도탈락률bean.set연번(연번);
				중도탈락률bean.set년도(년도);
				중도탈락률bean.set학과명(학과명);
				중도탈락률bean.set계(계);
				중도탈락률bean.set재적학생수(재적학생수);
				중도탈락률bean.set타학과전과자(타학과전과자);
				중도탈락률bean.set미등록(미등록);
				중도탈락률bean.set미복학(미복학);
				중도탈락률bean.set자퇴(자퇴);
				중도탈락률bean.set학사경고(학사경고);
				중도탈락률bean.set기타(기타);
				중도탈락률bean.set중도탈락률(중도탈락률계산);

				if (!dao.update(중도탈락률bean)) {
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
	}

	public float 중도탈락률(int 재적학생수, int 계, int 타학과전과자) {
		float 중도탈락률 = (float) (계 / ((재적학생수 + 타학과전과자) * 1.0) * 100);
		System.out.println("재적학생수 :" + 재적학생수 + " 계 :" + 계 + " 타학과전과자 :" + 타학과전과자 + " 중도탈락률 :" + 중도탈락률);
		return 중도탈락률;
	}

	public int 계(int 미등록, int 미복학, int 자퇴, int 학사경고, int 기타) {
		return 미등록 + 미복학 + 자퇴 + 학사경고 + 기타;
	}

	public void 비율재계산() {
		년도 년도class = new 년도();
		int 년도 = 년도class.년도();
		
		String column = "중도탈락률";
		String table = "중도탈락률";

		float 평균 = defaultQuery.avg(년도, column, table);
		float 표준편차 =defaultQuery.std(년도, column, table);

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);
		for (String 학과명 : 학과목록) {
			float 중도탈락률 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.중도탈락률T점수(중도탈락률, 평균, 표준편차);
			
			defaultQuery.updateT(T점수, 년도, 학과명, table);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}