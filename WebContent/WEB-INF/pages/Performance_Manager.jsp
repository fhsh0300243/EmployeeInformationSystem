<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8">
<title>Performance Manager</title>
<style type="text/css">
table {
	float: left;
	margin-right: 15px;
}
</style>
</head>
<body>
<div>
<input type="button" value=${ dag}>
</div>
	<div id="main"></div>
	<span>
		<table border="1">
			<tr>
				<td><a href="InsertPQT"><input type="button" value="+"></a>
		</table>
	</span>
	<script>
		$.getJSON("managertable", function(member) {
			var txt;
			for (let i = 0; i < member.length; i++) {
				txt += "<table border=\"1\" id=\"t\">";
				txt += "<tr><th><a href=\"ChangePQT?pid="+member[i][0].pID+"&time="+member[i][0].time+"&worksetter="+member[i][0].worksetter+
						"\"><input type=\"button\" value="+ member[i][0].PersonalQuarterlyTarget+">";
				for (let j = 0; j < member[i].length; j++) {
					txt += "<tr><td>" + member[i][j].Work;
					console.log(member[i][j].Work);
				}
				txt += "</table>"
				$("#main").html(txt);
			}
		})
	</script>
</body>
</html>