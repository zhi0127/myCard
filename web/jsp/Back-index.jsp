<%@ page import="java.util.*" %>
<%@ page import="com.great.javabean.TbMenu" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/12
  Time: 0:38
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
	<title>医院后台系统</title>
	<link rel="stylesheet" href=<%=cssPath+"bootstrap.css"%>>
	<link rel="stylesheet" href=<%=cssPath+"Back-index.css"%>>
	<script src=<%=JsPath + "jquery-3.4.1.js"%>></script>
	<script src=<%=JsPath + "bootstrap.min.js"%>></script>
	<script src=<%=JsPath + "json2.js"%>></script>
	<script src=<%=JsPath + "Back-index.js"%>></script>
</head>
<body>
<input type="hidden" value="${requestScope.loginID}" id="loginID">
<input type="hidden" value="${requestScope.loginName}" id="loginName">
<div id="head">
	<div id="page-title"><h1>医院后台系统</h1></div>
	<div id="user-name-label">
		<c:choose>
			<c:when test="${requestScope.loginName != null}">
				<span>欢迎你,${requestScope.loginName}</span>
				<span>/</span>
				<span><a href="javascript:void(0)" id="zx">注销</a></span>
				<span>/</span>
				<span><a href="javascript:void(0)" data-toggle="modal" data-target="#myModal">修改密码</a></span>
			</c:when>
		</c:choose>
	</div>

</div>
<div id="aside">
	<c:if test="${requestScope.menu != null}">
		<c:forEach items="${requestScope.menu}" begin="0" step="1" var="x">
			<h2>${x.key}</h2>
			<div class="close">
				<ul>
					<c:forEach items="${x.value}" begin="0" step="1" var="y">
						<li><a class="subitem"
						       title="${y.MENUURL}?loginID=${requestScope.loginID}&loginName=${requestScope.loginName}"
						       href="javascript:void(0)">${y.childName}</a></li>
					</c:forEach>
				</ul>
			</div>
		</c:forEach>
	</c:if>

</div>
<div id="section">
	<iframe src="" frameborder="0" name="page-view" id="myiframe"></iframe>
</div>


<!-- 模态框（Modal）修改密码 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="text-align: center">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title">
					修改密码
				</h4>
			</div>

			<div class="modal-body" style="margin-left: 25%">
				<form id="newfrom2" method="post" action=<%=path+"/BackUpdatePersonServlet"%>>
					<input type="hidden" value="${requestScope.loginID}" name="loginID">
					<input type="hidden" value="${requestScope.loginName}" name="loginName">
					<table class="search_panel" align="center">
						<tr class="search_panel_info">
							<td>旧密码:</td>
							<td>
								<input type="text" id="oldPass" name="oldPass">
							</td>
						</tr>
						<tr class="search_panel_info">
							<td>新密码:</td>
							<td>
								<input type="password" id="newPass" name="newPass"
								       onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')"
								       oncontextmenu="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')">

							</td>
						</tr>
						<tr class="search_panel_info">
							<td>确认密码:</td>
							<td>
								<input type="password" id="reNewPass"
								       onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')"
								       oncontextmenu="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')">

							</td>
						</tr>

					</table>
				</form>
			</div>
			<div class="modal-footer" id="modal-footer2" style="text-align: center">
				<button type="button" class="btn" onclick="updateMethod( )" value="提交修改">
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