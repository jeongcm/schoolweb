package 연구실적외부;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import 연구실적외부.연구실적외부;

public class 연구실적외부Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	
	private ResultSet rs;

	public boolean update(연구실적외부 연구실적외부) {
		String sql = "update 연구실적외부 set 대학명=?,학과명=?,저서=?,역서=?,연구재단등재지=?,연구재단등재후보=?,SCI급=?,SCOPUS학술지=?,연구실적=? ,비고=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, 연구실적외부.get대학명());
			pstmt.setString(2, 연구실적외부.get학과명());
			pstmt.setFloat(3, 연구실적외부.get저서());
			pstmt.setFloat(4, 연구실적외부.get역서());
			pstmt.setFloat(5, 연구실적외부.get연구재단등재지());
			pstmt.setFloat(6, 연구실적외부.get연구재단등재후보());
			pstmt.setFloat(7, 연구실적외부.getSCI급());
			pstmt.setFloat(8, 연구실적외부.getSCOPUS학술지());
			pstmt.setFloat(9, 연구실적외부.get연구실적());
			pstmt.setString(10, 연구실적외부.get비고());
			pstmt.setInt(11, 연구실적외부.get연번());
			
			pstmt.executeUpdate();

			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(연구실적외부 연구실적외부) {
		String sql = "insert into 연구실적외부(대학명,학과명,저서,역서,연구재단등재지,연구재단등재후보,SCI급,SCOPUS학술지,연구실적,입력부서,비고) values(?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 연구실적외부.get대학명());
			pstmt.setString(2, 연구실적외부.get학과명());
			pstmt.setFloat(3, 연구실적외부.get저서());
			pstmt.setFloat(4, 연구실적외부.get역서());
			pstmt.setFloat(5, 연구실적외부.get연구재단등재지());
			pstmt.setFloat(6, 연구실적외부.get연구재단등재후보());
			pstmt.setFloat(7, 연구실적외부.getSCI급());
			pstmt.setFloat(8, 연구실적외부.getSCOPUS학술지());
			pstmt.setFloat(9, 연구실적외부.get연구실적());
			pstmt.setString(10, 연구실적외부.get입력부서());
			pstmt.setString(11, 연구실적외부.get비고());
			
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 연구실적외부 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 연구실적외부 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 연구실적외부";
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

	public List<연구실적외부> select연구실적외부(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<연구실적외부> list = new ArrayList<연구실적외부>();

		String sql = "select * from 연구실적외부 order by 연번 desc limit ?,? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				연구실적외부 연구실적외부 = new 연구실적외부();
				
				연구실적외부.set연번(rs.getInt("연번"));
				연구실적외부.set대학명(rs.getString("대학명"));
				연구실적외부.set학과명(rs.getString("학과명"));
				연구실적외부.setSCI급(rs.getFloat("SCI급"));
				연구실적외부.setSCOPUS학술지(rs.getFloat("SCOPUS학술지"));
				연구실적외부.setT점수(rs.getFloat("T점수"));
				연구실적외부.set연구재단등재지(rs.getFloat("연구재단등재지"));
				연구실적외부.set연구재단등재후보(rs.getFloat("연구재단등재후보"));
				연구실적외부.set역서(rs.getFloat("역서"));
				연구실적외부.set저서(rs.getFloat("저서"));
				연구실적외부.set입력부서(rs.getString("입력부서"));
				연구실적외부.set비고(rs.getString("비고"));

				list.add(연구실적외부);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public float 연구실적외부(int 대학명, String 학과명) {
		float 연구실적외부 = 0;

		String sql = "select 연구실적외부 from 연구실적외부 where 대학명=? and 학과명=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 대학명);
			pstmt.setString(2, 학과명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				연구실적외부 = rs.getFloat("연구실적외부");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 연구실적외부;
	}
}