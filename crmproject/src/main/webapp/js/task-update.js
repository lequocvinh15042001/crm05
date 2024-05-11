/**
 * 
 */
$(document).ready(function(){
	 $(".btn-update").click(function(e){
		var id = $(this).attr("taskId")
		var projectId = $("#projectId option:selected").val()
        var taskName = $("#taskName").val()
        var userId = $("#userId option:selected").val()
        var startDate = $("#startDate").val()
        var endDate = $("#endDate").val()
        var statusId = $("#statusId option:selected").val()
        
		 
        if(projectId == "" || taskName == "" || userId == "" || startDate == "" || endDate == "") {
            // Validate here
            alert("Vui long nhap day du thong tin!")
            return;
        }
        
		$.ajax({
            method: "post",
            url: "http://localhost:8080/crmproject/api/update-task",
            data: { id: id, projectId: projectId, taskName: taskName, userId:userId, startDate: startDate, endDate: endDate, statusId: statusId}
        }).done(function( data ) {
            if(data.statusCode == 200){
                alert("Cap nhat task thanh cong!")
            }else{
                alert("Cap nhat task that bai!")
            }
            window.location.href = 'http://localhost:8080/crmproject/task'
        });
        
	 })
	 
 	$(".btn-return").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/task'
    })
 })