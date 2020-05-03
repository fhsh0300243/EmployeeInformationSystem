<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>番茄科技 員工管理</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<link rel="icon" href="images/favicon.ico">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
/*
    * {
        margin: 0;
        padding: 0;
    }
*/
body {
	font-family: 微軟正黑體;
}

.span1 {
	color: red;
}

.div1 {
	width: 100%;
	/*margin: 0 auto;
	margin-left: 37%;*/
}

.well, .panel {
	text-align: center;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}

.datepicker {
	border: 1px solid #ccc;
	border-radius: 4px;
	height: 24px;
	line-height: 24px;
	margin: 3px;
	width: 250px;
}

.datepicker:focus {
	outline: 0 none;
	border: 1px solid #1abc9c;
}

b {
	font-size: 20px;
}

.tb {
	position: absolute;
	bottom: 30px;
	right: 40%;
}

.idtable1 {
	position: relative;
	margin: 50px auto;
	border-collapse: collapse;
	float: left;
	left: 100px;
}

.idtable1 tr {
	text-align: right;
}

.idtable1 td {
	/*border-bottom: 1px solid #ddd;*/
	padding: 10px;
}

.msgmap {
	/*border-bottom: 1px solid #ddd;*/
	padding: 0px;
	color: red;
}

.idtable2 {
	position: absolute;
	margin: 50px auto;
	border-collapse: collapse;
	right: 200px;
}

.idtable2 tr {
	text-align: right;
}

.idtable2 td {
	/*border-bottom: 1px solid #ddd;*/
	padding: 10px;
}

.inputtd {
	text-align: left;
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
					<p>
						<b>Hi~</b> ${usersResultMap.Title},
					<p>${usersResultMap.UserName}您好~
					<p>歡迎登入番茄科技員工資訊系統
				</div>

				<%@ include file="SubFeatureForEmpManage.jsp"%>

			</div>


			<!--右邊欄位-->

			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">新增員工</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<div class="div1">
							<!--  
							<div>
								<a href="<c:url value="/EmployeePage.do"/>">回HR部門公告</a>
							</div>
							-->
							<br>
							<form action="<c:url value="/AddEmployee.action"/>" method="POST"
								enctype="multipart/form-data">
								<table class="idtable1">
									<tr>
										<td><span class="span1">*</span>帳號：</td>
										<td class="inputtd"><input type="text" id="account1"
											name="userName" value="${inputmsg.username}"
											autocomplete="off" class="datepicker"></td>
										<td class="msgmap">${msgmap.username}</td>
									</tr>

									<tr>
										<td><span class="span1">*</span>密碼：</td>
										<td class="inputtd"><input type="password" id="pwd1"
											name="userPassword" value="aa12345" autocomplete="off"
											class="datepicker"></td>
										<td class="msgmap">${msgmap.userpwd}</td>
									</tr>
									<tr>
										<td><span class="span1">*</span>姓名：</td>
										<td class="inputtd"><input type="text" id="name1"
											name="name" placeholder="請輸入姓名" autocomplete="off"
											value="${inputmsg.name}" class="datepicker"></td>
										<td class="msgmap">${msgmap.name}</td>
									</tr>
									<tr>
										<td>性別：</td>
										<td class="inputtd"><input type="radio" id="m1"
											name="gender" value="male">男 <input type="radio"
											id="m2" name="gender" value="female">女</td>
									</tr>
									<tr>
										<td>生日：</td>
										<td class="inputtd"><input type="date" name="birth"
											id="birth" value="${inputmsg.birthday}" class="datepicker"></td>
									</tr>

									<tr>
										<td>部門：</td>
										<td class="inputtd"><select name="dept" id="dept"
											class="datepicker"></select></td>
									</tr>
									<tr>
										<td><span class="span1">*</span>職稱：</td>
										<td class="inputtd"><select name="title" id="title"
											class="datepicker"></select></td>
										<td class="msgmap">${msgmap.title}</td>
									</tr>
									<tr>
										<td>主管：</td>
										<td class="inputtd"><select name="manager" id="manager"
											class="datepicker"></select></td>
									</tr>
								</table>
								<table class="idtable2">
									<tr>
										<td><span class="span1">*</span>薪水：</td>
										<td class="inputtd"><input type="text" name="salary" id="salary"
											value="${inputmsg.salary}" maxlength="10" autocomplete="off"
											class="datepicker"></td>
										<td class="msgmap">${msgmap.salary}</td>
									</tr>
									<tr>
										<td>分機號碼：</td>
										<td class="inputtd"><input type="text" name="exnum" id="exnum"
											value="${inputmsg.extensionNum}" maxlength="10"
											autocomplete="off" class="datepicker"></td>
									</tr>
									<tr>
										<td>電話：</td>
										<td class="inputtd"><input type="text" name="phonenum" id="phonenum"
											value="${inputmsg.phoneNum}" maxlength="10"
											autocomplete="off" class="datepicker"></td>
									</tr>
									<tr>
										<td>地址：</td>
										<td class="inputtd"><input type="text" name="address" id="address"
											value="${inputmsg.address}" autocomplete="off"
											class="datepicker"></td>
										<td>
									</tr>
									<tr>
										<td><span class="span1">*</span>Email：</td>
										<td class="inputtd"><input type="email" name="email" id="email"
											value="${inputmsg.email}" autocomplete="off"
											class="datepicker"></td>
										<td class="msgmap">${msgmap.email}</td>
									</tr>
									<tr>
										<td>照片：</td>
										<td class="inputtd"><input type="file" name="file"
											class="datepicker"></td>
									</tr>
									<tr>
										<td>到職日：</td>
										<td class="inputtd"><input type="date" name="hireDay" id="hireDay"
											value="${inputmsg.hireDay}" class="datepicker"></td>
									</tr>
									<tr>
										<td>離職日：</td>
										<td class="inputtd"><input type="date" name="lastWorkDay" id="lastWorkDay"
											value="${inputmsg.lastWorkDay}" class="datepicker"></td>
									</tr>
								</table>
								<br>
								<table class="tb">
									<tr>
										<td><input type="submit" value="送出" class="btn btn-info">
											<input type="reset" value="清除" onclick="reset()"
											class="btn btn-info">
										<a href=# onclick="onefinger()"
												class="btn btn-info">一鍵新增</a></td>
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

		function onefinger(){
				$("#account1").attr("value", "EEIT11219");
				$("#name1").attr("value", "王大明");
				$("#m1").attr("checked", "check");
				$("#birth").attr("value", "1990-05-01");
				//$("#dept").val('RD');
				//$("#title").val('RD工程師');
				$("#salary").attr("value", "45000");
				$("#exnum").attr("value", "8710");
				$("#phonenum").attr("value", "0987321456");
				$("#address").attr("value", "台北市大安區復興南路一段390號2樓");
				$("#email").attr("value", "eis_daming@gmail.com,tw");
				$("#hireDay").attr("value", "2020-05-08");		
			}
	</script>
</body>
</html>