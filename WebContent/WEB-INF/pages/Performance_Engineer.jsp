<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8">
<title>Performance Engineer</title>
<style>
#worklist {
	float: left;
	margin-right: 15px;
	border: 2px #CCC solid;
}

.work {
	weight: 100px;
	height: 500px;
	border: 2px solid #CCC;
	flex: 1;
	float: left;
	margin-right: 15px;
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
</style>
</head>
<body>
	<div id="worklist">
	</div>
	<div id="workstatus">
	<div class = "work" wkstatus =1 id="wkstatus1"></div>
	<div class = "work" wkstatus =2 id="wkstatus2"></div>
	<div class = "work" wkstatus =3 id="wkstatus3"></div>
	</div>
	<script>
		jQuery.event.props.push('dataTransfer');
		$
				.getJSON(
						"engworklist",
						function(member) {
							var txt = "";
							txt += "<ul id=\"wlist\">工作清單";
							for (let i = 0; i < member.length; i++) {
								txt += "<li class=\"wli\" id="+member[i].awID+" awid ="+member[i].awID+" draggable=\"true\">"
										+ member[i].Work;
							}
							txt += "</ui>";
							$("#worklist").html(txt);
							});
				$.getJSON("wkstatus1",function(member){
						var txt ="";
						txt += "<ul class=\"statusul\" border=\"1\">To do</ul>";
						for(let i=0;i<member.length;i++){
							txt += "<li class =\"chwli\" id="+member[i].awid+" awid="+member[i].awid+" draggable=\"true\">"+member[i].Work;
							}
						$("#wkstatus1").html(txt);
					});
				$.getJSON("wkstatus2",function(member){
					var txt ="";
					txt += "<ul class=\"statusul\" border=\"2\">Doing</ul>";
					for(let i=0;i<member.length;i++){
						txt += "<li class =\"chwli\" id="+member[i].awid+" awid="+member[i].awid+" draggable=\"true\">"+member[i].Work;
						}
					$("#wkstatus2").html(txt);
				});
				$.getJSON("wkstatus3",function(member){
					var txt ="";
					txt += "<ul class=\"statusul\" border=\"1\">Done</ul>";
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