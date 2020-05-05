<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8">
<title>Change Work</title>
<style>
#p{
display:none;
}
</style>
</head>
<body>
<form action="changework" method="post">
工作內容<input type="text" id="work" name="work"><br/>
<div id = "p">
<input type="text" name="time" value=${param["time"]}><br/>
<input type="text" name="worksetter" value=${ LoginOK.employee.name}>worksetter<br/>
<input type="text" name="wid" value=${param["wid"]}>wid<br/>
</div>
<input type="submit" onclick="pp()" value="送出">
<input type="reset" value="重填">
</form>
<form action="deletework">
<input type="submit" onclick="pp()" value="刪除">
<div id = "p">
<input type="text" name="wid" value=${param["wid"]}>
</div>
</form>
<div>
<input type="button" id="demo" value = "demo">
</div>
<script>
function pp(){
	window.opener.location.reload();  
    window.close(); 
}
$("#demo").click(function(){
	$("#work").val("9/7內部報告");
});
</script>
</body>
</html>