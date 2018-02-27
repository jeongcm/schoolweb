package 연구비외부;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 연구비외부Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(연구비외부 연구비외부) {
		String sql = "update 연구비외부 set 대학명=?,학과명=?,중앙정부=?,지자체=?,민간=?,외국=?,연구비=?, 전임교원수=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 연구비외부.get대학명());
			pstmt.setString(2, 연구비외부.get학과명());
			pstmt.setInt(3, 연구비외부.get중앙정부());
			pstmt.setInt(4, 연구비외부.get지자체());
			pstmt.setInt(5, 연구비외부.get민간());
			pstmt.setInt(6, 연구비외부.get외국());
			pstmt.setFloat(7, 연구비외부.get연구비());
			pstmt.setInt(8, 연구비외부.get전임교원수());
			pstmt.setInt(9, 연구비외부.get연번());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(연구비외부 연구비외부) {
		String sql = "insert into 연구비외부(대학명,학과명,중앙정부,지자체,민간,외국,연구비,입력부서,전임교원수) values(?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 연구비외부.get대학명());
			pstmt.setString(2, 연구비외부.get학과명());
			pstmt.setInt(3, 연구비외부.get중앙정부());
			pstmt.setInt(4, 연구비외부.get지자체());
			pstmt.setInt(5, 연구비외부.get민간());
			pstmt.setInt(6, 연구비외부.get외국());
			pstmt.setFloat(7, 연구비외부.get연구비());
			pstmt.setString(8, 연구비외부.get입력부서());
			pstmt.setInt(9, 연구비외부.get전임교원수());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 연구비외부 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 연구비외부 where 입력부서 =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 연구비외부";
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

	public List<연구비외부> select연구비외부(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<연구비외부> list = new ArrayList<연구비외부>();

		String sql = "select * from 연구비외부 order by 연번 desc limit ?,? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				연구비외부 연구비외부 = new 연구비외부();
				연구비외부.set대학명(rs.getString("대학명"));
				연구비외부.set학과명(rs.getString("학과명"));
				연구비외부.setT점수(rs.getFloat("T점수"));
				연구비외부.set민간(rs.getInt("민간"));
				연구비외부.set연구비(rs.getFloat("연구비"));
				연구비외부.set외국(rs.getInt("외국"));
				연구비외부.set중앙정부(rs.getInt("중앙정부"));
				연구비외부.set지자체(rs.getInt("지자체"));
				연구비외부.set연번(rs.getInt("연번"));
				연구비외부.set전임교원수(rs.getInt("전임교원수"));
				연구비외부.set입력부서(rs.getString("입력부서"));
				list.add(연구비외부);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public float 연구비외부(int 대학명, String 학과명) {
		float 연구비외부 = 0;

		String sql = "select 연구비 from 연구비외부 where 대학명=? and 학과명=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 대학명);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				연구비외부 = rs.getFloat("연구비");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 연구비외부;
	}


}