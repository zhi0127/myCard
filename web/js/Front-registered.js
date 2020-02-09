function weeks(node) {
	var myForm = $("#form-weeks");
	var whatText = $(node).val();
	$("#doWhich").val(whatText);

	myForm.submit();
}

//读卡
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
	}
	if (flag) {
		//利用脚本的方式 创建 一个对象
		var ob = {msg1: type, msg2: cardHead, msg3: cardNum};
		//对象转换成字符串
		var str = JSON.stringify(ob);
		$.ajax({
			type: "POST",//提交的方式
			url: "/JX190616Card/FrontRegisteredAjaxServlet?methodName=readCard",//提交的地址
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
					$("#money").val(ob2['PATIENTMONEY']);

					$("#oldCardID").val(ob2['CARDID']);
					$("#patientID").val(ob2['PATIENTID']);

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

var scheduleDate;
var personID;

//显示提供预约表格
function showRegistered(node) {
	if ($("#inputName").val() != null && $("#inputName").val().length > 0) {
		scheduleDate = $(node).parent().children().val();
		personID = $(node).val();
		var personName = $(node).text();
		$("#personName").text(personName);
		//利用脚本的方式 创建 一个对象
		var ob = {msg1: scheduleDate, msg2: personID};
		//对象转换成字符串
		var str = JSON.stringify(ob);
		$.ajax({
			type: "POST",//提交的方式
			url: "/JX190616Card/FrontRegisteredAjaxServlet?methodName=showRegistered",//提交的地址
			data: "msg=" + str,//提交的数据
			dataType: "text",//希望返回的数据类型
			async: true,//异步操作
			success: function (msg) {//成功的方法  msg为返回数据

				var ob2 = JSON.parse(msg);

				for (var i = 0; i < 6; i++) {
					if (ob2[i]['PATIENTID'] != null) {
						$("#schedule").find('tr').eq(i + 1).find('td').eq(1).children().text(ob2[i]['PATIENTNAME']);
						$("#schedule").find('tr').eq(i + 1).find('td').eq(1).children().val(ob2[i]['PATIENTID']);
						$("#schedule").find('tr').eq(i + 1).find('td').eq(1).children().css('background-color', "red");
					} else {
						$("#schedule").find('tr').eq(i + 1).find('td').eq(1).children().text("可预约");
						$("#schedule").find('tr').eq(i + 1).find('td').eq(1).children().css('background-color', "green");
					}
				}
			},
			error: function () {//错误的方法
				alert("服务器正忙")
			}
		});

		$(node).attr("data-toggle", "modal").attr("data-target", "#myModal2");
	} else {
		alert("请先读卡")
	}

}

//预约
function registered(node) {
	var patientName = $(node).text();
	var patientID = $("#patientID").val();
	var scheduleTime = $(node).parent().prev().text();
	var money = $("#money").val();
	var flag = false;
	var type;
	if (patientID == $(node).valueOf().val()) {
		if (window.confirm("您确定要取消预约吗?")) {

			type = "取消";
			flag = true;
		}

	} else {
		if (patientName === "可预约") {
			if (parseInt(money) > 30) {

				if (window.confirm("您确定要预约吗?")) {
					type = "预约";
					flag = true;
				}
			} else {
				alert("你的余额不足30元，请先充值");
			}


		} else {
			alert("已被预约");
		}
	}
	if (flag) {

		//利用脚本的方式 创建 一个对象
		var ob = {msg1: scheduleDate, msg2: patientID, msg3: scheduleTime, msg4: personID, msg5: type, msg6: money};
		//对象转换成字符串
		var str = JSON.stringify(ob);
		$.ajax({
			type: "POST",//提交的方式
			url: "/JX190616Card/FrontRegisteredAjaxServlet?methodName=registered",//提交的地址
			data: "msg=" + str,//提交的数据
			dataType: "text",//希望返回的数据类型
			async: true,//异步操作
			success: function (msg) {//成功的方法  msg为返回数据
				var ob2 = JSON.parse(msg);
				if (ob2.msg1 == "取消成功") {

					$(node).text("可预约");
					$(node).val("");
					$(node).css('background-color', "green");
					$("#money").val((parseInt(money) + 30));
					alert("取消成功,退还30元");

				} else if (ob2.msg1 == "预约成功") {

					$(node).text($("#inputName").val());
					$(node).val($("#patientID").val());
					$(node).css('background-color', "red");
					$("#money").val((parseInt(money) - 30));
					alert("预约成功,扣除30元");

				}
			},
			error: function () {//错误的方法
				alert("服务器正忙")
			}
		});

	}


}

function fh() {
	if (window.confirm("是否返回?")) {
		$(location).attr("href", "/JX190616Card/jsp/Front-login.jsp");
	}
}