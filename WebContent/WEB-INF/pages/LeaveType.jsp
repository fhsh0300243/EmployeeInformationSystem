<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>番茄科技 請假系統</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<link rel="stylesheet" type="text/css" href="css/SearchPage.css">
<link rel="icon" href="images/favicon.ico">
<style>
.col-sm-4, .functionTitle {
	text-align: center;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
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

				<%@ include file="LeaveMain.jsp"%>

			</div>

			<!--右邊欄位-->
			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">剩餘假別</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>

					<table id="idtable1">
						<tr>
							<th>請假類別</th>
							<th>可用時數</th>
							<th>已用時數</th>
							<th>申請時數</th>
							<th>剩餘時數</th>
							<th>有效期限</th>
							<th>給假原則</th>
						</tr>
						<c:forEach var='leaveDetail' items='${LeaveList}' varStatus='vs'>
							<tr class='classtr1'>
								<td>${leaveDetail.leaveType}</td>
								<td>${leaveDetail.maxHours}</td>
								<td>${leaveDetail.usedHours}</td>
								<td>${leaveDetail.applyHours}</td>
								<td>${leaveDetail.surplusHours}</td>
								<td>${leaveDetail.startDate}~${leaveDetail.endDate}</td>
								<td>${leaveDetail.remarks}</td>
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

	<div class="CanNotRightDownDiv">
		<img class="CanNotRightDown" src="images/CompanyLogo.png">
	</div>

	<script src="js/jquery-3.4.1.min.js"></script>
	<script>
		
	</script>

</body>
</html>