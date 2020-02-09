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
	<title>领卡审批</title>
	<link rel="stylesheet" href=<%=cssPath+"bootstrap.min.css"%>>
	<link rel="stylesheet" href=<%=cssPath+"Back-User.css"%>>
	<script src=<%=JsPath + "jquery-3.4.1.js"%>></script>
	<script src=<%=JsPath + "bootstrap.min.js"%>></script>
	<script src=<%=JsPath + "json2.js"%>></script>
	<script src=<%=JsPath + "Back-CardApproval.js"%>></script>
</head>
<body>
<br/><br/><br/>
<input type="hidden" id="isSuccess" value="${requestScope.isSuccess}">
<input type="hidden" value="${requestScope.loginID}" id="loginID">
<input type="hidden" value="${requestScope.loginName}" id="loginName">
<form action=<%=path+"/BackCardApprovalServlet"%>  id="serachfrom" method="post">
	<input type="hidden" value="${requestScope.loginID}" name="loginID">
	<input type="hidden" value="${requestScope.loginName}" name="loginName">
	<table class="search_panel" align="center">
		<tr class="search_panel_info">
			<td>申请人:</td>
			<td>
				<select class="field" name="applyPersonID">
					<option value="">==请选择==</option>
					<c:forEach items="${requestScope.selectType.collaCardList}" begin="0" step="1" var="i">
						<option value="${i.APPLYPERSONID}"
						        <c:if test="${requestScope.applyPersonID == i.APPLYPERSONID}">selected</c:if>>${i.APPLYPERSONNAME}</option>
					</c:forEach>
				</select>
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
			<td>申请日期:</td>
			<td>
				<input class="field" type="date" id="startTime" name="startTime" value="${requestScope.startTime}">
			</td>
			<td>至</td>
			<td>
				<input class="field" type="date" id="endTime" name="endTime" value="${requestScope.endTime}">
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
				<td width="10%">申请时间</td>
				<td width="10%">申请人</td>
				<td width="10%">卡前缀</td>
				<td width="10%">申请数量</td>
				<td width="10%">审核人</td>
				<td width="10%">审核时间</td>
				<td width="10%">状态</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.msg.list}" begin="0" step="1" var="i">
				<tr style="text-align: center">
					<td hidden>${i.AUDITPERSONID}</td>
					<td hidden>${i.COLLACARDID}</td>
					<td>${i.RN}</td>
					<td>${i.APPLYDATE}</td>
					<td>${i.APPLYPERSONNAME}</td>
					<td>${i.CARDHEAD}</td>
					<td>${i.APPLYNUM}</td>
					<td>${i.AUDITPERSONNAME}</td>
					<td>${i.AUDITDATE}</td>
					<td>${i.STATENAME}</td>
					<td>
						<c:choose>
							<c:when test="${i.STATENAME == '待审核'}">
								<input type="button" class="btn" value="审核" onclick="getStartNumMethod(this)">
								<input type="button" class="btn" value="查看" onclick="ajaxMethod(this)">
							</c:when>
							<c:otherwise>
								<input type="button" class="btn" value="查看" onclick="ajaxMethod(this)">
							</c:otherwise>
						</c:choose>
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


<!-- 领卡审批模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="text-align: center">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					领卡审批
				</h4>
			</div>

			<div class="modal-body">
				<form id="newfrom" method="post" action=<%=path+"/BackPassServlet"%>>
					<input type="hidden" value="${requestScope.loginID}" name="loginID">
					<input type="hidden" value="${requestScope.loginName}" name="loginName">
					<table class="search_panel" align="center">
						<tr class="search_panel_info">
							<td>申请人:</td>
							<td>
								<input id="collacardid" class="field" type="hidden" readonly name="collacardid">
								<input id="applyPerson-name-text" class="field" type="text" readonly
								       name="applyPersonName">

							</td>
						</tr>
						<tr class="search_panel_info">
							<td>申请日期:</td>
							<td>
								<input class="field" type="hidden" id="auditDate-text" name="auditDate" readonly>
								<input class="field" type="text" id="applyDate-text" name="applyDate" readonly>
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>卡前缀:</td>
							<td>
								<input id="card-head-text" class="field" type="text" placeholder="前缀" name="CardHead"
								       readonly>
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>申请卡数量:</td>
							<td>
								<input id="applyNum" class="field" type="text" placeholder="申请数量" name="applyNum"
								       readonly>
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>请输入开始卡号:</td>
							<td>
								<input id="card-start-text" class="field" type="text" placeholder="开始卡号"
								       name="newCardStart" readonly>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="modal-footer" id="modal-footer">
				<button type="button" class="btn btn-primary" id="passed" onclick="passMethod(this)" value="审核通过">
					审核通过
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					返回
				</button>

			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>


<!-- 查看模态框（Modal） -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="text-align: center">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel2">
					查看
				</h4>
			</div>

			<div class="modal-body">
				<table class="search_panel" align="center">
					<tr class="search_panel_info">
						<td>申请人:</td>
						<td>
							<input id="applyPerson-name2-text" class="field" type="text" readonly
							       name="applyPersonName">

						</td>
					</tr>
					<tr class="search_panel_info">
						<td>申请时间:</td>
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
					<tr class="search_panel_info">
						<td>审核人:</td>
						<td>
							<input id="auditPerson-name-text" class="field" type="text" readonly name="auditPersonName">

						</td>
					</tr>
					<tr class="search_panel_info">
						<td>审核时间:</td>
						<td>
							<input class="field" type="text" id="auditDate" name="auditDate" readonly>
						</td>
					</tr>
					<tr class="search_panel_info">
						<td>领用卡号:</td>
						<td>
							<%--								<input id="creditCardNum" class="field" type="text"  placeholder="领用卡号" name="creditCardNum"  readonly  >--%>
							<textarea id="Credit-card-number"
							          style=" min-width: 180px; min-height: 100px; max-width: 180px; max-height: 100px;"
							          readonly>45</textarea>
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
