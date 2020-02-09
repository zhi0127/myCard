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
	<title>人员管理</title>
	<link rel="stylesheet" href=<%=cssPath+"bootstrap.min.css"%>>
	<link rel="stylesheet" href=<%=cssPath+"Back-User.css"%>>
	<script src=<%=JsPath + "jquery-3.4.1.js"%>></script>
	<script src=<%=JsPath + "bootstrap.min.js"%>></script>
	<script src=<%=JsPath + "json2.js"%>></script>
	<script src=<%=JsPath + "Back-User.js"%>></script>
</head>
<body>
<br/><br/><br/>

<input type="hidden" value="${requestScope.loginID}" id="loginID">
<input type="hidden" value="${requestScope.loginName}" id="loginName">
<form action=<%=path+"/BackUserServlet"%>  id="serachfrom" method="post">
	<input type="hidden" value="${requestScope.loginID}" name="loginID">
	<input type="hidden" value="${requestScope.loginName}" name="loginName">
	<table class="search_panel" align="center">
		<tr class="search_panel_info">
			<td>人员姓名:</td>
			<td>
				<input class="field" type="text" id="user-name-text" placeholder="请输入人员姓名" name="userName"
				       maxlength="10" value="${requestScope.userName}"
				       onkeyup="value=value.replace(/[^\a-zA-Z\u4E00-\u9FA5]/g,'')"
				       onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\a-zA-Z\u4E00-\u9FA5]/g,''))">
			</td>

			<td>科室:</td>
			<td>
				<select class="field" name="departType">
					<option value="">==请选择==</option>
					<c:forEach items="${requestScope.selectType.departmentList}" begin="0" step="1" var="i">
						<option value="${i.DEPARTID}"
						        <c:if test="${requestScope.departType == i.DEPARTID}">selected</c:if>>${i.DEPARTNAME}</option>
					</c:forEach>
				</select>
			</td>
			<td colspan="2">
				<input type="button" class="btn" value="新增" data-toggle="modal" data-target="#myModal">

			</td>
		</tr>
		<tr class="search_panel_info">
			<td>角色:</td>
			<td>
				<select class="field" name="roleType">
					<option value="">==请选择==</option>
					<c:forEach items="${requestScope.selectType.roleList}" begin="0" step="1" var="i">
						<option value="${i.ROLEID}"
						        <c:if test="${requestScope.roleType == i.ROLEID}">selected</c:if>>${i.ROLENAME}</option>
					</c:forEach>
				</select>
			</td>
			<td>状态:</td>
			<td>
				<select class="field" name="stateType">
					<option value="">==请选择==</option>
					<option value="启用"
					        <c:if test="${requestScope.stateType == '启用'}">selected</c:if> >启用
					</option>
					<option value="禁用"
					        <c:if test="${requestScope.stateType == '禁用'}">selected</c:if> >禁用
					</option>
				</select>
			</td>
			<td colspan="2">

				<input type="button" class="btn" value="搜索" onclick="myaction(this)"/>
			</td>
		</tr>
	</table>
	<div id="myyy">
		<table class="order_panel" align="center" border="1" bordercolor="#E4E4E4" cellpadding="0" cellspacing="0">
			<tr class="order_panel_title" align="center">
				<td width="5%">序号</td>
				<td width="10%">账号</td>
				<td width="10%">人员姓名</td>
				<td width="20%">科室</td>
				<td width="15%">角色</td>
				<td width="10%">状态</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.msg.list}" begin="0" step="1" var="i">
				<tr style="text-align: center">
					<td hidden>${i.DEPARTID}</td>
					<td hidden>${i.ROLEID}</td>
					<td>${i.RN}</td>
					<td>${i.PERSONID}</td>
					<td>${i.PERSONNAME}</td>
					<td>${i.DEPARTNAME}</td>
					<td>${i.ROLENAME}</td>
					<td>${i.STATENAME}</td>
					<td>
						<input type="button" class="btn" value="修改" onclick="updateMethod(this)">

						<c:choose>
							<c:when test="${i.STATENAME == '启用'}">
								<input type="button" class="btn" value="禁用" onclick="ajaxMethod(this)">
							</c:when>
							<c:otherwise>
								<input type="button" class="btn" value="启用" onclick="ajaxMethod(this)">
							</c:otherwise>
						</c:choose>
						<input type="button" class="btn" value="删除" onclick="ajaxMethod(this)">
						<input type="button" class="btn" value="重置密码" onclick="ajaxMethod(this)">
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


