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
<%@ page import="�����.�����EditAction"%>
<%@ page import="defaultMethod.defaultQuery"%>
<%@ page import="defaultMethod.defaultClass"%>
<%@ page import="�����Ȳ.*"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "�����.jsp";
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
		�����EditAction edit = new �����EditAction();
		�����ȲEditAction ea = new �����ȲEditAction();
		�����ȲDao ��Ȳdao = new �����ȲDao();
		int �⵵ = 0;
		String �Էºμ� = (String) session.getAttribute("id");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line ��ȣ(����� ������ ������ �߻��� �����Դϴ�.) :"+i);
			
			�⵵ = (int) row.getCell(0).getNumericCellValue();
			String �а��� = formatter.formatCellValue(row.getCell(1));
			int �ǰ�����DB��������� = (int) row.getCell(2).getNumericCellValue();
			int �ؿ������ = (int) row.getCell(3).getNumericCellValue();
			int ���������� = (int) row.getCell(4).getNumericCellValue();
			int ��������� = (int) row.getCell(5).getNumericCellValue();
			int ����â��Ȱ�����缭 = (int) row.getCell(6).getNumericCellValue();
			int ����â���� = (int) row.getCell(7).getNumericCellValue();
			int �������� = (int) row.getCell(8).getNumericCellValue();

			
			int �� = �ǰ�����DB��������� + �ؿ������ + ����������+����â��Ȱ�����缭+����â����+��������;

			
			String sql = "insert into �����(�⵵,�а���,�ǰ�����db���������,�ؿ������,����������,���������,��,�Էºμ�,����â��Ȱ�����缭,1��â����,��������) values(?,?,?,?,?,?,?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			pstmt.setInt(3, �ǰ�����DB���������);
			pstmt.setInt(4, �ؿ������);
			pstmt.setInt(5, ����������);
			pstmt.setInt(6, ���������);
			pstmt.setInt(7, ��);
			pstmt.setString(8, �Էºμ�);
			pstmt.setInt(9, ����â��Ȱ�����缭);
			pstmt.setInt(10, ����â����);
			pstmt.setInt(11, ��������);
			pstmt.execute();

		}
		
		ea.��������();
		
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
	} finally {

	}
%>
<html>
<head>
</head>
<body>
</body>
</html>