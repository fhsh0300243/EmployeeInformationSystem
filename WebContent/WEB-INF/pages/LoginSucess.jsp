<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Success</title>
</head>
<body>
<h1>登入成功</h1>
<h3>你的個人資訊</h3>
<label>員工編號: </label>${usersResultMap.EmployeeID}<br>
<label>帳號: </label>${usersResultMap.UserName}<br>
<label>密碼: </label>${usersResultMap.UserPassword}<br>
<label>職稱: </label>${usersResultMap.Title}<br>
<label>部門: </label>${usersResultMap.Department}<br>
<a href="<c:url value="/EmployeePage.do"/>">員工管理</a><br/>
<a href="<c:url value="/logout/toLoginPage"/>">LOGOUT</a><br/>

</body>
</html>