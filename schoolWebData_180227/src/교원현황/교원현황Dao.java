package 교원현황;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import 교원현황.교원현황;

public class 교원현황Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;

	private ResultSet rs;

	public boolean update(교원현황 교원현황) {
		String sql = "update 교원현황 set 년도=?,학과명=?,일학기=?,이학기=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 교원현황.get년도());
			pstmt.setString(2, 교원현황.get학과명());
			pstmt.setInt(3, 교원현황.get일학기());
			pstmt.setInt(4, 교원현황.get이학기());
			pstmt.setInt(5, 교원현황.get연번());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(교원현황 교원현황) {

		String sql = "insert into 교원현황(년도,학과명,일학기,이학기,입력부서) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 교원현황.get년도());
			pstmt.setString(2, 교원현황.get학과명());
			pstmt.setInt(3, 교원현황.get일학기());
			pstmt.setInt(4, 교원현황.get이학기());
			pstmt.setString(5, 교원현황.get입력부서());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 교원현황 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 교원현황 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 교원현황";
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

	public List<교원현황> select교원현황(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<교원현황> list = new ArrayList<교원현황>();

		String sql = "select * from 교원현황 order by 연번 desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				교원현황 교원현황 = new 교원현황();

				교원현황.set년도(rs.getInt("년도"));
				교원현황.set학과명(rs.getString("학과명"));
				교원현황.set일학기(rs.getInt("일학기"));
				교원현황.set이학기(rs.getInt("이학기"));
				교원현황.set연번(rs.getInt("연번"));
				교원현황.set입력부서(rs.getString("입력부서"));

				list.add(교원현황);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
