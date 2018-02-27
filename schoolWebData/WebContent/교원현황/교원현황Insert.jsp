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
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "외국인학생비율.jsp";
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
		String 입력부서 = (String) session.getAttribute("id");

		Sheet sheet = wb.getSheetAt(0);

		Row row;
		DataFormatter formatter = new DataFormatter(Locale.US);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);

			int year = (int) row.getCell(0).getNumericCellValue();
			String majorname = formatter.formatCellValue(row.getCell(1));
			int 일학기 = (int) row.getCell(2).getNumericCellValue();
			int 이학기 = (int) row.getCell(3).getNumericCellValue();

			String sql = "INSERT INTO 교원현황(년도,학과명,일학기,이학기,입력부서) VALUES(?,?,?,?,?)";
			pstm = (PreparedStatement) con.prepareStatement(sql);
			pstm.setInt(1, year);
			pstm.setString(2, majorname);
			pstm.setInt(3, 일학기);
			pstm.setInt(4, 이학기);
			pstm.setString(5, 입력부서);

			pstm.execute();

		}
		pstm.close();
		con.close();
		wb.close();

	} catch (ClassNotFoundException e) {
		System.out.println(e);
	} catch (SQLException ex) {
		System.out.println(ex);
	} catch (IOException ioe) {
		System.out.println(ioe);
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

	} finally {

	}
%>
<html>
<head>
</head>
<body>
</body>
</html>