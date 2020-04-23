<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sortablejs@latest/Sortable.min.js"></script>
<meta charset="UTF-8">
<title>番茄科技 績效系統</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<link rel="icon" href="images/favicon.ico">
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
	margin-right: 15px;
}

#employee {
	weight: 20px;
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

li.wkul {
	list-style-type: none;
	margin: 10px;
	border: 1px solid #ccc;
	color: #666;
}

li.emul:hover {
	background-color: #ccc;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}

.well, .panel {
	text-align: center;
}

.main {
	position: relative;
	left: 20%;
	width: 82%;
}
</style>
</head>

<body>


	<br>
	<div class="container-fluid">
		<div class="row">

			<!--左邊欄位-->
			<div class="col-sm-4">
				<div class="well">
					<p>Hi, ${usersResultMap.UserName} 您好~
					<p>歡迎登入番茄科技員工資訊系統
				</div>

				<%@ include file="SubFeatureForPerformance.jsp"%>

			</div>

			<!--右邊欄位-->

			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">工作分派</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">

						<div id="main"></div>
						<div id="employee"></div>
						
						<div class="list_footer">
							<div id="tag"></div>
							<div id="page"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="CanNotRightDownDiv">
		<img class="CanNotRightDown" src="images/CompanyLogo.png">
	</div>

	<script type="text/javascript">
		$
				.getJSON(
						"assignwork",
						function(member) {
							var txt = "";
							txt += "<table border=\"1\" id=\"t\">";
							txt += "<tr><th>" + member[0].pqt;
							for (let i = 0; i < member.length; i++) {
								txt += "<tr><td>" + member[i].Work;
							}
							txt += "</table>"

							for (let j = 0; j < member.length; j++) {
								txt += "<div id = \"work\" ondragover=\"event.preventDefault()\"><ul ondraged border=\"1\" id=\"s"+j+"\">";
								txt += "<li class = \"wkul\">" + member[j].Work;
								txt += "<p class = \"wkp\">";
								txt += "</ul></div>"
							}
							$("#main").html(txt);
						});
		$
				.getJSON(
						"employeelist",
						function(employee) {
							var em = "";
							em += "員工名單"
							em += "<ul id=\"e\" class=\"taul\">";
							for (let k = 0; k < employee.length; k++) {
								em += "<li id = \"e\" class=\"emul\" draggable=\"true\">"
										+ employee[k].name + "</li>";
							}
							em += "<p><li class = \"emul\">xxxx</li>"
							em += "</ul>"
							$("#employee").html(em);
						});
		$(document).on("drag",".emul",function(event){
			event.dataTransfer.setData("text/plain",event.target)
		})
		$(document).on("drop",".wkp",function(event){
			var data = event.dataTransfer.getData("text/plain");
			  event.target.textContent = data;
			  event.preventDefault();
		})
	</script>
</body>
</html>