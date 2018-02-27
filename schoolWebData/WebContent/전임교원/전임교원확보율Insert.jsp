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
<%@ page import="전임교원확보율.전임교원확보율EditAction"%>
<%@ page import="전임교원확보율.전임교원확보율Dao"%>
<%@ page import="defaultMethod.*"%>
<%@ page language="java" pageEncoding="EUC-KR"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	window.opener.location.href = "전임교원확보율.jsp";
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

		전임교원확보율EditAction edit = new 전임교원확보율EditAction();
		전임교원확보율Dao dao = new 전임교원확보율Dao();

		int 년도 = 0;
		String 입력부서 = (String) session.getAttribute("id");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			System.out.println("import line 번호(멈춘곳 다음이 에러가 발생한 라인입니다.) :" + i);

			년도 = (int) row.getCell(0).getNumericCellValue();
			String 학과명 = formatter.formatCellValue(row.getCell(1));
			int 대학원생정원 = (int) row.getCell(2).getNumericCellValue();
			int 대학원재학생 = (int) row.getCell(3).getNumericCellValue();
			int 학생정원기준전임교원 = (int) row.getCell(4).getNumericCellValue();
			int 재학생기준전임교원 = (int) row.getCell(5).getNumericCellValue();

			int 학생정원 = dao.학생정원(년도, 학과명);
			int 군휴학자 = dao.군휴학자(년도, 학과명);
			int 재학생 = dao.재학생(년도, 학과명);
			String _5대계열 = dao._5대계열(년도, 학과명);

			int 인정학생정원 = 학생정원 - 군휴학자;

			int 학생정원계 = 인정학생정원 + 대학원생정원;
			int 재학생계 = 재학생 + 대학원재학생;

			System.out.println("학과명"+학과명+"인정"+인정학생정원+"군휴학자"+군휴학자);
			int 교원_학부_정원 = edit.교원법정정원(_5대계열, 인정학생정원);
			int 교원_학부_재학생 = edit.교원법정정원(_5대계열, 재학생);
			int 교원_대학원_정원 = edit.교원법정정원(_5대계열, 대학원생정원);
			int 교원_대학원_재학생 = edit.교원법정정원(_5대계열, 대학원재학생);
			int 교원_계_정원 = edit.교원법정정원(_5대계열, 학생정원계);
			int 교원_계_재학생 = edit.교원법정정원(_5대계열, 재학생계);

			float 학생수_정원 = edit.학생수_정원(학생정원계, 학생정원기준전임교원);
			float 학생수_재학생 = edit.학생수_재학생(재학생계, 재학생기준전임교원);
			float 확보율_정원 = edit.확보율_정원(학생정원기준전임교원, 교원_계_정원);
			float 확보율_재학생 = edit.확보율_재학생(재학생기준전임교원, 교원_계_재학생);

			float 전임교원확보율 = Math.min(확보율_정원, 확보율_재학생);

			System.out.println("학과명 :"+ 학과명 + "인정학생정원 : " + 인정학생정원 + "교원_학부_정원: " + 교원_학부_정원 + " 교원_학부_재학생 : " + 교원_학부_재학생);

			String sql = "insert into 전임교원확보율(년도,학과명,대학원생정원,대학원재학생,학생정원기준전임교원,재학생기준전임교원,전임교원확보율,인정학생정원,교원_학부_정원,교원_학부_재학생,교원_대학원_정원,교원_대학원_재학생,교원_계_정원,교원_계_재학생,학생수_정원,학생수_재학생,확보율_정원,확보율_재학생,입력부서) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setInt(1, 년도);
			pstmt.setString(2, 학과명);
			pstmt.setInt(3, 대학원생정원);
			pstmt.setInt(4, 대학원재학생);
			pstmt.setInt(5, 학생정원기준전임교원);
			pstmt.setInt(6, 재학생기준전임교원);
			pstmt.setFloat(7, 전임교원확보율);
			pstmt.setInt(8, 인정학생정원);
			pstmt.setInt(9, 교원_학부_정원);
			pstmt.setInt(10, 교원_학부_재학생);
			pstmt.setInt(11, 교원_대학원_정원);
			pstmt.setInt(12, 교원_대학원_재학생);
			pstmt.setInt(13, 교원_계_정원);
			pstmt.setInt(14, 교원_계_재학생);
			pstmt.setFloat(15, 학생수_정원);
			pstmt.setFloat(16, 학생수_재학생);
			pstmt.setFloat(17, 확보율_정원);
			pstmt.setFloat(18, 확보율_재학생);
			pstmt.setString(19, 입력부서);

			pstmt.execute();

		}

		edit.비율재계산();
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
		tmpFile.delete();

	}
%>
<html>
<head>
</head>
<body>
</body>
</html>