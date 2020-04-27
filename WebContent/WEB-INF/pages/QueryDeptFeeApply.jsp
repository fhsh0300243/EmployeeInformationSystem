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
						<table border="1" id="detaillist" style="margin: 0 auto"></table>
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
		var detail;
		const perpage = 10;
		let nowpage = 1;
		showdata();
		//從資料庫取得資料
		function showdata() {
			$.ajax({
				url : "DeptCostPercent.action",
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


			var txt = "<tr><th>部門<th>本月花費百分比<th>本季花費百分比<th>";

			if (maxData > datatotal) {
				maxData = datatotal;
			}
			for (let i = minData - 1; i < maxData; i++) {
				txt += "<tr><td>" + "HR";
				txt += "<td><a href='#' name='mHR' onclick='f(this)'>" + data[i].mHRcost+"</a>";
				txt += "<td><a href='#' name='sHR' onclick='f(this)'>" + data[i].sHRcost+"</a>";
				txt += "<tr><td>" + "RD";
				txt += "<td><a href='#' name='mRD' onclick='f(this)'>" + data[i].mRDcost+"</a>";
				txt += "<td><a href='#' name='sRD' onclick='f(this)'>" + data[i].sRDcost+"</a>";
				txt += "<tr><td>" + "QA";
				txt += "<td><a href='#' name='mQA' onclick='f(this)'>" + data[i].mQAcost+"</a>";
				txt += "<td><a href='#' name='sQA' onclick='f(this)'>" + data[i].sQAcost+"</a>";
				txt += "<tr><td>" + "Sales";
				txt += "<td><a href='#' name='mSl' onclick='f(this)'>" + data[i].mSalescost+"</a>";
				txt += "<td><a href='#' name='sSl' onclick='f(this)'>" + data[i].sSalescost+"</a>";
				txt += "<tr><td>" + "PM";
				txt += "<td><a href='#' name='mPM' onclick='f(this)'>" + data[i].mPMcost+"</a>";
				txt += "<td><a href='#' name='sPM' onclick='f(this)'>" + data[i].sPMcost+"</a>";

			}

			$("#datalist").html(txt);
		}
		
		function f(obj) {
			type = obj.name;
			console.log(type);
			$.ajax({
				url : "DeptCostDetail.action?type="+type,
				//url : "DeptCostPercent.action",
				type : "GET",
				success : function(Str) {
					detail = JSON.parse(Str);
					detailpagination(detail, nowpage);
				}
			});
			
		}
		function detailpagination(detail, nowpage) {
			$("#detaillist").html("");
			const datatotal = detail.length;
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
			var txt = "<tr><th>EmpID<th>申請人<th>類別<th>發票時間<th>發票號碼<th>申請時間<th>備註<th>金額<th>";

			if (maxData > datatotal) {
				maxData = datatotal;
			}
			for (let i = minData - 1; i < maxData; i++) {
				txt += "<tr><td>" + detail[i].empID;
				txt += "<td>" + detail[i].name;
				txt += "<td>" + detail[i].appItem;
				txt += "<td>" + detail[i].invoiceTime;
				txt += "<td>" + detail[i].invoiceNb;
				txt += "<td>" + detail[i].appTime;
				txt += "<td>" + detail[i].remark;
				txt += "<td>" + detail[i].appMoney;

			}
			$("#detaillist").html(txt);
		}

	</script>
</body>
</html>