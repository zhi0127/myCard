//分页
function myaction(node) {
	var value = $(node).val();
	var startPage = Number($("#startPage").val());
	if (value === "搜索") {
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

var updateNode;

//启用禁用，删除，重置密码
function ajaxMethod(node) {
	if ($(node).parent().prev().html() === "已删除") {
		window.alert("该账号已删除");
	} else {
		var flag = false;
		var personID = $(node).parent().prev().prev().prev().prev().prev().html();
		if (personID === $("#loginID").val()) {
			alert("该账号为您的账号");
		} else {
			if ($(node).val() === "启用") {
				if (window.confirm("您确定要启用吗?")) {
					$(node).attr("value", "禁用");
					var stateID = 1;
					flag = true;
				}
			} else if ($(node).val() === "禁用") {
				if (window.confirm("您确定要禁用吗?")) {
					$(node).attr("value", "启用");
					stateID = 2;
					flag = true;
				}
			} else if ($(node).val() === "删除") {
				if (window.confirm("您确定要删除吗?")) {
					stateID = 3;
					flag = true;
				}
			} else if ($(node).val() === "重置密码") {
				if (window.confirm("您确定要重置密码吗?")) {
					stateID = null;
					flag = true;
				}
			}
		}
		if (flag === true) {
			//利用脚本的方式 创建 一个对象
			var ob = {msg1: personID, msg2: stateID};
			//对象转换成字符串
			var str = JSON.stringify(ob);
			$.ajax({
				type: "POST",//提交的方式
				url: "AjaxServlet",//提交的地址
				data: "msg=" + str,//提交的数据
				dataType: "text",//希望返回的数据类型
				async: true,//异步操作
				success: function (msg) {//成功的方法  msg为返回数据
					var ob2 = JSON.parse(msg);
					if ("1" === ob2.msg1) {
						$(node).parent().prev().html("启用");
						alert("启用成功")
					} else if ("2" === ob2.msg1) {
						$(node).parent().prev().html("禁用");
						alert("禁用成功")
					} else if ("3" === ob2.msg1) {
						$(node).parent().prev().html("已删除");
						alert("删除成功")
					} else if ("4" === ob2.msg1) {
						alert("重置密码成功,初始密码为123456")
					}

				},
				error: function () {//错误的方法
					alert("服务器正忙")
				}
			});
		}
	}
}


//新增
function submitMehod() {
	var name = $("#user-name1-text").val();
	var pass = $("#user-pass-text").val();
	var rePass = $("#user-rePass-text").val();
	var newDepartType = $("#newDepartType").val();
	var newRoleType = $("#newRoleType").val();
	if (name.length > 0 && pass.length > 0 && rePass.length > 0 && pass === rePass && newDepartType.length > 0 && newRoleType.length > 0) {
		$("#newfrom").submit();
	} else {
		alert("请正确输入");
	}
}


// 修改
function updateMethod(node) {
	if ($(node).parent().prev().html() === "已删除") {
		alert("该账号已删除");
	} else {
		if ($(node).val() !== "确认") {
			if ($(node).val() === "修改") {
				updateNode = $(node);
				var personID = $(node).parent().prev().prev().prev().prev().prev().html();
				if (personID === $("#loginID").val()) {
					alert("操作失败,该账号为您的账号");
				} else {
					$("#hiddenUserID").val(personID);
					var departType = $(node).parent().prev().prev().prev().html();
					$("#hiddenDepartType").val(departType);
					var roleType = $(node).parent().prev().prev().html();
					$("#hiddenRoleType").val(roleType);
					var userName = $(node).parent().prev().prev().prev().prev().html();
					$("#user-name1-text2").html(userName);
					$(node).attr("data-toggle", "modal").attr("data-target", "#myModal2");
					var numbers = $("#newDepartType2").find("option"); //获取select下拉框的所有值
					for (var j = 0; j < numbers.length; j++) {
						if ($(numbers[j]).html() === $("#hiddenDepartType").val()) {
							$(numbers[j]).attr("selected", "selected");
						}
					}
					numbers = $("#newRoleType2").find("option"); //获取select下拉框的所有值
					for (j = 0; j < numbers.length; j++) {
						if ($(numbers[j]).html() === $("#hiddenRoleType").val()) {
							$(numbers[j]).attr("selected", "selected");
						}
					}
				}
			} else if ($(node).val() === "提交修改") {
				if (window.confirm("您确定要修改吗?")) {
					var roleTypeID = updateNode.parent().prev().prev().prev().prev().prev().prev().prev().html();
					var departTypeID = updateNode.parent().prev().prev().prev().prev().prev().prev().prev().prev().html();
					var newRoleType = $("#newRoleType2").val();
					var newDepartType = $("#newDepartType2").val();
					if (roleTypeID === newRoleType && departTypeID === newDepartType) {
						alert("您未做任何更改");
					} else {
						$("#newfrom2").submit();
					}
				}

			}
		}

	}

}