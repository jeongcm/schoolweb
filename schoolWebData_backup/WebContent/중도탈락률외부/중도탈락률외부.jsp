<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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
<title>중도탈락률외부</title>
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
			} else if (document.insertData.학과명.value == "") {
				swal({
					type:'question',
					title: '학과명를 입력해주세요!',
				}).then(
				  function () {document.insertData.학과명.focus();})
			} else if(document.insertData.재적학생수.value==""){
				swal({
					type:'question',
					title: '재적학생수를 입력해주세요!',
				}).then(
				  function () {document.insertData.재적학생수.focus();})
			return
		}else{
		var Data = $('#insertData').serialize();
		var button = '&oper=add';

		submitData = Data + button;
		$.ajax({
			url : "../중도탈락률외부EditAction",
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
							$("#중도탈락률_list").trigger("reloadGrid");

							document.insertData.학과명.value = "";
							document.insertData.대학명.value = "";
							document.insertData.재적학생수.value = "";
							document.insertData.미등록.value = "";
							document.insertData.미복학.value = "";
							document.insertData.자퇴.value = "";
							document.insertData.학사경고.value = "";
							document.insertData.기타.value = "";
							documnet.insertData.비고.value = "";
							document.insertData.타학과전과자.value = "";
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
			url : "../중도탈락률외부EditAction",
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
				$("#중도탈락률_list").trigger("reloadGrid");

				document.insertData.학과명.value = "";
				document.insertData.대학명.value = "";
				document.insertData.재적학생수.value = "";
				document.insertData.미등록.value = "";
				document.insertData.미복학.value = "";
				document.insertData.자퇴.value = "";
				document.insertData.학사경고.value = "";
				document.insertData.기타.value = "";
				document.insertData.비고.value = "";
				document.insertData.타학과전과자.value = "";
				
				document.insertData.대학명.focus();
							}});
			},
		datatype : "json"
	});
	}
