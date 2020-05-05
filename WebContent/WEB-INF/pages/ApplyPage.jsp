<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>番茄科技 請假系統</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"></link>

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<link rel="stylesheet" type="text/css" href="css/ApplyPage.css">
<link rel="icon" href="images/favicon.ico">
<style>
.col-sm-4, .functionTitle {
	text-align: center;
}

p {
	font-family: 'Noto Sans TC', sans-serif;
	font-size: 18px;
}

b {
	font-size: 20px;
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

				<%@ include file="LeaveMain.jsp"%>

			</div>

			<!--右邊欄位-->

			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">請假申請</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<form class="for2" action="<c:url value="/applyforleave"/>"
						method="post" enctype="multipart/form-data">
						<table id="idtable4">
							<tr>
								<td class="tdtag"><span class="span1">*</span>假別：</td>
								<td><select id=idLT name='selLT'><option value="">=======請選擇=======</option>${selLT}</select></td>

							</tr>
							<tr>
								<td></td>
								<td class=surplusHours></td>
							</tr>
							<tr>
								<td></td>
								<td class="tdErr"><img id="leaveTypeImg"> <span
									id="leaveTypeCheck"></span></td>
							</tr>
							<tr>
								<td class="tdtag"><span class="span1">*</span>日期／時間：</td>
								<td><input type="date" id="idStartDate" name="startdate"
									required> <select id=idSH name='selSH' required><option
											value="">請選擇</option>${selSH}</select>時 <select id=idSM name='selSM'
									required>
										<option value="">請選擇</option>
										<option value="0">0</option>
										<option value="30">30</option>
								</select>分 ~ <input type="date" id="idEndDate" name="enddate" required>
									<select id=idEH name='selEH' required><option value="">請選擇</option>${selEH}</select>時
									<select id=idEM name='selEM' required>
										<option value="">請選擇</option>
										<option value="0">0</option>
										<option value="30">30</option>
								</select>分</td>
							<tr>
								<td></td>
								<td class=sumHours></td>
							</tr>
							<tr>
								<td></td>
								<td class="tdErr"><img id="dateImg"> <span
									id="dateCheck">${DateError}</span></td>
							</tr>

							<tr>
								<td class="tdtag"><label for="cause">事由：</label></td>
								<td><textarea cols="30" rows="3" id="idCause" name="cause"
										onblur="checkCause();"></textarea></td>

							</tr>
							<tr>
								<td></td>
								<td class=tdNotes>字數限制：50字。</td>
							</tr>
							<tr>
								<td></td>
								<td class="tdErr"><img id="causeImg"> <span
									id="causeCheck"></span></td>
							</tr>
							<tr>
								<td class="tdtag">附件：</td>
								<td><input type="file" id="idAtt" name="myFile"
									accept="image/*" /></td>

							</tr>
							<tr>
								<td></td>
								<td class="tdErr"><img id="attImg"> <span
									id="attCheck"></span></td>
							</tr>

						</table>
						<hr>
						<div class="btn2">
							<input type="reset" value="清除" onclick="cls();"
								class="btn btn-info" /> <input type="submit" value="送出"
								onclick="return checkSubmit();" class="btn btn-info" />
						</div>
					</form>

					<div id="tag"></div>
					<div id="page"></div>
				</div>


			</div>
		</div>
	</div>

	<div class="CanNotRightDownDiv">
		<img class="CanNotRightDown" src="images/CompanyLogo.png">
	</div>




	<script src="js/jquery-3.4.1.min.js"></script>
	<script>
		$("#idLT, #idStartDate, #idSH, #idSM, #idEndDate, #idEH, #idEM")
				.change(
						function() {
							$("#dateImg").attr("src", "");
							$("#dateCheck,.sumHours").empty();

							var eldID = $("#idLT").val();
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
										if (eldID != null && eldID.length != 0) {
											$
													.ajax({
														url : "changeDHM",
														data : {
															eldID : eldID,
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
											$("#leaveTypeImg").attr("src",
													"images/X_icon.png");
											$("#leaveTypeCheck").html("請選擇假別");
										}
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
			$("#leaveTypeCheck,.surplusHours").empty();

			if ($("#idLT").val() != null && $("#idLT").val().length != 0) {
				$.ajax({
					url : "changeLT",
					data : {
						eldID : $("#idLT").val(),
					},
					type : "POST",
					success : function(data) {
						$(".surplusHours").text(data);
					}
				})
			} else {
				$("#leaveTypeImg").attr("src", "images/X_icon.png");
				$("#leaveTypeCheck").html("請選擇假別");
			}
		});

		function cls() {
			$("#leaveTypeImg,#dateImg,#causeImg,#attImg").attr("src", "");
			$(
					"#leaveTypeCheck,.surplusHours,.sumHours,#dateCheck,#causeCheck,#attCheck")
					.empty();
		}

		function checkCause() {
			var inputCause = $("#idCause").val();

			if (inputCause.length > 50) {
				$("#causeImg").attr("src", "images/X_icon.png");
				$("#causeCheck").html("不可超過50字");
			} else {
				$("#causeImg").attr("src", "");
				$("#causeCheck").empty();
			}
		}

		$("#idAtt").change(
				function() {
					var inputAtt = $("#idAtt").val();
					$("#attImg").attr("src", "");
					$("#attCheck").empty();

					if (inputAtt == null || inputAtt.length == 0) {
						$("#attImg").attr("src", "");
						$("#attCheck").empty();
					} else {
						var array = new Array(".jpg", ".png", ".gif", ".JPG",
								".PNG", ".GIF");
						inputAtt = inputAtt
								.substring(inputAtt.lastIndexOf("."));
						if (array.indexOf(inputAtt) < 0) {
							$("#attImg").attr("src", "images/X_icon.png");
							$("#attCheck")
									.html("只接受圖片檔（.jpg、.png、.gif，三種檔案格式)");
						}
					}
				});

		function checkSubmit() {
			var inputLT = $("#idLT").val();
			var checkSubLT = $("#leaveTypeCheck").text();
			var checkSubDate = $("#dateCheck").text();
			var checkSubCause = $("#causeCheck").text();
			var checkSubAtt = $("#attCheck").text();

			if (inputLT == "") {
				$("#leaveTypeImg").attr("src", "images/X_icon.png");
				$("#leaveTypeCheck").html("請選擇假別");
				return false;
			} else {
				if (checkSubCause == "" && checkSubLT == ""
						&& checkSubAtt == "" && checkSubDate == "") {
					return true;
				} else {
					return false;
				}
			}
		}
	</script>

</body>
</html>