<!-- 新增人员模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="text-align: center">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					新增人员
				</h4>
			</div>

			<div class="modal-body">
				<form id="newfrom" method="post" action=<%=path+"/BackNewPersonServlet"%>>
					<input type="hidden" value="${requestScope.loginID}" name="loginID">
					<input type="hidden" value="${requestScope.loginName}" name="loginName">
					<table class="search_panel" align="center">
						<tr class="search_panel_info">
							<td>人员姓名:</td>
							<td>
								<input id="user-name1-text" class="field" type="text" placeholder="请输入人员姓名"
								       name="newUserName" maxlength="10"
								       onkeyup="value=value.replace(/[^\a-zA-Z\u4E00-\u9FA5]/g,'')"
								       onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\a-zA-Z\u4E00-\u9FA5]/g,''))">
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>密码:</td>
							<td>
								<input id="user-pass-text" class="field" type="password" placeholder="请输入密码"
								       name="newUserPass" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')"
								       oncontextmenu="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" maxlength="10">
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>确认密码:</td>
							<td>
								<input id="user-rePass-text" class="field" type="password" placeholder="请确认密码"
								       onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')"
								       oncontextmenu="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" maxlength="10">
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>科室:</td>
							<td>
								<select class="field" name="newDepartType" id="newDepartType">
									<option value="">==请选择==</option>
									<c:forEach items="${requestScope.selectType.departmentList}" begin="0" step="1"
									           var="i">
										<option value="${i.DEPARTID}">${i.DEPARTNAME}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>角色:</td>
							<td>
								<select class="field" name="newRoleType" id="newRoleType">
									<option value="">==请选择==</option>
									<c:forEach items="${requestScope.selectType.roleList}" begin="0" step="1" var="i">
										<option value="${i.ROLEID}">${i.ROLENAME}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="modal-footer" id="modal-footer">
				<button type="button" class="btn btn-primary" onclick="submitMehod()">
					提交
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					返回
				</button>

			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>


<!-- 模态框（Modal）2 -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="text-align: center">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel2">
					修改人员
				</h4>
			</div>

			<div class="modal-body">
				<form id="newfrom2" method="post" action=<%=path+"/BackUpdatePersonServlet"%>>
					<input type="hidden" value="${requestScope.loginID}" name="loginID">
					<input type="hidden" value="${requestScope.loginName}" name="loginName">
					<table class="search_panel" align="center">
						<tr class="search_panel_info">
							<td>人员姓名:</td>
							<td>
								<input type="hidden" id="hiddenUserID" name="hiddenUserID">
								<input type="hidden" id="hiddenUserName">
								<label id="user-name1-text2">人员姓名</label>

							</td>
						</tr>
						<tr class="search_panel_info">
							<td>科室:</td>
							<td>
								<input type="hidden" id="hiddenDepartType">
								<select class="field" name="updateDepartType" id="newDepartType2">

									<c:forEach items="${requestScope.selectType.departmentList}" begin="0" step="1"
									           var="i">
										<option value="${i.DEPARTID}">${i.DEPARTNAME}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>角色:</td>
							<td>
								<input type="hidden" id="hiddenRoleType">
								<select class="field" name="updateRoleType" id="newRoleType2">

									<c:forEach items="${requestScope.selectType.roleList}" begin="0" step="1" var="i">
										<option value="${i.ROLEID}">${i.ROLENAME}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="modal-footer" id="modal-footer2" style="text-align: center">
				<button type="button" class="btn btn-primary" onclick="updateMethod(this)" value="提交修改">
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
