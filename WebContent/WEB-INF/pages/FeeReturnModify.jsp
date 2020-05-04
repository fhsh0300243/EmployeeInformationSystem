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
					<p class="functionTitle">差旅費退件</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<form class="for1"
							action="<c:url value="/FeeReturnEditPage?feeAppID=${S_feeAppID}"/>"
							method="post">

							<table id="idtable1">

								<c:forEach var='appfeeID' items='${appfeeIDList}' varStatus='vs'>
									<tr>
										<td>申請編號:</td>
										<td>${appfeeID.feeAppID}</td>

									</tr>
									<tr>
										<td>員工姓名:</td>
										<td>${appfeeID.employeeID.name}</td>
									</tr>
									<tr>
										<td>員工部門:</td>
										<td>${appfeeID.department}</td>
									</tr>
									<tr>
										<td>申請項目:</td>
										<td><select name="appItem">
												<option value="${appfeeID.appItem}">${appfeeID.appItem}</option>
												<option value="交通">交通</option>
												<option value="住宿">住宿</option>
												<option value="餐費">餐費</option>
												<option value="加班費">加班費</option>
												<option value="其他">其他</option>

										</select></td>
									</tr>
									<tr>
										<td>申請時間:</td>
										<td>${appfeeID.appTime.substring(0,16)}</td>
									</tr>
									<tr>
										<td>發票日期:</td>
										<td><input type="date" name="invoiceTime"
											value="${appfeeID.invoiceTime}"></td>
									</tr>
									<tr>
										<td>發票號碼:</td>
										<td><input type="tel" name="invoiceNb"
											value="${appfeeID.invoiceNb}"></td>
									</tr>
									<tr>
										<td>統編:</td>
										<td><input type="tel" name="editor"
											value="${appfeeID.editor}"></td>
									</tr>
									<tr>
										<td>申請金額:</td>
										<td><input type="tel" name="appMoney"
											value="${appfeeID.appMoney}"></td>
									</tr>
									<tr>
										<td>備註:</td>
										<td><textarea cols="30" rows="3" id="comment1"
												name="remark"></textarea></td>
									</tr>


								</c:forEach>
							</table>
							<hr />

							<div>
								<input type="submit" name="bot" value="送出" class="btn btn-info"> 
								<input type="submit" name="bot" value="刪除此筆申請" class="btn btn-info">
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