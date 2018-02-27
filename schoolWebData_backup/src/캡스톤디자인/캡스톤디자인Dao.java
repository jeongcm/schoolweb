package 캡스톤디자인;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 캡스톤디자인.캡스톤디자인;

public class 캡스톤디자인Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	
	private ResultSet rs;


	public boolean update(캡스톤디자인 캡스톤디자인) {
		String sql = "update 캡스톤디자인 set 년도=?,학과명=?,이수1학기=?,이수2학기=?,대상1학기=?,대상2학기=?,이수학생비율=? where 연번=?;";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 캡스톤디자인.get년도());
			pstmt.setString(2, 캡스톤디자인.get학과명());
			pstmt.setInt(3, 캡스톤디자인.get이수1학기());
			pstmt.setInt(4, 캡스톤디자인.get이수2학기());
			pstmt.setInt(5, 캡스톤디자인.get대상1학기());
			pstmt.setInt(6, 캡스톤디자인.get대상2학기());
			pstmt.setFloat(7, 캡스톤디자인.get이수학생비율());
			pstmt.setInt(8, 캡스톤디자인.get연번());

			pstmt.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	public boolean insert(캡스톤디자인 캡스톤디자인){
		String sql = "insert into 캡스톤디자인(년도,학과명,이수1학기,이수2학기,대상1학기,대상2학기,이수학생비율,입력부서) values(?,?,?,?,?,?,?,?);";
		try{
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, 캡스톤디자인.get년도());
			pstmt.setString(2, 캡스톤디자인.get학과명());
			pstmt.setInt(3, 캡스톤디자인.get이수1학기());
			pstmt.setInt(4, 캡스톤디자인.get이수2학기());
			pstmt.setInt(5, 캡스톤디자인.get대상1학기());
			pstmt.setInt(6, 캡스톤디자인.get대상2학기());
			pstmt.setFloat(7, 캡스톤디자인.get이수학생비율());
			pstmt.setString(8, 캡스톤디자인.get입력부서());
		
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
      e.printStackTrace();
      return false;
	}
}
	
	public void delete(int 연번) {
        String sql = "delete from 캡스톤디자인 where 연번=?;";
        try {
  
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 연번);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
   }
	
 public void dellAll(String 입력부서) {
		String sql = "delete from 캡스톤디자인 where 입력부서=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 입력부서);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public int getCountRow() {
		String sql = "select count(*) from 캡스톤디자인";
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
	
	public List<캡스톤디자인> select캡스톤디자인(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<캡스톤디자인> list = new ArrayList<캡스톤디자인>();

		String sql="select *,대상1학기+대상2학기 대상합계,이수1학기+이수2학기 이수합계 from 캡스톤디자인 order by 학과명 limit ?,?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				캡스톤디자인 캡스톤디자인 = new 캡스톤디자인();
				캡스톤디자인.set년도(rs.getInt("년도"));
				캡스톤디자인.set학과명(rs.getString("학과명"));
				캡스톤디자인.set대상1학기(rs.getInt("대상1학기"));
				캡스톤디자인.set대상2학기(rs.getInt("대상2학기"));
				캡스톤디자인.set이수1학기(rs.getInt("이수1학기"));
				캡스톤디자인.set이수2학기(rs.getInt("이수2학기"));
				캡스톤디자인.set입력부서(rs.getString("입력부서"));
				캡스톤디자인.set연번(rs.getInt("연번"));
			

				list.add(캡스톤디자인);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
