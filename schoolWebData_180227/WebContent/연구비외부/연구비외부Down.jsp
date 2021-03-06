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
		Connection con = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost:3306/schoolData?autoReconnect=true", "root", "123123");
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from 연구비외부  ");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("연구비외부");
		XSSFRow row = spreadsheet.createRow(0);
		XSSFCell cell;
		cell = row.createCell(0);
		cell.setCellValue("학교명");
		cell = row.createCell(1);
		cell.setCellValue("대학명");
		cell = row.createCell(2);
		cell.setCellValue("전임교원수");
		cell = row.createCell(3);
		cell.setCellValue("소계");
		cell = row.createCell(4);
		cell.setCellValue("중앙정부");
		cell = row.createCell(5);
		cell.setCellValue("지자체");
		cell = row.createCell(6);
		cell.setCellValue("민간");
		cell = row.createCell(7);
		cell.setCellValue("외국");
		cell = row.createCell(8);
		cell.setCellValue("연구비");
		cell = row.createCell(9);
		cell.setCellValue("T점수");

		int i = 1;

		while (resultSet.next()) {

			row = spreadsheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(resultSet.getString("대학명"));
			cell = row.createCell(1);
			cell.setCellValue(resultSet.getString("학교"));
			cell = row.createCell(2);
			cell.setCellValue(resultSet.getInt("전임교원수"));
			cell = row.createCell(3);
			cell.setCellValue(resultSet.getInt("중앙정부")+resultSet.getInt("지자체")+resultSet.getInt("민간")+resultSet.getInt("외국"));
			cell = row.createCell(4);
			cell.setCellValue(resultSet.getInt("중앙정부"));
			cell = row.createCell(5);
			cell.setCellValue(resultSet.getInt("지자체"));
			cell = row.createCell(6);
			cell.setCellValue(resultSet.getInt("민간"));
			cell = row.createCell(7);
			cell.setCellValue(resultSet.getInt("외국"));
			cell = row.createCell(8);
			cell.setCellValue(resultSet.getFloat("연구비"));
			cell = row.createCell(9);
	         cell.setCellValue(resultSet.getFloat("T점수"));

			i++;
		}
		FileOutputStream fo = new FileOutputStream(
				new File(application.getRealPath("/tmpDownload/") + "연구비외부.xlsx"));

		workbook.write(fo);
		fo.close();
		workbook.close();
	} catch (Exception e) {
			e.printStackTrace();
	} 
	request.setCharacterEncoding("UTF-8");
	String savePath = request.getSession().getServletContext().getRealPath("/tmpDownload");

	String filename = "연구비외부.xlsx";

	String orgfilename = "연구비외부.xlsx";

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

			// IE
			if (client.indexOf("MSIE") != -1) {
				response.setHeader("Content-Disposition",
						"attachment; filename=" + new String(orgfilename.getBytes("KSC5601"), "ISO8859_1"));

			} else {
				// 한글 파일명 처리
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
<title>파일다운로드 테스트</title>
</head>
<body>
</body>
</html>