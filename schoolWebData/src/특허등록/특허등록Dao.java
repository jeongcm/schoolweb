package 특허등록;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 특허등록.특허등록;

public class 특허등록Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(특허등록 특허등록) {
		String sql = "update 특허등록및기술이전수입료 set 년도=?,학과명=?,국제=?,국내=?  where 연번=?";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 특허등록.get년도());
			pstmt.setString(2, 특허등록.get학과명());
			pstmt.setInt(3, 특허등록.get국제());
			pstmt.setInt(4, 특허등록.get국내());
			pstmt.setInt(5, 특허등록.get연번());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(특허등록 특허등록) {
		String sql = "insert into 특허등록및기술이전수입료(년도,학과명,국제,국내,입력부서) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 특허등록.get년도());
			pstmt.setString(2, 특허등록.get학과명());
			pstmt.setInt(3, 특허등록.get국제());
			pstmt.setInt(4, 특허등록.get국내());
			pstmt.setString(5, 특허등록.get입력부서());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {

		String sql = "delete from 특허등록및기술이전수입료 where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 특허등록및기술이전수입료 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 특허등록및기술이전수입료";
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

	public List<특허등록> select특허등록(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<특허등록> list = new ArrayList<특허등록>();

		String sql = "select * from 특허등록및기술이전수입료 order by 연번 desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				특허등록 특허등록 = new 특허등록();
				특허등록.set년도(rs.getInt("년도"));
				특허등록.set학과명(rs.getString("학과명"));
				특허등록.set국제(rs.getInt("국제"));
				특허등록.set국내(rs.getInt("국내"));
				특허등록.set연번(rs.getInt("연번"));
				특허등록.set입력부서(rs.getString("입력부서"));

				list.add(특허등록);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public float 특허이전료기술이전(int 수정년도, String 수정학과명) {
		float 정액기술료 = 0;

		String sql = "select sum(정액기술료) 정액기술료 from 기술이전 where 년도=? and 학과명=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 수정년도);
			pstmt.setString(2, 수정학과명);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				정액기술료 = rs.getFloat("정액기술료");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 정액기술료;
	}
}
