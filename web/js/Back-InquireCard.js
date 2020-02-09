//分页
function myaction(node) {
	var value = $(node).val();


	if ($("#startNum").val() != null && $("#startNum").val().length > 0) {
		var head = $("#startNum").val().match(/^[a-z|A-Z]+/gi);
		var num = $("#startNum").val().match(/\d+$/gi);
		if (head == null || num == null) {
			alert("请正确输入卡号")
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

function ajaxMethod(node) {
	var cardID = $(node).parent().prev().prev().prev().prev().html();
	//利用脚本的方式 创建 一个对象
	var ob = {msg1: cardID};
	//对象转换成字符串
	var str = JSON.stringify(ob);
	$.ajax({
		type: "POST",//提交的方式
		url: "InquireCardAjaxServlet",//提交的地址
		data: "msg=" + str,//提交的数据
		dataType: "text",//希望返回的数据类型
		async: true,//异步操作
		success: function (msg) {//成功的方法  msg为返回数据
			var ob2 = JSON.parse(msg);
			if (ob2 != null) {
				var cardInf = ob2['list'][0];
				$("#card").val(cardInf['CARDHEAD'] + cardInf['CARDNUM']);
				$("#money").val(cardInf['PATIENTMONEY']);
				$("#cardState").val(cardInf['STATENAME']);
				$("#patientName").val(cardInf['PATIENTNAME']);
				$("#applyPersonName").val(cardInf['APPLYPERSONNAME']);
				$("#applyDate").val(cardInf['APPLYDATE']);
				$("#cardSeller").val(cardInf['CARDSELLER']);
				$("#cardSalesTime").val(cardInf['CARDSALESTIME']);
			}

		},
		error: function () {//错误的方法
			alert("服务器正忙")
		}
	});
	$(node).attr("data-toggle", "modal").attr("data-target", "#myModal");
}




