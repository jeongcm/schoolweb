package 외국인학생비율;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import 외국인학생비율.외국인학생비율;

public class 외국인학생비율Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;

	private ResultSet rs;

	public boolean update(외국인학생비율 외국인학생비율) {
		String sql = "update 외국인학생현황 set 년도=?,학과명=?,학년=?,학번=?,성명=?,국적=?,성별=?,비고=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 외국인학생비율.get년도());
			pstmt.setString(2, 외국인학생비율.get학과명());
			pstmt.setInt(3, 외국인학생비율.get학년());
			pstmt.setInt(4, 외국인학생비율.get학번());

			pstmt.setString(5, 외국인학생비율.get성명());
			pstmt.setString(6, 외국인학생비율.get국적());
			pstmt.setString(7, 외국인학생비율.get성별());
			pstmt.setString(8, 외국인학생비율.get비고());
			pstmt.setInt(9, 외국인학생비율.get연번());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(외국인학생비율 외국인학생비율) {
		String sql = "insert into 외국인학생현황(년도,학과명,학년,학번,성명,국적,성별,비고,입력부서) values(?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 외국인학생비율.get년도());
			pstmt.setString(2, 외국인학생비율.get학과명());
			pstmt.setInt(3, 외국인학생비율.get학년());
			pstmt.setInt(4, 외국인학생비율.get학번());
			pstmt.setString(5, 외국인학생비율.get성명());
			pstmt.setString(6, 외국인학생비율.get국적());
			pstmt.setString(7, 외국인학생비율.get성별());
			pstmt.setString(8, 외국인학생비율.get비고());
			pstmt.setString(9, 외국인학생비율.get입력부서());

			pstmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 외국인학생현황 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 외국인학생현황 where 입력부서=?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 외국인학생현황";
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

	public List<외국인학생비율> select외국인학생비율(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<외국인학생비율> list = new ArrayList<외국인학생비율>();

		String sql = "select * from 외국인학생현황 order by 연번 desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				외국인학생비율 외국인학생비율 = new 외국인학생비율();
				외국인학생비율.set년도(rs.getInt("년도"));
				외국인학생비율.set학과명(rs.getString("학과명"));
				외국인학생비율.set학년(rs.getInt("학년"));
				외국인학생비율.set학번(rs.getInt("학번"));
				외국인학생비율.set성명(rs.getString("성명"));
				외국인학생비율.set국적(rs.getString("국적"));
				외국인학생비율.set성별(rs.getString("성별"));
				외국인학생비율.set비고(rs.getString("비고"));
				외국인학생비율.set연번(rs.getInt("연번"));
				외국인학생비율.set입력부서(rs.getString("입력부서"));

				list.add(외국인학생비율);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int 외국인학생수(int 년도, String 학과명) {
		int 학생수 = 0;
		String sql = "select count(*) 학생수 from 외국인학생현황 where 학과명=? and 년도=?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 학과명);
			pstmt.setInt(2, 년도);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				학생수 = rs.getInt("학생수");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 학생수;
	}


	public void update외국인학생비율(int 수정년도, String 수정학과명, float 외국인학생비율) {
		String sql = "update 외국인학생비율 set 외국인학생비율=? where 학과명=? and 년도=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, 외국인학생비율);
			pstmt.setString(2, 수정학과명);
			pstmt.setInt(3, 수정년도);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}