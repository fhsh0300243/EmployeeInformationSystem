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
		<div class="panel-heading">相關功能</div>
		<div class="panel-body">
			<div class="title">Demo</div>
			<div>
				<a href="<c:url value="/CreateTable0300"/>">建立出勤表</a>
			</div>
			<div>
				<a href="<c:url value="/CheckStatus0830"/>">8:30判斷</a>
			</div>
			<div>
				<a href="<c:url value="/CheckStatus2330"/>">23:30判斷</a>
			</div>
			<br>
			<div class="title">個人專區</div>
			<div>
				<a href="<c:url value="/InquiryToday"/>">打卡</a>
			</div>
			<div>
				<a href="<c:url value="/gotoAttendanceOwnPage"/>">個人出勤查詢</a>
			</div>
			<div>
				<a href="<c:url value="/InqueryCalendar"/>">設定行事曆</a>
			</div>
			<div id="manager" name="${empLevel}" style="display: none;">
				<br>
				<div class="title">主管專區</div>
				<div>
					<a href="<c:url value="/gotoAttendanceDepartmentPage"/>">部門出勤查詢</a>
				</div>
			</div>
		</div>
		<div class="panel-footer"></div>
	</div>
</body>
</html>
<script>
	$(function() {
		var level = $("#manager").attr("name");
		if (level != 1) {
			$("#manager").show()
		}
	});
</script>