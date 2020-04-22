<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>請假詳細資訊</title>
<link rel="stylesheet" type="text/css" href="css/DetailPage.css">
<link rel="stylesheet" type="text/css" href="css/Menu.css">
</head>
<body>
	<aside class="menu">
		<table id="employee">
			<tr>
				<td class="tdTitle">個人專區</td>
			</tr>
			<tr class="trbtn">
				<td id="applyPage"><a href="preapplypage">請假申請</a></td>
			</tr>
			<tr class="trbtn">
				<td id="applyRecord"><a href="preapplyrecord">請假紀錄</a></td>
			</tr>
			<tr class="trbtn">
				<td id="leaveType"><a href="preleavetype">剩餘假別</a></td>
			</tr>
		</table>
		<table id="manager">
			<tr>
				<td class="tdTitle">主管專區</td>
			</tr>
			<tr class="trbtn">
				<td id="unsignedPage"><a href="preunsignedpage">人員請假-未簽核</a></td>
			</tr>
			<tr class="trbtn">
				<td id="signedPage"><a href="presignedpage">人員請假-已簽核</a></td>
			</tr>
		</table>
	</aside>

	<article class="content">
		<h2>請假詳細資訊</h2>
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
	</article>
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
							var str = "<img class='result' title='再次點擊圖片，即可縮回。' src='<c:url value='/preAttImage?applyId=${ApplyList.applyId}'/>'>";
							$("#dialog_pic").html(str);
						});

		$("#dialog_pic").on("click", ".result", function() {
			$(this).remove();
			$("#attImg").show();
		});
	</script>

</body>
</html>