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
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>
.well, .panel {
	text-align: center;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}

.tb {
	margin: 0 auto;
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
.mark1 {
	color: blue;
}
.mark2 {
	color: red;
}
.pInLeft{
	margin:0;
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
					<p class="functionTitle">差旅費查詢</p>
					
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
								
					<div class="panel-body">
				
						<form action="FeeAllPage.action" method="post">
						
							<div class="st1">
								<label class="ca1 " for="">查詢區間:</label> <label>
								<input	type="date" name="searchA" value="${appinputmsg.searchA}"></label> <label class="ca1 " for="">-</label>
								<label>
								<input type="date" name="searchB" value="${appinputmsg.searchB}"></label> 
								<input	type="submit" name="New" value="搜尋" class="btn btn-info"/><span class="mark2">${feemsgmap.searchday}</span>

							</div>
							
						<h4><span class="mark1">${TotalMoney}</span></h4>
						</form>
						
						<table id="idtable1">
								<tr>
									<th>申請編號</th>
									<th>申請時間</th>
									<th>申請項目</th>
									<th>發票日期</th>
									<th>發票號碼</th>
									<th>申請金額</th>
									<th>簽核主管</th>
									<th>簽核狀態</th>
									
								</tr>

								<c:forEach var='applyDetail' items='${dList}' varStatus='vs'>
									<tr>
										<td>${applyDetail.feeAppID}</td>
										<td>${applyDetail.appTime.substring(0,16)}</td>
										<td>${applyDetail.appItem}</td>
										<td>${applyDetail.invoiceTime}</td>
										<td>${applyDetail.invoiceNb}</td>
										
										<td>${applyDetail.appMoney}</td>
										<td>${applyDetail.employeeID.name}</td>
										<td>${applyDetail.signerStatus}</td>
										
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

</body>
</html>