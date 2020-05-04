<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert PQT</title>
<style>
#p{
display:none
}
</style>
</head>
<body>
<form action="insertpqt" method="post">
<div id="p">
<input type="text" name="dag"  value= ${ dag}><br/>
</div>
目標內容<input type="text" name="pqt"><br/>
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