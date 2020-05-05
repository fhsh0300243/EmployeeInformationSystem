<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>番茄科技 差旅費系統</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<link rel="icon" href="images/favicon.ico">
<style>
.well, .panel {
	text-align: center;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}
#idtable1 {
	margin: 30px auto;
	border-collapse: collapse;
}

#idtable1 tr {
	text-align: center;
}

#idtable1 th {
	background-color: #E0E0E0;
	padding: 10px 20px;
}

#idtable1 td {
	border-bottom: 1px solid #ddd;
	padding: 10px 20px;
}
b{
	font-size:20px;
}
.pInLeft{
	margin:0;
}
</style>
<script>
	function check(obj) {
		if (obj.id == 'checkbox1' && obj.checked == true) {
			document.getElementById('checkbox2').checked = false;
		} else if (obj.id == 'checkbox2' && obj.checked == true) {
			document.getElementById('checkbox1').checked = false;
		}
	}
</script>
</head>
<body>
	<br>
	<div class="container-fluid">
		<div class="row">

			<!--左邊欄位-->
			<div class="col-sm-4">
				<div class="well">
					<p class="pInLeft">
						<b>Hi~</b> ${usersResultMap.Title},
					<p class="pInLeft">${ LoginOK.employee.name} (${usersResultMap.UserName})您好~
					<p class="pInLeft">歡迎登入番茄科技員工資訊系統
				</div>

				<%@ include file="SubFeatureForFee.jsp"%>

			</div>

			<!--右邊欄位-->

			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">差旅費簽核</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						

														<table id="idtable1">
																<tr >
									<th>申請編號</th>
									<th>部門</th>
									<th>員工姓名</th>
									<th>申請項目</th>
									<th>申請日期</th>
									<th>申請金額</th>
									
									
									<th>簽核送出</th>
								</tr>

								<c:forEach var='appfee' items='${dSList}' varStatus='vs'>
									<tr>
										<td>${appfee.feeAppID}</td>
										<td>${appfee.department}</td>
										<td>${appfee.employeeID.name}</td>
										<td>${appfee.appItem}</td>
										<td>${appfee.appTime.substring(0,16)}</td>
										<td>${appfee.appMoney}</td>
										
										
										
										
										<td>
											<button class="btn-info" name="${appfee.feeAppID}">簽核</button>
										</td>
									</tr>
								</c:forEach>
							</table>


							
						
						<div class="list_footer">
							<div id="tag"></div>
							<div id="page"></div>
						</div>
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
	})
	$(".btn-info").click(function() {
		var feeAppID = $(this).attr("name");
		location.href = "SingerPage?feeAppID=" + feeAppID;
	})
	</script>

</body>
</html>