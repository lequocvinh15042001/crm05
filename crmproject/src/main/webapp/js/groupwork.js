/**
 * 
 */

 $(document).ready(function () {
	 
    $(".btn-update").click(function () {
		
        //var id = $(this).attr("projectId")
        //$.ajax({
            //method: "post",
            //url: "http://localhost:8080/crmproject/groupwork-update",
            //data: {id: id },
        //}) .done(function( data ) {
            //window.location.href = 'http://localhost:8080/crmproject/groupwork-update'
        //});
		
        var id = $(this).attr("projectId")
 		window.location.href = 'http://localhost:8080/crmproject/groupwork-update?id=' + id;
    })
    
     $(".btn-delete").click(function(){
        var id = $(this).attr("projectId")
        var This = $(this)
        $.ajax({
            method: "get",
            url: "http://localhost:8080/crmproject/api/delete-project?id=" + id,
            // data: { id: id, methods: methods }
        }).done(function( data ) {
            if(data.statusCode == 200){
                This.closest("tr").remove()
                alert("Da xoa du an!")
            }else{
                alert("Xoa du an that bai!")
            }
        });
    })

    $(".btn-insert").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/groupwork-add';
    })
    

    $(".btn-details").click(function () {
        var id = $(this).attr("projectId")
        window.location.href = 'http://localhost:8080/crmproject/groupwork-details?id=' + id;
    })
    
})