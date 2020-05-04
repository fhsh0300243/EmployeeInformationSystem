$(document).ready(function() {
	
	var oldNum;
	
	var TestNum = 0;
	
	reflash();
	setInterval(function () {reflash();}, 5000);
	
	
	function reflash() {
		$.ajax({
			url : "http://localhost:8080/EmployeeInformationSystem/reflash",
			type : "get",
			dataType : "text",
			cache : false,
			success :function(rs){
				console.log("reflash sucess");
				console.log("rs:" + rs);
				var NewNum = parseInt(rs);
				if(TestNum==0){
					oldNum=NewNum;
					TestNum++;
				}
				if(oldNum<NewNum){
					alert("有"+(NewNum-oldNum)+"個新通告，請至布告欄查看");
					oldNum=NewNum;
				}
//				console.log("oldNum:" + oldNum);
//				console.log("NewNum:" + NewNum);
				
				
			},
			error : function(rs){
				console.log("reflash error");
			},

		})
	}

})