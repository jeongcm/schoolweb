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
<%@ page import="defaultMethod.defaultQuery"%>
<%@ page import="defaultMethod.defaultClass"%>
<%@ page import="java.sql.*"%>
<%@ page import="Ư����.Ư����EditAction"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "�������.jsp";
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
		PreparedStatement pstm = null;
		Sheet sheet = wb.getSheetAt(0);

		Row row;
		int �⵵ = 0;
		String �Էºμ� = (String) session.getAttribute("id");
		Ư����EditAction ea=new Ư����EditAction();
		DataFormatter formatter = new DataFormatter(Locale.US);

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line ��ȣ(����� ������ ������ �߻��� �����Դϴ�.) :"+i);
			
			�⵵ = (int) row.getCell(0).getNumericCellValue();
			String �а��� = formatter.formatCellValue(row.getCell(1));
			String ��ǥ�߸���= formatter.formatCellValue(row.getCell(2));
			String ��������= formatter.formatCellValue(row.getCell(3));
			float ���ױ���� =(float) row.getCell(4).getNumericCellValue();

			String sql = "insert into �������(�⵵,�а���,��ǥ�߸���,��������,���ױ����,�Էºμ�) values(?,?,?,?,?,?);";

			pstm = (PreparedStatement) con.prepareStatement(sql);
			pstm.setInt(1, �⵵);
			pstm.setString(2, �а���);
			pstm.setString(3, ��ǥ�߸���);
			pstm.setString(4, ��������);
			pstm.setFloat(5, ���ױ����);
			pstm.setString(6, �Էºμ�);
			pstm.execute();

		}

		ea.��������();

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
	tmpFile.delete();
	} catch (IllegalStateException ise) {
		System.out.println(ise);
		tmpFile.delete();
	} finally {

	}
%>
<html>
<head>
</head>
<body>
</body>
</html>