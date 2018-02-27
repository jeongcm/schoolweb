package 동아리;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 동아리.동아리;

public class 동아리Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(동아리 동아리) {
		String sql = "update 동아리 set 년도=?,관리부서=?,학과명=?,동아리명=?,구분=?,지도교수=?,학생대표학년=?,학생대표이름=?,회원수=?,인정여부=?,예산지원액=?,실적서류=?,비고=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 동아리.get년도());
			pstmt.setString(2, 동아리.get관리부서());
			pstmt.setString(3, 동아리.get학과명());
			pstmt.setString(4, 동아리.get동아리명());
			pstmt.setString(5, 동아리.get구분());
			pstmt.setString(6, 동아리.get지도교수());
			pstmt.setString(7, 동아리.get학생대표학년());
			pstmt.setString(8, 동아리.get학생대표이름());
			pstmt.setInt(9, 동아리.get회원수());
			pstmt.setString(10, 동아리.get인정여부());
			pstmt.setInt(11, 동아리.get예산지원액());
			pstmt.setString(12, 동아리.get실적서류());
			pstmt.setString(13, 동아리.get비고());
			pstmt.setInt(14, 동아리.get연번());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(동아리 동아리) {
		String sql = "insert into 동아리(년도,관리부서,학과명,동아리명,구분,지도교수,학생대표학년,학생대표이름,회원수,인정여부,예산지원액,실적서류,비고,입력부서) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 동아리.get년도());
			pstmt.setString(2, 동아리.get관리부서());
			pstmt.setString(3, 동아리.get학과명());
			pstmt.setString(4, 동아리.get동아리명());
			pstmt.setString(5, 동아리.get구분());
			pstmt.setString(6, 동아리.get지도교수());
			pstmt.setString(7, 동아리.get학생대표학년());
			pstmt.setString(8, 동아리.get학생대표이름());
			pstmt.setInt(9, 동아리.get회원수());
			pstmt.setString(10, 동아리.get인정여부());
			pstmt.setInt(11, 동아리.get예산지원액());
			pstmt.setString(12, 동아리.get실적서류());
			pstmt.setString(13, 동아리.get비고());
			pstmt.setString(14, 동아리.get입력부서());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {

		String sql = "delete from 동아리 where 연번=? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 동아리 where 입력부서=?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 동아리";
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

	public List<동아리> select동아리(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<동아리> list = new ArrayList<동아리>();

		String sql = "select * from 동아리 order by 연번 desc limit ?,?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				동아리 동아리 = new 동아리();
				동아리.set년도(rs.getInt("년도"));
				동아리.set관리부서(rs.getString("관리부서"));
				동아리.set학과명(rs.getString("학과명"));
				동아리.set동아리명(rs.getString("동아리명"));
				동아리.set구분(rs.getString("구분"));
				동아리.set지도교수(rs.getString("지도교수"));
				동아리.set학생대표학년(rs.getString("학생대표학년"));
				동아리.set학생대표이름(rs.getString("학생대표이름"));
				동아리.set회원수(rs.getInt("회원수"));
				동아리.set인정여부(rs.getString("인정여부"));
				동아리.set실적서류(rs.getString("실적서류"));
				동아리.set예산지원액(rs.getInt("예산지원액"));
				동아리.set비고(rs.getString("비고"));
				동아리.set연번(rs.getInt("연번"));
				동아리.set입력부서(rs.getString("입력부서"));

				list.add(동아리);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int 계(int 수정년도, String 수정학과명) {
		int 계 = 0;
		String sql = "select sum(회원수) 계 from 동아리 where 년도=? and 학과명=?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 수정년도);
			pstmt.setString(2, 수정학과명);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				계 = rs.getInt("계");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 계;
	}

	public void update동아리참여비율(int 수정년도, String 수정학과명, float 동아리참여비율계산) {
		String sql = "update 동아리참여비율 set 동아리참여비율=? where 학과명=? and 년도=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, 동아리참여비율계산);
			pstmt.setString(2, 수정학과명);
			pstmt.setInt(3, 수정년도);
			
		
			pstmt.executeUpdate();
			
			System.out.println(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void 동아리참여비율insert(int 수정년도, String 수정학과명) {
		String sql = "insert into 동아리참여비율(학과명,년도) values(?,?);";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 수정학과명);
			pstmt.setInt(2, 수정년도);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}