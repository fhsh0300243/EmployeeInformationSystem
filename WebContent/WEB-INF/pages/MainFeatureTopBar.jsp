<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TopBar</title>
<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<style>
.imgButton{
	width:50px;
}
.home{
	width:43px;
}
.logout{
	width: 48px;
}


</style>
</head>
<body>


	<div >
		<a href="<c:url value="/mainPage"/>"><img class="imgButton home"
			src="images/home.png" border="0"></a> 
		<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
			src="images/left1.png" border="0"></a> 
		<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
			src="images/left2.png" border="0"></a> 
		<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
			src="images/left3.png" border="0"></a> 
		<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
			src="images/left4.png" border="0"></a> 
		<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
			src="images/left5.png" border="0"></a> 
		<a href="<c:url value="/EmployeePage.do"/>"><img
			class="imgButton" src="images/left6.png" border="0"></a> 
		<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
			src="images/left7.png" border="0"></a> 
		<a href="<c:url value="/toLoginPage"/>"><img
			class="imgButton logout" src="images/logout.png" border="0"></a>
	</div>
</body>
</html>