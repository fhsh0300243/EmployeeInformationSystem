<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8">
<title>Change Work</title>
</head>
<body>
<form action="changework" method="post">
<input type="text" name="work">work<br/>
<input type="text" name="time" value=${param["time"]}><br/>
<input type="text" name="worksetter" value=${ userName}>worksetter<br/>
<input type="text" name="wid" value=${param["wid"]}>wid<br/>
<input type="submit" value="送出">
<input type="reset" value="重填">
</form>
<form action="deletework">
<input type="submit" value="刪除">
<input type="text" name="wid" value=${param["wid"]}>
</form>
</body>
</html>