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
<%@ page import="defaultMethod.defaultQuery"%>
<%@ page import="defaultMethod.defaultClass"%>
<%@ page import="취업현황외부.취업현황외부EditAction"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "취업자외부.jsp";
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

		PreparedStatement pstmt = null;
		Sheet sheet = wb.getSheetAt(0);

		Row row;
		DataFormatter formatter = new DataFormatter(Locale.US);
		
		String 입력부서 = (String) session.getAttribute("id");
		취업현황외부EditAction ea=new 취업현황외부EditAction();
		String 대학명=null;

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line 번호(멈춘곳 다음이 에러가 발생한 라인입니다.) :"+i);

			대학명 = formatter.formatCellValue(row.getCell(0));
			String 학과명 = formatter.formatCellValue(row.getCell(1));
			int 건강보험DB연계취업자 = (int) row.getCell(2).getNumericCellValue();
			int 해외취업자 = (int) row.getCell(3).getNumericCellValue();
			int 영농업취업자 = (int) row.getCell(4).getNumericCellValue();
			int 개인창작활동조사서 = (int) row.getCell(5).getNumericCellValue();
			int 일인창업자 = (int) row.getCell(6).getNumericCellValue();
			int 프리랜서 = (int) row.getCell(7).getNumericCellValue();
			String 비고 = formatter.formatCellValue(row.getCell(8));
			
			int 계 = 건강보험DB연계취업자 + 해외취업자 + 영농업취업자+개인창작활동조사서+일인창업자+프리랜서;

			String sql = "insert into 취업자외부(대학명,학과명,건강보험db연계취업자,해외취업자,영농업취업자,계,비고,입력부서,개인창작활동조사서,1인창업자,프리랜서) values(?,?,?,?,?,?,?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1,대학명);
			pstmt.setString(2, 학과명);
			pstmt.setInt(3, 건강보험DB연계취업자);
			pstmt.setInt(4, 해외취업자);
			pstmt.setInt(5, 영농업취업자);
			pstmt.setInt(6, 계);
			pstmt.setString(7, 비고);
			pstmt.setString(8, 입력부서);
			pstmt.setInt(9, 개인창작활동조사서);
			pstmt.setInt(10, 일인창업자);
			pstmt.setInt(11, 프리랜서);

			pstmt.execute();

		}

		ea.비율재계산();

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