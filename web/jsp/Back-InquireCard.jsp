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
	<title>卡查询</title>
	<link rel="stylesheet" href=<%=cssPath+"bootstrap.min.css"%>>
	<link rel="stylesheet" href=<%=cssPath+"Back-User.css"%>>
	<script src=<%=JsPath + "jquery-3.4.1.js"%>></script>
	<script src=<%=JsPath + "bootstrap.min.js"%>></script>
	<script src=<%=JsPath + "json2.js"%>></script>
	<script src=<%=JsPath + "Back-InquireCard.js"%>></script>
</head>
<body>
<br/><br/><br/>
<input type="hidden" value="${requestScope.loginID}" id="loginID">
<input type="hidden" value="${requestScope.loginName}" id="loginName">
<form action=<%=path+"/BackInquireCardServlet"%>  id="serachfrom" method="post">
	<input type="hidden" value="${requestScope.loginID}" name="loginID">
	<input type="hidden" value="${requestScope.loginName}" name="loginName">
	<table class="search_panel" align="center">
		<tr class="search_panel_info">
			<td>卡号:</td>
			<td>
				<input class="field" type="text" id="startNum" placeholder="输入卡号,前缀大写" name="card"
				       value="${requestScope.card}" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'').toUpperCase()"
				       oncontextmenu="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" maxlength="13">
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
					<option value="8"
					        <c:if test="${requestScope.cardState == '11'}">selected</c:if> >待销售
					</option>
					<option value="9"
					        <c:if test="${requestScope.cardState == '9'}">selected</c:if> >已出售
					</option>
					<option value="10"
					        <c:if test="${requestScope.cardState == '10'}">selected</c:if> >已换卡
					</option>
					<option value="11"
					        <c:if test="${requestScope.cardState == '11'}">selected</c:if> >已注销
					</option>
				</select>
			</td>
			<td>领用人:</td>
			<td>
				<select class="field" name="applyPersonID">
					<option value="">==请选择==</option>
					<c:forEach items="${requestScope.selectType.collaCardList}" begin="0" step="1" var="i">
						<option value="${i.APPLYPERSONID}"
						        <c:if test="${requestScope.applyPersonID == i.APPLYPERSONID}">selected</c:if>>${i.APPLYPERSONNAME}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr class="search_panel_info">
			<td colspan="2">
				<input type="button" class="btn" value="查询" onclick="myaction(this)"/>
			</td>
		</tr>
	</table>
	<div id="myyy">
		<table class="order_panel" align="center" border="1" bordercolor="#E4E4E4" cellpadding="0" cellspacing="0">
			<tr class="order_panel_title" align="center">
				<td width="10%">序号</td>
				<td width="20%">卡号</td>
				<td width="20%">卡状态</td>
				<td width="20%">领用人</td>
				<td width="20%">就诊人</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.msg.list}" begin="0" step="1" var="i">
				<tr style="text-align: center">
					<td hidden>${i.CARDID}</td>
					<td>${i.RN}</td>
					<td>${i.CARDHEAD}${i.CARDNUM}</td>
					<td>${i.STATENAME}</td>
					<td>${i.APPLYPERSONNAME}</td>
					<td>${i.PATIENTNAME}</td>
					<td>
						<input type="button" class="btn" value="查看" onclick="ajaxMethod(this)">

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

<!-- 查看模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="text-align: center ">
	<div class="modal-dialog">
		<div class="modal-content" style="width: 700px">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel2">
					查看卡信息
				</h4>
			</div>

			<div class="modal-body">
				<table class="search_panel" align="center">
					<tr class="search_panel_info">
						<td>卡号:</td>
						<td>
							<input id="card" class="field" type="text" readonly>

						</td>
						<td>卡余额:</td>
						<td>
							<input id="money" class="field" type="text" readonly>

						</td>
					</tr>
					<tr class="search_panel_info">
						<td>卡状态:</td>
						<td>
							<input class="field" type="text" id="cardState" readonly>
						</td>
						<td>就诊人:</td>
						<td>
							<input class="field" type="text" id="patientName" readonly>
						</td>
					</tr>
					<tr class="search_panel_info">
						<td>领用人:</td>
						<td>

							<input id="applyPersonName" class="field" type="text">
						</td>
						<td>领用时间:</td>
						<td>

							<input id="applyDate" class="field" type="text">
						</td>
					</tr>
					<tr class="search_panel_info">
						<td>售卡人:</td>
						<td>
							<input id="cardSeller" class="field" type="text" readonly>

						</td>
						<td>售卡时间:</td>
						<td>
							<input id="cardSalesTime" class="field" type="text" readonly>

						</td>
					</tr>
				</table>

			</div>
			<div class="modal-footer" id="modal-footer2">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					返回
				</button>

			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

</body>
</html>
