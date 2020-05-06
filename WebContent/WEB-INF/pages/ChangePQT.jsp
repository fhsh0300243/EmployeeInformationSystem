<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8">
<title>Change PQT</title>
<style>
.h{
display:none;
}
</style>
</head>
<body>
<form action="changepqt" method="post">
目標內容<input type="text" id= "pqt" name="pqt"><br/>
<div class = "h">
<input type="text" name="time" value=${param["time"]}><br/>
<input type="text" name="pid" value=${param["pid"]}><br/>
<input type="text" name="worksetter" value=${param["worksetter"]}><br/>
</div>
<input type="submit" onclick="pp()" value="送出">
<input type="reset" value="重填">
</form>
<form action="deletepqt" method="post">
<input type="submit" onclick="pp()" value="刪除">
<div class = "h">
<input type="text" name="pid" value=${param["pid"]}><br/>
</div>
</form>
<div>
<input type = "button" id="demo" value="demo">
</div>
<script>
function pp(){
	window.opener.location.reload();  
    window.close(); 
}
$("#demo").click(function(){
	$("#pqt").val("在11/15前完成第二個功能");
});
</script>
</body>
</html>