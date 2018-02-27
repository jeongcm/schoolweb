<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="../jquery/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="../jqgrid/css/ui.jqgrid.css">
<script type="text/javascript"
	src="//code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="../jqgrid/js/i18n/grid.locale-kr.js"></script>
<script type="text/javascript" src="../importExcel.js"></script>

<link href="../css/custom.css" rel="stylesheet" type="text/css"/>
<title>입력확인</title>
</head>
<body>
	<!-- container -->
	<div class="container">
		<%@include file="../layout/header.jsp"%>
		
		<%@include file="../layout/leftContents.jsp"%>
	
		<!-- contests -->
		<div class="contents">
			<table style="text-align: center;">
				<tr>
					<th colspan="20"><font size='6'>[입력확인-입력부서]</font></th>
				</tr>
			</table>
			<hr>
			<div id="pager"></div>
			<table id="입력확인_list"></table>
		
			<script type="text/javascript">
				$(function() {
					$("#입력확인_list").jqGrid(
							{
								url : "../입력확인ListAction",
								mtype : "post",
		
								datatype : "json",
								caption : "입력확인-입력부서",
								height : "auto",
								rowNum : 20,
								rowList : [ 20, 50, 100 ],
								colNames : [ "입력부서","상태"],
								colModel : [ {
									name : "부서",
									index : "부서",
									align : "center",
									sortable : false,
								}, {
									name : "상태",
									index : "상태",
									align : "center",
									sortable : false,
								}],
		
								pager : "#pager",
								autowidth : true,
								viewrecords : true,
								rownumbers : true,
							});
		
					$("#입력확인_list").jqGrid("navGrid", "#pager", {
						search : false,
						edit : false,
						add : false,
						del : false,
					});
				});
			</script>
			<br/>
				<table style="text-align: center;">
				<tr>
					<th colspan="20"><font size='6'>[입력확인-학과]</font></th>
				</tr>
			</table>
			<hr>
			<div id="pager2"></div>
			<table id="입력확인2_list"></table>
		
			<script type="text/javascript">
				$(function() {
					$("#입력확인2_list").jqGrid(
							{
								url : "../입력확인2ListAction",
								mtype : "post",
		
								datatype : "json",
								caption : "입력확인-학과",
								height : "auto",
								rowNum : 20,
								rowList : [ 20, 50, 100 ],
								colNames : ["학과","상태"],
								colModel : [ {
									name : "학과",
									index : "학과",
									align : "center",
									sortable : false,
								}, {
									name : "상태",
									index : "상태",
									align : "center",
									sortable : false,
								}],
		
								pager : "#pager2",
								autowidth : true,
								viewrecords : true,
								rownumbers : true,
							});
		
					$("#입력확인2_list").jqGrid("navGrid", "#pager2", {
						search : false,
						edit : false,
						add : false,
						del : false,
					});
				});
			</script>
		</div>
		<!-- //contests -->
	</div>
	<!-- //container -->
</body>
</html>
