package 강의담당상세;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 강의담당상세.강의담당상세;
public class 강의담당상세Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(강의담당상세 강의담당) {
		String sql = "update 교원강의담당비율 set 년도=?,학과명=?,학기=?,구분=? ,성명=?,과목=?,학점=?,비고=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 강의담당.get년도());
			pstmt.setString(2, 강의담당.get학과명());
			pstmt.setString(3, 강의담당.get학기());
			pstmt.setString(4, 강의담당.get구분());
			pstmt.setString(5, 강의담당.get성명());
			pstmt.setString(6, 강의담당.get과목());
			pstmt.setFloat(7, 강의담당.get학점());
			pstmt.setString(8, 강의담당.get비고());
			pstmt.setInt(9, 강의담당.get연번());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(강의담당상세 강의담당) {
		String sql = "insert into 교원강의담당비율(년도,학과명,학기,구분,성명,과목,학점,비고,입력부서) values(?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 강의담당.get년도());
			pstmt.setString(2, 강의담당.get학과명());
			pstmt.setString(3, 강의담당.get학기());
			pstmt.setString(4, 강의담당.get구분());
			pstmt.setString(5, 강의담당.get성명());
			pstmt.setString(6, 강의담당.get과목());
			pstmt.setFloat(7, 강의담당.get학점());
			pstmt.setString(8, 강의담당.get비고());
			pstmt.setString(9, 강의담당.get입력부서());
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 교원강의담당비율 where 연번=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void dellAll(String 입력부서) {
		String sql = "delete from 교원강의담당비율 where 입력부서 =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public int getCountRow() {
		String sql = "select count(*) from 교원강의담당비율";
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

	
	public List<강의담당상세> select강의담당(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<강의담당상세> list = new ArrayList<강의담당상세>();

		String sql = "select * from 교원강의담당비율 order by 연번 desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				강의담당상세 강의담당 = new 강의담당상세();
				강의담당.set연번(rs.getInt("연번"));
				강의담당.set년도(rs.getInt("년도"));
				강의담당.set학과명(rs.getString("학과명"));
				강의담당.set과목(rs.getString("과목"));
				강의담당.set구분(rs.getString("구분"));
				강의담당.set비고(rs.getString("비고"));
				강의담당.set성명(rs.getString("성명"));
				강의담당.set학기(rs.getString("학기"));
				강의담당.set학점(rs.getFloat("학점"));
				강의담당.set입력부서(rs.getString("입력부서"));
				list.add(강의담당);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public float 강의담당(int 년도, String 학과명) {
		float 강의담당 = 0;

		String sql = "select 강의담당비율 from 개설강의담당비율 where 년도=? and 학과명=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				강의담당 = rs.getFloat("강의담당비율");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 강의담당;
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
		String sql = "select sum(학점) 교양필수 from 교원강의담당비율 where 년도=? and 학과명=? and 구분='교양 필수';";
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

	public float 전공과목(int 년도, String 학과명) {
		float 전공과목 = 0;
		String sql = "select 개설전공과목 from 개설강의담당비율 where 년도=? and 학과명=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				전공과목 = rs.getFloat("개설전공과목");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 전공과목;
	}

	public float 자유선택(int 년도, String 학과명) {
		float 자유선택 = 0;
		String sql = "select 개설자유선택과목 from 개설강의담당비율 where 년도=? and 학과명=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				자유선택 = rs.getFloat("개설자유선택과목");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 자유선택;
	}

	public void update강의담당(float 강의담당, int 년도, String 학과명) {
		String sql = "update 개설강의담당비율 set 강의담당비율=? where 년도=? and 학과명=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, 강의담당);
			pstmt.setInt(2, 년도);
			pstmt.setString(3, 학과명);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}