//分页
function myaction(node) {
	var value = $(node).val();
	if ($("#startTime").val().length > 0 && $("#endTime").val().length <= 0) {
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

$(function () {
	if ($("#isSuccess").val() === "提交申请成功") {
		alert("申请成功");
		$("#isSuccess").val("");
	}

});

// // 新增
// function addMethod(node) {
// 	if ($(node).val() === "新申请") {
// 		var date = new Date();
// 		var seperator1 = "-";
// 		var year = date.getFullYear();
// 		var month = date.getMonth() + 1;
// 		var strDate = date.getDate();
// 		if (month >= 1 && month <= 9) {
// 			month = "0" + month;
// 		}
// 		if (strDate >= 0 && strDate <= 9) {
// 			strDate = "0" + strDate;
// 		}
// 		var currentDate = year + seperator1 + month + seperator1 + strDate;
// 		$("#applyDate").val(currentDate);
// 		$("#applyPerson-name-text").val($("#loginName").val());
// 		$(node).attr("data-toggle", "modal").attr("data-target", "#myModal");
// 	} else if ($(node).val() === "提交申请") {
// 		if (window.confirm("您确定要申请吗?")) {
// 			var applyPerson = $("#applyPerson-name-text").val();
// 			var applyDate = $("#applyDate").val();
// 			var applyNum = $("#applyNum").val();
//
// 			if (applyPerson.length > 0 && applyDate.length > 0 && applyNum.length > 0) {
//
// 				$("#newfrom").submit();
//
// 			} else {
// 				alert("请输入数量");
// 			}
// 		}
// 	}
// }

// 审核
var updateNode;
var cardCount = null;

//获取开始卡号
function getStartNumMethod(node) {
	updateNode = $(node);
	var applyNum = $(node).parent().prev().prev().prev().prev().html();
	var applyPerson = $(node).parent().prev().prev().prev().prev().prev().prev().html();
	var applyDate = $(node).parent().prev().prev().prev().prev().prev().prev().prev().html();
	var collaCardid = $(node).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().html();
	var cardHead = $(node).parent().prev().prev().prev().prev().prev().html();
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
	$("#collacardid").val(collaCardid);
	$("#applyPerson-name-text").val(applyPerson);
	$("#auditDate-text").val(currentDate);
	$("#applyDate-text").val(applyDate);
	$("#card-head-text").val(cardHead);
	$("#applyNum").val(applyNum);


	//利用脚本的方式 创建 一个对象
	var ob = {msg1: cardHead, msg2: applyNum};
	//对象转换成字符串
	var str = JSON.stringify(ob);
	$.ajax({
		type: "POST",//提交的方式
		url: "GetCardNumAjaxServlet",//提交的地址
		data: "msg=" + str,//提交的数据
		dataType: "text",//希望返回的数据类型
		async: true,//异步操作
		success: function (msg) {//成功的方法  msg为返回数据
			var ob2 = JSON.parse(msg);
			if (ob2.msg1 === "卡不足") {
				cardCount = ob2.msg2;
				var intCardCount = parseInt(cardCount);
				var intApplyNum = parseInt(applyNum);
				alert("缺少" + (intApplyNum - intCardCount) + "张");
				$("#card-start-text").val("缺少" + (intApplyNum - intCardCount) + "张");
			} else if (ob2.msg1 === "卡充足") {
				cardCount = null;
				$("#card-start-text").val(ob2.msg2);

			}

		},
		error: function () {//错误的方法
			alert("服务器正忙")
		}
	});
	$(node).attr("data-toggle", "modal").attr("data-target", "#myModal");
}

//审核通过
function passMethod(node) {
	if (cardCount == null) {
		if (window.confirm("您确定要审核通过吗?")) {
			$("#newfrom").submit();
			alert("审核通过");
		}
	} else {
		alert("卡不足,请卡入库");
	}
}

//查看卡
function ajaxMethod(node) {
	var applyPerson = $(node).parent().prev().prev().prev().prev().prev().prev().html();
	var applyDate = $(node).parent().prev().prev().prev().prev().prev().prev().prev().html();
	var applyNum = $(node).parent().prev().prev().prev().prev().html();
	var auditPerson = $(node).parent().prev().prev().prev().html();
	var collaCardid = $(node).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().html();

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
	$("#applyPerson-name2-text").val(applyPerson);
	$("#applyDate2").val(applyDate);
	$("#applyNum2").val(applyNum);
	$("#auditPerson-name-text").val(auditPerson);
	$("#auditDate").val(currentDate);


	//利用脚本的方式 创建 一个对象
	var ob = {msg1: collaCardid};
	//对象转换成字符串
	var str = JSON.stringify(ob);
	$.ajax({
		type: "POST",//提交的方式
		url: "CreditCardAjaxServlet",//提交的地址
		data: "msg=" + str,//提交的数据
		dataType: "text",//希望返回的数据类型
		async: true,//异步操作
		success: function (msg) {//成功的方法  msg为返回数据
			var ob2 = JSON.parse(msg);
			if (ob2 != null) {
				$("#Credit-card-number").empty();

				for (var i = 0; i < ob2.length; i++) {
					$("#Credit-card-number").append(ob2[i]['CARDHEAD'] + ob2[i]['CARDNUM'] + "\n");
				}

			}

		},
		error: function () {//错误的方法
			alert("服务器正忙")
		}
	});
	$(node).attr("data-toggle", "modal").attr("data-target", "#myModal2");
}

