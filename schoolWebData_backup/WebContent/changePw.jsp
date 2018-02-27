<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 변경</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript"
	src="//code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="./jquery/jquery-ui.js"></script>

<script>
	function sameCheck() {
		var id = document.changePw.id.value;
		var pw = document.changePw.pw.value;
		var rpw = document.changePw.rpw.value;
		if (pw == "" | rpw == "") {
			alert("모든 값을 정확히 입력해주세요");
		} else {
			if (pw != rpw) {
				alert("두 비밀번호 입력이 일치하지 않습니다!");
			} else {
				$
						.ajax({
							url : "./changePw",
							type : "post",
							contentType : "application/x-www-form-urlencoded; charset=utf-8",
							data : "id=" + id + "&pw=" + pw,
							datatype : "json",
							success : function(msg) {
								if (msg == 'change') {
									alert("변경을 완료하였습니다");
									window.open("about:blank", "_self").close();
								}
							},
						});
			}
		}
	}
</script>

<body>
	<%
		String id = (String)session.getAttribute("id");
	%>
	<b><%=id%>님의 비밀번호를 변경하겠습니다.</b>
	<hr>
	<form name=changePw>
		<input type=hidden value=<%=id%> name=id id=id /> 
		    ● 새로운 비밀번호를 입력해주세요.
		<p>
			<input type=password id=pw name=pw>
		<p>● 한번 더 입력해주세요.
		<p>
			<input type=password id=rpw name=rpw> <input type='button'
				value="확인" onclick="sameCheck();" />
	</form>
</body>
</html>
