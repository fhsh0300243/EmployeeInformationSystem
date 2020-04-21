<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TopBar</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>
<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
.well, .panel {
	text-align: center;
}
.imgButton{
	width:50px;
}
</style>
</head>
<body>

	<div >
		<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
			src="images/left1.png" border="0"></a> <a
			href="<c:url value="/xxx.do"/>"><img class="imgButton"
			src="images/left2.png" border="0"></a> <a
			href="<c:url value="/xxx.do"/>"><img class="imgButton"
			src="images/left3.png" border="0"></a> <a
			href="<c:url value="/xxx.do"/>"><img class="imgButton"
			src="images/left4.png" border="0"></a> <a
			href="<c:url value="/xxx.do"/>"><img class="imgButton"
			src="images/left5.png" border="0"></a> <a
			href="<c:url value="/EmployeePage.do"/>"><img
			class="imgButton" src="images/left6.png" border="0"></a> <a
			href="<c:url value="/xxx.do"/>"><img class="imgButton bbs"
			src="images/left7.png" border="0"></a> <a
			href="<c:url value="/toLoginPage"/>"><img
			class="imgButton logout" src="images/logout.png" border="0"></a>
	</div>
</body>
</html>