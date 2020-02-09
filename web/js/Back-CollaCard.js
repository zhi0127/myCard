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

// 新增
function addMethod(node) {
	if ($(node).val() === "新申请") {
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
		$("#applyDate").val(currentDate);
		$("#applyPerson-name-text").val($("#loginName").val());
		$(node).attr("data-toggle", "modal").attr("data-target", "#myModal");
	} else if ($(node).val() === "提交申请") {
		if (window.confirm("您确定要申请吗?")) {
			var applyPerson = $("#applyPerson-name-text").val();
			var applyDate = $("#applyDate").val();
			var applyNum = $("#applyNum").val();

			if (applyPerson.length > 0 && applyDate.length > 0 && applyNum.length > 0 && parseInt(applyNum) > 0) {
				if ($("#cardHead option:checked").text() === '') {
					alert("无前缀可选，请联系管理员设置");
				} else {
					$("#newfrom").submit();
				}

			} else {
				alert("请输入大于0的数量");
			}
		}

	}


}


// 修改
var updateNode;

function ajaxMethod(node) {
	if ($(node).parent().prev().prev().prev().html() === "已审核") {
		window.alert("只能修改待审核的申请");
	} else {
		var flag = false;
		var collaCardID;
		var updateApplyNum;
		var applyNum;
		if ($(node).val() === "修改") {
			updateNode = $(node);
			applyNum = updateNode.parent().prev().prev().prev().prev().prev().html();
			updateApplyNum = $("#applyNum2").val();
			$("#applyPerson-name2-text").val($("#loginName").val());
			var applyDate = updateNode.parent().prev().prev().prev().prev().prev().prev().prev().html();
			$("#applyDate2").val(applyDate);
			$("#applyNum2").val(applyNum);
			$(node).attr("data-toggle", "modal").attr("data-target", "#myModal2");
		} else if ($(node).val() === "提交修改") {

			if (window.confirm("您确定要修改吗?")) {
				applyNum = updateNode.parent().prev().prev().prev().prev().prev().html();
				if (applyNum === $("#applyNum2").val()) {
					alert("您未做任何更改");

				} else if ($("#applyNum2").val().length === 0 || parseInt($("#applyNum2").val()) <= 0) {
					alert("请输入大于0的数量");
				} else {
					flag = true;
				}
			}
		}
		if (flag) {
			//利用脚本的方式 创建 一个对象
			collaCardID = updateNode.parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().html();
			updateApplyNum = $("#applyNum2").val();
			var ob = {msg1: collaCardID, msg2: updateApplyNum};
			//对象转换成字符串
			var str = JSON.stringify(ob);
			$.ajax({
				type: "POST",//提交的方式
				url: "UpdateApplyNumAjaxServlet",//提交的地址
				data: "msg=" + str,//提交的数据
				dataType: "text",//希望返回的数据类型
				async: true,//异步操作
				success: function (msg) {//成功的方法  msg为返回数据
					var ob2 = JSON.parse(msg);
					if ("1" === ob2.msg1) {
						updateNode.parent().prev().prev().prev().prev().prev().text(updateApplyNum);
						alert("修改成功");
					} else {
						alert("修改失败");
					}

				},
				error: function () {//错误的方法
					alert("服务器正忙")
				}
			});
		}


	}
	$(".modal").map(function () {
		if (!$(this).is(":hidden")) {
			$(this).modal("hide");
		}
	})
}



