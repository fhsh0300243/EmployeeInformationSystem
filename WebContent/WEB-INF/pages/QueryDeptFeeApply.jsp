<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>番茄科技 員工管理</title>

<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="icon" href="images/favicon.ico">
<style>
.well, .panel {
	text-align: center;
}

body {
	font-family: 微軟正黑體;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}

table {
	margin: 20px;
	border-collapse: collapse;
}
.userImg {
	width: 20%;
	height: 20%;
	border: 2px solid tan;
	border-radius: 15px;
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

				<%@ include file="SubFeatureForEmpManage.jsp"%>

			</div>




			<!--右邊欄位-->

			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">部門差旅費用</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<table border="1" id="datalist" style="margin: 0 auto"></table>
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
		var data;
		const perpage = 10;
		let nowpage = 1;
		showdata();
		//從資料庫取得資料
		function showdata() {
			$.ajax({
				url : "thisSeasonDeptCostPercent.action",
				type : "GET",
				success : function(Str) {
					data = JSON.parse(Str);
					pagination(data, nowpage);
				}
			});
		}
		//產生顯示的資料
		function pagination(data, nowpage) {
			$("#datalist").html("");
			const datatotal = data.length;
			const pagesTotal = Math.ceil(datatotal / perpage);
			let currentPage = nowpage;
			var minData = (currentPage * perpage) - perpage + 1;
			var maxData = (currentPage * perpage);


			var txt = "<tr><th>部門<th>季花費百分比<th>";

			if (maxData > datatotal) {
				maxData = datatotal;
			}
			for (let i = minData - 1; i < maxData; i++) {
				txt += "<tr><td>" + "HR部門";
				txt += "<td>" + data[i].HRcost;
				txt += "<tr><td>" + "RD部門";
				txt += "<td>" + data[i].RDcost;
				txt += "<tr><td>" + "QA部門";
				txt += "<td>" + data[i].QAcost;
				txt += "<tr><td>" + "Sales部門";
				txt += "<td>" + data[i].Salescost;
				txt += "<tr><td>" + "PM部門";
				txt += "<td>" + data[i].PMcost;
				/*
				txt += "<td><a href='<c:url value='/EditEmployee.do?id="
						+ emps[i].empID + "'/>' name='" + emps[i].empID
						+ "'>Edit</a>";
				*/
			}

			$("#datalist").html(txt);
		}


	</script>
</body>
</html>