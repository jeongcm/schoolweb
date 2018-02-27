package 재학생현황;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 재학생현황Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(재학생현황 재학생현황) {

		String sql = "update 재학생현황 set 년도=?,학과명=?,기준=? ,군휴학자=? ,타학과전과자=?,정원내=?, 정원외=? ,학생정원=?,계=?,전체재학생충원율=?,정원내재학생충원율=?,재학생충원율=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 재학생현황.get년도());
			pstmt.setString(2, 재학생현황.get학과명());
			pstmt.setString(3, "04.01");
			pstmt.setInt(4, 재학생현황.get군휴학자());
			pstmt.setInt(5, 재학생현황.get타학과전과자());
			pstmt.setInt(6, 재학생현황.get정원내());
			pstmt.setInt(7, 재학생현황.get정원외());
			pstmt.setInt(8, 재학생현황.get학생정원());
			pstmt.setInt(9, 재학생현황.get계());
			pstmt.setFloat(10, 재학생현황.get전체재학생충원율());
			pstmt.setFloat(11, 재학생현황.get정원내재학생충원율());
			pstmt.setFloat(12, 재학생현황.get재학생충원율());
			pstmt.setInt(13, 재학생현황.get연번());
			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(재학생현황 재학생현황) {
		String sql = "insert into 재학생현황(년도,기준,학과명,학생정원,군휴학자,정원내,정원외,계,정원내재학생충원율,전체재학생충원율,재학생충원율,입력부서,타학과전과자) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 재학생현황.get년도());
			pstmt.setString(2, "04.01");
			pstmt.setString(3, 재학생현황.get학과명());
			pstmt.setInt(4, 재학생현황.get학생정원());
			pstmt.setInt(5, 재학생현황.get군휴학자());
			pstmt.setInt(6, 재학생현황.get정원내());
			pstmt.setInt(7, 재학생현황.get정원외());
			pstmt.setInt(8, 재학생현황.get계());
			pstmt.setFloat(9, 재학생현황.get정원내재학생충원율());
			pstmt.setFloat(10, 재학생현황.get전체재학생충원율());
			pstmt.setFloat(11, 재학생현황.get재학생충원율());
			pstmt.setString(12, 재학생현황.get입력부서());
			pstmt.setInt(13, 재학생현황.get타학과전과자());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public void delete(int 연번) {
		String sql = "delete from 재학생현황 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 재학생현황  where 기준 = '04.01' and 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 재학생현황 where 기준 = '04.01'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 결과물 편집, 리턴
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<재학생현황> select재학생현황(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<재학생현황> list = new ArrayList<재학생현황>();

		String sql = "select * from 재학생현황 where 기준 = '04.01' order by 연번 desc limit ?,?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				재학생현황 재학생현황 = new 재학생현황();
				재학생현황.set년도(rs.getInt("년도"));
				재학생현황.set연번(rs.getInt("연번"));
				재학생현황.set학과명(rs.getString("학과명"));
				재학생현황.set입력부서(rs.getString("입력부서"));
				재학생현황.set학생정원(rs.getInt("학생정원"));
				재학생현황.set군휴학자(rs.getInt("군휴학자"));
				재학생현황.set정원내(rs.getInt("정원내"));
				재학생현황.set정원외(rs.getInt("정원외"));
				재학생현황.set타학과전과자(rs.getInt("타학과전과자"));
				재학생현황.set계(rs.getInt("계"));
				재학생현황.set재학생충원율(rs.getFloat("재학생충원율"));
				재학생현황.set전체재학생충원율(rs.getFloat("전체재학생충원율"));
				재학생현황.set정원내재학생충원율(rs.getFloat("정원내재학생충원율"));
				재학생현황.setT점수(rs.getFloat("T점수"));

				list.add(재학생현황);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public float 재학생충원율(int 년도, String 학과명) {
		float 재학생충원율 = 0;

		String sql = "select 재학생충원율 from 재학생현황 where 년도=? and 학과명=? and 기준=04.01;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				재학생충원율 = rs.getFloat("재학생충원율");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 재학생충원율;
	}

	public float avg(int 년도) {
		float 평균 = 0;
		String sql = "select avg(재학생충원율) 평균 from 재학생현황  where 기준='04.01' and 년도=?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 년도);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				평균 = rs.getFloat("평균");

			}

			System.out.println("평균"+평균);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 평균;

	}

	public float std(int 년도) { //재학생현황은 기준이 04.01에 대한 평균,표준편차를 구하므로 따로 구현해놓음
		float 표준편차 = 0;
		String sql = "select stddev_samp(재학생충원율) 표준편차 from 재학생현황  where 기준='04.01' and 년도=?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 년도);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				표준편차 = rs.getFloat("표준편차");

			}
			System.out.println("표준편차"+표준편차);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 표준편차;

	}
}