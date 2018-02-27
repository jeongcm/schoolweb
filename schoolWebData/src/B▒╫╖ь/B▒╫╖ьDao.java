package B그룹;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class B그룹Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt, pstmt2, pstmt3, pstmt4;

	private ResultSet rs, rs2, rs3, rs4;

	public List<B그룹> selectB그룹() {
		List<B그룹> list = new ArrayList<B그룹>();

		String sql = " select max(학과.년도) 년도,학과.학과명,학과.단과대학,학과.학문계열1,T점수 중도탈락T,(T점수*0.05) 중도탈락환산 from 중도탈락률 right outer join  학과현황 학과 on 중도탈락률.학과명=학과.학과명   group by 학과.학과명 order by 학과.연번 desc ;";
		String sql2 = "select T점수 장학금T,(T점수*0.06) 장학금환산 from 교외장학금 right outer join 학과현황 학과 on 교외장학금.학과명=학과.학과명 order by 학과.연번 desc ;";
		String sql3 = "select T점수 발전기금T,(T점수*0.07) 발전기금환산 from 발전기금조성액 right outer join 학과현황 학과 on 발전기금조성액.학과명=학과.학과명 order by 학과.연번 desc ;";
		String sql4 = "select T점수 교원확보T,(T점수*0.08) 교원확보환산 from 전임교원확보율 right outer join 학과현황 학과 on 전임교원확보율.학과명=학과.학과명 order by 학과.연번 desc ;";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt4 = conn.prepareStatement(sql4);

			rs = pstmt.executeQuery();
			rs2 = pstmt2.executeQuery();
			rs3 = pstmt3.executeQuery();
			rs4 = pstmt4.executeQuery();

			while (rs.next() && rs2.next() && rs3.next() && rs4.next()) {
				B그룹 B그룹= new B그룹();
				B그룹.set년도(rs.getInt("년도"));
				B그룹.set학과명(rs.getString("학과명"));
				B그룹.set단과대학(rs.getString("단과대학"));
				B그룹.set학문계열(rs.getString("학문계열1"));

				B그룹.set중도탈락T(rs.getFloat("중도탈락T"));
				B그룹.set중도탈락환산((float)(Math.round(rs.getFloat("중도탈락환산")*100)/100.0));

				B그룹.set장학금T(rs2.getFloat("장학금T"));
				B그룹.set장학금환산((float)(Math.round(rs2.getFloat("장학금환산")*100)/100.0));

				B그룹.set발전기금T(rs3.getFloat("발전기금T"));
				B그룹.set발전기금환산((float)(Math.round(rs3.getFloat("발전기금환산")*100)/100.0));

				B그룹.set교원확보T(rs4.getFloat("교원확보T"));
				B그룹.set교원확보환산((float)(Math.round(rs4.getFloat("교원확보환산")*100)/100.0));

				B그룹.set환산점수((float)(Math.round(rs.getFloat("중도탈락환산")+rs2.getFloat("장학금환산")+rs3.getFloat("발전기금환산")+rs4.getFloat("교원확보환산")*100)/100.0));
				
				list.add(B그룹);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
