<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>番茄科技 部門/個人績效</title>

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

.userImg {
	width: 20%;
	height: 20%;
	border: 2px solid tan;
	border-radius: 15px;
}
.idtable1 {
	margin: 30px auto;
	border-collapse: collapse;
}

.idtable1 tr {
	text-align: center;
}

.idtable1 th {
	text-align: center;
	background-color: #E0E0E0;
	padding: 10px 20px;
}

.idtable1 td {
	border-bottom: 1px solid #ddd;
	padding: 10px 20px;
}
b{
	font-size:20px;
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

				<%@ include file="SubFeatureForEmpManage.jsp"%>

			</div>




			<!--右邊欄位-->

			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">部門/個人績效</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<table id="datalist" class="idtable1" style="margin: 0 auto"></table><br/>
						<table id="detaillist" class="idtable1" style="margin: 0 auto"></table>
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
		var deptdetail;
		var persondetail;
		var ratedata;
		const perpage = 10;
		let nowpage = 1;
		showdata();
		//從資料庫取得資料
		function showdata() {
			$.ajax({
				url : "YearDeptGoals.action",
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


			var txt = "<tr><th>DeptId<th>部門<th>年度目標<th>訂定日期";

			if (maxData > datatotal) {
				maxData = datatotal;
			}
			for (let i = minData - 1; i < maxData; i++) {
				txt += "<tr><td>" + data[i].deptID;
				txt += "<td>" + data[i].deptname;
				txt += "<td><a href='#' name='"+data[i].deptID+"' onclick='f(this)'>" + data[i].goal+"</a>";
				txt += "<td>" + data[i].setupdate;
			}

			$("#datalist").html(txt);
		}
		
		
		function f(obj) {
			deptid = obj.name;
			$.ajax({
				url : "DeptPersonTargetDetail.action?deptid="+deptid,
				type : "GET",
				success : function(Str) {
					deptdetail = JSON.parse(Str);
					$.ajax({
						url : "calculateGoalAchievementRate.action",
						type : "POST",
						data:{"jsondata":JSON.stringify(deptdetail)},
						success : function(Str) {
							ratedata = JSON.parse(Str);
							detailpagination(deptdetail,ratedata, nowpage);
						}
					});									
				}
			});
		}

	
		function detailpagination(deptdetail,ratedata, nowpage) {
			$("#datalist").html("");
			const datatotal = deptdetail.length;
			const pagesTotal = Math.ceil(datatotal / perpage);
			let currentPage = nowpage;
			var minData = (currentPage * perpage) - perpage + 1;
			var maxData = (currentPage * perpage);
			//產生<a>標籤
			atag = "<a href=# name='1' onclick='f(this)'>" + 1 + "</a> ";
			for (let i = 2; i <= pagesTotal; i++) {
				atag += "<a href=# name='" + i + "' onclick='f(this)'>" + i
						+ "</a> ";
			}
			document.getElementById("tag").innerHTML = atag;
			$("#page").html("第" + nowpage + "頁");
			var txt = "<tr><th>PID<th>部門<th>季度目標<th>訂定人<th>訂定日期<th>達成率";

			if (maxData > datatotal) {
				maxData = datatotal;
			}
			for (let i = minData - 1; i < maxData; i++) {
				txt += "<tr><td>" + deptdetail[i].pID;
				txt += "<td>" + deptdetail[i].deptname;
				txt += "<td>" + deptdetail[i].target;
				txt += "<td>" + deptdetail[i].setter;
				txt += "<td>" + deptdetail[i].setupdate;			
				txt += "<td><a href='#' name='"+deptdetail[i].pID+"' onclick='f2(this)'>"+ratedata[i].rate+"</a>";

			}

			$("#datalist").html(txt);
		}


		
		
		function f2(obj) {
			pid = obj.name;
			$.ajax({
				url : "personGoalAchievementStatus.action?pid="+pid,
				type : "GET",
				success : function(Str) {
					persondetail = JSON.parse(Str);
					persondetailpagination(persondetail, nowpage);
				}
			});
			
		}

		function persondetailpagination(persondetail, nowpage) {
			$("#detaillist").html("");
			const datatotal = persondetail.length;
			const pagesTotal = Math.ceil(datatotal / perpage);
			let currentPage = nowpage;
			var minData = (currentPage * perpage) - perpage + 1;
			var maxData = (currentPage * perpage);
			//產生<a>標籤
			atag = "<a href=# name='1' onclick='f(this)'>" + 1 + "</a> ";
			for (let i = 2; i <= pagesTotal; i++) {
				atag += "<a href=# name='" + i + "' onclick='f(this)'>" + i
						+ "</a> ";
			}
			document.getElementById("tag").innerHTML = atag;
			$("#page").html("第" + nowpage + "頁");
			var txt = "<tr><th>工作<th>執行人員<th>狀態";

			if (maxData > datatotal) {
				maxData = datatotal;
			}
			for (let i = minData - 1; i < maxData; i++) {
				txt += "<tr><td>" + persondetail[i].work;
				txt += "<td>" + persondetail[i].name;
				txt += "<td>" + persondetail[i].status;
			}

			$("#detaillist").html(txt);
		}
		
	</script>
</body>
</html>