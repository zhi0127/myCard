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
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>欢迎使用一卡通自助终端</title>
	<link rel="stylesheet" href=<%=cssPath+"Front-login.css"%>>
	<script src=<%=jsPath + "jquery-3.4.1.js"%>></script>
	<script src=<%=jsPath + "Front-login.js"%>></script>
</head>
<body>
<div id="div1">
	<h2>欢迎使用一卡通自助终端</h2>
	<input type="button" value="卡充值" onclick="CardRecharge()"><br>
	<input type="button" value="卡对账" onclick="CardReconciliation()"><br>
	<input type="button" value="预约挂号" onclick="registered()"><br>
	<input type="button" value="预约取号" onclick="take()"><br>
	<input type="button" value="处方查询" onclick="query()">


</div>

</body>
</html>
