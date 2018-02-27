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
<%@ page import="중도탈락률외부.중도탈락률외부EditAction"%>
<%@ page import="중도탈락률외부.중도탈락률외부Dao"%>
<%@ page import="defaultMethod.*"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "중도탈락률.jsp";
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

		중도탈락률외부EditAction edit = new 중도탈락률외부EditAction();
		중도탈락률외부Dao dao = new 중도탈락률외부Dao();
		String 입력부서 = (String) session.getAttribute("id");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line 번호(멈춘곳 다음이 에러가 발생한 라인입니다.) :"+i);
			
			String 대학명 = formatter.formatCellValue(row.getCell(0));
			String 학과명 = formatter.formatCellValue(row.getCell(1));
			int 재적학생수 = (int) row.getCell(2).getNumericCellValue();
			int 타학과전과자 = (int) row.getCell(3).getNumericCellValue();
			int 미등록 = (int) row.getCell(4).getNumericCellValue();
			int 미복학 = (int) row.getCell(5).getNumericCellValue();
			int 자퇴 = (int) row.getCell(6).getNumericCellValue();
			int 학사경고 = (int) row.getCell(7).getNumericCellValue();
			int 기타 = (int) row.getCell(8).getNumericCellValue();

			int 계 = edit.계(미등록, 미복학, 자퇴, 학사경고, 기타);
			float 중도탈락률 = edit.중도탈락률외부(재적학생수, 계);

			String sql = "insert into 중도탈락률외부(대학명,학과명,재적학생수,미등록,미복학,자퇴,학사경고,기타,계,중도탈락률,타학과전과자,입력부서) values(?,?,?,?,?,?,?,?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, 대학명);
			pstmt.setString(2, 학과명);
			pstmt.setInt(3, 재적학생수);
			pstmt.setInt(4, 미등록);
			pstmt.setInt(5, 미복학);
			pstmt.setInt(6, 자퇴);
			pstmt.setInt(7, 학사경고);
			pstmt.setInt(8, 기타);
			pstmt.setInt(9, 계);
			pstmt.setFloat(10, 중도탈락률);
			pstmt.setInt(11, 타학과전과자);
			pstmt.setString(12, 입력부서);

			pstmt.execute();

		}

		String column = "중도탈락률";
		String table = "중도탈락률외부";

		float 평균 = (float) (Math.round(defaultQuery.외부avg(column, table) * 100) / 100.0);
		float 표준편차 = (float) (Math.round(defaultQuery.외부std(column, table) * 100) / 100.0);
		
		System.out.println("average" + 평균 + "std" + 표준편차);

		ArrayList<String> 대학목록 = defaultQuery.외부대학목록(table);
		for (String 대학명 : 대학목록) {
			float 중도탈락률 = defaultQuery.외부비율(대학명, column, table);
			float T점수 = defaultClass.T점수(중도탈락률, 평균, 표준편차);
			System.out.println("jungdo" + 중도탈락률 + "t" + T점수);
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
	} finally {

	}
%>
<html>
<head>
</head>
<body>
</body>
</html>