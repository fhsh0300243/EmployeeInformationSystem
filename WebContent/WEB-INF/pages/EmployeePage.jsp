<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工管理</title>
<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>


<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

<style>
.well, .panel {
	text-align: center;
}

body {
	font-family: 微軟正黑體;
}

p {
	margin-bottom: 10px;
}

table {
	margin: 20px;
	border-collapse: collapse;
}


</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">

			<!--左邊欄位-->
			<div class="col-sm-4">
				<div class="well">Hi ${usersResultMap.UserName} 您好~</div>
				<div class="panel panel-primary">
					<div class="panel-heading">主選單</div>
					<div class="panel-body">

						<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
							src="images/left1.png" border="0"></a> <a
							href="<c:url value="/xxx.do"/>"><img class="imgButton"
							src="images/left2.png" border="0"></a> <a
							href="<c:url value="/xxx.do"/>"><img class="imgButton"
							src="images/left3.png" border="0"></a> <a
							href="<c:url value="/xxx.do"/>"><img class="imgButton"
							src="images/left4.png" border="0"></a> <a
							href="<c:url value="/xxx.do"/>"><img class="imgButton"
							src="images/left5.png" border="0"></a> <a
							href="<c:url value="/EmployeePage.do"/>"><img
							class="imgButton" src="images/left6.png" border="0"></a> <a
							href="<c:url value="/xxx.do"/>"><img class="imgButton bbs"
							src="images/left7.png" border="0"></a> <a
							href="<c:url value="/toLoginPage"/>"><img
							class="imgButton logout" src="images/logout.png" border="0"></a>
					</div>
					<div class="panel-footer"></div>
				</div>




			</div>

			<!--右邊欄位-->
			<div class="col-sm-8">
				<div class="panel panel-primary">
					<div class="panel-heading">員工管理</div>
					<div class="panel-body">
						<label for="" class="t1">員工Id：</label><input type="text"
							id="searchid" name="searchid" size="30"><br /> <label
							for="" class="t1">員工姓名：</label><input type="text" id="searchname"
							name="searchname" size="30"><br /> <select
							name="searchdept" id="searchdept"></select> <input type="button"
							id="search" name="search" value="搜尋">
						<p>${msg[0]}</p>
						<table border="1" id="emplist"></table>
						<div class="list_footer">
							<div id="tag"></div>
							<div id="page"></div>
						</div>
						<div>
							<a href="<c:url value="/AddEmployee.do"/>">新增員工</a>
						</div>
					</div>
				</div>
			</div>

		</div>
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
		showemps();
		//從資料庫取得資料
		function showemps() {
			$.ajax({
				url : "EmpList",
				type : "GET",
				success : function(Str) {
					emps = JSON.parse(Str);
					pagination(emps, nowpage);
				}
			});
		}
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
			var txt = "<tr><th>EmpID<th>帳號<th>姓名<th>部門<th>職稱<th>主管<th>";
			
			if (maxData > datatotal) {
				maxData = datatotal;
			}
			for (let i = minData - 1; i < maxData; i++) {
				txt += "<tr><td>" + emps[i].empID;
				txt += "<td>" + emps[i].username;
				txt += "<td>" + emps[i].name;
				txt += "<td>" + emps[i].department;
				txt += "<td>" + emps[i].title;
				txt += "<td>" + emps[i].manager;
				txt += "<td><a href='<c:url value='/EditEmployee.do?id="+emps[i].empID+"'/>' name='" + emps[i].empID
						+ "'>Edit</a>";
	
			}
			
			$("#emplist").html(txt);
		}
		
		//換頁		
		function f(obj) {
			nowpage = obj.name;
			$("#page").html("第" + nowpage + "頁");
			showemps();
		}
/*
		$("#search").keydown(function(e) {
			code = (e.keyCode ? e.keyCode : e.which);
			if (code == 13) {
				//startSearch();
			}
		});
*/
		$("#search").click(function() {
			nowpage=1;
			$.ajax({
				url : "QueryEmp.action?searchid="+ $("#searchid").val()+"&searchname="+ $("#searchname").val()+"&searchdept="+ $("#searchdept").val(),
				type : "GET",
				success : function(Str) {
					emps = JSON.parse(Str);
					pagination(emps, nowpage);
				}
			});
		});
		
	</script>
</body>
</html>