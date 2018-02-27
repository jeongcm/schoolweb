package 중도탈락률외부;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import defaultMethod.defaultClass;
import defaultMethod.defaultQuery;
import 중도탈락률외부.중도탈락률외부Dao;

@WebServlet("/중도탈락률외부EditAction")

public class 중도탈락률외부EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	중도탈락률외부Dao dao = new 중도탈락률외부Dao();
	String 수정대학명 = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String oper = request.getParameter("oper");

		if (oper.equals("add")) {
			String 학과명 = request.getParameter("학과명");
			String 입력부서 = request.getParameter("입력부서");
			수정대학명 = request.getParameter("대학명");
			String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
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

			float 중도탈락률외부값 = 중도탈락률외부(재적학생수, 계);

			중도탈락률외부 중도탈락률외부 = new 중도탈락률외부();

			중도탈락률외부.set학과명(학과명);
			중도탈락률외부.set대학명(수정대학명);
			중도탈락률외부.set계(계);
			중도탈락률외부.set재적학생수(재적학생수);
			중도탈락률외부.set미등록(미등록);
			중도탈락률외부.set미복학(미복학);
			중도탈락률외부.set자퇴(자퇴);
			중도탈락률외부.set타학과전과자(타학과전과자);
			중도탈락률외부.set학사경고(학사경고);
			중도탈락률외부.set입력부서(입력부서);
			중도탈락률외부.set기타(기타);
			중도탈락률외부.set비고(비고);
			중도탈락률외부.set중도탈락률(중도탈락률외부값);

			PrintWriter out = response.getWriter();

			if (dao.insert(중도탈락률외부) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}

		} else if (oper.equals("cal") || (oper.equals("editCal"))) {
			String column = "중도탈락률";
			String table = "중도탈락률외부";

			float 평균 = defaultQuery.외부avg(column, table);
			float 표준편차 = defaultQuery.외부std(column, table);

			ArrayList<String> 대학목록 = defaultQuery.외부대학목록(table);
			for (String 대학명 : 대학목록) {
				float 중도탈락률 = defaultQuery.외부비율(대학명, column, table);
				float T점수 = defaultClass.중도탈락률T점수(중도탈락률, 평균, 표준편차);
				defaultQuery.외부updateT(T점수, 대학명, table);
			}
		} else if (oper.equals("edit")) {
			PrintWriter out = response.getWriter();

			String 대학명 = request.getParameter("대학명");
			String 학과명 = request.getParameter("학과명");
			String 비고 = defaultClass.nullCheck(request.getParameter("비고")) ? null : request.getParameter("비고");
			int 연번 = Integer.parseInt(request.getParameter("연번"));
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
			float 중도탈락률외부값 = 중도탈락률외부(재적학생수, 계);

			중도탈락률외부 중도탈락률외부 = new 중도탈락률외부();

			중도탈락률외부.set대학명(대학명);
			중도탈락률외부.set학과명(학과명);
			중도탈락률외부.set연번(연번);
			중도탈락률외부.set계(계);
			중도탈락률외부.set비고(비고);
			중도탈락률외부.set재적학생수(재적학생수);
			중도탈락률외부.set미등록(미등록);
			중도탈락률외부.set타학과전과자(타학과전과자);
			중도탈락률외부.set미복학(미복학);
			중도탈락률외부.set자퇴(자퇴);
			중도탈락률외부.set학사경고(학사경고);
			중도탈락률외부.set기타(기타);
			중도탈락률외부.set중도탈락률(중도탈락률외부값);

			if (dao.update(중도탈락률외부) == false) {
				out.write("fail");
				out.flush();
				out.close();
			}

		} else if (oper.equals("del")) {
			int 연번 = Integer.parseInt(request.getParameter("연번"));
			dao.delete(연번);

			String column = "중도탈락률";
			String table = "중도탈락률외부";

			float 평균 = defaultQuery.외부avg(column, table);
			float 표준편차 = defaultQuery.외부std(column, table);

			ArrayList<String> 대학목록 = defaultQuery.외부대학목록(table);
			for (String 대학명 : 대학목록) {
				float 중도탈락률 = defaultQuery.외부비율(대학명, column, table);
				float T점수 = defaultClass.중도탈락률T점수(중도탈락률, 평균, 표준편차);
				defaultQuery.외부updateT(T점수, 대학명, table);
			}

		} else if (oper.equals("delAll")) {
			String 입력부서 = request.getParameter("입력부서");
			dao.dellAll(입력부서);

		} else {
			System.out.println("잘못된 접근 입니다.");

		}
	}

	public float 중도탈락률외부(int 재적학생수, int 계) {
		return (float) (계 / (재적학생수 * 1.0) * 100);
	}

	public int 계(int 미등록, int 미복학, int 자퇴, int 학사경고, int 기타) {
		return 미등록 + 미복학 + 자퇴 + 학사경고 + 기타;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
