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
		ResultSet resultSet = statement.executeQuery("select * from 교육프로그램운영내역양식  ");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("교육프로그램운영내역양식");
		XSSFRow row = spreadsheet.createRow(0);
		XSSFCell cell;
		cell = row.createCell(0);
		cell.setCellValue("년도");
		cell = row.createCell(1);
		cell.setCellValue("운영부서명");
		cell = row.createCell(2);
		cell.setCellValue("프로그램명");
		cell = row.createCell(3);
		cell.setCellValue("학과명");
		cell = row.createCell(4);
		cell.setCellValue("학년");
		cell = row.createCell(5);
		cell.setCellValue("학번");
		cell = row.createCell(6);
		cell.setCellValue("성명");
		cell = row.createCell(7);
		cell.setCellValue("비고");

		int i = 1;

		while (resultSet.next()) {

			row = spreadsheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(resultSet.getInt("년도"));
			cell = row.createCell(1);
			cell.setCellValue(resultSet.getString("운영부서명"));
			cell = row.createCell(2);
			cell.setCellValue(resultSet.getString("프로그램명"));
			cell = row.createCell(3);
			cell.setCellValue(resultSet.getString("학과명"));
			cell = row.createCell(4);
			cell.setCellValue(resultSet.getInt("학년"));
			cell = row.createCell(5);
			cell.setCellValue(resultSet.getString("학번"));
			cell = row.createCell(6);
			cell.setCellValue(resultSet.getString("성명"));
			cell = row.createCell(7);
			cell.setCellValue(resultSet.getString("비고"));

			i++;
		}
		FileOutputStream fo = new FileOutputStream(
				new File(application.getRealPath("/tmpDownload/") + "교육프로그램양식.xlsx"));

		workbook.write(fo);
		fo.close();
		workbook.close();
		System.out.println("exceldatabase.xlsx written successfully");
	} catch (Exception e) {
			e.printStackTrace();
	} 
	request.setCharacterEncoding("UTF-8");
	String savePath = request.getSession().getServletContext().getRealPath("/tmpDownload");

	String filename = "교육프로그램양식.xlsx";

	String orgfilename = "교육프로그램양식.xlsx";

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
			fe.printStackTrace();
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
<title>파일다운로드 테스트</title>
</head>
<body>
</body>
</html>