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
<link rel="stylesheet" href="css/amazeui.min.css">
<link rel="stylesheet" href="css/common.css">

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
	margin-left:auto; 
    margin-right:auto;
}

.col-sm-4, .functionTitle {
	text-align: center;
}

.span1 {
	color: red;
}

.msgmap {
	color: red;
}

.div1 {
	width: 30%;
	margin: 0 auto;
	margin-left: 37%;
}
.userImg {
	width: 20%;
	height: 20%;
	border: 2px solid tan;
	border-radius: 15px;
}

.tb{
	margin-left:auto; 
    margin-right:auto;
}

form{
    position:relative;
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
		<span class=""></span> 新增培訓課程
    </div>

	<div class="basicInfo_wrap">
		<!-- banner -->
		<div class="am-container">
			<img src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg"
				class="am-img-responsive" width=100% alt="" />
		</div>
		<!-- address -->
		<div class="am-cf my_address">
		
		</div>
		<!-- 培訓課程 -->
		<div class="am-container">
			<div class="am-panel am-panel-primary">
				<div class="am-panel-hd">培訓課程</div>
				<div class="">
				<br/>
				<br/>
					<form class="for2" action="<c:url value="/addCourse"/>" method="post"
								enctype="multipart/form-data" >
								
								<table class="st1" id="idtable4" >
									<tr>
										<td><span class="span1">*</span>開課部門：</td>
										<td><select name="dept" id="dept"></select></td>
									</tr>
									
									<tr>
										<td><span class="span1">*</span>選擇職稱：</td>
										<td><select name="title" id="title"></select></td>
										<td class="msgmap">${msgmap.title}</td>
									</tr>
									
									<tr>
										<td>主管：</td>
										<td><select name="manager" id="manager"></select></td>
									</tr>
									
									
								<tr>
								<td class="tdtag"><span class="span1">*</span>開課時間：</td>
								<td><input type="date" id="idStartDate" name="startdate" required> 
								<select id=idSH name='selSH' required>
									<option value="">請選擇</option>
									<option value="9">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									</select>  點 
								
								 - <input type="date" id="idEndDate" name="enddate" required>
									<select id=idEH name='selEH' required>
								
								    <option value="">請選擇</option>
								    <option value="9">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
								    </select>  點
									
								</td>
							<tr>
								<td></td>
								<td class=sumHours></td>
							</tr>
							<tr>
								<td></td>
								<td class="tdErr"><img id="dateImg"> 
								<span id="dateCheck">${DateError}</span></td>
							</tr>
							<!--  -->
							<tr>
								<td class="st1"><span class="span1">*</span>培訓課程：</td>
							<td>
								<select>
									<option value="">請選擇</option>
									<option value="deptHR">新進人員職務說明與分析</option>
									<option value="deptHR">勞動契約過程之勞動條件</option>
									<option value="deptRD">產品研發流程管理與策略</option>
									<option value="deptQA">軟體測試與整合實務課程</option>
									<option value="deptTEST">測試程式穩定性及工程服務</option>
									<option value="deptSales">業務績效設定與管理辦法</option>
									<option value="deptPM">專案管理PMP企業內訓</option>
								</select>
							</td>
							
								</table>
								
								
								<br>
								
								<table class="tb">
									<tr>
										<td><input type="submit" value="送出" class="btn btn-info"> <input
											type="reset" value="清除" onclick="reset()" class="btn btn-info"></td>
										<td class="msgmap">${msgmap.status}</td>
									</tr>								
								</table>
								
							</form>
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
		var titles;
		var managers;
		var deptid = '${requestScope.inputmsg.department}';
		$('#dept').empty();
		$('#title').empty();
		$('#manager').empty();

		$.ajax({
			url : "DeptList",
			type : "GET",
			success : function(Str) {
				depts = JSON.parse(Str);
				var opt = $("<option>").val("").text("選擇部門")
				$("#dept").append(opt);
				for (let i = 0; i < depts.length; i++) {
					var opt = $("<option>").val(depts[i].deptid).text(
							depts[i].deptabb)
					$("#dept").append(opt);
				}
				$("#dept").val('${requestScope.inputmsg.department}');
			}
		});

		$.ajax({
			url : "TitleList?deptid=" + deptid,
			type : "GET",
			success : function(Str) {
				$('#title').empty();
				titles = JSON.parse(Str);
				$('#title').append(
						$("<option></option>").attr("value", "").text("選擇職稱"));
				for (let i = 0; i < titles.length; i++) {
					$("#title").append(
							$("<option></option>").attr("value",
									titles[i].titleid).text(
									titles[i].titlechname));
				}
				$("#title").val('${requestScope.inputmsg.title}');
			}
		});

		function titleList(deptid) {
			$('#title').empty();
			$.ajax({
				url : "TitleList?deptid=" + deptid,
				type : "GET",
				success : function(Str) {
					titles = JSON.parse(Str);
					$('#title').append(
							$("<option></option>").attr("value", "").text(
									"選擇職稱"));
					for (let i = 0; i < titles.length; i++) {
						$("#title").append(
								$("<option></option>").attr("value",
										titles[i].titleid).text(
										titles[i].titlechname));
					}
				}
			});
		}

		$(document).on('change', '#dept', function() {
			deptid = this.value;
			titleList(deptid);
			managerList();
		});

		$.ajax({
			url : "ManagerList?deptid=" + deptid,
			type : "GET",
			success : function(Str) {
				$('#manager').empty();
				managers = JSON.parse(Str);
				$('#manager').append(
						$("<option></option>").attr("value", "").text("選擇主管"));
				for (let i = 0; i < managers.length; i++) {
					$("#manager").append(
							$("<option></option>").attr("value",
									managers[i].empid).text(managers[i].name));
				}
				$("#manager").val('${requestScope.inputmsg.manager}');
			}
		});

		function managerList() {
			$('#manager').empty();
			$.ajax({
				url : "ManagerList?deptid=" + deptid,
				type : "GET",
				success : function(Str) {
					managers = JSON.parse(Str);
					$('#manager').append(
							$("<option></option>").attr("value", "").text(
									"選擇主管"));
					for (let i = 0; i < managers.length; i++) {
						$("#manager").append(
								$("<option></option>").attr("value",
										managers[i].empid).text(
										managers[i].name));
					}
				}
			});
		}

		if (document.getElementById('m1').value == '${requestScope.inputmsg.gender}') {
			document.getElementById('m1').checked = true;
		}
		if (document.getElementById('m2').value == '${requestScope.inputmsg.gender}') {
			document.getElementById('m2').checked = true;
		}

		function reset() {
			document.getElementById('account1').value = "";
			document.getElementById('pwd1').value = "";
			document.getElementById('name1').value = "";
			if (document.getElementById('m1').checked) {
				document.getElementById('m1').checked = false;
			}
			if (document.getElementById('m2').checked) {
				document.getElementById('m2').checked = false;
			}
		}
	</script>
	
	<script>
		$("#idStartDate, #idSH, #idSM, #idEndDate, #idEH, #idEM")
				.change(
						function() {
							$("#dateImg").attr("src", "");
							$("#dateCheck").empty();
							$(".sumHours").empty();

							var startD = $("#idStartDate").val();
							var startH = $("#idSH").val();
							var startM = $("#idSM").val();
							var endD = $("#idEndDate").val();
							var endH = $("#idEH").val();
							var endM = $("#idEM").val();
							if ((startH == 12 && startM == 30)
									|| (endH == 12 && endM == 30)
									|| (endH == 8 && endM.length == 1 && endM == 0)
									|| (endH == 17 && endM == 30)) {
								if ((startH == 12 && startM == 30)
										|| (endH == 12 && endM == 30)) {
									$("#dateImg").attr("src",
											"images/X_icon.png");
									$("#dateCheck").append("12:30為休息時間。");
								}
								if (endH == 8 && endM.length == 1 && endM == 0) {
									$("#dateImg").attr("src",
											"images/X_icon.png");
									$("#dateCheck").append("結束時間不能選取8:00。");
								}
								if (endH == 17 && endM == 30) {
									$("#dateImg").attr("src",
											"images/X_icon.png");
									$("#dateCheck").append("17:30為休息時間。");
								}
							} else {
								if (startD != null && startD.length != 0
										&& startH != null && startH.length != 0
										&& startM != null && startM.length != 0
										&& endD != null && endD.length != 0
										&& endH != null && endH.length != 0
										&& endM != null && endM.length != 0) {

									startH = parseInt(startH);
									startM = parseInt(startM);
									endH = parseInt(endH);
									endM = parseInt(endM);
									if (startD < endD
											|| (startD == endD && (startH < endH || (startH == endH && startM < endM)))) {
										$
												.ajax({
													url : "changeDHM",
													data : {
														leaveType : $("#idLT")
																.val(),
														startdate : startD,
														selSH : startH,
														selSM : startM,
														enddate : endD,
														selEH : endH,
														selEM : endM
													},
													type : "POST",
													dataType : "json",
													success : function(data) {
														$(".sumHours")
																.text(
																		data[0]["sumHours"]);
														$("#dateCheck")
																.append(
																		data[0]["sumHoursError"]);
														$("#dateCheck")
																.append(
																		data[0]["dateError"]);
													}
												})
									} else {
										$("#dateImg").attr("src",
												"images/X_icon.png");
										$("#dateCheck").append("結束時間必須大於開始時間");
									}
								}
							}
						});

		$("#idLT").change(function() {

			$("#leaveTypeImg").attr("src", "");
			$("#leaveTypeCheck").empty();

			if ($("#idLT").val() != null && $("#idLT").val().length != 0) {
				$.ajax({
					url : "changeLT",
					data : {
						leaveType : $("#idLT").val()
					},
					type : "POST",
					success : function(data) {
						$(".surplusHours").text(data);
					}
				})
			}
			$(".surplusHours").empty();
		});

		function cls() {
			
			$(".surplusHours").empty();
			$(".sumHours").empty();
			$("#dateCheck").empty();
			$("#causeCheck").empty();
			$("#attImg").attr("src", "");
			$("#attCheck").empty();
		}

		
		
	</script>
</body>
</html>