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
			<div class="title">個人專區</div>
			<div>
				<a href="<c:url value="/InquiryToday"/>">打卡</a>
			</div>
			<div>
				<a href="<c:url value="/gotoAttendanceOwnPage"/>">個人出勤查詢</a>
			</div>
			<br>
			<div id="manager" name="${LoginOK.getEmployee().getLevel()}"
				style="display: none;">
				<div class="title">主管專區</div>
				<div>
					<a href="<c:url value="/gotoAttendanceDepartmentPage"/>">部門出勤查詢</a>
				</div>
			</div>
			<br>
			<div class="title">Demo</div>
			<div>
				<a href="<c:url value="/CreateTable0300"/>">建立今日出勤表</a>
			</div>
			<div>
				<a href="<c:url value="/DeleteTodayAttendance"/>">清除今日出勤表</a>
			</div>
			<div>
				<a href="<c:url value="/CheckStatusALL"/>">判斷出勤異常</a>
			</div>
			<div>
				<a href="<c:url value="/UpdateOKAttemdance"/>">更新為正常上下班</a>
			</div>
			<div>
				<a href="<c:url value="/UpdateStartNGAttemdance"/>">更新為遲到</a>
			</div>
			<div>
				<a href="<c:url value="/UpdateEndNGAttemdance"/>">更新為早退</a>
			</div>
		</div>
		<div class="panel-footer"></div>
	</div>
	<script>
		$(function() {
			var level = $("#manager").attr("name");
			if (level != 1) {
				$("#manager").show();
			}
		});
	</script>
</body>
</html>