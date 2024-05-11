/**
 * 
 */
$(document).ready(function () {
    $(".menu-dashboard").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/home'
    })
    $(".menu-user").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/users'
    })
    $(".menu-role").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/role'
    })
    $(".menu-job").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/groupwork'
    })
    $(".menu-task").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/task'
    })
    $(".menu-blank").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/blank'
    })
    $(".menu-error").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/error'
    })
    
    
    $(".menu-profile").click(function () {
		var id = $(this).attr("userId")
        window.location.href = 'http://localhost:8080/crmproject/profile?id=' + id;
    })
    
    $(".menu-logout").click(function () {
        window.location.href = 'http://localhost:8080/crmproject/logout'
    })
})