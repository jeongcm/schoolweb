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
		Statement statement2 = connect.createStatement();
		Statement statement3 = connect.createStatement();
		Statement statement4 = connect.createStatement();

		ResultSet resultSet = statement.executeQuery("select * from 취업률view;");

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("취업률");
		XSSFRow row = spreadsheet.createRow(0);
		XSSFCell cell;
		cell = row.createCell(0);
		cell.setCellValue("년도");
		cell = row.createCell(1);
		cell.setCellValue("단과대학");
		cell = row.createCell(2);
		cell.setCellValue("학과명");
		cell = row.createCell(3);
		cell.setCellValue("학문계열1");
		cell = row.createCell(4);
		cell.setCellValue("졸업자");
		cell = row.createCell(5);
		cell.setCellValue("취업자 합계");
		cell = row.createCell(6);
		cell.setCellValue("건강보험db연계취업자");
		cell = row.createCell(7);
		cell.setCellValue("해외취업자");
		cell = row.createCell(8);
		cell.setCellValue("영농업취업자");
		cell = row.createCell(9);
		cell.setCellValue("개인창작활동조사서");
		cell = row.createCell(10);
		cell.setCellValue("프리랜서");
		cell = row.createCell(11);
		cell.setCellValue("취업제외자 합계");
		cell = row.createCell(12);
		cell.setCellValue("진학자");
		cell = row.createCell(13);
		cell.setCellValue("입대자");
		cell = row.createCell(14);
		cell.setCellValue("취업불가능자");
		cell = row.createCell(15);
		cell.setCellValue("외국인유학생");
		cell = row.createCell(16);
		cell.setCellValue("건강보험직장가입제외대상");
		cell = row.createCell(17);
		cell.setCellValue("입학당시기취업자");
		cell = row.createCell(18);
		cell.setCellValue("2차유지취업률");
		cell = row.createCell(19);
		cell.setCellValue("취업률");
		cell = row.createCell(20);
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
			cell.setCellValue(resultSet.getString("학문계열1"));
			cell = row.createCell(4);
			cell.setCellValue(resultSet.getInt("졸업자"));
			cell = row.createCell(5);
			cell.setCellValue(resultSet.getInt("취업합계"));
			cell = row.createCell(6);
			cell.setCellValue(resultSet.getInt("건강보험db연계취업자"));
			cell = row.createCell(7);
			cell.setCellValue(resultSet.getInt("해외취업자"));
			cell = row.createCell(8);
			cell.setCellValue(resultSet.getInt("영농업취업자"));
			cell = row.createCell(9);
			cell.setCellValue(resultSet.getInt("개인창작활동조사서"));
			cell = row.createCell(10);
			cell.setCellValue(resultSet.getInt("프리랜서"));
			cell = row.createCell(11);
			cell.setCellValue(resultSet.getInt("제외자합계"));
			cell = row.createCell(12);
			cell.setCellValue(resultSet.getInt("진학자"));
			cell = row.createCell(13);
			cell.setCellValue(resultSet.getInt("입대자"));
			cell = row.createCell(14);
			cell.setCellValue(resultSet.getInt("취업불가능자"));
			cell = row.createCell(15);
			cell.setCellValue(resultSet.getInt("외국인유학생"));
			cell = row.createCell(16);
			cell.setCellValue(resultSet.getInt("건강보험직장가입제외대상"));
			cell = row.createCell(17);
			cell.setCellValue(resultSet.getInt("입학당시기취업자"));
			cell = row.createCell(18);
			cell.setCellValue(resultSet.getFloat("2차유지취업률"));
			cell = row.createCell(19);
			cell.setCellValue(resultSet.getInt("취업률"));
			cell = row.createCell(20);
			cell.setCellValue(resultSet.getInt("T점수"));

			i++;
		}
		FileOutputStream fo = new FileOutputStream(
				new File(application.getRealPath("/tmpDownload/") + "취업률.xlsx"));

		workbook.write(fo);
		fo.close();
		workbook.close();
		System.out.println("exceldatabase.xlsx written successfully");
	} catch (Exception e) {
		e.printStackTrace();
	} finally {

	}
%>
<%
	//
	request.setCharacterEncoding("UTF-8");
	String savePath = request.getSession().getServletContext().getRealPath("/tmpDownload");

	// 서버에 실제 저장된 파일명
	String filename = "취업률.xlsx";

	// 실제 내보낼 파일명
	String orgfilename = "취업률.xlsx";

	InputStream in = null;

	OutputStream ops = null;
	File file = null;
	boolean skip = false;
	String client = "";

	try {

		// 파일을 읽어 스트림에 담기
		try {
			file = new File(savePath, filename);
			in = new FileInputStream(file);
		} catch (FileNotFoundException fe) {
			skip = true;
		}

		client = request.getHeader("User-Agent");

		// 파일 다운로드 헤더 지정
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
			out = pageContext.pushBody();
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
<title>파일다운로드</title>
</head>
<body>
</body>
</html>