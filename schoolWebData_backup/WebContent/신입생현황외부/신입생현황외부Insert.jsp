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
<%@ page import="신입생현황외부.신입생현황외부EditAction"%>
<%@ page import="신입생현황외부.신입생현황외부Dao"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "신입생현황외부.jsp";
	opener.location.reload();
	window.opener.alert("업로드를 완료하였습니다");
	window.open("about:blank", "_self").close();
</script>
<%
	//파일 업로드 부분.
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

		신입생현황외부EditAction edit = new 신입생현황외부EditAction();
		신입생현황외부Dao dao = new 신입생현황외부Dao();
		String 입력부서 = (String) session.getAttribute("id");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line 번호(멈춘곳 다음이 에러가 발생한 라인입니다.) :"+i);
			
			String 대학명 = formatter.formatCellValue(row.getCell(0));
			String 학과명 = formatter.formatCellValue(row.getCell(1));
			int 입학자수 = (int) row.getCell(2).getNumericCellValue();
			int 모집인원 = (int) row.getCell(3).getNumericCellValue();
			String 비고 = formatter.formatCellValue(row.getCell(4));
			
			float 신입생충원율 = edit.신입생충원율(입학자수, 모집인원);

			String sql = "insert into 신입생현황외부(대학명,학과명,정원내입학자수,정원내모집인원,신입생충원율,비고,입력부서) values(?,?,?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, 대학명);
			pstmt.setString(2, 학과명);
			pstmt.setInt(3, 입학자수);
			pstmt.setInt(4, 모집인원);
			pstmt.setFloat(5, 신입생충원율);
			pstmt.setString(6, 비고);
			pstmt.setString(7,입력부서);
			pstmt.execute();

		}
		
		String column = "신입생충원율";
		String table = "신입생현황외부";
		float 평균 = (float) (Math.round(defaultQuery.외부avg(column, table) * 100) / 100.0);
		float 표준편차 = (float) (Math.round(defaultQuery.외부std(column, table) * 100) / 100.0);

		ArrayList<String> 대학목록 = defaultQuery.외부대학목록(table);
		for (String 대학명 : 대학목록) {
			float 신입생충원율 = defaultQuery.외부비율(대학명, column, table);
			float T점수 = defaultClass.T점수(신입생충원율, 평균, 표준편차);
			defaultQuery.외부updateT(T점수, 대학명, table);
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
		ise.printStackTrace();
	} 
%>
<html>
<head>
</head>
<body>
</body>
</html>