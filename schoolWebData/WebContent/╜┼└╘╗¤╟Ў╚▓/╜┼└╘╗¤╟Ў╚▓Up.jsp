<%@page contentType="text/html;charset=euc-kr"%>
<script type="text/javascript" src="importExcel.js" charset='euc-kr'></script>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />

<html>
<body>
	<form action="신입생현황Insert.jsp" name="upload" method="POST"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td><input type="file" name="file" size="20" /></td>
				<td><input type="submit" value=전송 id=send name=send onclick="checkForm()"/></td>
			</tr>
		</table>
	</form>
	
</body>

</html>