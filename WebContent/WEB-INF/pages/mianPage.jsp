<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>番茄科技 員工資訊系統</title>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>
<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<style>
.well, .panel {
	text-align: center;
}
</style>
</head>
<body>
	<div class="CanNotLeftDiv">
		<img class="CanNotLeft" src="images/CompanyLogo.jpg">
		<p>上班記得打卡</p>
		<p>番茄科技關心您</p>
	</div>
			<div class="col-md-5">
				<div class="well">Hi ${usersResultMap.UserName} 您好~</div>
				<div class="panel panel-primary">
					<div class="panel-heading">主選單</div>
					<div class="panel-body">

						<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
							src="images/left1.png" border="0"></a> <a
							href="<c:url value="/xxx.do"/>"><img class="imgButton"
							src="images/left2.png" border="0"></a> <a
							href="<c:url value="/xxx.do"/>"><img class="imgButton"
							src="images/left3.png" border="0"></a> <a
							href="<c:url value="/xxx.do"/>"><img class="imgButton"
							src="images/left4.png" border="0"></a> <a
							href="<c:url value="/xxx.do"/>"><img class="imgButton"
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
				<img class="CanNotRight" src="images/CompanyLogo.jpg">
				<p>請假記得申請</p>
				<p>番茄科技關心您</p>
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