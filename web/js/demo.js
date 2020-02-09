var calendarEl;
var calendar;
$(function () {

	//利用脚本的方式 创建 一个对象
	var ob = {msg1: "get"};
	//对象转换成字符串
	var str = JSON.stringify(ob);
	$.ajax({
		type: "POST",//提交的方式
		url: "ScheduleAjaxServlet",//提交的地址
		data: "msg=" + str,//提交的数据
		dataType: "text",//希望返回的数据类型
		async: true,//异步操作
		success: function (msg) {//成功的方法  msg为返回数据
			var ob2 = JSON.parse(msg);
			$.each(ob2, function (i, n) {
				calendar.addEvent({
					id: n['PERSONID'],// 日程标题;
					title: n['PERSONNAME'],// 日程标题
					start: new Date(n['SCHEDULEDATE']),// 日程起始时间
					end: new Date(n['SCHEDULEDATE']),// 日程结束时间
					allDay: true
					// 日程是否全天
				});
			})

		},
		error: function () {//错误的方法
			alert("服务器正忙")
		}
	});


	// 日历插件初始化
	calendarEl = document.getElementById('calendar');
	// calendarEl=$("#calendar");
	calendar = new FullCalendar.Calendar(calendarEl, {
		plugins: ['interaction', 'dayGrid'],
		defaultDate: new Date(),
		editable: true,
		eventLimit: true, // allow "more" link when too many events
		navLinks: false, // 开启单击天/周名称导航视图
		selectable: true,
		selectMirror: true,
		select: function (arg) {
			$("#starttime").val(new Date(arg.start).format('yyyy-MM-dd'));// 使用时间格式转换时间填入Dialog
			$("#caidan").dialog("open");
			calendar.unselect();

		},
		eventClick: function (info) {
			$("#groups2").text(info.event.title);
			$("#groups3").text(new Date(info.event.start).format('yyyy-MM-dd'));

			$("#sameday").dialog({
				autoOpen: false,
				modal: true,
				width: 550,
				height: 370,
				draggable: false,
				resizable: false,
				buttons: [{
					text: "删除",
					icon: "ui-icon-heart",
					click: function () {
						var personID = info.event.id;
						// alert(personID);
						var scheduleDate = new Date(info.event.start).format('yyyy-MM-dd');
						// alert(scheduleDate);
						if (personID !== null && scheduleDate != null) {
							//利用脚本的方式 创建 一个对象
							var ob = {msg1: "delete", msg2: personID, msg3: scheduleDate};
							//对象转换成字符串
							var str = JSON.stringify(ob);
							$.ajax({
								type: "POST",//提交的方式
								url: "ScheduleAjaxServlet",//提交的地址
								data: "msg=" + str,//提交的数据
								dataType: "text",//希望返回的数据类型
								async: true,//异步操作
								success: function (msg) {//成功的方法  msg为返回数据
									var ob2 = JSON.parse(msg);
									if (ob2 == "删除成功") {
										alert("删除成功");
										info.event.remove();

									} else if (ob2 == "删除失败") {
										alert("删除失败")
									}

								},
								error: function () {//错误的方法
									alert("服务器正忙")
								}
							});
						}
						$(this).dialog("close");// dialog关闭
					}
				},
					// 	{
					// 	text : "确定",
					// 	icon : "ui-icon-heart",
					// 	click : function() {
					// 		info.title = $('#groups option:selected').val(),// 日程标题
					// 		info.start = new Date($("#starttime").val()),// 日程起始时间
					// 		info.end = new Date($("#endtime").val()),// 日程结束时间
					// 		info.allDay = true// 日程是否全天
					// 		$(this).dialog("close");// dialog关闭
					// 	}
					// },
					{
						text: "取消",
						icon: "ui-icon-heart",
						click: function () {
							$(this).dialog("close");
						}
					},],
				show: {
					effect: "blind",
					duration: 1000
				},
				hide: {
					effect: "explode",
					duration: 1000
				}
			});
			$("#sameday").dialog("open");

		},
		validRange: {
			// validRange 为控制锁死某些日期 当前日期的下一天即为明天开始可用 之前的日期全部锁死 官网原版写法 为 start
			// end 设定开始和结束日期
			start: new Date(new Date().getTime() + (1000 * 60 * 60 * 24))
		}
	});

	calendar.render();


	// 初始化dialog
	$("#caidan").dialog({
		autoOpen: false,
		modal: true,
		width: 500,
		height: 250,
		draggable: false,
		resizable: false,

		buttons: [{
			text: "确定",
			icon: "ui-icon-heart",
			click: function () {
				// 创建新的日程

				var personID = $('#groups option:selected').val();
				var scheduleDate = $("#starttime").val();

				if (personID !== null && scheduleDate != null) {

					//利用脚本的方式 创建 一个对象
					var ob = {msg1: "add", msg2: personID, msg3: scheduleDate};
					//对象转换成字符串
					var str = JSON.stringify(ob);
					$.ajax({
						type: "POST",//提交的方式
						url: "ScheduleAjaxServlet",//提交的地址
						data: "msg=" + str,//提交的数据
						dataType: "text",//希望返回的数据类型
						async: true,//异步操作
						success: function (msg) {//成功的方法  msg为返回数据
							var ob2 = JSON.parse(msg);
							if (ob2 == "添加成功") {
								calendar.addEvent({
									id: $('#groups option:selected').val(),// 日程标题
									title: $('#groups option:selected').text(),// 日程标题
									start: new Date($("#starttime").val()),// 日程起始时间
									end: new Date($("#endtime").val()),// 日程结束时间
									allDay: true
									// 日程是否全天
								});
								alert("添加成功")
							} else if (ob2 == "添加失败") {
								alert("添加失败")
							} else if (ob2 == "该排班已经存在") {
								alert("该排班已经存在")
							}

						},
						error: function () {//错误的方法
							alert("服务器正忙")
						}
					});
				}

				$(this).dialog("close");// dialog关闭
			}
		}, {
			text: "取消",
			icon: "ui-icon-heart",
			click: function () {
				$(this).dialog("close");
			}
		},],
		show: {
			effect: "blind",
			duration: 1000
		},
		hide: {
			effect: "explode",
			duration: 1000
		}
	});

	$("#groups").selectmenu();// 下拉列表使用jquery UI样式

	$("#starttime").datepicker({// 日期选择器使用jquery UI样式
		dateFormat: "yy-mm-dd"
	});
	$("#endtime").datepicker({// 日期选择器使用jquery UI样式
		dateFormat: "yy-mm-dd"
	});

	$("input[type='checkbox']").checkboxradio({
		icon: false
	});
	// $("#groups2").selectmenu();
});
// 重构Date对象下的时间格式
Date.prototype.format = function (format) {
	var o = {
		"M+": this.getMonth() + 1,
		"d+": this.getDate(),
		"h+": this.getHours(),
		"m+": this.getMinutes(),
		"s+": this.getSeconds(),
		"q+": Math.floor((this.getMonth() + 3) / 3),
		"S": this.getMilliseconds()
	}
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
			.substr(4 - RegExp.$1.length));
	}
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
				: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
