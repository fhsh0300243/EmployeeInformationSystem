<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>番茄科技 教育訓練</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<link rel="stylesheet" type="text/css" href="css/SearchPage.css">
<link rel="stylesheet" type="text/css" href="css/Menu.css">
<link rel="icon" href="images/favicon.ico">
<style>
.col-sm-4, .functionTitle {
	text-align: center;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}
.pInLeft{
	margin:0;
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
					<p class="pInLeft">
						<b>Hi~</b> ${usersResultMap.Title},
					<p class="pInLeft">${ LoginOK.employee.name} (${usersResultMap.UserName})您好~
					<p class="pInLeft">歡迎登入番茄科技員工資訊系統
				</div>

				<%@ include file="SubFeatureForEDU.jsp"%>

			</div>

			<!--右邊欄位-->
			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">教育訓練</p>
					<div class="panel-heading">
					
					<%@ include file="MainFeatureTopBar.jsp"%></div>

					<h6>${SignError}</h6>
					<h4>教育訓練課程</h4>
					<table id="idtable1">
						<tr>
							<th>申請時間</th>
							<th>所屬部門</th>
							<th>職位</th>
							<th>申請人員</th>
							<th>課程類別</th>
							<th>開始時間</th>
							<th>結束時間</th>
							<th>簽核狀態</th>
							<th>備註</th>
						</tr>
						<c:forEach var='signDetail' items='${SignList}' varStatus='vs'>
							<tr class='classtr1'>
								<td>${signDetail.createTime}</td>
								<td>${signDetail.employeeId.empDept.deptAbb}</td>
								<td>${signDetail.employeeId.empTitle.titleChName}</td>
								<td>${signDetail.employeeId.name}</td>
								<td>${signDetail.courseType}</td>
								<td>${signDetail.startTime}</td>
								<td>${signDetail.endTime}</td>
								<td>${signDetail.signingProgress}</td>
								<td>
									<button class="btn btn-info" name="${signDetail.applyId}">進入簽核</button>
								</td>
							</tr>
						</c:forEach>
					</table>

					<div class="list_footer">
						<div id="tag"></div>
						<div id="page"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="CanNotRightDownDiv">
		<img class="CanNotRightDown" src="images/CompanyLogo.png">
	</div>


	<script src="js/jquery-3.4.1.min.js"></script>
	<script>
		$(function() {
			$("td:empty").text("-");
		})
		$(".btn-info").click(function() {
			var applyId = $(this).attr("name");
			location.href = "presigningpage?applyId=" + applyId;
		})
	</script>
	
	<script>
				var depts;
				$('#searchdept').empty();
				$.ajax({
					url : "DeptList",
					type : "GET",
					success : function(Str) {
						depts = JSON.parse(Str);
						$('#searchdept').append(
								"<option value=''>選擇部門</option>");
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
					atag = "<a href=# name='1' onclick='f(this)'>" + 1
							+ "</a> ";
					for (let i = 2; i <= pagesTotal; i++) {
						atag += "<a href=# name='" + i + "' onclick='f(this)'>"
								+ i + "</a> ";
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
						txt += "<td><a href='<c:url value='/EditEmployee.do?id="
								+ emps[i].empID
								+ "'/>' name='"
								+ emps[i].empID
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
				$("#search").click(
						function() {
							nowpage = 1;
							$.ajax({
								url : "QueryEmp.action?searchid="
										+ $("#searchid").val() + "&searchname="
										+ $("#searchname").val()
										+ "&searchdept="
										+ $("#searchdept").val(),
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