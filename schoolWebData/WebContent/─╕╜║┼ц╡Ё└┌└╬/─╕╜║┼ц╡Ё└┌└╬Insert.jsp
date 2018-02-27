<%@page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.*,java.io.*"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="org.apache.poi.ss.usermodel.DataFormatter"%>
<%@page import="org.apache.poi.ss.usermodel.Row"%>
<%@page import="org.apache.poi.ss.usermodel.Sheet"%>
<%@page import="org.apache.poi.ss.usermodel.Workbook"%>
<%@page import="org.apache.poi.ss.usermodel.WorkbookFactory"%>
<%@ page import="java.sql.*"%>
<%@ page import="캡스톤디자인.캡스톤디자인EditAction"%>
<%@ page import="defaultMethod.defaultQuery"%>
<%@ page import="defaultMethod.defaultClass"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "캡스톤디자인.jsp";
	opener.location.reload();
	window.opener.alert("업로드를 완료하였습니다");
	window.open("about:blank", "_self").close();
</script>
<%
	String saveDir = application.getRealPath("/Upload/");
	int maxSize = 1024 * 1024 * 100;
	String encType = "euc-kr";

	MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType,
			new DefaultFileRenamePolicy());

	File file = multipartRequest.getFile("file");
	File tmpFile = new File(file.getAbsolutePath()); //임시 저장된 파일

	Workbook wb = WorkbookFactory.create(new File(file.getPath()));

	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost:3306/schoolData?autoReconnect=true", "root", "123123");
		PreparedStatement pstm = null;

		Sheet sheet = wb.getSheetAt(0);
		System.out.println(sheet.getRow(1).getCell(0));

		Row row;
		int 년도 = 0;
		String 입력부서 = (String) session.getAttribute("id");
		캡스톤디자인EditAction ea = new 캡스톤디자인EditAction();

		DataFormatter formatter = new DataFormatter(Locale.US);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line 번호(멈춘곳 다음이 에러가 발생한 라인입니다.) :"+i);
			
			년도 = (int) row.getCell(0).getNumericCellValue();
			String 학과명 = formatter.formatCellValue(row.getCell(1));

			int 이수1학기 = (int) row.getCell(2).getNumericCellValue();
			int 이수2학기 = (int) row.getCell(3).getNumericCellValue();
			int 대상1학기 = (int) row.getCell(4).getNumericCellValue();
			int 대상2학기 = (int) row.getCell(5).getNumericCellValue();

			int 이수합계 = 이수1학기 + 이수2학기;
			int 대상합계 = 대상1학기 + 대상2학기;

			float 이수학생비율 = ea.이수학생비율(이수합계, 대상합계);

			String sql = "insert into 캡스톤디자인(년도,학과명,이수1학기,이수2학기,대상1학기,대상2학기,이수학생비율,입력부서) values(?,?,?,?,?,?,?,?);";

			pstm = (PreparedStatement) con.prepareStatement(sql);
			pstm.setInt(1, 년도);
			pstm.setString(2, 학과명);
			pstm.setInt(3, 이수1학기);
			pstm.setInt(4, 이수2학기);
			pstm.setInt(5, 대상1학기);
			pstm.setInt(6, 대상2학기);
			pstm.setFloat(7, 이수학생비율);
			pstm.setString(8, 입력부서);
			pstm.execute();

		}

		ea.비율재계산();
		
		pstm.close();
		con.close();
		wb.close();

	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException ex) {
		ex.printStackTrace();
	} catch (IOException ioe) {
		ioe.printStackTrace();
	} catch (NullPointerException npe) {
		npe.printStackTrace();
		if (tmpFile.exists()) {
			wb.close();
			tmpFile.delete();
		}
%>
<script>
	closePopup();
</script>
<%
	tmpFile.delete();
	} catch (IllegalStateException ise) {
		System.out.println(ise);
		tmpFile.delete();
	}
%>
<html>
<head>
</head>
<body>
</body>
</html>