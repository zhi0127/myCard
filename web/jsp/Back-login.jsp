<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/12
  Time: 0:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String cssPath = request.getContextPath() + "/css/";
	String jsPath = request.getContextPath() + "/js/";
	String path = request.getContextPath();
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>智能一卡通登录系统</title>
	<link rel="stylesheet" href=<%=cssPath+"Back-login.css"%>>
	<script src=<%=jsPath + "jquery-3.4.1.js"%>></script>
	<script src=<%=jsPath + "Back-login.js"%>></script>
</head>
<body>
<input type="hidden" value="${requestScope.isFail}" id="isFail">
<div id="div1">
	<h2>智能一卡通登录系统</h2>
	<form action=<%=path+"/BackLoginServlet"%> method="post" onsubmit="return submitMehod()">
		<label for="loginID-text">账号：</label>
		<input type="text" id="loginID-text" placeholder="用户账号" name="loginID"
		       onkeyup="value=value.replace(/[^0-9]/g,'')" onblur="isLoginDisable()" maxlength="10"><br/>
		<label for="loginPass-text">密码：</label>
		<input type="password" id="loginPass-text" placeholder="用户密码" name="loginPass"
		       onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')"
		       oncontextmenu="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" maxlength="10"><br/>
		<label>验证码：</label>
		<input type="text" id="verification-text" placeholder="验证码" style="width: 20%"
		       onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')"
		       oncontextmenu="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" maxlength="4">
		<input type="text" onclick="createCode()" readonly="readonly" id="checkCode" class="unchanged"
		       style="width: 20%"/><br>
		<input type="submit" value="确认登录">
	</form>


</div>


<%
	String msg = (String) request.getAttribute("msg");
	if (msg != null)
	{
		out.write("<script>alert('" + msg + "')</script>");
	}
%>
</body>
</html>
