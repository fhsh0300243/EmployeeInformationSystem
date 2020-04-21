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
<link rel="stylesheet" type="text/css" href="../css/LoginSucess.css">
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
					<a href="<c:url value="/login/userLoginCheck"/>"><img class="img1" src="../images/home.png" border="0"></a>
					<a href="<c:url value="/logout/toLoginPage"/>"><img class="img1" class="logout" src="../images/logout.png" border="0"></a>
					Hi ${usersResultMap.UserName} 您好~
					
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading">
						主選單
					</div>
					<div class="panel-body">
					
						<a href="<c:url value="/xxx.do"/>"><img class="imgButton" src="../images/left1.png" border="0"></a>
						<a href="<c:url value="/xxx.do"/>"><img class="imgButton" src="../images/left2.png" border="0"></a>
						<a href="<c:url value="/xxx.do"/>"><img class="imgButton" src="../images/left3.png" border="0"></a>
						<a href="<c:url value="/xxx.do"/>"><img class="imgButton" src="../images/left4.png" border="0"></a>
						<a href="<c:url value="/xxx.do"/>"><img class="imgButton" src="../images/left5.png" border="0"></a>
						<a href="<c:url value="/EmployeePage.do"/>"><img class="imgButton" src="../images/left6.png" border="0"></a>
						
					</div>
					<div class="panel-footer">					
					</div>
				</div>




			</div>

			<!--右邊欄位-->
			<div class="col-sm-8">
				<figure class="well1">
					
				</figure>


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