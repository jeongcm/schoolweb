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
<%@ page import="연구실적.연구실적EditAction"%>
<%@ page import="연구실적외부.연구실적외부Dao"%>
<%@ page import="defaultMethod.*"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "연구실적외부.jsp";
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

		연구실적EditAction edit = new 연구실적EditAction();
		연구실적외부Dao dao = new 연구실적외부Dao();

		String 대학명=null;
		String 입력부서 = (String) session.getAttribute("id");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line 번호(멈춘곳 다음이 에러가 발생한 라인입니다.) :"+i);
			
			대학명 = formatter.formatCellValue(row.getCell(0));
			String 학과명 = formatter.formatCellValue(row.getCell(1));
			int 전임교원수 = (int) row.getCell(2).getNumericCellValue();
			float 저서 = (float) row.getCell(3).getNumericCellValue();
			float 역서 = (float) row.getCell(4).getNumericCellValue();
			float 연구재단등재지 = (float) row.getCell(5).getNumericCellValue();
			float 연구재단등재후보 = (float) row.getCell(6).getNumericCellValue();
			float SCI급 = (float) row.getCell(7).getNumericCellValue();
			float SCOPUS학술지 = (float) row.getCell(8).getNumericCellValue();

			float 연구실적 = edit.연구실적(전임교원수, 저서, 역서, 연구재단등재지, 연구재단등재후보, SCI급, SCOPUS학술지);

			String sql = "insert into 연구실적외부(대학명,학과명,저서,역서,연구재단등재지,연구재단등재후보,SCI급,SCOPUS학술지,연구실적,입력부서,전임교원수) values(?,?,?,?,?,?,?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, 대학명);
			pstmt.setString(2, 학과명);
			pstmt.setFloat(3, 저서);
			pstmt.setFloat(4, 역서);
			pstmt.setFloat(5, 연구재단등재지);
			pstmt.setFloat(6, 연구재단등재후보);
			pstmt.setFloat(7, SCI급);
			pstmt.setFloat(8, SCOPUS학술지);
			pstmt.setFloat(9, 연구실적);
			pstmt.setString(10, 입력부서);
			pstmt.setInt(11, 전임교원수);
			pstmt.execute();

		}

		String column = "연구실적";
		String table = "연구실적외부";

		float 평균 = (float) (Math.round(defaultQuery.외부avg(column, table) * 100) / 100.0);
		float 표준편차 = (float) (Math.round(defaultQuery.외부std(column, table) * 100) / 100.0);

		ArrayList<String> 대학목록 = defaultQuery.외부대학목록(table);

		for (String 대학: 대학목록) {
			float 연구실적외부계산 = defaultQuery.외부비율(대학명, column, table);
			float T점수 = defaultClass.T점수(연구실적외부계산, 평균, 표준편차);
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
		System.out.println(ise);
	}
%>
<html>
<head>
</head>
<body>
</body>
</html>