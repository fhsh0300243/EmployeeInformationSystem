<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>差旅費申請</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>
body {
	background-image: url(../../images/picture_back.jpg)
}

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
</style>
</head>
<body>
	<a href="FeeAllPage.action">差旅費申請主頁</a>
	<a href="AddFeeApp.action">差旅費申請</a>

	<fieldset>
		<legend>差旅費申請</legend>
		<form action="AddFeeApp.action" method="post">
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
			<!--  
			<div class="st1">
				<label class="ca1" for="xx1">員工編號:</label><input type="text"
					id="xx1" name="employeeID" placeholder="guest" autofocus
					autocomplete="off" size="20">
			</div>
			-->
			<div class="st1">
				<label for="" class="ca1">申請項目:</label> <select name="appItem">
					<option value="交通">交通</option>
					<option value="住宿">住宿</option>
					<option value="餐費">餐費</option>
					<option value="其他">其他</option>

				</select>
			</div>
			<!--  
			<div class="st1">
				<label class="ca1 " for="">申請日期:</label> <label><input
					type="text"  name=appTime></label>

			</div>
			-->
			<div class="st1">
				<label class="ca1 " for="">發票日期:</label> <label><input
					type="date"   name="invoiceTime"></label>

			</div>			
			<div class="st1">
				<label class="ca1 " for="">發票號碼:</label> <label><input
					type="tel" name="invoiceNb"></label>

			</div>
			<div class="st1">
				<label class="ca1 " for="">統編:</label> <label><input
					type="tel" name="editor"></label>

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
				<label class="ca1 " for="">金額:</label> <label><input
					type="tel" name="appMoney"></label>

			</div>
			
			<div class="st2">
				<input type="submit" name="New" value="申請" /> <input type="reset"
					value="清除" />
			</div>

		</form>
	</fieldset>
</body>
</html>