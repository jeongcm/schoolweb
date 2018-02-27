package 전임교원1인당국책사업수주실적;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import 전임교원1인당국책사업수주실적.전임교원1인당국책사업수주실적;

public class 전임교원1인당국책사업수주실적Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	
	private ResultSet rs;
	
	public boolean update(전임교원1인당국책사업수주실적 전임교원1인당국책사업수주실적) {
		String sql = "update 전임교원1인당국책사업수주실적 set 년도=?,학과명=?,대학명의국책사업수주총액=?,전임교원1인당국책사업수주총액=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 전임교원1인당국책사업수주실적.get년도());
			pstmt.setString(2, 전임교원1인당국책사업수주실적.get학과명());
			pstmt.setInt(3, 전임교원1인당국책사업수주실적.get대학명의국책사업수주총액());
			pstmt.setFloat(4, 전임교원1인당국책사업수주실적.get전임교원1인당국책사업수주총액());
			pstmt.setInt(5, 전임교원1인당국책사업수주실적.get연번());

			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean insert(전임교원1인당국책사업수주실적 전임교원1인당국책사업수주실적) {
		String sql = "insert into 전임교원1인당국책사업수주실적(년도,학과명,대학명의국책사업수주총액,전임교원1인당국책사업수주총액,입력부서) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 전임교원1인당국책사업수주실적.get년도());
			pstmt.setString(2, 전임교원1인당국책사업수주실적.get학과명());
			pstmt.setInt(3, 전임교원1인당국책사업수주실적.get대학명의국책사업수주총액());
			pstmt.setFloat(4, 전임교원1인당국책사업수주실적.get전임교원1인당국책사업수주총액());
			pstmt.setString(5, 전임교원1인당국책사업수주실적.get입력부서());
			
			System.out.print(pstmt);
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void delete(int 연번) {
		String sql = "delete from 전임교원1인당국책사업수주실적 where 연번=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 전임교원1인당국책사업수주실적 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getCountRow() {
		String sql = "select count(*) from 전임교원1인당국책사업수주실적";
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

	public List<전임교원1인당국책사업수주실적> select전임교원1인당국책사업수주실적(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<전임교원1인당국책사업수주실적> list = new ArrayList<전임교원1인당국책사업수주실적>();

		String sql = "select * from 전임교원1인당국책사업수주실적 order by 연번 desc limit ?,? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				전임교원1인당국책사업수주실적 전임교원1인당국책사업수주실적 = new 전임교원1인당국책사업수주실적();
				전임교원1인당국책사업수주실적.set년도(rs.getInt("년도"));
				전임교원1인당국책사업수주실적.set학과명(rs.getString("학과명"));
				전임교원1인당국책사업수주실적.setT점수(rs.getFloat("T점수"));
				전임교원1인당국책사업수주실적.set대학명의국책사업수주총액(rs.getInt("대학명의국책사업수주총액"));
				전임교원1인당국책사업수주실적.set전임교원1인당국책사업수주총액(rs.getFloat("전임교원1인당국책사업수주총액"));
				전임교원1인당국책사업수주실적.set연번(rs.getInt("연번"));
				전임교원1인당국책사업수주실적.set입력부서(rs.getString("입력부서"));

				list.add(전임교원1인당국책사업수주실적);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Float 전임교원1인당국책사업수주실적(int 년도, String 학과명) {
		String sql = "select 전임교원1인당국책사업수주실적 from 전임교원1인당국책사업수주총액 where 년도=? and 학과명=?";
		Float 전임교원1인당국책사업수주총액 = 0.0F;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				전임교원1인당국책사업수주총액 = rs.getFloat("전임교원1인당국책사업수주총액");
			}
		} catch (SQLException e) {
			return 0.0F;
		}
		return 전임교원1인당국책사업수주총액;
	}
}
