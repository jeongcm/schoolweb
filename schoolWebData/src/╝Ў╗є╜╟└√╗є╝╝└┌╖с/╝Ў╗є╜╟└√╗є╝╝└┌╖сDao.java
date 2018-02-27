package 수상실적상세자료;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 수상실적상세자료.수상실적상세자료;

public class 수상실적상세자료Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean update(수상실적상세자료 수상실적상세자료) {

		String sql = "update 수상실적 set 년도=?,학과명=?,구분=?,수상일자=?,대회명=?,수상내용=?,인정여부=?,수상대상자=? where 연번=?;";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 수상실적상세자료.get년도());
			pstmt.setString(2, 수상실적상세자료.get학과명());
			pstmt.setString(3, 수상실적상세자료.get구분());
			pstmt.setString(4, 수상실적상세자료.get수상일자());
			pstmt.setString(5, 수상실적상세자료.get대회명());
			pstmt.setString(6, 수상실적상세자료.get수상내용());
			pstmt.setString(7, 수상실적상세자료.get인정여부());
			pstmt.setString(8, 수상실적상세자료.get수상대상자());

			pstmt.setInt(9, 수상실적상세자료.get연번());
			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(수상실적상세자료 수상실적상세자료) {
		String sql = "insert into 수상실적(년도,학과명,구분,수상일자,대회명,수상내용,인정여부,수상대상자,입력부서) values(?,?,?,?,?,?,?,?,?);";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 수상실적상세자료.get년도());
			pstmt.setString(2, 수상실적상세자료.get학과명());
			pstmt.setString(3, 수상실적상세자료.get구분());
			pstmt.setString(4, 수상실적상세자료.get수상일자());
			pstmt.setString(5, 수상실적상세자료.get대회명());
			pstmt.setString(6, 수상실적상세자료.get수상내용());
			pstmt.setString(7, 수상실적상세자료.get인정여부());
			pstmt.setString(8, 수상실적상세자료.get수상대상자());
			pstmt.setString(9, 수상실적상세자료.get입력부서());

			pstmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 수상실적 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int 연번) {

		String sql = "delete from 수상실적 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public int getCountRow() {

		String sql = "select count(*) from 수상실적";
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

	public List<수상실적상세자료> select수상실적상세자료(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<수상실적상세자료> list = new ArrayList<수상실적상세자료>();
		ResultSet rs = null;
		try {
			String sql = "select * from 수상실적 order by  연번 desc limit ?,? ;";
			Class.forName("com.mysql.jdbc.Driver");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				수상실적상세자료 수상실적상세자료 = new 수상실적상세자료();

				수상실적상세자료.set년도(rs.getInt("년도"));
				수상실적상세자료.set학과명(rs.getString("학과명"));
				수상실적상세자료.set대회규모규분(rs.getString("구분"));
				수상실적상세자료.set수상일자(rs.getString("수상일자"));
				수상실적상세자료.set대회명(rs.getString("대회명"));
				수상실적상세자료.set수상내용(rs.getString("수상내용"));
				수상실적상세자료.set인정여부(rs.getString("인정여부"));
				수상실적상세자료.set수상대상자(rs.getString("수상대상자"));
				수상실적상세자료.set연번(rs.getInt("연번"));
				수상실적상세자료.set입력부서(rs.getString("입력부서"));

				list.add(수상실적상세자료);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void 수상실적비율insert(int 수정년도, String 수정학과명) {
		String sql = "insert into 수상실적비율(학과명,년도) values(?,?);";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 수정학과명);
			pstmt.setInt(2, 수정년도);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update수상실적(int 수정년도, String 수정학과명, float 수상실적계산) {
		String sql = "update 수상실적비율 set 수상실적=? where 학과명=? and 년도=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, 수상실적계산);
			pstmt.setString(2, 수정학과명);
			pstmt.setInt(3, 수정년도);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public float 전임교원수상실적(int 수정년도, String 수정학과명) {
		int 계 = 0;
		String sql = "SELECT 교원국제*3+교원전국*2+교원지역*1 계 FROM schoolData.수상실적view where 년도=? and 학과명=?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 수정년도);
			pstmt.setString(2, 수정학과명);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				계 = rs.getInt("계");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 계;
	}

	public float 재학생수상실적(int 수정년도, String 수정학과명) {
		int 계 = 0;
		String sql = "SELECT 학생국제*3+학생전국*2+학생지역*1 계 FROM schoolData.수상실적view where 년도=? and 학과명=?;";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 수정년도);
			pstmt.setString(2, 수정학과명);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				계 = rs.getInt("계");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 계;
	}

}
