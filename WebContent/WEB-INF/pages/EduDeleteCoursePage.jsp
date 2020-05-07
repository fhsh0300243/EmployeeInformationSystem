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


<script type="text/javascript" src="/jquery/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  $("button").click(function(){
    $("iframe").slideToggle();
  });
});

</script>

<script>
function myFunction(){ 
	if(confirm("您確定要取消課程嗎？"))
	;
}
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
					<div class="panel-heading">
					<%@ include file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">

						<div class="am-text-lg am-text-center"></div>


						<div class="introduce_wrap mt60">


	<!-- header -->
  <br/>
  <div class="mark am-text-lg am-text-center">
    <span class="" ><img src="images/book.png" width=2.5%></span>
                        &nbsp;取消培訓課程
                        </div> 
  </div>

	<div class="basicInfo_wrap">
		<!-- banner -->
		<div class="am-container">
			<img src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg"
				class="am-img-responsive" width=100% alt="" />
		</div>
		<!-- address -->
							<div class="am-cf my_address">
								<div class="am-text-sm am-fl col6">地點：中區訓練中心 會議室A203</div>
							     
								<div id="demo" style="visibility:;"> 
								<!-- <iframe id="demo" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d394.6255503842566!2d120.65072081200775!3d24.15056066749464!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x34693d964fff6dc5%3A0x5d526ce61b5b9ca6!2zNDA45Y-w5Lit5biC5Y2X5bGv5Y2A5YWs55uK6Lev5LqM5q61NTHomZ8!5e0!3m2!1szh-TW!2stw!4v1588560237251!5m2!1szh-TW!2stw" aria-hidden="flase" tabindex="0"></iframe> -->
								</div>
								
								<div>
								<button id="btn1" type="button" class="am-btn am-round am-btn-xs am-btn-primary am-fr col3">地圖查看</button>
							    </div>
							</div>
		<!-- 報名按鈕 -->
							<div>
							<div class="am-container am-margin-bottom-sm">
								 <!-- <button type="button" 
								         class="am-btn am-btn-primary am-btn-block baoming"
								         style="display:none;"
								         >點我報名</button>  -->
							
								<button type="button"
									class="am-btn am-btn-warning am-btn-block baoming am-margin-top-0"
									onclick="location.href='http://localhost:8080/EmployeeInformationSystem/EduDeleteCoursePage2.do'"
										
									style='display: show'>取消報名</button>
							</div>
							</div>
							<!-- tabs -->
							<div data-am-widget="tabs"
								class="am-tabs am-tabs-d2 am-container" style="margin: 0 auto">
								<ul class="am-tabs-nav am-cf">
									<li class=""><a href="">課程介绍</a></li>
					

								</ul>
						
								<div class="am-tabs-bd">
									<div data-tab-panel-0 class="am-tab-panel am-active">
										<!-- 培訓訊息 -->
										<div>
											<div class="am-panel am-panel-default">
												<div class="am-panel-hd">培訓訊息</div>
												<div class="am-panel-bd col9">
													<div class='am-text-sm'>
														報到時間：<span>2020/05/11 07:50</span>
													</div>
													<div class='am-text-sm'>
														上課時間：<span>08:00-17:00</span>
													</div>
													<div class='am-text-sm'>
														培訓課程日期：<span>2020/05/11-2020/05/12</span>
													</div>
													<div class='am-text-sm'>
														人數上限：<span>40人</span>
													</div>
													<div class='am-text-sm'>
														培訓課程類別：<span>人資相關(HR)</span>
													</div>
													<div class='am-text-sm'>
														培訓分類：<span>新進人員職前訓練</span>
													</div>
													<div class='am-text-sm'>
														課程講師：<span>林老師</span>
													</div>
												</div>
												<hr />
												<div class="am-container">
													<p>
														<p><p/>
														<img
															src="images/empcourse.png"
														    style="position:relative;float:left"
															width='9.5%' alt="" /> 
														新進人員可在最短時間了解公司經營理念與企業文化，縮短進入新環境之適應期，並加速工作無經驗者之學習曲線。
														<br/>
														<hr/>
														<br/>
														
														1.新進人員職前訓練：新人入職三個月內安排新人教育訓練課程，訓練內容包含公司文化及組織介紹、作業系統說明、
														品質政策及品質管理系統介紹。同時由用人單位主管安排完整的新人職前教育訓練計劃，以協助新進人員迅速瞭解企業文化、適應環境熟悉未來工作職掌。
														<br/>
														<br/>
														<hr/>
														<br/>
														
														<img
															src="images/empcourse2.png"
														    style="position:relative;float:left"
															width='10%' alt="" /> 
														2.專業訓練：
														經由主管或資深員工依自身經驗傳承、講授專業知識、技能之訓練，藉以強化員工專業知能、提升現有工作生產力與效率。
														<br/>2-1.外部訓練：提供資源，開放有進修需求之員工至專業訓練機構受訓，藉以深化專業知識與技能。
														<br/>同時，番茄科技鼓勵員工積極進行在職進修，以持續學習新知、提升自我競爭力。
														<br/>2-2.證照派訓：依據法規要求，由職務專責人員參加專業證照訓練課程。
														<br/>
														<br/>
													<p>

												</div>
											</div>

										</div>
									</div>

									<div data-tab-panel-1 class="am-tab-panel ">
									
								
										

				</div>
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
				txt += "<td><a href='<c:url value='/EditEmployee.do?id="
						+ emps[i].empID + "'/>' name='" + emps[i].empID
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
								+ $("#searchname").val() + "&searchdept="
								+ $("#searchdept").val(),
						type : "GET",
						success : function(Str) {
							emps = JSON.parse(Str);
							pagination(emps, nowpage);
						}
					});
				});
	</script>
	
	<script>
	$(".baoming").click(function(){
		$(this).hide().siblings('button').show()
	})
</script>

<script type="text/javascript">
$("iframe").hide();
$("").slide();
</script>


</body>
</html>