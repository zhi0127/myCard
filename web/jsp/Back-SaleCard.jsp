<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/10/13
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String cssPath = request.getContextPath() + "/css/";
	String jsPath = request.getContextPath() + "/js/";
	String path = request.getContextPath();
%>
<html>
<head>
	<meta charset="utf-8">
	<title>售卡</title>
	<script src=<%=jsPath + "jquery-3.4.1.js"%>></script>
	<script src=<%=jsPath + "bootstrap.min.js"%>></script>
	<script src=<%=jsPath + "city.js"%>></script>
	<script src=<%=jsPath + "Back-SaleCard.js"%>></script>
	<link rel="stylesheet" href=<%=cssPath+"bootstrap.css"%>>

	<style>
		.btn {
			width: 200px;
			margin-left: 41%;
		}

		.form-control {
			width: 50%;
			display: inline-block;
			clear: both;
		}

		.ag {
			width: 15%;
		}

		.name {
			padding-left: 20%;
		}

		.form-group {
			margin-top: 40px;
		}
	</style>
</head>

<body>
<input type="hidden" value="${requestScope.loginID}" id="loginID">
<input type="hidden" value="${requestScope.loginName}" id="loginName">
<input type="hidden" value="${requestScope.msg}" id="msg">
<input type="hidden" value="${requestScope.patientMoney}" id="patientMoney">
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">

			<div class="panel panel-primary" style="margin-top: 30px;">

				<div class="panel-heading">
					<h3 class="panel-title">
						售卡<input type="hidden" id="myID" name="myID" value="${sessionScope.staffID}">
					</h3>
				</div>
				<form class="form-horizontal" role="form" id="SaleCardForm"
				      action=<%=path+"/BackSaleCardServlet"%> method="post">
					<input type="hidden" value="${requestScope.loginID}" name="loginID">
					<input type="hidden" value="${requestScope.loginName}" name="loginName">
					<div class="panel-body">
						<div class="form-group">
							<div class="col-sm-6 name">
								<label class="control-label" for="inputName">姓名:</label>
								<input class="form-control" id="inputName" name="patientName" placeholder="请输入十位中文或英文姓名"
								       type="text" style="width: 82%;" required="" maxlength="10"
								       onkeyup="value=value.replace(/[^\a-zA-Z\u4E00-\u9FA5]/g,'')"
								       onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\a-zA-Z\u4E00-\u9FA5]/g,''))"/>
							</div>
							<div class="col-sm-6">
								<label class="control-label" for="age">年龄:</label>
								<input class="form-control ag" id="age" maxlength="3" type="text" required=""
								       onblur="checkAge()" name="patientAge"
								       onkeyup="value=value.replace(/[^0-9]/g,'')"/>&nbsp;&nbsp;&nbsp;岁&nbsp;&nbsp;
								<input class="form-control ag" id="age1" maxlength="2" type="text" onblur="checkWeek()"
								       name="patientWeek" onkeyup="value=value.replace(/[^0-9]/g,'')"/> &nbsp;&nbsp;周
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-6 name">
								<div class="radio">
									<strong>性别:</strong>
									<label style="margin-left: 5%;">
										<input type="radio" name="patientSexy" id="optionsRadios1" value="男"
										       checked="checked"> 男
									</label>

									<label style="margin-left: 5%;">
										<input type="radio" name="patientSexy" id="optionsRadios2" value="女">女
									</label>
								</div>
							</div>
							<div class="col-sm-6"><strong>籍贯:</strong>
								<div class="btn-group" style="margin-left:15px;">
									<div>
										<select id="s_province" name="s_province"></select>
										<select id="s_city" name="s_city"></select>
										<script type="text/javascript">_init_area();</script>
									</div>

								</div>

							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-7">
								<label class="control-label" for="idNum" style="margin-left: 16%">证件号码:</label>
								<input class="form-control" maxlength="18" id="idNum" type="text" style="width: 260px;"
								       required="" onblur="check_id(this)" name="patientIdentity"/>
							</div>
							<div class="col-sm-5">
								<label class="control-label" for="cellphone">联系电话:</label>
								<input class="form-control" maxlength="11" id="cellphone" type="text" required=""
								       onblur="checkPhone()" name="patientPhone"
								       onkeyup="value=value.replace(/[^0-9]/g,'')"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<label class="control-label" for="inputAddress" style="margin-left: 20%;">现住址:</label>

								<input class="form-control" id="inputAddress" type="text" style="width: 57%;"
								       placeholder="请输入三十位以内地址" maxlength="30" name="patientAdress"/>
							</div>

						</div>
						<div class="form-group">
							<div class="col-sm-6 name">
								<label class="control-label" for="money">预存金额:</label>
								<input class="form-control" id="money" type="text" required="" maxlength="4"
								       placeholder="请输入大于5元" name="patientMoney"
								       onkeyup="value=value.replace(/[^0-9]/g,'')"/>
							</div>
							<div class="col-sm-6">
								<label class="control-label" for="number">卡号:</label>
								<c:choose>
									<c:when test="${card == null}">
										<input class="form-control" id="number" type="text" required="" maxlength="12"
										       readonly="readonly" value="您申领的卡已经售光"/>
									</c:when>
									<c:otherwise>
										<input class="form-control" id="number" type="text" required="" maxlength="12"
										       readonly="readonly" value="${card.CARDHEAD}${card.CARDNUM}"/>
										<input type="hidden" name="cardID" id="cardID" value="${card.CARDID}">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<button class="btn btn-primary btn-default active" type="button" onclick="SaleCardMethod()">出售
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
