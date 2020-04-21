$(document).ready(
				function() {
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
												+'<div class="card-header" id="heading'+i+'">'
												+'<h2 class="mb-0">'
												+'<button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">'
												+rs2[i].Title
												+'</button></h2></div>'
												+' <div id="collapse'+i+'" class="collapse" aria-labelledby="heading'+i+'" data-parent="#result">'													
												+'<div class="card-body">'
												+rs2[i].Context
												+'<p>權限:' + rs2[i].Authority + '</p>'
												+'<p>上線日期:' + rs2[i].upTime + '</p>'
												+'<p>下架日期:' + rs2[i].downTime + '</p>'
												+'<p>創造日期:' + rs2[i].Date + '</p>'
												+'</div></div></div>'
											
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
			
											str += '<div class="card">'
												+'<div class="card-header" id="heading'+i+'">'
												+'<h2 class="mb-0">'
												+'<button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">'
												+rs2[i].Title
												+'</button></h2></div>'
												+' <div id="collapse'+i+'" class="collapse" aria-labelledby="heading'+i+'" data-parent="#checkData">'													
												+'<div class="card-body" id="'+rs2[i].BulletinBoardID+'">'
												+'<p class="cardContext">' + rs2[i].Context + '</p>'
												+'<span>權限:</span><p class="cardAuthority">' + rs2[i].Authority + '</p>'
												+'<span>上線日期:</span><p class="cardupTime">' + rs2[i].upTime + '</p>'
												+'<span>下架日期:</span><p class="carddownTime">' + rs2[i].downTime + '</p>'
												+'<span>創造日期:</span><p class="cardDate">' + rs2[i].Date + '</p>'
												
												if(rs2[i].AttachedFilesName == undefined){
												str	+='<span>附件:</span><p>無</p>'												
												}
												else{
												str	+='<span>附件:</span><p><a class="AttachedFilesName" href="http://localhost:8080/EmployeeInformationSystem/download?BulletinBoardID='+rs2[i].BulletinBoardID+'&fileName='+rs2[i].AttachedFilesName+'">' + rs2[i].AttachedFilesName + '</a></p>'												
												}
												
													
											str +='<a class="btn btn-primary change " id="ch'  + i + '" data-toggle="tab" href="#update" role="tab" aria-controls="update">更改</a>'
												+'<input type="button" class="btn btn-primary del mx-1" id="de' + i + '" value="刪除">'
												+'</div></div></div>'
										
										}
										$("#checkData").html(str);	

										$(document).on("click",".card-body a.change",function() {
											var ind = $(".card-body a.change").index(this)
											var idd = $(this).attr("id")
											$("#contact-tab").addClass("active");
											$("#profile-tab").removeClass("active");
											console.log("ind.change:"+ind);
											console.log("idd.change:"+idd);
											
											$("#BulletinBoardid").val($(".card-body").eq(ind).attr("id"))
											alert($("#BulletinBoardid").val())
											$("#title").val($(".card-header .btn-link").eq(ind).html());
											$("#editor").htmlcode($(".card-body .cardContext").eq(ind).html());
											$("#uptime").val($(".card-body .cardupTime").eq(ind).html());
											$("#downtime").val($(".card-body .carddownTime").eq(ind).html());
											
											
											console.log($(".card-body .cardAuthority").eq(ind).html());
											
											
									
										})
										
										$(document).on("click",".card-body input.del",function() {
											var ind = $(".card-body input.del").index(this)
											var idd = $(this).attr("id")
											console.log("ind.del:"+ind);
											console.log("idd.del:"+idd);
										})
					
									},
									error : function(rs) {
										console.log("error")
									}

								})
						}

					
				})