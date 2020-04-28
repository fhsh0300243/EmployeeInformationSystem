$(document)
		.ready(
				function() {
					var oldDate = "";
					var newDate = "";

					checkDate();
					function checkDate() {
						$
								.ajax({
									url : "http://localhost:8080/EmployeeInformationSystem/checkDate",
									type : "get",
									cache : false,
									async : false,
									success : function(rs) {
										var rs2 = JSON.parse(rs);
										oldDate = rs2[0].oldDate;
										newDate = rs2[0].newDate;

										oldDate = new Date(oldDate);
										newDate = new Date(newDate);
										if (((newDate - oldDate) / (1000 * 86400)) > 1) {
											if (oldDate == null) {
												console.log();
												$("#maintext").html("安安，新人");
												querybullboard();
												$('#myModal').modal();
											} else {
												query();
												querybullboard();
												$('#myModal').modal();
											}
										}
									},
									error : function(rs) {
										console.log("error");
									}
								})
					}

					function query() {
						$
								.ajax({
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
															"有"
																	+ Num
																	+ "個下屬的差旅費還沒被簽核!");
										}
									},
									error : function(rs) {
										alert("error");
									}
								})
					}

					function querybullboard() {
						$
								.ajax({
									url : "http://localhost:8080/EmployeeInformationSystem/reflash",
									type : "get",
									dataType : "text",
									cache : false,
									success : function(rs) {
										var Num = parseInt(rs);
										if (Num != 0) {
											$("#bullboardtext").html(
													"有" + Num + "個新的布告欄通知!");
										}
									},
									error : function(rs) {
										console.log("reflash error");
									}

								})
					}

				})