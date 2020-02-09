//分页
function myaction(node) {
	var value = $(node).val();
	if ($("#startNum").val().length > 0 && $("#endNum").val().length <= 0) {
		alert("请输入截止卡号");
	} else if ($("#startNum").val().length <= 0 && $("#endNum").val().length > 0) {
		alert("请输入开始卡号");
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

//注销
function ajaxMethod(node) {
	if ($(node).parent().prev().html() === "已注销") {
		window.alert("该卡已注销");
	} else {
		var flag = false;
		if ($(node).val() === "注销") {
			if (window.confirm("您确定要注销吗?")) {
				var cardID = $(node).parent().prev().prev().prev().prev().html();
				// alert(cardID);
				flag = true;
			}
		}
		if (flag === true) {
			//利用脚本的方式 创建 一个对象
			var ob = {msg1: cardID};
			//对象转换成字符串
			var str = JSON.stringify(ob);
			$.ajax({
				type: "POST",//提交的方式
				url: "CancelCardAjaxServlet",//提交的地址
				data: "msg=" + str,//提交的数据
				dataType: "text",//希望返回的数据类型
				async: true,//异步操作
				success: function (msg) {//成功的方法  msg为返回数据
					var ob2 = JSON.parse(msg);
					if ("1" === ob2.msg1) {
						$(node).parent().prev().html("已注销");
						alert("注销成功")
					}

				},
				error: function () {//错误的方法
					alert("服务器正忙")
				}
			});
		}

	}


}

