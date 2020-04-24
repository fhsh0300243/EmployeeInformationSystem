<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>子功能</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<style>
.well, .panel {
	text-align: center;
}

</style>
</head>
<body>
	<div class="panel panel-primary">
		<div class="panel-heading">相關功能</div>
		<div class="panel-body">
			<div>
				<a href="<c:url value="/AddEmployee.do"/>">新增員工</a>
			</div>
			<div>
				<a href="<c:url value="/QueryEmployee.do"/>">查詢/編輯員工</a>
			</div>
			<div>
				<a href="<c:url value="/QueryEmpAttendance.do"/>">員工出勤查詢</a>
			</div>
			<div>
				<a href="<c:url value="/InqueryCalendar"/>">設定行事曆</a>
			</div>
		</div>
		<div class="panel-footer"></div>
	</div>
</body>
</html>