package 학과현황;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class 학과현황Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(학과현황 학과현황) {

		String sql = "update 학과현황 set 단과대학=?,_5대계열=?,학문계열1=?,신설연도=?,비고=?, 학과명=?,년도=? where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 학과현황.get단과대학());
			pstmt.setString(2, 학과현황.get_5대계열());
			pstmt.setString(3, 학과현황.get학문계열1());
			pstmt.setString(4, 학과현황.get신설연도());
			pstmt.setString(5, 학과현황.get비고());
			pstmt.setString(6, 학과현황.get학과명());
			pstmt.setInt(7, 학과현황.get년도());
			pstmt.setInt(8, 학과현황.get연번());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(학과현황 학과현황) {

		String sql = "insert into 학과현황(년도,단과대학,학과명,_5대계열,학문계열1,신설연도,비고,입력부서) values(?,?,?,?,?,?,?,?);";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 학과현황.get년도());
			pstmt.setString(2, 학과현황.get단과대학());
			pstmt.setString(3, 학과현황.get학과명());
			pstmt.setString(4, 학과현황.get_5대계열());
			pstmt.setString(5, 학과현황.get학문계열1());
			pstmt.setString(6, 학과현황.get신설연도());
			pstmt.setString(7, 학과현황.get비고());
			pstmt.setString(8, 학과현황.get입력부서());
			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {

		String sql = "delete from 학과현황 where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 학과현황 where 입력부서=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 학과현황";
		try {
			rs = pstmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<학과현황> select학과현황(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<학과현황> list = new ArrayList<학과현황>();
		String sql = "select * from 학과현황 order by 연번 desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				학과현황 학과현황 = new 학과현황();
				학과현황.set연번(rs.getInt("연번"));
				학과현황.set년도(rs.getInt("년도"));
				학과현황.set단과대학(rs.getString("단과대학"));
				학과현황.set학과명(rs.getString("학과명"));
				학과현황.set_5대계열(rs.getString("_5대계열"));
				학과현황.set학문계열1(rs.getString("학문계열1"));
				학과현황.set신설연도(rs.getString("신설연도"));
				학과현황.set비고(rs.getString("비고"));
				학과현황.set입력부서(rs.getString("입력부서"));
				list.add(학과현황);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void 비율학과명update(int 년도, String 학과명) {

		PreparedStatement pstmt2, pstmt3, pstmt4, pstmt5;

		String sql = "insert into 외국인학생비율(년도,학과명) values(?,?);";
		String sql2 = "insert into 봉사실적비율(년도,학과명) values(?,?);";
		String sql3 = "insert into 교육프로그램비율(년도,학과명) values(?,?);";
		String sql4 = "insert into 수상실적비율(년도,학과명) values(?,?);";
		String sql5 = "insert into 동아리참여비율(년도,학과명) values(?,?);";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt4 = conn.prepareStatement(sql4);
			pstmt5 = conn.prepareStatement(sql5);

			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);

			pstmt2.setInt(1, 년도);
			pstmt2.setString(2, 학과명);

			pstmt3.setInt(1, 년도);
			pstmt3.setString(2, 학과명);

			pstmt4.setInt(1, 년도);
			pstmt4.setString(2, 학과명);

			pstmt5.setInt(1, 년도);
			pstmt5.setString(2, 학과명);

			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			pstmt3.executeUpdate();
			pstmt4.executeUpdate();
			pstmt5.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}