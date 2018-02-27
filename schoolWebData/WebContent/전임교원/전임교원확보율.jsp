<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<title>전임교원확보율</title>
</head>
<script>
	function save() {
		var 입력부서='<%=(String) session.getAttribute("id")%>';
		if (!insertCheck(입력부서)) {	
			if (document.insertData.년도.value == "") {
				swal({
					type:'question',
					title: '년도를 입력해주세요!',
				}).then(
				  function () {document.insertData.년도.focus();})
		
		} else if (document.insertData.단과대학.value == "단과대학") {
			swal({
					type:'question',  
					title: '단과대학을 선택해주세요!',
				}).then(
				  function () {document.insertData.단과대학.focus();})
		
		}  else if (document.insertData.학과명.value == "학과명") {
			swal({
				  title: '학과명을 선택해주세요!',
				  type:'question',
				}).then(
				  function () {document.insertData.학과명.focus();})
		
		} 	 else {
				var Data = $('#insertData').serialize();
				var button = '&oper=add';
				submitData = Data + button;
				
				$
						.ajax({
							url : "../전임교원확보율EditAction",
							type : "post",
							contentType : "application/x-www-form-urlencoded; charset=utf-8",
							data : submitData+"&입력부서="+입력부서,
							datatype : "json",
							success : function(msg) {
								if (msg == 'fail') {
									swal({type:'error',
										  title: '입력에 실패했습니다. \n중복되는 데이터가 존재합니다!',
										  timer:1000,
									}).then(function() {}, 
									function (dismiss) {
										if(dismiss=='timer'){
											$("#전임교원확보율_list").trigger("reloadGrid");
											$("#전임교원확보율view_list").trigger("reloadGrid");

											document.insertData.학과명.value = "학과명";
											document.insertData.대학원생정원.value = "";
											document.insertData.대학원재학생.value = "";
											document.insertData.학생정원기준전임교원.value = "";
											document.insertData.재학생기준전임교원.value = "";

											document.insertData.학과명.focus();
										 }});}
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
			url : "../전임교원확보율EditAction",
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
				$("#전임교원확보율_list").trigger("reloadGrid");
				$("#전임교원확보율view_list").trigger("reloadGrid");

				document.insertData.학과명.value = "학과명";
				document.insertData.대학원생정원.value = "";
				document.insertData.대학원재학생.value = "";
				document.insertData.학생정원기준전임교원.value = "";
				document.insertData.재학생기준전임교원.value = "";

				document.insertData.학과명.focus();
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
				url : "../전임교원확보율EditAction",
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
					$("#전임교원확보율_list").trigger("reloadGrid");
					$("#전임교원확보율view_list").trigger("reloadGrid");
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
	function del(rowId) {
		var 입력부서='<%=(String) session.getAttribute("id")%>';
		var row부서=$("#전임교원확보율_list").jqGrid('getRowData', rowId).입력부서;
		if(입력부서==row부서){
			if (!insertCheck(입력부서)) { 
				var 연번 = $("#전임교원확보율_list").jqGrid('getRowData', rowId).연번;
				
				button = '&oper=del';
				
				$.ajax({
						url : "../전임교원확보율EditAction",
						type : "post",
						contentType : "application/x-www-form-urlencoded; charset=utf-8",
						data : "연번=" + 연번 +button,
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
							$("#전임교원확보율_list").trigger("reloadGrid");
							$("#전임교원확보율view_list").trigger("reloadGrid");
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
	function editRow(id) {
		var 입력부서='<%=(String) session.getAttribute("id")%>';
		var row부서= $("#전임교원확보율_list").jqGrid('getRowData', id).입력부서;
		if(입력부서==row부서){
			if (!insertCheck(입력부서)) {
				var lastid
				if (id && id !== lastid) {
					$('#전임교원확보율_list').restoreRow(lastid);
					lastid = id;
				}
				$("#전임교원확보율_list").editRow(id, true,null, reload);
			}
		} else { 
			swal(
		    	'수정할 수 없습니다!',
		    	'다른 사람이 입력한 데이터는 수정할 수 없습니다.',
		    'error'
		  )	
		}
	}
	function reload(response) {
		var result = response.responseText
		if (result == 'fail') {
			 swal(
				    	'수정할 수 없습니다!',
				    	'중복되는 데이터가 존재합니다.',
				    'error'
				  )
		} else if (result == 'failMajor') {
			swal(
			    	'수정할 수 없습니다!',
			    	'존재하지 않는 학과명입니다',
			    'error'
			  )
		} else if (result == "failYear") {
			swal(
			    	'수정할 수 없습니다!',
			    	'년도를 잘못 입력하셨습니다.',
			    'error'
			  )
		} else {
			var id = $("#전임교원확보율_list").jqGrid('getGridParam', "selrow");
			
			$('#전임교원확보율_list').restoreRow(id);
			
			$.ajax({
				type : 'post',
				url : "../전임교원확보율EditAction",
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
						$("#전임교원확보율_list").trigger("reloadGrid");
						$("#전임교원확보율view_list").trigger("reloadGrid");
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
							<th colspan="20"><font size="6">[전임교원 확보율]</font></th>
						</tr>
						<tr>
							<td colspan="20">
								<input type=button value="양식 다운로드" onclick="onJavaScript:window.location='전임교원확보율양식Down.jsp';" title="엑셀을 업로드 하기위해 필요한 양식을 다운 받습니다"> 
								<input type=button value="엑셀 업로드" onclick="전임교원확보율Up();" title="이미 작성된 엑셀을 업로드합니다"> 
								<input type=button value="엑셀 다운로드" onclick="onJavaScript:window.location='전임교원확보율Down.jsp';" title="입력되어있는 데이터를 엑셀 형식으로 다운 받습니다">
							</td>
						</tr>
						<tr>
							<td>년도</td>
							<td>단과대학</td>
							<td>학과명</td>
							<td>대학원생정원</td>
							<td>대학원재학생</td>
							<td>학생정원기준전임교원</td>
							<td>재학생기준전임교원</td>
						</tr>
						<tr>
							<td>
								<input type=text name="년도" id="년도" onKeyup="if(event.keyCode ==13)save();">
							</td>
							<td>
								<select name="단과대학" id="단과대학" onchange="sub();">
									<option selected>단과대학</option>
									<option value="공과대학">공과대학</option>
									<option value="건설교통대학">건설교통대학</option>
									<option value="융합기술대학">융합기술대학</option>
									<option value="인문사회대학">인문사회대학</option>
									
									<option value="보건생명대학">보건생명대학</option>
									
									<option value="철도대학">철도대학</option>
								</select>
							</td>
							<td>
								<select name="학과명" id="학과명">
									<option value="학과명">학과명</option>
								</select>
							</td>
							<td>
								<input type='text' id="대학원생정원" name="대학원생정원" onKeyup="if(event.keyCode ==13)save();">
							</td>
							<td>
								<input type='text' id="대학원재학생" name="대학원재학생" onKeyup="if(event.keyCode ==13)save();">
							</td>
							<td>
								<input type='text' name="학생정원기준전임교원" id="학생정원기준전임교원" onKeyup="if(event.keyCode ==13)save();">
							</td>
							<td>
								<input type="text" name="재학생기준전임교원" id="재학생기준전임교원" onKeyup="if(event.keyCode ==13)save();">
							</td>
							<td>
								<input type=button value="추가하기" onclick="save();">
							</td>
							<td>
								<input type=button value='전체 삭제하기' onclick="delAll(); " />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<hr>
			<div id="pager"></div>
			<table id="전임교원확보율_list"></table>
			<script type="text/javascript">
				$(function() {
					$("#전임교원확보율_list").jqGrid(
							{
								url : "../전임교원확보율ListAction",
								mtype : "post",
								datatype : "json",
								caption : "전임교원확보율",
								height : "auto",
								rowNum : 20,
								rowList : [ 20, 50, 100 ],
								colNames : [ "연번", "년도", "학과명","대학원생정원 ",
										"대학원재학생", "학생정원기준전임교원", "재학생기준전임교원","입력부서", "" ],
								colModel : [ {
									name : "연번",
									editable : true,
								}, {
									name : "년도",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "학과명",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "대학원생정원",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "대학원재학생",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "학생정원기준전임교원",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "재학생기준전임교원",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name: "입력부서",
									editable : true,
								}, {
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
								editurl : "../전임교원확보율EditAction",
							}).jqGrid('setGroupHeaders', {
						useColSpanStyle : true,
						groupHeaders : [  {
							startColumnName : '대학원생정원',
							numberOfColumns : 2,
							titleText : '대학원'
						}, {
							startColumnName : '학생정원기준전임교원',
							numberOfColumns : 2,
							titleText : '전임교원현황'
						}, ]
					});
			
					$("#전임교원확보율_list").jqGrid("hideCol", [ "연번","입력부서" ]);
					$("#전임교원확보율_list").jqGrid("navGrid", "#pager", {
						search : false,
						edit : false,
						add : false,
						del : false,
		
					});
				});
			</script>
			<br /> <br />
			<div id="pager2"></div>
			<table id="전임교원확보율view_list"></table>
			<script type="text/javascript">
				$(function() {
					$("#전임교원확보율view_list").jqGrid(
							{
								url : "../전임교원확보율viewListAction",
								mtype : "post",
		
								datatype : "json",
								caption : "전임교원확보율",
								height : "auto",
								rowNum : 20,
								rowList : [ 20, 50, 100 ],
								colNames : [ "년도", "단과대학", "학과명", "계열", "신설연도", "학생정원",
										"군휴학자", "인정학생정원", "재학생", "대학원생정원 ", "대학원재학생",
										"학생정원계", "재학생계", "학생정원기준전임교원", "재학생기준전임교원",
										"학부생정원기준", "학부재학생기준", "대학원생기준", "대학원재학생기준",
										"총학생정원기준", "총재학생기준", "학생정원기준학생수", "재학생기준학생수",
										"학생정원 기준 교원확보율", "재학생기준 교원확보율", "전임교원확보율",
										"T점수" ],
								colModel : [ {
									name : "년도",
									align : "center",
									sortable : false,
								}, {
									name : "단과대학",
									align : "center",
									sortable : false,
								}, {
									name : "학과명",
									align : "center",
									sortable : false,
								}, {
									name : "계열",
									align : "center",
									sortable : false,
								}, {
									name : "신설연도",
									align : "center",
									sortable : false,
								}, {
									name : "학생정원",
									align : "center",
									sortable : false,
								}, {
									name : "군휴학자",
									align : "center",
									sortable : false,
								}, {
									name : "인정학생정원",
									align : "center",
									sortable : false,
								}, {
									name : "재학생",
									align : "center",
									sortable : false,
								}, {
									name : "대학원생정원",
									align : "center",
									sortable : false,
								}, {
									name : "대학원재학생",
									align : "center",
									sortable : false,
								}, {
									name : "학생정원계",
									align : "center",
									sortable : false,
								}, {
									name : "재학생계",
									align : "center",
									sortable : false,
								}, {
									name : "학생정원기준전임교원",
									align : "center",
									sortable : false,
								}, {
									name : "재학생기준전임교원",
									align : "center",
									sortable : false,
								}, {
									name : "교원_학부_정원",
									align : "center",
									sortable : false,
								}, {
									name : "교원_학부_재학생",
									align : "center",
									sortable : false,
								}, {
									name : "교원_대학원_정원",
									align : "center",
									sortable : false,
								}, {
									name : "교원_대학원_재학생",
									align : "center",
									sortable : false,
								}, {
									name : "교원_계_정원",
									align : "center",
									sortable : false,
								}, {
									name : "교원_계_재학생",
									align : "center",
									sortable : false,
								}, {
									name : "학생수_정원",
									align : "center",
									sortable : false,
								}, {
									name : "학생수_재학생",
									align : "center",
									sortable : false,
								}, {
									name : "확보율_정원",
									align : "center",
									sortable : false,
								}, {
									name : "확보율_재학생",
									align : "center",
									sortable : false,
								}, {
									name : "전임교원확보율",
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
						useColSpanStyle : true,
						groupHeaders : [ {
							startColumnName : '학생정원',
							numberOfColumns : 3,
							titleText : '학생정원'
						}, {
							startColumnName : '학생정원계',
							numberOfColumns : 2,
							titleText : '계'
						}, {
							startColumnName : '교원_학부_정원',
							numberOfColumns : 2,
							titleText : '학부'
						}, {
							startColumnName : '교원_대학원_정원',
							numberOfColumns : 2,
							titleText : '대학원'
						}, {
							startColumnName : '교원_계_정원',
							numberOfColumns : 2,
							titleText : '계'
						}, {
							startColumnName : '학생수_정원',
							numberOfColumns : 2,
							titleText : '전임교원 1인당 학생수'
						}, {
							startColumnName : '확보율_정원',
							numberOfColumns : 2,
							titleText : '전임교원확보율'
						}, {
							startColumnName : '대학원생정원',
							numberOfColumns : 2,
							titleText : '대학원'
						}, {
							startColumnName : '학생정원기준전임교원',
							numberOfColumns : 2,
							titleText : '전임교원현황'
						}, ]
					}).jqGrid("navGrid", "#pager2", {
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