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
<%@ page import="���л���Ȳ.���л���ȲEditAction"%>
<%@ page import="defaultMethod.*"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "���л���Ȳ2.jsp";
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
	File tmpFile = new File(file.getAbsolutePath()); //�ӽ� ����� ����
	Workbook wb = WorkbookFactory.create(new File(file.getPath()));
	try {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost:3306/schoolData?autoReconnect=true", "root", "123123");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Sheet sheet = wb.getSheetAt(0);

		Row row;
		DataFormatter formatter = new DataFormatter(Locale.US);

		���л���ȲEditAction edit = new ���л���ȲEditAction();
		String �Էºμ� = (String) session.getAttribute("id");

		int �⵵ = 0;

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);

			�⵵ = (int) row.getCell(0).getNumericCellValue();
			String �а��� = formatter.formatCellValue(row.getCell(1));
			int �� = (int) row.getCell(2).getNumericCellValue();

			String sql = "insert into ���л���Ȳ(�⵵,����,�а���,�л�����,��������,������,������,��,���������л������,��ü���л������,���л������,�Էºμ�) values(?,?,?,?,?,?,?,?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, "10.01");
			pstmt.setString(3, �а���);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, ��);
			pstmt.setFloat(9, 0);
			pstmt.setFloat(10, 0);
			pstmt.setFloat(11, 0);
			pstmt.setString(12,�Էºμ�);
			pstmt.execute();

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
		npe.printStackTrace();
		System.out.print("���� ������ �ΰ��� �ֽ��ϴ�");
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