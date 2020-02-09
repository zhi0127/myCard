<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/24
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String jspPath = request.getContextPath() + "/jsp/";
%>
<html>
<head>
	<title>错误</title>

</head>
<body>
<a href=<%=jspPath + "Back-login.jsp"%>>错误，返回登录页面</a>
</body>
</html>
