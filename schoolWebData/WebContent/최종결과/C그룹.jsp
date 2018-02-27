<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="../jquery/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="../jqgrid/css/ui.jqgrid.css">
<script type="text/javascript" src="//code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="../jqgrid/js/i18n/grid.locale-kr.js"></script>
<script type="text/javascript" src="../js/importExcel.js"></script>
<link href="../css/custom.css" rel="stylesheet" type="text/css" />
<title>C그룹</title>
</head>
<body>
	<!-- container -->
	<div class="container">
		<%@include file="../layout/header.jsp"%>
		<%@include file="../layout/leftContents.jsp"%>
		<!-- contests -->
		<div class="contents">
			<div class=top>
				<form id="insertData" onsubmit="return false" name="insertData">
					<table style="text-align: center;">
						<tr>
							<th colspan="20"><font size="6">[C 그룹]</font></th>
						<tr>
						<tr>
							<td colspan="20">
								<input type=button value="엑셀 다운로드" onclick="C그룹Down();" title="입력되어있는 데이터를 엑셀 형식으로 다운 받습니다">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<hr>
			<div id="pager"></div>
			<table id="C그룹_list"></table>
			<script type="text/javascript">
				$(function() {
					$("#C그룹_list").jqGrid(
							{
								url : "../C그룹ListAction",
								
								mtype : "post",
								datatype : "json",
								jsonReader:{repeatitems:false},
								caption : "C그룹 List",
								height : "auto",
								rowNum : -1,
								colNames : [ "년도","단과대학","학과명","학문계열","T점수","환산점수","T점수","환산점수",
									"T점수","환산점수","T점수","환산점수","T점수","환산점수","T점수","환산점수",
									"T점수","환산점수","T점수","환산점수","T점수","환산점수","T점수","환산점수","C그룹환산점수"],
								colModel : [ {
									name : "년도",
									align : "center",
								},{
									name : "단과대학",
									align : "center",
								},{
									name : "학과명",
									align : "center",
								},{
									name : "학문계열",
									align : "center",
								},{
									name : "외국인T",
									align : "center",
								},{
									name : "외국인환산",
									align : "center",
								},{
									name : "만족도T",
									align : "center",
								},{
									name : "만족도환산",
									align : "center",
								},{
									name : "현장실습T",
									align : "center",
								},{
									name : "현장실습환산",
									align : "center",
								},{
									name : "캡스톤T",
									align : "center",
								},{
									name : "캡스톤환산",
									align : "center",
								},{
									name : "교육프로그램T",
									align : "center",
								},{
									name : "교육프로그램환산",
									align : "center",
								},{
									name : "동아리T",
									align : "center",
								},{
									name : "동아리환산",
									align : "center",
								},{
									name : "특허등록T",
									align : "center",
								},{
									name : "특허등록환산",
									align : "center",
								},{
									name : "봉사실적T",
									align : "center",
								},{
									name : "봉사실적환산",
									align : "center",
								},{
									name : "수상실적T",
									align : "center",
								},{
									name : "수상실적환산",
									align : "center",
								},{
									name : "강의공개T",
									align : "center",
								},{
									name : "강의공개환산",
									align : "center",
								},{
									name: "환산점수",
									align : "center",
								}],
								pager : "#pager",
								autowidth : true,
								viewrecords : true,
								rownumbers:true,
								
							}).jqGrid('setGroupHeaders',{
							useColSpanStyle:true,
							groupHeaders:[{
								startColumnName : '외국인T',
								numberOfColumns:2,
								titleText: '외국인학생비율'
								},{
										startColumnName:"만족도T",
										numberOfColumns:2,
										titleText: '학생만족도평가'
								},{
									startColumnName:"현장실습T",
									numberOfColumns:2,
									titleText: '현장실습이수학생비율'
								},{
								startColumnName:"캡스톤T",
								numberOfColumns:2,
								titleText: '캡스톤디자인이수학생비율'
								},{
									startColumnName:"교육프로그램T",
									numberOfColumns:2,
									titleText: '교육프로그램참여비율'
								},{
								startColumnName:"동아리 T",
								numberOfColumns:2,
								titleText: '동아리참여학생비율'
								},{
									startColumnName:"특허등록T",
									numberOfColumns:2,
									titleText: '특허등록및기술이전수입료'
								},{
									startColumnName:"봉사실적T",
									numberOfColumns:2,
									titleText: '학생봉사실적'
								},{
									startColumnName:"수상실적T",
									numberOfColumns:2,
									titleText: '수상실적'
								},{
									startColumnName:"강의공개T",
									numberOfColumns:2,
									titleText: '강의공개실적'
								},]
							});
					
					
					$("#C그룹_list").jqGrid("navGrid", "#pager", {
						search : false,
						edit : false,
						add : false,
						del : false
						
					}, 
					{
						closeAfterEdit : true,
						reloadAfterSubmit : true
					}, {
						closeAfterAdd : true,
						reloadAfterSubmit : true
					}, {
						reloadAfterSubmit : true
					});
				
				
				});
			</script>
		</div>
		<!-- //contests -->
	</div>
	<!-- //container -->
</body>
</html>