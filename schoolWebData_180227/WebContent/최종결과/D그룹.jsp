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
<script type="text/javascript" src="../js/importExcel.js"></script>

<link href="../css/custom.css" rel="stylesheet" type="text/css"/>
<title>D그룹</title>
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
					<th colspan="20"><font size="6">[D 그룹]</font></th>
				</tr>
				<tr>
					<td colspan="20"><input type=button value="엑셀 다운로드" onclick="D그룹Down();" title="입력되어있는 데이터를 엑셀 형식으로 다운 받습니다"></td>
				</tr>
			</table>
			<hr>
			<div id="pager"></div>
			<table id="D"></table>
			<script type="text/javascript">
				$(function() {
					$("#D").jqGrid(
							{
								url : "../D그룹ListAction",
								mtype : "post",
		
								datatype : "json",
								caption : "D그룹",
								height : "auto",
								rowNum :-1,
								colNames : [ "년도", "단과대학", "학과명", "학문계열", "T점수",
										"환산점수", "T점수", "환산점수", "T점수", "환산점수", "T점수",
										"환산점수", "T점수", "환산점수", "T점수", "환산점수",
										 "D그룹환산점수" ],
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
									name : "신입생T",
									index : "신입생T",
									align : "center",
									sortable : false,
								}, {
									name : "신입생환산",
									index : "신입생환산",
									align : "center",
									sortable : false,
								}, {
									name : "중도탈락률T",
									index : "중도탈락률T",
									align : "center",
									sortable : false,
								}, {
									name : "중도탈락률환산",
									index : "중도탈락률환산",
									align : "center",
									sortable : false,
								}, {
									name : "취업률T",
									index : "취업률T",
									align : "center",
									sortable : false,
								}, {
									name : "취업률환산",
									index : "취업률환산",
									align : "center",
								}, {
									name : "연구실적T",
									index : "연구실적T",
									align : "center",
									sortable : false,
								}, {
									name : "연구실적환산",
									index : "연구실적환산",
									align : "center",
									sortable : false,
								}, {
									name : "연구비T",
									index : "연구비T",
									align : "center",
									sortable : false,
								}, {
									name : "연구비환산",
									index : "연구비환산",
									align : "center",
									sortable : false,
								}, {
									name : "강의T",
									index : "강의T",
									align : "center",
									sortable : false,
								}, {
									name : "강의환산",
									index : "강의환산",
									align : "center",
									sortable : false,
								},   {
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
							startColumnName : '신입생T',
							numberOfColumns : 2,
							titleText : '신입생충원율'
						}, {
							startColumnName : '중도탈락률T',
							numberOfColumns : 2,
							titleText : '중도탈락률'
						}, {
							startColumnName : '취업률T',
							numberOfColumns : 2,
							titleText : '취업률'
						}, {
							startColumnName : '연구실적T',
							numberOfColumns : 2,
							titleText : '연구실적'
						}, {
							startColumnName : '연구비T',
							numberOfColumns : 2,
							titleText : '교외연구비'
						}, {
							startColumnName : '강의T',
							numberOfColumns : 2,
							titleText : '강의담당비율'
						}, ],
						
		
					});
					$("#D").jqGrid("navGrid", "#pager", {
						search : false,
						edit : false,
						add : false,
						del : false,
						
					}).jqGrid("navButtonAdd", "#pager", {
						id : 'exportexcel',
						caption : '',
						title : '엑셀 다운',
						onClickButton : function(e) {
							D그룹Down();
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