</script>
<script>
	function del(rowId) {
		var 입력부서='<%=(String) session.getAttribute("id")%>';
		var row부서=$("#중도탈락률_list").jqGrid('getRowData', rowId).입력부서;
		if(입력부서==row부서){
			if (!insertCheck(입력부서)) { 
				var 연번 = $("#중도탈락률_list").jqGrid('getRowData', rowId).연번;
		var button = '&oper=del';

		$.ajax({
			url : "../중도탈락률외부EditAction",
			type : "post",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : "연번=" + 연번 + button,
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

				$("#중도탈락률_list").trigger("reloadGrid");

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
			url : "../중도탈락률외부EditAction",
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
				$("#중도탈락률_list").trigger("reloadGrid");
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
function editRow(id) {
	var 입력부서='<%=(String) session.getAttribute("id")%>';
	var row부서= $("#중도탈락률_list").jqGrid('getRowData', id).입력부서;
	if(입력부서==row부서){
		if (!insertCheck(입력부서)) {
			var lastid
			if (id && id !== lastid) {
				$('#중도탈락률_list').restoreRow(lastid);
				lastid = id;
			}
			$("#중도탈락률_list").editRow(id, true,null, reload);
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
	} else {
		var id = $("#중도탈락률_list").jqGrid('getGridParam',
		"selrow");
		 $('#중도탈락률_list').restoreRow(id);
		$.ajax({
			type : 'post',
			url : "../중도탈락률외부EditAction",
			data : {
				oper : 'editCal'
			},
			datatype : "json",
			success : function(msg) {
				swal({type:'success',
					  title: '요청완료!',
					  timer:500}).then(function () {},
							  function (dismiss) {
						  if(dismiss=='timer'){
					$("#중도탈락률_list").trigger("reloadGrid");
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
							<th colspan="20"><font size="6">[중도탈락률 외부]</font></th>
						</tr>
						<tr>
							<td colspan="20">
							<input type=button value="양식 다운로드" onclick="JavaScript:window.location='중도탈락률외부양식Down.jsp';" title="엑셀을 업로드 하기위해 필요한 양식을 다운 받습니다">
							<input type=button value="엑셀 다운로드" onclick="JavaScript:window.location='중도탈락률외부Down.jsp';" title="입력되어있는 데이터를 엑셀 형식으로 다운 받습니다">
							<input type=button value="엑셀 업로드" onclick="중도탈락률외부Up();" title="이미 작성된 엑셀을 업로드합니다"></td>
							
						</tr>
						<tr>
							<td>대학명</td>
							<td>학과명</td>
							<td>재적학생수</td>
							<td>타학과전과자</td>
							<td>미등록</td>
							<td>미복학</td>
							<td>자퇴</td>
							<td>학사경고</td>
							<td>기타</td>
							<td>비고</td>
						</tr>
						<tr>
							<td><input type='text' id="대학명" name="대학명"
								onKeyup="if(event.keyCode ==13)save();"></td>
							<td><input type='text' id="학과명" name="학과명"
								onKeyup="if(event.keyCode ==13)save();"></td>
							<td><input type='text' id="재적학생수" name="재적학생수"
								onKeyup="if(event.keyCode ==13)save();"></td>
								<td><input type='text' id="타학과전과자" name="타학과전과자"
								onKeyup="if(event.keyCode ==13)save();"></td>
							<td><input type='text' id="미등록" name="미등록"
								onKeyup="if(event.keyCode ==13)save();"></td>
							<td><input type='text' name="미복학" id="미복학"
								onKeyup="if(event.keyCode ==13)save();"></td>
							<td><input type='text' name="자퇴" id="자퇴"
								onKeyup="if(event.keyCode ==13)save();"></td>
							<td><input type='text' name="학사경고" id="학사경고"
								onKeyup="if(event.keyCode ==13)save();"></td>
							<td><input type="text" name="기타" id="기타"
								onKeyup="if(event.keyCode ==13)save();"></td>
							<td><input type="text" name="비고" id="비고"
		            onKeyup="if(event.keyCode ==13)save();"></td>
 							<td><input type=button value="추가하기" onclick="save();"></td>
							<td><input type=button value='전체 삭제하기' onclick="delAll(); " /></td>
						</tr>
					</table>
				</form>
			</div>
		
			<hr>
			<div id="pager"></div>
			<table id="중도탈락률_list"></table>
		
			<script type="text/javascript">
				$(function() {
					$("#중도탈락률_list").jqGrid(
							{
								url : "../중도탈락률외부ListAction",
								mtype : "post",
		
								datatype : "json",
								caption : "중도탈락률",
								height : "auto",
								rowNum : 20,
								rowList : [ 20, 50, 100 ],
								colNames : ["연번", "대학명", "학과명", "재적학생수","타학과전과자", "미등록", "미복학", "자퇴","학사경고","기타","계","중도탈락률","T점수","비고","입력부서","" ],
								colModel : [ {
									name : "연번",
									index : "연번",
									editable : true,
									edittype : "text",
								}, {
									name : "대학명",
									index : "대학명",
									align : "center",
									editable :true,
									edittype : "text",
									sortable : false,
								}, {
									name : "학과명",
									index : "학과명",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "재적학생수",
									index : "재적학생수",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								},{
									name : "타학과전과자",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "미등록",
									index : "미등록",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "미복학",
									index : "미복학",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "자퇴",
									index : "자퇴",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "학사경고",
									index : "학사경고",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								}, {
									name : "기타",
									index : "기타",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								},{
									name : "계",
									index : "계",
									align : "center",
									editable : true,
									edittype : "text",
									sortable : false,
								},  {
									name : "중도탈락률",
									index : "중도탈락률",
									align : "center",
									editable : false,
									edittype : "text",
									sortable : false,
								},{
									name : "T점수",
									index : "T점수",
									align : "center",
									editable : false,
									edittype : "text",
									sortable : false,
								},{
                  name : "비고",
                  index : "비고",
                  align : "center",
                  editable : true,
                  edittype : "text",
                  sortable : false,
                                 },{
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
								},
								],
								pager : "#pager",
								autowidth : true,
								viewrecords : true,
								onSelectRow : editRow,
								rownumbers : true,
								editurl : "../중도탈락률외부EditAction",
							});
				
					$("#중도탈락률_list").jqGrid("hideCol", [ "연번" ,"입력부서"]);
		
			
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
