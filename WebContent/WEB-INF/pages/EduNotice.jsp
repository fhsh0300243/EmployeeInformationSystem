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
<meta name="viewport" content="width=device-width, initial-scale=1">
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

<script type="text/javascript" src="/jquery/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  $("button").click(function(){
    $("iframe").slideToggle();
  });
});

</script>

<script type="text/javascript">
$("iframe").hide();

</script>


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

/* iframe */
#parent {
  width: 200px;
  height: 200px;
  border:solid 1px #87ceeb;
}

iframe {
  width:75%;
  height:100%;
  border:0px;
  position:relative;
  float:inherit;
  display:none;
}

#parent {
  opacity: 0.3;
  position:relative;
  float: right;
  right:10px;
  bottom:35px;
  transition: 0.5s;
}

#parent {
  opacity: 1;
}  

#demo{
  display:;
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
					
					<div class="panel-body">

						<div class="am-text-lg am-text-center"></div>


						<div class="introduce_wrap mt60">




<!-- header -->
  <div class="mark am-text-lg am-text-center">
    <span class=""></span>
    課程訊息通知
    
  </div>

  <div class="notice_wrap">
    <!-- banner -->
    <div class="am-container">
      <img src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg" class="am-img-responsive" width=100% alt=""/>
    </div>
    <!-- address -->
    <div class="am-cf my_address">
	<div class="am-text-sm am-fl col6">地點：中區訓練中心 會議室A203</div>
							     
	<div id="demo" style="visibility:;"> 
	<iframe id="demo" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d394.6255503842566!2d120.65072081200775!3d24.15056066749464!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x34693d964fff6dc5%3A0x5d526ce61b5b9ca6!2zNDA45Y-w5Lit5biC5Y2X5bGv5Y2A5YWs55uK6Lev5LqM5q61NTHomZ8!5e0!3m2!1szh-TW!2stw!4v1588560237251!5m2!1szh-TW!2stw" aria-hidden="flase" tabindex="0"></iframe>
	</div>
								
	<div>
	<button id="btn1" type="button" class="am-btn am-round am-btn-xs am-btn-primary am-fr col3">地圖查看</button>
	</div>
	</div>
    <!-- 培訓訊息 -->
    <div class="am-container">

           <div class="notice-list am-g">
              <div class="am-fl  am-u-sm-3">
                <img class="am-radius" src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSCWNRtSCuC4u_wA8qJzHVNbWuyazKuO_J13i4oOwYo5dr7X6wo&usqp=CAU" width="50%"/>
              </div>
              <div class="am-fl  am-u-sm-9">
                <h6 class='am-text-sm'>培訓通知</h6>
                <div class='am-text-sm col9'>新進人員職務說明與分析</div>
              </div>
            </div>

            <div class="notice-list am-g">
              <div class="am-fl  am-u-sm-3">
                <img class="am-radius" src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTCTQEEeGi_OKa3jlEOA7tZNRIcvgwcCiZG6TMEam09GvY4SyOx&usqp=CAU" width="50%"/>
              </div>
              <div class="am-fl  am-u-sm-9">
                <h6 class='am-text-sm'>培訓通知</h6>
                <div class='am-text-sm col9'>勞動契約過程之勞動條件</div>
              </div>
            </div>

            <div class="notice-list am-g">
              <div class="am-fl  am-u-sm-3">
                <img class="am-radius" src="https://cdn1.iconfinder.com/data/icons/engineering-vol-2/100/Engineering-02-512.png" width="50%"/>
              </div>
              <div class="am-fl  am-u-sm-9">
                <h6 class='am-text-sm'>培訓通知</h6>
                <div class='am-text-sm col9'>產品開發的效率化</div>
              </div>
            </div>
            </div>
            </div>
            </div>
            </div>
            </div>
            </div>
            
            
            

      
    </div>
    </div>
  </div>
  </div>
  </div>
  </div>
  </div>
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