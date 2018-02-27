package A그룹;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class A그룹Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt, pstmt2, pstmt3, pstmt4, pstmt5, pstmt6;

	private ResultSet rs, rs2, rs3, rs4, rs5, rs6;

	public List<A그룹> selectA그룹() {

		List<A그룹> list = new ArrayList<A그룹>();

		String sql = "select max(학과.년도) 년도,단과대학,학과.학과명,학문계열1,T점수 재학생T,(T점수*0.09) 재학생환산 from 학과현황 학과 left outer join 재학생현황 재학생 on 학과.학과명=재학생.학과명 group by 학과.학과명 order by 학과.연번 desc ;";
		String sql2 = "select 학과.학과명,T점수 신입생T,(T점수*0.09) 신입생환산 from 신입생현황 right outer join 학과현황 학과 on 신입생현황.학과명=학과.학과명 order by 학과.연번 desc ;";
		String sql3 = "select 학과.학과명,T점수 취업률T,(T점수*0.08) 취업률환산 from 취업현황 right outer join 학과현황 학과 on 취업현황.학과명=학과.학과명 order by 학과.연번 desc;";
		String sql4 = "select 학과.학과명,T점수 연구실적T,(T점수*0.07) 연구실적환산 from 연구실적 right outer join 학과현황 학과 on 연구실적.학과명=학과.학과명 order by 학과.연번 desc;";
		String sql5 = "select 학과.학과명,T점수 연구비T,(T점수*0.08) 연구비환산 from 연구비 right outer join 학과현황 학과 on 연구비.학과명=학과.학과명 order by 학과.연번 desc;";
		String sql6 = "select 학과.학과명,T점수 강의T,(T점수*0.09) 강의환산 from 개설강의담당비율 개설 right outer join 학과현황 학과 on 개설.학과명=학과.학과명 order by 학과.연번 desc;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt4 = conn.prepareStatement(sql4);
			pstmt5 = conn.prepareStatement(sql5);
			pstmt6 = conn.prepareStatement(sql6);


			rs = pstmt.executeQuery();
			rs2 = pstmt2.executeQuery();
			rs3 = pstmt3.executeQuery();
			rs4 = pstmt4.executeQuery();
			rs5 = pstmt5.executeQuery();
			rs6 = pstmt6.executeQuery();

			while (rs.next() && rs2.next() && rs3.next() && rs4.next() && rs5.next() && rs6.next()) {
				A그룹 A그룹 = new A그룹();
				A그룹.set년도(rs.getInt("년도"));
				A그룹.set학과명(rs.getString("학과명"));
				A그룹.set단과대학(rs.getString("단과대학"));
				A그룹.set학문계열(rs.getString("학문계열1"));

				A그룹.set재학생T(rs.getFloat("재학생T"));
				A그룹.set재학생환산((float)(Math.round(rs.getFloat("재학생환산")*100)/100.0));

				A그룹.set신입생T(rs2.getFloat("신입생T"));
				A그룹.set신입생환산((float)(Math.round(rs2.getFloat("신입생환산")*100)/100.0));

				A그룹.set취업률T(rs3.getFloat("취업률T"));
				A그룹.set취업률환산((float)(Math.round(rs3.getFloat("취업률환산")*100)/100.0));

				A그룹.set연구실적T(rs4.getFloat("연구실적T"));
				A그룹.set연구실적환산((float)(Math.round(rs4.getFloat("연구실적환산")*100)/100.0));

				A그룹.set연구비T(rs5.getFloat("연구비T"));
				A그룹.set연구비환산((float)(Math.round(rs5.getFloat("연구비환산")*100)/100.0));

				A그룹.set강의T(rs6.getFloat("강의T"));
				A그룹.set강의환산((float)(Math.round(rs6.getFloat("강의환산")*100)/100.0));

				A그룹.set환산점수((float)(Math.round(rs.getFloat("재학생환산") + rs2.getFloat("신입생환산") + rs6.getFloat("강의환산") + rs4.getFloat("연구실적환산")
						+ rs5.getFloat("연구비환산") + rs3.getFloat("취업률환산")*100)/100.0));

				list.add(A그룹);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
