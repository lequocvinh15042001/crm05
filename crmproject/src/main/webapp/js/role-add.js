/**
 * 
 */
$(document).ready(function(){
	 $(".btn-add").click(function(e){
		var name = $("#name").val()
        var description = $("#description").val()
		 
        if(name == "" || description == "") {
            // Validate here
            alert("Vui long nhap day du thong tin!")
            return;
        }
        
		$.ajax({
            method: "post",
            url: "http://localhost:8080/crmproject/api/add-role",
            data: { name: name, description: description}
        }).done(function( data ) {
            if(data.statusCode == 200){
                alert("Them role thanh cong!")
            }else{
                alert("Them role that bai!")
            }
            window.location.href = 'http://localhost:8080/crmproject/role'
        });
        
	 })
	 
 	$(".btn-return").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/role'
    })
 })