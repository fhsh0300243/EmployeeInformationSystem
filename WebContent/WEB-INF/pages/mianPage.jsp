<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>番茄科技 員工資訊系統</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/mainCSS.css">

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="icon" href="images/favicon.ico">
<style>
.well, .panel {
	text-align: center;
}
</style>
</head>
<body>

	<div class="CanNotLeftDiv">
		<img class="CanNotLeft" src="images/CompanyLogo.png">
	</div>
	<br>
	<div class="col-md-5">
		<div class="well">Hi, ${usersResultMap.UserName}
			您好~歡迎登入番茄科技員工資訊系統</div>
		<div class="panel panel-primary">
			<div class="panel-heading">主選單</div>
			<div class="panel-body">

				<a href="<c:url value="/preapplypage"/>"><img class="imgButton"
					src="images/left1.png" border="0"></a> <a
					href="<c:url value="/InquiryToday"/>"><img class="imgButton"
					src="images/left2.png" border="0"></a> <a
					href="<c:url value="/performance"/>"><img class="imgButton"
					src="images/left3.png" border="0"></a> <a
					href="<c:url value="/EducationPage.do"/>"><img
					class="imgButton" src="images/left4.png" border="0"></a> <a
					href="<c:url value="/FeeAllPage.action"/>"><img
					class="imgButton" src="images/left5.png" border="0"></a> <a
					href="<c:url value="/EmployeePage.do"/>"><img class="imgButton"
					src="images/left6.png" border="0"></a> <a
					href="<c:url value="/BullenBoardPage"/>"><img
					class="imgButton bbs" src="images/left7.png" border="0"></a> <a
					href="<c:url value="/toLoginPage"/>"><img
					class="imgButton logout" src="images/logout.png" border="0"></a>
			</div>
			<div class="panel-footer"></div>

		</div>
	</div>
	<div class="CanNotRightDiv">
		<img class="CanNotRight" src="images/CompanyLogo.png">

	</div>

	<!-- 浮動視窗 by GK -->


	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header"
					style="background: #f6b33d -moz-linear-gradient(center top, #f6b33d 5%, #d29105 100%) repeat scroll 0 0;">
					<a class="close" data-dismiss="modal">×</a>
					<h3>登入提醒</h3>
				</div>
				<div class="modal-body">
					<p id="maintext"></p>
					<p id="querysucess"></p>
					<p id="modaltext"></p>
					<p id="feetext"></p>
					<p id="bullboardtext"></p>
					<p id="applyforLeavetext"></p>
				</div>
			</div>
		</div>
	</div>




	<!-- End -->





	<script src="https://code.jquery.com/jquery-2.2.4.min.js"
		integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src='<c:url value="/js/littleRemind.js"></c:url>'></script>

</body>
</html>