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
<%@ page import="강의공개실적.강의공개실적EditAction"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "강의공개실적.jsp";
	opener.location.reload();
	window.opener.alert("업로드를 완료하였습니다");
	window.open("about:blank", "_self").close();
</script>
<%
	String saveDir = application.getRealPath("/Upload/");
	int maxSize = 1024 * 1024 * 100;
	String encType = "euc-kr";

	MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType,
			new DefaultFileRenamePolicy());

	File file = multipartRequest.getFile("file");
	File tmpFile = new File(file.getAbsolutePath()); //임시 저장된 파일
	Workbook wb = WorkbookFactory.create(new File(file.getPath()));
	try {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost:3306/schoolData?autoReconnect=true", "root", "123123");
		PreparedStatement pstm = null;

		Sheet sheet = wb.getSheetAt(0);

		Row row;
		int 년도 = 0;
		강의공개실적EditAction ea = new 강의공개실적EditAction();
		String 입력부서 = (String) session.getAttribute("id");

		DataFormatter formatter = new DataFormatter(Locale.US);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line 번호(멈춘곳 다음이 에러가 발생한 라인입니다.) :"+i);
			
			년도 = (int) row.getCell(0).getNumericCellValue();
			String majorname = formatter.formatCellValue(row.getCell(1));
			int B = (int) row.getCell(2).getNumericCellValue();
			int C = (int) row.getCell(3).getNumericCellValue();
			int D = (int) row.getCell(4).getNumericCellValue();
			int E = (int) row.getCell(5).getNumericCellValue();
			int F = (int) row.getCell(6).getNumericCellValue();
			int G = (int) row.getCell(7).getNumericCellValue();

			String sql = "INSERT INTO 강의공개실적(년도,학과명,강의동영상B,이러닝강의C,강의자료D,강의동영상E,이러닝강의F,강의자료G,입력부서) VALUES(?,?,?,?,?,?,?,?,?)";
			pstm = (PreparedStatement) con.prepareStatement(sql);
			pstm.setInt(1, 년도);
			pstm.setString(2, majorname);
			pstm.setInt(3, B);
			pstm.setInt(4, C);
			pstm.setInt(5, D);
			pstm.setInt(6, E);
			pstm.setInt(7, F);
			pstm.setInt(8, G);
			pstm.setString(9, 입력부서);
			pstm.execute();

		}


		ea.비율재계산();
		
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
		System.out.print("엑셀 문서에 널값이 있습니다");
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