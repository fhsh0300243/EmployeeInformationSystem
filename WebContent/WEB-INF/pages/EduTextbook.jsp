<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
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
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">

						<div class="am-text-lg am-text-center"></div>


						<div class="introduce_wrap mt60">



<!-- header -->
  <div class="mark am-text-lg am-text-center">
    <span class="am-icon-chevron-left am-fl left-btn"></span>
    目前培訓名稱
    
  </div>

  <div class="textbook_wrap">
    <!-- banner -->
    <div class="am-container">
      <img src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg" class="am-img-responsive" width=100% alt="培訓圖片"/>
    </div>
    <!-- address -->
    <div class="am-cf my_address">
      <div class="am-text-sm am-fl col6">地點：本公司會議室A203</div>
      <button class="am-btn am-round am-btn-xs am-btn-primary am-fr">地圖查看</button>
    </div>
    <!-- 培訓訊息 -->
    <div class="am-container">
      <div>
        <div>
          <div class="am-panel am-panel-default">
            <div class="am-panel-bd">
              <div class='am-text-sm'>教材名稱：<span class='col6'>人資類相關教材</span></div>
                <div class='am-text-sm'>教材下載：<span><a href="#">點擊下載</a></span></div>
            </div>
          </div>
          <div class="am-panel am-panel-default">
            <div class="am-panel-bd">
              <div class='am-text-sm'>教材名稱：<span class='col6'>研發類相關教材</span></div>
                <div class='am-text-sm'>教材下載：<span><a href="#">點擊下載</a></span></div>
            </div>
          </div>
          <div class="am-panel am-panel-default">
            <div class="am-panel-bd">
              <div class='am-text-sm'>教材名稱：<span class='col6'>品質保證類相關教材</span></div>
                <div class='am-text-sm'>教材下載：<span><a href="#">點擊下載</a></span></div>
            </div>
          </div>
          <div class="am-panel am-panel-default">
            <div class="am-panel-bd">
              <div class='am-text-sm'>教材名稱：<span class='col6'>測試類相關教材</span></div>
                <div class='am-text-sm'>教材下載：<span><a href="#">點擊下載</a></span></div>
            </div>
          </div>
          <div class="am-panel am-panel-default">
            <div class="am-panel-bd">
              <div class='am-text-sm'>教材名稱：<span class='col6'>業務類相關教材</span></div>
                <div class='am-text-sm'>教材下載：<span><a href="#">點擊下載</a></span></div>
            </div>
          </div>
          <div class="am-panel am-panel-default">
            <div class="am-panel-bd">
              <div class='am-text-sm'>教材名稱：<span class='col6'>專案類相關教材</span></div>
                <div class='am-text-sm'>教材下載：<span><a href="#">點擊下載</a></span></div>
            </div>
          </div>
          


        </div>

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