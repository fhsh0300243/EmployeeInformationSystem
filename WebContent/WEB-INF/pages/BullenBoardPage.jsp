<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>布告欄維護系統</title>
<link rel="stylesheet"
	href="<c:url value = "/js/BulletinBoard/wysibb/theme/default/wbbtheme.css"/>" />
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href='<c:url value = "/lib/bootstrap-4.4.1/css/bootstrap.css"/>' />
<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<style>
#editor {
	width: 60%;
	height: 250px;
}
#title {
	width: 60%;
}
.date {
	width: 24%
}
#BulletinBoardid, .none {
	display: none;
}
body {
	font-family: 微軟正黑體;
}
p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size:18px;
}

.imgButton{
	width:30%;
}
.home{
	width:30%;
}
.logout{
	width:30%;
}
.CanNotRightDownDiv{
	width:15%;
	left:3.5%;
	z-index:2;
}
.functionTitle{
	right: 0px;
}
</style>
</head>
<body>
<br>

	<div class="cont">
		<div class="row1">

			<!--左邊欄位-->
			<div class="leftTop">
				<div class="leftTopP">
					<p>Hi, ${usersResultMap.UserName} 您好~
					<p>歡迎登入番茄科技員工資訊系統
				</div>
				<div class="leftSideBar">
					<a href="<c:url value="/mainPage"/>"><img class="imgButton home"
						src="images/home.png" border="0"></a> 
					<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
						src="images/left1.png" border="0"></a> 
					<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
						src="images/left2.png" border="0"></a> 
					<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
						src="images/left3.png" border="0"></a> 
					<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
						src="images/left4.png" border="0"></a> 
					<a href="<c:url value="/xxx.do"/>"><img class="imgButton"
						src="images/left5.png" border="0"></a> 
					<a href="<c:url value="/EmployeePage.do"/>"><img
						class="imgButton" src="images/left6.png" border="0"></a> 
					<a href="<c:url value="/BullenBoardPage"/>"><img class="imgButton"
						src="images/left7.png" border="0"></a> 
					<a href="<c:url value="/toLoginPage"/>"><img
						class="imgButton logout" src="images/logout.png" border="0"></a>
				</div>
			</div>
			<!--右邊欄位-->

			<div class="rightTop">

				<div class="panel2 panel-primary2">
					<div class="panel-heading2"></div>
					<div class="panel-body2">
						<section class="BBSboard">
							
							<p class="functionTitle">布告欄</p>

							<div>
								<ul class="nav nav-tabs" id="myTab" role="tablist">
									<li class="nav-item "><a class="nav-link" id="profile-tab"
										data-toggle="tab" href="#boardlook" role="tab"
										aria-controls="#check" aria-selected="false">通知查看頁面</a></li>
									<li class="nav-item "><a class="nav-link" id="profile-tab"
										data-toggle="tab" href="#check" role="tab"
										aria-controls="#check" aria-selected="false">個人發布通知列表</a></li>
									<li class="nav-item"><a class="nav-link" id="contact-tab"
										data-toggle="tab" href="#update" role="tab"
										aria-controls="update" aria-selected="false">新增/更新通知</a></li>
									<li class="nav-item"><a class="nav-link" id="record-tab"
										data-toggle="tab" href="#record" role="tab"
										aria-controls="#record" aria-selected="false">歷年紀錄</a></li>
								</ul>
								<div class="tab-content" id="myTabContent">
									<div class="tab-pane fade ${check}" id="boardlook" role="tabpanel"
										aria-labelledby="profile-tab">
										<h3>顯示</h3>
										<section class="accordion" id="board"></section>
									</div>
								
								
									<div class="tab-pane fade ${check}" id="check" role="tabpanel"
										aria-labelledby="profile-tab">
										<h3>顯示</h3>
										<div class="accordion" id="checkData"></div>
									</div>

									<div class="tab-pane fade" id="update" role="tabpanel"
										aria-labelledby="contact-tab">
										<h3 id="updateName">新增/更新通知</h3>

										<form id="insertNotice" name="insertNotice"
											action='<c:url value="/insert"></c:url>' method="post"
											enctype="multipart/form-data">

											<input type="text" name="BulletinBoardid" class="none"
												id="BulletinBoardid" />
											<p>
												<input type="text" name="title" placeholder="標題" id="title" />
											</p>
											<input type="text" name="BulletinBoardid"
												id="BulletinBoardid"> <input type="text"
												name="content" class="none content" />


											<textarea placeholder="內文" id="editor"></textarea>

											<p>
												發佈日期：<input class="date" id="uptime" type="date"
													name="uptime"> 下架日期：<input class="date"
													id="downtime" type="date" name="downtime">
											</p>

											<p>
											<div class="depAuthority">
												<p>傳送至 :</p>

												<input type="checkbox" value="HR" name="dep"> HR <input
													type="checkbox" value="RD" name="dep">RD <input
													type="checkbox" value="Test" name="dep"> Test <input
													type="checkbox" value="Sales" name="dep"> Sales <input
													type="checkbox" value="PM" name="dep"> PM

											</div>
											<section class="my-2">
												<p>附件:</p>
												<input type="file" name="file" id="file" />
											</section>


											<section class="text-center">
												<button type="submit" id="insertData"
													class="my-2 btn btn-secondary">送出</button>
												<button type="button" id="tttttt"
													class="my-2 btn btn-secondary">測試</button>
											</section>
										</form>
									</div>

									<div class="tab-pane fade ${record}" id="record"
										role="tabpanel" aria-labelledby="contact-tab">
										<h3>歷年紀錄</h3>
										<div class="accordion" id="result"></div>
									</div>
								</div>
							</div>

						</section>
					</div>
				</div>
			</div>
		</div>
	</div>
	

	<div class="CanNotRightDownDiv">
		<img class="CanNotRightDown" src="images/CompanyLogo.png">
	</div>


<script type="text/javascript"
		src='<c:url value = "/lib/jquery/jquery-3.4.1.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value = "/lib/bootstrap-4.4.1/js/bootstrap.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/js/BulletinBoard/BullBoard.js"></c:url>'></script>
	<script type="text/javascript"
		src='<c:url value="/js/BulletinBoard/index.js"></c:url>'></script>
	<script type="text/javascript"
		src='<c:url value="/js/BulletinBoard/wysibb/jquery.wysibb.js"></c:url>'></script>

	<script>
	$(document).ready(
			function() {
				var wbbOpt = {
					buttons : "bold,italic,underline,strike,sup,sub,|,img,video,link,|,bullist,numlist,|,fontcolor,fontsize,fontfamily,|,justifyleft,justifycenter,justifyright,|,quote,code,table,removeFormat"
				}
				$("#editor").wysibb(wbbOpt);
			});
	</script>
	
	
	
</body>
</html>