package 취업현황외부;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class 취업현황외부Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;
	
		public boolean update(취업현황외부 취업현황외부) {
			String sql = "update 취업현황외부 set 대학명=?,학과명=?,졸업자=?,2차유지취업률=? where 연번=?;";
			try {

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, 취업현황외부.get대학명());
				pstmt.setString(2, 취업현황외부.get학과명());
				pstmt.setInt(3, 취업현황외부.get졸업자());
				pstmt.setFloat(4, 취업현황외부.get이차유지취업률());
				pstmt.setInt(5, 취업현황외부.get연번());

				pstmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

		}

		public boolean insert(취업현황외부 취업현황외부) {
			String sql = "insert into 취업현황외부(대학명,학과명,졸업자,2차유지취업률,입력부서) values(?,?,?,?,?);";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, 취업현황외부.get대학명());
				pstmt.setString(2, 취업현황외부.get학과명());
				pstmt.setInt(3, 취업현황외부.get졸업자());
				pstmt.setFloat(4, 취업현황외부.get이차유지취업률());
				pstmt.setString(5, 취업현황외부.get입력부서());
				pstmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	public void delete(int 연번) {
		String sql = "delete from 취업현황외부 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = " delete from 취업현황외부 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 취업현황외부";
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

	public List<취업현황외부> select취업현황외부(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<취업현황외부> list = new ArrayList<취업현황외부>();
		
		String sql = "select * from 취업현황외부 order by 연번 desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				취업현황외부 취업현황외부 = new 취업현황외부();
				취업현황외부.set대학명(rs.getString("대학명"));
				취업현황외부.set학과명(rs.getString("학과명"));
				취업현황외부.set이차유지취업률(rs.getFloat("2차유지취업률"));
				취업현황외부.set졸업자(rs.getInt("졸업자"));
				취업현황외부.setT점수(rs.getFloat("T점수"));
				취업현황외부.set취업률(rs.getFloat("취업률"));
				취업현황외부.set연번(rs.getInt("연번"));
				취업현황외부.set입력부서(rs.getString("입력부서"));
				취업현황외부.set비고(rs.getString("비고"));

				list.add(취업현황외부);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public float 취업자(String 대학명) {
		String sql = "select 취업자.계-입학당시기취업자 계 from 취업자외부 취업자 join 취업제외자외부 취업제외자 on 취업자.학과명=취업제외자.학과명  where 취업자.대학명=?;";
		float _12월취업자 = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 대학명);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				_12월취업자 = rs.getFloat("계");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _12월취업자;
	}

	public float 취업제외자(String 대학명) {
		String sql = "select 취업제외자.계-입학당시기취업자 계 from 취업현황외부 취업현황 join 취업제외자외부 취업제외자 on 취업현황.학과명=취업제외자.학과명  where 취업현황.대학명=?;";
		float _12월취업제외자 = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 대학명);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				_12월취업제외자 = rs.getFloat("계");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _12월취업제외자;
	}

	public int 이차유지취업률(String 대학명) {
		String sql = " select 2차유지취업률  from 취업현황외부  where 대학명=?;";
		int 이차유지취업률 = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 대학명);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				이차유지취업률 = rs.getInt("2차유지취업률");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 이차유지취업률;
	}


	public boolean 취업률update(float 취업률계산, String 수정대학명) {
		String sql = "update 취업현황외부 set 취업률=? where 대학명=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, 취업률계산);
			pstmt.setString(2, 수정대학명);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	public float 취업률외부( String 대학명) {
		String sql = "select 취업률 from 취업현황외부  where 대학명=? ";
		float 취업률 = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 대학명);
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
