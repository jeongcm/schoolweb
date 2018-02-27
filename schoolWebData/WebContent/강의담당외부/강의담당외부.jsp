<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="../jquery/jquery-ui.css">
<link rel="stylesheet" href="../jqgrid/css/ui.jqgrid.css">
<link rel="stylesheet" href="../design.css">
<link rel="stylesheet" href="../css/custom.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.6/sweetalert2.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.6/sweetalert2.min.js"></script>
<script src="//code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="../jquery/jquery-ui.js"></script>
<script src="../jqgrid/js/jquery.jqGrid.min.js"></script>
<script src="../jqgrid/js/i18n/grid.locale-kr.js"></script>
<script src="../js/importExcel.js"></script>
<script src="../js/insertCheck.js"></script>
<script src="../js/sub.js"></script>
<title>강의담당외부비율</title>
</head>
<script>
	function save() {
		var 입력부서='<%=(String) session.getAttribute("id")%>';
		if (!insertCheck(입력부서)) {
			if (document.insertData.대학명.value == "") {
				swal({
					type:'question',
					title: '대학명를 입력해주세요!',
				}).then(
				  function () {document.insertData.대학명.focus();})
				document.insertData.대학명.focus();
			} else if (document.insertData.학과명.value == "") {
				swal({
					type:'question',
					title: '학과명를 입력해주세요!',
				}).then(
				  function () {document.insertData.학과명.focus();})
			}else{
				var Data = $('#insertData').serialize();
				var button = '&oper=add';
				
				submitData = Data + button;
				
				$.ajax({
					url : "../강의담당외부EditAction",
					type : "post",
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					data : submitData+"&입력부서="+입력부서,
					datatype : "json",
					success : function(msg) {
							if(msg=="fail"){
								swal({type:'error',
									  title: '입력에 실패했습니다. \n중복되는 데이터가 존재합니다!',
									  timer:1000,
								}).then(function() {}, 
								function (dismiss) {
									if(dismiss=='timer'){
										document.insertData.대학명.value = "";
										document.insertData.전공과목.value= "";
										document.insertData.교양필수과목.value= "";
										document.insertData.자유선택과목.value= "";
										
										$("#강의담당외부_list").trigger("reloadGrid");
										$("#강의담당외부view_list").trigger("reloadGrid");
										
										document.insertData.대학명.focus();
									 }});
							}
							else{
								cal();
							 }
						}
					});
				}
			}
				}
</script>
<script>
	function cal() {
		$.ajax({
			url : "../강의담당외부EditAction",
			type : "post",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				oper : 'cal'
			},
			beforeSend:function(){
				swal('잠시만기다려주세요..');
				swal.showLoading() ;  
			},
			success : function(msg) {
				swal({type:'success',
					  title: '요청완료!',
					  timer:500}).then(function() {}, 
								function (dismiss) {
							if(dismiss=='timer'){
								document.insertData.대학명.value = "";
								document.insertData.전공과목.value= "";
								document.insertData.교양필수과목.value= "";
								document.insertData.자유선택과목.value= "";
								
								$("#강의담당외부_list").trigger("reloadGrid");
								$("#강의담당외부view_list").trigger("reloadGrid");
								
								document.insertData.대학명.focus();
							}});
			},
		datatype : "json"
	});
}
</script>
<script>
function delAll() {
	var 입력부서='<%=(String) session.getAttribute("id")%>';
	if (!insertCheck(입력부서)) {
		swal({
			  title: '정말로 모든 데이터를 \n삭제하시겠습니까?',
			  text: "전체 삭제를 원하지 않으시면 취소해주세요!",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '확인',
			  cancelButtonText: '취소'
			}).then(function () {
	var button = '&oper=delAll';

	$.ajax({
		url : "../강의담당외부EditAction",
		type : "post",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : button+"&입력부서="+입력부서,
		datatype : "json",
		success : function(msg) {
			swal(
			    	'전체 삭제완료!',
			    	'자신이 입력한 모든 데이터가 삭제되었습니다.',
			    'success'
			  ).then(
					  function () {
			$("#강의담당외부_list").trigger("reloadGrid");
			$("#강의담당외부view_list").trigger("reloadGrid");
					  });
		}
	});
}, function (dismiss) {
  if (dismiss === 'cancel') {
    swal(
      		'취소됨',
      		'전체삭제를 취소하셨습니다.',
      'error'
		    )
  		}
	})
	}
}
</script>
<script>
function del(rowId){
	var 입력부서='<%=(String) session.getAttribute("id")%>';
	var row입력부서= $("#강의담당외부_list").jqGrid('getRowData', rowId).입력부서;
	if(입력부서==row입력부서){
		if (!insertCheck(입력부서)) {
	 		var 연번 = $("#강의담당외부_list").jqGrid('getRowData', rowId).연번;
  			 var button="&oper=del"
	
			$.ajax({
				url : "../강의담당외부EditAction",
				type : "post",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				data : "연번="+연번+button,
				datatype : "json",
				beforeSend:function(){
					swal('잠시만기다려주세요..');
					swal.showLoading() ;  
				},
				success : function(msg) {
					swal({type:'success',
						  title: '요청완료!',
						  timer:500}).then(function() {}, 
									function (dismiss) {
								if(dismiss=='timer'){
					$("#강의담당외부_list").trigger("reloadGrid");
					$("#강의담당외부view_list").trigger("reloadGrid");
								}});
				}
	});
	}
		}else{
			 swal(
				    	{title:'삭제할 수 없습니다!',
				    	text:'다른 사람이 입력한 데이터는 삭제할 수 없습니다 \n셀이 선택되어있다면 esc를 누른 뒤 시도해주세요',
				    	type:'error'}
				  )
		}
	}

