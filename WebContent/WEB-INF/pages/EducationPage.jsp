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
					
					<div class="panel-heading">
					<%@ include file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">
                        </div>
                        <!-- header -->
						<div class="mark am-text-lg am-text-center">
                        <span class=""></span>
                        培訓課程
                        </div>

                        <div class="introduce_wrap mt60">
							<!-- banner -->
							<div class="am-container">
								<img src="" 
								     class="" align="middle" width=10% alt=""/>
							</div>
							<!-- address -->
							<div class="am-cf my_address">
								<div class="am-text-sm am-fl col6">地點：本公司會議室A203</div>
							     
								<div id="demo" style="visibility:;"> 
								<iframe id="demo" src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d814.1923408317872!2d121.54316593617708!3d25.03396013748355!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3442abd379a5ec97%3A0xec12a62666a81e3d!2zMTA25Y-w5YyX5biC5aSn5a6J5Y2A5b6p6IiI5Y2X6Lev5LiA5q61Mzkw6Jmf!5e0!3m2!1szh-TW!2stw!4v1588126267665!5m2!1szh-TW!2stw" aria-hidden="flase" tabindex="0"></iframe>
								</div>
								
								<div>
								<button id="btn1" type="button" class="am-btn am-round am-btn-xs am-btn-primary am-fr col3">地圖查看</button>
							    </div>
							</div>
							
							<!-- 報名按鈕 -->
							<div class="am-container am-margin-bottom-sm">
								<button type="button"
									class="am-btn am-btn-primary am-btn-block baoming">點我報名</button>
								<button type="button"
									class="am-btn am-btn-warning am-btn-block baoming am-margin-top-0"
									style='display: none'>取消報名</button>
							</div>
							<!-- tabs -->
							<div data-am-widget="tabs"
								class="am-tabs am-tabs-d2 am-container" style="margin: 0 auto">
								<ul class="am-tabs-nav am-cf">
									<li class="am-active"><a href="[data-tab-panel-0]">課程介绍</a></li>
									<li class=""><a href="[data-tab-panel-1]">課程日期</a></li>
									<li class=""><a href="[data-tab-panel-2]">課程教材</a></li>
									<!--<li class=""><a href="[data-tab-panel-3]">課程評價</a></li>  -->
									

								</ul>
						
								<div class="am-tabs-bd">
									<div data-tab-panel-0 class="am-tab-panel am-active">
										<!-- 培訓訊息 -->
										<div>
											<div class="am-panel am-panel-default">
												<div class="am-panel-hd">培訓訊息</div>
												<div class="am-panel-bd col9">
													<div class='am-text-sm'>
														報到時間：<span>2020/06/01 PM13:45</span>
													</div>
													<div class='am-text-sm'>
														上課時間：<span>PM14:00-17:00</span>
													</div>
													<div class='am-text-sm'>
														培訓課程日期：<span>2020/06/01-2020/06/01</span>
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
														 
														<img
															src="https://image.flaticon.com/icons/svg/2849/2849198.svg"
														    style="position:relative;float:left"
															width='10%' alt="" /> 
														新進人員可在最短時間了解公司經營理念與企業文化，縮短進入新環境之適應期，並加速工作無經驗者之學習曲線。
														
													</p>

												</div>
											</div>

										</div>
									</div>

									<div data-tab-panel-1 class="am-tab-panel ">
										<!-- 培訓訊息 -->
										<div>

											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm'>
														培訓課程日期：<span>2020/06/01-2020/06/01</span>
													</div>
													<div class='am-text-sm'>
														報到時間：<span>PM13:45</span>
													</div>
													<div class='am-text-sm'>
														上課時間：<span>PM14:00-17:00</span>
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
											</div>
											
											<br />
											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm'>
														培訓課程日期：<span>2020/06/03-2020/06/03</span>
													</div>
													<div class='am-text-sm'>
														報到時間：<span>PM13:45</span>
													</div>
													<div class='am-text-sm'>
														上課時間：<span>PM14:00-17:00</span>
													</div>

													<div class='am-text-sm'>
														人數上限：<span>40人</span>
													</div>
													<div class='am-text-sm'>
														培訓課程類別：<span>人資相關(HR)</span>
													</div>
													<div class='am-text-sm'>
														培訓分類：<span>勞動法規</span>
													</div>
													<div class='am-text-sm'>
														課程講師：<span>陳老師</span>
													</div>
												</div>
											</div>

											<br />

											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm'>
														培訓課程日期：<span>2020/06/10-2020/06/10</span>
													</div>
													<div class='am-text-sm'>
														報到時間：<span>AM08:45</span>
													</div>
													<div class='am-text-sm'>
														上課時間：<span>AM09:00-12:00</span>
													</div>

													<div class='am-text-sm'>
														人數上限：<span>40人</span>
													</div>
													<div class='am-text-sm'>
														培訓課程類別：<span>研發相關(RD)</span>
													</div>
													<div class='am-text-sm'>
														培訓分類：<span>產品開發的效率化</span>
													</div>
													<div class='am-text-sm'>
														課程講師：<span>王老師</span>
													</div>
												</div>
											</div>

											<br/>

											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm'>
														培訓課程日期：<span>2020/06/15-2020/06/16</span>
													</div>
													<div class='am-text-sm'>
														報到時間：<span>PM14:50</span>
													</div>
													<div class='am-text-sm'>
														上課時間：<span>PM15:00-17:00</span>
													</div>

													<div class='am-text-sm'>
														人數上限：<span>40人</span>
													</div>
													<div class='am-text-sm'>
														培訓課程類別：<span>測試相關(QA)</span>
													</div>
													<div class='am-text-sm'>
														培訓分類：<span>軟體測試管理實務</span>
													</div>
													<div class='am-text-sm'>
														課程講師：<span>張老師</span>
													</div>
												</div>
											</div>
											
											<br/>
											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm'>
														培訓課程日期：<span>2020/06/21-2020/06/21</span>
													</div>
													<div class='am-text-sm'>
														報到時間：<span>PM13:50</span>
													</div>
													<div class='am-text-sm'>
														上課時間：<span>PM14:00-17:00</span>
													</div>

													<div class='am-text-sm'>
														人數上限：<span>40人</span>
													</div>
													<div class='am-text-sm'>
														培訓課程類別：<span>業務相關(Sales)</span>
													</div>
													<div class='am-text-sm'>
														培訓分類：<span>業務開發能力與銷售流程</span>
													</div>
													<div class='am-text-sm'>
														課程講師：<span>李老師</span>
													</div>
												</div>
											</div>
											<br/>
											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm'>
														培訓課程日期：<span>2020/06/30-2020/06/30</span>
													</div>
													<div class='am-text-sm'>
														報到時間：<span>AM09:50</span>
													</div>
													<div class='am-text-sm'>
														上課時間：<span>AM10:00-12:00</span>
													</div>

													<div class='am-text-sm'>
														人數上限：<span>40人</span>
													</div>
													<div class='am-text-sm'>
														培訓課程類別：<span>專案相關(PM)</span>
													</div>
													<div class='am-text-sm'>
														培訓分類：<span>專案管理PMP企業內訓</span>
													</div>
													<div class='am-text-sm'>
														課程講師：<span>林老師</span>
													</div>
												</div>
											</div>

										</div>

									</div>

									<br />

									<div data-tab-panel-2 class="am-tab-panel ">
										<!-- 培訓訊息 -->
										 <div>
			                            <div>
										
											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm'>
														課程名稱：<span>新進人員職務說明與分析</span>
													</div>
													<div class='am-text-sm'>
														教材大綱電子檔下載：<span><a href="#">下載</a></span>
													</div>
												</div>
											</div>
											</div>
											<!-- 培訓訊息 -->
											<div>
											<div>
											<div>
												<div class="am-panel am-panel-default">
													<div class="am-panel-bd">
														<div class='am-text-sm'>
															課程名稱：<span>勞動契約過程之勞動條件</span>
														</div>
														<div class='am-text-sm'>
															教材大綱電子檔下載：<span><a href="#">下載</a></span>
														</div>
													</div>
												</div>
											<!-- 培訓訊息 -->
											<div>
											<div>
											<div>
												<div class="am-panel am-panel-default">
													<div class="am-panel-bd">
														<div class='am-text-sm'>
															課程名稱：<span>產品開發的效率化和日常管理</span>
														</div>
														<div class='am-text-sm'>
															教材大綱電子檔下載：<span><a href="#">下載</a></span>
														</div>
													</div>
												</div>
											<div>
											<div>
											<div>
												<div class="am-panel am-panel-default">
													<div class="am-panel-bd">
														<div class='am-text-sm'>
															課程名稱：<span>業務開發能力與邏輯銷售流程</span>
														</div>
														<div class='am-text-sm'>
															教材大綱電子檔下載：<span><a href="#">下載</a></span>
														</div>
													</div>
												</div>
												<!-- 培訓訊息 -->
												<div>
													<div class="am-panel am-panel-default">
														<div class="am-panel-bd">
															<div class='am-text-sm'>
																課程名稱：<span>專案管理PMP企業內訓</span>
															</div>
															<div class='am-text-sm'>
																教材大綱電子檔下載：<span><a href="#">下載</a></span>
															</div>
														</div>
													</div>
												</div>
											</div>

											<br/>
											
											<div data-tab-panel-2 class="am-tab-panel ">
										<!-- 培訓訊息 -->
										<div>
											<div class="am-panel am-panel-default">
												<div class="am-panel-bd">
													<div class='am-text-sm'>
														課程名稱：<span>新進人員職務說明與分析</span>
													</div>
													<div class='am-text-sm'>
														教材大綱電子檔下載：<span><a href="#">下載</a></span>
													</div>
												</div>
											</div>
											<!-- 培訓訊息 -->
											<div>
												<div class="am-panel am-panel-default">
													<div class="am-panel-bd">
														<div class='am-text-sm'>
															課程名稱：<span>專案管理PMP企業內訓</span>
														</div>
														<div class='am-text-sm'>
															教材大綱電子檔下載：<span><a href="#">下載</a></span>
														</div>
													</div>
												</div>
												<!-- 培訓訊息 -->
												<div>
													<div class="am-panel am-panel-default">
														<div class="am-panel-bd">
															<div class='am-text-sm'>
																課程名稱：<span>業務開發能力與邏輯銷售流程</span>
															</div>
															<div class='am-text-sm'>
																教材大綱電子檔下載：<span><a href="#">下載</a></span>
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

											
				
			    <div data-tab-panel-3 class="am-tab-panel ">
                <!-- 培訓訊息 -->
			    <div>
			      <div>
			        <div>
			          <div class="am-panel am-panel-default">
			            <div class="am-panel-bd">
			              <div class='am-text-sm am-cf'>培訓班評價：<span>xxx培訓班</span>
			              <button class="am-btn am-round am-btn-xs am-btn-default am-fr">我要評價</button>
			              </div>
			              <div class='am-text-sm am-margin-top-sm'>
			                <span class='am-icon-star am-text-success am-margin-right-sm'><i class='am-text-success'>滿意：60%</i></span>
			                <span class='am-icon-star-half-o am-text-warning am-margin-right-sm'><i class='am-text-warning'>普通：30%</i></span>
			                <span class='am-icon-star-o am-text-danger'><i class='am-text-danger'>不滿意：10%</i></span>
			              </div>
			            </div>
			          </div>
			          <div class="am-panel am-panel-default">
			            <div class="am-panel-bd">
			              <div class='am-text-sm am-cf'>課程名稱：<span>xxx課程</span>
			              <button class="am-btn am-round am-btn-xs am-btn-default am-fr">我要評價</button>
			              </div>
			              <div class='am-text-sm am-margin-top-sm'>
			                <span class='am-icon-star am-text-success am-margin-right-sm'><i class='am-text-success'>滿意：60%</i></span>
			                <span class='am-icon-star-half-o am-text-warning am-margin-right-sm'><i class='am-text-warning'>普通：30%</i></span>
			                <span class='am-icon-star-o am-text-danger'><i class='am-text-danger'>不滿意：10%</i></span>
			              </div>
			            </div>
			          </div>
			          <div class="am-panel am-panel-default">
			            <div class="am-panel-bd">
			              <div class='am-text-sm am-cf'>課程名稱：<span>xxx課程</span>
			              <button class="am-btn am-round am-btn-xs am-btn-default am-fr">我要評價</button>
			              </div>
			              <div class='am-text-sm am-margin-top-sm'>
			                <span class='am-icon-star am-text-success am-margin-right-sm'><i class='am-text-success'>滿意：60%</i></span>
			                <span class='am-icon-star-half-o am-text-warning am-margin-right-sm'><i class='am-text-warning'>普通：30%</i></span>
			                <span class='am-icon-star-o am-text-danger'><i class='am-text-danger'>不滿意：10%</i></span>
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
				</div>

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

			

<script src="js/amazeui.min.js"></script>

 

<script>
	$(".baoming").click(function(){
		$(this).hide().siblings('button').show()
	})
</script>
</body>	
</html>