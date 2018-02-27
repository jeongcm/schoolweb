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
</style>

	<%
		String id = (String) session.getAttribute("id");
		if (id == null) {
	%>
	<center>
		<br /> <br /> <br />
		<h1>학과평가</h1>
		<br /> <br /> <br />
		
		<form action='loginProc.jsp' method='post'>
			ID <input type='text' name='id' />
			PW <input type='password' name='pw' /> 
			<input type='submit' value=Login />
		</form>
	</center>	
		<%
			} else if (id.equals("fail")) {
		%>
		<script>
			alert("아이디와 비밀번호를 확인해주세요");
		</script>
		<%
			session.invalidate();
		%>
	<center>	
		<br /> <br /> <br />
		<h1>학과평가</h1>
		<br /> <br /> <br />
		<form action='loginProc.jsp' method='post'>
			ID <input type='text' name='id' /> 
			PW <input type='password' name='pw' /> 
			<input type='submit' value=Login />
		</form>
	</center>
	<%
		} else {
	%>
	<%
		String loginId = session.getAttribute("id").toString();
	%>
	
<!-- header -->
<div class="header">
	<center>
		<h2><%=loginId%></h2>
		<form>
			<input type=button value="최종입력" class="button" title="모든 입력이 완료되었을때만 눌러주세요!!" onclick=saveAll(); />
			<input type=button value=logout class="button" onclick="window.location='logout.jsp'" /> 
			<input type=button value='비밀번호 변경' class="button" onclick="changePw(this.form);" /> 
		</form>
		<br>
	</center>
	<%@include file="./left.jsp"%>
	
	<%
		}
	%>
</div>
<!-- //header -->