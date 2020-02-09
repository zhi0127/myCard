function js_ajaxMehod(node) {


	var data = node.value;

	//利用脚本的方式 创建 一个对象
	var ob = {sendMsg: data};
	//对象转换成字符串
	var str = JSON.stringify(ob);

	// alert(str);
	// alert(typeof str);
	if (data.length > 0) {
		//原生脚本的ajax
		var xhr = new XMLHttpRequest();

		//触发链接的事件
		xhr.onreadystatechange = function () {
			//判断状态
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					//成功
					// alert(xhr.responseText);
					var jsonstr = xhr.responseText;
					var ob2 = JSON.parse(jsonstr);
					// alert(ob2.sendMsg);
				}
			}
		}

		//打开链接
		xhr.open('post', 'AjaxServlet', true);
		//发送
		xhr.send("msg = " + str);
	}

}

function jq_ajaxMethod() {

	var data = $("#myText").val();

	//利用脚本的方式 创建 一个对象
	var ob = {sendMsg: data};
	//对象转换成字符串
	var str = JSON.stringify(ob);

	// alert(str);
	// alert(typeof str);

	$.ajax({
		type: "POST",//提奥的方式
		url: "AjaxServlet",//提交的地址
		data: "msg=" + str,//提交的数据
		dataType: "text",//希望返回的数据类型
		async: true,//异步操作
		success: function (msg) {//成功的方法  msg为返回数据

			var ob2 = JSON.parse(msg);
			alert(ob2.sendMsg);

		},
		error: function () {//错误的方法
			alert("服务器正忙")
		}
	});
}