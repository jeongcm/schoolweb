function sub() {
	if (document.insertData.년도.value == "") {
		alert("년도를  입력해주세요");
		document.insertData.단과대학.value = "단과대학";
		document.insertData.년도.focus();
	} else {
		var 단과대학 = document.insertData.단과대학.value;
		var 년도 = Number(document.insertData.년도.value);

		$.ajax({
			type : 'post',
			url : "../defaultEditAction",
			data : "년도=" + 년도 + "&단과대학=" + 단과대학,
			datatype : "json",
			success : function(msg) {
				document.insertData.학과명.options.length = null

				var op = new Option();

				op.text = "학과명";
				insertData.학과명.options.add(op);

				jsonMsg = $.parseJSON(msg);

				for (i = 0; i < jsonMsg.length; i++) {
					var op = new Option();
					op.value = jsonMsg[i];
					op.text = jsonMsg[i];
					insertData.학과명.options.add(op);
				}
			},
			error : function(request, error) {
				alert("error");
			}
		});
	}
}
