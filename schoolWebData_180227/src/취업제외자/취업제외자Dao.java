package 취업제외자;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class 취업제외자Dao {
   private Connection conn = dbConnection.getConnection();
   private PreparedStatement pstmt;
   private ResultSet rs;

   public boolean update(취업제외자 취업제외자) {
      String sql = "update 취업제외자 set 년도=?,학과명=?,진학자=?,입대자=?,취업불가능자=?,외국인유학생=?,건강보험직장가입제외대상=?,입학당시기취업자=?,계=? where 연번=?;";
      try {

         pstmt = conn.prepareStatement(sql);

         pstmt.setInt(1, 취업제외자.get년도());
         pstmt.setString(2, 취업제외자.get학과명());
         pstmt.setInt(3, 취업제외자.get진학자());
         pstmt.setInt(4, 취업제외자.get입대자());
         pstmt.setInt(5, 취업제외자.get취업불가능자());
         pstmt.setInt(6, 취업제외자.get외국인유학생());
         pstmt.setInt(7, 취업제외자.get건강보험직장가입제외대상());
         pstmt.setInt(8, 취업제외자.get입학당시기취업자());
         pstmt.setInt(9, 취업제외자.get계());
         pstmt.setInt(10, 취업제외자.get연번());

         pstmt.executeUpdate();
         return true;
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }

   }

   public boolean insert(취업제외자 취업제외자) {
      String sql = "insert into 취업제외자(년도,학과명,진학자,입대자,취업불가능자,외국인유학생,건강보험직장가입제외대상,계) values(?,?,?,?,?,?,?,?);";
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, 취업제외자.get년도());
         pstmt.setString(2, 취업제외자.get학과명());
         pstmt.setInt(3, 취업제외자.get진학자());
         pstmt.setInt(4, 취업제외자.get입대자());
         pstmt.setInt(5, 취업제외자.get취업불가능자());
         pstmt.setInt(6, 취업제외자.get외국인유학생());
         pstmt.setInt(7, 취업제외자.get건강보험직장가입제외대상());
         pstmt.setInt(8, 취업제외자.get계());
         pstmt.setString(9, 취업제외자.get입력부서());

         pstmt.executeUpdate();
         return true;
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
   }

   public void delete(int 연번) {
      String sql = "delete from 취업제외자 where 연번=?;";
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, 연번);
         pstmt.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public void dellAll(String 입력부서) {
      String sql = "delete from 취업제외자 where 입력부서=?";
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, 입력부서);
         pstmt.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }


   public int getCountRow() {
      String sql = "select count(*) from 취업제외자";
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

   public List<취업제외자> select취업제외자(int page, int perPageRow) {
      int beginRow = perPageRow * page - perPageRow;
      List<취업제외자> list = new ArrayList<취업제외자>();

      String sql = "select * from 취업제외자 order by 연번 desc limit ?,? ;";
      try {

         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, beginRow);
         pstmt.setInt(2, perPageRow);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            취업제외자 취업제외자 = new 취업제외자();
            취업제외자.set연번(rs.getInt("연번"));
            취업제외자.set년도(rs.getInt("년도"));
            취업제외자.set학과명(rs.getString("학과명"));
            취업제외자.set계(rs.getInt("계"));
            취업제외자.set입력부서(rs.getString("입력부서"));
            취업제외자.set진학자(rs.getInt("진학자"));
            취업제외자.set입대자(rs.getInt("입대자"));
            취업제외자.set취업불가능자(rs.getInt("취업불가능자"));
            취업제외자.set외국인유학생(rs.getInt("외국인유학생"));
            취업제외자.set건강보험직장가입제외대상(rs.getInt("건강보험직장가입제외대상"));
            취업제외자.set입학당시기취업자(rs.getInt("입학당시기취업자"));

            list.add(취업제외자);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return list;
   }

   

}