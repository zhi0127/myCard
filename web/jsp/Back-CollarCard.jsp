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
	<title>领卡</title>
	<link rel="stylesheet" href=<%=cssPath+"bootstrap.min.css"%>>
	<link rel="stylesheet" href=<%=cssPath+"Back-User.css"%>>
	<script src=<%=JsPath + "jquery-3.4.1.js"%>></script>
	<script src=<%=JsPath + "bootstrap.min.js"%>></script>
	<script src=<%=JsPath + "json2.js"%>></script>
	<script src=<%=JsPath + "Back-CollaCard.js"%>></script>
</head>
<body>
<br/><br/><br/>
<input type="hidden" value="${requestScope.loginID}" id="loginID">
<input type="hidden" value="${requestScope.loginName}" id="loginName">
<input type="hidden" id="isSuccess" value="${requestScope.isSuccess}">
<form action=<%=path+"/BackCollarCardServlet"%>  id="serachfrom" method="post">
	<input type="hidden" value="${requestScope.loginID}" name="loginID">
	<input type="hidden" value="${requestScope.loginName}" name="loginName">
	<table class="search_panel" align="center">
		<tr class="search_panel_info">

			<td>申请日期:</td>
			<td>
				<input class="field" type="date" id="startTime" name="startTime" value="${requestScope.startTime}">
			</td>
			<td>至</td>
			<td>
				<input class="field" type="date" id="endTime" name="endTime" value="${requestScope.endTime}">
			</td>
			<td>审核状态:</td>
			<td>
				<select class="field" name="auditState">
					<option value="">==请选择==</option>
					<option value="6"
					        <c:if test="${requestScope.auditState == '6'}">selected</c:if> >待审核
					</option>
					<option value="7"
					        <c:if test="${requestScope.auditState == '7'}">selected</c:if> >已审核
					</option>
				</select>
			</td>
		</tr>
		<tr class="search_panel_info">

			<td colspan="4">
			</td>
			<td>
				<input type="button" class="btn" value="新申请" onclick="addMethod(this)">
			</td>
			<td>
				<input type="button" class="btn" value="查询" onclick="myaction(this)"/>
			</td>

		</tr>
	</table>
	<div id="myyy">
		<table class="order_panel" align="center" border="1" bordercolor="#E4E4E4" cellpadding="0" cellspacing="0">
			<tr class="order_panel_title" align="center">
				<td width="10%">序号</td>
				<td width="10%">申请日期</td>
				<td width="10%">卡前缀</td>
				<td width="10%">申请卡数量</td>
				<td width="10%">申请人</td>
				<td width="10%">审核状态</td>
				<td width="10%">审核人</td>
				<td width="10%">审核时间</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.msg.list}" begin="0" step="1" var="i">
				<tr style="text-align: center">
					<td hidden>${i.AUDITPERSONID}</td>
					<td hidden>${i.COLLACARDID}</td>
					<td>${i.RN}</td>
					<td>${i.APPLYDATE}</td>
					<td>${i.CARDHEAD}</td>
					<td>${i.APPLYNUM}</td>
					<td>${i.APPLYPERSONNAME}</td>
					<td>${i.STATENAME}</td>
					<td>${i.AUDITPERSONNAME}</td>
					<td>${i.AUDITDATE}</td>
					<td>
						<input type="button" class="btn" value="修改" onclick="ajaxMethod(this)">
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


<!-- 新增模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="text-align: center">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					申请单
				</h4>
			</div>

			<div class="modal-body">
				<form id="newfrom" method="post" action=<%=path+"/BackNewApplyServlet"%>>
					<input type="hidden" value="${requestScope.loginID}" name="loginID">
					<input type="hidden" value="${requestScope.loginName}" name="loginName">
					<table class="search_panel" align="center">
						<tr class="search_panel_info">
							<td>前缀:</td>
							<td>
								<select class="field" name="cardHead" id="cardHead">
									<c:forEach items="${requestScope.selectType.cardHeadList}" begin="0" step="1"
									           var="i">
										<option value="${i.CARDHEAD}"
										        <c:if test="${requestScope.cardHead == i.CARDHEAD}">selected</c:if>>${i.CARDHEAD}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>申请人:</td>
							<td>
								<input id="applyPerson-name-text" class="field" type="text" readonly
								       name="applyPersonName" value="${requestScope.loginName}">

							</td>
						</tr>
						<tr class="search_panel_info">
							<td>申请日期:</td>
							<td>
								<input class="field" type="text" id="applyDate" name="applyDate" readonly>
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>申请卡数量:</td>
							<td>
								<input id="applyNum" class="field" type="text" placeholder="申请数量" name="applyNum"
								       onkeyup="value=value.replace(/[^0-9]/g,'')" maxlength="5">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="modal-footer" id="modal-footer">
				<button type="button" class="btn btn-primary" onclick="addMethod(this)" value="提交申请">
					提交
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					返回
				</button>

			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>


<!-- 修改模态框（Modal） -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="text-align: center">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel2">
					修改申请单
				</h4>
			</div>

			<div class="modal-body">
				<form id="newfrom2" method="post" action=<%=path+"/BackCollarCardServlet"%>>
					<input type="hidden" value="${requestScope.loginID}" name="loginID">
					<input type="hidden" value="${requestScope.loginName}" name="loginName">
					<table class="search_panel" align="center">
						<tr class="search_panel_info">
							<td>申请人:</td>
							<td>
								<input id="applyPerson-name2-text" class="field" type="text" readonly
								       name="applyPersonName">

							</td>
						</tr>
						<tr class="search_panel_info">
							<td>申请日期:</td>
							<td>
								<input class="field" type="text" id="applyDate2" name="applyDate" readonly>
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>申请卡数量:</td>
							<td>
								<input id="applyNum2" class="field" type="text" placeholder="申请数量" name="applyNum"
								       onkeyup="value=value.replace(/[^0-9]/g,'')">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="modal-footer" id="modal-footer2">
				<button type="button" class="btn btn-primary" onclick=" ajaxMethod(this)" value="提交修改">
					提交
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
