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
<%@ page import="현장실습.현장실습EditAction"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="/js/importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "현장실습.jsp";
	opener.location.reload();
	window.opener.alert("업로드를 완료하였습니다");
	window.open("about:blank", "_self").close();
</script>
<%
	String saveDir = application.getRealPath("/Upload/");

	int maxSize = 1 * 1024 * 2024;

	String encType = "euc-kr";

	MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType,
			new DefaultFileRenamePolicy());

	File file = multipartRequest.getFile("file");
	File tmpFile = new File(file.getAbsolutePath());
	Workbook wb = WorkbookFactory.create(new File(file.getPath()));
	현장실습EditAction ea = new 현장실습EditAction();
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager
				.getConnection("jdbc:mysql://localhost:3306/schoolData?autoReconnect=true", "root", "123123");
		PreparedStatement pstm = null;
		Sheet sheet = wb.getSheetAt(0);
		Row row;

		int 년도 = 0;
		String 입력부서 = (String) session.getAttribute("id");

		DataFormatter formatter = new DataFormatter(Locale.US);

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line 번호(멈춘곳 다음이 에러가 발생한 라인입니다.) :"+i);
			
			년도 = (int) row.getCell(0).getNumericCellValue();
			String 학과명 = formatter.formatCellValue(row.getCell(1));
			int _1학기 = (int) row.getCell(2).getNumericCellValue();
			int _2학기 = (int) row.getCell(3).getNumericCellValue();
			int 장기1학기 = (int) row.getCell(4).getNumericCellValue();
			int 장기2학기 = (int) row.getCell(5).getNumericCellValue();
			int 대상학생수 = (int) row.getCell(6).getNumericCellValue();

			int 합계 = _1학기 + _2학기;
			int 장기합계 = 장기1학기 + 장기2학기;

			float result = (float) (((합계 + (장기합계 * 2)) / (대상학생수 * 1.0)) * 100);
			float 이수학생비율 = ea.이수학생비율(합계, 장기합계, 대상학생수);

			String sql = "INSERT INTO 현장실습(년도,학과명,1학기,2학기,장기1학기,장기2학기,대상학생수,입력부서,이수학생비율) VALUES(?,?,?,?,?,?,?,?,?)";

			pstm = (PreparedStatement) con.prepareStatement(sql);
			pstm.setInt(1, 년도);
			pstm.setString(2, 학과명);
			pstm.setInt(3, _1학기);
			pstm.setInt(4, _2학기);
			pstm.setInt(5, 장기1학기);
			pstm.setInt(6, 장기2학기);
			pstm.setInt(7, 대상학생수);
			pstm.setString(8, 입력부서);
			pstm.setFloat(9, 이수학생비율);
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
	}
%>
<html>
<head>
</head>
<body>
</body>
</html>