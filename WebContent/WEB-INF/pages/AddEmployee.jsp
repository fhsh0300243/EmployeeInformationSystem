<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增員工</title>
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

.msgmap {
	color: red;
}

.div1 {
	margin: 0 auto;
	margin-left: 750px;
}
</style>
</head>
<body>

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
					<p class="functionTitle">員工管理</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						<div class="div1">
							<h2>新增員工資料</h2>
							<div>
								<a href="<c:url value="/EmployeePage.do"/>">回員工清單</a>
							</div>
							<form action="<c:url value="/AddEmployee.action"/>" method="POST"
								enctype="multipart/form-data">
								<table class="st1">
									<tr>
										<td>帳號：</td>
										<td><input type="text" id="account1" name="userName"
											size="30" value="${inputmsg.username}" autocomplete="off"></td>
										<td class="msgmap">${msgmap.username}</td>
									</tr>
									<tr>
										<td>密碼：</td>
										<td><input type="password" id="pwd1" name="userPassword"
											value="aa12345" size="30" autocomplete="off"></td>
										<td class="msgmap">${msgmap.userpwd}</td>
									</tr>
									<tr>
										<td>姓名：</td>
										<td><input type="text" id="name1" name="name"
											placeholder="請輸入姓名" autocomplete="off" size="30"
											value="${inputmsg.name}"></td>
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
											value="${inputmsg.birthday}"></td>
									</tr>
									<tr>
										<td>地址：</td>
										<td><input type="text" name="address"
											value="${inputmsg.address}" size="30" autocomplete="off"></td>
										<td>
									<tr>
										<td>部門：</td>
										<td><select name="dept" id="dept"></select></td>
									</tr>
									<tr>
										<td>主管：</td>
										<td><select name="manager" id="manager"></select></td>
									</tr>
									<tr>
										<td>職稱：</td>
										<td><select name="title" id="title"></select></td>
										<td class="msgmap">${msgmap.title}</td>
									</tr>
									<tr>
										<td>薪水：</td>
										<td><input type="text" name="salary"
											value="${inputmsg.salary}" maxlength="10" size="30"
											autocomplete="off"></td>
										<td class="msgmap">${msgmap.salary}</td>
									</tr>
									<tr>
										<td>分機號碼：</td>
										<td><input type="text" name="exnum"
											value="${inputmsg.extensionNum}" maxlength="10" size="30"
											autocomplete="off"></td>
									</tr>
									<tr>
										<td>電話：</td>
										<td><input type="text" name="phonenum"
											value="${inputmsg.phoneNum}" maxlength="10" size="30"
											autocomplete="off"></td>
									</tr>
									<tr>
										<td>Email：</td>
										<td><input type="email" name="email"
											value="${inputmsg.email}" size="30" autocomplete="off"></td>
										<td class="msgmap">${msgmap.email}</td>
									</tr>
									<tr>
										<td>照片：</td>
										<td><input type="file" name="file"></td>
									</tr>
									<tr>
										<td>到職日：</td>
										<td><input type="date" name="hireDay"
											value="${inputmsg.hireDay}"></td>
									</tr>
									<tr>
										<td>離職日：</td>
										<td><input type="date" name="lastWorkDay"
											value="${inputmsg.lastWorkDay}"></td>
									</tr>
								</table>
								<table>
									<tr>
										<td><input type="submit" value="送出"> <input
											type="reset" value="清除" onclick="reset()"></td>
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
	</script>
</body>
</html>