$(function () {
	var H2NodeArr = $("h2");
	for (var i = 0; i < H2NodeArr.length; i++) {
		$(H2NodeArr[i]).mouseover(function () {
			$(this).next().attr("class", "open");
			$("h2").next().not($(this).next()).attr("class", "close");
		})
	}
	var ANodeArr = $(".subitem");
	var title;
	for (var i = 0; i < ANodeArr.length; i++) {
		$(ANodeArr[i]).click(function () {
			title = $(this).attr("title");
			$("#myiframe", parent.document.body).attr("src", title);
		})
	}
	$("#zx").click(function () {
		if (window.confirm("是否退出系统?")) {
			$(location).attr("href", "jsp/Back-login.jsp");
		}
	});
});

//修改密码
function updateMethod() {
	var loginID = $("#loginID").val();
	// alert(loginID);
	var oldPass = $("#oldPass").val();
	var newPass = $("#newPass").val();
	var reNewPass = $("#reNewPass").val();
	if (oldPass.length > 0 && newPass.length > 0 && reNewPass.length > 0) {
		if (newPass === reNewPass) {
			//利用脚本的方式 创建 一个对象
			// alert(loginID);
			var ob = {msg1: loginID, msg2: oldPass, msg3: newPass, msg4: null};
			//对象转换成字符串
			var str = JSON.stringify(ob);
			$.ajax({
				type: "POST",//提交的方式
				url: "UpdatePassAjaxServlet",//提交的地址
				data: "msg=" + str,//提交的数据
				dataType: "text",//希望返回的数据类型
				async: true,//异步操作
				success: function (msg) {//成功的方法  msg为返回数据
					var ob2 = JSON.parse(msg);
					if ("0" === ob2.msg1) {
						alert("密码错误")
					} else if ("1" === ob2.msg1) {
						alert("修改成功,新密码为" + newPass)
					}

				},
				error: function () {//错误的方法
					alert("服务器正忙")
				}
			});

		} else {
			alert("两次密码不一致");
		}

	} else {
		alert("请正确完整");
	}

}