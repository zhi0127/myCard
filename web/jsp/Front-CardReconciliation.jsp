<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/10/13
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String cssPath = request.getContextPath() + "/css/";
	String jsPath = request.getContextPath() + "/js/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>一卡通_卡对账</title>
	<script src=<%=jsPath + "jquery-3.4.1.js"%>></script>
	<script src=<%=jsPath + "bootstrap.min.js"%>></script>
	<script src=<%=jsPath + "Front-CardReconciliation.js"%>></script>
	<link rel="stylesheet" href=<%=cssPath+"bootstrap.css"%>>
	<link rel="stylesheet" href=<%=cssPath+"Back-User.css"%>>

	<style>
		.btn {
			width: 200px;
			margin-left: 41%;
		}

		.form-control {
			width: 50%;
			display: inline-block;
			clear: both;
		}

		.ag {
			width: 15%;
		}

		.name {
			padding-left: 20%;
		}

		.form-group {
			margin-top: 40px;
		}
	</style>
</head>

<body>
<input type="hidden" value="${requestScope.msg}" id="msg">
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">

			<div class="panel panel-primary" style="margin-top: 30px;">

				<div class="panel-heading">
					<h3 class="panel-title">
						卡对账<input type="hidden" id="myID">
					</h3>
				</div>
				<form class="form-horizontal" role="form" id="changeCardForm">
					<input type="hidden" id="oldCardID" name="oldCardID">
					<input type="hidden" id="patientID" name="patientID">

					<div class="panel-body">
						<div class="form-group">
							<div class="col-sm-6 name">
								<label class="control-label">请输入卡号:</label>
								<input class="form-control" id="oldCard" name="oldCard" placeholder="请输入" type="text"
								       style="width: 82%;" maxlength="18"
								       onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'').toUpperCase()"
								       oncontextmenu="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')"/>
							</div>
							<div class="col-sm-6">
								<button class="btn btn-primary btn-default active" type="button"
								        onclick="readCardMethod()">读卡
								</button>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-6 name">
								<label class="control-label" for="inputName">姓名:</label>
								<input class="form-control" id="inputName" type="text" style="width: 50%" readonly/>
							</div>
							<div class="col-sm-6 ">
								<label class="control-label" for="sexy">性别:</label>
								<input class="form-control ag" id="sexy" style="width: 50px" type="text" readonly/>
								<label class="control-label" for="age">年龄:</label>
								<input class="form-control ag" id="age" style="width: 50px" type="text" readonly/>&nbsp;&nbsp;&nbsp;岁&nbsp;&nbsp;
								<input class="form-control ag" id="age1" type="text" style="width: 50px" readonly/>
								&nbsp;&nbsp;周
								<label class="control-label" for="birthPlace">籍贯:</label>
								<input class="form-control ag" id="birthPlace" type="text" style="width: 150px"
								       readonly/>

							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-7">
								<label class="control-label" for="idNum" style="margin-left: 30%">证件号码:</label>
								<input class="form-control" id="idNum" type="text" style="width: 260px;" readonly/>
							</div>
							<div class="col-sm-5">
								<label class="control-label" for="cellphone">联系电话:</label>
								<input class="form-control" id="cellphone" type="text" readonly/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<label class="control-label" for="inputAddress" style="margin-left: 20%;">现住址:</label>

								<input class="form-control" id="inputAddress" type="text" style="width: 57%;" readonly/>
							</div>

						</div>
						<div class="form-group">
							<div class="col-sm-6 name">
								<label class="control-label" for="money">卡金额:</label>
								<input class="form-control" id="money" type="text" readonly/>
								<label class="control-label" for="deposit">押金:</label>
								<input class="form-control ag" id="deposit" value="5元" type="text" readonly/>
							</div>
						</div>
					</div>
					<table class="order_panel" align="center" border="1" bordercolor="#E4E4E4" cellpadding="0"
					       cellspacing="0" style="width: 50%">
						<tr class="order_panel_title" align="center">
							<td width="30%">发生时间</td>
							<td width="20%">发生事项</td>
							<td width="20%">发生金额</td>
							<td>执行人</td>
						</tr>
						<tr style="text-align: center">
							<td>发生时间</td>
							<td>发生事项</td>
							<td>发生金额</td>
							<td>执行人</td>
						</tr>
						<tr style="text-align: center">
							<td>发生时间</td>
							<td>发生事项</td>
							<td>发生金额</td>
							<td>执行人</td>
						</tr>
						<tr style="text-align: center">
							<td>发生时间</td>
							<td>发生事项</td>
							<td>发生金额</td>
							<td>执行人</td>
						</tr>

					</table>


				</form>
			</div>
			<button class="btn btn-primary btn-default active" type="button" onclick="fh()">返回</button>

		</div>
	</div>
</div>

</body>
</html>
