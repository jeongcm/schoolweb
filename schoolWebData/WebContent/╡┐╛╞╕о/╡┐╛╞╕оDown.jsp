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
		ResultSet resultSet = statement.executeQuery("select 학과.년도,단과대학,학과.학과명,동아리참여비율,참여.T점수," 
				+ "sum(if(구분='취업',회원수,0)) 취업,"
				+ "sum(if(구분='학습',회원수,0)) 학습,"
				+ "sum(if(구분='창업',회원수,0)) 창업," 
				+ "sum(if(구분='문예',회원수,0)) 문예,"
				+ "sum(if(구분='봉사',회원수,0)) 봉사," 
				+ "sum(if(구분='취미',회원수,0)) 취미,"
				+ "sum(if(구분='기타',회원수,0)) 기타,"
				+ "sum(회원수) 계,"
				+	 " 계 재학생수 "
				+ "from 학과현황 학과 join 동아리참여비율 참여 on 학과.학과명=참여.학과명 and 학과.년도=참여.년도 "
				+ "join 동아리 on 학과.학과명=동아리.학과명 "
				+ "join 재학생현황 on 학과.학과명=재학생현황.학과명 and 학과.년도=재학생현황.년도 where 기준=4.01"
				+ "group by 학과.학과명  ");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("동아리");
		XSSFRow row = spreadsheet.createRow(0);
		XSSFCell cell;
		cell = row.createCell(0);
		cell.setCellValue("년도");
		cell = row.createCell(1);
		cell.setCellValue("단과대학");
		cell = row.createCell(2);
		cell.setCellValue("학과명");
		cell = row.createCell(3);
		cell.setCellValue("학습");
		cell = row.createCell(4);
		cell.setCellValue("취업");
		cell = row.createCell(5);
		cell.setCellValue("창업");
		cell = row.createCell(6);
		cell.setCellValue("연구");
		cell = row.createCell(7);
		cell.setCellValue("봉사");
		cell = row.createCell(8);
		cell.setCellValue("취미");
		cell = row.createCell(9);
		cell.setCellValue("기타");
		cell = row.createCell(10);
		cell.setCellValue("계");
		cell = row.createCell(11);
		cell.setCellValue("재학생수");
		cell = row.createCell(12);
		cell.setCellValue("동아리참여비율");
		cell = row.createCell(13);
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
			cell.setCellValue(resultSet.getInt("학습"));
			cell = row.createCell(4);
			cell.setCellValue(resultSet.getInt("취업"));
			cell = row.createCell(5);
			cell.setCellValue(resultSet.getInt("창업"));
			cell = row.createCell(6);
			cell.setCellValue(resultSet.getInt("연구"));
			cell = row.createCell(7);
			cell.setCellValue(resultSet.getInt("봉사"));
			cell = row.createCell(8);
			cell.setCellValue(resultSet.getInt("취미"));
			cell = row.createCell(9);
			cell.setCellValue(resultSet.getInt("기타"));
			cell = row.createCell(10);
			cell.setCellValue(resultSet.getInt("계"));
			cell = row.createCell(11);
			cell.setCellValue(resultSet.getInt("재학생수"));
			cell = row.createCell(12);
			cell.setCellValue(resultSet.getFloat("동아리참여비율"));
			cell = row.createCell(13);
			cell.setCellValue(resultSet.getDouble("T점수"));

			i++;
		}
		FileOutputStream fo = new FileOutputStream(
				new File(application.getRealPath("/tmpDownload/") + "동아리.xlsx"));

		workbook.write(fo);
		fo.close();
		workbook.close();
	} catch (Exception e) {
			e.printStackTrace();
	} 
	request.setCharacterEncoding("UTF-8");
	String savePath = request.getSession().getServletContext().getRealPath("/tmpDownload");

	String filename = "동아리.xlsx";

	String orgfilename = "동아리.xlsx";

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