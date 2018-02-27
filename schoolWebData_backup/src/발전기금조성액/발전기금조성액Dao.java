package 발전기금조성액;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import 발전기금조성액.발전기금조성액;

public class 발전기금조성액Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	
	private ResultSet rs;

	public boolean update(발전기금조성액 발전기금조성액) {
		String sql = "update 발전기금조성액 set 년도=?,학과명=?,지정기부금=?,발전기금조성액=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 발전기금조성액.get년도());
			pstmt.setString(2, 발전기금조성액.get학과명());
			pstmt.setInt(3, 발전기금조성액.get지정기부금());
			pstmt.setFloat(4, 발전기금조성액.get발전기금조성액계산());
			pstmt.setInt(5, 발전기금조성액.get연번());

			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(발전기금조성액 발전기금조성액) {
		String sql = "insert into 발전기금조성액(년도,학과명,지정기부금,발전기금조성액,입력부서) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 발전기금조성액.get년도());
			pstmt.setString(2, 발전기금조성액.get학과명());
			pstmt.setInt(3, 발전기금조성액.get지정기부금());
			pstmt.setFloat(4, 발전기금조성액.get발전기금조성액계산());
			pstmt.setString(5, 발전기금조성액.get입력부서());
			
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 발전기금조성액 where 연번=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 발전기금조성액 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 발전기금조성액";
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

	public List<발전기금조성액> select발전기금조성액(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<발전기금조성액> list = new ArrayList<발전기금조성액>();

		String sql = "select * from 발전기금조성액 order by 연번 desc limit ?,? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				발전기금조성액 발전기금조성액 = new 발전기금조성액();
				발전기금조성액.set년도(rs.getInt("년도"));
				발전기금조성액.set학과명(rs.getString("학과명"));
				발전기금조성액.setT점수(rs.getFloat("T점수"));
				발전기금조성액.set지정기부금(rs.getInt("지정기부금"));
				발전기금조성액.set발전기금조성액계산(rs.getFloat("발전기금조성액"));
				발전기금조성액.set연번(rs.getInt("연번"));
				발전기금조성액.set입력부서(rs.getString("입력부서"));

				list.add(발전기금조성액);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int 발전기금모금액(int 년도, String 학과명) {
		String sql = "select sum(금액) 발전기금모금액 from 발전기금조성액상세자료  where 학과명=? and 년도=?;";
		int 발전기금모금액 = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, 학과명);
			pstmt.setInt(2, 년도);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				발전기금모금액 = rs.getInt("발전기금모금액");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 발전기금모금액;
	}
	
	public float 발전기금조성액(int 년도, String 학과명) {
		String sql = "select 발전기금조성액 from 발전기금조성액 where 년도=? and 학과명=?";
		float 발전기금조성액 = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				발전기금조성액 = rs.getFloat("발전기금조성액");
			}
		} catch (SQLException e) {
			return 0;
		}
		return 발전기금조성액;
	}
}