package C그룹;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import dbConnection.dbConnection;

public class C그룹Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt, pstmt2, pstmt3, pstmt4, pstmt5, pstmt6, pstmt7, pstmt8, pstmt9, pstmt10;
	private ResultSet rs, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10;

	public List<C그룹> selectC그룹() throws SQLException {
		List<C그룹> list = new ArrayList<C그룹>();

		String sql = "select max(학과.년도) 년도,단과대학,학과.학과명,학문계열1,T점수 외국인T,(T점수*0.02) 외국인환산 from 학과현황 학과 left outer join 외국인학생현황 현황 on 학과.학과명=현황.학과명 left outer join 외국인학생비율 비율 on 현황.학과명=비율.학과명 group by 학과.학과명 order by 학과.연번 desc ;";
		String sql2 = "select 학과.학과명,T점수 만족도T,(T점수*0.02) 만족도환산 from 설문조사 right outer join 학과현황 학과 on 설문조사.학과명=학과.학과명 order by 학과.연번 desc ;";
		String sql3 = "select 학과.학과명,T점수 현장실습T,(T점수*0.02) 현장실습환산 from 현장실습 right outer join 학과현황 학과 on 현장실습.학과명=학과.학과명 order by 학과.연번 desc ;";
		String sql4 = "select 학과.학과명,T점수 캡스톤T,(T점수*0.02) 캡스톤환산 from 캡스톤디자인 right outer join 학과현황 학과 on 캡스톤디자인.학과명=학과.학과명 order by 학과.연번 desc ;";
		String sql5 = "select 학과.학과명,T점수 교육프로그램T,(T점수*0.02) 교육프로그램환산 from 교육프로그램운영내역 교육 right outer join 학과현황 학과 on 교육.학과명=학과.학과명 left outer join 교육프로그램비율 비율 on 교육.학과명=비율.학과명 order by 학과.연번 desc ;";
		String sql6 = "select 학과.학과명,T점수 동아리T,(T점수*0.02) 동아리환산 from 동아리 right outer join 학과현황 학과 on 동아리.학과명=학과.학과명 left outer join 동아리참여비율 비율 on 동아리.학과명=비율.학과명 order by 학과.연번 desc ;";
		String sql7 = "select 학과.학과명,T점수 특허등록T,(T점수*0.02) 특허등록환산 from 특허등록view right outer join 학과현황 학과 on 특허등록view.학과명=학과.학과명 order by 학과.연번 desc ;";
		String sql8 = "select 학과.학과명,T점수 봉사실적T,(T점수*0.02) 봉사실적환산 from 봉사실적  right outer join 학과현황 학과 on 봉사실적.학과명=학과.학과명 left outer join 봉사실적비율 비율 on 봉사실적.학과명=비율.학과명 order by 학과.연번 desc ;";
		String sql9 = "select 학과.학과명,T점수 수상실적T,(T점수*0.02) 수상실적환산 from 수상실적 right outer join 학과현황 학과 on 수상실적.학과명=학과.학과명 left outer join 수상실적비율 비율 on 수상실적.학과명=비율.학과명 order by 학과.연번 desc ;";
		String sql10 = "select 학과.학과명,T점수 강의공개T,(T점수*0.02) 강의공개환산 from 강의공개실적 right outer join 학과현황 학과 on 강의공개실적.학과명=학과.학과명 order by 학과.연번 desc ;";
		

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt4 = conn.prepareStatement(sql4);
			pstmt5 = conn.prepareStatement(sql5);
			pstmt6 = conn.prepareStatement(sql6);
			pstmt7 = conn.prepareStatement(sql7);
			pstmt8 = conn.prepareStatement(sql8);
			pstmt9 = conn.prepareStatement(sql9);
			pstmt10 = conn.prepareStatement(sql10);

			rs = pstmt.executeQuery();
			rs2 = pstmt2.executeQuery();
			rs3 = pstmt3.executeQuery();
			rs4 = pstmt4.executeQuery();
			rs5 = pstmt5.executeQuery();
			rs6 = pstmt6.executeQuery();
			rs7 = pstmt7.executeQuery();
			rs8 = pstmt8.executeQuery();
			rs9 = pstmt9.executeQuery();
			rs10 = pstmt10.executeQuery();


			while (rs.next() && rs2.next() && rs3.next() && rs4.next() && rs5.next() && rs6.next() && rs7.next()
					&& rs8.next() && rs9.next() && rs10.next()) {
				C그룹 C그룹 = new C그룹();

				C그룹.set년도(rs.getInt("년도"));
				C그룹.set학과명(rs.getString("학과명"));
				C그룹.set단과대학(rs.getString("단과대학"));
				C그룹.set학문계열(rs.getString("학문계열1"));

				C그룹.set외국인T(rs.getFloat("외국인T"));
				C그룹.set외국인환산((float) (Math.round(rs.getFloat("외국인환산") * 100) / 100.0));

				C그룹.set만족도T(rs2.getFloat("만족도T"));
				C그룹.set만족도환산((float) (Math.round(rs2.getFloat("만족도환산") * 100) / 100.0));

				C그룹.set현장실습T(rs3.getFloat("현장실습T"));
				C그룹.set현장실습환산((float) (Math.round(rs3.getFloat("현장실습환산") * 100) / 100.0));

				C그룹.set캡스톤T(rs4.getFloat("캡스톤T"));
				C그룹.set캡스톤환산((float) (Math.round(rs4.getFloat("캡스톤환산") * 100) / 100.0));

				C그룹.set교육프로그램T(rs5.getFloat("교육프로그램T"));
				C그룹.set교육프로그램환산((float) (Math.round(rs5.getFloat("교육프로그램환산") * 100) / 100.0));

				C그룹.set동아리T(rs6.getFloat("동아리T"));
				C그룹.set동아리환산((float) (Math.round(rs6.getFloat("동아리환산") * 100) / 100.0));
				
				C그룹.set특허등록T(rs7.getFloat("특허등록T"));
				C그룹.set특허등록환산((float) (Math.round(rs7.getFloat("특허등록환산") * 100) / 100.0));

				C그룹.set봉사실적T(rs8.getFloat("봉사실적T"));
				C그룹.set봉사실적환산((float) (Math.round(rs8.getFloat("봉사실적환산") * 100) / 100.0));

				C그룹.set수상실적T(rs9.getFloat("수상실적T"));
				C그룹.set수상실적환산((float) (Math.round(rs9.getFloat("수상실적환산") * 100) / 100.0));

				C그룹.set강의공개T(rs10.getFloat("강의공개T"));
				C그룹.set강의공개환산((float) (Math.round(rs10.getFloat("강의공개환산") * 100) / 100.0));

				
				C그룹.set환산점수((float) (Math.round(rs.getFloat("외국인환산") + rs2.getFloat("만족도환산") + rs3.getFloat("현장실습환산")
						+ rs4.getFloat("캡스톤환산") + rs5.getFloat("교육프로그램환산") + rs6.getFloat("동아리환산") + rs7.getFloat("특허등록환산")
						+ rs8.getFloat("봉사실적환산") + rs9.getFloat("수상실적환산") + rs10.getFloat("강의공개환산") * 100) / 100.0));

				list.add(C그룹);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}