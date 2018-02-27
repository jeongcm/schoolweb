package defaultMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dbConnection.dbConnection;

public class defaultQuery {
	private static Connection conn = dbConnection.getConnection();
	private static PreparedStatement pstmt = null;

	private static ResultSet rs;
	static Log log = new Log();

	public static String 학과명체크(String 학과명) {
		String sql = "select exists(select * from 학과현황 where 학과명=? ) result;";
		String result = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 학과명);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String 학문계열(int 년도, String 학과명) { // 취업엑셀표에서 사용
		String 학문계열 = null;

		String sql = "select 학문계열1 from 학과현황 where 년도=? and 학과명=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				학문계열 = rs.getString("학문계열1");
			}
			System.out.println("학과명 :"+학과명+" 학문계열 :"+학문계열);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 학문계열;
	}

	public static String 년도체크(int 년도) {
		String sql = "select exists(select * from 학과현황 where 년도=? ) result;";
		String result = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static float avg(int 년도, String column, String table) {
		float 평균 = 0;
		String sql = "select avg(" + column + ") 평균 from " + table + " where 년도=" + 년도 + ";";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			log.debug("일반 평균 쿼리" + pstmt);

			while (rs.next()) {
				평균 = rs.getFloat("평균");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.debug("평균: " + 평균);
		System.out.println("평균: " + 평균);
		return 평균;

	}

	public static float 외부avg(String column, String table) {
		float 평균 = 0;
		String sql = "select avg(" + column + ") 평균 from " + table + ";";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			log.debug("외부 평균 쿼리" + pstmt);

			while (rs.next()) {
				평균 = rs.getFloat("평균");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 평균;

	}

	public static float 학문계열std(int 년도, String column, String table, String 학문계열) { // 표춘편차를 구하는 함수 stddev_samp는 엑셀의 표본집단의 표준편차를 구하는 stddev와 일치
		float 표준편차 = 0;
		String sql = "select stddev_samp(" + column + ") 표준편차 from " + table + " join " + " 학과현황 on " + table
				+ ".학과명=학과현황.학과명 where 학과현황.년도=" + 년도 + " and 학문계열1= '" + 학문계열 + "';";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			log.debug("학문 계열 표준편차 쿼리" + pstmt);

			while (rs.next()) {

				표준편차 = rs.getFloat("표준편차");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 표준편차;
	}

	public static float 학문계열avg(int 년도, String column, String table, String 학문계열) {
		float 평균 = 0;
		String sql = "select avg(" + column + ") 평균 from " + table + " join " + " 학과현황 on " + table
				+ ".학과명=학과현황.학과명 where 학과현황.년도=" + 년도 + " and 학문계열1= '" + 학문계열 + "';";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			log.debug("학문계열 평균 쿼리" + pstmt);

			while (rs.next()) {
				평균 = rs.getFloat("평균");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 평균;

	}


	public static float std(int 년도, String column, String table) {
		float 표준편차 = 0;
		String sql = "select stddev_samp(" + column + ") 표준편차 from " + table + " where 년도=" + 년도 + ";";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			log.debug("일반 표준편차 쿼리" + pstmt);

			while (rs.next()) {

				표준편차 = rs.getFloat("표준편차");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.debug("표준편차 : " + 표준편차);
		System.out.println("표준편차"+표준편차);
		return 표준편차;
	}

	public static float 외부std(String column, String table) {
		float 표준편차 = 0;
		String sql = "select stddev_samp(" + column + ") 표준편차 from " + table + ";";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			log.debug("외부 표준편차 쿼리" + pstmt);

			while (rs.next()) {

				표준편차 = rs.getFloat("표준편차");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 표준편차;
	}

	public static ArrayList<String> getMajor(int 년도, String 단과대학) {

		ArrayList<String> 학과명 = new ArrayList<String>();

		String sql = "select 학과명 from 학과현황 where 년도=? and 단과대학=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 단과대학);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				학과명.add(rs.getString("학과명"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 학과명;

	}

	public static void updateT(float T점수, int 년도, String 학과명, String table) {

		String sql = "update " + table + " set T점수=? where 년도=? and 학과명=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, T점수);
			pstmt.setInt(2, 년도);
			pstmt.setString(3, 학과명);

			pstmt.executeUpdate();

			log.debug("T점수 갱신 쿼리" + pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void 외부updateT(float T점수, String 대학명, String table) {

		String sql = "update " + table + " set T점수=? where 대학명=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, T점수);
			pstmt.setString(2, 대학명);

			pstmt.executeUpdate();

			log.debug("외부 T점수 갱신 쿼리" + pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> 학과목록(int 년도, String table) {

		ArrayList<String> 학과명 = new ArrayList<String>();

		String sql = "select 학과명 from " + table + " where 년도=" + 년도 + ";";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				학과명.add(rs.getString("학과명"));
			}
			return 학과명;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<String> 외부대학목록(String table) {

		ArrayList<String> 대학명 = new ArrayList<String>();

		String sql = "select 대학명 from " + table + ";";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				대학명.add(rs.getString("대학명"));
			}
			return 대학명;

		} catch (SQLException e) {
			return null;
		}
	}

	public static float 비율(int 년도, String 학과명, String column, String table) {
		float 비율 = 0;
		
		System.out.println(학과명);
		
		String sql = "select " + column + " from " + table + " where 년도=? and 학과명=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();

			log.debug("일반 비율 쿼리" + pstmt);

			while (rs.next()) {
				비율 = rs.getFloat(column);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.debug("비율값 " + 비율);
		return 비율;
	}

	public static float 외부비율(String 대학명, String column, String table) {
		float 비율 = 0;

		String sql = "select " + column + " from " + table + " where 대학명=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 대학명);
			rs = pstmt.executeQuery();

			log.debug("외부 비율 쿼리" + pstmt);

			while (rs.next()) {
				비율 = rs.getFloat(column);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 비율;
	}

	public static int 전임교원수(int 년도, String 학과명) {
		String sql = "select 일학기 전임교원수 from 교원현황 where 년도=? and 학과명=?";
		int 전임교원수 = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				전임교원수 = rs.getInt("전임교원수");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 전임교원수;
	}

	public static int 재학생수(int 년도, String 학과명) {
		int 재학생수 = 0;
		String sql = "select 계 from 재학생현황  where 학과명=? and 기준=4.01 and 년도=?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 학과명);
			pstmt.setInt(2, 년도);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				재학생수 = rs.getInt("계");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 재학생수;
	}

}