function delButton(cellValue, options) {
	return '<input type="button" onclick="del(' + options.rowId
			+ ')" value="삭제"/>';

}
	
</script>
<script>
function editRow(id){
				var 입력부서='<%=(String) session.getAttribute("id")%>';
				var row입력부서 = $("#강의담당외부_list").jqGrid('getRowData', id).입력부서;
					
				if(입력부서==row입력부서){
					if (!insertCheck(입력부서)) {
							var lastid
							if (id && id !== lastid) {
								$('#강의담당외부_list').restoreRow(lastid);
									lastid = id;
								}
								$("#강의담당외부_list").editRow(id, true,null, reload);
						}
					}
				else{
					 swal(
						    	'수정할 수 없습니다!',
						    	'다른 사람이 입력한 데이터는 수정할 수 없습니다.',
						    'error'
						  )
					}
		}

</script>
<script>
function reload(response) {
				var result = response.responseText
				if (result == 'fail') {
					 swal(
						    	'수정할 수 없습니다!',
						    	'중복되는 데이터가 존재합니다.',
						    'error'
						  )
				} else {
					var id = $("#강의담당외부_list").jqGrid('getGridParam', "selrow");
					$('#강의담당외부_list').restoreRow(id);

					$.ajax({
						type : 'post',
						url : "../강의담당외부EditAction",
						beforeSend:function(){
							swal('잠시만기다려주세요..');
							swal.showLoading() ;  
						},
						success:function(msg){
							swal({type:'success',
								  title: '요청완료!',
								  timer:500}).then(function () {},
										  function (dismiss) {
									  if(dismiss=='timer'){
								$("#강의담당외부_list").trigger("reloadGrid");
								$("#강의담당외부view_list").trigger("reloadGrid");
									  } });
						},
						data : {
							oper : 'editCal',
						},
						datatype : "json"
					});
				}
}
</script>
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
							<th colspan="20"><font size='6'>[전임교원 강의담당외부 비율]</font></th>
						</tr>
						<tr>
							<td colspan="20"><input type=button value="양식 다운로드" onclick="JavaScript:window.location='강의담당외부양식Down.jsp';" title="엑셀을 업로드 하기위해 필요한 양식을 다운 받습니다">
							<input type=button value="엑셀 업로드" onclick="강의담당외부tUp();" title="이미 작성된 엑셀을 업로드합니다">
							<input type=button value="엑셀 다운로드" onclick="JavaScript:window.location='강의비율외부Down.jsp';" title="입력되어있는 데이터를 엑셀 형식으로 다운 받습니다"></td>
						</tr>
						<tr>
							<td>대학명</td>
							<td>학과명</td>
							<td>전공과목</td>
							<td>교양필수과목</td>
							<td>자유선택과목</td>
						</tr>
						<tr>
							<td><input type=text name="대학명" id="대학명"
								onKeyup ="if(event.keyCode ==13)save();"></td>
							<td><input type=text name="대학명" id="대학명"
								onKeyup="if(event.keyCode ==13)save();"></td>
							<td><input type='text' id="전공과목" name="전공과목"
								onKeyup="if(event.keyCode ==13)save();"></td>
							<td><input type='text' id="교양필수과목" name="교양필수과목"
								onKeyup="if(event.keyCode ==13)save();"></td>
							<td><input type='text' name="자유선택과목" id="자유선택과목"
								onKeyup="if(event.keyCode ==13)save();"></td>
									<td><input type=button value="추가하기" onclick="save();"></td>
							<td><input type=button value='전체 삭제하기' onclick="delAll(); " /></td>
						
						</tr>
					</table>
				</form>
			</div>
		
			<hr>
			<div id="pager"></div>
			<table id="강의담당외부_list"></table>
		
			<script type="text/javascript">
				$(function() {
					$("#강의담당외부_list").jqGrid(
							{
								url : "../강의담당외부ListAction",
								mtype : "post",
		
								datatype : "json",
								caption : "강의담당외부",
								height : "auto",
								rowNum : 20,
								rowList : [ 20, 50, 100 ],
								colNames : [ "연번", "대학명", "학과명", "전공과목", "교양필수과목",
										"자유선택과목","비고","입력부서","" ],
								colModel : [ {
									name : "연번",
									editable : true,
		
								}, {
									name : "대학명",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								},{
									name : "학과명",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "전공과목",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "교양필수과목",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "자유선택과목",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "비고",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "입력부서",
									editable : true,
		
								},{
									name : 'del',
									width : 50,
									align : "center",
									fixed : true,
									sortable : false,
									resize : false,
									formatter : delButton,
									formatoptions : {
										keys : true
									}
								}, ],
		
								pager : "#pager",
								autowidth : true,
								viewrecords : true,
								onSelectRow : editRow,
								rownumbers : true,
								editurl : "../강의담당외부EditAction",
							});
		
					$("#강의담당외부_list").jqGrid("hideCol", [ "연번","입력부서" ]);
					$("#강의담당외부_list").jqGrid("navGrid", "#pager", {
						search : false,
						edit : false,
						add : false,
						del : false,
					});
				});
			</script>
		
			<br />
			<br />
			<div id="pager2"></div>
			<table id="강의담당외부view_list"></table>
			<script type="text/javascript">
				$(function() {
					$("#강의담당외부view_list").jqGrid(
							{
								url : "../강의담당외부viewListAction",
								mtype : "post",
		
								datatype : "json",
								caption : "강의담당외부",
								height : "auto",
								rowNum : 20,
								rowList : [ 20, 50, 100 ],
								colNames : [ "대학명",  "학과명", "개설전공과목", "개설교양필수과목",
										"개설자유선택과목", "전임교원전공과목", "전임교원교양필수과목",
										"전임교원자유선택과목", "강의담당외부비율", "T점수" ],
								colModel : [ {
									name : "대학명",
									align : "center",
									sortable : false,
								}, {
									name : "학과명",
									align : "center",
									sortable : false,
								}, {
									name : "개설전공과목",
									align : "center",
									sortable : false,
								}, {
									name : "개설교양필수과목",
									align : "center",
									sortable : false,
								}, {
									name : "개설자유선택과목",
									align : "center",
									sortable : false,
								}, {
									name : "교원전공과목",
									align : "center",
									sortable : false,
								}, {
									name : "교원교양필수과목",
									align : "center",
									sortable : false,
								}, {
									name : "교원자유선택과목",
									align : "center",
									sortable : false,
								}, {
									name : "강의담당외부비율",
									align : "center",
									sortable : false,
								}, {
									name : "T점수",
									align : "center",
									sortable : false,
								}, ],
								pager : "#pager2",
								autowidth : true,
								viewrecords : true,
								rownumbers : true,
								hiddengrid : true,
		
							}).jqGrid('setGroupHeaders', {
						useColSpanStyle : false,
						groupHeaders : [ {
							startColumnName : '개설전공과목',
							numberOfColumns : 3,
							titleText : '총 개설강의 학점'
						}, {
							startColumnName : '교원전공과목',
							numberOfColumns : 3,
							titleText : '전임교원 강의담당외부 학점'
						} ]
					});
					$("#강의담당외부view_list").jqGrid("navGrid", "#pager2", {
						search : false,
						edit : false,
						add : false,
						del : false,
		
					});
				});
		</script>
		<script>
			$("input[type='text'],select").focus(function() { 
				var 입력부서='<%=(String) session.getAttribute("id")%>';
					if (insertCheck(입력부서)) {
						$(this).blur();
					}
				});
		</script>
		</div>
		<!-- //contests -->
	</div>
	<!-- //container -->
</body>
</html>