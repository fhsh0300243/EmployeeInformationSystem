<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出勤系統</title>
</head>
<body>
<h1>出勤系統</h1>
<label>員工編號: </label>${usersResultMap.EmployeeID}<br>
<label>帳號: </label>${usersResultMap.UserName}<br>
<label>職稱: </label>${usersResultMap.Title}<br>
<label>部門: </label>${usersResultMap.Department}<br>
<table>
	<tr>
		<td><input type="button" value="打卡" onclick="location.href='/EmployeeInformationSystem/InquiryToday'"></td>
		<td><input type="button" value="個人出勤查詢" onclick="location.href='/EmployeeInformationSystem/InquiryPage'"></td>
		<td><input type="button" value="部門出勤查詢" onclick="location.href='/EmployeeInformationSystem/InquiryDepartmentPage'"></td>
		<td><input type="button" value="設定行事曆" onclick="location.href='/EmployeeInformationSystem/InqueryCalendar'"></td>
	</tr>
</table>
</body>
</html>