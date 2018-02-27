package 발전기금조성액상세자료;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 발전기금조성액상세자료Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(발전기금조성액상세자료 발전기금조성액상세자료) {
		String sql = "update 발전기금조성액상세자료 set 년도=?,학과명=?,구분=?,금액=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 발전기금조성액상세자료.get년도());
			pstmt.setString(2, 발전기금조성액상세자료.get학과명());
			pstmt.setString(3, 발전기금조성액상세자료.get구분());
			pstmt.setInt(4, 발전기금조성액상세자료.get금액());
			pstmt.setInt(5, 발전기금조성액상세자료.get연번());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insert(발전기금조성액상세자료 발전기금조성액상세자료) {
		String sql = "insert into 발전기금조성액상세자료(년도,학과명,구분,금액,입력부서) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 발전기금조성액상세자료.get년도());
			pstmt.setString(2, 발전기금조성액상세자료.get학과명());
			pstmt.setString(3, 발전기금조성액상세자료.get구분());
			pstmt.setInt(4, 발전기금조성액상세자료.get금액());
			pstmt.setString(5, 발전기금조성액상세자료.get입력부서());
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 발전기금조성액상세자료 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 발전기금조성액상세자료 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 발전기금조성액상세자료";
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

	public List<발전기금조성액상세자료> select발전기금조성액상세자료(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<발전기금조성액상세자료> list = new ArrayList<발전기금조성액상세자료>();

		String sql = "select * from 발전기금조성액상세자료 order by 연번 desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				발전기금조성액상세자료 발전기금조성액상세자료 = new 발전기금조성액상세자료();
				발전기금조성액상세자료.set년도(rs.getInt("년도"));
				발전기금조성액상세자료.set학과명(rs.getString("학과명"));
				발전기금조성액상세자료.set구분(rs.getString("구분"));
				발전기금조성액상세자료.set금액(rs.getInt("금액"));
				발전기금조성액상세자료.set연번(rs.getInt("연번"));
				발전기금조성액상세자료.set입력부서(rs.getString("입력부서"));
				

				list.add(발전기금조성액상세자료);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public int 지정기부금(int 년도, String 학과명) {
		int 지정기부금 = 0;

		String sql = "select 지정기부금 from 발전기금조성액 where 년도=? and 학과명=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				지정기부금 = rs.getInt("지정기부금");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 지정기부금;
	}

	public Float 발전기금조성액(int 년도, String 학과명) {
		float 발전기금조성액 = 0;

		String sql = "select 발전기금조성액 from 발전기금조성액 where 년도=? and 학과명=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				발전기금조성액 = rs.getFloat("발전기금조성액");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 발전기금조성액;
	}

	public void update발전기금(int 년도, String 학과명, float 발전기금조성액) {
		String sql = "update 발전기금조성액 set 발전기금조성액=? where 년도=? and 학과명=?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, 발전기금조성액);
			pstmt.setInt(2, 년도);
			pstmt.setString(3, 학과명);
			pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int 발전기금모금액(int 년도, String 학과명) {
		String sql = "select sum(금액) div 1000 발전기금모금액 from 발전기금조성액상세자료  where 학과명=? and 년도=?;";
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


}
