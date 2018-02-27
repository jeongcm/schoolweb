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
<%@ page import="동아리.*"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "동아리.jsp";
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
		System.out.println(sheet.getRow(1).getCell(0));

		Row row;
		int 년도=0;
		String 학과명=null;
		String 입력부서 = (String) session.getAttribute("id");
		동아리Dao dao=new 동아리Dao();
		동아리EditAction ea=new 동아리EditAction();
		DataFormatter formatter = new DataFormatter(Locale.US);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			
			System.out.println("import line 번호(멈춘곳 다음이 에러가 발생한 라인입니다.) :"+i);
			
			년도 = (int) row.getCell(0).getNumericCellValue();
			String 관리부서 = formatter.formatCellValue(row.getCell(1));
			학과명 = formatter.formatCellValue(row.getCell(2));
			String 동아리명 = formatter.formatCellValue(row.getCell(3));
			String 구분 = formatter.formatCellValue(row.getCell(4));
			String 지도교수 = formatter.formatCellValue(row.getCell(5));
			String 학생대표학년 = formatter.formatCellValue(row.getCell(6));
			String 학생대표이름 = formatter.formatCellValue(row.getCell(7));
			int 회원수 = (int) row.getCell(8).getNumericCellValue();
			String 인정여부 = formatter.formatCellValue(row.getCell(9));
			int 예산지원액 = (int) row.getCell(10).getNumericCellValue();
			String 실적서류 = formatter.formatCellValue(row.getCell(11));
			String 비고 = formatter.formatCellValue(row.getCell(12));

			String sql = "insert into 동아리(년도,관리부서,학과명,동아리명,구분,지도교수,학생대표학년,학생대표이름,회원수,인정여부,예산지원액,실적서류,비고,입력부서) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			pstm = (PreparedStatement) con.prepareStatement(sql);
			pstm.setInt(1, 년도);
			pstm.setString(2, 관리부서);
			pstm.setString(3, 학과명);
			pstm.setString(4, 동아리명);
			pstm.setString(5, 구분);
			pstm.setString(6, 지도교수);
			pstm.setString(7, 학생대표학년);
			pstm.setString(8, 학생대표이름);
			pstm.setInt(9, 회원수);
			pstm.setString(10, 인정여부);
			pstm.setInt(11, 예산지원액);
			pstm.setString(12, 실적서류);
			pstm.setString(13, 비고);
			pstm.setString(14, 입력부서);
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