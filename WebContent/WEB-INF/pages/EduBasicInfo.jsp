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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
<link rel="stylesheet" href="css/amazeui.min.css">
<link rel="stylesheet" href="css/common.css">

<link rel="stylesheet" type="text/css" href="css/mainCSS.css">

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

<script src="js/jquery.min.js"></script>
<script src="js/handlebars.min.js"></script>
<script src="js/amazeui.widgets.helper.min.js"></script>

<!-- test1 -->
<script src="js/datepicker-zh-TW.js"></script>
<!-- test2 -->
<link href="css/fontawesome.css" rel="stylesheet">
   
    <link href="css/style.css" rel="stylesheet">
    <link href="css/jquery.alerts.min.css" rel="stylesheet" />
    
    <link href="css/jquery-ui.min.css" rel="stylesheet" />

    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/polyfill.min.js"></script>
    <script src="js/popper.js" charset="utf-8"></script>
    <script src="js/bootstrap_4.1.1.js"></script>
    <script src="js/all.js" charset="utf-8"></script>
    <script src="js/vue.min.js"></script>
    <script src="js/axios.min.js"></script>
    <script src="js/jquery.alerts.min.js"></script>
    
    <script src="js/jquery-ui-1.12.1-chinese.js"></script>
    
    <script src="js/vue-bs-pager-front.js"></script>

<!-- test2 end -->
<!-- test3 -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-131294839-5"></script>
    <script>
        window.dataLayer = window.dataLayer || [];
        function gtag(){dataLayer.push(arguments);}
        gtag('js', new Date());

        gtag('config', 'UA-131294839-5');
    </script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- test3 end -->
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

b{
	font-size:20px;
}

/* iframe */
/* #parent {
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
} */
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

				<%@ include file="SubFeatureForEDU.jsp"%>

			</div>

			<!--右邊欄位-->
			<div class="col-sm-8">
				<div class="panel panel-primary">
					<p class="functionTitle">教育訓練</p>
					<div class="panel-heading">
					<%@ include file="MainFeatureTopBar.jsp"%></div>
					<div class="panel-body">

						<div class="am-text-lg am-text-center"></div>


						<div class="introduce_wrap mt60">


	<!-- header -->
	<div class="mark am-text-lg am-text-center">
		<span class=""><img src="images/query.png" width=2.5% ></span> &nbsp;查詢課程

	</div>

	<div class="basicInfo_wrap">
		<!-- banner -->
		<div class="am-container">
			<img src=""
				class="am-img-responsive" width=10% alt="" />
		</div>
		<!-- address -->
		<!-- <div class="am-cf my_address">
		<div class="am-text-sm am-fl col6">地點：中區訓練中心 會議室A203</div>
							     
		<div id="demo" style="visibility:;"> 
	    <iframe id="demo" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d394.6255503842566!2d120.65072081200775!3d24.15056066749464!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x34693d964fff6dc5%3A0x5d526ce61b5b9ca6!2zNDA45Y-w5Lit5biC5Y2X5bGv5Y2A5YWs55uK6Lev5LqM5q61NTHomZ8!5e0!3m2!1szh-TW!2stw!4v1588560237251!5m2!1szh-TW!2stw" aria-hidden="flase" tabindex="0"></iframe>
		</div>
								
		<div>
		<button id="btn1" type="button" class="am-btn am-round am-btn-xs am-btn-primary am-fr col3">地圖查看</button>
		</div>
		</div> -->
		
	
		<!-- test -->
		<div class="search_area_1">
        <form class="form-inline">
            <input name="__RequestVerificationToken" type="hidden" value="bD_q6xqk_n_dNsV41zH1_yavMkfI3PpEaIABDZZzuYcRl0l4DI5oZqcGmw1gWI19il39tLPOO-4rrWiw7PLsy595_RR-LJHlhjD6GYQzzZw1" />
            <div class="form-group w-100">
                <label for="SDate">開課日期：</label>
                <input type="date" id="idStartDate" name="startdate"
					class="" placeholder="開課起日" v-model="SDate">
                ~
                <label class="sr-EDate" for="EDate">結束日期：</label>
                <input type="date" id="idStartDate" name="startdate" type="text" id="EDate" class="" name="date_end" placeholder="開課迄日" v-model="EDate">
                &emsp;
                <label for="Department">開課部門：</label>
                <select class="form-control my-2 mx-sm-3" v-model="Department" id="Department">
                    <option value="">不分部門(不拘)</option>
                    <option value="HR">HR</option>
					<option value="RD">RD</option>
					<option value="QA">QA</option>
					<option value="Sales">Sales</option>
					<option value="PM">PM</option>
                    <!-- <option v-for="item in DepartmentList" :value="item.No" v-text="item.Name"></option> -->
                </select>
            </div>
            &emsp;
            <div class="form-group w-100">
                <button type="button" class="btn btn-info" id="datepicker1" onclick="Search()" @click="Search()" name="check">查詢</button>
                <button type="button" class="btn btn-info" id="datepicker2" onclick="SearchInOneMonth()" @click="SearchInOneMonth()" name="checkmonth">僅顯示一個月內課程</button>
            </div>
        </form>
    </div>
		
		
		<!-- 培訓訊息 -->
		<div>
		<br/>
		</div>
		<hr/>
		
	
		<div2 class="am-container" hidden="" onclick="Search()" id="datepicker1" name="check">
			<div class="am-panel am-panel-primary">
			
				<div class="am-panel-hd">培訓課程</div>
				<div class="am-panel-bd col9">
					<div class='am-text-sm'>
						培訓課程日期：<span>2020/05/11-2020/05/12</span>
					</div>
					<div class='am-text-sm'>
						報到時間：<span>07:50</span>
					</div>
					<div class='am-text-sm'>
						上課時間：<span>08:00-17:00</span>
					</div>
					<div class='am-text-sm'>
						人數上限：<span>40人</span>
					</div>
					<div class='am-text-sm'>
						培訓課程類別：<span>人資相關</span>
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
					<p class='am-text-sm'>
						 <img
							src="images/class.png"
							width='3%' alt=""/>&emsp;目前培訓課程 
					</p>

				</div>
			</div>
			

		</div>

		    </div>
		    </div>
		    </div>
		    </div>
			
				</div>
			

