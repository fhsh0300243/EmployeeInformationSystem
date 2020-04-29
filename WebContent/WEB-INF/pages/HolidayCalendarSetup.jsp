<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.eis.model.HolidayCalendar, java.util.*"%>
<html>
<head>
<title>番茄科技 打卡系統</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>
<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<link rel="stylesheet" type="text/css" href="css/SearchPage.css">
<link rel="icon" href="images/favicon.ico">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}

.well, .panel {
	text-align: center;
}

.tb {
	position: relative;
	width: 70%;
	left: 28.5%;
}

b {
	font-size: 20px;
}
</style>
</head>
<body>

	<br>
	<div class="container-fluid">
		<div class="row">

			<!--左邊欄位-->
			<div class="col-sm-4">
				<div class="well">
					<p>
						<b>Hi~</b> ${usersResultMap.Title},
					<p>${usersResultMap.UserName}您好~
					<p>歡迎登入番茄科技員工資訊系統
				</div>

				<%@ include file="SubFeatureForAttendance.jsp"%>

			</div>

			<!--右邊欄位-->

			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">設定行事曆</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<form action="<c:url value='/HolidayAction'/>" method="post">
							設定行事曆 : <SELECT NAME="action">
								<OPTION VALUE="1">新增
								<OPTION VALUE="2">修改
							</SELECT> <input type="text" id="pickHoliday" name="date"
								autocomplete="off"> <SELECT NAME="dateType">
								<OPTION VALUE="國定假日">國定假日
								<OPTION VALUE="補班">補班
							</SELECT> <input type="text" name="remark" /> <input type="submit"
								value="送出" class="btn btn-info" />
						</form>
						<hr />
						<form class="tb" action="<c:url value='/DeleteCalendar'/>"
							method="post">
							<table id="idtable1">
								<tr>
									<th>日期</th>
									<th>種類</th>
									<th>備註</th>
									<th>新增人員ID</th>
									<th><input type="submit" value="移除" class="btn btn-info"></th>
								</tr>
								<c:forEach var='cal' items='${calenderlist}' varStatus='vs'>
									<tr class='classtr1'>
										<td>${cal.getDate()}</td>
										<td>${cal.getDateType()}</td>
										<td>${cal.getRemark()}</td>
										<td>${cal.getEmployee().getEmpID()}</td>
										<td><input type="checkbox" name="Date"
											value="${cal.getDate()}"></td>
									</tr>
								</c:forEach>
							</table>
						</form>

						<div class="list_footer">
							<div id="tag"></div>
							<div id="page"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="CanNotRightDownDiv">
		<img class="CanNotRightDown" src="images/CompanyLogo.png">
	</div>
	<script>
		$(document).ready(function() {
			$("#pickHoliday").datepicker();
		})
	</script>
</html>
</body>
