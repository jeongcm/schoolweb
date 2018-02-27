<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="org.apache.poi.xssf.usermodel.*"%>
<%@ page import="com.mysql.jdbc.Connection"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@page language="java" trimDirectiveWhitespaces="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolData?autoReconnect=true","root","123123");
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"select 학과.단과대학,학과._5대계열,교원.* ,학과.신설연도,재학생.군휴학자,재학생.학생정원,재학생.계 재학생,(인정학생정원+대학원생정원) 학생정원계,(재학생.계+대학원재학생) 재학생계"
						+ " from 학과현황 학과 join 전임교원확보율 교원 on 학과.학과명=교원.학과명 and 학과.년도=교원.년도 join 재학생현황 재학생 on 학과.년도=재학생.년도 and 재학생.학과명=학과.학과명 "
						+ "where 기준=04.01 order by 연번 desc; ");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("전임교원확보율");
		XSSFRow row = spreadsheet.createRow(0);
		XSSFCell cell;
		cell = row.createCell(0);
		cell.setCellValue("년도");
		cell = row.createCell(1);
		cell.setCellValue("단과대학");
		cell = row.createCell(2);
		cell.setCellValue("학과명");
		cell = row.createCell(3);
		cell.setCellValue("계열");
		cell = row.createCell(4);
		cell.setCellValue("신설연도");
		cell = row.createCell(5);
		cell.setCellValue("학생정원");
		cell = row.createCell(6);
		cell.setCellValue("군휴학자");
		cell = row.createCell(7);
		cell.setCellValue("인정학생정원");
		cell = row.createCell(8);
		cell.setCellValue("재학생");
		cell = row.createCell(9);
		cell.setCellValue("대학원생정원");
		cell = row.createCell(10);
		cell.setCellValue("대학원재학생");
		cell = row.createCell(11);
		cell.setCellValue("학생정원계");
		cell = row.createCell(12);
		cell.setCellValue("재학생계");
		cell = row.createCell(13);
		cell.setCellValue("학생정원기준전임교원");
		cell = row.createCell(14);
		cell.setCellValue("재학생기준전임교원");
		cell = row.createCell(15);
		cell.setCellValue("교원_학부_정원");
		cell = row.createCell(16);
		cell.setCellValue("교원_학부_재학생");
		cell = row.createCell(17);
		cell.setCellValue("교원_대학원_정원");
		cell = row.createCell(18);
		cell.setCellValue("교원_대학원_재학생");
		cell = row.createCell(19);
		cell.setCellValue("교원_계_정원");
		cell = row.createCell(20);
		cell.setCellValue("교원_계_재학생");
		cell = row.createCell(21);
		cell.setCellValue("학생수_정원");
		cell = row.createCell(22);
		cell.setCellValue("학생수_재학생");
		cell = row.createCell(23);
		cell.setCellValue("확보율_정원");
		cell = row.createCell(24);
		cell.setCellValue("확보율_재학생");
		cell = row.createCell(25);
		cell.setCellValue("전임교원확보율");
		cell = row.createCell(26);
		cell.setCellValue("T점수");

		int i = 1;
		while (resultSet.next()) {

			row = spreadsheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(resultSet.getInt("년도"));
			cell = row.createCell(1);
			cell.setCellValue(resultSet.getString("단과대학"));
			cell = row.createCell(2);
			cell.setCellValue(resultSet.getString("학과명"));
			cell = row.createCell(3);
			cell.setCellValue(resultSet.getString("_5대계열"));
			cell = row.createCell(4);
			cell.setCellValue(resultSet.getInt("신설연도"));
			cell = row.createCell(5);
			cell.setCellValue(resultSet.getInt("학생정원"));
			cell = row.createCell(6);
			cell.setCellValue(resultSet.getInt("군휴학자"));
			cell = row.createCell(7);
			cell.setCellValue(resultSet.getInt("인정학생정원"));
			cell = row.createCell(8);
			cell.setCellValue(resultSet.getInt("재학생"));
			cell = row.createCell(9);
			cell.setCellValue(resultSet.getInt("대학원생정원"));
			cell = row.createCell(10);
			cell.setCellValue(resultSet.getString("대학원재학생"));
			cell = row.createCell(11);
			cell.setCellValue(resultSet.getInt("학생정원계"));
			cell = row.createCell(12);
			cell.setCellValue(resultSet.getString("재학생계"));
			cell = row.createCell(13);
			cell.setCellValue(resultSet.getInt("학생정원기준전임교원"));
			cell = row.createCell(14);
			cell.setCellValue(resultSet.getInt("재학생기준전임교원"));
			cell = row.createCell(15);
			cell.setCellValue(resultSet.getInt("교원_학부_정원"));
			cell = row.createCell(16);
			cell.setCellValue(resultSet.getInt("교원_학부_재학생"));
			cell = row.createCell(17);
			cell.setCellValue(resultSet.getInt("교원_대학원_정원"));
			cell = row.createCell(18);
			cell.setCellValue(resultSet.getInt("교원_대학원_재학생"));
			cell = row.createCell(19);
			cell.setCellValue(resultSet.getInt("교원_계_정원"));
			cell = row.createCell(20);
			cell.setCellValue(resultSet.getInt("교원_계_재학생"));
			cell = row.createCell(21);
			cell.setCellValue(resultSet.getFloat("학생수_정원"));
			cell = row.createCell(22);
			cell.setCellValue(resultSet.getFloat("학생수_재학생"));
			cell = row.createCell(23);
			cell.setCellValue(resultSet.getFloat("확보율_정원"));
			cell = row.createCell(24);
			cell.setCellValue(resultSet.getFloat("확보율_재학생"));
			cell = row.createCell(25);
			cell.setCellValue(resultSet.getFloat("전임교원확보율"));
			cell = row.createCell(26);
			cell.setCellValue(resultSet.getFloat("T점수"));

			i++;
		}
		FileOutputStream fo = new FileOutputStream(
				new File(application.getRealPath("/tmpDownload/") + "전임교원확보율.xlsx"));

		workbook.write(fo);
		fo.close();
		workbook.close();
	} catch (Exception e) {
			e.printStackTrace();
	} 
	request.setCharacterEncoding("UTF-8");
	String savePath = request.getSession().getServletContext().getRealPath("/tmpDownload");

	String filename = "전임교원확보율.xlsx";

	String orgfilename = "전임교원확보율.xlsx";

	InputStream in = null;

	OutputStream ops = null;
	File file = null;
	boolean skip = false;
	String client = "";

	try {

		try {
			file = new File(savePath, filename);
			in = new FileInputStream(file);
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
			skip = true;
		}

		client = request.getHeader("User-Agent");

		response.reset();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Description", "JSP Generated Data");

		if (!skip) {

			if (client.indexOf("MSIE") != -1) {
				response.setHeader("Content-Disposition",
						"attachment; filename=" + new String(orgfilename.getBytes("KSC5601"), "ISO8859_1"));

			} else {
				orgfilename = new String(orgfilename.getBytes("utf-8"), "iso-8859-1");

				response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\"");
				response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
			}

			response.setHeader("Content-Length", "" + file.length());
			out.clear();
			out=pageContext.pushBody();
			ops = response.getOutputStream();

			byte b[] = new byte[(int) file.length()];
			int leng = 0;

			while ((leng = in.read(b)) > 0) {
				ops.write(b, 0, leng);
			}

		} else {
			response.setContentType("text/html;charset=UTF-8");
			out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
		}

		in.close();
		ops.flush();
		ops.close();

	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일다운로드</title>
</head>
<body>
</body>
</html>