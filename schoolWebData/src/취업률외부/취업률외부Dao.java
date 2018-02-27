package 취업률외부;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbConnection.dbConnection;

public class 취업률외부Dao {
	private Connection conn = dbConnection.getConnection();

	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCountRow() {
		String sql = "select count(*) from 취업현황외부";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 결과물 편집, 리턴
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<취업률외부> select취업률외부(int page, int perPageRow) {
		int beginRow = perPageRow * page - perPageRow;
		List<취업률외부> list = new ArrayList<취업률외부>();

		String sql = " select * from 취업률외부view limit ?,?;";

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, perPageRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				취업률외부 취업률 = new 취업률외부();
				취업률.set대학명(rs.getString("대학명"));
				취업률.set학과명(rs.getString("학과명"));
				취업률.set취업자(rs.getInt("취업합계"));
				취업률.set취업제외자(rs.getInt("제외자합계"));
				취업률.set건강보험db연계취업자(rs.getInt("건강보험db연계취업자"));
				취업률.set해외취업자(rs.getInt("해외취업자"));
				취업률.set영농업취업자(rs.getInt("영농업취업자"));
				취업률.set진학자(rs.getInt("진학자"));
				취업률.set입대자(rs.getInt("입대자"));
				취업률.set취업불가능자(rs.getInt("취업불가능자"));
				취업률.set외국인유학생(rs.getInt("외국인유학생"));
				취업률.set건강보험직장가입제외대상(rs.getInt("건강보험직장가입제외대상"));
				취업률.set입학당시기취업자(rs.getInt("입학당시기취업자"));

				취업률.set졸업자(rs.getInt("졸업자"));
				취업률.set이차유지취업률(rs.getFloat("2차유지취업률"));
				취업률.setT점수(rs.getFloat("T점수"));
				취업률.set취업률(rs.getFloat("취업률"));

				취업률.set개인창작활동조사서(rs.getInt("개인창작활동조사서"));
				취업률.set일인창업자(rs.getInt("1인창업자"));
				취업률.set프리랜서(rs.getInt("프리랜서"));

				list.add(취업률);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
