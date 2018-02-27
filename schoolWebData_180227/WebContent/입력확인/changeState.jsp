<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>권한변경</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript"
	src="//code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../jquery/jquery-ui.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
	function change() {
		var major = document.changeState.major.value;
		$.ajax({
			url : "../changeState",
			type : "post",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : "major=" + major,
			datatype : "json",
			success : function(msg) {
				if (msg == 'change') {
					alert("변경을 완료하였습니다.");
					window.open("about:blank", "_self").close();
				} else if(msg=='fail'){
					alert("부서/과 명을 다시한번 확인해주세요.")
				}
			},
		});
	}
</script>
<body>
	입력을 가능하게 권한을 부여합니다.
	<hr>
	● 변경을 원하는 부서/과를 입력해주세요
	<form name=changeState method=post>
		<input type=text id=major name=major onKeypress="if(event.keyCode ==13)change();"> 
		<input type=button value="확인" onclick="change();" />
	</form>
	<script>console.log("경로확인")</script>
</body>
</html>