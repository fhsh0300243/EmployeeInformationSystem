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
								
								<c:forEach var='appDetail' items='${appIDList}' varStatus='vs'>
									<tr>
										<td>申請編號:</td>
										<td>${appDetail.feeAppID}</td>
									</tr>
									<tr>
										<td >員工姓名:</td>
										<td>${appDetail.employeeID.name}</td>
									</tr>
									<tr>
										<td>員工部門:</td>
										<td>${appDetail.department}</td>
									</tr>
									<tr>
										<td>申請項目:</td>
										<td>${appDetail.appItem}</td>
									</tr>
									<tr>
										<td>申請時間:</td>
										<td>${appDetail.appTime.substring(0,16)}</td>
									</tr>
									<tr>
										<td>發票日期:</td>
										<td>${appDetail.invoiceTime}</td>
									</tr>
									<tr>
										<td>發票號碼:</td>
										<td>${appDetail.invoiceNb}</td>
									</tr>
									<tr>
										<td>統編:</td>
										<td>${appDetail.editor}</td>
									</tr>
									<tr>
										<td>申請金額:</td>
										<td>${appDetail.appMoney}</td>
									</tr>
									<tr>
										<td>備註:</td>
										<td>${appDetail.remark}</td>
									</tr>
									
									
								</c:forEach>
							</table>
						<hr/>
						<form class="for1"
						action="<c:url value="/SingerPassPage?feeAppID=${S_feeAppID}"/>"
						method="post">
						<table id="idtable1">
						
							<th><input type="checkbox" id="checkbox1" name="decide" value="通過" onclick = 'check(this)'>通過</th>
							<th><input type="checkbox" id="checkbox2" name="decide" value="退件" onclick = 'check(this)'>退件</th>
							<th><input type="submit" name="button" id="button" value="送出" class="btn btn-info"></th>
						
 						
						</table>
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
	<script src="js/jquery-3.4.1.min.js"></script>
	<script>
	$("#button").click(function(){
		var check=$("input[name='decide']:checked").length;//判斷有多少個方框被勾選
		if(check==0){
			alert("您尚未勾選任何項目");
			return false;//不要提交表單
		}
	})
	</script>
	

</body>
</html>