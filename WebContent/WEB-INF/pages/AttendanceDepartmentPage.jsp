<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.eis.model.Employee,java.util.*"%>
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

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
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
						<form action="<c:url value='/InquiryAttendanceDepartment'/>" method="post">
							<div class="col-md-7">
								<input type="text" id="datepicker"
									class="datepicker form-control" name="month" value=""
									placeholder="選擇查詢月份" autocomplete="off"> <input
									type="submit" value="查詢" class="btn btn-info"/>
							</div>
						</form>
						<table width="500" border="1">
							<tr>
								<td><b>員工ID</b></td>
								<td><b>姓名</b></td>
								<td><b>部門</b></td>
								<td><b>職稱</b></td>
								<td><b>異常次數</b></td>
								<td><b>詳細資料</b></td>
							</tr>
							<%
							Map<Employee,Integer> countMap = (Map<Employee,Integer>)request.getAttribute("countMap");
								if (countMap == null || countMap.size() < 1) {
							%>
							<tr id="test">
								<td align="center" colspan="6">沒有資料!</td>
							</tr>
							<%
								} else {
									for (Map.Entry<Employee,Integer> element : countMap.entrySet()) {
							%>

							<tr align="center">
								<td><%=element.getKey().getEmpID() %></td>
								<td><%=element.getKey().getName() %></td>
								<td><%=element.getKey().getDepartment() %></td>
								<td><%=element.getKey().getTitle() %></td>
								<td><%=element.getValue() %></td>
								<td><input type="button" name="id" value="詳細資料" class="btn btn-info"></td>
							</tr>
							<%
								}
								}
								
							%>
						</table>
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
</html>
</body>
