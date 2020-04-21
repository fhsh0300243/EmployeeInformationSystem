<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Success</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
.well, .panel {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">

			<!--左邊欄位-->
			<div class="col-sm-4">
				<div class="well">Hi ${usersResultMap.UserName} 您好~</div>
				<div class="panel panel-primary">
						<%@ include file="MainFeature.jsp"%>
				</div>
			</div>

			<!--右邊欄位-->
			<div class="col-sm-8">
				<div class="panel panel-primary">
					<div class="panel-heading">員工布告欄</div>
					<div class="panel-body">布告欄內容</div>
				</div>
			</div>

		</div>
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