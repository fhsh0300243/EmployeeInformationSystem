<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>差旅費申請主頁</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
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
					<p class="functionTitle">差旅費查詢</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<form action="FeeAllPage.action" method="post">
							<div class="st1">
								<label class="ca1 " for="">查詢區間:</label> <label><input
									type="date"   name="searchA"></label>

							
								<label class="ca1 " for="">-</label> <label><input
									type="date"    name="searchB"></label>
								
								
							</div>
							<table>
								<tr>
									<th>申請時間</th>
									<th>申請項目</th>
									<th>申請金額</th>
									<th>簽核狀態</th>
									<th>詳細資訊</th>

								</tr>

								<c:forEach var='applyDetail' items='${dList}' varStatus='vs'>
									<tr>
										<td>${applyDetail.appTime.substring(0,16)}</td>
										<td>${applyDetail.appItem}</td>
										<td>${applyDetail.appMoney}</td>
										<td>${applyDetail.signerStatus}</td>
										<td>
										<button class="classD" name="edit">編輯</button>
										</td>
									</tr>
								</c:forEach>
							</table>


							<div class="st2">
								<input type="submit" name="New" value="搜尋" /> <input
									type="reset" value="清除" />
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