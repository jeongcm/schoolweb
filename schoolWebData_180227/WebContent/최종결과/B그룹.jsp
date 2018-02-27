<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="../jquery/jquery-ui.css"> 
<link rel="stylesheet" type="text/css" href="../jqgrid/css/ui.jqgrid.css">
<script type="text/javascript"
	src="//code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="../jqgrid/js/i18n/grid.locale-kr.js"></script>
<script type="text/javascript" src="../importExcel.js"></script>

<link href="../css/custom.css" rel="stylesheet" type="text/css"/>
<title>B그룹</title>
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
					<th colspan="20"><font size="6">[B 그룹]</font></th>
				</tr>
				<tr>
					<td colspan="20"><input type=button value="엑셀 다운로드" onclick="B그룹Down();" title="입력되어있는 데이터를 엑셀 형식으로 다운 받습니다"></td>
				</tr>
			</table>
			<hr>
			<div id="pager"></div>
			<table id="B"></table>
			<script type="text/javascript">
				$(function() {
					$("#B").jqGrid(
							{
								url : "../B그룹ListAction",
								mtype : "post",
		
								datatype : "json",
								caption : "B그룹",
								height : "auto",
								rowNum : -1,
								colNames : [ "년도", "단과대학", "학과명", "학문계열", "T점수",
										"환산점수", "T점수", "환산점수", "T점수", "환산점수", "T점수",
										"환산점수",  "B그룹환산점수" ],
								colModel : [ {
									name : "년도",
									index : "년도",
									align : "center",
									sortable : false,
								}, {
									name : "단과대학",
									index : "단과대학",
									align : "center",
									sortable : false,
								}, {
									name : "학과명",
									index : "학과명",
									align : "center",
									sortable : false,
								}, {
									name : "학문계열",
									index : "학문계열",
									align : "center",
									sortable : false,
								}, {
									name : "중도탈락T",
									index : "중도탈락T",
									align : "center",
									sortable : false,
								}, {
									name : "중도탈락환산",
									index : "중도탈락환산",
									align : "center",
									sortable : false,
								}, {
									name : "장학금T",
									index : "장학금T",
									align : "center",
									sortable : false,
								}, {
									name : "장학금환산",
									index : "장학금환산",
									align : "center",
									sortable : false,
								}, {
									name : "발전기금T",
									index : "발전기금T",
									align : "center",
									sortable : false,
								}, {
									name : "발전기금환산",
									index : "발전기금환산",
									align : "center",
								}, {
									name : "교원확보T",
									index : "교원확보T",
									align : "center",
									sortable : false,
								}, {
									name : "교원확보환산",
									index : "교원확보환산",
									align : "center",
									sortable : false,
								}, {
									name : "환산점수",
									index : "환산점수",
									align : "center",
									sortable : false,
								}, ],
								pager : "#pager",
								autowidth : true,
								viewrecords : true,
								rownumbers : true,
							}).jqGrid('setGroupHeaders', {
						useColSpanStyle : true,
						groupHeaders : [ {
							startColumnName : '중도탈락T',
							numberOfColumns : 2,
							titleText : '중도탈락률'
						}, {
							startColumnName : '장학금T',
							numberOfColumns : 2,
							titleText : '교외장학금'
						}, {
							startColumnName : '발전기금T',
							numberOfColumns : 2,
							titleText : '발전기금 조성액'
						}, {
							startColumnName : '교원확보T',
							numberOfColumns : 2,
							titleText : '전임교원 확보율'
						},]
					});
					$("#B").jqGrid("navGrid", "#pager", {
						search : false,
						edit : false,
						add : false,
						del : false,
						
					}).jqGrid("navButtonAdd", "#pager", {
						id : 'exportexcel',
						caption : '',
						title : '엑셀 다운',
						onClickButton : function(e) {
							B그룹Down();
						},
						buttonicon : 'ui-icon-arrowthickstop-1-s'
					});
				});
			</script>
		</div>
		<!-- //contests -->
	</div>
	<!-- //container -->
</body>
</html>