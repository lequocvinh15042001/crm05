/**
 * 
 */
$(document).ready(function(){
	 $(".btn-add").click(function(e){
		var fullname = $("#fullname").val()
        var email = $("#email").val()
        var password = $("#password").val()
        var phone = $("#phone").val()
        var roleId = $("#roleId option:selected").val()
		 
        if(fullname == "" || email == "" || password == "" || phone == "") {
            // Validate here
            alert("Vui long nhap day du thong tin!")
            return;
        }
        
		$.ajax({
            method: "post",
            url: "http://localhost:8080/crmproject/api/user/add",
            data: { fullname: fullname, email: email, password:password, phone: phone, roleId: roleId}
        }).done(function( data ) {
            if(data.statusCode == 200){
                alert("Them user thanh cong!")
            }else{
                alert("Them user that bai!")
            }
            window.location.href = 'http://localhost:8080/crmproject/users'
        });
        
	 })
	 
 	$(".btn-return").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/users'
    })
 })