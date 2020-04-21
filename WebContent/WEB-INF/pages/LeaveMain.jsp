<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>請假主頁面</title>
<link rel="stylesheet" type="text/css" href="css/MainPage.css">
</head>
<body>
	<div id="main">
		<table id="employee">
			<tr>
				<td class="tdTitle" colspan="3">個人專區</td>
			</tr>
			<tr>
				<td class="tdbtn"><a href="preapplypage">請假申請</a></td>
				<td class="tdbtn"><a href="preapplyrecord">請假紀錄</a></td>
				<td class="tdbtn"><a href="preleavetype">剩餘假別</a></td>
			</tr>
		</table>
		<table id="manager">
			<tr>
				<td class="tdTitle" colspan="3">主管專區</td>
			</tr>
			<tr>
				<td class="tdbtn"><a href="preunsignedpage">人員請假-未簽核</a></td>
				<td class="tdbtn"><a href="presignedpage">人員請假-已簽核</a></td>
			</tr>
		</table>
	</div>


</body>
</html>