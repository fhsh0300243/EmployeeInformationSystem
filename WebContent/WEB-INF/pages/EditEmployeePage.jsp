<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="employee" class="tw.eis.model.Employee" scope="page" />
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

.msgmap {
	color: red;
}

.div1 {
	width: 30%;
	margin: 0 auto;
	margin-left: 37%;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}
.tb{
	position: relative;
	left: 30%;
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

	<br>
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
					<p class="functionTitle">編輯員工資料</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<div class="div1">
							<div>
								<a href="<c:url value="/EmployeePage.do"/>">回HR部門公告</a> 
								<!--  <img alt="" src="<c:url value="/empimgurl"/>" class="userImg" />-->
								<img alt="..." src="<c:url value="/empimgurl"/>" class="img-thumbnail" />
							</div>
							<br>
							<form action="<c:url value="/EditAddEmployee.action"/>"
								method="POST" enctype="multipart/form-data">
								<table>
									<tr>
										<td><span class="span1">*</span>帳號：</td>
										<td><input type="text" id="account1" name="userName"
											value='${user.userName}' size="30" readonly class="datepicker"></td>
										<td class="msgmap">${msgmap.username}</td>
									</tr>
									<tr>
										<td><span class="span1">*</span>姓名：</td>
										<td><input type="text" id="name1" name="name"
											value='${emp.name}' placeholder="請輸入姓名" autocomplete="off"
											size="30" class="datepicker"></td>
										<td class="msgmap">${msgmap.name}</td>
									</tr>
									<tr>
										<td>性別：</td>
										<td><input type="radio" id="m1" name="gender"
											value="male">男 <input type="radio" id="m2"
											name="gender" value="female">女</td>
									</tr>
									<tr>
										<td>生日：</td>
										<td><input type="date" name="birth"
											value='${emp.birthDay}' class="datepicker"></td>
									</tr>
									<tr>
										<td>地址：</td>
										<td><input type="text" name="address"
											value='${emp.address}' size="30" autocomplete="off" class="datepicker"></td>
									</tr>
									<tr>
										<td>部門：</td>
										<td><select name="dept" id="dept" class="datepicker"></select></td>
									</tr>
									<tr>
										<td><span class="span1">*</span>職稱：</td>
										<td><select name="title" id="title" class="datepicker"></select></td>
										<td class="msgmap">${msgmap.title}</td>
									</tr>
									<tr>
										<td>主管：</td>
										<td><select name="manager" id="manager" class="datepicker"></select></td>
									</tr>
									<tr>
										<td><span class="span1">*</span>薪水：</td>
										<td><input type="text" name="salary"
											value='${emp.salary}' maxlength="10" size="30"
											autocomplete="off" class="datepicker"></td>
										<td class="msgmap">${msgmap.salary}</td>
									</tr>
									<tr>
										<td>分機號碼：</td>
										<td><input type="text" name="exnum" maxlength="10"
											value='${emp.extensionNum}' size="30" autocomplete="off" class="datepicker"></td>
									</tr>
									<tr>
										<td>電話：</td>
										<td><input type="text" name="phonenum" maxlength="10"
											value='${emp.phoneNum}' size="30" autocomplete="off" class="datepicker"></td>
									</tr>
									<tr>
										<td><span class="span1">*</span>Email：</td>
										<td><input type="email" name="email" value='${emp.email}'
											size="30" autocomplete="off" class="datepicker"></td>
										<td class="msgmap">${msgmap.email}</td>
									</tr>
									<tr>
										<td>照片：</td>
										<td><input type="file" name="file" class="datepicker"></td>
									</tr>
									<tr>
										<td>到職日：</td>
										<td><input type="date" name="hireDay"
											value='${emp.hireDay}' class="datepicker"></td>
									</tr>
									<tr>
										<td>離職日：</td>
										<td><input type="date" name="lastWorkDay"
											value='${emp.lastWorkDay}' class="datepicker"></td>
									</tr>
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
	<div class="CanNotRightDownDiv">
		<img class="CanNotRightDown" src="images/CompanyLogo.png">
	</div>







	<script>
		var depts;
		var titles;
		var managers;
		var deptid = '${requestScope.emp.department}';
		$('#dept').empty();
		$('#title').empty();
		$('#manager').empty();

		$.ajax({
			url : "DeptList",
			type : "GET",
			success : function(Str) {
				$('#title').empty();
				depts = JSON.parse(Str);
				var opt = $("<option>").val("").text("選擇部門")
				$("#dept").append(opt);
				for (let i = 0; i < depts.length; i++) {
					var opt = $("<option>").val(depts[i].deptid).text(
							depts[i].deptabb)
					$("#dept").append(opt);
				}
				//下面這行要寫在ajax裡面，超坑!!!坑爆!!!!!坑了我一個禮拜!!!!!!
				$("#dept").val('${requestScope.emp.department}');
			}
		});

		$.ajax({
			url : "TitleList?deptid=" + deptid,
			type : "GET",
			success : function(Str) {
				titles = JSON.parse(Str);
				$('#title').append(
						$("<option></option>").attr("value", "").text("選擇職稱"));
				for (let i = 0; i < titles.length; i++) {
					$("#title").append(
							$("<option></option>").attr("value",
									titles[i].titleid).text(
									titles[i].titlechname));
				}
				$("#title").val('${requestScope.emp.title}');

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
				$("#manager").val('${requestScope.emp.manager}');
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

		if (document.getElementById('m1').value == '${requestScope.emp.gender}') {
			document.getElementById('m1').checked = true;
		}
		if (document.getElementById('m2').value == '${requestScope.emp.gender}') {
			document.getElementById('m2').checked = true;
		}
	</script>

</body>
</html>