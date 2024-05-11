/**
 * 
 */

 $(document).ready(function(){
	 $(".btn-add").click(function(e){
		 var name = $("#name").val()
		 var startDate = $("#startDate").val()
		 var endDate = $("#endDate").val()
		 
        if(name == "" || startDate == "" || endDate == "") {
            // Validate here
            alert("Vui long nhap day du thong tin!")
            return;
        }
        
		$.ajax({
            method: "post",
            url: "http://localhost:8080/crmproject/api/add-project",
            data: { name: name, startDate: startDate, endDate: endDate }
        }).done(function( data ) {
            if(data.statusCode == 200){
                alert("Them du an thanh cong!")
            }else{
                alert("Them du an that bai!")
            }
            window.location.href = 'http://localhost:8080/crmproject/groupwork'
        });
        
	 })
	 
 	$(".btn-return").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/groupwork'
    })
 })