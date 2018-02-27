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
<title>동아리</title>
</head>
<script> 
	function save(){
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
		
		}  else if (document.insertData.관리부서.value == "") {
			swal({
				  title: '관리부서를 입력해주세요!',
				  type:'question',
				}).then(
				  function () {document.insertData.관리부서.focus();})
			}  else if (document.insertData.동아리명.value == "") {
				swal({
					  title: '동아리명를 입력해주세요!',
					  type:'question',
					}).then(
					  function () {document.insertData.동아리명.focus();})
			} else if (document.insertData.구분.value == "구분") {
				swal({
					  title: '구분을 선택해주세요!',
					  type:'question',
					}).then(
					  function () {document.insertData.구분.focus();})
			
			}else if (document.insertData.지도교수.value == "") {
				swal({
					  title: '지도교수를 입력해주세요!',
					  type:'question',
					}).then(
					  function () {document.insertData.지도교수.focus();})
			} else if (document.insertData.학생대표학년.value == "선택") {
				swal({
					  title: '학생대표학년을 선택해주세요!',
					  type:'question',
					}).then(
					  function () {document.insertData.학생대표학년.focus();})
			
			} else if (document.insertData.학생대표이름.value == "") {
				swal({
					  title: '학생대표이름를 입력해주세요!',
					  type:'question',
					}).then(
					  function () {document.insertData.학생대표이름.focus();})
			} else if (document.insertData.회원수.value == "") {
				swal({
					  title: '회원수를 입력해주세요!',
					  type:'question',
					}).then(
					  function () {document.insertData.회원수.focus();})
			} else if (document.insertData.인정여부.value == "인정여부") {
				swal({
					  title: '인정여부을 선택해주세요!',
					  type:'question',
					}).then(
					  function () {document.insertData.인정여부.focus();})
			
			} else if (document.insertData.실적서류.value == "") {
				swal({
					  title: '실적서류를 입력해주세요!',
					  type:'question',
					}).then(
					  function () {document.insertData.실적서류.focus();})
			} else if (document.insertData.예산지원액.value == "") {
				swal({
					  title: '예산지원액 입력해주세요!',
					  type:'question',
					}).then(
					  function () {document.insertData.예산지원액.focus();})
			}
		else{
			var Data = $('#insertData').serialize();
			var button = '&oper=add';
			submitData = Data + button;
			$.ajax({
				url : "../동아리EditAction",
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
								$("#동아리_list").trigger("reloadGrid");
					            $("#동아리view_list").trigger("reloadGrid");
					      
					      document.insertData.관리부서.value = "";
								document.insertData.학과명.value = "학과명";
								document.insertData.동아리명.value = "";
								document.insertData.지도교수.value = "";
								document.insertData.학생대표학년.value = "학년";
								document.insertData.학생대표이름.value = "";
								document.insertData.회원수.value = "";
								document.insertData.인정여부.value = "인정여부";
								document.insertData.예산지원액.value = "예산지원액";
								document.insertData.실적서류.value = "";
								document.insertData.구분.value = "구분";
								document.insertData.비고.value = "";
								
								document.insertData.관리부서.focus();
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
		url : "../동아리EditAction",
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
			$("#동아리_list").trigger("reloadGrid");
            $("#동아리view_list").trigger("reloadGrid");
      
      document.insertData.관리부서.value = "";
			document.insertData.학과명.value = "학과명";
			document.insertData.동아리명.value = "";
			document.insertData.지도교수.value = "";
			document.insertData.학생대표학년.value = "학년";
			document.insertData.학생대표이름.value = "";
			document.insertData.회원수.value = "";
			document.insertData.인정여부.value = "인정여부";
			document.insertData.예산지원액.value = "예산지원액";
			document.insertData.실적서류.value = "";
			document.insertData.구분.value = "구분";
			document.insertData.비고.value = "";
			
			document.insertData.관리부서.focus();
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
			url : "../동아리EditAction",
			type : "post",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data :button+"&입력부서="+입력부서,
			datatype : "json",
			success : function(msg) {
				 swal(
					    	'전체 삭제완료!',
					    	'자신이 입력한 모든 데이터가 삭제되었습니다.',
					    'success'
					  ).then(
							  function () {
				$("#동아리_list").trigger("reloadGrid");
				$("#동아리view_list").trigger("reloadGrid");
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
		var row입력부서= $("#동아리_list").jqGrid('getRowData', rowId).입력부서;
		
		if(입력부서==row입력부서){
		if (!insertCheck(입력부서)) {
		
			var 연번 = $("#동아리_list").jqGrid('getRowData', rowId).연번;

		var button="&oper=del"
		$.ajax({
			url : "../동아리EditAction",
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
				$("#동아리_list").trigger("reloadGrid");
	            $("#동아리view_list").trigger("reloadGrid");
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
					var row입력부서 = $("#동아리_list").jqGrid('getRowData', id).입력부서;
		
					if (입력부서 == row입력부서) {
						if (!insertCheck(입력부서)) {
							var lastid
							if (id && id !== lastid) {
								$('#동아리_list').restoreRow(lastid);
								lastid = id;
							}
							$("#동아리_list").editRow(id, true, null, reload);
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
					}  else {
						var id = $("#동아리_list").jqGrid('getGridParam', "selrow");
		
						$('#동아리_list').restoreRow(id);
						
						$.ajax({
							type : 'post',
							url : "../동아리EditAction",
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
								$("#동아리_list").trigger("reloadGrid");
								$("#동아리view_list").trigger("reloadGrid");
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
							<th colspan="20"><font size='6'>[동아리 학생 참여비율]</font></th>
						</tr>
						<tr>
							<td colspan="20">
								<input type=button value="양식 다운로드" onclick="JavaScript:window.location='동아리양식Down.jsp';" title="엑셀을 업로드 하기위해 필요한 양식을 다운 받습니다"> <input type=button value="엑셀 업로드" onclick="동아리Up();" title="이미 작성된 엑셀을 업로드합니다"> <input type=button value="엑셀 다운로드" onclick="JavaScript:window.location='동아리Down.jsp';" title="입력되어있는 데이터를 엑셀 형식으로 다운 받습니다">
							</td>
						</tr>
						<tr>
							<td>년도</td>
							<td>단과대학</td>
							<td>학과명</td>
							<td>관리부서</td>
							<td>동아리명</td>
							<td>구분</td>
							<td>지도교수</td>
							<td>학생대표학년</td>
							<td>학생대표이름</td>
							<td>회원수</td>
							<td>예산지원액</td>
							<td>인정여부</td>
							<td>실적서류</td>
							<td>비고</td>
							<td>
								<input type=button value='전체 삭제하기' onclick="delAll(); " />
							</td>
						</tr>
						<tr>
							<td>
								<input type="text" name="년도" id="년도" size=3 onKeyup="if(event.keyCode ==13)save();">
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
								<input type="text" name="관리부서" id="관리부서" size=5 onKeyup="if(event.keyCode ==13)save();">
							</td>
							<td>
								<input type="text" name="동아리명" id="동아리명" size=5 onKeyup="if(event.keyCode ==13)save();">
							</td>
							<td>
								<select name="구분">
									<option>구분</option>
									<option value="학습">학습</option>
									<option value="취업">취업</option>
									<option value="창업">창업</option>
									<option value="봉사">봉사</option>
									<option value="취미">취미</option>
									<option value="문예">문예</option>
									<option value="기타">기타</option>
								</select>
							</td>
							<td>
								<input type="text" name="지도교수" id="지도교수" size=4 onKeyup="if(event.keyCode ==13)save();">
							</td>
							<td>
								<select name="학생대표학년" id="학생대표학년">
									<option>학년</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="대학원">대학원</option>
								</select>
							</td>
							<td>
								<input type="text" name="학생대표이름" id="학생대표이름" size=4 onKeyup="if(event.keyCode ==13)save();">
							</td>
							<td>
								<input type="text" name="회원수" id="회원수" size=3 onKeyup="if(event.keyCode ==13)save();">
							</td>
							<td>
								<input type="text" name="예산지원액" id="예산지원액" size=3 onKeyup="if(event.keyCode ==13)save();">
							</td>
							<td>
								<select name="인정여부" id="인정여부">
									<option>인정여부</option>
									<option value="인정">인정</option>
									<option value="제외">제외</option>
								</select>
							</td>
							<td>
								<input type="text" name="실적서류" id="실적서류" size=5 onKeyup="if(event.keyCode ==13)save();">
							</td>
							<td>
								<input type="text" name="비고" id="비고" size=8 onKeyup="if(event.keyCode ==13)save();">
							</td>
							<td>
								<input type=button value="추가하기" onclick="save();">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<hr>
			<div id="pager"></div>
			<table id="동아리_list"></table>
			<script type="text/javascript">
				$(function() {
					
					$("#동아리_list").jqGrid(
							{
								url : "../동아리ListAction",
								mtype : "post",
								datatype : "json",
								jsonReader:{repeatitems:false},
								caption : "동아리",
								height : "auto",
								
								rowNum : 20,
								rowList : [ 10, 15, 20 ],
								colNames : [ "년도","관리부서","학과명","동아리명","구분","지도교수","학년","이름","회원수","예산지원액","인정여부","실적서류","비고","연번","입력부서",""],
								colModel : [ {
									name : "년도",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								},{
									name : "관리부서",
									sortable : false,
									align : "center",
									editable : true,
									edittype : "text"
								},{
									name: "학과명",
									sortable : false,
									align : "center",
									editable : true,
									edittype : "text"
								},{
									name: "동아리명",
									sortable : false,
									align : "center",
									editable : true,
									edittype : "text"
									
								},{
									name: "구분",
									align : "center",
									sortable : false,
									editable : true,
									edittype : "select",
									editoptions : {
										value : "학습:학습;취업:취업;창업:창업;문예:문예;봉사:봉사;취미:취미;기타:기타"
									},
								},{
									name: "지도교수",
									sortable : false,
									align : "center",
									editable : true,
									edittype : "text"
								},{
									name: "학생대표학년",
									sortable : false,
									align : "center",
									editable : true,
									edittype : "select",
									editoptions : {
										value : "1:1;2:2;3:3;4:4;대학원:대학원"
									},
								},{
									name: "학생대표이름",
									sortable : false,
									align : "center",
									editable : true,
									edittype : "text"
								},{
									name: "회원수",
									sortable : false,
									align : "center",
									editable : true,
									edittype : "text"
								},{
									name: "예산지원액",
									sortable : false,
									align : "center",
									editable : true,
									edittype : "text",
									formatter:'integer',
									formatoptions: { 
									defaultValue: '', 
									thousandsSeparator: ',' }
								},{
									name: "인정여부",
									sortable : false,
									align : "center",
									editable : true,
									edittype : "select",
									editoptions : {
										value : "인정:인정;제외:제외"
									},
								},{
									name: "실적서류",
									index : "실적서류",
									align : "center",
									editable : true,
									edittype : "text"
								},{
									name: "비고",
									index : "비고",
									sortable : false,
									align : "center",
									editable : true,
									edittype : "text"
								},{
									name: "연번",
									editable : true,
								} ,{
									name: "입력부서",
									editable : true,
								}
								,{
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
								}],
								pager : "#pager",
								autowidth : true,
								viewrecords : true,
								onSelectRow : editRow,
								rownumbers:true,
								editurl : "../동아리EditAction"
								
							}).jqGrid('setGroupHeaders',{
								useColSpanStyle:true,
								groupHeaders:[{
									startColumnName : '학생대표학년',
									numberOfColumns:2,
									titleText: '대표 학생'
								}]
							});
					
					
					$("#동아리_list").jqGrid("navGrid", "#pager", {
						search : false,
						edit : false,
						add : false,
						del : false
					});
					
					$("#동아리_list").jqGrid("hideCol", [ "연번" ,"입력부서"]);
				
				});
				</script>
			<br /> <br />
			<div id="pager1"></div>
			<table id="동아리view_list"></table>
			<script type="text/javascript">
				$(function() {
					$("#동아리view_list").jqGrid(
							{
								url : "../동아리viewListAction",
								mtype : "post",
								datatype : "json",
								caption : "동아리 학생 참여 비율",
								height : "auto",
								rowNum : 20,
								rowList : [ 10, 15, 20 ],
								colNames : [ "년도", "단과대학", "학과명", "학습", "취업", "창업",
										"문예", "봉사", "취미", "기타", "계", "재학생수", "동아리참여비율",
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
									name : "학습",
									align : "center",
									sortable : false,
		
								}, {
									name : "취업",
									align : "center",
									sortable : false,
								}, {
									name : "창업",
									align : "center",
									sortable : false,
								}, {
									name : "문예",
									align : "center",
									sortable : false,
								}, {
									name : "봉사",
									align : "center",
									sortable : false,
								}, {
									name : "취미",
									align : "center",
									sortable : false,
								}, {
									name : "기타",
									align : "center",
									editable : true,
									edittype : "text"
								}, {
									name : "계",
									align : "center",
									sortable : false,
								}, {
									name : "재학생수",
									align : "center",
									sortable : false,
								}, {
									name : "동아리참여비율",
									align : "center",
									sortable : false,
								}, {
									name : "T점수",
									align : "center",
									sortable : false,
								} ],
								pager : "#pager1",
								autowidth : true,
								viewrecords : true,
								hiddengrid : true,
								rownumbers : true,
							}).jqGrid('setGroupHeaders',{
								useColSpanStyle:true,
								groupHeaders:[{
									startColumnName : '학습',
									numberOfColumns:8,
									titleText: '동아리 현황'
								}]
							});
		
		
					$("#동아리view_list").jqGrid("navGrid", "#pager1", {
						search : false,
						edit : false,
						add : false,
						del : false
		
					});
				});
			</script>
			<script>
			$("input[type='text'],select").focus(function() { 
			var 입력부서='<%=(String) session.getAttribute("id")%>
				';
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