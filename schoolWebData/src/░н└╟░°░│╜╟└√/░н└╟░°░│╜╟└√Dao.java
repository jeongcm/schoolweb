package 강의공개실적;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnection;
import 강의공개실적.강의공개실적;

public class 강의공개실적Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;

	private ResultSet rs;

	public boolean update(강의공개실적 강의공개실적) {
		String sql = "update 강의공개실적 set 년도=?,학과명=?,강의동영상B=?,이러닝강의C=?,강의자료D=?,강의동영상E=?,이러닝강의F=?,강의자료G=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 강의공개실적.get년도());
			pstmt.setString(2, 강의공개실적.get학과명());
			pstmt.setInt(3, 강의공개실적.get강의동영상B());
			pstmt.setInt(4, 강의공개실적.get이러닝강의C());
			pstmt.setInt(5, 강의공개실적.get강의자료D());
			pstmt.setInt(6, 강의공개실적.get강의동영상E());
			pstmt.setInt(7, 강의공개실적.get이러닝강의F());
			pstmt.setInt(8, 강의공개실적.get강의자료G());
			pstmt.setInt(9, 강의공개실적.get연번());

			pstmt.executeUpdate();

			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(강의공개실적 강의공개실적) {
		String sql = "insert into 강의공개실적(년도,학과명,강의동영상B,이러닝강의C,강의자료D,강의동영상E,이러닝강의F,강의자료G,강의공개실적,입력부서) values(?,?,?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 강의공개실적.get년도());
			pstmt.setString(2, 강의공개실적.get학과명());
			pstmt.setInt(3, 강의공개실적.get강의동영상B());
			pstmt.setInt(4, 강의공개실적.get이러닝강의C());
			pstmt.setInt(5, 강의공개실적.get강의자료D());
			pstmt.setInt(6, 강의공개실적.get강의동영상E());
			pstmt.setInt(7, 강의공개실적.get이러닝강의F());
			pstmt.setInt(8, 강의공개실적.get강의자료G());
			pstmt.setFloat(9, 강의공개실적.get강의공개실적계산());
			pstmt.setString(10, 강의공개실적.get입력부서());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 강의공개실적 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 강의공개실적 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 강의공개실적";
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

	public List<강의공개실적> select강의공개실적(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<강의공개실적> list = new ArrayList<강의공개실적>();

		String sql = "select * from 강의공개실적 order by 연번 desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				강의공개실적 강의공개실적 = new 강의공개실적();

				강의공개실적.set학과명(rs.getString("학과명"));
				강의공개실적.set강의동영상B(rs.getInt("강의동영상B"));
				강의공개실적.set이러닝강의C(rs.getInt("이러닝강의C"));
				강의공개실적.set강의자료D(rs.getInt("강의자료D"));
				강의공개실적.set강의동영상E(rs.getInt("강의동영상E"));
				강의공개실적.set이러닝강의F(rs.getInt("이러닝강의F"));
				강의공개실적.set강의자료G(rs.getInt("강의자료G"));
				강의공개실적.set연번(rs.getInt("연번"));
				강의공개실적.set입력부서(rs.getString("입력부서"));
				강의공개실적.set년도(rs.getInt("년도"));

				list.add(강의공개실적);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}