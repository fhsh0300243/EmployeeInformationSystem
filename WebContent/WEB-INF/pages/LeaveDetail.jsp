<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>番茄科技 請假系統</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<link rel="stylesheet" type="text/css" href="css/DetailPage.css">
<link rel="stylesheet" type="text/css" href="css/Menu.css">
<link rel="icon" href="images/favicon.ico">
<style>
.col-sm-4, .functionTitle {
	text-align: center;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}
</style>
</head>
<body>

	<br>
	<div class="container-fluid">
		<div class="row">

			<!--左邊欄位-->
			<div class="col-sm-4">
				<div class="well">
					<p>Hi, ${usersResultMap.UserName} 您好~
					<p>歡迎登入番茄科技員工資訊系統
				</div>

				<%@ include file="LeaveMain.jsp"%>

			</div>

			<!--右邊欄位-->
			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">請假詳細資訊</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>

					<table id="idtable2">
						<tr>
							<td class="tdtag">申請時間：</td>
							<td>${ApplyList.createTime}</td>
						</tr>
						<tr>
							<td class="tdtag">部門：</td>
							<td>${ApplyList.employeeId.empDept.deptName}</td>
						</tr>
						<tr>
							<td class="tdtag">職位：</td>
							<td>${ApplyList.employeeId.empTitle.titleChName}</td>
						</tr>
						<tr>
							<td class="tdtag">申請人：</td>
							<td>${ApplyList.employeeId.name}</td>
						</tr>
						<tr>
							<td class="tdtag">請假類別：</td>
							<td>${ApplyList.leaveType}</td>
						</tr>
						<tr>
							<td class="tdtag">開始時間：</td>
							<td>${ApplyList.startTime}</td>
						</tr>
						<tr>
							<td class="tdtag">結束時間：</td>
							<td>${ApplyList.endTime}</td>
						</tr>
						<tr>
							<td class="tdtag">總計時數：</td>
							<td>${ApplyList.sumHours}</td>
						</tr>
						<tr>
							<td class="tdtag">事由：</td>
							<td>${ApplyList.cause}</td>
						</tr>
						<tr>
							<td class="tdtag">附件：</td>
							<td id=noAtt><img id="attImg" title="點擊圖片，即可於下方放大檢視。"
								src="<c:url value="/preAttImage?applyId=${ApplyList.applyId}"/>"></td>
						</tr>
					</table>

					<div id="dialog_pic"></div>

					<hr />

					<table id="idtable1">
						<tr>
							<th>部門</th>
							<th>職位</th>
							<th>簽核人</th>
							<th>簽核狀態</th>
							<th>簽核時間</th>
							<th>意見</th>
						</tr>
						<tr class='classtr1'>
							<td>${ApplyList.signerId.empDept.deptName}</td>
							<td>${ApplyList.signerId.empTitle.titleChName}</td>
							<td>${ApplyList.signerId.name}</td>
							<td>${ApplyList.signingProgress}</td>
							<td>${ApplyList.confirmTime}</td>
							<td>${ApplyList.comment}</td>
						</tr>
					</table>

					<div class="list_footer">
						<div id="tag"></div>
						<div id="page"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="CanNotRightDownDiv">
		<img class="CanNotRightDown" src="images/CompanyLogo.png">
	</div>

	<script src="js/jquery-3.4.1.min.js"></script>
	<script>
		$(function() {
			$("td:empty").text("-");
		});

		$("#attImg").on("error", function() {
			$("#noAtt").text("-");
		});

		$("#attImg")
				.click(
						function() {
							$(this).hide();
							var str = "<img class='result' width=100% title='再次點擊圖片，即可縮回。' src='<c:url value='/preAttImage?applyId=${ApplyList.applyId}'/>'>";
							$("#dialog_pic").html(str);
						});

		$("#dialog_pic").on("click", ".result", function() {
			$(this).remove();
			$("#attImg").show();
		});
	</script>

</body>
</html>