<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>請假主頁面</title>
<link rel="stylesheet" type="text/css" href="css/LeaveMain.css">
<style>
.title{
	font-weight:bold;
}
</style>
</head>
<body>
	<div class="panel panel-primary">
		<div class="panel-heading">相關功能</div>
		<div class="panel-body">


			<div class="title">個人專區</div>

			<div>
				<a href="preapplypage">請假申請</a>
			</div>
			<div>
				<a href="preapplyrecord">請假紀錄</a>
			</div>
			<div>
				<a href="preleavetype">剩餘假別</a>
			</div>
			<br>


			<div class="title">主管專區</div>

			<div>
				<a href="preunsignedpage">人員請假-未簽核</a>
			</div>
			<div>
				<a href="presignedpage">人員請假-已簽核</a>
			</div>
		</div>
		<div class="panel-footer"></div>
	</div>

</body>
</html>