package 취업자외부;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 취업자외부Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(취업자외부 취업자외부) {
		String sql = "update 취업자외부 set 대학명=?,학과명=?,건강보험db연계취업자=?,해외취업자=?,영농업취업자=?,계=? ,비고=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, 취업자외부.get대학명());
			pstmt.setString(2, 취업자외부.get학과명());
			pstmt.setInt(3, 취업자외부.get건강보험DB연계취업자());
			pstmt.setInt(4, 취업자외부.get해외취업자());
			pstmt.setInt(5, 취업자외부.get영농업취업자());
			pstmt.setInt(6, 취업자외부.get계());
			pstmt.setString(7, 취업자외부.get비고());
			pstmt.setInt(8, 취업자외부.get연번());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(취업자외부 취업자외부) {
		String sql = "insert into 취업자외부(대학명,학과명,건강보험db연계취업자,해외취업자,영농업취업자,계,비고,입력부서) values(?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 취업자외부.get대학명());
			pstmt.setString(2, 취업자외부.get학과명());
			pstmt.setInt(3, 취업자외부.get건강보험DB연계취업자());
			pstmt.setInt(4, 취업자외부.get해외취업자());
			pstmt.setInt(5, 취업자외부.get영농업취업자());
			pstmt.setInt(6, 취업자외부.get계());
			pstmt.setString(7, 취업자외부.get비고());
			pstmt.setString(8, 취업자외부.get입력부서());
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 취업자외부 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void dellAll(String 입력부서) {
		String sql = "delete from 취업자외부 where 입력부서 =?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 취업자외부";
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

	public List<취업자외부> select취업자외부(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<취업자외부> list = new ArrayList<취업자외부>();

		String sql = "select * from 취업자외부 order by 연번 desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				취업자외부 취업자외부 = new 취업자외부();
				취업자외부.set대학명(rs.getString("대학명"));
				취업자외부.set학과명(rs.getString("학과명"));
				취업자외부.set계(rs.getInt("계"));
				취업자외부.set건강보험DB연계취업자(rs.getInt("건강보험DB연계취업자"));
				취업자외부.set해외취업자(rs.getInt("해외취업자"));
				취업자외부.set영농업취업자(rs.getInt("영농업취업자"));
				취업자외부.set연번(rs.getInt("연번"));
				취업자외부.set비고(rs.getString("비고"));
				취업자외부.set입력부서(rs.getString("입력부서"));
				list.add(취업자외부);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
