package 교외장학금;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import 교외장학금.교외장학금;

public class 교외장학금Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	
	private ResultSet rs;

	public boolean update(교외장학금 교외장학금) {
		String sql = "update 교외장학금 set 년도=?,학과명=?,교외장학금=?,1인당교외장학금=?  where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 교외장학금.get년도());
			pstmt.setString(2, 교외장학금.get학과명());
			pstmt.setInt(3, 교외장학금.get교외장학금());
			pstmt.setFloat(4, 교외장학금.get일인당교외장학금());
			pstmt.setInt(5, 교외장학금.get연번());

			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(교외장학금 교외장학금) {
		String sql = "insert into 교외장학금(년도,학과명,교외장학금,1인당교외장학금,입력부서) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 교외장학금.get년도());
			pstmt.setString(2, 교외장학금.get학과명());
			pstmt.setInt(3, 교외장학금.get교외장학금());
			pstmt.setFloat(4, 교외장학금.get일인당교외장학금());
			pstmt.setString(5, 교외장학금.get입력부서());
			
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 교외장학금 where 연번=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 교외장학금 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 교외장학금";
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

	public List<교외장학금> select교외장학금(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<교외장학금> list = new ArrayList<교외장학금>();

		String sql = "select * from 교외장학금 order by 연번 desc limit ?,? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				교외장학금 교외장학금 = new 교외장학금();
				
				교외장학금.set년도(rs.getInt("년도"));
				교외장학금.set학과명(rs.getString("학과명"));
				교외장학금.set교외장학금(rs.getInt("교외장학금"));
				교외장학금.set연번(rs.getInt("연번"));
				교외장학금.set입력부서(rs.getString("입력부서"));
				
				list.add(교외장학금);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public float 재학생평균(int 년도, String 학과명) {
		float 재학생평균 = 0;
		String sql = "select avg(계) 재학생평균  from 재학생현황  where 년도=? and 학과명=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				재학생평균 = rs.getFloat("재학생평균");
			}

			return 재학생평균;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public float 일인당교외장학금(int 년도, String 학과명) {
		String sql = "select 1인당교외장학금 from 교외장학금 where 년도=? and 학과명=?;";
		float 일인당교외장학금 = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				일인당교외장학금 = rs.getInt("1인당교외장학금");
			}
			return 일인당교외장학금;

		} catch (SQLException e) {
			return 0;
		}
	}

}