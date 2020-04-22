<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Work</title>
<style>
#p{
visibility: hidden;
}
</style>
</head>
<body>
<form action="insertwork" method="post">
<input type="text" name="pid" id="p" value= ${param["pid"]}><br/>
<input type="text" name="pqt" id="p" value= ${param["pqt"]}><br/>
<input type="text" name="work"><br/>
<input type="submit" value="確認"><input type="reset" value="清除">
</form>
</body>
</html>