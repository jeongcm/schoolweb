package 전임교원확보율;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 전임교원확보율Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(전임교원확보율 전임교원확보율) {

		String sql = "update 전임교원확보율 set 년도=?,학과명=?,대학원생정원=?,대학원재학생=?,학생정원기준전임교원=?,재학생기준전임교원=?,전임교원확보율=?,인정학생정원=?,교원_학부_정원=?,교원_학부_재학생=?,교원_대학원_정원=?,교원_대학원_재학생=?,교원_계_정원=?,교원_계_재학생=?,학생수_정원=?,학생수_재학생=?,확보율_정원=?,확보율_재학생=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 전임교원확보율.get년도());
			pstmt.setString(2, 전임교원확보율.get학과명());
			pstmt.setInt(3, 전임교원확보율.get대학원생정원());
			pstmt.setInt(4, 전임교원확보율.get대학원재학생());
			pstmt.setInt(5, 전임교원확보율.get학생정원기준전임교원());
			pstmt.setInt(6, 전임교원확보율.get재학생기준전임교원());
			pstmt.setFloat(7, 전임교원확보율.get전임교원확보율());
			pstmt.setInt(8, 전임교원확보율.get인정학생정원());
			pstmt.setInt(9, 전임교원확보율.get교원_학부_정원());
			pstmt.setInt(10, 전임교원확보율.get교원_학부_재학생());
			pstmt.setInt(11, 전임교원확보율.get교원_대학원_정원());
			pstmt.setInt(12, 전임교원확보율.get교원_대학원_재학생());
			pstmt.setInt(13, 전임교원확보율.get교원_계_정원());
			pstmt.setInt(14, 전임교원확보율.get교원_계_재학생());
			pstmt.setFloat(15, 전임교원확보율.get학생수_정원());
			pstmt.setFloat(16, 전임교원확보율.get학생수_재학생());
			pstmt.setFloat(17, 전임교원확보율.get확보율_정원());
			pstmt.setFloat(18, 전임교원확보율.get확보율_재학생());
			pstmt.setFloat(19, 전임교원확보율.get연번());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(전임교원확보율 전임교원확보율) {
		String sql = "insert into 전임교원확보율(년도,학과명,대학원생정원,대학원재학생,학생정원기준전임교원,재학생기준전임교원,전임교원확보율,인정학생정원,교원_학부_정원,교원_학부_재학생,교원_대학원_정원,교원_대학원_재학생,교원_계_정원,교원_계_재학생,학생수_정원,학생수_재학생,확보율_정원,확보율_재학생,입력부서) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 전임교원확보율.get년도());
			pstmt.setString(2, 전임교원확보율.get학과명());
			pstmt.setInt(3, 전임교원확보율.get대학원생정원());
			pstmt.setInt(4, 전임교원확보율.get대학원재학생());
			pstmt.setInt(5, 전임교원확보율.get학생정원기준전임교원());
			pstmt.setInt(6, 전임교원확보율.get재학생기준전임교원());
			pstmt.setFloat(7, 전임교원확보율.get전임교원확보율());
			pstmt.setInt(8, 전임교원확보율.get인정학생정원());
			pstmt.setInt(9, 전임교원확보율.get교원_학부_정원());
			pstmt.setInt(10, 전임교원확보율.get교원_학부_재학생());
			pstmt.setInt(11, 전임교원확보율.get교원_대학원_정원());
			pstmt.setInt(12, 전임교원확보율.get교원_대학원_재학생());
			pstmt.setInt(13, 전임교원확보율.get교원_계_정원());
			pstmt.setInt(14, 전임교원확보율.get교원_계_재학생());
			pstmt.setFloat(15, 전임교원확보율.get학생수_정원());
			pstmt.setFloat(16, 전임교원확보율.get학생수_재학생());
			pstmt.setFloat(17, 전임교원확보율.get확보율_정원());
			pstmt.setFloat(18, 전임교원확보율.get확보율_재학생());
			pstmt.setString(19, 전임교원확보율.get입력부서());

			System.out.println(pstmt);

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public void delete(int 연번) {
		String sql = "delete from 전임교원확보율 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 전임교원확보율 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 전임교원확보율";
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

	public List<전임교원확보율> select전임교원확보율(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<전임교원확보율> list = new ArrayList<전임교원확보율>();

		String sql = "select 전임교원확보율.* from 전임교원확보율 order by 연번 desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				전임교원확보율 전임교원확보율 = new 전임교원확보율();
				전임교원확보율.set년도(rs.getInt("년도"));
				전임교원확보율.set연번(rs.getInt("연번"));
				전임교원확보율.set학과명(rs.getString("학과명"));
				전임교원확보율.set대학원생정원(rs.getInt("대학원생정원"));
				전임교원확보율.set대학원재학생(rs.getInt("대학원재학생"));
				전임교원확보율.set재학생기준전임교원(rs.getInt("재학생기준전임교원"));
				전임교원확보율.set학생정원기준전임교원(rs.getInt("학생정원기준전임교원"));
				전임교원확보율.set인정학생정원(rs.getInt("인정학생정원"));
				전임교원확보율.set교원_학부_정원(rs.getInt("교원_학부_정원"));
				전임교원확보율.set교원_학부_재학생(rs.getInt("교원_학부_재학생"));
				전임교원확보율.set교원_대학원_정원(rs.getInt("교원_대학원_정원"));
				전임교원확보율.set교원_대학원_재학생(rs.getInt("교원_대학원_재학생"));
				전임교원확보율.set교원_계_정원(rs.getInt("교원_계_정원"));
				전임교원확보율.set교원_계_재학생(rs.getInt("교원_계_재학생"));
				전임교원확보율.set학생수_정원(rs.getFloat("학생수_정원"));
				전임교원확보율.set학생수_재학생(rs.getFloat("학생수_재학생"));
				전임교원확보율.set입력부서(rs.getString("입력부서"));
				list.add(전임교원확보율);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public float 전임교원확보율(int 년도, String 학과명) {
		float 전임교원확보율 = 0;

		String sql = "select 전임교원확보율 from 전임교원확보율 where 년도=? and 학과명=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				전임교원확보율 = rs.getFloat("전임교원확보율");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 전임교원확보율;
	}

	public int 학생정원(int 년도, String 학과명) {
		int 학생정원 = 0;
		String sql = "select 학생정원 from 재학생현황 where 기준=04.01 and 년도=? and 학과명=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				학생정원 = rs.getInt("학생정원");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 학생정원;
	}

	public int 군휴학자(int 년도, String 학과명) {
		String sql = "select 신설연도 from 학과현황 where  년도=? and 학과명=?;";
		String 신설연도 = null;
		int 군휴학자 = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				신설연도 = rs.getString("신설연도");
			}
			if (신설연도 != null) {
				sql = "select 군휴학자 from 재학생현황 where  년도=? and 학과명=? and 기준='04.01';";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 년도);
					pstmt.setString(2, 학과명);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						군휴학자 = rs.getInt("군휴학자");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				return 0;
			}
			return 군휴학자;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int 재학생(int 년도, String 학과명) {
		String sql = "select 계 from 재학생현황 where 기준=04.01 and 년도=? and 학과명=?;";
		int 계 = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				계 = rs.getInt("계");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 계;
	}

	public String _5대계열(int 년도, String 학과명) {
		String _5대계열 = null;

		String sql = "select _5대계열 from 학과현황 where 년도=? and 학과명=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				_5대계열 = rs.getString("_5대계열");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _5대계열;
	}
}
