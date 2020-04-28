<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="UTF-8">
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

<script src="/js/jquery.min.js"></script>
<script src="/js/handlebars.min.js"></script>
<script src="/js/amazeui.widgets.helper.min.js"></script>

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

/* 新增 */

.col-sm-4, .functionTitle {
	text-align: center;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
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
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">

						<div class="am-text-lg am-text-center"></div>


						<div class="introduce_wrap mt60">

							<!-- header -->
							<div class="mark am-text-lg am-text-center">
								<span class="am-icon-chevron-left am-fl left-btn"></span>
								目前培訓課程名稱
							</div>

							<div class="commentKecheng_wrap mt60">
								<!-- banner -->
								<div class="am-container">
									<img src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg"
										class="am-img-responsive" width=100% alt="培訓圖片" />
								</div>
								<!-- address -->
								<div class="am-cf my_address">
									<div class=" am-fl col6">xxx培訓</div>
								</div>


								<!-- 培訓訊息 -->
								<div class="am-container">
									<div>
										<h6 class='am-margin-bottom-xs am-text-sm'>課程：</h6>
										<!-- 課程 -->
										<div class=' am-form'>
											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm am-cf'>
														<span>1.</span>您對本課程的滿意程度
													</div>
													<div class='am-text-sm am-margin-top-sm'>
														<span class='am-margin-right'><input type="radio"
															name='01' id='011'><label for='011'
															class='am-text-success'>滿意</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='012'><label for='012'
															class='am-text-warning'>普通</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='013'><label for='013'
															class='am-text-danger'>不滿意</label></span>
													</div>
												</div>
											</div>
											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm am-cf'>
														<span>2.</span>課程具體內容
													</div>
													<div class='am-text-sm am-margin-top-sm'>
														<span class='am-margin-right'><input type="radio"
															name='01' id='011'><label for='011'
															class='am-text-success'>滿意</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='012'><label for='012'
															class='am-text-warning'>普通</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='013'><label for='013'
															class='am-text-danger'>不滿意</label></span>
													</div>
												</div>
											</div>
											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm am-cf'>
														<span>3.</span>授課時間的安排
													</div>
													<div class='am-text-sm am-margin-top-sm'>
														<span class='am-margin-right'><input type="radio"
															name='01' id='011'><label for='011'
															class='am-text-success'>滿意</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='012'><label for='012'
															class='am-text-warning'>普通</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='013'><label for='013'
															class='am-text-danger'>不滿意</label></span>
													</div>
												</div>
											</div>
											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm am-cf'>
														<span>4.</span>專業知識的補充
													</div>
													<div class='am-text-sm am-margin-top-sm'>
														<span class='am-margin-right'><input type="radio"
															name='01' id='011'><label for='011'
															class='am-text-success'>滿意</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='012'><label for='012'
															class='am-text-warning'>普通</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='013'><label for='013'
															class='am-text-danger'>不滿意</label></span>
													</div>
												</div>
											</div>

											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm am-cf'>
														<span>5.</span>對於工作能力的提升
													</div>
													<div class='am-text-sm am-margin-top-sm'>
														<span class='am-margin-right'><input type="radio"
															name='01' id='011'><label for='011'
															class='am-text-success'>滿意</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='012'><label for='012'
															class='am-text-warning'>普通</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='013'><label for='013'
															class='am-text-danger'>不滿意</label></span>
													</div>
												</div>
											</div>

										</div>
										<hr />
										<!-- 講師 -->
										<h6 class='am-margin-bottom-xs am-text-sm'>授課講師：</h6>
										<div class=' am-form'>
											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm am-cf'>
														<span>1.</span>專業程度
													</div>
													<div class='am-text-sm am-margin-top-sm'>
														<span class='am-margin-right'><input type="radio"
															name='01' id='011'><label for='011'
															class='am-text-success'>滿意</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='012'><label for='012'
															class='am-text-warning'>普通</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='013'><label for='013'
															class='am-text-danger'>不滿意</label></span>
													</div>
												</div>
											</div>
											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm am-cf'>
														<span>2.</span>授課內容
													</div>
													<div class='am-text-sm am-margin-top-sm'>
														<span class='am-margin-right'><input type="radio"
															name='01' id='011'><label for='011'
															class='am-text-success'>滿意</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='012'><label for='012'
															class='am-text-warning'>普通</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='013'><label for='013'
															class='am-text-danger'>不滿意</label></span>
													</div>
												</div>
											</div>

											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm am-cf'>
														<span>3.</span>教學方式
													</div>
													<div class='am-text-sm am-margin-top-sm'>
														<span class='am-margin-right'><input type="radio"
															name='01' id='011'><label for='011'
															class='am-text-success'>滿意</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='012'><label for='012'
															class='am-text-warning'>普通</label></span> <span
															class='am-margin-right'><input type="radio"
															name='01' id='013'><label for='013'
															class='am-text-danger'>不滿意</label></span>
													</div>
												</div>
											</div>

											
											<div class="am-panel am-panel-default am-form">
												<div class="am-panel-bd">
													<div class='am-text-sm am-cf'>
														<span>4.</span>您對本課程還有其他建議嗎？
													</div>
													<div class='am-text-sm am-margin-top-sm'>
														<div class="am-form-group">
															<textarea class="" rows="3"></textarea>
														</div>
													</div>
												<!-- 按鈕 -->
								<tr>
								<td></td>
								<td class=tdNotes>字數限制：100字</td>
							    </tr>
							    <tr>
								<td></td>
								<td class="tdErr"><img id="causeImg"> <span
									id="causeCheck"></span></td>
							    </tr>
							    
							    <hr>
						        <div class="btn2">
							    <input type="reset" value="清除" onclick="cls();"
								class="btn btn-info" /> <input type="submit" value="送出"
								onclick="return checkSubmit();" class="btn btn-info" />
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
							<script src="/js/amazeui.min.js"></script>

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