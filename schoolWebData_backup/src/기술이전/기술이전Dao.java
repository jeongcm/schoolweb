package 기술이전;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 기술이전.기술이전;

public class 기술이전Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(기술이전 기술이전) {
		String sql = "update 기술이전 set 년도=?,학과명=?,대표발명자=?,지식재산권=?,정액기술료=? where 연번=?";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 기술이전.get년도());
			pstmt.setString(2, 기술이전.get학과명());
			pstmt.setString(3, 기술이전.get대표발명자());
			pstmt.setString(4, 기술이전.get지식재산권());
			pstmt.setFloat(5, 기술이전.get정액기술료());
			pstmt.setInt(6, 기술이전.get연번());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(기술이전 기술이전) {
		String sql = "insert into 기술이전(년도,학과명,대표발명자,지식재산권,정액기술료,입력부서) values(?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 기술이전.get년도());
			pstmt.setString(2, 기술이전.get학과명());
			pstmt.setString(3, 기술이전.get대표발명자());
			pstmt.setString(4, 기술이전.get지식재산권());
			pstmt.setFloat(5, 기술이전.get정액기술료());
			pstmt.setString(6, 기술이전.get입력부서());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {

		String sql = "delete from 기술이전 where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 기술이전 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 기술이전";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<기술이전> select기술이전(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<기술이전> list = new ArrayList<기술이전>();

		String sql = "select * from 기술이전 order by 연번 desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				기술이전 기술이전 = new 기술이전();
				기술이전.set년도(rs.getInt("년도"));
				기술이전.set학과명(rs.getString("학과명"));
				기술이전.set대표발명자(rs.getString("대표발명자"));
				기술이전.set지식재산권(rs.getString("지식재산권"));
				기술이전.set정액기술료(rs.getFloat("정액기술료"));
				기술이전.set연번(rs.getInt("연번"));
				기술이전.set입력부서(rs.getString("입력부서"));

				list.add(기술이전);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int 국제(int 수정년도, String 수정학과명) {
		int 국제 = 0;
		String sql = "select 국제 from 특허등록및기술이전수입료 where 년도=?,학과명=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 수정년도);
			pstmt.setString(2, 수정학과명);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				국제 = rs.getInt("국제");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 국제;
	}

	public int 국내(int 수정년도, String 수정학과명) {
		int 국내 = 0;
		String sql = "select 국내 from 특허등록및기술이전수입료 where 년도=?,학과명=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 수정년도);
			pstmt.setString(2, 수정학과명);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				국내 = rs.getInt("국내");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 국내;
	}
}
