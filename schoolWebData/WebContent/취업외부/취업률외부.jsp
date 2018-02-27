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
<link rel="stylesheet" type="text/css" href="../design.css">
<script type="text/javascript"
	src="//code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="../jqgrid/js/i18n/grid.locale-kr.js"></script>
<script type="text/javascript" src="../js/importExcel.js"></script>
<script src="../js/insertCheck.js"></script>
<script src="../js/sub.js"></script>
<link href="../css/custom.css" rel="stylesheet" type="text/css"/>
<title>취업률외부</title>
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
					<th colspan="20"><font size="6">[외부 취업률]</font></th>
				</tr>
				<tr>
					<td colspan="20">
					<input type=button value="엑셀 다운로드" onclick="취업률외부Down();" title="입력되어있는 데이터를 엑셀 형식으로 다운 받습니다"></td>
				</tr>
			</table>
			<hr>
			<div id="pager"></div>
			<table id="취업률"></table>
			<script type="text/javascript">
				$(function() {
					$("#취업률").jqGrid(
							{
								url : "../취업률외부ListAction",
								mtype : "post",
								datatype : "json",
								caption : "취업률외부",
								height : "auto",
								rowNum : 20,
								rowList : [ 20, 50, 100 ],
								colNames : [ "대학명", "학과명","졸업자",
									"소계", "건강보험DB연계취업자", "해외취업자","영농업취업자", "개인창작활동조사서", "1인창(사)업자", "프리랜서",
									"소계", "진학자", "입대자","취업불가능자", "외국인유학생", "건강보험직장가입제외대상","입학당시기취업자",
									"2차유지취업률",  "취업률",
									"T점수" ],
								colModel : [ {
									name : "대학명",
									editable : false,
									align : "center",
									sortable : false,
								}, {
									name : "학과명",
									editable : false,
									align : "center",
									sortable : false,
								}, {
									name : "졸업자",
									align : "center",
									editable : false,
									sortable : false,
								}, {
									name : "취업자",
									align : "center",
									sortable : false,
								}, {
									name : "건강보험db연계취업자",
									align : "center",
									sortable : false,
								}, {
									name : "해외취업자",
									align : "center",
									sortable : false,
								}, {
									name : "영농업취업자",
									align : "center",
									sortable : false,
								},{
									name : "개인창작활동조사서",
									align : "center",
									sortable : false,
								},{
									name : "일인창업자",
									align : "center",
									sortable : false,
								},{
									name : "프리랜서",
									align : "center",
									sortable : false,
								},  {
									name : "취업제외자",
									align : "center",
									sortable : false,
								}, {
									name : "진학자",
									align : "center",
									sortable : false,
								}, {
									name : "입대자",
									align : "center",
									sortable : false,
								}, {
									name : "취업불가능자",
									align : "center",
									sortable : false,
								}, {
									name : "외국인유학생",
									align : "center",
									sortable : false,
								}, {
									name : "건강보험직장가입제외대상",
									align : "center",
									sortable : false,
								}, {
									name : "입학당시기취업자",
									align : "center",
									sortable : false,
								},{
									name : "이차유지취업률",
									align : "center",
									sortable : false,
								}, {
									name : "취업률",
									align : "center",
									sortable : false,
								}, {
									name : "T점수",
									align : "center",
									sortable : false,
								},  ],
								pager : "#pager",
								autowidth:true,
								viewrecords : true,
								rownumbers : true,
							}).jqGrid('setGroupHeaders',{ 
						useColSpanStyle : true,
						groupHeaders : [ {
							startColumnName : '_6월취업자',
							numberOfColumns : 4,
							titleText : '6월 취업자'
						}, {
							startColumnName : '_6월취업제외자',
							numberOfColumns : 7,
							titleText : '6월 취업 제외자'
						}, {
							startColumnName : '국세db취업자',
							numberOfColumns : 4,
							titleText : '국세db취업자'
						},  {
							startColumnName : '_12월취업자',
							numberOfColumns : 4,
							titleText : '12월 취업자'
						},  {
							startColumnName : '_12월취업제외자',
							numberOfColumns : 7,
							titleText : '12월 취업 제외자'
						},  ]
					});
					$("#취업률").jqGrid("navGrid", "#pager", {
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