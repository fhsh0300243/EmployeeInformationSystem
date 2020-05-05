$(document)
		.ready(
				function() {
					var oldDate = "";
					var newDate = "";
					var num1;
					var num2;
					var num3;
					var num4;
					var num5;
				
					
//					checkDate();
					function checkDate() {
						$.ajax({
									url : "http://localhost:8080/EmployeeInformationSystem/checkDate",
									type : "get",
									cache : false,
									async : false,
									success : function(rs) {
										var rs2 = JSON.parse(rs);
										oldDate = rs2[0].oldDate;
										newDate = rs2[0].newDate;
										
											if (oldDate == null) {
												console.log();
												$("#maintext").html("你是第一次登入對吧~歡迎來到番茄科技");
												querybullboard();
												$('#myModal').modal();
											} else {
												oldjsDate = new Date(oldDate);
												newjsDate = new Date(newDate);
												
//												if (((newDate - oldDate) / (1000 * 86400)) > 1) {
												query();
//												querybullboard();
												querysucess(oldDate,newDate);
												querysucessApplyForLeave(oldDate,newDate);
												queryNewApply();
												
												console.log("num1:"+num1);
												console.log("num2:"+num2);
												console.log("num3:"+num3);
												console.log("num4:"+num4);
//												console.log("num5:"+num5);
												
												if((num1+num2+num3+num4)==0){
													$("#maintext").html("在你昨天下班之後~沒有新消息!");
												}else if(num1+num2!=0){
													$("#maintext").html("在你昨天下班之後~");
													$("#feetext").html("差旅費部分:");
												}else if(num3+num4!=0){
													$("#maintext").html("在你昨天下班之後~");
													$("#feetext").html("請假申請部分:");
												}
												
												
												$('#myModal').modal();
												}
//											}
									},
									error : function(rs) {
										console.log("error");
									}
								})
					}

					function query() {
						$.ajax({
									url : "http://localhost:8080/EmployeeInformationSystem/queySingerPage",
									type : "get",
									cache : false,
									async : false,
									dataType : "text",
									success : function(rs) {
										console.log("sucess");
										var Num = parseInt(rs);
										if (Num != 0) {
											$("#modaltext")
													.html(
															"有"+ Num+ "個下屬新申請的差旅費申請尚未被簽核!");
										}
										num1=Num;
									},
									error : function(rs) {
										alert("error");
									}
								})
					}
					
					function querysucess(oldDate,newDate) {
						$.ajax({
									url : "http://localhost:8080/EmployeeInformationSystem/querysucess",
									type : "get",
									cache : false,
									async : false,
									dataType : "text",
									data : {
										"oldDate":oldDate,
										"newDate":newDate
									},
									success : function(rs) {
										console.log("sucess");
										var Num = parseInt(rs);
										if (Num != 0) {
											$("#querysucess")
													.html(
															"你提出的"+Num+ "個差旅費申請已被簽核完成!");		
										}
										num2=Num;
										
									},
									error : function(rs) {
										alert("error");
									}
								})
					}
//					請假申請簽核
					function queryNewApply(){
						$.ajax({
							url : "http://localhost:8080/EmployeeInformationSystem/queryNewApply",
							type : "get",
							cache : false,
							async : false,
							dataType : "text",
							success : function(rs) {
								console.log("sucess");
								var Num = parseInt(rs);
								if (Num != 0) {
									$("#queryNewApply").html("有"+ Num+ "個下屬新申請的請假申請尚未被簽核!");		}
								num3=Num;
								
							},
							error : function(rs) {
								alert("error");
							}
						})
					}
//					請假簽核完成
					function querysucessApplyForLeave(oldDate,newDate){
						$.ajax({
							url : "http://localhost:8080/EmployeeInformationSystem/querysucessApplyForLeave",
							type : "get",
							cache : false,
							async : false,
							dataType : "text",
							data : {
								"oldDate":oldDate,
								"newDate":newDate
							},
							success : function(rs) {
								console.log("sucess");
								var Num = parseInt(rs);
								if (Num != 0) {
									$("#querysucessApplyForLeave")
											.html(
													"你提出的"+Num+ "個請假申請已被簽核完成!");		
								}
								num4=Num;
								
							},
							error : function(rs) {
								alert("error");
							}
						})
					}
					
					
					
					
					
					
					
					
					

//					function querybullboard() {
//						$
//								.ajax({
//									url : "http://localhost:8080/EmployeeInformationSystem/reflash",
//									type : "get",
//									dataType : "text",
//									cache : false,
//									async : false,
//									success : function(rs) {
//										var Num = parseInt(rs);
//										if (Num != 0) {
//											$("#bullboardtext").html(
//													"有" + Num + "個新的布告欄通知!");
//										}
//										console.log("Num3"+Num)
//										num5=Num;
//										console.log("num3"+num3)
//										
//									},
//									error : function(rs) {
//										console.log("reflash error");
//									}
//
//								})
//					}

				})