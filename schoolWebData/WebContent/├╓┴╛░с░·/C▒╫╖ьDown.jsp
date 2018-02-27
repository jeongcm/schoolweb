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
		ResultSet resultSet = statement.executeQuery("select * from C그룹  ");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("C그룹");
		XSSFRow row = spreadsheet.createRow(0);
		XSSFCell cell;
		cell = row.createCell(0);
		cell.setCellValue("단과대학");
		cell = row.createCell(1);
		cell.setCellValue("학과명");
		cell = row.createCell(2);
		cell.setCellValue("T점수A");
		cell = row.createCell(3);
		cell.setCellValue("환사점수A");
		cell = row.createCell(4);
		cell.setCellValue("T점수B");
		cell = row.createCell(5);
		cell.setCellValue("환산점수B");
		cell = row.createCell(6);
		cell.setCellValue("T점수C");
		cell = row.createCell(7);
		cell.setCellValue("환산점수C");
		cell = row.createCell(8);
		cell.setCellValue("T점수D");
		cell = row.createCell(9);
		cell.setCellValue("환산점수D");
		cell = row.createCell(10);
		cell.setCellValue("T점수E");
		cell = row.createCell(11);
		cell.setCellValue("환산점수E");
		cell = row.createCell(12);
		cell.setCellValue("T점수F");
		cell = row.createCell(13);
		cell.setCellValue("환산점수F");
		cell = row.createCell(14);
		cell.setCellValue("T점수G");
		cell = row.createCell(15);
		cell.setCellValue("환산점수G");
		cell = row.createCell(16);
		cell.setCellValue("T점수H");
		cell = row.createCell(17);
		cell.setCellValue("환산점수H");
		cell = row.createCell(18);
		cell.setCellValue("T점수I");
		cell = row.createCell(19);
		cell.setCellValue("환산점수I");
		cell = row.createCell(20);
		cell.setCellValue("T점수J");
		cell = row.createCell(21);
		cell.setCellValue("환산점수J");

		int i = 1;

		while (resultSet.next()) {

			row = spreadsheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(resultSet.getString("단과대학"));
			cell = row.createCell(1);
			cell.setCellValue(resultSet.getString("학과명"));

			cell = row.createCell(2);
			cell.setCellValue(resultSet.getDouble("T점수A"));
			cell = row.createCell(3);
			cell.setCellValue(resultSet.getDouble("환산점수A"));

			cell = row.createCell(4);
			cell.setCellValue(resultSet.getDouble("T점수B"));
			cell = row.createCell(5);
			cell.setCellValue(resultSet.getDouble("환산점수B"));

			cell = row.createCell(6);
			cell.setCellValue(resultSet.getDouble("T점수C"));
			cell = row.createCell(7);
			cell.setCellValue(resultSet.getDouble("환산점수C"));

			cell = row.createCell(8);
			cell.setCellValue(resultSet.getDouble("T점수D"));
			cell = row.createCell(9);
			cell.setCellValue(resultSet.getDouble("환산점수D"));

			cell = row.createCell(10);
			cell.setCellValue(resultSet.getDouble("T점수E"));
			cell = row.createCell(11);
			cell.setCellValue(resultSet.getDouble("환산점수E"));

			cell = row.createCell(12);
			cell.setCellValue(resultSet.getDouble("T점수F"));
			cell = row.createCell(13);
			cell.setCellValue(resultSet.getDouble("환산점수F"));

			cell = row.createCell(14);
			cell.setCellValue(resultSet.getDouble("T점수G"));
			cell = row.createCell(15);
			cell.setCellValue(resultSet.getDouble("환산점수G"));

			cell = row.createCell(16);
			cell.setCellValue(resultSet.getDouble("T점수H"));
			cell = row.createCell(17);
			cell.setCellValue(resultSet.getDouble("환산점수H"));

			cell = row.createCell(18);
			cell.setCellValue(resultSet.getDouble("T점수I"));
			cell = row.createCell(19);
			cell.setCellValue(resultSet.getDouble("환산점수I"));

			cell = row.createCell(20);
			cell.setCellValue(resultSet.getDouble("T점수J"));
			cell = row.createCell(21);
			cell.setCellValue(resultSet.getDouble("환산점수J"));

			i++;
		}
		FileOutputStream fo = new FileOutputStream(
				new File(application.getRealPath("/tmpDownload/") + "C그룹.xlsx"));

		workbook.write(fo);
		fo.close();
		workbook.close();
		System.out.println("exceldatabase.xlsx written successfully");
	} catch (Exception e) {

	} finally {

	}
%>
<%
	//
	request.setCharacterEncoding("UTF-8");
	String savePath = request.getSession().getServletContext().getRealPath("/tmpDownload");

	// 서버에 실제 저장된 파일명
	String filename = "C그룹.xlsx";

	// 실제 내보낼 파일명
	String orgfilename = "테스트C그룹.xlsx";

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
<title>파일다운로드 테스트</title>
</head>
<body>
</body>
</html>