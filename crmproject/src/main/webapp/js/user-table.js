$(document).ready(function () {
    $(".btn-add").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/user-add'
    })
    $(".btn-update").click(function () {
        var id = $(this).attr("userId")
 		window.location.href = 'http://localhost:8080/crmproject/user-update?id=' + id;
        //$.ajax({
            //method: "post",
            //url: "http://localhost:8080/crmproject/user-update",
            //data: {id: id },
       //}) .done(function( data ) {
            //window.location.href = 'http://localhost:8080/crmproject/user-update'
        //});
    })

    $(".btn-delete").click(function () {
        var id = $(this).attr("userId")
        var This = $(this)
        $.ajax({
            method: "get",
            url: "http://localhost:8080/crmproject/api/user/delete?id=" + id,
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

    $(".btn-detail").click(function () {
        var id = $(this).attr("userId")
		window.location.href = 'http://localhost:8080/crmproject/user-details?id=' + id;
    })
})