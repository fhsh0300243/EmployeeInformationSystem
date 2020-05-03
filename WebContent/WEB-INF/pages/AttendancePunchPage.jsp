<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.eis.model.Attendance, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>番茄科技 出勤系統</title>
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

<style>
.well, .panel, .panel-body {
	text-align: center;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}

#clock {
	color: black;
	font: 3em sans-serif;
	font-family: Microsoft JhengHei;
	background: #E0E0E0;
	margin: 5px;
	padding: 5px;
	border-radius: 10px;
	width: 480px;
	text-align: center;
	position: relative;
	left: 33.3%;
}

b {
	font-size: 20px;
}
</style>
</head>
<body onload="startTime()">
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
					<p class="functionTitle">打卡</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<div style="align: center">
							<div id="clock"></div>
							<br>
							<form action="<c:url value="/PunchAction" />" method="post">
								<input type="submit" class="btn btn-primary btn-lg" value="打卡" />
							</form>
							<p />
							<br>
							<table id="idtable1">
								<tr>
									<th>日期</th>
									<th>上班時間</th>
									<th>下班時間</th>
									<th>狀態</th>
									<th>假別</th>
								</tr>
								<c:forEach var='mypunch' items='${myPunch}' varStatus='vs'>
									<tr class='classtr1'>
										<td>${mypunch.getDate()}</td>
										<td>${mypunch.getStartTime()}</td>
										<td>${mypunch.getEndTime()}</td>
										<td>${mypunch.getStatus()}</td>
										<td>${mypunch.getLeaveType()}</td>
									</tr>
								</c:forEach>
							</table>

							<div class="list_footer">
								<div id="tag"></div>
								<div id="page"></div>
							</div>
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
<script type="text/javascript">
	function startTime() {
		var today = new Date();
		var yyyy = today.getFullYear();
		var MM = (today.getMonth()) + 1;
		var dd = today.getDate();
		var weekday = new Array(7)
		weekday[0] = "星期日"
		weekday[1] = "星期一"
		weekday[2] = "星期二"
		weekday[3] = "星期三"
		weekday[4] = "星期四"
		weekday[5] = "星期五"
		weekday[6] = "星期六"
		var week = weekday[today.getDay()]

		var hh = today.getHours();
		var mm = today.getMinutes();
		var ss = today.getSeconds();
		mm = checkTime(mm);
		ss = checkTime(ss);
		document.getElementById('clock').innerHTML = yyyy + "年" + MM + "月" + dd
				+ "日" + " " + week + " " + hh + ":" + mm + ":" + ss;
		var timeoutId = setTimeout(startTime, 500);
	}

	function checkTime(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	}
</script>