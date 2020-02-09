//分页
function myaction(node) {
	var value = $(node).val();
	if ($("#startNum").val().length > 0 && $("#endNum").val().length <= 0) {
		alert("请输入截止卡号");
	} else if ($("#startNum").val().length <= 0 && $("#endNum").val().length > 0) {
		alert("请输入开始卡号");
	} else if ($("#startTime").val().length > 0 && $("#endTime").val().length <= 0) {
		alert("请输入截止日期");
	} else if ($("#startTime").val().length <= 0 && $("#endTime").val().length > 0) {
		alert("请输入开始日期");
	} else {
		var startPage = Number($("#startPage").val());
		if (value === "查询") {
			$('#startPage').val(1);
			$("#serachfrom").submit();
		} else if (value === "上一页") {
			if (startPage > 1) {
				$('#startPage').val(startPage - 1);
				$("#serachfrom").submit();
			}

		} else {
			if (startPage < $("#countPage").val()) {
				$('#startPage').val(startPage + 1);
				$("#serachfrom").submit();
			}
		}
	}


}
