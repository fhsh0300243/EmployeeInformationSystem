<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8">
<title>performance_manager</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<style type="text/css">
#title {
	width: 80%
}

table {
	float: left;
	margin-right: 15px;
}
p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}
.well, .panel {
	text-align: center;
}
.main{
	position: relative; 
	left: 20%;
	width:82%;

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
					<p class="functionTitle">績效</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
					
						<div id="main" class="main"></div>
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
		$.getJSON("jsontable",
						function(member) {
							var txt;
							for (let i = 0; i < member.length; i++) {
								txt += "<table border=\"1\" id=\"t\">";
								txt += "<tr><th><a href = \"toAssignWork?pid="
										+ member[i][0].pID
										+ "\"><input type = \"button\" value=\""+member[i][0].PersonalQuarterlyTarget+"\"></a>";
								for (let j = 0; j < member[i].length; j++) {
									txt += "<tr><td><a href=\"ChangeWork?wid="
											+ member[i][j].wid
											+ "&work="
											+ member[i][j].Work
											+ "&worksetter="
											+ member[i][j].worksetter
											+ "&time="
											+ member[i][j].time
											+ "\"><input type=\"button\" value=\""+member[i][j].Work+"\">";
								}
								txt += "<tr><th><a href =\"InsertWork?pid="
										+ member[i][0].pID
										+ "&pqt="
										+ member[i][0].PersonalQuarterlyTarget
										+ "\"><input type=\"button\" value=\"+\">";
								txt += "<\/table>"

								$("#main").html(txt);
							}
						})
	</script>

</body>
</html>