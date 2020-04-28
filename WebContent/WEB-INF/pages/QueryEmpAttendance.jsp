<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工出勤資料</title>

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
.datepicker{
    border:1px  solid #ccc;
    border-radius: 4px;
    height: 24px;
    line-height:24px;
    margin:3px;    
} 
.datepicker:focus{
    outline:0 none;
    border:1px solid #1abc9c;
        
}
</style>
</head>
<body>
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
					<p class="functionTitle">員工出勤查詢</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<label for="" class="t1">員工Id：</label><input type="text"
							id="searchid" name="searchid" size="30" class="datepicker"><br /> <label
							for="" class="t1">員工姓名：</label><input type="text" id="searchname"
							name="searchname" size="30" class="datepicker"><br /> <select
							name="searchdept" id="searchdept" class="datepicker"></select><br /> <label for="" class="t1">起訖日:</label><input
							type="date" name="startdate" id="startdate" class="datepicker">~<input
							type="date" name="enddate" id="enddate" class="datepicker"><br /> <input
							type="button" id="search" name="search" value="搜尋" class="btn btn-info">
						<p>${msgmap.noinputmsg}</p>
						<table id="emplist" class="idtable1" style="margin: 0 auto"></table>
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
		var depts;
		$('#searchdept').empty();
		$.ajax({
			url : "DeptList",
			type : "GET",
			success : function(Str) {
				depts = JSON.parse(Str);
				$('#searchdept').append("<option value=''>選擇部門</option>");
				for (let i = 0; i < depts.length; i++) {
					$('#searchdept').append(
							"<option value='"+depts[i].deptabb+"'>"
									+ depts[i].deptabb + "</option>")
				}
			}
		});

		var emps;
		const perpage = 10;
		let nowpage = 1;

		//產生顯示的資料
		function pagination(emps, nowpage) {
			$("#emplist").html("");
			const datatotal = emps.length;
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
			var txt = "<tr><th>EmpID<th>姓名<th>部門<th>日期<th>上班<th>下班<th>status";

			if (maxData > datatotal) {
				maxData = datatotal;
			}
			for (let i = minData - 1; i < maxData; i++) {
				txt += "<tr><td>" + emps[i].empID;
				txt += "<td>" + emps[i].name;
				txt += "<td>" + emps[i].department;
				txt += "<td>" + emps[i].date;
				txt += "<td>" + emps[i].starttime;
				txt += "<td>" + emps[i].endtime;
				txt += "<td>" + emps[i].status;
			}

			$("#emplist").html(txt);
		}

		//換頁		
		function f(obj) {
			nowpage = obj.name;
			$("#page").html("第" + nowpage + "頁");
			showemps();
		}
		
		$("#search").click(
				function() {
					nowpage = 1;
					$.ajax({
						url : "QueryEmpAttdenance.action?searchid="
								+ $("#searchid").val() + "&searchname="
								+ $("#searchname").val() + "&searchdept="
								+ $("#searchdept").val()
								+ "&startdate="+$("#startdate").val()
								+ "&enddate="+$("#enddate").val(),
						type : "GET",
						traditional: true,
						success : function(Str) {
							emps = JSON.parse(Str);
							pagination(emps, nowpage);
						}
					});
				});
	</script>
</body>
</html>