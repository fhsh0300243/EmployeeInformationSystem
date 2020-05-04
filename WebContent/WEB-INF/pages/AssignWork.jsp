<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
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
	border: 2px solid #CCC;
	width:150px;
	height:200px;
	text-align: center;
}

.work {
	weight: 10px;
	height: 500px;
	border: 2px solid #CCC;
	flex: 1;
	float: left;
	margin-right: 15px;
}

#e {
	user-select: none;
	moz-user-select: none;
	webkit-user-select: none;
	ms-user-select: none;
}

#employee {
	width: 200px;
	height: 500px;
	border: 2px solid #ccc;
	float: right;
}

li.emul, li.chemul {
	list-style-type: none;
	margin: 10px;
	border: 1px solid #ccc;
	padding: 4px;
	color: #666;
	cursor: move;
}

li.wkli {
	list-style-type: none;
	margin: 10px;
	border: 3px solid #ccc;
	color: #666;
	width:180px;
	height:30px;
	font-size:15px;
	font-weight:bold;
	text-align:center;
}

li.emul:hover {
	background-color: #ccc;
}

li.chemul:hover {
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
.wkul{
padding:0px;
}
.taul{
padding:0px;
}
b {
	font-size: 20px;
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
					<p>
						<b>Hi~</b> ${usersResultMap.Title},
					<p>${usersResultMap.UserName}您好~
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
		jQuery.event.props.push('dataTransfer');
		//var dragel = null;

		$
				.getJSON(
						"assignwork",
						function(member) {
							var txt = "";
							txt += "<table border=\"1\" id=\"t\">";
							txt += "<tr><th>" + member[0][0].pqt;
							for (let i = 0; i < member.length; i++) {
								txt += "<tr><td>" + member[i][0].Work;
							}
							txt += "</table>"

							for (let j = 0; j < member.length; j++) {
								txt += "<div class = \"work\" wid = "+member[j][0].wid+" work = "+member[j][0].Work+"><ul class = \"wkul\" border=\"1\" id="+member[j].Work+">";
								txt += "<li id = "+member[j][0].Work+" class = \"wkli\">"
										+ member[j][0].Work;
								txt += "</ul>";
									for(let k=0;k<member[j].length;k++){
										console.log(member[j][k].empid);
										if(member[j][k].empid!=undefined){
										txt += "<li class=\"chemul\" draggable=\"true\" wid="+member[j][0].wid+" empid="+member[j][k].empid+">"+member[j][k].empname+"</li>";
										}
										}
									txt += "</div>";
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
								em += "<li id = "+ employee[k].empid +" class=\"emul\" draggable=\"true\" wid=\"\" empid="+employee[k].empid+">"
										+ employee[k].name + "</li>";
							}
							em += "<p><li class = \"emul\">xxxx</li>"
							em += "</ul>"
							$("#employee").html(em);
						});
		$(document).on("dragstart", ".emul", function(event) {
			event.dataTransfer.effectAllowed = "copyMove";
			event.dataTransfer.setData("text/plain", event.target.id);
			console.log(this.innerHTML);
		});
		$(document).on("dragstart", ".chemul", function(event) {
			event.dataTransfer.effectAllowed = "Move";
			event.dataTransfer.setData("text/plain", event.target.id);
			console.log(this.innerHTML);
		});
		$(document).on("dragover", ".work", function(event) {
			event.stopPropagation();
			event.preventDefault();
			event.dataTransfer.dropEffect = "Move";
		});
		$(document).on("dragover", ".emul", function(event) {
			event.dataTransfer.dropEffect = "none"
		})
		$(document).on("dragover", ".wkul", function(event) {
			event.dataTransfer.dropEffect = "none"
		})
		$(document).on("drop", ".work", function(event) {
			var data = event.dataTransfer.getData("text");
			var nodeCopy = document.getElementById(data);

			var wid = nodeCopy.getAttribute("wid");
			var cwid = $(event.target).attr("wid");
			var work = $(event.target).attr("work");
			console.log("wid:" + wid);
			console.log("data:" + data);
			console.log("work:" + work);
			console.log("targetid:"+event.target.children.id);
			event.stopPropagation();//停止冒泡事件、事件往父容器觸發
			event.preventDefault();
			//↑上述兩行for firefox，避免瀏覽器直接Redirect
			//event.target.appendChild(document.getElementById(data));
		
			var empid = nodeCopy.getAttribute("empid")
			var wchildren = event.target.children;
			for(i=0;i<wchildren.length;i++){
				if(wchildren[i].getAttribute("empid") == empid){
					event.dataTransfer.dropEffect = "none";
					return false;
					}
				};
			if ((nodeCopy.getAttribute("class")) == "emul") {
				nodeCopy = nodeCopy.cloneNode(true);
				nodeCopy.setAttribute("wid", cwid);
				nodeCopy.setAttribute("class", "chemul");
				nodeCopy.setAttribute("id", data + "ch");
				//nodeCopy.id = "newId";
				event.target.appendChild(nodeCopy)
			} else if ((nodeCopy.getAttribute("class")) == "chemul") {
				nodeCopy.setAttribute("id", data + "ch")
				
				event.target.appendChild(nodeCopy);
				$.post("changeaw", {
					empid : empid,
					wid : wid,
					cwid:cwid,
					work : work
				}, function(member, status) {
					if (status == "success")
						console.log("change success");
				});
				nodeCopy.setAttribute("wid", cwid);
				;
				return false;
			}
			;
			//event.target.textContent = data;
			$.post("insertaw", {
				empid : empid,
				wid : cwid,
				work : work
			}, function(member, status) {
				if (status == "success")
					console.log("insert success");
			});
			$(document).on("dragover", ".chemul", function(event) {
				event.dataTransfer.dropEffect = "none"
			})
		
		});
	</script>
</body>
</html>