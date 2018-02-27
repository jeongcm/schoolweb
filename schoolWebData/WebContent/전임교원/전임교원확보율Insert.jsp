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
<%@ page import="���ӱ���Ȯ����.���ӱ���Ȯ����EditAction"%>
<%@ page import="���ӱ���Ȯ����.���ӱ���Ȯ����Dao"%>
<%@ page import="defaultMethod.*"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "���ӱ���Ȯ����.jsp";
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

		���ӱ���Ȯ����EditAction edit = new ���ӱ���Ȯ����EditAction();
		���ӱ���Ȯ����Dao dao = new ���ӱ���Ȯ����Dao();

		int �⵵ = 0;
		String �Էºμ� = (String) session.getAttribute("id");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line ��ȣ(����� ������ ������ �߻��� �����Դϴ�.) :" + i);

			�⵵ = (int) row.getCell(0).getNumericCellValue();
			String �а��� = formatter.formatCellValue(row.getCell(1));
			int ���п������� = (int) row.getCell(2).getNumericCellValue();
			int ���п����л� = (int) row.getCell(3).getNumericCellValue();
			int �л������������ӱ��� = (int) row.getCell(4).getNumericCellValue();
			int ���л��������ӱ��� = (int) row.getCell(5).getNumericCellValue();

			int �л����� = dao.�л�����(�⵵, �а���);
			int �������� = dao.��������(�⵵, �а���);
			int ���л� = dao.���л�(�⵵, �а���);
			String _5��迭 = dao._5��迭(�⵵, �а���);

			int �����л����� = �л����� - ��������;

			int �л������� = �����л����� + ���п�������;
			int ���л��� = ���л� + ���п����л�;

			System.out.println("�а���"+�а���+"����"+�����л�����+"��������"+��������);
			int ����_�к�_���� = edit.������������(_5��迭, �����л�����);
			int ����_�к�_���л� = edit.������������(_5��迭, ���л�);
			int ����_���п�_���� = edit.������������(_5��迭, ���п�������);
			int ����_���п�_���л� = edit.������������(_5��迭, ���п����л�);
			int ����_��_���� = edit.������������(_5��迭, �л�������);
			int ����_��_���л� = edit.������������(_5��迭, ���л���);

			float �л���_���� = edit.�л���_����(�л�������, �л������������ӱ���);
			float �л���_���л� = edit.�л���_���л�(���л���, ���л��������ӱ���);
			float Ȯ����_���� = edit.Ȯ����_����(�л������������ӱ���, ����_��_����);
			float Ȯ����_���л� = edit.Ȯ����_���л�(���л��������ӱ���, ����_��_���л�);

			float ���ӱ���Ȯ���� = Math.min(Ȯ����_����, Ȯ����_���л�);

			System.out.println("�а��� :"+ �а��� + "�����л����� : " + �����л����� + "����_�к�_����: " + ����_�к�_���� + " ����_�к�_���л� : " + ����_�к�_���л�);

			String sql = "insert into ���ӱ���Ȯ����(�⵵,�а���,���п�������,���п����л�,�л������������ӱ���,���л��������ӱ���,���ӱ���Ȯ����,�����л�����,����_�к�_����,����_�к�_���л�,����_���п�_����,����_���п�_���л�,����_��_����,����_��_���л�,�л���_����,�л���_���л�,Ȯ����_����,Ȯ����_���л�,�Էºμ�) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setInt(1, �⵵);
			pstmt.setString(2, �а���);
			pstmt.setInt(3, ���п�������);
			pstmt.setInt(4, ���п����л�);
			pstmt.setInt(5, �л������������ӱ���);
			pstmt.setInt(6, ���л��������ӱ���);
			pstmt.setFloat(7, ���ӱ���Ȯ����);
			pstmt.setInt(8, �����л�����);
			pstmt.setInt(9, ����_�к�_����);
			pstmt.setInt(10, ����_�к�_���л�);
			pstmt.setInt(11, ����_���п�_����);
			pstmt.setInt(12, ����_���п�_���л�);
			pstmt.setInt(13, ����_��_����);
			pstmt.setInt(14, ����_��_���л�);
			pstmt.setFloat(15, �л���_����);
			pstmt.setFloat(16, �л���_���л�);
			pstmt.setFloat(17, Ȯ����_����);
			pstmt.setFloat(18, Ȯ����_���л�);
			pstmt.setString(19, �Էºμ�);

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