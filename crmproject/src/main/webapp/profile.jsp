<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
    <title>Pixel Admin</title>
    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- animation CSS -->
    <link href="css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="css/colors/blue-dark.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
    <!-- Preloader -->
    <div class="preloader">
        <div class="cssload-speeding-wheel"></div>
    </div>
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top m-b-0">
            <div class="navbar-header">
                <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse"
                    data-target=".navbar-collapse">
                    <i class="fa fa-bars"></i>
                </a>
                <div class="top-left-part">
                    <a class="logo" href="home">
                        <b>
                            <img src="plugins/images/pixeladmin-logo.png" alt="home" />
                        </b>
                        <span class="hidden-xs">
                            <img src="plugins/images/pixeladmin-text.png" alt="home" />
                        </span>
                    </a>
                </div>
                <ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
                    <li>
                        <form role="search" class="app-search hidden-xs">
                            <input type="text" placeholder="Search..." class="form-control">
                            <a href="">
                                <i class="fa fa-search"></i>
                            </a>
                        </form>
                    </li>
                </ul>
                <ul class="nav navbar-top-links navbar-right pull-right">
                    <li>
                        <div class="dropdown">
                            <a class="profile-pic dropdown-toggle" data-toggle="dropdown" href="#">
                                <c:choose>
                                <c:when test="${user.id == 8}">
                                                    <img width="36" src="plugins/images/users/arijit.jpg"
                                                         class="img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${userProfile.id == 7}">
                                                    <img width="36" src="plugins/images/users/genu.jpg"
                                                         class="img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${userProfile.id == 6}">
                                                    <img width="36" src="plugins/images/users/govinda.jpg"
                                                         class="img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${userProfile.id == 5}">
                                                    <img width="36" src="plugins/images/users/hritik.jpg"
                                                         class="img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${userProfile.id == 4}">
                                                    <img width="36" src="plugins/images/users/pawandeep.jpg"
                                                         class="img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${userProfile.id == 3}">
                                                    <img width="36" src="plugins/images/users/ritesh.jpg"
                                                         class="img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${userProfile.id == 2}">
                                                    <img width="36" src="plugins/images/users/sonu.jpg"
                                                         class="img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${userProfile.id == 1}">
                                                    <img width="36" src="plugins/images/users/varun.jpg"
                                                         class="img-circle" alt="img">
                                                </c:when>
                                                <c:otherwise>
                                                    <img width="36" src="plugins/images/users/genu.jpg"
                                                         class="img-circle" alt="img">
                                                </c:otherwise>
                                    </c:choose>
                                <b class="hidden-xs">${userProfile.fullname}</b> 
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="profile">Thông tin cá nhân</a></li>
                                <li><a href="#">Thống kê công việc</a></li>
                                <li class="divider"></li>
                                <li><a href="logout">Đăng xuất</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-header -->
            <!-- /.navbar-top-links -->
            <!-- /.navbar-static-side -->
        </nav>
        <!-- Left navbar-header -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse slimscrollsidebar">
                <ul class="nav" id="side-menu">
                    <li style="padding: 10px 0 0;">
                        <a href="#" class="waves-effect menu-dashboard"><i class="fa fa-clock-o fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a>
                    </li>
                    <li>
                        <a href="#" class="waves-effect menu-user"><i class="fa fa-user fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a>
                    </li>
                    <li>
                        <a href="#" class="waves-effect menu-role"><i class="fa fa-modx fa-fw"
                                                                         aria-hidden="true"></i><span class="hide-menu">Quyền</span></a>
                    </li>
                    <li>
                        <a href="#" class="waves-effect menu-job"><i class="fa fa-table fa-fw"
                                                                        aria-hidden="true"></i><span class="hide-menu">Dự án</span></a>
                    </li>
                    <li>
                        <a href="#" class="waves-effect menu-task"><i class="fa fa-table fa-fw"
                                                                   aria-hidden="true"></i><span class="hide-menu">Công việc</span></a>
                    </li>
                    <li>
                        <a href="#" class="waves-effect menu-blank"><i class="fa fa-columns fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Blank Page</span></a>
                    </li>
                    <li>
                        <a href="#" class="waves-effect menu-error"><i class="fa fa-info-circle fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Error 404</span></a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- Left navbar-header end -->
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Chi tiết thành viên</h4>
                    </div>
                </div>
                <!-- /.row -->
                <!-- .row -->
                <div class="row">
                    <div class="col-md-4 col-xs-12">
                        <div class="white-box">
                            <div class="user-bg"> 
								<c:choose>
                                    <c:when test="${user.id == 8}">
                                        <img width="100%" alt="user" src="plugins/images/users/arijit.jpg">
                                    </c:when>
                                    <c:when test="${user.id == 7}">
                                        <img width="100%" alt="user" src="plugins/images/users/genu.jpg">
                                    </c:when>
                                    <c:when test="${user.id == 6}">
                                        <img width="100%" alt="user" src="plugins/images/users/govinda.jpg">
                                    </c:when>
                                    <c:when test="${user.id == 5}">
                                        <img width="100%" alt="user" src="plugins/images/users/hritik.jpg">
                                    </c:when>
                                    <c:when test="${user.id == 4}">
                                        <img width="100%" alt="user" src="plugins/images/users/pawandeep.jpg">
                                    </c:when>
                                    <c:when test="${user.id == 3}">
                                        <img width="100%" alt="user" src="plugins/images/users/ritesh.jpg">
                                    </c:when>
                                    <c:when test="${user.id == 2}">
                                        <img width="100%" alt="user" src="plugins/images/users/sonu.jpg">
                                    </c:when>
                                    <c:when test="${user.id == 1}">
                                        <img width="100%" alt="user" src="plugins/images/users/varun.jpg">
                                    </c:when>
                                    <c:otherwise>
                                        <img width="100%" alt="user" src="plugins/images/users/genu.jpg">
                                    </c:otherwise>
                                </c:choose>
                                <div class="overlay-box">
                                    <div class="user-content">
                                        <a href="javascript:void(0)">
                                        <c:choose>
                                                <c:when test="${user.id == 8}">
                                                    <img src="plugins/images/users/arijit.jpg"
                                                         class="thumb-lg img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${user.id == 7}">
                                                    <img src="plugins/images/users/genu.jpg"
                                                         class="thumb-lg img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${user.id == 6}">
                                                    <img src="plugins/images/users/govinda.jpg"
                                                         class="thumb-lg img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${user.id == 5}">
                                                    <img src="plugins/images/users/hritik.jpg"
                                                         class="thumb-lg img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${user.id == 4}">
                                                    <img src="plugins/images/users/pawandeep.jpg"
                                                         class="thumb-lg img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${user.id == 3}">
                                                    <img src="plugins/images/users/ritesh.jpg"
                                                         class="thumb-lg img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${user.id == 2}">
                                                    <img src="plugins/images/users/sonu.jpg"
                                                         class="thumb-lg img-circle" alt="img">
                                                </c:when>
                                                <c:when test="${user.id == 1}">
                                                    <img src="plugins/images/users/varun.jpg"
                                                         class="thumb-lg img-circle" alt="img">
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="plugins/images/users/genu.jpg"
                                                         class="thumb-lg img-circle" alt="img">
                                                </c:otherwise>
                                            </c:choose>
										</a>
                                        <h4 class="text-white">${user.lastName} ${user.firstName}</h4>
                                        <h5 class="text-white">${user.email}</h5>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-8 col-xs-12">
                        <!-- BEGIN THỐNG KÊ -->
                        <div class="row">
                            <!--col -->
                            
                            <c:forEach items="${statusCount}" var="statusCountItem">
							    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
							        <div class="white-box">
							            <div class="col-in row">
							                <div class="col-xs-12">
							                    <h3 class="counter text-right m-t-15 text-danger">${statusCountItem.count/3*99.9}%</h3>
							                </div>
							                <div class="col-xs-12">
							                    <i data-icon="E" class="linea-icon linea-basic"></i>
							                    <h5 class="text-muted vb text-center">${statusCountItem.name}</h5>
							                </div>
							                <div class="col-md-12 col-sm-12 col-xs-12">
							                    <div class="progress">
							                        <div class="progress-bar progress-bar-danger" role="progressbar"
							                            aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
							                            style="width: ${statusCountItem.count/3*100}%"></div>
							                    </div>
							                </div>
							            </div>
							        </div>
							    </div>
							</c:forEach>
                           
                            <!-- /.col -->
                            <!--col -->
                            <%-- <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                <div class="white-box">
                                    <div class="col-in row">
                                        <div class="col-xs-12">
                                            <h3 class="counter text-right m-t-15 text-megna">${statusCount.count/100}%</h3>
                                        </div>
                                        <div class="col-xs-12">
                                            <i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
                                            <h5 class="text-muted vb text-center">ĐANG THỰC HIỆN</h5>
                                        </div>
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="progress">
                                                <div class="progress-bar progress-bar-megna" role="progressbar"
                                                    aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                    style="width: 50%"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> --%>
                            <!-- /.col -->
                            <!--col -->
                            <!-- <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                <div class="white-box">
                                    <div class="col-in row">
                                        <div class="col-xs-12">
                                            <h3 class="counter text-right m-t-15 text-primary">30%</h3>
                                        </div>
                                        <div class="col-xs-12">
                                            <i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
                                            <h5 class="text-muted vb text-center">HOÀN THÀNH</h5>
                                        </div>
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="progress">
                                                <div class="progress-bar progress-bar-primary" role="progressbar"
                                                    aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                                    style="width: 30%"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> -->
                            <!-- /.col -->
                        </div>
                        <!-- END THỐNG KÊ -->

                    </div>
                </div><br />
                <!-- /.row -->
                <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
                <h4>DANH SÁCH CÔNG VIỆC</h4>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <div class="table-responsive">
                                <table class="table" id="example">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên Công Việc</th>
                                            <th>Dự Án</th>
                                            <th>Ngày Bắt Đầu</th>
                                            <th>Ngày Kết Thúc</th>
                                            <th>Trạng Thái</th>
                                            <th>Hành Động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach  items="${tasks}" var="task" varStatus="loop">
                                            <tr>
                                                <td>${loop.index + 1}</td>
                                                <td>${task.taskName}</td>
                                                <td>${task.projectName}</td>
                                                <td>${task.startDate}</td>
                                                <td>${task.endDate}</td>
                                                <td>${task.statusTask}</td>
	                                            <td>
	                                                <a taskId="${task.id}" href="#" class="btn btn-sm btn-primary btn-update">Cập nhật</a>
	                                            </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END DANH SÁCH CÔNG VIỆC -->
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="js/waves.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="js/menu.js"></script>
    <script src="js/custom.min.js"></script>
    <script src="js/profile.js"></script>
</body>

</html>