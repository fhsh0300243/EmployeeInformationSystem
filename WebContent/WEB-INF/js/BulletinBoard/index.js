$(document).ready(function() {
	
	queryBoard();
	
	
	function queryBoard() {
		$("#board").html("");
		var str = "";
		$.ajax({
			url : "http://localhost:8080/EmployeeInformationSystem/queryBulletinForLook",
			type : "get",
//			dataType : "json",
			cache : false,
			success : function(rs) {
				console.log("sucess");
				console.log("rs.length:"+rs.length);
				var rs2 =  JSON.parse(rs);
				for (var i = 0; i < rs2.length; i++) {

					str += '<div class="card">'
						+'<div class="card-header" id="heading'+i+'">'
						+'<h2 class="mb-0">'
						+'<button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">'
						+rs2[i].Title+'</button></h2></div>'
						+' <div id="collapse'+i+'" class="collapse" aria-labelledby="heading'+i+'" data-parent="#board">'
						+'<div class="card-body">'
						+rs2[i].Context+'</div></div></div>'
				}
		
				$("#board").html(str);
				
			},
			error : function(rs) {
				console.log("error");
				
			}

		})
	}

})