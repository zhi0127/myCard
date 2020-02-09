<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>fullcalendar日历</title>

	<link rel="shortcut icon" href=<%=path+"/image/favicon.ico"%>>
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/jquery-ui.min.css" %>>
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/jquery-ui.structure.min.css" %>>
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/jquery-ui.theme.min.css" %>>
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/core/main.css" %>>
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/daygrid/main.css" %>>
	<link rel="stylesheet" type="text/css" href=<%=path+"/css/demostyle.css" %>>
	<script type="text/javascript" src=<%=path + "/js/jquery.js" %>></script>
	<script type="text/javascript" src=<%=path + "/js/jquery-ui.min.js" %>></script>
	<script type="text/javascript" src=<%=path + "/js/core/main.js" %>></script>
	<script type="text/javascript" src=<%=path + "/js/daygrid/main.js" %>></script>
	<script type="text/javascript" src=<%=path + "/js/interaction/main.js" %>></script>
	<script type="text/javascript" src=<%=path + "/js/demo.js" %>></script>
</head>
<body>
<div id='calendar'></div>
<div id="caidan" title="请输入排班日程" hidden="hidden">
	<form class="form-inline">
		<p>
			<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：</label>
			<input type="text" class="sear datepicker" id="starttime" disabled="disabled">
		</p>
		<p>
			<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人员：</label>
			<select name="groups" id="groups">
				<c:forEach items="${requestScope.selectType.doctorsList}" begin="0" step="1" var="i">
					<option value="${i.PERSONID}">${i.PERSONNAME}</option>
				</c:forEach>
			</select>
		</p>
	</form>
</div>

<div id="sameday" title="日程明细" hidden="hidden">
	<form class="form-inline">
		<p>
			<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人员：</label>
			<span id="groups2"> </span>
		</p>
		<p>
			<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：</label>
			<span id="groups3"> </span>
		<fieldset>
			<legend>上午时间</legend>
			<label for="checkbox-1">09:00-10:00</label>
			<input type="checkbox" name="checkbox-1" id="checkbox-1" checked="checked" disabled>
			<label for="checkbox-2">10:00-11:00</label>
			<input type="checkbox" name="checkbox-2" id="checkbox-2" checked="checked" disabled>
			<label for="checkbox-3">11:00-12:00</label>
			<input type="checkbox" name="checkbox-3" id="checkbox-3" checked="checked" disabled>
		</fieldset>
		<fieldset>
			<legend>下午时间</legend>
			<label for="checkbox-4">14:00-15:00</label>
			<input type="checkbox" name="checkbox-4" id="checkbox-4" checked="checked" disabled>
			<label for="checkbox-5">15:00-16:00</label>
			<input type="checkbox" name="checkbox-5" id="checkbox-5" checked="checked" disabled>
			<label for="checkbox-6">16:00-17:00</label>
			<input type="checkbox" name="checkbox-6" id="checkbox-6" checked="checked" disabled>
		</fieldset>
		</p>
	</form>
</div>
</body>
</html>