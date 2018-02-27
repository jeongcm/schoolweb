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
<%@ page import="������ܺ�.������ܺ�EditAction"%>
<%@ page import="������ܺ�.������ܺ�Dao"%>
<%@ page import="defaultMethod.*"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "������ܺ�.jsp";
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

		������ܺ�EditAction edit = new ������ܺ�EditAction();
		������ܺ�Dao dao = new ������ܺ�Dao();

		int �⵵ = 0;
		String �Էºμ� = (String) session.getAttribute("id");
		String ���и�=null;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line ��ȣ(����� ������ ������ �߻��� �����Դϴ�.) :"+i);
			
			���и� = formatter.formatCellValue(row.getCell(0));
			String �а��� = formatter.formatCellValue(row.getCell(1));
			int ���ӱ�����= (int) row.getCell(2).getNumericCellValue();
			int �߾����� = (int) row.getCell(3).getNumericCellValue();
			int ����ü = (int) row.getCell(4).getNumericCellValue();
			int �ΰ� = (int) row.getCell(5).getNumericCellValue();
			int �ܱ� = (int) row.getCell(6).getNumericCellValue();
			

			int �� = �߾�����+����ü+ �ΰ�+ �ܱ�;

			float ������ܺ� =edit.������ܺ�(���ӱ�����, ��);

			String sql = "insert into ������ܺ�(���и�,�а���,�߾�����,����ü,�ΰ�,�ܱ�,������,�Էºμ�) values(?,?,?,?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, ���и�);
			pstmt.setString(2, �а���);
			pstmt.setInt(3, �߾�����);
			pstmt.setInt(4, ����ü);
			pstmt.setInt(5, �ΰ�);
			pstmt.setInt(6, �ܱ�);
			pstmt.setFloat(7, ������ܺ�);
			pstmt.setString(8, �Էºμ�);

			pstmt.execute();

		}

		String column = "������";
		String table = "������ܺ�";

		float ��� = (float) (Math.round(defaultQuery.�ܺ�avg(column, table) * 100) / 100.0);
		float ǥ������ = (float) (Math.round(defaultQuery.�ܺ�std(column, table) / 100.0));

		ArrayList<String> ���и�� = defaultQuery.�ܺδ��и��(table);

		for (String ���� : ���и��) {
			float ������ܺ� = defaultQuery.�ܺκ���(���и�, column, table);
			float T���� = defaultClass.T����(������ܺ�, ���, ǥ������);
			defaultQuery.�ܺ�updateT(T����, ���и�, table);
		}

		pstmt.close();
		con.close();
		wb.close();

	} catch (ClassNotFoundException e) {
		System.out.println(e);
	} catch (SQLException ex) {
		ex.printStackTrace();
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
	} 
%>
<html>
<head>
</head>
<body>
</body>
</html>