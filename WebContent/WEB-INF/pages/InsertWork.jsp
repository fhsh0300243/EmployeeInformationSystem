<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8">
<title>Insert Work</title>
<style>
#p{
display:none;
}
</style>
</head>
<body>
工作內容<form action="insertwork" method="post">
<div id="p">
<input type="text" name="pid" value= ${param["pid"]}><br/>
<input type="text" name="pqt" value= ${param["pqt"]}><br/>
</div>
<input type="text" id= "work" name="work"><br/>
<input type="submit" onclick="pp()" value="確認"><input type="reset" value="清除">
</form>
<div>
<input type="button" id="demo" value="demo">
</div>
<script>
function pp(){
	window.opener.location.reload();  
    window.close(); 
}
$("#demo").click(function(){
	$("#work").val("制定好框架");
});
</script>
</body>
</html>