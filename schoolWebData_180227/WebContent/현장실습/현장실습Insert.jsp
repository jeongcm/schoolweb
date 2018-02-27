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
<%@ page import="defaultMethod.defaultQuery"%>
<%@ page import="defaultMethod.defaultClass"%>
<%@ page import="����ǽ�.����ǽ�EditAction"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="/js/importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "����ǽ�.jsp";
	opener.location.reload();
	window.opener.alert("���ε带 �Ϸ��Ͽ����ϴ�");
	window.open("about:blank", "_self").close();
</script>
<%
	String saveDir = application.getRealPath("/Upload/");

	int maxSize = 1 * 1024 * 2024;

	String encType = "euc-kr";

	MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType,
			new DefaultFileRenamePolicy());

	File file = multipartRequest.getFile("file");
	File tmpFile = new File(file.getAbsolutePath());
	Workbook wb = WorkbookFactory.create(new File(file.getPath()));
	����ǽ�EditAction ea = new ����ǽ�EditAction();
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost:3306/schoolData?autoReconnect=true", "root", "123123");
		PreparedStatement pstm = null;
		Sheet sheet = wb.getSheetAt(0);
		Row row;

		int �⵵ = 0;
		String �Էºμ� = (String) session.getAttribute("id");

		DataFormatter formatter = new DataFormatter(Locale.US);

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line ��ȣ(����� ������ ������ �߻��� �����Դϴ�.) :"+i);
			
			�⵵ = (int) row.getCell(0).getNumericCellValue();
			String �а��� = formatter.formatCellValue(row.getCell(1));
			int _1�б� = (int) row.getCell(2).getNumericCellValue();
			int _2�б� = (int) row.getCell(3).getNumericCellValue();
			int ���1�б� = (int) row.getCell(4).getNumericCellValue();
			int ���2�б� = (int) row.getCell(5).getNumericCellValue();
			int ����л��� = (int) row.getCell(6).getNumericCellValue();

			int �հ� = _1�б� + _2�б�;
			int ����հ� = ���1�б� + ���2�б�;

			float result = (float) (((�հ� + (����հ� * 2)) / (����л��� * 1.0)) * 100);
			float �̼��л����� = ea.�̼��л�����(�հ�, ����հ�, ����л���);

			String sql = "INSERT INTO ����ǽ�(�⵵,�а���,1�б�,2�б�,���1�б�,���2�б�,����л���,�Էºμ�,�̼��л�����) VALUES(?,?,?,?,?,?,?,?,?)";

			pstm = (PreparedStatement) con.prepareStatement(sql);
			pstm.setInt(1, �⵵);
			pstm.setString(2, �а���);
			pstm.setInt(3, _1�б�);
			pstm.setInt(4, _2�б�);
			pstm.setInt(5, ���1�б�);
			pstm.setInt(6, ���2�б�);
			pstm.setInt(7, ����л���);
			pstm.setString(8, �Էºμ�);
			pstm.setFloat(9, �̼��л�����);
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
		ise.printStackTrace();
		tmpFile.delete();
	}
%>
<html>
<head>
</head>
<body>
</body>
</html>