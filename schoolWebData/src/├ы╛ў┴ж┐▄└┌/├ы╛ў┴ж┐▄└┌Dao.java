package ���������;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class ���������Dao {
   private Connection conn = dbConnection.getConnection();
   private PreparedStatement pstmt;
   private ResultSet rs;

   public boolean update(��������� ���������) {
      String sql = "update ��������� set �⵵=?,�а���=?,������=?,�Դ���=?,����Ұ�����=?,�ܱ������л�=?,�ǰ��������尡�����ܴ��=?,���д�ñ������=?,��=? where ����=?;";
      try {

         pstmt = conn.prepareStatement(sql);

         pstmt.setInt(1, ���������.get�⵵());
         pstmt.setString(2, ���������.get�а���());
         pstmt.setInt(3, ���������.get������());
         pstmt.setInt(4, ���������.get�Դ���());
         pstmt.setInt(5, ���������.get����Ұ�����());
         pstmt.setInt(6, ���������.get�ܱ������л�());
         pstmt.setInt(7, ���������.get�ǰ��������尡�����ܴ��());
         pstmt.setInt(8, ���������.get���д�ñ������());
         pstmt.setInt(9, ���������.get��());
         pstmt.setInt(10, ���������.get����());

         pstmt.executeUpdate();
         return true;
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }

   }

   public boolean insert(��������� ���������) {
      String sql = "insert into ���������(�⵵,�а���,������,�Դ���,����Ұ�����,�ܱ������л�,�ǰ��������尡�����ܴ��,��) values(?,?,?,?,?,?,?,?);";
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, ���������.get�⵵());
         pstmt.setString(2, ���������.get�а���());
         pstmt.setInt(3, ���������.get������());
         pstmt.setInt(4, ���������.get�Դ���());
         pstmt.setInt(5, ���������.get����Ұ�����());
         pstmt.setInt(6, ���������.get�ܱ������л�());
         pstmt.setInt(7, ���������.get�ǰ��������尡�����ܴ��());
         pstmt.setInt(8, ���������.get��());
         pstmt.setString(9, ���������.get�Էºμ�());

         pstmt.executeUpdate();
         return true;
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
   }

   public void delete(int ����) {
      String sql = "delete from ��������� where ����=?;";
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, ����);
         pstmt.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public void dellAll(String �Էºμ�) {
      String sql = "delete from ��������� where �Էºμ�=?";
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, �Էºμ�);
         pstmt.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }


   public int getCountRow() {
      String sql = "select count(*) from ���������";
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

   public List<���������> select���������(int page, int perPageRow) {
      int beginRow = perPageRow * page - perPageRow;
      List<���������> list = new ArrayList<���������>();

      String sql = "select * from ��������� order by ���� desc limit ?,? ;";
      try {

         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, beginRow);
         pstmt.setInt(2, perPageRow);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            ��������� ��������� = new ���������();
            ���������.set����(rs.getInt("����"));
            ���������.set�⵵(rs.getInt("�⵵"));
            ���������.set�а���(rs.getString("�а���"));
            ���������.set��(rs.getInt("��"));
            ���������.set�Էºμ�(rs.getString("�Էºμ�"));
            ���������.set������(rs.getInt("������"));
            ���������.set�Դ���(rs.getInt("�Դ���"));
            ���������.set����Ұ�����(rs.getInt("����Ұ�����"));
            ���������.set�ܱ������л�(rs.getInt("�ܱ������л�"));
            ���������.set�ǰ��������尡�����ܴ��(rs.getInt("�ǰ��������尡�����ܴ��"));
            ���������.set���д�ñ������(rs.getInt("���д�ñ������"));

            list.add(���������);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return list;
   }

   

}