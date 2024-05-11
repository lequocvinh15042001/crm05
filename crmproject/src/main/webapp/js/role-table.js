/**
 * 
 */

 $(document).ready(function () {
    $(".btn-add").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/role-add'
    })
    $(".btn-update").click(function () {
        var id = $(this).attr("roleId")
 		window.location.href = 'http://localhost:8080/crmproject/role-update?id=' + id;
    })

    $(".btn-delete").click(function () {
        var id = $(this).attr("roleId")
        var This = $(this)
        $.ajax({
            method: "get",
            url: "http://localhost:8080/crmproject/api/delete-role?id=" + id,
            // data: { id: id, methods: methods }
        }).done(function( data ) {
            if(data.statusCode == 200){
                This.closest("tr").remove()
                alert("Xoa thanh cong!")
            }else{
                alert("Xoa that bai!")
            }
        });
    })
})