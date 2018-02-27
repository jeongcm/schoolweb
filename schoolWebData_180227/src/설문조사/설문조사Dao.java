package 설문조사;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 설문조사.설문조사;

public class 설문조사Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(설문조사 설문조사) {
		String sql = "update 설문조사 set 년도=?,학과명=?,참여학생수=?,설문조사총점=?,학생만족도평가=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 설문조사.get년도());
			pstmt.setString(2, 설문조사.get학과명());
			pstmt.setInt(3, 설문조사.get참여학생수());
			pstmt.setFloat(4, 설문조사.get설문조사총점());
			pstmt.setFloat(5, 설문조사.get학생만족도평가());
			pstmt.setInt(6, 설문조사.get연번());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(설문조사 설문조사) {
		String sql = "insert into 설문조사(년도,학과명,참여학생수,설문조사총점,학생만족도평가,입력부서) values(?,?,?,?,?,?);";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 설문조사.get년도());
			pstmt.setString(2, 설문조사.get학과명());
			pstmt.setInt(3, 설문조사.get참여학생수());
			pstmt.setFloat(4, 설문조사.get설문조사총점());
			pstmt.setFloat(5, 설문조사.get학생만족도평가());
			pstmt.setString(6, 설문조사.get입력부서());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 설문조사 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 설문조사 where 입력부서 =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 설문조사";
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

	public List<설문조사> select설문조사(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<설문조사> list = new ArrayList<설문조사>();

		String sql = "select * from 설문조사  order by 연번 desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				설문조사 설문조사 = new 설문조사();
				설문조사.set년도(rs.getInt("년도"));
				설문조사.set학과명(rs.getString("학과명"));
				설문조사.set참여학생수(rs.getInt("참여학생수"));
				설문조사.set설문조사총점(rs.getFloat("설문조사총점"));
				설문조사.set학생만족도평가(rs.getFloat("학생만족도평가"));
				설문조사.set연번(rs.getInt("연번"));
				설문조사.set입력부서(rs.getString("입력부서"));
				list.add(설문조사);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int 참여학생수(int 년도, String 학과명) {
		int 참여학생수 = 0;
		String sql = "select 참여학생수  from 설문조사 where 년도=? and 학과명=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				참여학생수 = rs.getInt("참여학생수");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 참여학생수;

	}

	public float 설문조사총점(int 년도, String 학과명) {
		float 설문조사총점 = 0;
		String sql = "select 설문조사총점  from 설문조사 where 년도=? and 학과명=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				설문조사총점 = rs.getInt("설문조사총점");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 설문조사총점;

	}

}