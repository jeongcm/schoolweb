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
<%@ page import="�ߵ�Ż�����ܺ�.�ߵ�Ż�����ܺ�EditAction"%>
<%@ page import="�ߵ�Ż�����ܺ�.�ߵ�Ż�����ܺ�Dao"%>
<%@ page import="defaultMethod.*"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "�ߵ�Ż����.jsp";
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

		�ߵ�Ż�����ܺ�EditAction edit = new �ߵ�Ż�����ܺ�EditAction();
		�ߵ�Ż�����ܺ�Dao dao = new �ߵ�Ż�����ܺ�Dao();
		String �Էºμ� = (String) session.getAttribute("id");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line ��ȣ(����� ������ ������ �߻��� �����Դϴ�.) :"+i);
			
			String ���и� = formatter.formatCellValue(row.getCell(0));
			String �а��� = formatter.formatCellValue(row.getCell(1));
			int �����л��� = (int) row.getCell(2).getNumericCellValue();
			int Ÿ�а������� = (int) row.getCell(3).getNumericCellValue();
			int �̵�� = (int) row.getCell(4).getNumericCellValue();
			int �̺��� = (int) row.getCell(5).getNumericCellValue();
			int ���� = (int) row.getCell(6).getNumericCellValue();
			int �л��� = (int) row.getCell(7).getNumericCellValue();
			int ��Ÿ = (int) row.getCell(8).getNumericCellValue();

			int �� = edit.��(�̵��, �̺���, ����, �л���, ��Ÿ);
			float �ߵ�Ż���� = edit.�ߵ�Ż�����ܺ�(�����л���, ��);

			String sql = "insert into �ߵ�Ż�����ܺ�(���и�,�а���,�����л���,�̵��,�̺���,����,�л���,��Ÿ,��,�ߵ�Ż����,Ÿ�а�������,�Էºμ�) values(?,?,?,?,?,?,?,?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, ���и�);
			pstmt.setString(2, �а���);
			pstmt.setInt(3, �����л���);
			pstmt.setInt(4, �̵��);
			pstmt.setInt(5, �̺���);
			pstmt.setInt(6, ����);
			pstmt.setInt(7, �л���);
			pstmt.setInt(8, ��Ÿ);
			pstmt.setInt(9, ��);
			pstmt.setFloat(10, �ߵ�Ż����);
			pstmt.setInt(11, Ÿ�а�������);
			pstmt.setString(12, �Էºμ�);

			pstmt.execute();

		}

		String column = "�ߵ�Ż����";
		String table = "�ߵ�Ż�����ܺ�";

		float ��� = (float) (Math.round(defaultQuery.�ܺ�avg(column, table) * 100) / 100.0);
		float ǥ������ = (float) (Math.round(defaultQuery.�ܺ�std(column, table) * 100) / 100.0);
		
		System.out.println("average" + ��� + "std" + ǥ������);

		ArrayList<String> ���и�� = defaultQuery.�ܺδ��и��(table);
		for (String ���и� : ���и��) {
			float �ߵ�Ż���� = defaultQuery.�ܺκ���(���и�, column, table);
			float T���� = defaultClass.T����(�ߵ�Ż����, ���, ǥ������);
			System.out.println("jungdo" + �ߵ�Ż���� + "t" + T����);
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