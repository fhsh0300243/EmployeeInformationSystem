$(document).ready(
		
				function() {
					var path = location.pathname.split("/");
					if(path[2]=="insert"){
						$("#profile-tab").tab('show');
					}
					
					console.log(path);
					$("#tttttt").click(function() {
						alert("$('#file').val():" + $('#file').val())
					})
					
					$("input[type='radio']").click(function() {
						var data = $(this).val();
						if(data=="parallel"){
							$(".depAuthority").css("display","inline");
							console.log("inline");
						}else{
							$(".depAuthority").css("display","none");
							console.log("none");
						}
					})
					
					$("input[type='checkbox']").click(function() {
						console.log($(this).val());
					})
					
					
					
					$("#insertData").click(function() {
						$(".content").val($("#editor").htmlcode())
					})
					
					$("#clear").click(function(){
						$("#BulletinBoardid").val("");
						$("#editor").htmlcode("");
						
					})
					

					
					result();
					function result() {
						$("#result").html("");
						var str = "";

						$.ajax({
									url : "http://localhost:8080/EmployeeInformationSystem/queryBulletinRecord",
									type : "get",
									cache : false,
									async : false,
									success : function(rs) {
										var rs2 =  JSON.parse(rs);
										console.log("sucess");
										console.log("rs2.length:" + rs2.length);
										for (var i = 0; i < rs2.length; i++) {

											str += '<div class="card">'
												+'<div class="card-header" id="Record'+i+'">'
												+'<h2 class="mb-0">'
												+'<button class="btn btn-link" type="button" data-toggle="collapse" data-target="#dataRecord'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">'
												+'<span class="mx-4">['+rs2[i].Department+']'+rs2[i].Title+'</span>'
												+'<span class="mx-1">分發至:</span><span class="cardAuthority">' + rs2[i].Authority + '</span>'
												+'</button></h2></div>'
												+' <div id="dataRecord'+i+'" class="collapse" aria-labelledby="Record'+i+'" data-parent="#result">'													
												+'<div class="card-body">'
												+'<p>內容:</p>'
												+'<div class="cardContext border w70 center">' + rs2[i].Context + '</div>'
												+'<div class="row w70 center">'
												+'<div class="col border"><p>上線日期:</p><div>' + rs2[i].upTime + '</div></div>'
												+'<div class="col border"><p>下架日期:</p><div>' + rs2[i].downTime + '</div></div>'
												+'<div class="col border"><p>創造日期:</p><div>' + rs2[i].Date.split("\.",1) + '</div></div>'
												+'</div>'
												
												if(rs2[i].AttachedFilesName == undefined){
													str	+='<span>附件:</span><p>無</p>'												
														+'</div></div></div>'
												}
												else{
													str	+='<span>附件:</span><p><a class="AttachedFilesName" href="http://localhost:8080/EmployeeInformationSystem/download?BulletinBoardID='+rs2[i].BulletinBoardID+'&fileName='+rs2[i].AttachedFilesName+'">' + rs2[i].AttachedFilesName + '</a></p>'												
													+'</div></div></div>'
												}
											
										}

										$("#result").html(str);
									},
									error : function(rs) {
										console.log("error")
									}

								})
					}
					
						checkData();
						function checkData() {
						$("#checkData").html("");
						var str = "";
						var checkDatanow="";
						var checkDatawilluping="";
						
						
						$.ajax({
									url : "http://localhost:8080/EmployeeInformationSystem/checkdata",
									type : "get",
									cache : false,
									async : false,
									success : function(rs) {
										console.log("sucess");
										var rs2 =  JSON.parse(rs);
										console.log("rs2.length:" + rs2.length);
										
										for (var i = 0; i < rs2.length; i++) {
											var upTime = new Date(rs2[i].upTime);
											var nowtime = new Date(); 
											var dataparent="";
											if((parseInt(nowtime - upTime))>0){
												dataparent ="checkDatanow";
											}else{
												dataparent ="checkDatawilluping";
											}
		
											str += '<div class="card">'
												+'<div class="card-header" id="heading'+i+'">'
												+'<h2 class="mb-0">'
												+'<button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">'
												+'<span class="cardtitle mx-4">'+rs2[i].Title+'</span>'
												+'<span class="mx-1">分發至:</span><span class="cardAuthority">' + rs2[i].Authority + '</span>'
												+'</button></h2></div>'
												+' <div id="collapse'+i+'" class="collapse" aria-labelledby="heading'+i+'" data-parent="#'+dataparent+'">'													
												+'<div class="card-body idName" id="'+rs2[i].BulletinBoardID+'">'
												+'<p>內容:</p>'
												+'<div class="cardContext border w70 center">' + rs2[i].Context + '</div>'
												+'<div class="row w70 center">'
												+'<div class="col border"><p>上線日期:</p><div class="cardupTime">' + rs2[i].upTime + '</div></div>'
												+'<div class="col border"><p>下架日期:</p><div class="carddownTime">' + rs2[i].downTime + '</div></div>'
												+'<div class="col border"><p>創造日期:</p><div>' + rs2[i].Date.split("\.",1) + '</div></div>'
												+'</div>'
												if(rs2[i].AttachedFilesName == undefined){
												str	+='<span>附件:</span><p>無</p>'												
												}
												else{
												str	+='<span>附件:</span><p><a class="AttachedFilesName" href="http://localhost:8080/EmployeeInformationSystem/download?BulletinBoardID='+rs2[i].BulletinBoardID+'&fileName='+rs2[i].AttachedFilesName+'">' + rs2[i].AttachedFilesName + '</a></p>'												
												}
												
													
											str +='<a class="btn btn-primary change " id="ch'  + i + '">更改</a>'
												+'<input type="button" class="btn btn-primary del mx-1" id="de' + i + '" value="刪除">'
												+'</div></div></div>'
												
												
												
												if((parseInt(nowtime - upTime))>0){
													checkDatanow+=str;
													str="";
												}else{
													checkDatawilluping+=str;
													str="";
												}
												
										
										}
										$("#checkDatanow").html(checkDatanow);	
										$("#checkDatawilluping").html(checkDatawilluping);	

										$(document).on("click",".card-body a.change",function() {
											var ind = $(".card-body a.change").index(this)
											var idd = $(this).attr("id")
																				
											$("#BulletinBoardid").val($(".idName").eq(ind).attr("id"))
		
											
											$("#title").val($(".card-header .btn-link .cardtitle").eq(ind).html());
											$("#editor").htmlcode($(".card-body .cardContext").eq(ind).html());
											$("#uptime").val($(".card-body .cardupTime").eq(ind).html());
											$("#downtime").val($(".card-body .carddownTime").eq(ind).html());

											$("#contact-tab").tab('show');
											
											var Authority = $(".btn-link .cardAuthority").eq(ind).html();
											Authorities = Authority.split("\,");
											for(var i=0;i<Authorities.length;i++){
												Authority = Authorities[i];
												if(Authority=="HR"){
													$("#HR").prop("checked", true)
												}
												if(Authority=="RD"){
													$("#RD").prop("checked", true)
												}
												if(Authority=="Test"){
													$("#Test").prop("checked", true)
												}
												if(Authority=="Sales"){
													$("#Sales").prop("checked", true)
												}
												if(Authority=="PM"){
													$("#PM").prop("checked", true)
												}
												
												
											}
											
											
									
										})
										
										$(document).on("click",".card-body input.del",function() {
											var ind = $(".card-body input.del").index(this)
											var idd = $(this).attr("id")
											var idName = $(".idName").eq(ind).attr("id");
											console.log("ind.del:"+ind);
											console.log("idd.del:"+idd);
											console.log("idName:"+idName);
											
											if(confirm("確定要刪除?")){
											$.ajax({
												url : "http://localhost:8080/EmployeeInformationSystem/delete",
												type : "get",
												cache : false,
												async : false,
												data:{"BulletinBoardid":idName},
												success: function(rs){
													checkData();
													setTimeout(alert("刪除成功"),10000);
												},
												error:function(rs){
													console.log("error")
												}
											})
											}
											
										})
					
									},
									error : function(rs) {
										console.log("error")
									}

								})
						}
						
						
						
						
						
						
						
						
					
				})