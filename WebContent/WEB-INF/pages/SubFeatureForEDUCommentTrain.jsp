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
				<a href="<c:url value="/EduSchedule.do"/>">目前培訓課程</a>
			</div>
			<div>
				<a href="<c:url value="/insertCoursePage.do"/>">新增課程</a>
			</div>
			<div>
				<a href="<c:url value="/XXX.do"/>">修改課程</a>
			</div>
			<div>
				<a href="<c:url value="/queryCouseRecords.do"/>">查詢課程</a>
			</div>
			<div>
				<a href="<c:url value="/deleteCourse.do"/>">刪除課程</a>
			</div>
			<div>
				<a href="<c:url value="/EduEmpComment.do"/>">課程滿意度評價</a>
			</div>
		</div>
		<div class="panel-footer"></div>
	</div>
</body>
</html>