<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>差旅費申請主頁</title>
<style>
body {
	background-image: url(../../images/picture_back.jpg)
}
</style>
</head>
<body>
	<a href="FeeAllPage.action">差旅費申請主頁</a>
	<a href="AddFeeApp.action">差旅費申請</a>

	<form action="FeeAllPage.action" method="post">
		<div class="st1">
			<label class="ca1" for="xx1">員工編號:</label><input type="text" id="xx1"
				name="employeeID" placeholder="guest" autofocus autocomplete="off"
				size="20">
		</div>
		<c:forTokens items="${id}" delims="," var="id">
			<p>id:${id}</p>
		</c:forTokens>

		<c:forTokens items="${money}" delims="," var="money">
			<p>money:${money}</p>
		</c:forTokens>
		<div class="st2">
			<input type="submit" name="New" value="搜尋" /> <input type="reset"
				value="清除" />
		</div>

	</form>

</body>
</html>