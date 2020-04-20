<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部門管理</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
* {
	margin: 0;
	padding: 0;
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

a {
	text-decoration: none;
}
</style>
</head>
<body>
	<!--  <select name="searchdept" id="searchdept"></select>
	<input type="button" id="search" name="search" value="搜尋">
	-->
	<p>${msg[0]}</p>
	<table border="1" id="deptlist"></table>
	<div>
		<a href="#" id="add">Add</a>
	</div>
	<div class="list_footer">
		<div id="tag"></div>
		<div id="page"></div>
	</div>
	<div>
		<a href="<c:url value="/logout/toLoginPage"/>">LOGOUT</a>
	</div>

	<script>
		var depts;
		const perpage = 10;
		let nowpage = 1;

		showdepts();

		//從資料庫取得資料
		function showdepts() {
			$.ajax({
				url : "DeptList",
				type : "GET",
				success : function(Str) {

					depts = JSON.parse(Str);

					pagination(depts, nowpage);

				}
			});
		}

		//產生顯示的資料
		function pagination(depts, nowpage) {

			$("#deptlist").html("");

			const datatotal = depts.length;
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

			var txt = "<tr><th>DeptID<th>部門名稱<th>部門簡稱<th>";

			if (maxData > datatotal) {
				maxData = datatotal;

			}
			for (let i = minData - 1; i < maxData; i++) {
				txt += "<tr><td>" + depts[i].deptid;
				txt += "<td>" + depts[i].deptname;
				txt += "<td>" + depts[i].deptabb;
				txt += "<td><a href='<c:url value='/xxxx.do?id="
						+ depts[i].deptID + "'/>' name='" + depts[i].deptID
						+ "'>Edit</a>";

			}

			$("#deptlist").html(txt);

		}

		//換頁		
		function f(obj) {
			nowpage = obj.name;
			$("#page").html("第" + nowpage + "頁");
			showemps();
		}

		$("#add").click(function(e) {
							var txt = "<tr><td><td><input type='text' id='deptname' name='deptname' autocomplete='off'><td><input type='text' id='deptabb' name='deptabb' autocomplete='off'><td><a id='ok' href='#'>ok </a><a id='cancel' href='#''>cancel</a>";
							$("#deptlist").append(txt);
						});
		$(document).on('click', '#ok', function() {
			$.ajax({
				url : "AddDepartment.action?deptname="+ $("#deptname").val()+"&deptabb="+ $("#deptabb").val(),
				type : "GET",
				success : function(Str) {
					showdepts();
					console.log("ok")
				}
			});
		})
/*
		$("#ok").click(function(e) {
			$.ajax({
				url : "AddDepartment.action",
				type : "GET",
				success : function(Str) {
					showdepts();
					console.log("ok")
				}
			});
		});
*/


	$(document).on('click', '#cancel', function() {
		showdepts();
		console.log("cancel")
	})

	/*
		$("#cancel").click(function(e) {
			showdepts();
			console.log("cancel")
		});
*/


		/*
		$("#search").click(function() {
			nowpage=1;
			$.ajax({
				url : "xxxx.action?searchdept="+ $("#searchdept").val(),
				type : "GET",
				success : function(Str) {

					depts = JSON.parse(Str);

					pagination(depts, nowpage);

				}
			});
		});
		 */
	</script>
</body>
</html>