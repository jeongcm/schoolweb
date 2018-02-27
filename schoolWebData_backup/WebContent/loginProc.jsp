<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="login.loginDao"%>

<%
	request.setCharacterEncoding("utf-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../default.js"></script>
<title>Insert title here</title>
</head>
<body>

	<%
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String send_id = request.getParameter("id");

		loginDao dao = new loginDao();
		boolean result = dao.logincheck(id, pw);

		if (result) {
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			session.setMaxInactiveInterval(-1);
			response.sendRedirect("main.jsp");
		} else {
			session.setAttribute("id", "fail");
			response.sendRedirect("main.jsp");

		}
	%>


</body>
</html>