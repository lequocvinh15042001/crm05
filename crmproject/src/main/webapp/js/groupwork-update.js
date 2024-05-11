/**
 * 
 */
 $(document).ready(function(){
	 $(".btn-update").click(function(e){
		 
		 var id = $(this).attr("projectId")
		 var name = $("#name").val()
		 var startDate = $("#startDate").val()
		 var endDate = $("#endDate").val()
		 
        if(name == "" || startDate == "" || endDate == "") {
            alert("Vui long nhap day du thong tin!")
            return;
        }
        
		$.ajax({
            method: "post",
            url: "http://localhost:8080/crmproject/api/update-project",
            data: { id: id, name: name, startDate: startDate, endDate: endDate }
        }).done(function( data ) {
            if(data.statusCode == 200){
                alert("Cap nhat du an thanh cong!")
            }else{
                alert("Cap nhat du an that bai!")
            }
            window.location.href = 'http://localhost:8080/crmproject/groupwork'
        });
        
	 })
	 
 	$(".btn-return").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/groupwork'
    })
 })