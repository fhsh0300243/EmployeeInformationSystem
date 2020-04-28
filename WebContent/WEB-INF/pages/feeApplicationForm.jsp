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

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="icon" href="images/favicon.ico">
<style>

.st1 {
	width: 450px;
	padding-bottom: 10px;

}

.st2 {
	width: 450px;
	text-align: center;

}

.ca1 {
	width: 100px;
	float: left;
	text-align: right;

}

fieldset {
	width: 500px;
	border: 2px solid blue;
	margin: 0 auto;
}

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
					<p class="functionTitle">差旅費申請</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<fieldset style="margin: 0 auto">
							
							<form action="AddFeeApp.action" method="post">
								<!--  
								<div class="st1">
									<label for="" class="ca1">部門:</label> 
									<select name="department">
										<option value="HR">HR(人力資源)</option>
										<option value="RD">RD(研發工程師)</option>
										<option value="Test">Test(測試工程師)</option>
										<option value="Sales">Sales(銷售部門)</option>
										<option value="PM">PM(專案管理)</option>
									</select>
								</div>
								
			<div class="st1">
				<label class="ca1" for="xx1">員工編號:</label><input type="text"
					id="xx1" name="employeeID" placeholder="guest" autofocus
					autocomplete="off" size="20">
			</div>
			-->
								
								
								<div class="st1">
								
									<label class="ca1"><span class="mark1">*</span>申請項目:</label> 
									<select name="appItem" >
										<option value="====請選擇項目====">====請選擇項目====</option>
										<option value="交通">交通</option>
										<option value="住宿">住宿</option>
										<option value="餐費">餐費</option>
										<option value="加班費">加班費</option>
										<option value="其他">其他</option>

									</select><span class="mark1">${feemsgmap.appItem}</span>
								</div>
								<!--  
			<div class="st1">
				<label class="ca1 " for="">申請日期:</label> <label><input
					type="text"  name=appTime></label>

			</div>
			-->
								<div class="st1">
									<label class="ca1 " for=""><span class="mark1">*</span>發票日期:</label> <label><input
										type="date" name="invoiceTime"><span class="mark1">${feemsgmap.invoiceTime}</span></label>

								</div>
								<div class="st1">
									<label class="ca1 " for="">發票號碼:</label> <label><input
										type="tel" name="invoiceNb" maxlength="10"  ></label>
										

								</div>
								<div class="st1">
									<label class="ca1 " for="">統編:</label> <label><input
										type="tel" name="editor" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') " >
										<span class="mark1">${feemsgmap.invoiceNb}</span></label>

								</div>
								<!--
			<div class="st1">
				<label for="" class="ca1">照片:</label> <input type="file" name="file1">
			</div>
			-->
								<div class="st1">
									<label class="ca1" for="comment1">備註:</label>
									<textarea cols="30" rows="3" id="comment1" name="remark"></textarea>
								</div>
								<div class="st1">
									<label class="ca1 " for=""><span class="mark1">*</span>金額:</label> <label><input
										type="tel" name="appMoney"><span class="mark1">${feemsgmap.appMoney}</span></label>

								</div>

								<div class="st2">
									<input type="submit" name="New" value="申請" class="btn btn-info"/> <input
										type="reset" value="清除" class="btn btn-info"/><span class="mark1">${feemsgmap.Success}</span>
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