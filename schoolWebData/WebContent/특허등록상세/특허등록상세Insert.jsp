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
<%@ page import="특허등록.특허등록EditAction"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "특허등록상세.jsp";
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
		int 년도 = 0;
		특허등록EditAction ea=new 특허등록EditAction();
		String 입력부서 = (String) session.getAttribute("id");

		DataFormatter formatter = new DataFormatter(Locale.US);

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line 번호(멈춘곳 다음이 에러가 발생한 라인입니다.) :"+i);
			
			년도 = (int) row.getCell(0).getNumericCellValue();
			String 학과명 = formatter.formatCellValue(row.getCell(1));
			String 대표발명자 = formatter.formatCellValue(row.getCell(2));
			String 지식재산권 = formatter.formatCellValue(row.getCell(3));
			float 정액기술료 = (float) row.getCell(4).getNumericCellValue();

			String sql = "insert into 특허등록(년도,학과명,대표발명자,지식재산권,정액기술료,입력부서) values(?,?,?,?,?,?);";

			pstm = (PreparedStatement) con.prepareStatement(sql);
			pstm.setInt(1, 년도);
			pstm.setString(2, 학과명);
			pstm.setString(3, 대표발명자);
			pstm.setString(4, 지식재산권);
			pstm.setFloat(5, 정액기술료);
			pstm.setString(6, 입력부서);
			pstm.execute();

		}

		String column = "수입료";
		String table = "특허등록및기술이전수입료";

		float 평균 = (float) (Math.round(defaultQuery.avg(년도, column, table) * 100) / 100.0);
		float 표준편차 = (float) (Math.round(defaultQuery.std(년도, column, table) * 100) / 100.0);

		ArrayList<String> 학과목록 = defaultQuery.학과목록(년도, table);

		for (String 학과명 : 학과목록) {
			float 수입료 = defaultQuery.비율(년도, 학과명, column, table);
			float T점수 = defaultClass.T점수(수입료, 평균, 표준편차);
			defaultQuery.updateT(T점수, 년도, 학과명, table);
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
	} finally {

	}
%>
<html>
<head>
</head>
<body>
</body>
</html>