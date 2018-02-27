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
		ResultSet resultSet = statement.executeQuery("select  distinct 학과.단과대학,학과.신설연도,재학생.*"
				+ " from 재학생현황 재학생 join 학과현황 학과 on 재학생.년도=학과.년도 and 학과.학과명=재학생.학과명 where 기준='04.01' "
				+ "order by 재학생.연번 desc  ");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("재학생 현황");
		XSSFRow row = spreadsheet.createRow(0);
		XSSFCell cell;
		cell = row.createCell(0);
		cell.setCellValue("년도");
		cell = row.createCell(1);
		cell.setCellValue("단과대학");
		cell = row.createCell(2);
		cell.setCellValue("학과명");
		cell = row.createCell(3);
		cell.setCellValue("신설연도");
		cell = row.createCell(4);
		cell.setCellValue("기준");
		cell = row.createCell(5);
		cell.setCellValue("학생정원");
		cell = row.createCell(6);
		cell.setCellValue("군휴학자");
		cell = row.createCell(7);
		cell.setCellValue("타학과전과자");
		cell = row.createCell(8);
		cell.setCellValue("정원내학생수");
		cell = row.createCell(9);
		cell.setCellValue("정원외학생수");
		cell = row.createCell(10);
		cell.setCellValue("계");
		cell = row.createCell(11);
		cell.setCellValue("전체 재학생 충원율");
		cell = row.createCell(12);
		cell.setCellValue("정원내 재학생 충원율");
		cell = row.createCell(13);
		cell.setCellValue("재학생 충원율");
		cell = row.createCell(14);
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
			cell.setCellValue(resultSet.getString("신설연도"));
			cell = row.createCell(4);
			cell.setCellValue(resultSet.getString("기준"));
			cell = row.createCell(5);
			cell.setCellValue(resultSet.getInt("학생정원"));
			cell = row.createCell(6);
			cell.setCellValue(resultSet.getInt("군휴학자"));
			cell = row.createCell(7);
			cell.setCellValue(resultSet.getInt("타학과전과자"));
			cell = row.createCell(8);
			cell.setCellValue(resultSet.getInt("정원내"));
			cell = row.createCell(9);
			cell.setCellValue(resultSet.getInt("정원외"));
			cell = row.createCell(10);
			cell.setCellValue(resultSet.getInt("계"));
			cell = row.createCell(11);
			cell.setCellValue(resultSet.getFloat("전체재학생충원율"));
			cell = row.createCell(12);
			cell.setCellValue(resultSet.getFloat("정원내재학생충원율"));
			cell = row.createCell(13);
			cell.setCellValue(resultSet.getFloat("재학생충원율"));
			cell = row.createCell(14);
			cell.setCellValue(resultSet.getFloat("T점수"));

			i++;
		}
		FileOutputStream fo = new FileOutputStream(
				new File(application.getRealPath("/tmpDownload/") + "재학생현황.xlsx"));

		workbook.write(fo);
		fo.close();
		workbook.close();

	} catch (Exception e) {
			e.printStackTrace();
	}
	request.setCharacterEncoding("UTF-8");
	String savePath = request.getSession().getServletContext().getRealPath("/tmpDownload");
	String filename = "재학생현황.xlsx";
	String orgfilename = "재학생현황.xlsx";

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
<title>파일다운로드</title>
</head>
<body>
</body>
</html>