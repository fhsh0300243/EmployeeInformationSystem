<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.eis.model.Attendance, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出勤系統</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

<style>
.well, .panel, .panel-body {
	text-align: center;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
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
					<p>Hi, ${usersResultMap.UserName} 您好~
					<p>歡迎登入番茄科技員工資訊系統
				</div>

				<%@ include file="SubFeatureForAttendance.jsp"%>

			</div>




			<!--右邊欄位-->

			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">出勤系統</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">

						<h2>
							日期：<span id="nowdate"></span>
						</h2>
						<h2>
							時間：<span id="nowtime"></span>
						</h2>
						<form action="<c:url value="/PunchAction" />" method="post">
							<input type="submit" value="打卡" />
						</form>
						<p/>
						<table width="800" border="1" align="center">
							<tr>
								<td><b>日期</b></td>
								<td><b>上班時間</b></td>
								<td><b>下班時間</b></td>
								<td><b>狀態</b></td>
								<td><b>假別</b></td>
							</tr>
							<%
								List<Attendance> myPunch = (List<Attendance>) request.getAttribute("myPunch");
								if (myPunch == null || myPunch.size() < 1) {
							%>
							<tr id="test">
								<td align="center" colspan="5">沒有資料!</td>
							</tr>
							<%
								} else {
									for (Attendance mypunch : myPunch) {
							%>

							<tr align="center">
								<td><%=mypunch.getDate()%></td>
								<td><%=mypunch.getStartTime()%></td>
								<td><%=mypunch.getEndTime()%></td>
								<td><%=mypunch.getStatus()%></td>
								<td><%=mypunch.getLeaveType()%></td>
							</tr>
							<%
								}
								}
							%>
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
	<div class="CanNotRightDownDiv">
		<img class="CanNotRightDown" src="images/CompanyLogo.png">
	</div>



</body>
</html>
<script type="text/javascript">
	function showTime() {
		var today = new Date();
		var hour = parseInt(today.getHours()) < 10 ? '0' + today.getHours()
				: today.getHours();

		var min = parseInt(today.getMinutes()) < 10 ? '0' + today.getMinutes()
				: today.getMinutes();

		var sec = parseInt(today.getSeconds()) < 10 ? '0' + today.getSeconds()
				: today.getSeconds();

		document.getElementById('nowtime').innerHTML = hour + ':' + min + ':'
				+ sec
	}
	window.onload = function() {
		setInterval(showTime, 1000);
		var today = new Date();
		document.getElementById('nowdate').textContent = today.getFullYear()
				+ '/' + (today.getMonth() + 1) + '/' + today.getDate() + '/'
				+ '星期' + today.getDay()
	}
</script>