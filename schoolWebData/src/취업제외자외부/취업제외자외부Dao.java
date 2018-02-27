package 취업제외자외부;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class 취업제외자외부Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(취업제외자외부 취업제외자외부) {
		String sql = "update 취업제외자외부 set 대학명=?,학과명=?,진학자=?,입대자=?,취업불가능자=?,외국인유학생=?,건강보험직장가입제외대상=?,입학당시기취업자=?,계=?,비고=?  where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, 취업제외자외부.get대학명());
			pstmt.setString(2, 취업제외자외부.get학과명());
			pstmt.setInt(3, 취업제외자외부.get진학자());
			pstmt.setInt(4, 취업제외자외부.get입대자());
			pstmt.setInt(5, 취업제외자외부.get취업불가능자());
			pstmt.setInt(6, 취업제외자외부.get외국인유학생());
			pstmt.setInt(7, 취업제외자외부.get건강보험직장가입제외대상());
			pstmt.setInt(8, 취업제외자외부.get입학당시기취업자());
			pstmt.setInt(9, 취업제외자외부.get계());
			pstmt.setString(10, 취업제외자외부.get비고());
			pstmt.setInt(11, 취업제외자외부.get연번());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(취업제외자외부 취업제외자외부) {
		String sql = "insert into 취업제외자외부(대학명,학과명,진학자,입대자,취업불가능자,외국인유학생,건강보험직장가입제외대상,계,비고,입력부서) values(?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 취업제외자외부.get대학명());
			pstmt.setString(2, 취업제외자외부.get학과명());
			pstmt.setInt(3, 취업제외자외부.get진학자());
			pstmt.setInt(4, 취업제외자외부.get입대자());
			pstmt.setInt(5, 취업제외자외부.get취업불가능자());
			pstmt.setInt(6, 취업제외자외부.get외국인유학생());
			pstmt.setInt(7, 취업제외자외부.get건강보험직장가입제외대상());
			pstmt.setInt(8, 취업제외자외부.get계());
			pstmt.setString(9, 취업제외자외부.get비고());
			pstmt.setString(10, 취업제외자외부.get입력부서());


			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 취업제외자외부 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서)  {
		String sql = "delete from 취업제외자외부 where 입력부서 =?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 취업제외자외부";
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

	public List<취업제외자외부> select취업제외자외부(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<취업제외자외부> list = new ArrayList<취업제외자외부>();

		String sql = "select * from 취업제외자외부 order by 연번 desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				취업제외자외부 취업제외자외부 = new 취업제외자외부();
				취업제외자외부.set연번(rs.getInt("연번"));
				취업제외자외부.set대학명(rs.getString("대학명"));
				취업제외자외부.set학과명(rs.getString("학과명"));
				취업제외자외부.set계(rs.getInt("계"));
				취업제외자외부.set진학자(rs.getInt("진학자"));
				취업제외자외부.set입대자(rs.getInt("입대자"));
				취업제외자외부.set취업불가능자(rs.getInt("취업불가능자"));
				취업제외자외부.set외국인유학생(rs.getInt("외국인유학생"));
				취업제외자외부.set건강보험직장가입제외대상(rs.getInt("건강보험직장가입제외대상"));
				취업제외자외부.set입학당시기취업자(rs.getInt("입학당시기취업자"));
				취업제외자외부.set비고(rs.getString("비고"));
				취업제외자외부.set입력부서(rs.getString("입력부서"));


				list.add(취업제외자외부);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
