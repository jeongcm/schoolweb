package D그룹;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class D그룹Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt, pstmt2, pstmt3, pstmt4, pstmt5, pstmt6;

	private ResultSet rs, rs2, rs3, rs4, rs5, rs6;

	public List<D그룹> selectD그룹() {

		List<D그룹> list = new ArrayList<D그룹>();

		String sql = "select max(학과.년도) 년도,단과대학,학과.학과명,학문계열1,T점수 중도탈락률T,(T점수*0.04) 중도탈락률환산 from 학과현황 학과 left outer join 중도탈락률외부 중도탈락률 on 학과.학과명=중도탈락률.학과명 group by 학과.학과명 order by 학과.연번 desc ;";
		String sql2 = "select 학과.학과명,T점수 신입생T,(T점수*0.02) 신입생환산 from 신입생현황외부 right outer join 학과현황 학과 on 신입생현황외부.학과명=학과.학과명 order by 학과.연번 desc ;";
		String sql3 = "select 학과.학과명,T점수 취업률T,(T점수*0.04) 취업률환산 from 취업현황외부 취업현황 right outer join 학과현황 학과 on 취업현황.학과명=학과.학과명 order by 학과.연번 desc;";
		String sql4 = "select 학과.학과명,T점수 연구실적T,(T점수*0.04) 연구실적환산 from 취업현황외부 취업현황 right outer join 학과현황 학과 on 취업현황.학과명=학과.학과명 order by 학과.연번 desc;";
		String sql5 = "select 학과.학과명,T점수 연구비T,(T점수*0.04) 연구비환산 from 취업현황외부 취업현황 right outer join 학과현황 학과 on 취업현황.학과명=학과.학과명 order by 학과.연번 desc;";
		String sql6 = "select 학과.학과명,T점수 강의T,(T점수*0.04) 강의환산 from 취업현황외부 취업현황 right outer join 학과현황 학과 on 취업현황.학과명=학과.학과명 order by 학과.연번 desc;";
		
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
				D그룹 D그룹 = new D그룹();
				D그룹.set년도(rs.getInt("년도"));
				D그룹.set학과명(rs.getString("학과명"));
				D그룹.set단과대학(rs.getString("단과대학"));
				D그룹.set학문계열(rs.getString("학문계열1"));

				D그룹.set중도탈락률T(rs.getFloat("중도탈락률T"));
				D그룹.set중도탈락률환산((float)(Math.round(rs.getFloat("중도탈락률환산")*100)/100.0));

				D그룹.set신입생T(rs2.getFloat("신입생T"));
				D그룹.set신입생환산((float)(Math.round(rs2.getFloat("신입생환산")*100)/100.0));

				D그룹.set취업률T(rs3.getFloat("취업률T"));
				D그룹.set취업률환산((float)(Math.round(rs3.getFloat("취업률환산")*100)/100.0));

				D그룹.set연구실적T(rs4.getFloat("연구실적T"));
				D그룹.set연구실적환산((float)(Math.round(rs4.getFloat("연구실적환산")*100)/100.0));

				D그룹.set연구비T(rs5.getFloat("연구비T"));
				D그룹.set연구비환산((float)(Math.round(rs5.getFloat("연구비환산")*100)/100.0));

				D그룹.set강의T(rs6.getFloat("강의T"));
				D그룹.set강의환산((float)(Math.round(rs6.getFloat("강의환산")*100)/100.0));

				D그룹.set환산점수((float)(Math.round(rs.getFloat("중도탈락률환산") + rs2.getFloat("신입생환산") + rs6.getFloat("강의환산") + rs4.getFloat("연구실적환산")
						+ rs5.getFloat("연구비환산") + rs3.getFloat("취업률환산")*100)/100.0));

				list.add(D그룹);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


}
