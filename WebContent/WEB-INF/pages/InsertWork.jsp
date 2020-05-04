<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
<input type="text" name="work"><br/>
<input type="submit" onclick="pp()" value="確認"><input type="reset" value="清除">
</form>
<script>
function pp(){
	window.opener.location.reload();  
    window.close(); 
} 
</script>
</body>
</html>