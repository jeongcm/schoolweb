package 취업현황;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 취업현황Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(취업현황 취업현황) {
		String sql = "update 취업현황 set 년도=?,학과명=?,졸업자=?,2차유지취업률=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 취업현황.get년도());
			pstmt.setString(2, 취업현황.get학과명());
			pstmt.setInt(3, 취업현황.get졸업자());
			pstmt.setFloat(4, 취업현황.get이차유지취업률());
			pstmt.setInt(5, 취업현황.get연번());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(취업현황 취업현황) {
		String sql = "insert into 취업현황(년도,학과명,졸업자,2차유지취업률,입력부서) values(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 취업현황.get년도());
			pstmt.setString(2, 취업현황.get학과명());
			pstmt.setInt(3, 취업현황.get졸업자());
			pstmt.setFloat(4, 취업현황.get이차유지취업률());
			pstmt.setString(5, 취업현황.get입력부서());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 취업현황 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 취업현황 where 입력부서 =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 취업현황";
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

	public List<취업현황> select취업현황(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<취업현황> list = new ArrayList<취업현황>();

		String sql = "select 년도,학과명,2차유지취업률,졸업자,T점수,연번,입력부서,round(취업률,2) 취업률 from 취업현황 order by 연번 desc limit ?,? ;"; //화면에 반올림해서 보여주기위해 이렇게 함
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				취업현황 취업현황 = new 취업현황();
				취업현황.set년도(rs.getInt("년도"));
				취업현황.set학과명(rs.getString("학과명"));
				취업현황.set이차유지취업률(rs.getFloat("2차유지취업률"));
				취업현황.set졸업자(rs.getInt("졸업자"));
				취업현황.setT점수(rs.getFloat("T점수"));
				취업현황.set취업률(rs.getFloat("취업률"));
				취업현황.set연번(rs.getInt("연번"));
				취업현황.set입력부서(rs.getString("입력부서"));

				list.add(취업현황);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean 취업률update(float 취업률, String 학과명, int 년도) {
		String sql = "update 취업현황 set 취업률=? where 년도=? and 학과명=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, 취업률);
			pstmt.setInt(2, 년도);
			pstmt.setString(3, 학과명);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public int 취업자(String 학과명, int 년도) {
		String sql = "select 취업자.계-입학당시기취업자 계 from 취업자 join 취업제외자 on 취업자.학과명=취업제외자.학과명  where 취업자.학과명=? and 취업자.년도=?;";
		int 취업자 = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 학과명);
			pstmt.setInt(2, 년도);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				취업자 = rs.getInt("계");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 취업자;
	}

	public int 취업제외자(String 학과명, int 년도) {
		String sql = "select 취업제외자.계-입학당시기취업자 계 from 취업현황 join 취업제외자 on 취업현황.학과명=취업제외자.학과명  where 취업현황.학과명=? and 취업현황.년도=?;";
		int 취업제외자 = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 학과명);
			pstmt.setInt(2, 년도);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				취업제외자 = rs.getInt("계");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 취업제외자;
	}

	public float 이차유지취업률(String 학과명, int 년도) {
		String sql = " select 2차유지취업률  from 취업현황  where 학과명=? and 년도=?;";
		float 이차유지취업률 = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 학과명);
			pstmt.setInt(2, 년도);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				이차유지취업률 = rs.getFloat("2차유지취업률");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 이차유지취업률;
	}

	public float 취업률(int 년도, String 학과명) {
		String sql = "select 취업률 from 취업현황  where 년도=? and 학과명=?; ";
		float 취업률 = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				취업률 = rs.getFloat("취업률");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 취업률;
	}


}