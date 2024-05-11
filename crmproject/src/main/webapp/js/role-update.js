/**
 * 
 */

 $(document).ready(function () {
    $(".btn-update").click(function () {
        var id = $(this).attr("roleId")
        var name = $("#name").val()
        var description = $("#description").val()

        if(name == "" || description == "") {
            alert("Vui long nhap du thong tin!")
            return;
        }

        $.ajax({
			
            method: "post",
            url: "http://localhost:8080/crmproject/api/update-role",
            data: {id: id, name: name, description: description}
            
        }).done(function( data ) {
            if(data.statusCode == 200){
                alert("Cap nhat thong tin thanh cong!")
            }else{
                alert("Cap nhat thong tin that bai!")
            }
            window.location.href = 'http://localhost:8080/crmproject/role'
        });
    })

    $(".btn-return").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/role'
    })
})