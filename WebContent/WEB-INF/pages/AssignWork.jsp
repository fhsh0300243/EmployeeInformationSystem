<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8">
<title>Assign Work</title>
<style type="text/css">
table {
	float: left;
	margin-right: 15px;
}

#e {
	user-select: none;
	moz-user-select: none;
	webkit-user-select: none;
	ms-user-select: none;
}

#work {
	weight: 10px;
	height: 500px;
	border: 2px solid #CCC;
	flex: 1;
	float: left;
}
</style>
</head>
<body>
	<div id="main"></div>
	<div id="employee"></div>
	<script type="text/javascript">
		$.getJSON("assignwork", function(member) {
			var txt;
			txt += "<table border=\"1\" id=\"t\">";
			txt += "<tr><th>" + member[0].pqt;
			for (let i = 0; i < member.length; i++) {
				txt += "<tr><td>" + member[i].Work;
			}
			txt += "</table>"

			for (let j = 0; j < member.length; j++) {
				txt += "<div id = \"work\"><table border=\"1\" id=\"s"+j+"\">";
				txt += "<tr><th>" + member[j].Work;
				txt += "</table></div>"
			}
			$("#main").html(txt);
		});
		$.getJSON(
				"employeelist",
				function(employee) {
					var em;
					em += "<table border=\"1\" id=\"e\">";
					em += "<tr><th><input type=\"button\" value=\"員工名單\">";
					for (let k = 0; k < employee.length; k++) {
						em += "<tr><td><input type=\"button\" value=\""+employee[k].name+"\" id = \"e\" draggable=\"true\">";
					}
					em += "</table>"
					$("#employee").html(em);
				});
		$("#e").on("drag", function(e) {
			e.dataTransfer.setData('text/plain', e.target.id)
		});
		$("#work").on({
			"dragover" : function(e) {
				ev.preventDefault();
				// Set the dropEffect to move
				ev.dataTransfer.dropEffect = "move"
			},
			"drop" : function(e) {
				ev.preventDefault();
				// Get the id of the target and add the moved element to the target's DOM
				var data = ev.dataTransfer.getData("text");
				ev.target.appendChild(document.getElementById(data));
			}
		});

	</script>
</body>
</html>