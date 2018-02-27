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
<%@ page import="���л���Ȳ.���л���ȲDao"%>
<%@ page import="defaultMethod.*"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "���л���Ȳ.jsp";
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
		���л���ȲDao dao = new ���л���ȲDao();
		int �⵵ = 0;
		String �Էºμ� = (String) session.getAttribute("id");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line ��ȣ(����� ������ ������ �߻��� �����Դϴ�.) :"+i);

			�⵵ = (int) row.getCell(0).getNumericCellValue();
			String ���� = formatter.formatCellValue(row.getCell(2));
			String �а��� = formatter.formatCellValue(row.getCell(1));
			int �л����� = (int) row.getCell(3).getNumericCellValue();
			int �������� = (int) row.getCell(4).getNumericCellValue();
			int Ÿ�а������� = (int) row.getCell(5).getNumericCellValue();
			int ������ = (int) row.getCell(6).getNumericCellValue();
			int ������ = (int) row.getCell(7).getNumericCellValue();
			int �� = ������+������;
			float ���������л������ = edit.���������л������(������, �л�����, ��������,Ÿ�а�������);
			float ��ü���л������ = edit.��ü���л������(��, �л�����, ��������,Ÿ�а�������);
			float ���л������ = edit.���л������(��ü���л������, ���������л������);

			String sql = "insert into ���л���Ȳ(�⵵,����,�а���,�л�����,��������,Ÿ�а�������,������,������,��,���������л������,��ü���л������,���л������,�Էºμ�) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, ����);
			pstmt.setString(3, �а���);
			pstmt.setInt(4, �л�����);
			pstmt.setInt(5, ��������);
			pstmt.setInt(6, Ÿ�а�������);
			pstmt.setInt(7, ������);
			pstmt.setInt(8, ������);
			pstmt.setInt(9, ��);
			pstmt.setFloat(10, ���������л������);
			pstmt.setFloat(11, ��ü���л������);
			pstmt.setFloat(12, ���л������);
			pstmt.setString(13, �Էºμ�);
			pstmt.execute();

		}
		
		edit.��������();
		
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
	} 
%>
<html>
<head>
</head>
<body>
</body>
</html>