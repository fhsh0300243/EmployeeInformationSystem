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
<link rel="stylesheet" type="text/css" href="css/DetailPage.css">
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
					<p class="functionTitle">請假簽核</p>
					<div class="panel-heading"><%@ include
							file="MainFeatureTopBar.jsp"%></div>

					<table id="idtable2">
						<tr>
							<td class="tdtag">申請時間：</td>
							<td>${ApplyList.createTime}</td>
						</tr>
						<tr>
							<td class="tdtag">部門：</td>
							<td>${ApplyList.employeeId.empDept.deptName}</td>
						</tr>
						<tr>
							<td class="tdtag">職位：</td>
							<td>${ApplyList.employeeId.empTitle.titleChName}</td>
						</tr>
						<tr>
							<td class="tdtag">申請人：</td>
							<td>${ApplyList.employeeId.name}</td>
						</tr>
						<tr>
							<td class="tdtag">請假類別：</td>
							<td>${ApplyList.leaveType}</td>
						</tr>
						<tr>
							<td class="tdtag">開始時間：</td>
							<td>${ApplyList.startTime}</td>
						</tr>
						<tr>
							<td class="tdtag">結束時間：</td>
							<td>${ApplyList.endTime}</td>
						</tr>
						<tr>
							<td class="tdtag">總計時數：</td>
							<td>${ApplyList.sumHours}</td>
						</tr>
						<tr>
							<td class="tdtag">事由：</td>
							<td>${ApplyList.cause}</td>
						</tr>
						<tr>
							<td class="tdtag">附件：</td>
							<td id=noAtt><img id="attImg" title="點擊圖片，即可於下方放大檢視。"
								src="<c:url value="/preAttImage?applyId=${ApplyList.applyId}"/>"></td>
						</tr>
					</table>

					<div id="dialog_pic"></div>

					<hr />
					<form class="for1"
						action="<c:url value="/signforleave?applyId=${ApplyList.applyId}"/>"
						method="post">
						<table id="idtable3">
							<tr>
								<td class="tdtag"><span class="span1">*</span>簽核：</td>
								<td><input type="radio" name="sign" value="yes"
									onclick="radioClick()" />同意 <input type="radio" name="sign"
									value="no" onclick="radioClick()" />不同意</td>
							</tr>
							<tr>
								<td></td>
								<td><img id="signImg"><span id="signCheck"></span></td>
							</tr>
							<tr>
								<td class="tdtag">意見：</td>
								<td><textarea cols="45" rows="5" id="idComment"
										name="comment" onblur="checkComment();"></textarea></td>
							</tr>
							<tr>
								<td></td>
								<td class="tdNotes">字數限制：100字。</td>
							</tr>
							<tr>
								<td></td>
								<td><img id="commentImg"> <span id="commentCheck"></span></td>
							</tr>

						</table>
						<hr />

						<div class="btn1">
							<input type="reset" value="清除" onclick="cls();" class="btn btn-info"/> <input
								type="submit" value="送出" onclick="return checkSubmit();" class="btn btn-info"/>
						</div>
					</form>
					<div class="list_footer">
						<div id="tag"></div>
						<div id="page"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="CanNotRightDownDiv">
		<img class="CanNotRightDown" src="images/CompanyLogo.png">
	</div>

	<script src="js/jquery-3.4.1.min.js"></script>
	<script>
		//$("#attImg").click(function() {
		//	var applyId = $(this).attr("name");
		//	location.href = "getFullImg?applyId=" + applyId;
		//});

		$("#attImg")
				.click(
						function() {
							$(this).hide();
							var str = "<img class='result' width=100% title='再次點擊圖片，即可縮回。' src='<c:url value='/preAttImage?applyId=${ApplyList.applyId}'/>'>";
							$("#dialog_pic").html(str);
						});

		$("#dialog_pic").on("click", ".result", function() {
			$(this).remove();
			$("#attImg").show();
		});

		$(function() {
			$("#idtable2 td:empty").text("-");
		});

		$("#attImg").on("error", function() {
			$("#noAtt").text("-");
		});

		function radioClick() {
			$("#signImg").attr("src", "");
			$("#signCheck").html("");
		}

		function checkComment() {
			var inputComment = $("#idComment").val();

			if (inputComment.length > 100) {
				$("#commentImg").attr("src", "images/X_icon.png");
				$("#commentCheck").html("不可超過100字").attr("style", "color:red;");
			} else {
				$("#commentImg").attr("src", "");
				$("#commentCheck").html("")
			}
		}

		function checkSubmit() {

			checkComment();

			var checkSubSign = $("#signCheck").text();
			var checkSubComment = $("#commentCheck").text();
			var radioVal = $('input:radio[name="sign"]:checked').val();

			if (radioVal == null) {
				$("#signImg").attr("src", "images/X_icon.png");
				$("#signCheck").html("請選擇簽核項目").attr("style", "color:red;");
				return false;
			} else {
				if (checkSubSign == "" && checkSubComment == "") {
					return true;
				} else {
					return false;
				}
			}
		}

		function cls() {
			$("#signImg").attr("src", "");
			$("#signCheck").html("");
			$("#commentImg").attr("src", "");
			$("#commentCheck").html("");
		}
	</script>

</body>
</html>