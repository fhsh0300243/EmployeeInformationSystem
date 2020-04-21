<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>請假申請</title>
<link rel="stylesheet" type="text/css" href="css/ApplyPage.css">
<link rel="stylesheet" type="text/css" href="css/Menu.css">
</head>
<body>
	<aside class="menu">
		<table id="employee">
			<tr>
				<td class="tdTitle">個人專區</td>
			</tr>
			<tr class="trbtn">
				<td id="applyPage"><a href="preapplypage">請假申請</a></td>
			</tr>
			<tr class="trbtn">
				<td id="applyRecord"><a href="preapplyrecord">請假紀錄</a></td>
			</tr>
			<tr class="trbtn">
				<td id="leaveType"><a href="preleavetype">剩餘假別</a></td>
			</tr>
		</table>
		<table id="manager">
			<tr>
				<td class="tdTitle">主管專區</td>
			</tr>
			<tr class="trbtn">
				<td id="unsignedPage"><a href="preunsignedpage">人員請假-未簽核</a></td>
			</tr>
			<tr class="trbtn">
				<td id="signedPage"><a href="presignedpage">人員請假-已簽核</a></td>
			</tr>
		</table>
	</aside>

	<article class="content">
		<h2>請假申請</h2>
		<form class="for2" action="<c:url value="/applyforleave"/>"
			method="post" enctype="multipart/form-data">
			<table id="idtable4">
				<tr>
					<td class="tdtag"><span class="span1">*</span>假別：</td>
					<td><select id=idLT name='selLT' onchange="changeCls();"><option
								value="">=======請選擇=======</option>${selLT}</select></td>
					<td class="tdErr"><img id="leaveTypeImg"> <span
						id="leaveTypeCheck"></span></td>
				</tr>
				<tr>
					<td></td>
					<td class=surplusHours colspan="2"></td>
				</tr>
				<tr>
					<td class="tdtag"><span class="span1">*</span>日期／時間：</td>
					<td colspan="2"><input type="date" id="idStartDate"
						name="startdate" required> <select id=idSH name='selSH'
						required><option value="">請選擇</option>${selSH}</select>時 <select
						id=idSM name='selSM' required>
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
					<td class=sumHours colspan="2"></td>
				</tr>

				<tr>
					<td class="tdtag"><label for="cause">事由：</label></td>
					<td><textarea cols="30" rows="3" id="idCause" name="cause"
							onblur="checkCause();"></textarea></td>
					<td class="tdErr"><img id="causeImg"> <span
						id="causeCheck"></span></td>
				</tr>
				<tr>
					<td></td>
					<td class=tdNotes colspan="2">字數限制：50字。</td>
				</tr>
				<tr>
					<td class="tdtag">附件：</td>
					<td colspan="2"><input type="file" name="myFile"
						accept="image/*" /></td>
				</tr>
			</table>
			<hr>
			<div class="btn2">
				<input type="reset" value="清除" onclick="cls();" /> <input
					type="submit" value="送出" onclick="return checkSubmit();" />
			</div>
		</form>
	</article>
	<script src="js/jquery-3.4.1.min.js"></script>
	<script>
		$("#idStartDate", "#idSH", "#idSM", "#idEndDate", "#idEH", "#idEM")
				.change(
						function() {

							var startD = $("#idStartDate").val();
							var startH = $("#idSH").val();
							var startM = $("#idSM").val();
							var endD = $("#idEndDate").val();
							var endH = $("#idEH").val();
							var endM = $("#idEM").val();

							if (startD != null && startD.length != 0
									&& startH != null && startH.length != 0
									&& startM != null && startM.length != 0
									&& endD != null && endD.length != 0
									&& endH != null && endH.length != 0
									&& endM != null && endM.length != 0) {
								$.ajax({
									url : "changeLT",
									data : {
										startD : startD,
										startH : startH,
										startM : startM,
										endD : endD,
										endH : endH,
										endM : endM
									},
									type : "POST",
									success : function(data) {
										$(".sumHours").text(data);
									}
								})
							}
							$(".sumHours").empty();
						});

		$("#idLT").change(function() {
			if ($("#idLT").val() != null && $("#idLT").val().length != 0) {
				$.ajax({
					url : "changeLT",
					data : {
						leaveType : $("#idLT").val()
					},
					type : "POST",
					success : function(data) {
						$(".surplusHours").text(data);
					}
				})
			}
			$(".surplusHours").empty();
		});

		function changeCls() {
			$("#leaveTypeImg").attr("src", "");
			$("#leaveTypeCheck").empty();
		}

		function cls() {
			$("#causeImg").attr("src", "");
			$("#causeCheck").empty();
			$("#leaveTypeImg").attr("src", "");
			$("#leaveTypeCheck").empty();
			$(".surplusHours").empty();
			$(".sumHours").empty();
		}

		function checkCause() {
			var inputCause = $("#idCause").val();

			if (inputCause.length > 50) {
				$("#causeImg").attr("src", "images/error.png");
				$("#causeCheck").html("不可超過50字").attr("style", "color:red;");
			} else {
				$("#causeImg").attr("src", "");
				$("#causeCheck").empty();
			}
		}

		function checkSubmit() {
			var inputLT = $("#idLT").val();
			var checkSubLT = $("#leaveTypeCheck").text();
			var checkSubCause = $("#causeCheck").text();

			if (inputLT == "") {
				$("#leaveTypeImg").attr("src", "images/error.png");
				$("#leaveTypeCheck").html("請選擇假別").attr("style", "color:red;");
				return false;
			} else {
				if (checkSubCause == "" && checkSubLT == "") {
					return true;
				} else {
					return false;
				}
			}
		}
	</script>

</body>
</html>