<script src="js/amazeui.min.js"></script>

			<div class="CanNotRightDownDiv" style="position:absolute;top:340px;left:0px;">
				<img class="CanNotRightDown" src="images/CompanyLogo.png" style="position:absolute;top:10px;left:0px;">
			</div>
	
			
			

	<script>
		var depts;
		$('#searchdept').empty();
		$.ajax({
			url : "DeptList",
			type : "GET",
			success : function(Str) {
				depts = JSON.parse(Str);
				$('#searchdept').append("<option value=''>選擇部門</option>");
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
			atag = "<a href=# name='1' onclick='f(this)'>" + 1 + "</a> ";
			for (let i = 2; i <= pagesTotal; i++) {
				atag += "<a href=# name='" + i + "' onclick='f(this)'>" + i
						+ "</a> ";
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
						+ emps[i].empID + "'/>' name='" + emps[i].empID
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
								+ $("#searchname").val() + "&searchdept="
								+ $("#searchdept").val(),
						type : "GET",
						success : function(Str) {
							emps = JSON.parse(Str);
							pagination(emps, nowpage);
						}
					});
				});
	</script>
	
	<!-- test -->
	<script type="text/javascript">
        $(document).ready(function () {

            $("#datepicker").datepicker($.datepicker.regional["zh-TW"]);
            $('.datetime').datepicker({
                dateFormat: "R/mm/dd",
                monthNamesShort: ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
                changeMonth: true,
                changeYear: true,
                yearRange: "-100:+0",
            }).on("change", function (e) {
                vm.$data[$(this).attr("id")] = $(this).val();
            });

        });
        </script>
<script>
        var vm = new Vue
            ({
                methods: {
                    pageChange: function (num) {
                        var self = this;
                        self.pageIndex = num;
                        axios.post('/api/Recurrent/List', {
                            page: self.pageIndex,
                            SDate: self.SDate,
                            EDate: self.EDate,
                            Department: self.Department,
                            InOneMonth: self.InOneMonth,
                        }).then(function(response) {
                            var data = response.data;
                            self.itemlist = data.Data;
                            self.totalcount = data.Total;
                        }).catch(function (error) {
                            if (error.response.status === 400) {
                                var err = [];
                                for (var i in error.response.data.ModelState) {
                                    err.push(error.response.data.ModelState[i]);
                                }
                                var message = err.join().replace(/,/g, '\n');
                                jAlert(message, '警告');
                            }
                        });
                        //return false;
                    },
            </script>
            <script>        
                    Search: function () {
                        this.InOneMonth = "";
                        this.pageChange(1);
                    },
                    SearchInOneMonth: function () {
                        this.SDate = "";
                        this.EDate = "";
                        this.Department = "";
                        this.InOneMonth = "1";
                        this.pageChange(1);
                    },
                    ClearSearch: function() {
                        this.SDate = "";
                        this.EDate = "";
                        this.Department = "";
                        this.InOneMonth = "";
                        this.pageChange(1);
                    }
                   
                    Download: function () {
                        axios.post('/api/Files/Recurrent', {
                        },
                        {
                            responseType: 'blob',
                        }).then(function(x) {
                            if (x.status == 200) {
                                var f = decodeURIComponent(x.headers["content-disposition"]);
                                f = /filename=([^=:]+\.\S+)/gi.exec(f);
                                //-- 檔案下載
                                var blob = new Blob([x.data], { type: 'application/octet-stream' });
                                var downloadUrl = URL.createObjectURL(blob); 
                                var a = document.createElement("a");
                                a.href = downloadUrl;
                                a.download = f[1];
                                document.body.appendChild(a);
                                a.click();
                            }

                        }).catch(function (error) {
                            jAlert('下載失敗!', '警告');
                        });
                    },
                },
                mounted: function() {
                    this.pageChange(1);
                },

            });
</script>

<script type="text/javascript">
function Search() {
	  var x = document.getElementById("datepicker1");
	  if (x.style.display == "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}
</script>

<script>
$(document).ready(function(){
  $("#hide").click(function(){
    $("p").hide();
  });
  $("#datepicker1").click(function(){
    $("div2").show();
  });
  $("#datepicker2").click(function(){
	    $("div2").show();
	  });
});
</script>

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