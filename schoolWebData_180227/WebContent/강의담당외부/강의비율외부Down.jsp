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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connect = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost:3306/schoolData?autoReconnect=true", "root", "123123");
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery("select 학과.학과명,개설.*,"
				+"sum(if(구분='개설과목',학점,0)) as 전공과목,"
				+" sum(if(구분='교양필수',학점,0)) as 교양필수과목,"
				+" sum(if(구분='자유선택',학점,0)) as 자유선택과목"  
				+" from 교원강의담당외부비율 교원" 
				+" join 학과현황 학과 on 교원.학과명=학과.학과명" 
				+" join 개설강의담당외부비율 개설 on 학과.학과명=개설.학과명" 
				+" group by 학과.학과명 order by 연번 desc");
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("강의담당외부비율");
		XSSFRow row = spreadsheet.createRow(0);
		XSSFCell cell;
		cell = row.createCell(0);
		cell.setCellValue("대학명");
		cell = row.createCell(1);
		cell.setCellValue("학과명");
		cell = row.createCell(2);
		cell.setCellValue("개설 자유선택 과목");
		cell = row.createCell(3);
		cell.setCellValue("개설 교양필수 과목");
		cell = row.createCell(4);
		cell.setCellValue("개설 전공 과목");
		cell = row.createCell(5);
		cell.setCellValue("교원담당 자유선택");
		cell = row.createCell(6);
		cell.setCellValue("교원담당 교양필수");
		cell = row.createCell(7);
		cell.setCellValue("교원 담당 전공과목");
		cell = row.createCell(8);
		cell.setCellValue("강의 담당비율");
		cell = row.createCell(9);
		cell.setCellValue("T점수");

		int i = 1;
		while (resultSet.next()) {
			row = spreadsheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(resultSet.getString("대학명"));
			cell = row.createCell(1);
			cell.setCellValue(resultSet.getString("학과명"));
			cell = row.createCell(2);
			cell.setCellValue(resultSet.getFloat("개설자유선택과목"));
			cell = row.createCell(3);
			cell.setCellValue(resultSet.getFloat("개설교양필수과목"));
			cell = row.createCell(4);
			cell.setCellValue(resultSet.getFloat("개설전공과목"));
			cell = row.createCell(5);
			cell.setCellValue(resultSet.getFloat("자유선택과목"));
			cell = row.createCell(6);
			cell.setCellValue(resultSet.getFloat("교양필수과목"));
			cell = row.createCell(7);
			cell.setCellValue(resultSet.getFloat("전공과목"));
			cell = row.createCell(10);
			cell.setCellValue(Math.round(resultSet.getFloat("강의담당비율") * 100) / 100.0);
			cell = row.createCell(11);
			cell.setCellValue(resultSet.getFloat("T점수"));

			i++;
		}
		FileOutputStream fo = new FileOutputStream(
				new File(application.getRealPath("/tmpDownload/") + "강의담당외부비율.xlsx"));

		workbook.write(fo);
		fo.close();
		workbook.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	request.setCharacterEncoding("UTF-8");
	String savePath = request.getSession().getServletContext().getRealPath("/tmpDownload");

	String filename = "강의담당외부비율.xlsx";

	String orgfilename = "강의담당외부비율.xlsx";

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
	//
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
</body>
</html>