<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>子功能</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css" href="css/mainCSS.css">
<style>
.well, .panel {
	text-align: center;
}

.title {
	font-weight: bold;
}

</style>
</head>
<body>
	<div class="panel panel-primary" id="idLevel" name="${empLevel}">
		<div class="panel-heading">相關功能</div>
		<div class="panel-body">
		
		<div class="title">個人專區</div>
		
			<div>
				<a href="<c:url value="/EdumyTrain.do"/>">參加培訓課程</a>
			</div>
			<div>
				<a href="<c:url value="/EduBasicInfo.do"/>">查詢課程</a>
			</div>
			<%-- <div>
				<a href="<c:url value="/EduDeleteCoursePage.do"/>">刪除課程</a>
			</div> --%>
			<%-- <div>
				<a href="<c:url value="/EduNotice.do"/>">課程訊息通知</a>
			</div> --%>
			<div>
				<a href="<c:url value="/EduEmpComment.do"/>">課程滿意度評價</a>
			</div>
		</div>
		
		<div id="man" name="${LoginOK.getEmployee().getLevel()}" style="display: none;">
		        <div class="title">主管專區</div>
		        
		        <div>
				<a href="<c:url value="/EduAddCoursePage.do"/>">新增課程</a>
			    </div>

				<div>
					<a href="<c:url value="/EduUnsigned.do"/>">未簽核課程</a>
				</div>
				<div>
					<a href="<c:url value="/EduSigned.do"/>">已簽核課程</a>
				</div>
				<br/>
		
		
		
		<div class="panel-footer"></div>
	</div>
	</div>
	
	<script src="js/jquery-3.4.1.min.js"></script>
	
	<script>
		$(function() {
			var level = $("#man").attr("name");
			if (level != 1) {
				$("#man").show()
			}
		});
	</script>
	 
</body>
</html>