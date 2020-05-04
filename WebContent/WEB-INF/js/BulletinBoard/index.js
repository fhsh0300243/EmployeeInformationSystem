$(document).ready(function() {
	
	queryBoard();
	
	
	function queryBoard() {
		$("#board").html("");
		var str = "";
		$.ajax({
			url : "http://localhost:8080/EmployeeInformationSystem/queryBulletinForLook",
			type : "get",
			cache : false,
			success : function(rs) {
				console.log("sucess");
				console.log("rs.length:"+rs.length);
				var rs2 =  JSON.parse(rs);
				for (var i = 0; i < rs2.length; i++) {
					
					str += '<div class="card">'
						+'<div class="card-header" id="ForLook'+i+'">'
						+'<h2 class="mb-0">'
						+'<button class="btn btn-link" type="button" data-toggle="collapse" data-target="#dataForLook'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">'
						+'<span class="mx-4">['+rs2[i].Department+']'+rs2[i].Title+'</span>'
						+'<span>公告日期:</span><span>' + rs2[i].upTime + '</span>'
						+'</button></h2></div>'
						+' <div id="dataForLook'+i+'" class="collapse" aria-labelledby="ForLook'+i+'" data-parent="#result">'													
						+'<div class="card-body">'
						+'<p>內容:</p>'
						+'<div class="border w70 center">' + rs2[i].Context + '</div>'
						
						if(rs2[i].AttachedFilesName == undefined){
							str	+='<span>附件:</span><p>無</p>'												
								+'</div></div></div>'
						}
						else{
							str	+='<span>附件:</span><p><a class="AttachedFilesName" href="http://localhost:8080/EmployeeInformationSystem/download?BulletinBoardID='+rs2[i].BulletinBoardID+'&fileName='+rs2[i].AttachedFilesName+'">' + rs2[i].AttachedFilesName + '</a></p>'												
							+'</div></div></div>'
						}
				}
		
				$("#board").html(str);
				
			},
			error : function(rs) {
				console.log("error");
				
			}

		})
	}

})