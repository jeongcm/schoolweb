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
<%@ page import="�а���Ȳ.�а���ȲDao"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "�а���Ȳ.jsp";
	opener.location.reload();
	window.opener.alert("���ε带 �Ϸ��Ͽ����ϴ�");
	window.open("about:blank", "_self").close();
</script>
<%
	String saveDir = application.getRealPath("/Upload/");
	int maxSize = 1024 * 1024 * 100;
	String encType = "euc-kr";
	MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType,
			new DefaultFileRenamePolicy());
	File file = multipartRequest.getFile("file");
	File tmpFile = new File(file.getAbsolutePath());
	Workbook wb = WorkbookFactory.create(new File(file.getPath()));
	try {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost:3306/schoolData?autoReconnect=true", "root", "123123");
		PreparedStatement pstmt = null;

		String �Էºμ� = (String) session.getAttribute("id");
		�а���ȲDao dao=new �а���ȲDao();
		Sheet sheet = wb.getSheetAt(0);

		Row row;
		DataFormatter formatter = new DataFormatter(Locale.US);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);

			int �⵵ = (int) row.getCell(0).getNumericCellValue();
			String �ܰ����� = formatter.formatCellValue(row.getCell(1));
			String �а��� = formatter.formatCellValue(row.getCell(2));
			String _5��迭 = formatter.formatCellValue(row.getCell(3));
			String �й��迭1 = formatter.formatCellValue(row.getCell(4));
			String �ż����� = formatter.formatCellValue(row.getCell(5));
			String ��� = formatter.formatCellValue(row.getCell(6));

			String sql = "insert into �а���Ȳ(�⵵,�ܰ�����,�а���,_5��迭,�й��迭1,�ż�����,���,�Էºμ�) values(?,?,?,?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �ܰ�����);
			pstmt.setString(3, �а���);
			pstmt.setString(4, _5��迭);
			pstmt.setString(5, �й��迭1);
			pstmt.setString(6, �ż�����);
			pstmt.setString(7, ���);
			pstmt.setString(8, �Էºμ�);

			pstmt.execute();
			
			dao.�����а���update(�⵵, �а���);

		}
		pstmt.close();
		con.close();
		wb.close();

	} catch (ClassNotFoundException e) {
		System.out.println(e);
	} catch (SQLException ex) {
		System.out.println(ex);
	} catch (IOException ioe) {
		System.out.println(ioe);
	} catch (NullPointerException npe) {
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