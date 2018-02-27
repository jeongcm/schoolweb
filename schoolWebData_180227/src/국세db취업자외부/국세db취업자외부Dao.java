package 국세db취업자외부;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class 국세db취업자외부Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(국세취업자외부 국세db취업자외부) {
		String sql = "update 국세db취업자외부 set 개인창작활동조사서=?,1인창업자=?,프리랜서=?,계=?,대학명=?,학과명=?,비고=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 국세db취업자외부.get개인창작활동조사서());
			pstmt.setInt(2, 국세db취업자외부.get일인창업자());
			pstmt.setInt(3, 국세db취업자외부.get프리랜서());
			pstmt.setInt(4, 국세db취업자외부.get계());
			pstmt.setString(5, 국세db취업자외부.get대학명());
			pstmt.setString(6, 국세db취업자외부.get학과명());
			pstmt.setString(7, 국세db취업자외부.get비고());
			pstmt.setInt(8, 국세db취업자외부.get연번());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(국세취업자외부 국세db취업자외부) {
		String sql = "insert into 국세db취업자외부(대학명,월,학과명,개인창작활동조사서,1인창업자,프리랜서,계,비고,입력부서) values(?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 국세db취업자외부.get대학명());
			pstmt.setString(2, 국세db취업자외부.get월());
			pstmt.setString(3, 국세db취업자외부.get학과명());
			pstmt.setInt(4, 국세db취업자외부.get개인창작활동조사서());
			pstmt.setInt(5, 국세db취업자외부.get일인창업자());
			pstmt.setInt(6, 국세db취업자외부.get프리랜서());
			pstmt.setInt(7, 국세db취업자외부.get계());
			pstmt.setString(8, 국세db취업자외부.get비고());
			pstmt.setString(9, 국세db취업자외부.get입력부서());
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 국세db취업자외부 where 연번=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 국세db취업자외부  where 입력부서 =?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 국세db취업자외부";
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

	// 전체 행의 데이터 리스트를 리턴하는 메서드
	public List<국세취업자외부> select국세db취업자외부(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<국세취업자외부> list = new ArrayList<국세취업자외부>();

		String sql = "select * from 국세db취업자외부 order by 연번 desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			// 결과물 편집, 리턴
			while (rs.next()) {
				국세취업자외부 국세db취업자외부 = new 국세취업자외부();
				국세db취업자외부.set연번(rs.getInt("연번"));
				국세db취업자외부.set대학명(rs.getString("대학명"));
				국세db취업자외부.set학과명(rs.getString("학과명"));
				국세db취업자외부.set계(rs.getInt("계"));
				국세db취업자외부.set월(rs.getString("월"));
				국세db취업자외부.set개인창작활동조사서(rs.getInt("개인창작활동조사서"));
				국세db취업자외부.set일인창업자(rs.getInt("1인창업자"));
				국세db취업자외부.set프리랜서(rs.getInt("프리랜서"));
				국세db취업자외부.set비고(rs.getString("비고"));
				국세db취업자외부.set입력부서(rs.getString("입력부서"));

				list.add(국세db취업자외부);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
