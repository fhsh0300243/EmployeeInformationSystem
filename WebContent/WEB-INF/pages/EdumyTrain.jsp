<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport"
        content="width=device-width, initial-scale=1">
  <title>番茄科技 教育訓練</title>
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="icon" type="images/png" href="images/CompanyLogo.png">
<link rel="stylesheet" type="text/css" href="css/amazeui.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/mainCSS.css">


<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

<script src="js/jquery.min.js"></script>
<script src="js/handlebars.min.js"></script>
<script src="js/amazeui.widgets.helper.min.js"></script>

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

				<%@ include file="SubFeatureForEDU.jsp"%>

			</div>

			<!--右邊欄位-->
			<div class="col-sm-8">
				<div class="panel panel-primary">
					<p class="functionTitle">教育訓練</p>
					<div class="panel-heading">
				<%@ include file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">

						<div class="am-text-lg am-text-center"></div>


						<div class="introduce_wrap mt60">



  <!-- header -->
  <div class="mark am-text-lg am-text-center">
    <span class=""></span>
    培訓課程
    
  </div>

  <div class="myTrain_wrap mt60">

    <!-- list -->
    <div class="myTrain-list am-cf">
      <div class="am-fl titleIcon">
        <img class="am-radius" src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSCWNRtSCuC4u_wA8qJzHVNbWuyazKuO_J13i4oOwYo5dr7X6wo&usqp=CAU" width="85px"/>
      </div>
      <div class="am-fl content">
        <div class='am-text-sm'>課程名稱：新進人員職務說明與分析</div>
        <div class='am-text-sm'>課程類別：員工訓練<br/><span class='am-text-xs'>人資類</span></div>
        <div class='am-text-sm'>課程日期<span class='am-text-xs'>2020/07/01-2020/07/01</span></div>
      </div>
      <div class="am-fr statusIcon">
        <button class="am-radius" type="button" title="我要報名" onclick=""><img class="am-circle" src="images/baoming.png" width="50px"/></button>
      </div>
    </div>
    <div class="myTrain-list am-cf">
      <div class="am-fl titleIcon">
        <img class="am-radius" src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTCTQEEeGi_OKa3jlEOA7tZNRIcvgwcCiZG6TMEam09GvY4SyOx&usqp=CAU" width="80px"/>
      </div>
      <div class="am-fl content">
        <div class='am-text-sm'>課程名稱：勞動契約過程之勞動條件</div>
        <div class='am-text-sm'>課程類別：勞動法規<br/><span class='am-text-xs'>人資類</span></div>
        <div class='am-text-sm'>課程日期<span class='am-text-xs'>2020/07/02-2020/07/02</span></div>
      </div>
      <div class="am-fr statusIcon">
        <img class="am-circle" src="images/baoming.png" width="50px"/>
      </div>
    </div>
    <div class="myTrain-list am-cf">
      <div class="am-fl titleIcon">
        <img class="am-radius" src="https://ya-webdesign.com/transparent250_/training-vector-12.png" width="80px"/>
      </div>
      <div class="am-fl content">
        <div class='am-text-sm'>課程名稱：業務目標設定與管理辦法</div>
        <div class='am-text-sm'>課程類別：業務目標<br/><span class='am-text-xs'>業務類</span></div>
        <div class='am-text-sm'>課程日期<span class='am-text-xs'>2020/08/01-2020/08/01</span></div>
      </div>
      <div class="am-fr statusIcon">
       <img class="am-circle" src="images/baoming.png" width="50px"/>
      </div>
    </div>
  </div>
<<<<<<< HEAD
     </div>
    </div>
  </div>
     </div>
    </div>
=======
  </div>
  </div>
  </div>
  </div>
>>>>>>> 2b2c0369ce6a79db95ac0dc8d09065a6ac454e3b
  </div>
  

<script src="js/amazeui.min.js"></script>

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