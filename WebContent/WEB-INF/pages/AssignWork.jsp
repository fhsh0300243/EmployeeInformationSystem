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
	margin-right:15px;
}
#employee{
	weight:20px;
	height: 500px;
	border: 2px solid #ccc;
	float: right;
}
li.emul {
	list-style-type: none;
	margin: 10px;
	border: 1px solid #ccc;
	padding: 4px;
	color: #666;
	cursor: move;
}
li.wkul{
	list-style-type:none;
	margin:10px;
	border:1px solid #ccc;
	color:#666;
}
li.emul:hover{
background-color:#ccc;
}
}
</style>
</head>
<body>
	<div id="main"></div>
	<div id="employee"></div>
	<script type="text/javascript">
		$
				.getJSON(
						"assignwork",
						function(member) {
							var txt;
							txt += "<table border=\"1\" id=\"t\">";
							txt += "<tr><th>" + member[0].pqt;
							for (let i = 0; i < member.length; i++) {
								txt += "<tr><td>" + member[i].Work;
							}
							txt += "</table>"

							for (let j = 0; j < member.length; j++) {
								txt += "<div id = \"work\"ondragover=\"event.preventDefault()\"><ul ondraged border=\"1\" id=\"s"+j+"\">";
								txt += "<li class = \"wkul\">" + member[j].Work;
								txt += "<p class = \"wkp\">"
								txt += "</ul></div>"
							}
							$("#main").html(txt);
						});
		$
				.getJSON(
						"employeelist",
						function(employee) {
							var em;
							em += "員工名單"
							em += "<ul id=\"e\">";
							for (let k = 0; k < employee.length; k++) {
								em += "<li id = \"e\" draggable=\"true\" class=\"emul\"ondragstart=\"event.dataTransfer.setData(\"text/plain\", event.target)>"
										+ employee[k].name + "</li>";
							}
							em += "<p><li class = \"emul\">xxxx</li>"
							em += "</ul>"
							$("#employee").html(em);
						});
	</script>
</body>
</html>