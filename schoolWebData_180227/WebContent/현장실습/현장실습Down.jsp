<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="org.apache.poi.xssf.usermodel.*"%>
<%@ page import="com.mysql.jdbc.Connection"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connect = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost:3306/schoolData?autoReconnect=true", "root", "123123");
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"select *,1학기+2학기 합계,장기1학기+장기2학기 장기합계 from 현장실습 현장 join  학과현황 학과 on 현장.학과명 =학과.학과명 and 현장.년도=학과.년도 ;");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("현장 실습 이수 학생 비율");
		XSSFRow row = spreadsheet.createRow(0);
		XSSFCell cell;
		cell = row.createCell(0);
		cell.setCellValue("년도");
		cell = row.createCell(1);
		cell.setCellValue("단과대학");
		cell = row.createCell(2);
		cell.setCellValue("학과명");
		cell = row.createCell(3);
		cell.setCellValue("1학기");
		cell = row.createCell(4);
		cell.setCellValue("2학기");
		cell = row.createCell(5);
		cell.setCellValue("합계");
		cell = row.createCell(6);
		cell.setCellValue("장기1학기");
		cell = row.createCell(7);
		cell.setCellValue("장기2학기");
		cell = row.createCell(8);
		cell.setCellValue("장기_합계");
		cell = row.createCell(9);
		cell.setCellValue("대상학생 수");
		cell = row.createCell(10);
		cell.setCellValue("이수학생비율");
		cell = row.createCell(11);
		cell.setCellValue("T점수");

		int i = 1;

		while (resultSet.next()) {

			row = spreadsheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(resultSet.getString("년도"));
			cell = row.createCell(1);
			cell.setCellValue(resultSet.getString("단과대학"));
			cell = row.createCell(2);
			cell.setCellValue(resultSet.getString("학과명"));
			cell = row.createCell(3);
			cell.setCellValue(resultSet.getInt("1학기"));
			cell = row.createCell(4);
			cell.setCellValue(resultSet.getInt("2학기"));
			cell = row.createCell(5);
			cell.setCellValue(resultSet.getInt("합계"));
			cell = row.createCell(6);
			cell.setCellValue(resultSet.getInt("장기1학기"));
			cell = row.createCell(7);
			cell.setCellValue(resultSet.getInt("장기2학기"));
			cell = row.createCell(8);
			cell.setCellValue(resultSet.getInt("장기합계"));
			cell = row.createCell(9);
			cell.setCellValue(resultSet.getFloat("대상학생수"));
			cell = row.createCell(10);
			cell.setCellValue(Math.round(resultSet.getFloat("이수학생비율") * 100) / 100.0);
			cell = row.createCell(11);
			cell.setCellValue(Math.round(resultSet.getFloat("T점수") * 100) / 100.0);
			i++;
		}
		FileOutputStream fo = new FileOutputStream(
				new File(application.getRealPath("/tmpDownload/") + "현장실습.xlsx"));
		workbook.write(fo);
		fo.close();
		workbook.close();
	} catch (Exception e) {
		e.printStackTrace();
	}

	request.setCharacterEncoding("UTF-8");
	String savePath = request.getSession().getServletContext().getRealPath("/tmpDownload");

	String filename = "현장실습.xlsx";

	String orgfilename = "현장실습.xlsx";

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
%>
<html>
<head>
</head>
<body>
</body>
</html>