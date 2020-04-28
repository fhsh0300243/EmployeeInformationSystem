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
	border:2px #CCC solid;
}

.work {
	weight: 100px;
	height: 500px;
	border: 2px solid #CCC;
	flex: 1;
	float: left;
	margin-right: 15px;
}
.wli{
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
<div id="worklist" ></div>
<div id="workstatus"></div>
<script>
jQuery.event.props.push('dataTransfer');
$.getJSON("engworklist",function(member){
	var txt = "";
	txt += "<ul id=\"wlist\">工作清單";
	for(let i=0;i<member.length;i++){
		txt += "<li class=\"wli\" id="+member[i].awID+" draggable=\"true\">"+member[i].Work;
		}
		txt += "</ui>"
		$("#worklist").html(txt);
		var em="";
		em += "<div class =\"work\" wkstatus =\"1\"><ul border=\"1\">To do</div>"
		em += "<div class =\"work\" wkstatus =\"2\"><ul border=\"1\">Doing</div>"
		em += "<div class =\"work\" wkstatus =\"3\"><ul border=\"1\">Done</div>"
		$("#workstatus").html(em);
	
})
$(document).on("dragstart",".wli",function(event){
	event.dataTransfer.effectAllowed = "copyMove";
	event.dataTransfer.setData("text/plain",event.target.id);
	console.log(this.innerHTML);
})
$(document).on("dragover",".work",function(event){
	event.stopPropagation();
    event.preventDefault();
    event.dataTransfer.dropEffect = "Move";
})
$(document).on("drop",".work",function(event){
	var data = event.dataTransfer.getData("text");
	var wkstatus = $(event.target).attr("wkstatus")
	console.log(data);
	console.log(wkstatus);
	event.stopPropagation();
	event.preventDefault();
	var nodeCopy = document.getElementById(data).cloneNode(true);
	event.target.appendChild(nodeCopy);
	$.post("workstatus",{awid:data,wkstatus:wkstatus},function(f1,status){
		if(status=="success")
			console.log("change work status success");
		})
})
</script>
</body>
</html>