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

				<%@ include file="SubFeatureForFee.jsp"%>

			</div>

			<!--右邊欄位-->

			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">差旅費簽核</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<form action="FeeSingerPage.action" method="post">
							
							<table style="border:1px solid black;">
								<tr style="border:1px solid black;">
									<th>部門</th>
									<th>員工姓名</th>
									<th>申請項目</th>
									<th>申請日期</th>
									<th>申請金額</th>
									<th>詳細內容</th>
									<th>簽核勾選</th>
									<th>簽核送出</th>
								</tr>

								<c:forEach var='applyDetail' items='${dSList}' varStatus='vs'>
									<tr>
										<td>${applyDetail.department}</td>
										<td>${applyDetail.employeeID}</td>
										<td>${applyDetail.appItem}</td>
										<td>${applyDetail.appTime.substring(0,16)}</td>
										<td>${applyDetail.appMoney}</td>
										<td>
										<button class="classD" name="FeeDetail">詳細內容</button>
										</td>
										<td>
											<input type="checkbox" name="Status" value="Pass">Pass
											<input type="checkbox" name="Status" value="Return">Return
										</td>																				
										<td>
										<button class="classD" name="send">送出</button>
										</td>
									</tr>
								</c:forEach>
							</table>


							<div class="st2">
								<input type="submit" name="New" value="搜尋" /> 
							</div>
						</form>
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