<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.eis.model.Attendance, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>番茄科技 打卡系統</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<link rel="stylesheet" type="text/css" href="css/SearchPage.css">
<link rel="icon" href="images/favicon.ico">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<style>
.ui-datepicker-calendar {
	display: none;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}

.well, .panel {
	text-align: center;
}

.f1 {
	position: relative;
	width: 70%;
	left: 30%;
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
					<p class="functionTitle">員工出勤明細</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<div class="tb">
							<table id="idtable1">
								<tr>
									<th>日期</th>
									<th>上班時間</th>
									<th>下班時間</th>
									<th>狀態</th>
									<th>假別</th>
								</tr>
								<c:forEach var='att' items='${attlist}' varStatus='vs'>
									<tr class='classtr1'>
										<td>${att.getDate()}</td>
										<td>${att.getStartTime()}</td>
										<td>${att.getEndTime()}</td>
										<td>${att.getStatus()}</td>
										<td>${att.getLeaveType()}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
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
</body>
</html>