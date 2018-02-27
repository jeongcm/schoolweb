package 연구실적;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import 연구실적.연구실적;

public class 연구실적Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	
	private ResultSet rs;

	public boolean update(연구실적 연구실적) {
		String sql = "update 연구실적 set 년도=?,학과명=?,저서=?,역서=?,연구재단등재지=?,연구재단등재후보=?,SCI급=?,SCOPUS학술지=?,연구실적=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 연구실적.get년도());
			pstmt.setString(2, 연구실적.get학과명());
			pstmt.setFloat(3, 연구실적.get저서());
			pstmt.setFloat(4, 연구실적.get역서());
			pstmt.setFloat(5, 연구실적.get연구재단등재지());
			pstmt.setFloat(6, 연구실적.get연구재단등재후보());
			pstmt.setFloat(7, 연구실적.getSCI급());
			pstmt.setFloat(8, 연구실적.getSCOPUS학술지());
			pstmt.setFloat(9, 연구실적.get연구실적());
			pstmt.setInt(10, 연구실적.get연번());
			
			pstmt.executeUpdate();

			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(연구실적 연구실적) {
		String sql = "insert into 연구실적(년도,학과명,저서,역서,연구재단등재지,연구재단등재후보,SCI급,SCOPUS학술지,연구실적,입력부서) values(?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연구실적.get년도());
			pstmt.setString(2, 연구실적.get학과명());
			pstmt.setFloat(3, 연구실적.get저서());
			pstmt.setFloat(4, 연구실적.get역서());
			pstmt.setFloat(5, 연구실적.get연구재단등재지());
			pstmt.setFloat(6, 연구실적.get연구재단등재후보());
			pstmt.setFloat(7, 연구실적.getSCI급());
			pstmt.setFloat(8, 연구실적.getSCOPUS학술지());
			pstmt.setFloat(9, 연구실적.get연구실적());
			pstmt.setString(10, 연구실적.get입력부서());
			
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 연구실적 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 연구실적 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 연구실적";
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

	public List<연구실적> select연구실적(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<연구실적> list = new ArrayList<연구실적>();

		String sql = "select * from 연구실적 order by 연번 desc limit ?,? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				연구실적 연구실적 = new 연구실적();
				
				연구실적.set연번(rs.getInt("연번"));
				연구실적.set년도(rs.getInt("년도"));
				연구실적.set학과명(rs.getString("학과명"));
				연구실적.setSCI급(rs.getFloat("SCI급"));
				연구실적.setSCOPUS학술지(rs.getFloat("SCOPUS학술지"));
				연구실적.setT점수(rs.getFloat("T점수"));
				연구실적.set연구재단등재지(rs.getFloat("연구재단등재지"));
				연구실적.set연구재단등재후보(rs.getFloat("연구재단등재후보"));
				연구실적.set역서(rs.getFloat("역서"));
				연구실적.set저서(rs.getFloat("저서"));
				연구실적.set입력부서(rs.getString("입력부서"));

				list.add(연구실적);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String 학문계열(int 년도, String 학과명) {
		String sql = "select 학문계열1 학문계열 from 학과현황 where 학과현황.년도=? and 학과현황.학과명=?";
		String 학문계열 = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				학문계열 = rs.getString("학문계열");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 학문계열;
	}

	public float 연구실적(int 년도, String 학과명) {
		float 연구실적 = 0;

		String sql = "select 연구실적 from 연구실적 where 년도=? and 학과명=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				연구실적 = rs.getFloat("연구실적");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 연구실적;
	}
}