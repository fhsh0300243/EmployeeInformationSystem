<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>子功能</title>
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
	<div class="panel panel-primary">
		<div class="panel-heading">子功能</div>
		<div class="panel-body">
			<div>
				<input type="button" value="個人出勤查詢"
					onclick="location.href='/EmployeeInformationSystem/InquiryPage'">
			</div>
			<div>
				<input type="button" value="部門出勤查詢"
					onclick="location.href='/EmployeeInformationSystem/InquiryDepartmentPage'">
			</div>
			<div>
				<input type="button" value="設定行事曆"
					onclick="location.href='/EmployeeInformationSystem/InqueryCalendar'">
			</div>
		</div>
		<div class="panel-footer"></div>
	</div>
</body>
</html>