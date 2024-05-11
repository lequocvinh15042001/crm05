/**
 * 
 */

 $(document).ready(function () {
    $(".btn-update").click(function () {
        var id = $(this).attr("userId")
        var fullname = $("#fullname").val()
        var email = $("#email").val()
        var password = $("#password").val()
        var phone = $("#phone").val()
        var roleId = $("#roleId option:selected").val()

        if(fullname == "" || phone == "" || email == "" || password == "" || roleId == null) {
            alert("Vui long nhap du thong tin!")
            return;
        }

        $.ajax({
			
            method: "post",
            url: "http://localhost:8080/crmproject/api/user/update",
            data: {id: id, fullname: fullname, phone: phone, email: email, password: password, roleId: roleId }
            
        }).done(function( data ) {
            if(data.statusCode == 200){
                alert("Cap nhat thong tin thanh cong!")
            }else{
                alert("Cap nhat thong tin that bai!")
            }
            window.location.href = 'http://localhost:8080/crmproject/users'
        });
    })

    $(".btn-return").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/users'
    })
})