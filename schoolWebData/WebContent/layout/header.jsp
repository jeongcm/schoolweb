<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style type="text/css">
	.button{
		font-size : 11px;
		width: 75px; height: 20px;
		vertical-align:middle;
	}
	
	.table {		
		margin-left: auto;
    margin-right: auto;
	}
	.header {
		width: 100%; width: 240px; height: 71px; background-color: #cdd4ec;
	}
</style>

<script>
	function changePw(frm) {
		var title = '비밀번호 변경';
		var popUrl = "../changePw.jsp"; // 팝업창에 출력될 페이지 URL
		var popOption = "width=330, height=320, resizable=no, scrollbars=no, status=no;"; // 팝업창
		window.open("",title,popOption);
		
		frm.target=title;
		frm.action=popUrl;
		frm.method="post";
		frm.submit();

	}
</script>

	<%
		String id = (String) session.getAttribute("id");
		String loginId = session.getAttribute("id").toString();
	%>

<!-- header -->
<div class="header">	
	<center>
		<h2><%=loginId%></h2>
		<form>
			<input type=button value='Main' class="button" onclick="window.location='../main.jsp'" />
			<input type=button value=logout class="button" onclick="window.location='../logout.jsp'" />
			<input type=button value='비밀번호 변경' class="button" onclick="changePw(this.form);" />
		</form>
		<br>
	</center>

</div>
<!-- //header -->