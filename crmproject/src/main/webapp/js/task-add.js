/**
 * 
 */
$(document).ready(function(){
	 $(".btn-add").click(function(e){
		var projectId = $("#projectId option:selected").val()
        var taskName = $("#taskName").val()
        var userId = $("#userId option:selected").val()
        var startDate = $("#startDate").val()
        var endDate = $("#endDate").val()
		 
        if(projectId == "" || taskName == "" || userId == "" || startDate == "" || endDate == "") {
            // Validate here
            alert("Vui long nhap day du thong tin!")
            return;
        }
        
		$.ajax({
            method: "post",
            url: "http://localhost:8080/crmproject/api/add-task",
            data: { projectId: projectId, taskName: taskName, userId:userId, startDate: startDate, endDate: endDate}
        }).done(function( data ) {
            if(data.statusCode == 200){
                alert("Them task thanh cong!")
            }else{
                alert("Them task that bai!")
            }
            window.location.href = 'http://localhost:8080/crmproject/task'
        });
        
	 })
	 
 	$(".btn-return").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/task'
    })
 })