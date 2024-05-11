/**
 * 
 */

 $(document).ready(function () {
    $(".btn-update").click(function () {
        var id = $(this).attr("taskId")
 		window.location.href = 'http://localhost:8080/crmproject/profile-edit?id=' + id;
    })
})