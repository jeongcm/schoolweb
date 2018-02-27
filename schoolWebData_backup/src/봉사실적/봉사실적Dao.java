package 봉사실적;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 봉사실적.봉사실적;

public class 봉사실적Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public boolean update(봉사실적 봉사실적) {
		String sql = "update 봉사실적 set 년도=?,학기=?,학과명=?,학번=?,성명=?,이수학점=?,비고=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 봉사실적.get년도());
			pstmt.setString(2, 봉사실적.get학기());
			pstmt.setString(3, 봉사실적.get학과명());
			pstmt.setInt(4, 봉사실적.get학번());
			pstmt.setString(5, 봉사실적.get성명());
			pstmt.setInt(6, 봉사실적.get이수학점());
			pstmt.setString(7, 봉사실적.get비고());
			pstmt.setInt(8, 봉사실적.get연번());

			pstmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(봉사실적 봉사실적) {
		String sql = "insert into 봉사실적(년도,학기,학과명,학번,성명,이수학점,비고,입력부서) values(?,?,?,?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 봉사실적.get년도());
			pstmt.setString(2, 봉사실적.get학기());
			pstmt.setString(3, 봉사실적.get학과명());
			pstmt.setInt(4, 봉사실적.get학번());
			pstmt.setString(5, 봉사실적.get성명());
			pstmt.setInt(6, 봉사실적.get이수학점());
			pstmt.setString(7, 봉사실적.get비고());
			pstmt.setString(8, 봉사실적.get입력부서());

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void dellAll(String 입력부서) {
		String sql = "delete from 봉사실적 where 입력부서=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int 연번) {
		String sql = "delete from 봉사실적 where 연번=?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 연번);
			pstmt.executeUpdate();

		} catch ( SQLException e) {
			e.printStackTrace();
			
		}
	}

	public int getCountRow() {
		String sql = "select count(*) from 봉사실적";
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

	public List<봉사실적> select봉사실적(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<봉사실적> list = new ArrayList<봉사실적>();

		String sql = "select * from 봉사실적 order by  연번 desc limit ?,?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				봉사실적 봉사실적 = new 봉사실적();
				봉사실적.set년도(rs.getInt("년도"));
				봉사실적.set학기(rs.getString("학기"));
				봉사실적.set학과명(rs.getString("학과명"));
				봉사실적.set학번(rs.getInt("학번"));
				봉사실적.set성명(rs.getString("성명"));
				봉사실적.set이수학점(rs.getInt("이수학점"));
				봉사실적.set비고(rs.getString("비고"));
				봉사실적.set연번(rs.getInt("연번"));
				봉사실적.set입력부서(rs.getString("입력부서"));

				list.add(봉사실적);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void 봉사실적비율insert(int 수정년도, String 수정학과명) {
		String sql = "insert into 봉사실적비율(학과명,년도) values(?,?);";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 수정학과명);
			pstmt.setInt(2, 수정년도);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int 이수학점(String 수정학과명, String 학기) {
		int 이수학점=0;
		String sql="select sum(if(학기='"+학기+"' and 학과명='"+수정학과명+"',이수학점,0)) 이수학점 from 봉사실적;";
		try{
				
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
	
			
			while(rs.next()){
				이수학점=rs.getInt("이수학점");
			}
		} catch (Exception e) {
			이수학점=0;
			e.printStackTrace();
		}
		return 이수학점;
	}

	public int 재학생2학기(int 수정년도, String 수정학과명) {
		int 재학생수 = 0;
		String sql = "select 계 from 재학생현황  where 학과명=? and 기준=10.01 and 년도=?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 수정학과명);
			pstmt.setInt(2, 수정년도);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				재학생수 = rs.getInt("계");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 재학생수;
	}

	public void update봉사실적비율(int 수정년도, String 수정학과명, float 이수학점) {
		String sql = "update 봉사실적비율 set 이수학점=? where 학과명=? and 년도=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, 이수학점);
			pstmt.setString(2, 수정학과명);
			pstmt.setInt(3, 수정년도);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
