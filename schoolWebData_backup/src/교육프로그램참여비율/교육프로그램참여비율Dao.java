package 교육프로그램참여비율;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;
import 교육프로그램참여비율.교육프로그램참여비율;

public class 교육프로그램참여비율Dao {
	private Connection conn = dbConnection.getConnection();
	private PreparedStatement pstmt=null;
	private ResultSet rs;

	public boolean update(교육프로그램참여비율 교육프로그램참여비율) {
		String sql = "update 교육프로그램운영내역 set 년도=?,운영부서명=?,프로그램명=?,학과명=?,학년=?,학번=?,성명=?,비고=? where 연번=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 교육프로그램참여비율.get년도());
			pstmt.setString(2, 교육프로그램참여비율.get운영부서명());
			pstmt.setString(3, 교육프로그램참여비율.get프로그램명());
			pstmt.setString(4, 교육프로그램참여비율.get학과명());
			pstmt.setInt(5, 교육프로그램참여비율.get학년());
			pstmt.setString(6, 교육프로그램참여비율.get학번());
			pstmt.setString(7, 교육프로그램참여비율.get성명());
			pstmt.setString(8, 교육프로그램참여비율.get비고());
			pstmt.setInt(9, 교육프로그램참여비율.get연번());
		
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {		
			e.printStackTrace();
			return false;
		}

	}
	public boolean insert(교육프로그램참여비율 교육프로그램참여비율){
		String sql = "insert into 교육프로그램운영내역(년도,운영부서명,프로그램명,학과명,학년,학번,성명,비고,입력부서) values(?,?,?,?,?,?,?,?,?);";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, 교육프로그램참여비율.get년도());
			pstmt.setString(2, 교육프로그램참여비율.get운영부서명());
			pstmt.setString(3, 교육프로그램참여비율.get프로그램명());
			pstmt.setString(4, 교육프로그램참여비율.get학과명());
			pstmt.setInt(5, 교육프로그램참여비율.get학년());
			pstmt.setString(6, 교육프로그램참여비율.get학번());
			pstmt.setString(7, 교육프로그램참여비율.get성명());
			pstmt.setString(8, 교육프로그램참여비율.get비고());
			pstmt.setString(9, 교육프로그램참여비율.get입력부서());
		
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
        }
	}
	public void delete(int 연번) {
        String sql = "delete from 교육프로그램운영내역 where 연번=?;";
        try {     
            pstmt = conn.prepareStatement(sql);     
            pstmt.setInt(1, 연번);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } 
    }
	 public void dellAll(String 입력부서) {
			String sql = "delete from 교육프로그램운영내역 where 입력부서 =?;";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, 입력부서);
				pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	 public int getCountRow() {
		String sql = "select count(*) from 교육프로그램운영내역";
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

	public List<교육프로그램참여비율> select교육프로그램참여비율(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<교육프로그램참여비율> list = new ArrayList<교육프로그램참여비율>();

		String sql="select * from 교육프로그램운영내역 order by 연번 desc limit ?,?;";
		
		try {
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				교육프로그램참여비율 교육프로그램참여비율 = new 교육프로그램참여비율();
				교육프로그램참여비율.set년도(rs.getInt("년도"));
				교육프로그램참여비율.set운영부서명(rs.getString("운영부서명"));
				교육프로그램참여비율.set프로그램명(rs.getString("프로그램명"));
				교육프로그램참여비율.set학과명(rs.getString("학과명"));
				교육프로그램참여비율.set학년(rs.getInt("학년"));
				교육프로그램참여비율.set학번(rs.getString("학번"));
				교육프로그램참여비율.set성명(rs.getString("성명"));
				교육프로그램참여비율.set비고(rs.getString("비고"));
				교육프로그램참여비율.set연번(rs.getInt("연번"));
				교육프로그램참여비율.set입력부서(rs.getString("입력부서"));
				list.add(교육프로그램참여비율);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void 동아리참여비율insert(int 수정년도, String 수정학과명) {
		String sql = "insert into 교육프로그램비율(학과명,년도) values(?,?);";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, 수정학과명);
			pstmt.setInt(2, 수정년도);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int 계(int 수정년도, String 수정학과명) {
		int 계 = 0;
		String sql = "select 계 from 교육프로그램view where 년도=? and 학과명=?;";
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
	public void update교육프로그램비율(int 수정년도, String 수정학과명, float 교육프로그램비율계산) {
		String sql = "update 교육프로그램비율 set 교육프로그램참여비율=? where 학과명=? and 년도=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, 교육프로그램비율계산);
			pstmt.setString(2, 수정학과명);
			pstmt.setInt(3, 수정년도);

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
}