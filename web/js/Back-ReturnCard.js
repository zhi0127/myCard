function readCardMethod() {
	var inputData = $("#oldCard").val();
	var pattern = /^[a-zA-Z].*$/;
	var flag = false;
	var type;
	var cardHead;
	var cardNum;
	if (pattern.test(inputData)) {
		// alert("卡号");
		var head = inputData.match(/^[a-z|A-Z]+/gi);
		cardHead = head.toString();
		var num = inputData.match(/\d+$/gi);
		cardNum = num.toString();
		type = 1;
		flag = true;

	} else {
		alert("查无此号或输入错误");
		flag = false;
	}


	if (flag) {
		//利用脚本的方式 创建 一个对象
		var ob = {msg1: type, msg2: cardHead, msg3: cardNum};
		//对象转换成字符串
		var str = JSON.stringify(ob);
		$.ajax({
			type: "POST",//提交的方式
			url: "BackReadCardAjaxServlet",//提交的地址
			data: "msg=" + str,//提交的数据
			dataType: "text",//希望返回的数据类型
			async: true,//异步操作
			success: function (msg) {//成功的方法  msg为返回数据
				var ob2 = JSON.parse(msg);
				if (ob2 != null) {
					$("#inputName").val(ob2['PATIENTNAME']);
					$("#sexy").val(ob2['PATIENTSEXY']);
					$("#age").val(ob2['PATIENTAGE']);
					$("#age1").val(ob2['PATIENTWEEK']);
					$("#birthPlace").val(ob2['PATIENTBIRTHPLACE']);
					$("#idNum").val(ob2['PATIENTIDENTITY']);
					$("#cellphone").val(ob2['PATIENTPHONE']);
					$("#inputAddress").val(ob2['PATIENTADRESS']);
					$("#money").val(ob2['PATIENTMONEY'] + "元");
					$("#oldCardID").val(ob2['CARDID']);
					$("#patientID").val(ob2['PATIENTID']);

					$("#returnMoney").val((parseInt(ob2['PATIENTMONEY']) + 5));


				} else {
					alert("查无此号")
				}


			},
			error: function () {//错误的方法
				alert("服务器正忙")
			}
		});

	}
}

function returnCardMethod() {
	if ($("#inputName").val() != null && $("#inputName").val().length > 0) {
		if (window.confirm("您确定要退卡吗?")) {
			$("#returnCardForm").submit();
		}

	} else {
		alert("请先读卡");
	}
}


$(function () {
	if ($("#msg").val() === "成功") {
		alert("退卡成功,退还金额:" + $("#returnMoney").val())
	}
});