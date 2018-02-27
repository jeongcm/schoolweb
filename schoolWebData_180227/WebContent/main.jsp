<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<script type="text/javascript" src="//code.jquery.com/jquery-3.1.1.min.js"></script>
		<script>
		function changeState() {
			var title = '입력 권한 변경';
			var popUrl = "입력확인/changeState.jsp";// 팝업창에 출력될 페이지 URL
			var popOption = "width=350, height=200, resizable=no, scrollbars=no, status=no;"; // 팝업창
			window.open(popUrl, title, popOption);
	
		}
	</script>
	<script>
		function changePw(frm) {
			var title = '비밀번호 변경';
			var popUrl = "changePw.jsp"; // 팝업창에 출력될 페이지 URL
			var popOption = "width=330, height=320, resizable=no, scrollbars=no, status=no;"; // 팝업창
			window.open("",title,popOption);
			
			frm.target=title;
			frm.action=popUrl;
			frm.method="post";
			frm.submit();
	
		}
	</script>
	<script>
		function saveAll() {
			if (confirm("모든 데이터 입력이 완료되었다면 확인버튼을 클릭해주세요!") == true){    //확인
				var 입력부서='<%=(String) session.getAttribute("id")%>';
				$
						.ajax({
							url : "./입력확인EditAction",
							type : "post",
							contentType : "application/x-www-form-urlencoded; charset=utf-8",
							data : "입력부서=" + 입력부서,
							datatype : "json",
							success : function(msg) {
								alert("수고하셨습니다 ^^");
							},
							error : function(request, error) {
							}
						});
			} else { //취소
				return;
			}
	
		}
	</script>


	<link href="./css/custom.css" rel="stylesheet" type="text/css"/>
	<title>학과평가</title>
</head>
<body>
	
	<!-- container -->
	<div class="container">
		<%@include file="./layout/headerLogin.jsp"%>
		
		<!-- contests -->
		<div class="contents">

		</div>
		<!-- //contests -->
	</div>
	<!-- //container -->
</body>
</html>
