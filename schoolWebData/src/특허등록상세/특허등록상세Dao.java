package 특허등록상세;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 특허등록상세.특허등록상세;

public class 특허등록상세Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(특허등록상세 특허등록상세) {
		String sql = "update 특허등록 set 년도=?,학과명=?,대표발명자=?,지식재산권=?,정액기술료=? where 연번=?";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 특허등록상세.get년도());
			pstmt.setString(2, 특허등록상세.get학과명());
			pstmt.setString(3, 특허등록상세.get대표발명자());
			pstmt.setString(4, 특허등록상세.get지식재산권());
			pstmt.setFloat(5, 특허등록상세.get정액기술료());
			pstmt.setInt(6, 특허등록상세.get연번());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(특허등록상세 특허등록상세) {
		String sql = "insert into 특허등록(년도,학과명,대표발명자,지식재산권,정액기술료,입력부서) values(?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 특허등록상세.get년도());
			pstmt.setString(2, 특허등록상세.get학과명());
			pstmt.setString(3, 특허등록상세.get대표발명자());
			pstmt.setString(4, 특허등록상세.get지식재산권());
			pstmt.setFloat(5, 특허등록상세.get정액기술료());
			pstmt.setString(6, 특허등록상세.get입력부서());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {

		String sql = "delete from 특허등록 where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 특허등록 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 특허등록";
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

	public List<특허등록상세> select특허등록상세(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<특허등록상세> list = new ArrayList<특허등록상세>();

		String sql = "select * from 특허등록 order by 연번 desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				특허등록상세 특허등록상세 = new 특허등록상세();
				특허등록상세.set년도(rs.getInt("년도"));
				특허등록상세.set학과명(rs.getString("학과명"));
				특허등록상세.set대표발명자(rs.getString("대표발명자"));
				특허등록상세.set지식재산권(rs.getString("지식재산권"));
				특허등록상세.set정액기술료(rs.getFloat("정액기술료"));
				특허등록상세.set연번(rs.getInt("연번"));
				특허등록상세.set입력부서(rs.getString("입력부서"));

				list.add(특허등록상세);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
