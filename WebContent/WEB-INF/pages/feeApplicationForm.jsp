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
<link rel="stylesheet" type="text/css" href="css/ApplyPage.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="icon" href="images/favicon.ico">
<style>
.well, .panel {
	text-align: center;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}

.mark1 {
	color: red;
}

b {
	font-size: 20px;
}

.pInLeft {
	margin: 0;
}

td {
	text-align: left;
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
					<p class="pInLeft">${ LoginOK.employee.name}
						(${usersResultMap.UserName})您好~
					<p class="pInLeft">歡迎登入番茄科技員工資訊系統
				</div>

				<%@ include file="SubFeatureForFee.jsp"%>

			</div>

			<!--右邊欄位-->

			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">差旅費申請</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<fieldset>

							<form class="for3" action="AddFeeApp.action" method="post">
								<table id="idtable4">

									<tr>
										<td class="tdtag1"><span class="mark1">*</span>申請項目:</td>
										<td><select name="appItem">
												<option value="====請選擇項目====">====請選擇項目====</option>
												<option value="交通">交通</option>
												<option value="住宿">住宿</option>
												<option value="餐費">餐費</option>
												<option value="加班費">加班費</option>
												<option value="其他">其他</option>

										</select></td>

									</tr>
									<tr>
										<td></td>
										<td class="tdErr">${feemsgmap.appItem}</td>
									</tr>

									<tr>
										<td class="tdtag1"><span class="mark1">*</span>發票日期:</td>
										<td><input type="date" name="invoiceTime"></td>

									</tr>
									<tr>
										<td></td>
										<td class="tdErr">${feemsgmap.invoiceTime}</td>
									</tr>
									<tr>
										<td class="tdtag1">發票號碼:</td>
										<td><input type="tel" name="invoiceNb" maxlength="10"></td>

									</tr>
									<tr>
										<td></td>
										<td></td>
									</tr>
									<!-- 
								<div class="st1">
									<label class="ca1 " for="">統編:</label> <label><input
										type="tel" name="editor" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') " >
										<span class="mark1">${feemsgmap.invoiceNb}</span></label>

								</div>
								-->
									<!--
								<div class="st1">
								<label for="" class="ca1">照片:</label> <input type="file" name="file1">
								</div>
								-->
									<tr>
										<td class="tdtag1">備註:</td>
										<td><textarea cols="22" rows="3" id="comment1"
												name="remark"></textarea></td>

									</tr>
									<tr>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td class="tdtag1"><span class="mark1">*</span>金額:</td>
										<td><input type="tel" name="appMoney"></td>

									</tr>
									<tr>
										<td></td>
										<td class="tdErr">${feemsgmap.appMoney}</td>

									</tr>
								</table>


								<div>
									<input type="submit" name="New" value="申請" class="btn btn-info" />
									<input type="reset" value="清除" class="btn btn-info" /><br />
									<span class="mark1">${feemsgmap.Success}</span>
								</div>

							</form>
						</fieldset>
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