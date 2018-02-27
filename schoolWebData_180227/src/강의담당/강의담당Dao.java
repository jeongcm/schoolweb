package 강의담당;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 강의담당.강의담당;

public class 강의담당Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(강의담당 강의담당) {
		String sql = "update 개설강의담당비율 set 년도=?,학과명=?,개설전공과목=?,개설교양필수과목=?,개설자유선택과목=? ,강의담당비율=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 강의담당.get년도());
			pstmt.setString(2, 강의담당.get학과명());
			pstmt.setFloat(3, 강의담당.get전공과목());
			pstmt.setFloat(4, 강의담당.get교양필수과목());
			pstmt.setFloat(5, 강의담당.get자유선택과목());
			pstmt.setFloat(6, 강의담당.get강의담당비율());
			pstmt.setInt(7, 강의담당.get연번());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(강의담당 강의담당) {
		String sql = "insert into 개설강의담당비율(년도,학과명,개설전공과목,개설교양필수과목,개설자유선택과목,강의담당비율,입력부서) values(?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 강의담당.get년도());
			pstmt.setString(2, 강의담당.get학과명());
			pstmt.setFloat(3, 강의담당.get전공과목());
			pstmt.setFloat(4, 강의담당.get교양필수과목());
			pstmt.setFloat(5, 강의담당.get자유선택과목());
			pstmt.setFloat(6, 강의담당.get강의담당비율());
			pstmt.setString(7, 강의담당.get입력부서());
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 개설강의담당비율 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 개설강의담당비율 where 입력부서 =?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public int getCountRow() {
		String sql = "select count(*) from 개설강의담당비율";
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
	


	public List<강의담당> select강의담당(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<강의담당> list = new ArrayList<강의담당>();

		String sql = "select * from 개설강의담당비율 order by 연번 desc limit ?,? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				강의담당 강의담당 = new 강의담당();
				강의담당.set년도(rs.getInt("년도"));
				강의담당.set학과명(rs.getString("학과명"));
				강의담당.setT점수(rs.getFloat("T점수"));
				강의담당.set강의담당비율(rs.getFloat("강의담당비율"));
				강의담당.set자유선택과목(rs.getFloat("개설자유선택과목"));
				강의담당.set교양필수과목(rs.getFloat("개설교양필수과목"));
				강의담당.set전공과목(rs.getFloat("개설전공과목"));
				강의담당.set연번(rs.getInt("연번"));
				강의담당.set입력부서(rs.getString("입력부서"));
				list.add(강의담당);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public float 과목총계(int 년도, String 학과명) {
		float 과목총계 = 0;
		String sql = "select sum(학점) 총계 from 교원강의담당비율 where 년도=? and 학과명=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				과목총계 = rs.getFloat("총계");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 과목총계;
	}

	public float 교양필수과목(int 년도, String 학과명) {
		float 교양필수과목 = 0;
		String sql = "select sum(학점) 교양필수 from 교원강의담당비율 where 년도=? and 학과명=? and 구분='교양필수과목';";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				교양필수과목 = rs.getFloat("교양필수");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 교양필수과목;

	}

	
	
}