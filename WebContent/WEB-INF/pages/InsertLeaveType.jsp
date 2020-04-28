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

				<%@ include file="LeaveMain.jsp"%>

			</div>

			<!--右邊欄位-->

			<div class="col-sm-8">

				<div class="panel panel-primary">
					<p class="functionTitle">假別匯入</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>
					<form class="for2" action="<c:url value="/applyforleave"/>"
						method="post" enctype="multipart/form-data">
						<table id="idtable5">
							<tr>
								<td class="tdtag"><span class="span1">*</span>員工編號：</td>
								<td colspan="3"><input type=number id="idIEmpId" step="1"
									min="0" onblur="checkEmpId();"></td>
							</tr>
							<tr>
								<td></td>
								<td class="tdErr" colspan="3"><img id="empIdImg"> <span
									id="empIdCheck"></span></td>
							</tr>
							<tr>
								<td class="tdtag"><span class="span1">*</span>假別：</td>
								<td colspan="3"><input type=checkbox id="idCS"
									name="leaveType" value="s">事假</td>
							</tr>
							<tr>
								<td></td>
								<td class="tdErr" colspan="3"></td>
							</tr>
							<tr>
								<td></td>
								<td colspan="3"><input type=checkbox id="idCB"
									name="leaveType" value="b">病假</td>
								<td id=hTYear class="tdtag" style="display: none;"><span
									class="span1">*</span>年份：<br /></td>
								<td id="hYear" style="display: none;"><input type=number
									id="idYear" step="1" min="2020" max="2100"
									onblur="checkYear();"><span class="span2">&emsp;事假、病假、特休，請輸入年份。</span>
								</td>
							</tr>
							<tr>
								<td></td>
								<td class="tdErr"></td>
								<td id="hEYear" class="tdErr" style="display: none;"><img
									id="yearImg"> <span id="yearCheck"></span></td>
							</tr>
							<tr>
								<td></td>
								<td colspan="3"><input type=checkbox id="idCT"
									name="leaveType" value="t">特休</td>
							</tr>
							<tr>
								<td></td>
								<td class="tdErr" colspan="3"></td>
							</tr>
							<tr>
								<td></td>
								<td><input type=checkbox id="idCG" name="leaveType"
									value="g">公假</td>
								<td id="hTHours" class="tdtag" style="display: none;"><span
									class="span1">*</span>申請時數：</td>
								<td id="hHours" style="display: none;"><input type=number
									step="1" min="0" max="200"><span class="span2">&emsp;最小單位：1小時。</span></td>
							</tr>
							<tr>
								<td></td>
								<td class="tdErr" colspan="3"></td>
							</tr>
							<tr>
								<td></td>
								<td><input type=checkbox id="idCSa" name="leaveType"
									value="sa">喪假</td>
								<td id="hTOption" class="tdtag" style="display: none;"><span
									class="span1">*</span>喪亡者：</td>
								<td id="hOption" style="display: none;"><select id=idWho
									name='selWho'>
										<option value="0">==============請選擇==============</option>
										<option value="1">父母、養父母、繼父母、配偶</option>
										<option value="2">祖父母、子女、配偶之父母、配偶之養父母或繼父母</option>
										<option value="3">曾祖父母、兄弟姊妹、配偶之祖父母</option>
								</select></td>
							</tr>
							<tr>
								<td></td>
								<td class="tdErr" colspan="3"><img id="leaveTypeImg">
									<span id="leaveTypeCheck"></span></td>
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
		$('input[type="checkbox"]').click(function() {
			$("#leaveTypeImg").attr("src", "");
			$("#leaveTypeCheck").empty();
			if ($("#idCS,#idCB,#idCT").is(":checked")) {
				$("#hTYear,#hYear,#hEYear").show();
			} else {
				$("#hTYear,#hYear,#hEYear").hide();
			}
			if ($("#idCG").is(":checked")) {
				$("#hTHours,#hHours").show();
			} else {
				$("#hTHours,#hHours").hide();
			}
			if ($("#idCSa").is(":checked")) {
				$("#hTOption,#hOption").show();
			} else {
				$("#hTOption,#hOption").hide();
			}
		});

		function cls() {
			$("#hTYear,#hYear").hide();
			$("#hTHours,#hHours").hide();
			$("#hTOption,#hOption").hide();
			$("#empIdImg").attr("src", "");
			$("#empIdCheck").empty();
			$("#leaveTypeImg").attr("src", "");
			$("#leaveTypeCheck").empty();
			$("#yearImg").attr("src", "");
			$("#yearCheck").empty();
		}

		function checkEmpId() {
			var inputEmpId = $("#idIEmpId").val();

			if (inputEmpId == "" || inputEmpId.length == 0) {
				$("#empIdImg").attr("src", "images/X_icon.png");
				$("#empIdCheck").html("不可空白");
			} else {
				$("#empIdImg").attr("src", "");
				$("#empIdCheck").empty();
			}
		}

		function checkCheckBox() {
			var inputCS = $("#idCS").val();
			var inputCB = $("#idCB").val();
			var inputCT = $("#idCT").val();
			var inputCG = $("#idCG").val();
			var inputCSa = $("#idCSa").val();

			if (!$("#idCS,#idCB,#idCT,#idCG,#idCSa").is(":checked")) {
				$("#leaveTypeImg").attr("src", "images/X_icon.png");
				$("#leaveTypeCheck").html("請選擇假別");
			} else {
				$("#leaveTypeImg").attr("src", "");
				$("#leaveTypeCheck").empty();
			}
		}

		function checkYear() {
			var inputYear = $("#idYear").val();

			if ($("#idCS,#idCB,#idCT").is(":checked")) {
				if (inputYear == "" || inputYear.length == 0) {
					$("#yearImg").attr("src", "images/X_icon.png");
					$("#yearCheck").html("請輸入年份");
				} else {
					$("#yearImg").attr("src", "");
					$("#yearCheck").empty();
				}
			}
		}

		function checkSubmit() {
			checkEmpId();
			checkCheckBox();
			checkYear();

			var checkSubEmpId = $("#empIdCheck").text();
			var checkSubLT = $("#leaveTypeCheck").text();
			var checkSubYear = $("#yearCheck").text();

			if (checkSubEmpId == "" && checkSubLT == "" && checkSubYear == "") {
				return true;
			} else {
				return false;
			}
		}
	</script>

</body>
</html>