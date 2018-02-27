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
<%@ page import="교육프로그램참여비율.*"%>
<%@ page import="defaultMethod.defaultQuery"%>
<%@ page import="defaultMethod.defaultClass"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "교육프로그램참여비율.jsp";
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
%>
<%
	try {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost:3306/schoolData?autoReconnect=true", "root", "123123");
		PreparedStatement pstm = null;

		Sheet sheet = wb.getSheetAt(0);

		Row row;
		int 년도= 0;
		String 학과명=null;
		교육프로그램참여비율Dao dao= new 교육프로그램참여비율Dao();
		교육프로그램참여비율EditAction ea=new 교육프로그램참여비율EditAction();
		String 입력부서 = (String) session.getAttribute("id");

		DataFormatter formatter = new DataFormatter(Locale.US);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			
			System.out.println("import line 번호(멈춘곳 다음이 에러가 발생한 라인입니다.) :"+i);
			
			년도 = (int) row.getCell(0).getNumericCellValue();
			String 운영부서명 = formatter.formatCellValue(row.getCell(1));
			String 프로그램명 = formatter.formatCellValue(row.getCell(2));
			학과명 = formatter.formatCellValue(row.getCell(3));
			int 학년 = (int) row.getCell(4).getNumericCellValue();
			String 학번 = formatter.formatCellValue(row.getCell(5));
			String 성명 = formatter.formatCellValue(row.getCell(6));
			String 비고 = formatter.formatCellValue(row.getCell(7));

			String sql = "INSERT INTO 교육프로그램운영내역(년도,운영부서명,프로그램명,학과명,학년,학번,성명,비고,입력부서) VALUES(?,?,?,?,?,?,?,?,?)";
			pstm = (PreparedStatement) con.prepareStatement(sql);
			pstm.setInt(1, 년도);
			pstm.setString(2, 운영부서명);
			pstm.setString(3, 프로그램명);
			pstm.setString(4, 학과명);
			pstm.setInt(5, 학년);
			pstm.setString(6, 학번);
			pstm.setString(7, 성명);
			pstm.setString(8, 비고);
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