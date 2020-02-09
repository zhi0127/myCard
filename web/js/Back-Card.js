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

var updateNode;

//删除
function ajaxMethod(node) {
	if ($(node).parent().prev().html() !== "待领用") {
		alert("只能删除是待领用的卡");
	} else {
		var flag = false;
		if ($(node).val() === "删除") {
			if (window.confirm("您确定要删除吗?")) {
				var cardID = $(node).parent().prev().prev().prev().prev().prev().html();
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
				url: "CardAjaxServlet",//提交的地址
				data: "msg=" + str,//提交的数据
				dataType: "text",//希望返回的数据类型
				async: true,//异步操作
				success: function (msg) {//成功的方法  msg为返回数据
					var ob2 = JSON.parse(msg);
					if ("1" === ob2.msg1) {
						$(node).parent().prev().html("删除");
						alert("删除成功")
					}

				},
				error: function () {//错误的方法
					alert("服务器正忙")
				}
			});
		}

	}


}

//根据前缀获取开始卡号
function cardHeadMethod() {
	var cardHead = $("#card-head-text").val();
	var pattern = /^[A-Za-z]{1,5}$/;
	if (!pattern.test(cardHead)) {
		alert("请输入小于5位字母");
		$("#card-head-text").val("");
		$("#card-num-text").val("");
		$("#card-num-text").attr("placeholder", "请先输入前缀");
		$("#card-num-text").attr("readOnly", true);

	} else {
		if (cardHead.length > 0) {

			//利用脚本的方式 创建 一个对象
			var ob = {msg1: cardHead};
			//对象转换成字符串
			var str = JSON.stringify(ob);

			$.ajax({
				type: "POST",//提交的方式
				url: "CardHeadAjaxServlet2",//提交的地址
				data: "msg=" + str,//提交的数据
				dataType: "text",//希望返回的数据类型
				async: true,//异步操作
				success: function (msg) {//成功的方法  msg为返回数据
					var ob2 = JSON.parse(msg);
					if (ob2.msg1 == null) {
						var cardstart = (Array(8).join('0') + 1).slice(-8);
						$("#card-start-text").val(cardstart);

					} else {
						var intcardstart = parseInt(ob2.msg1) + 1;
						var cardstart = (Array(8).join('0') + intcardstart).slice(-8);
						$("#card-start-text").val(cardstart);
					}
					$("#card-num-text").removeAttr("readonly");
					$("#card-num-text").attr("placeholder", "请输入数量");
				},
				error: function () {//错误的方法
					alert("服务器正忙")
				}
			});
		}

	}

}

//获取截止卡号
function cardNumMethod() {
	var cardNum = $("#card-num-text").val();
	var cardHead = $("#card-head-text").val();
	if (cardHead != null && cardHead.length > 0) {
		var pattern = /^\d{1,5}$/;
		if (!pattern.test(cardNum) || parseInt(cardNum) <= 0) {
			alert("请输入小于5位数字");
			$("#card-end-text").val("");
			$("#card-num-text").val("");
		} else {
			var cardStart = $("#card-start-text").val();
			var intCardStart = parseInt(cardStart);
			var intCardNum = parseInt(cardNum);
			var intCardEnd = intCardStart + intCardNum - 1;
			var cardEnd = (Array(8).join('0') + intCardEnd).slice(-8);
			$("#card-end-text").val(cardEnd);
		}

	} else {
		$("#card-end-text").val("");
		$("#card-end-text").attr("readOnly", true);

	}

}

//入库
function addCardMethod(node) {

	var cardHead = $("#card-head-text").val();
	var cardNum = $("#card-num-text").val();

	if (cardHead.length > 0 && cardNum.length > 0) {
		var date = new Date();
		var seperator1 = "-";
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var currentDate = year + seperator1 + month + seperator1 + strDate;
		// alert(currentDate);
		$("#card-time-text").val(currentDate);
		$("#newfrom").submit();

	} else {
		alert("请完整输入");
	}
}