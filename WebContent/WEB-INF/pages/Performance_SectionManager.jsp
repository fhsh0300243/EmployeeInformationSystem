<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8">
<title>performance_manager</title>
<style type="text/css">
#title {
	width: 80%
}

table {
	float: left;
	margin-right: 15px;
}
</style>
</head>
<body>
<aside class="menu">
		<table id = "t">
			<tr>
				<td class="tdTitle"><input type="button" value="研發部" id=a1></td>
			</tr>
			<tr class="trbtn">
				<td><input type="button" value="銷售部" id=a1></td>
			</tr>
			<tr class="trbtn">
				<td><input type="button" value="人資部" id=a1></td>
			</tr>
			<tr class="trbtn">
				<td><input type="button" value="測試部" id=a1></td>
			</tr>
			<tr class="trbtn">
				<td><input type="button" value="專案管理部" id=a1></td>
			</tr>
		</table>
	</aside>
	<div id="main">
	</div>
	
	<script>
	$.getJSON("jsontable", function(member){
		var txt;
		for(let i=0;i<member.length;i++){
			txt += "<table border=\"1\" id=\"t\">";
			txt += "<tr><th><a href = \"toAssignWork?pid="+member[i][0].pID+"\"><input type = \"button\" value=\""+member[i][0].PersonalQuarterlyTarget+"\"></a>";
			for(let j=0;j<member[i].length;j++){
				txt += "<tr><td><a href=\"ChangeWork?wid="+member[i][j].wid+"&work="+member[i][j].Work+"&worksetter="+member[i][j].worksetter+"&time="+member[i][j].time+
						"\"><input type=\"button\" value=\""+member[i][j].Work+"\">";
				}
			txt += "<tr><th><a href =\"InsertWork?pid="+member[i][0].pID+"&pqt="+member[i][0].PersonalQuarterlyTarget+"\"><input type=\"button\" value=\"+\">";
			txt += "<\/table>"
	
			$("#main").html(txt);
		}
	})
		
	</script>

</body>
</html>