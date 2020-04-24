<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
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
							<span class="am-icon-chevron-left am-fl left-btn"></span> 目前培訓課程名稱 
						</div>

						<div class="commentPeixun_wrap mt60">
							<!-- banner -->
							<div class="am-container">
								<img src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg"
									class="am-img-responsive" width=100% alt="培訓圖片" />
							</div>
							<!-- address -->
							<div class="am-cf my_address">
								<div class="am-text-sm am-fl col6">地址：本公司會議室A203</div>
								<button class="am-btn am-round am-btn-xs am-btn-primary am-fr">地圖查看</button>
							</div>
							<!-- 培訓訊息 -->
							<div class="am-container">
								<div>
									<div>
										<div class="am-panel am-panel-default">
											<div class="am-panel-bd">
												<div class='am-text-sm am-cf'>
													課程名稱：<span>xxx課程</span>
													<button
														class="am-btn am-round am-btn-xs am-btn-default am-fr">我要評價</button>
												</div>
												<div class='am-text-sm am-margin-top-sm'>
													<span
														class='am-icon-star am-text-success am-margin-right-sm'><i
														class='am-text-success'>滿意：60%</i></span> <span
														class='am-icon-star-half-o am-text-warning am-margin-right-sm'><i
														class='am-text-warning'>普通：30%</i></span> <span
														class='am-icon-star-o am-text-danger'><i
														class='am-text-danger'>不滿意：10%</i></span>
												</div>
											</div>
										</div>
										<div class="am-panel am-panel-default">
											<div class="am-panel-bd">
												<div class='am-text-sm am-cf'>
													課程名稱：<span>xxx課程</span>
													<button
														class="am-btn am-round am-btn-xs am-btn-default am-fr">我要評價</button>
												</div>
												<div class='am-text-sm am-margin-top-sm'>
													<span
														class='am-icon-star am-text-success am-margin-right-sm'><i
														class='am-text-success'>滿意：60%</i></span> <span
														class='am-icon-star-half-o am-text-warning am-margin-right-sm'><i
														class='am-text-warning'>普通：30%</i></span> <span
														class='am-icon-star-o am-text-danger'><i
														class='am-text-danger'>不滿意：10%</i></span>
												</div>
											</div>
										</div>
										<div class="am-panel am-panel-default">
											<div class="am-panel-bd">
												<div class='am-text-sm am-cf'>
													課程名稱：<span>xxx課程</span>
													<button
														class="am-btn am-round am-btn-xs am-btn-default am-fr">我要評價</button>
												</div>
												<div class='am-text-sm am-margin-top-sm'>
													<span
														class='am-icon-star am-text-success am-margin-right-sm'><i
														class='am-text-success'>滿意：60%</i></span> <span
														class='am-icon-star-half-o am-text-warning am-margin-right-sm'><i
														class='am-text-warning'>普通：30%</i></span> <span
														class='am-icon-star-o am-text-danger'><i
														class='am-text-danger'>不滿意：10%</i></span>
												</div>
											</div>
										</div>


									</div>

								</div>

							</div>



						</div>
</body>
<script src="/js/amazeui.min.js"></script>

</html>