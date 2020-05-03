<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.eis.model.Employee,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>番茄科技 打卡系統</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<link rel="stylesheet" type="text/css" href="css/SearchPage.css">
<link rel="icon" href="images/favicon.ico">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<style type="text/css">
.ui-datepicker-calendar {
	display: none;
}

.well, .panel {
	text-align: center;
}

.f1 {
	position: relative;
	width: 70%;
	left: 30%;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}

b {
	font-size: 20px;
}
/*.tb {
	position: relative;
	width: 70%;
	left: 28.5%;
}*/
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

				<%@ include file="SubFeatureForAttendance.jsp"%>

			</div>


			<!--右邊欄位-->

			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">部門出勤查詢</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
						${errormsg}
						<div class="f1">
							<form action="<c:url value='/InquiryAttendanceDepartment'/>"
								method="post">
								<div class="col-md-7">
									<input type="text" id="datepicker"
										class="datepicker form-control" name="month" value="${month}"
										placeholder="選擇查詢月份" autocomplete="off"><br> <input
										type="submit" value="查詢" class="btn btn-info" />
								</div>
							</form>
						</div>
						<br> <br> <br> <br>
						<div class="tb">
							<table id="idtable1">
								<tr>
									<th>員工ID</th>
									<th>姓名</th>
									<th>部門</th>
									<th>職稱</th>
									<th>異常次數</th>
									<th>詳細資料</th>
								</tr>
								<c:forEach var='element' items='${countMap}' varStatus='vs'>
									<tr class='classtr1'>
										<td>${element.getKey().getEmpID()}</td>
										<td>${element.getKey().getName()}</td>
										<td>${element.getKey().getDepartment()}</td>
										<td>${element.getKey().getTitle()}</td>
										<td>${element.getValue()}</td>
										<td><input type="button"
											onclick="detail(${element.getKey().getEmpID()})" value="詳細資料"
											class="btn btn-info"></td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div class="list_footer">
							<div id="tag"></div>
							<div id="page"></div>
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
	function detail(EmpID){
		const month = document.getElementById("datepicker").value;
		window.location.href = "InquiryAttendanceByBoss?EmpId="+EmpID+"&month="+month;
	}
		$(function() {
			$('#datepicker')
					.datepicker(
							{
								changeYear : true, // 年下拉選單
								changeMonth : true, // 月下拉選單
								showButtonPanel : true, // 顯示介面
								showMonthAfterYear : true, // 月份顯示在年後面
								dateFormat : 'yy-mm-dd',
								showButtonPanel : true,
								monthNamesShort : [ "1月", "2月", "3月", "4月",
										"5月", "6月", "7月", "8月", "9月", "10月",
										"11月", "12月" ], // 月名中文
								prevText : '上月', // 上月按鈕
								nextText : '下月', // 下月按鈕
								currentText : "本月", // 本月按鈕
								closeText : "確認", // 確認選項按鈕
								onClose : function(dateText, inst) {
									var month = $(
											"#ui-datepicker-div .ui-datepicker-month option:selected")
											.val(); //得到選則的月份值
									var parseIntok = parseInt(month) + 1;
									if (parseIntok < 10) {
										parseIn = '0' + parseIntok;
									} else {
										parseIn = parseIntok;
									}
									var year = $(
											"#ui-datepicker-div .ui-datepicker-year option:selected")
											.val(); //得到選則的年份值
									$('#datepicker').val(year + '-' + parseIn); //给input赋值，其中要對月值加1才是實際的月份
								}
							});
		});
	</script>
</body>
</html>
