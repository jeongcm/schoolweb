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
<%@ page import="���ӱ���1�δ籹å������ֽ���.���ӱ���1�δ籹å������ֽ���EditAction"%>
<%@ page import="���ӱ���1�δ籹å������ֽ���.���ӱ���1�δ籹å������ֽ���Dao"%>
<%@ page import="defaultMethod.*"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "���ӱ���1�δ籹å������ֽ���.jsp";
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
		Sheet sheet = wb.getSheetAt(0);

		Row row;
		DataFormatter formatter = new DataFormatter(Locale.US);

		���ӱ���1�δ籹å������ֽ���EditAction edit = new ���ӱ���1�δ籹å������ֽ���EditAction();
		���ӱ���1�δ籹å������ֽ���Dao dao = new ���ӱ���1�δ籹å������ֽ���Dao();

		int �⵵ = 0;
		String �Էºμ� = (String) session.getAttribute("id");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line ��ȣ(����� ������ ������ �߻��� �����Դϴ�.) :"+i);

			�⵵ = (int) row.getCell(0).getNumericCellValue();
			String �а��� = formatter.formatCellValue(row.getCell(1));
			int ���и��Ǳ�å��������Ѿ�  = (int) row.getCell(2).getNumericCellValue();
			float ���ӱ���1�δ籹å��������Ѿ� = dao.���ӱ���1�δ籹å������ֽ���(�⵵, �а���);

			String sql = "insert into ���ӱ���1�δ籹å������ֽ���(�⵵,�а���,���и��Ǳ�å��������Ѿ�,���ӱ���1�δ籹å��������Ѿ�,�Էºμ�) values(?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			pstmt.setInt(3, ���и��Ǳ�å��������Ѿ�);
			pstmt.setFloat(4, ���ӱ���1�δ籹å��������Ѿ�);
			pstmt.setString(5, �Էºμ�);

			pstmt.execute();

		}

		
		edit.��������();

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
		tmpFile.delete();

	}
%>
<html>
<head>
</head>
<body>
</body>
</html>