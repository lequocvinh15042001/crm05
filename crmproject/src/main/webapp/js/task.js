/**
 * 
 */

 $(document).ready(function () {
    $(".btn-insert").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/task-add'
    })
    $(".btn-update").click(function () {
        var id = $(this).attr("taskId")
 		window.location.href = 'http://localhost:8080/crmproject/task-update?id=' + id;
    })

    $(".btn-delete").click(function () {
        var id = $(this).attr("taskId")
        var This = $(this)
        $.ajax({
            method: "get",
            url: "http://localhost:8080/crmproject/api/delete-task?id=" + id,
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