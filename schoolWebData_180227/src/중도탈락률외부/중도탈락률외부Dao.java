package 중도탈락률외부;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;

public class 중도탈락률외부Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(중도탈락률외부 중도탈락률외부) {
		String sql = "update 중도탈락률외부 set 재적학생수=?,미등록=?,미복학=?,자퇴=?,학사경고=?,기타=?,계=?,중도탈락률=?,T점수=?,대학명=?,학과명=?,비고=?,타학과전과자=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 중도탈락률외부.get재적학생수());
			pstmt.setInt(2, 중도탈락률외부.get미등록());
			pstmt.setInt(3, 중도탈락률외부.get미복학());
			pstmt.setInt(4, 중도탈락률외부.get자퇴());
			pstmt.setInt(5, 중도탈락률외부.get학사경고());
			pstmt.setInt(6, 중도탈락률외부.get기타());
			pstmt.setInt(7, 중도탈락률외부.get계());
			pstmt.setFloat(8, 중도탈락률외부.get중도탈락률());
			pstmt.setFloat(9, 중도탈락률외부.getT점수());
			pstmt.setString(10, 중도탈락률외부.get대학명());
			pstmt.setString(11, 중도탈락률외부.get학과명());
			pstmt.setString(12, 중도탈락률외부.get비고());
			pstmt.setInt(13, 중도탈락률외부.get타학과전과자());
			pstmt.setInt(14, 중도탈락률외부.get연번());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(중도탈락률외부 중도탈락률외부) {
		String sql = "insert into 중도탈락률외부(대학명,학과명,재적학생수,미등록,미복학,자퇴,학사경고,기타,계,중도탈락률,비고,타학과전과자,입력부서) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 중도탈락률외부.get대학명());
			pstmt.setString(2, 중도탈락률외부.get학과명());
			pstmt.setInt(3, 중도탈락률외부.get재적학생수());
			pstmt.setInt(4, 중도탈락률외부.get미등록());
			pstmt.setInt(5, 중도탈락률외부.get미복학());
			pstmt.setInt(6, 중도탈락률외부.get자퇴());
			pstmt.setInt(7, 중도탈락률외부.get학사경고());
			pstmt.setInt(8, 중도탈락률외부.get기타());
			pstmt.setInt(9, 중도탈락률외부.get계());
			pstmt.setFloat(10, 중도탈락률외부.get중도탈락률());
			pstmt.setString(11, 중도탈락률외부.get비고());
			pstmt.setInt(12, 중도탈락률외부.get타학과전과자());
			pstmt.setString(13, 중도탈락률외부.get입력부서());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 중도탈락률외부 where 연번=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 중도탈락률외부 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 중도탈락률외부";
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

	public List<중도탈락률외부> select중도탈락률외부(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<중도탈락률외부> list = new ArrayList<중도탈락률외부>();

		String sql = "select * from 중도탈락률외부 order by 연번 desc limit ?,? ;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			// 결과물 편집, 리턴
			while (rs.next()) {
				중도탈락률외부 중도탈락률외부 = new 중도탈락률외부();
				중도탈락률외부.set연번(rs.getInt("연번"));
				중도탈락률외부.set대학명(rs.getString("대학명"));
				중도탈락률외부.set학과명(rs.getString("학과명"));
				중도탈락률외부.set계(rs.getInt("계"));
				중도탈락률외부.setT점수(rs.getFloat("T점수"));
				중도탈락률외부.set기타(rs.getInt("기타"));
				중도탈락률외부.set미등록(rs.getInt("미등록"));
				중도탈락률외부.set미복학(rs.getInt("미복학"));
				중도탈락률외부.set자퇴(rs.getInt("자퇴"));
				중도탈락률외부.set재적학생수(rs.getInt("재적학생수"));
				중도탈락률외부.set중도탈락률(rs.getFloat("중도탈락률"));
				중도탈락률외부.set기타(rs.getInt("기타"));
				중도탈락률외부.set학사경고(rs.getInt("학사경고"));
				중도탈락률외부.set비고(rs.getString("비고"));
				중도탈락률외부.set타학과전과자(rs.getInt("타학과전과자"));
				중도탈락률외부.set입력부서(rs.getString("입력부서"));
				
				list.add(중도탈락률외부);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public float 중도탈락률(String 대학명) {
		float 중도탈락률 = 0;

		String sql = "select 중도탈락률 from 중도탈락률외부 where 대학명=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 대학명);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				중도탈락률 = rs.getFloat("중도탈락률");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 중도탈락률;
	}

	public void updateT(float T점수, String 대학명) {

		String sql = "update 중도탈락률외부 set T점수=? where 대학명=? ;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, T점수);
			pstmt.setString(2, 대학명);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
