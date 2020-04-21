<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>番茄科技 員工資訊系統</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap" rel="stylesheet"> 
<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<style>
.well, .panel {
	text-align: center;
}
</style>
</head>
<body>
	<div class="CanNotLeftDiv">
		<img class="CanNotLeft" src="images/CompanyLogo.png">
	</div>
			<div class="col-md-5">
				<div class="well">Hi, ${usersResultMap.UserName} 您好~歡迎登入番茄科技員工資訊系統</div>
				<div class="panel panel-primary">
					<div class="panel-heading">主選單</div>
					<div class="panel-body">

						<a href="<c:url value="/preLoginLeave"/>"><img class="imgButton"
							src="images/left1.png" border="0"></a> <a
							href="<c:url value="/gotoMainAttendancePage"/>"><img class="imgButton"
							src="images/left2.png" border="0"></a> <a
							href="<c:url value="/xxx.do"/>"><img class="imgButton"
							src="images/left3.png" border="0"></a> <a
							href="<c:url value="/xxx.do"/>"><img class="imgButton"
							src="images/left4.png" border="0"></a> <a
							href="<c:url value="/FeeAllPage.action"/>"><img class="imgButton"
							src="images/left5.png" border="0"></a> <a
							href="<c:url value="/EmployeePage.do"/>"><img
							class="imgButton" src="images/left6.png" border="0"></a> <a
							href="<c:url value="/xxx.do"/>"><img class="imgButton bbs"
							src="images/left7.png" border="0"></a> <a
							href="<c:url value="/toLoginPage"/>"><img
							class="imgButton logout" src="images/logout.png" border="0"></a>
					</div>
					<div class="panel-footer"></div>

				</div>
			</div>
			<div class="CanNotRightDiv">
				<img class="CanNotRight" src="images/CompanyLogo.png">

			</div>

	<h1>登入成功</h1>
	<h3>你的個人資訊</h3>
	<label>員工編號: </label>${usersResultMap.EmployeeID}<br>
	<label>帳號: </label>${usersResultMap.UserName}<br>
	<label>密碼: </label>${usersResultMap.UserPassword}<br>
	<label>職稱: </label>${usersResultMap.Title}<br>
	<label>部門: </label>${usersResultMap.Department}<br>
	<a href="<c:url value="/EmployeePage.do"/>">員工管理</a>
	<br />
	<a href="FeeAllPage.action">差旅費申請主頁</a>
	<br />
	<a href="AddFeeApp.action">差旅費申請</a>
	<br />

	<a href="<c:url value="/toLoginPage"/>">LOGOUT</a>
	<br />
</body>
</html>