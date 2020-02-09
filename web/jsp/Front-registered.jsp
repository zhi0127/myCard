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
	String path = request.getContextPath() + "/js/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>预约挂号</title>
	<link rel="stylesheet" href=<%=cssPath+"bootstrap.css"%>>
	<link rel="stylesheet" href=<%=cssPath+"Back-User.css"%>>
	<script src=<%=jsPath + "jquery-3.4.1.js"%>></script>
	<script src=<%=jsPath + "bootstrap.min.js"%>></script>
	<script src=<%=jsPath + "Front-registered.js"%>></script>


	<style>
		.btn {
			width: 200px;
			margin-left: 41%;
		}

		.btn-schedule {
			width: 80%;
			margin-left: 10%;
		}

		.btn-default {
			width: 100px;
			margin-left: 25%;
			text-align: center; /*内容居中*/
		}

		.form-control {
			width: 50%;
			display: inline-block;
			clear: both;
		}

		.btn-registered {
			width: 50%;
			margin-left: 10%;
			color: white;
			background-color: green;
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
<div class="container" style="width: 82%;">
	<div class="row clearfix">
		<div class="col-md-12 column">

			<div class="panel panel-primary" style="margin-top: 30px;">

				<div class="panel-heading">
					<h3 class="panel-title">
						预约挂号<input type="hidden" id="myID">
					</h3>
				</div>
				<form class="form-horizontal" role="form" method="post" id="form-weeks">
					<input type="hidden" id="oldCardID" name="oldCardID">
					<input type="hidden" id="patientID" name="patientID">

					<div class="panel-body">
						<div class="form-group">
							<div class="col-sm-6 name">
								<label class="control-label">请输入卡号:</label>
								<input class="form-control" id="oldCard" name="oldCard" placeholder="请输入" type="text"
								       style="width: 82%" maxlength="18"
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
								<label class="control-label" for="deposit">元 押金:</label>
								<input class="form-control ag" id="deposit" value="5元" type="text" readonly/>
							</div>
						</div>
					</div>
					<table class="order_panel" align="center" border="1" bordercolor="#E4E4E4" cellpadding="0"
					       cellspacing="0" style="width: 80%">
						<thead>
						<tr id="monitor">
							<th>科室/日期</th>
							<input type="hidden" name="now-Date" value="${requestScope.tableHead[0]}">
							<th width="12%">${requestScope.tableHead[0]}<br>星期一</th>
							<th width="12%">${requestScope.tableHead[1]}<br>星期二</th>
							<th width="12%">${requestScope.tableHead[2]}<br>星期三</th>
							<th width="12%">${requestScope.tableHead[3]}<br>星期四</th>
							<th width="12%">${requestScope.tableHead[4]}<br>星期五</th>
							<th width="12%">${requestScope.tableHead[5]}<br>星期六</th>
							<th width="12%">${requestScope.tableHead[6]}<br>星期日</th>
						</tr>
						</thead>
						<tbody id="table-body">
						<c:if test="${requestScope.tableBody!=null}">
							<c:forEach items="${requestScope.tableBody}" var="i" begin="0" step="1">
								<tr>
									<td>${i.key}</td>
									<td>
										<input type="hidden" value="${requestScope.tableHead[0]}">
										<c:forEach items="${i.value}" begin="0" step="1" var="j">
											<c:if test="${j.SCHEDULEDATE==requestScope.tableHead[0]}">
												<button type="button" class="btn btn-schedule" value="${j.PERSONID}"
												        onclick="showRegistered(this)">${j.PERSONNAME}</button>
											</c:if>
										</c:forEach>
									</td>
									<td>
										<input type="hidden" value="${requestScope.tableHead[1]}">
										<c:forEach items="${i.value}" begin="0" step="1" var="j">
											<c:if test="${j.SCHEDULEDATE==requestScope.tableHead[1]}">
												<button type="button" class="btn btn-schedule" value="${j.PERSONID}"
												        onclick="showRegistered(this)">${j.PERSONNAME}</button>
											</c:if>
										</c:forEach>
									</td>
									<td>
										<input type="hidden" value="${requestScope.tableHead[2]}">
										<c:forEach items="${i.value}" begin="0" step="1" var="j">
											<c:if test="${j.SCHEDULEDATE==requestScope.tableHead[2]}">
												<button type="button" class="btn btn-schedule" value="${j.PERSONID}"
												        onclick="showRegistered(this)">${j.PERSONNAME}</button>
											</c:if>
										</c:forEach>
									</td>
									<td>
										<input type="hidden" value="${requestScope.tableHead[3]}">
										<c:forEach items="${i.value}" begin="0" step="1" var="j">
											<c:if test="${j.SCHEDULEDATE==requestScope.tableHead[3]}">
												<button type="button" class="btn btn-schedule" value="${j.PERSONID}"
												        onclick="showRegistered(this)">${j.PERSONNAME}</button>
											</c:if>
										</c:forEach>
									</td>
									<td>
										<input type="hidden" value="${requestScope.tableHead[4]}">
										<c:forEach items="${i.value}" begin="0" step="1" var="j">
											<c:if test="${j.SCHEDULEDATE==requestScope.tableHead[4]}">
												<button type="button" class="btn btn-schedule" value="${j.PERSONID}"
												        onclick="showRegistered(this)">${j.PERSONNAME}</button>
											</c:if>
										</c:forEach>
									</td>
									<td>
										<input type="hidden" value="${requestScope.tableHead[5]}">
										<c:forEach items="${i.value}" begin="0" step="1" var="j">
											<c:if test="${j.SCHEDULEDATE==requestScope.tableHead[5]}">
												<button type="button" class="btn btn-schedule" value="${j.PERSONID}"
												        onclick="showRegistered(this)">${j.PERSONNAME}</button>

											</c:if>
										</c:forEach>
									</td>
									<td>
										<input type="hidden" value="${requestScope.tableHead[6]}">
										<c:forEach items="${i.value}" begin="0" step="1" var="j">
											<c:if test="${j.SCHEDULEDATE==requestScope.tableHead[6]}">
												<button type="button" class="btn btn-schedule" value="${j.PERSONID}"
												        onclick="showRegistered(this)">${j.PERSONNAME}</button>
											</c:if>
										</c:forEach>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						</tbody>


					</table>
					<input type="hidden" name="doWhich" id="doWhich">
					<input type="button" class="btn btn-default" value="上一周" onclick="weeks(this)"/>
					<input type="button" class="btn btn-default" value="下一周" onclick="weeks(this)"/>
				</form>

			</div>

		</div>
		<button class="btn btn-primary btn-default active" type="button" onclick="fh()">返回</button>

	</div>
</div>

<!-- 医生门诊模态框（Modal） -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="text-align: center">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<span id="personName"></span>医生门诊时间
				</h4>
			</div>

			<div class="modal-body">
				<form id="newfrom" method="post" action=<%=path+"/BackNewPersonServlet"%>>
					<table class="search_panel" align="center" border="1px" id="schedule">
						<tr class="search_panel_info">
							<td>
								门诊时间
							</td>
							<td>
								预约人
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>09:00-10:00</td>
							<td>

								<%--								<input type="button" class="btn btn-showRegistered"  onclick="showRegistered(this)">--%>
								<button type="button" class="btn btn-registered" onclick="registered(this)"></button>

							</td>
						</tr>
						<tr class="search_panel_info">
							<td>10:00-11:00</td>
							<td>
								<%--								<input type="button" class="btn btn-showRegistered"  onclick="showRegistered(this)">--%>
								<button type="button" class="btn btn-registered" onclick="registered(this)"></button>


							</td>
						</tr>
						<tr class="search_panel_info">
							<td>11:00-12:00</td>
							<td>
								<%--								<input type="button" class="btn btn-showRegistered"  onclick="showRegistered(this)" >--%>
								<button type="button" class="btn btn-registered" onclick="registered(this)"></button>
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>14:00-15:00</td>
							<td>
								<%--								<input type="button" class="btn btn-showRegistered"  onclick="showRegistered(this)">--%>
								<button type="button" class="btn btn-registered" onclick="registered(this)"></button>
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>15:00-16:00</td>
							<td>
								<%--								<input type="button" class="btn btn-showRegistered"  onclick="showRegistered(this)" >--%>
								<button type="button" class="btn btn-registered" onclick="registered(this)"></button>
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>16:00-17:00</td>
							<td>
								<%--								<input type="button" class="btn btn-showRegistered" onclick="showRegistered(this)" >--%>
								<button type="button" class="btn btn-registered" onclick="registered(this)"></button>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="modal-footer" id="modal-footer">
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

</body>
</html>
