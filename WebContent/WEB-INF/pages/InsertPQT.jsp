<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert PQT</title>
<style>
#p{
visibility: hidden;
}
</style>
</head>
<body>
<form action="insertpqt" method="post">
<input type="text" name="dag" id="p" value= ${ dag}><br/>
<input type="text" name="pqt"><br/>
<input type="submit" value="確認"><input type="reset" value="清除">
</form>
</body>
</html>