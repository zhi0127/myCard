<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/15
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cssPath = request.getContextPath() + "/css/";
	String JsPath = request.getContextPath() + "/js/";
	String path = request.getContextPath();
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>卡入库</title>
	<link rel="stylesheet" href=<%=cssPath+"bootstrap.min.css"%>>
	<link rel="stylesheet" href=<%=cssPath+"Back-User.css"%>>
	<script src=<%=JsPath + "jquery-3.4.1.js"%>></script>
	<script src=<%=JsPath + "bootstrap.min.js"%>></script>
	<script src=<%=JsPath + "json2.js"%>></script>
	<script src=<%=JsPath + "Back-Card.js"%>></script>
</head>
<body>
<br/><br/><br/>
<input type="hidden" value="${requestScope.loginID}" id="loginID">
<input type="hidden" value="${requestScope.loginName}" id="loginName">
<form action=<%=path+"/BackInCardServlet"%>  id="serachfrom" method="post">
	<input type="hidden" value="${requestScope.loginID}" name="loginID">
	<input type="hidden" value="${requestScope.loginName}" name="loginName">
	<table class="search_panel" align="center">
		<tr class="search_panel_info">
			<td>前缀:</td>
			<td>
				<select class="field" name="cardHead">
					<option value="">==请选择==</option>
					<c:forEach items="${requestScope.selectType.cardHeadList}" begin="0" step="1" var="i">
						<option value="${i.CARDHEAD}"
						        <c:if test="${requestScope.cardHead == i.CARDHEAD}">selected</c:if>    >${i.CARDHEAD}</option>
					</c:forEach>
				</select>
			</td>
			<td>卡号:</td>
			<td>
				<input class="field" type="text" id="startNum" placeholder="开始卡号" name="startNum"
				       value="${requestScope.startNum}" onkeyup="value=value.replace(/[^0-9]/g,'')" maxlength="8">
			</td>
			<td>至</td>
			<td>
				<input class="field" type="text" id="endNum" placeholder="截止卡号" name="endNum"
				       value="${requestScope.endNum}" onkeyup="value=value.replace(/[^0-9]/g,'')" maxlength="8">
			</td>

			<td>卡状态:</td>
			<td>
				<select class="field" name="cardState">
					<option value="">==请选择==</option>
					<option value="4"
					        <c:if test="${requestScope.cardState == '4'}">selected</c:if> >待领用
					</option>
					<option value="5"
					        <c:if test="${requestScope.cardState == '5'}">selected</c:if> >删除
					</option>
				</select>
			</td>
		</tr>
		<tr class="search_panel_info">
			<td>导入日期:</td>
			<td>
				<input class="field" type="date" id="startTime" name="startTime" value="${requestScope.startTime}">
			</td>
			<td>至</td>
			<td>
				<input class="field" type="date" id="endTime" name="endTime" value="${requestScope.endTime}">
			</td>
			<td colspan="2">
				<input type="button" class="btn" value="查询" onclick="myaction(this)"/>
			</td>
			<td>
				<input type="button" class="btn" value="卡入库" data-toggle="modal" data-target="#myModal">
			</td>
		</tr>
	</table>
	<div id="myyy">
		<table class="order_panel" align="center" border="1" bordercolor="#E4E4E4" cellpadding="0" cellspacing="0">
			<tr class="order_panel_title" align="center">
				<td width="10%">序号</td>
				<td width="10%">卡号</td>
				<td width="20%">导入日期</td>
				<td width="15%">卡状态</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.msg.list}" begin="0" step="1" var="i">
				<tr style="text-align: center">
					<td hidden>${i.CARDID}</td>
					<td>${i.RN}</td>
					<td>${i.CARDHEAD}${i.CARDNUM}</td>
					<td>${i.CARDTIME}</td>
					<td>${i.STATENAME}</td>
					<td>
						<input type="button" class="btn" value="删除" onclick="ajaxMethod(this)">

					</td>
				</tr>
			</c:forEach>

		</table>
	</div>
	<table class="function_panel" align="center" id="function_panel">
		<tr>
			<td>
				<input class="btn" type="button" value="上一页" onclick="myaction(this)"/>
				<span class="font">${requestScope.msg.start} </span>
				<input type="hidden" id="startPage" value="${requestScope.msg.start}" name="startPage">
				<span>/</span>
				<input type="hidden" id="countPage" value="${requestScope.msg.count}" name="startPage">
				<span class="font">${requestScope.msg.count}</span>
				<input class="btn" type="button" value="下一页" onclick="myaction(this)"/>
			</td>
		</tr>
	</table>
</form>


<!-- 卡入库模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="text-align: center">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					卡入库
				</h4>
			</div>

			<div class="modal-body">
				<form id="newfrom" method="post" action=<%=path+"/BackNewCardServlet"%>>
					<input type="hidden" value="${requestScope.loginID}" name="loginID">
					<input type="hidden" value="${requestScope.loginName}" name="loginName">
					<table class="search_panel" align="center">
						<tr class="search_panel_info">
							<td>前缀:</td>
							<td>
								<input id="card-head-text" class="field" type="text" placeholder="请先输入前缀"
								       name="newCardHead" onblur="cardHeadMethod()" onkeyup="value=value.toUpperCase()">
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>数量:</td>
							<td>
								<input id="card-num-text" class="field" type="text" placeholder="请先输入前缀"
								       oninput="cardNumMethod()" maxlength="4" readonly>
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>开始卡号:</td>
							<td>
								<input id="card-start-text" class="field" type="text" placeholder="开始卡号"
								       name="newCardStart" readonly>
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>截止卡号:</td>
							<td>
								<input id="card-end-text" class="field" type="text" placeholder="截止卡号" name="newCardEnd"
								       readonly>
							</td>
							<td>
								<input id="card-time-text" class="field" type="hidden" placeholder="时间"
								       name="newCardTime" readonly>
							</td>
						</tr>

					</table>
				</form>
			</div>
			<div class="modal-footer" id="modal-footer">
				<button type="button" class="btn btn-primary" onclick="addCardMethod()">
					入库
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					返回
				</button>

			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>


</body>
</html>
