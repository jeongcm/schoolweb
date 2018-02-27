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
		Statement statement2 = connect.createStatement();
		Statement statement3 = connect.createStatement();
		Statement statement4 = connect.createStatement();
		Statement statement5 = connect.createStatement();
		Statement statement6 = connect.createStatement();

		ResultSet resultSet = statement.executeQuery(
				"select max(학과.년도) 년도,단과대학,학과.학과명,학문계열1,T점수 중도탈락률T,(T점수*0.09) 중도탈락률환산 from 학과현황 학과 left outer join 중도탈락률현황 중도탈락률 on 학과.학과명=중도탈락률.학과명 group by 학과.학과명 order by 학과.연번 desc ;");
		ResultSet resultSet2 = statement2.executeQuery(
				"select 학과.학과명,T점수 재학생T,(T점수*0.09) 재학생환산 from 재학생현황 right outer join 학과현황 학과 on 재학생현황.학과명=학과.학과명 order by 학과.연번 desc ;");
		ResultSet resultSet3 = statement3.executeQuery(
				"select 학과.학과명,T점수 취업률T,(T점수*0.08) 취업률환산 from 취업현황 right outer join 학과현황 학과 on 취업현황.학과명=학과.학과명 order by 학과.연번 desc;");
		ResultSet resultSet4 = statement4.executeQuery(
				"select 학과.학과명,T점수 연구실적T,(T점수*0.07) 연구실적환산 from 연구실적 right outer join 학과현황 학과 on 연구실적.학과명=학과.학과명 order by 학과.연번 desc;");
		ResultSet resultSet5 = statement5.executeQuery(
				"select 학과.학과명,T점수 연구비T,(T점수*0.08) 연구비환산 from 연구비 right outer join 학과현황 학과 on 연구비.학과명=학과.학과명 order by 학과.연번 desc;");
		ResultSet resultSet6 = statement6.executeQuery(
				"select 학과.학과명,T점수 강의T,(T점수*0.09) 강의환산 from 개설강의담당비율 개설 right outer join 학과현황 학과 on 개설.학과명=학과.학과명 order by 학과.연번 desc;");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("D그룹");
		XSSFRow row = spreadsheet.createRow(0);
		XSSFCell cell;

		cell = row.createCell(0);
		cell.setCellValue("년도");
		cell = row.createCell(1);
		cell.setCellValue("단과대학");
		cell = row.createCell(2);
		cell.setCellValue("학과명");
		cell = row.createCell(3);
		cell.setCellValue("학문계열");
		cell = row.createCell(4);
		cell.setCellValue("중도탈락률T점수");
		cell = row.createCell(5);
		cell.setCellValue("중도탈락률환산점수");
		cell = row.createCell(6);
		cell.setCellValue("재학생T점수");
		cell = row.createCell(7);
		cell.setCellValue("재학생환산점수");
		cell = row.createCell(8);
		cell.setCellValue("취업률T점수");
		cell = row.createCell(9);
		cell.setCellValue("취업률환산점수");
		cell = row.createCell(10);
		cell.setCellValue("연구실적T점수");
		cell = row.createCell(11);
		cell.setCellValue("연구실적환산점수");
		cell = row.createCell(12);
		cell.setCellValue("교외연구비T점수");
		cell = row.createCell(13);
		cell.setCellValue("교외연구비환산점수");
		cell = row.createCell(14);
		cell.setCellValue("강의담당비율T점수");
		cell = row.createCell(15);
		cell.setCellValue("강의담당비율환산점수");
		cell = row.createCell(16);
		cell.setCellValue("D그룹환산점수(취업률제외)");
		cell = row.createCell(17);
		cell.setCellValue("D그룹환산점수");

		int i = 1;
		while (resultSet.next()&&resultSet2.next()&&resultSet3.next()&&resultSet4.next()&&resultSet5.next()&&resultSet6.next()) {

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
			cell.setCellValue(resultSet.getFloat("중도탈락률T"));
			cell = row.createCell(5);
			cell.setCellValue((float) (Math.round(resultSet.getFloat("중도탈락률환산") * 100) / 100.0));
			cell = row.createCell(6);
			cell.setCellValue(resultSet2.getFloat("재학생T"));
			cell = row.createCell(7);
			cell.setCellValue((float) (Math.round(resultSet2.getFloat("재학생환산") * 100) / 100.0));
			cell = row.createCell(8);
			cell.setCellValue(resultSet3.getFloat("취업률T"));
			cell = row.createCell(9);
			cell.setCellValue((float) (Math.round(resultSet3.getFloat("취업률환산") * 100) / 100.0));
			cell = row.createCell(10);
			cell.setCellValue(resultSet4.getFloat("연구실적T"));
			cell = row.createCell(11);
			cell.setCellValue((float) (Math.round(resultSet4.getFloat("연구실적환산") * 100) / 100.0));
			cell = row.createCell(12);
			cell.setCellValue(resultSet5.getFloat("연구비T"));
			cell = row.createCell(13);
			cell.setCellValue((float) (Math.round(resultSet5.getFloat("연구비환산") * 100) / 100.0));
			cell = row.createCell(14);
			cell.setCellValue(resultSet6.getFloat("강의T"));
			cell = row.createCell(15);
			cell.setCellValue((float) (Math.round(resultSet6.getFloat("강의환산") * 100) / 100.0));

			float 취업률제외환산 = (float) (Math.round(
					resultSet.getFloat("중도탈락률환산") + resultSet2.getFloat("재학생환산") + resultSet6.getFloat("강의환산")
							+ resultSet4.getFloat("연구실적환산") + resultSet5.getFloat("연구비환산") * 100)
					/ 100.0);

			float 환산 = (float) (Math.round(resultSet.getFloat("중도탈락률환산") + resultSet2.getFloat("재학생환산")
					+ resultSet6.getFloat("강의환산") + resultSet4.getFloat("연구실적환산") + resultSet5.getFloat("연구비환산")
					+ resultSet3.getFloat("취업률환산") * 100) / 100.0);

			cell = row.createCell(16);
			cell.setCellValue(취업률제외환산);
			cell = row.createCell(17);
			cell.setCellValue(환산);

			i++;
		}

		FileOutputStream fo = new FileOutputStream(
				new File(application.getRealPath("/tmpDownload/") + "D그룹.xlsx"));
		workbook.write(fo);
		fo.close();
		workbook.close();
	} catch (Exception e) {

	} finally {

	}
%>
<%
	//
	request.setCharacterEncoding("UTF-8");
	String savePath = request.getSession().getServletContext().getRealPath("/tmpDownload");

	System.out.println(savePath);

	// 서버에 실제 저장된 파일명
	String filename = "D그룹.xlsx";

	// 실제 내보낼 파일명
	String orgfilename = "D그룹.xlsx";

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