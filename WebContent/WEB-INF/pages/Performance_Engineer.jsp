<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
#worklist {
	float: left;
	margin-right: 15px;
	border: 2px #CCC solid;
}

.work {
	width: 200px;
	height: 500px;
	border: 2px solid #CCC;
	flex: 1;
	float: left;
	margin-right: 150px;
}

.wli {
	list-style-type: none;
	margin: 10px;
	border: 1px solid #ccc;
	padding: 4px;
	color: #666;
	cursor: move;
	user-select: none;
	moz-user-select: none;
	webkit-user-select: none;
	ms-user-select: none;
}
.chwli {
	list-style-type: none;
	margin: 10px;
	border: 1px solid #ccc;
	padding: 4px;
	color: #666;
	cursor: move;
	user-select: none;
	moz-user-select: none;
	webkit-user-select: none;
	ms-user-select: none;
}
.tit{
font-size:15px;
	font-weight:bold;
	text-align:center;
	margin-right:30px;
}
#wlist{
padding:0px;
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
					<p><b>Hi~</b> ${usersResultMap.Title},
					<p>${usersResultMap.UserName} 您好~
					<p>歡迎登入番茄科技員工資訊系統
				</div>

				<%@ include file="SubFeatureForPerformance.jsp"%>

			</div>

			<!--右邊欄位-->

			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">績效管理</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">

						<div id="worklist" wkstatus =0 class = "work">
	</div>
	<div id="workstatus">
	<div class = "work" wkstatus =1 id="wkstatus1"></div>
	<div class = "work" wkstatus =2 id="wkstatus2"></div>
	<div class = "work" wkstatus =3 id="wkstatus3"></div>
	</div>
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
	
	<script>
		jQuery.event.props.push('dataTransfer');
		$
				.getJSON(
						"engworklist",
						function(member) {
							var txt = "";
							txt += "<ul id=\"wlist\"><div class=\"tit\">工作清單</div>";
							for (let i = 0; i < member.length; i++) {
								txt += "<li class=\"wli\" id="+member[i].awID+" awid ="+member[i].awID+" draggable=\"true\">"
										+ member[i].Work;
							}
							txt += "</ui>";
							$("#worklist").html(txt);
							});
				$.getJSON("wkstatus1",function(member){
						var txt ="";
						txt += "<ul class=\"statusul\" border=\"1\"><div class=\"tit\">To do</div></ul>";
						for(let i=0;i<member.length;i++){
							txt += "<li class =\"chwli\" id="+member[i].awid+" awid="+member[i].awid+" draggable=\"true\">"+member[i].Work;
							}
						$("#wkstatus1").html(txt);
					});
				$.getJSON("wkstatus2",function(member){
					var txt ="";
					txt += "<ul class=\"statusul\" border=\"2\"><div class=\"tit\">Doing</div></ul>";
					for(let i=0;i<member.length;i++){
						txt += "<li class =\"chwli\" id="+member[i].awid+" awid="+member[i].awid+" draggable=\"true\">"+member[i].Work;
						}
					$("#wkstatus2").html(txt);
				});
				$.getJSON("wkstatus3",function(member){
					var txt ="";
					txt += "<ul class=\"statusul\" border=\"1\"><div class=\"tit\">Done</div></ul>";
					for(let i=0;i<member.length;i++){
						txt += "<li class =\"chwli\" id="+member[i].awid+" awid="+member[i].awid+" draggable=\"true\">"+member[i].Work;
						}
					$("#wkstatus3").html(txt);
				});
		$(document).on("dragstart", ".wli", function(event) {
			event.dataTransfer.effectAllowed = "copyMove";
			event.dataTransfer.setData("text/plain", event.target.id);
			console.log("id"+event.target.id);
		})
			$(document).on("dragstart", ".chwli", function(event) {
				event.dataTransfer.effectAllowed = "Move";
				event.dataTransfer.setData("text/plain", event.target.id);
				console.log("id:"+this.innerHTML);
			});
		$(document).on("dragover", ".work", function(event) {
			event.stopPropagation();
			event.preventDefault();
			event.dataTransfer.dropEffect = "Copy";
		})
		$(document).on("dragover",".wli",function(event){
			event.dataTransfer.dropEffect="none"
			});
		$(document).on("dragover", ".statusul",function(event){
			event.dataTransfer.dropEffect="none"
		});
		$(document).on("drop", ".work", function(event) {
			var data = event.dataTransfer.getData("text");
			var nodeCopy = document.getElementById(data);
			var wkstatus = $(event.target).attr("wkstatus")
			console.log("data:"+data);
			console.log("wkstatus:"+wkstatus);
			var awid = nodeCopy.getAttribute("awid");
			event.stopPropagation();
			event.preventDefault();
			
		
			if((nodeCopy.getAttribute("class")) == "wli"){
				//nodeCopy = nodeCopy.cloneNode(true);
				nodeCopy.setAttribute("class", "chwli");
				event.target.appendChild(nodeCopy);
			}else if((nodeCopy.getAttribute("class")) == "chwli")	{
				event.target.appendChild(nodeCopy);
			}	
			$.post("workstatus", {
				awid : data,
				wkstatus : wkstatus
			}, function(f1, status) {
				if (status == "success")
					console.log("change work status success");
			})
			$(document).on("dragover", ".chwli", function(event) {
				event.dataTransfer.dropEffect = "none"
			})
		
		});
	</script>
</body>
</html>