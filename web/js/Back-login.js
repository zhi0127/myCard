function submitMehod() {


	if ($("#loginID-text").val().length > 0 && $("#loginPass-text").val().length > 0) {
		if (validate()) {
			return true;
		}
	} else {
		alert("请输入账号密码进行登录");
	}
	return false;
}


$(function () {
	if ($("#isFail").val() === "fail") {
		alert("登录失败,账号或密码错误");
		$("#isFail").val("");

	}
});

$(function () {
	createCode();
	//注销后防止页面后退
	history.pushState(null, null, document.URL);
	window.addEventListener('popstate', function () {
		history.pushState(null, null, document.URL);
	});
});

function isLoginDisable() {
	var loginID = $("#loginID-text").val();
	if (loginID.length > 0) {
		//利用脚本的方式 创建 一个对象
		var ob = {msg1: loginID};
		//对象转换成字符串
		var str = JSON.stringify(ob);
		$.ajax({
			type: "POST",//提交的方式
			url: "/JX190616Card/BackLoginAjaxServlet",//提交的地址
			data: "msg=" + str,//提交的数据
			dataType: "text",//希望返回的数据类型
			async: true,//异步操作
			success: function (msg) {//成功的方法  msg为返回数据
				var ob2 = JSON.parse(msg);
				if (ob2.msg1 === "被禁用") {
					alert("该账号被禁用");
					$("#loginID-text").val("");
				}
			},
			error: function () {//错误的方法
				alert("服务器正忙")
			}
		});
	}
}


var code; //在全局 定义验证码
function createCode() {
	code = "";
	var codeLength = 4;//验证码的长度
	var checkCode = document.getElementById("checkCode");
	var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');//所有候选组成验证码的字符，当然也可以用中文的

	for (var i = 0; i < codeLength; i++) {
		var charIndex = Math.floor(Math.random() * 36);
		code += selectChar[charIndex];
	}
	if (checkCode) {
		checkCode.className = "code";
		checkCode.value = code;
	}
}

function validate() {
	var inputCode = document.getElementById("verification-text").value;
	if (inputCode.length <= 0) {
		alert("请输入验证码！");
	} else if (inputCode.toLowerCase() !== code.toLowerCase()) {
		alert("验证码输入错误！");
		createCode();//刷新验证码
	} else {
		return true;
	}

}