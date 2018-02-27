
function insertCheck(입력부서) {
	$.ajax({
		url : "../입력판단EditAction",
		type : "post",
		async: false,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : "&입력부서=" + 입력부서,
		datatype : "json",
		success : function(msg) {
			if (msg == 'fail') {
				flag=true;
				alert("이미 최종입력을 선택하셨습니다. 관리자에게 문의해주세요");
			}else{
				flag=false;
			}
		}
	});
	return flag;
}
