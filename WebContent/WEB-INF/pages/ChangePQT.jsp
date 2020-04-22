<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8">
<title>Change PQT</title>
</head>
<body>
<form action="changepqt" method="post">
pqt<input type="text" name="pqt"><br/>
time<input type="text" name="time" value=${param["time"]}><br/>
pid<input type="text" name="pid" value=${param["pid"]}><br/>
worksetter<input type="text" name="worksetter" value=${param["worksetter"]}><br/>
<input type="submit" value="送出">
<input type="reset" value="重填">
</form>
<form action="deletepqt" method="post">
<input type="submit" value="刪除">
<input type="text" name="pid" value=${param["pid"]}><br/>
</form>
</body>
</html>