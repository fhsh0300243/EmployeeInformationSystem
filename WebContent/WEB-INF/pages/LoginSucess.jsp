<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Success</title>
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
	<div class="container-fluid">
		<div class="row">

			<!--左邊欄位-->
			<div class="col-sm-4">
				<div class="well">
					Hi ${usersResultMap.UserName} 您好~
					<a href="<c:url value="/logout/toLoginPage"/>">LOGOUT</a>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading">
						主選單
					</div>
					<div class="panel-body">
						請假<br>
						出勤查詢<br>
						績效系統<br>
						教育訓練<br>
						差旅費申請<br>
						<a href="<c:url value="/EmployeePage.do"/>">員工管理</a><br>
						
					</div>
					<div class="panel-footer">					
					</div>
				</div>




			</div>

			<!--右邊欄位-->
			<div class="col-sm-8">
				<div class="well">搜尋頁面</div>
				<div class="input-group">
					<input placeholder="輸入 ID" class="form-control input-lg"
						type="text"> <span class="input-group-btn">
						<button class="btn btn-primary btn-lg">查詢</button>
					</span>
				</div>
				<br />
				<div class="panel panel-primary">
					<div class="panel-heading">Raspberry Pi 加入成功 裝置 1</div>
					<div class="panel-body">這裡是數據內容</div>
				</div>
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
	<a href="<c:url value="/logout/toLoginPage"/>">LOGOUT</a>
	<br />
</body>
</html>