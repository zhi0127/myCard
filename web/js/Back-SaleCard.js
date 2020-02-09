function checkAge() {

	var pattern = /^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
	if (!pattern.test($("#age").val())) {
		alert("请输入1到120数字");
		$("#age").val("");
	}

}

function checkWeek() {
	var pattern = /^[0-9]$|^1[0-2]$/;
	if (!pattern.test($("#age1").val())) {
		alert("请输入0到12数字");
		$("#age1").val("");


	}
}


function checkPhone() {
	var pattern = /^(((13|14|15|18|17)\d{9}))$/;
	if (!pattern.test($("#cellphone").val())) {
		alert("正确输入手机号");
		$("#cellphone").val("");


	}

}


$(function () {
	if ($("#msg").val() === "成功") {
		var money = $("#patientMoney").val();
		alert("出售成功,扣除押金5元，余额" + (parseInt(money) - 5) + "元")
	}
});

function SaleCardMethod() {
	var name = $("#inputName").val().length;
	var age = $("#age").val().length;
	var age1 = $("#age1").val().length;
	var s_province = $("#s_province").val();
	var s_city = $("#s_city").val();
	var idNum = $("#idNum").val().length;
	var cellphone = $("#cellphone").val().length;
	var inputAddress = $("#inputAddress").val().length;
	var money = $("#money").val();
	var number = $("#number").val();

	if (parseInt(money) > 5) {
		if (name > 0 && age > 0 && age1 > 0 && idNum > 0 && cellphone > 0 && inputAddress > 0 && money.length > 0) {
			if (s_province !== "省份" && s_city !== "地级市") {
				if (number !== "您申领的卡已经售光") {
					if (window.confirm("您确定要出售吗?")) {
						$("#SaleCardForm").submit();

					}
				} else {
					alert("您申领的卡已经售光,请再去申领")
				}


			} else {
				alert("请选择户籍")
			}

		} else {
			alert("请输入完整")
		}
	} else {
		alert("请输入大于5元")
	}


}

function check_id() {
	// if (isCardNo($("#idNum").val()) == false) {
	// 	alert("无效身份证");
	// 	$("#idNum").val("");
	//
	// }
	var pattern = /^[1-9]\d{5}(18|19|2([0-9]))\d{2}(0[0-9]|10|11|12)([0-2][1-9]|30|31)\d{3}[0-9Xx]$/;
	if (!pattern.test($("#idNum").val())) {
		alert("身份证输入错误");
		$("#idNum").val("");
	}

}

//
// function isCardNo(num) {
// 	num = num.toUpperCase();
// 	//身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。   
// 	if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
// 		return false;
// 	}
// 	//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
// 	//下面分别分析出生日期和校验位 
// 	var len, re;
// 	len = num.length;
// 	if (len == 15) {
// 		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
// 		var arrSplit = num.match(re);
//
// 		//检查生日日期是否正确 
// 		var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
// 		var bCorrectDay;
// 		bCorrectDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) &&
// 			(
// 				dtmBirth.getDate() == Number(arrSplit[4]));
// 		if (!bCorrectDay) {
// 			return false;
// 		} else {
// 			//将15位身份证转成18位 
// 			//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
// 			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
// 			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
// 			var nTemp = 0,
// 				i;
// 			num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
// 			for (i = 0; i < 17; i++) {
// 				nTemp += num.substr(i, 1) * arrInt[i];
// 			}
// 			num += arrCh[nTemp % 11];
// 			return true;
// 		}
// 	}
// 	if (len == 18) {
// 		re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
// 		var arrSplit = num.match(re);
//
// 		//检查生日日期是否正确 
// 		var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
// 		var bCorrectDay;
// 		bCorrectDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) &&
// 			(dtmBirth.getDate() == Number(arrSplit[4]));
// 		if (!bCorrectDay) {
// 			return false;
// 		} else {
// 			//检验18位身份证的校验码是否正确。 
// 			//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
// 			var valnum;
// 			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
// 			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
// 			var nTemp = 0,
// 				i;
// 			for (i = 0; i < 17; i++) {
// 				nTemp += num.substr(i, 1) * arrInt[i];
// 			}
// 			valnum = arrCh[nTemp % 11];
// 			if (valnum != num.substr(17, 1)) {
// 				return false;
// 			}
// 			return true;
// 		}
// 	}
// 	return false;
// }
