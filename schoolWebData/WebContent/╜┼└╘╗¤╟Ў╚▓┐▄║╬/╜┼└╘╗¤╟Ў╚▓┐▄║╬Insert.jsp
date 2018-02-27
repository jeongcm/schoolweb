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
<%@ page import="defaultMethod.*"%>
<%@ page import="���Ի���Ȳ�ܺ�.���Ի���Ȳ�ܺ�EditAction"%>
<%@ page import="���Ի���Ȳ�ܺ�.���Ի���Ȳ�ܺ�Dao"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "���Ի���Ȳ�ܺ�.jsp";
	opener.location.reload();
	window.opener.alert("���ε带 �Ϸ��Ͽ����ϴ�");
	window.open("about:blank", "_self").close();
</script>
<%
	//���� ���ε� �κ�.
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

		���Ի���Ȳ�ܺ�EditAction edit = new ���Ի���Ȳ�ܺ�EditAction();
		���Ի���Ȳ�ܺ�Dao dao = new ���Ի���Ȳ�ܺ�Dao();
		String �Էºμ� = (String) session.getAttribute("id");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line ��ȣ(����� ������ ������ �߻��� �����Դϴ�.) :"+i);
			
			String ���и� = formatter.formatCellValue(row.getCell(0));
			String �а��� = formatter.formatCellValue(row.getCell(1));
			int �����ڼ� = (int) row.getCell(2).getNumericCellValue();
			int �����ο� = (int) row.getCell(3).getNumericCellValue();
			String ��� = formatter.formatCellValue(row.getCell(4));
			
			float ���Ի������ = edit.���Ի������(�����ڼ�, �����ο�);

			String sql = "insert into ���Ի���Ȳ�ܺ�(���и�,�а���,�����������ڼ�,�����������ο�,���Ի������,���,�Էºμ�) values(?,?,?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, ���и�);
			pstmt.setString(2, �а���);
			pstmt.setInt(3, �����ڼ�);
			pstmt.setInt(4, �����ο�);
			pstmt.setFloat(5, ���Ի������);
			pstmt.setString(6, ���);
			pstmt.setString(7,�Էºμ�);
			pstmt.execute();

		}
		
		String column = "���Ի������";
		String table = "���Ի���Ȳ�ܺ�";
		float ��� = (float) (Math.round(defaultQuery.�ܺ�avg(column, table) * 100) / 100.0);
		float ǥ������ = (float) (Math.round(defaultQuery.�ܺ�std(column, table) * 100) / 100.0);

		ArrayList<String> ���и�� = defaultQuery.�ܺδ��и��(table);
		for (String ���и� : ���и��) {
			float ���Ի������ = defaultQuery.�ܺκ���(���и�, column, table);
			float T���� = defaultClass.T����(���Ի������, ���, ǥ������);
			defaultQuery.�ܺ�updateT(T����, ���и�, table);
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
		ise.printStackTrace();
	} 
%>
<html>
<head>
</head>
<body>
</body>
</html>