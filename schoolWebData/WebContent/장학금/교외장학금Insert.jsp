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
<%@ page import="교외장학금.교외장학금EditAction"%>
<%@ page import="교외장학금.교외장학금Dao"%>
<%@ page import="defaultMethod.*"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "교외장학금.jsp";
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

		PreparedStatement pstmt = null;
		Sheet sheet = wb.getSheetAt(0);
		System.out.println(sheet.getRow(1).getCell(0));

		Row row;
		DataFormatter formatter = new DataFormatter(Locale.US);

		교외장학금EditAction edit = new 교외장학금EditAction();
		교외장학금Dao dao = new 교외장학금Dao();
		
		int 년도 = 0;
		String 입력부서 = (String) session.getAttribute("id");


		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);

			년도 = (int) row.getCell(0).getNumericCellValue();
			String 학과명 = formatter.formatCellValue(row.getCell(1));
			int 교외장학금 = (int) row.getCell(2).getNumericCellValue();

			float 재학생평균 = dao.재학생평균(년도, 학과명);
			float _1인당교외장학금 = edit.교외장학금(재학생평균, 교외장학금);

			String sql = "insert into 교외장학금(년도,학과명,교외장학금,1인당교외장학금,입력부서) values(?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			pstmt.setInt(3, 교외장학금);
			pstmt.setFloat(4, _1인당교외장학금);
			pstmt.setString(5, 입력부서);

			pstmt.execute();

		}

		edit.비율재계산();
		
		pstmt.close();
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
		System.out.print("엑셀 문서에 널값이 있습니다");
		if (tmpFile.exists()) {
			wb.close();
			tmpFile.delete();
		}
%>
<script>
	closePopup();
</script>
<%
	} catch (IllegalStateException ise) {
		System.out.println(ise);
	} 
%>
<html>
<head>
</head>
<body>
</body>
</html>