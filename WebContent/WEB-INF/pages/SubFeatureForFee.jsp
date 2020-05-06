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
* {
	font-family: 微軟正黑體;
}
.well, .panel {
	text-align: center;
}
.mark2 {
	color: red;
}
</style>
</head>
<body>
	<div class="panel panel-primary">
		<div class="panel-heading">相關功能</div>
		<div class="panel-body">
			<div>
				<a href="<c:url value="/AddFeeApp.action"/>">差旅費申請</a>
			</div>
			
			<div>
				<a href="<c:url value="/FeeAllPage.action"/>">差旅費查詢</a>
			</div>
			<div>
				<a href="<c:url value="/FeeReturnPage.action"/>">差旅費退件</a><span class="mark2">${qReturnTota}</span>
			</div>
			<div>
				<a href="<c:url value="/FeeSingerPage.action"/>">差旅費簽核</a><span class="mark2">${SingerTotal}</span>
			</div>
		</div>
		<div class="panel-footer"></div>
	</div>
</body>
</html>