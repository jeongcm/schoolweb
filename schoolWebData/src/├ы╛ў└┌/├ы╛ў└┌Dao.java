package 취업자;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 취업자.취업자;

public class 취업자Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(취업자 취업자) {
		String sql = "update 취업자 set 년도=?,학과명=?,건강보험db연계취업자=?,해외취업자=?,영농업취업자=?,취업인정자=?,계=?,개인창작활동조사서=?,1인창업자=?,프리랜서=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 취업자.get년도());
			pstmt.setString(2, 취업자.get학과명());
			pstmt.setInt(3, 취업자.get건강보험DB연계취업자());
			pstmt.setInt(4, 취업자.get해외취업자());
			pstmt.setInt(5, 취업자.get영농업취업자());
			pstmt.setInt(6, 취업자.get취업인정자());
			pstmt.setInt(7, 취업자.get계());
			pstmt.setInt(8, 취업자.get개인창작활동조사서());
			pstmt.setInt(9, 취업자.get일인창업자());
			pstmt.setInt(10, 취업자.get프리랜서());
			pstmt.setInt(11, 취업자.get연번());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(취업자 취업자) {
		String sql = "insert into 취업자(년도,학과명,건강보험db연계취업자,해외취업자,영농업취업자,취업인정자,계,입력부서,개인창작활동조사서,1인창업자,프리랜서) values(?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 취업자.get년도());
			pstmt.setString(2, 취업자.get학과명());
			pstmt.setInt(3, 취업자.get건강보험DB연계취업자());
			pstmt.setInt(4, 취업자.get해외취업자());
			pstmt.setInt(5, 취업자.get영농업취업자());
			pstmt.setInt(6, 취업자.get취업인정자());
			pstmt.setInt(7, 취업자.get계());
			pstmt.setString(8, 취업자.get입력부서());
			pstmt.setInt(9, 취업자.get개인창작활동조사서());
			pstmt.setInt(10, 취업자.get일인창업자());
			pstmt.setInt(11, 취업자.get프리랜서());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 취업자 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 취업자 where 입력부서 =?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 취업자";
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

	public List<취업자> select취업자(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<취업자> list = new ArrayList<취업자>();

		String sql = "select * from 취업자 order by 연번 desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				취업자 취업자 = new 취업자();
				취업자.set년도(rs.getInt("년도"));
				취업자.set학과명(rs.getString("학과명"));
				취업자.set계(rs.getInt("계"));
				취업자.set건강보험DB연계취업자(rs.getInt("건강보험DB연계취업자"));
				취업자.set해외취업자(rs.getInt("해외취업자"));
				취업자.set영농업취업자(rs.getInt("영농업취업자"));
				취업자.set취업인정자(rs.getInt("취업인정자"));
				취업자.set연번(rs.getInt("연번"));
				취업자.set입력부서(rs.getString("입력부서"));
				취업자.set개인창작활동조사서(rs.getInt("개인창작활동조사서"));
				취업자.set일인창업자(rs.getInt("1인창업자"));
				취업자.set프리랜서(rs.getInt("프리랜서"));
				
				list.add(취업자);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
