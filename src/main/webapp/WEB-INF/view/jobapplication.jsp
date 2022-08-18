<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Job Application</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="resources/css/flaticon.css">
    <link rel="stylesheet" href="resources/css/price_rangs.css">
    <link rel="stylesheet" href="resources/css/slicknav.css">
    <link rel="stylesheet" href="resources/css/animate.min.css">
    <link rel="stylesheet" href="resources/css/magnific-popup.css">
    <link rel="stylesheet" href="resources/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="resources/css/themify-icons.css">
    <link rel="stylesheet" href="resources/css/slick.css">
    <link rel="stylesheet" href="resources/css/nice-select.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="resources/img/favicon.ico">
    <link rel="stylesheet" href="resources/css/style.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body class="my-login-page">
<jsp:include page="templates/customerheader.jsp"/>

<div class="card">
    <div class="card-header">
        <ul class="float-right">
            <li>
                <form action="/searchBox" class="form-inline my-2 my-lg-0 ">
                    <input class="form-control mr-sm-2" name="searchTerm" type="search" placeholder="Search"
                           aria-label="Search">
                    <input type="hidden" name="page" value="0"/>
                    <input type="hidden" name="size" value="${maxTraySize}"/>
                    <div class="form-group m-2">
                        <button type="submit" class=" btn btn-primary btn-block">Search
                        </button>
                    </div>
                </form>
            </li>
        </ul>
    </div>
    <c:forEach var="ja" items="${jobaaplications}">
    <div class="col-xl-10">
        <!-- single-job-content -->
        <div class="single-job-items mb-30">
            <div class="job-items">
                <div class="company-img">
                    <a href="/login"><img src="resources/img/icon/job-list1.png" alt=""></a>
                </div>
                <div class="job-tittle">
                    <ul>
                        <li id="jobskill">${ja.getJob().getSkill().getSkillName()}</li>
                        <li id="jobtype">Job Type:${ja.getJob().getType()}</li>
                        <li id="budget">${ja.getJob().getBudget()}&nbsp;birr</li>
                        <li id="enddate">${ja.getJob().getEndDate()}</li>
                        <li>
                            <div class="form-group m-2">
                                <a href="/job${job.getJobId()}" class=" btn btn-primary btn-block">Apply
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>


            <%--        --%>
        <!-- single-job-content -->
        <div class="single-job-items mb-30">
            <div class="job-items">
                <div class="col-lg-8">
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Job Title</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0">${ja.getJob().getSkill().getSkillName()}</p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Application Date</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0">${ja.getPpplicationDate()}</p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Salary</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0">${job.getBudget()}&nbsp;birr</p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Freelancer Name</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0">${ja.getFreelancer().getFullName()}&nbsp;</p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">End Date</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0">${ja.getJob().getEndDate()}</p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Description</p>
                                </div>
                                <div class="col-sm-9">
                                    <p class="text-muted mb-0">${ja.getJob().getDescription()}</p>
                                </div>
                            </div>
                            <hr>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </c:forEach>
    </section>
    <jsp:include page="templates/pageScript.jsp"/>
    <!-- JS here -->

    <!-- All JS Custom Plugins Link Here here -->
    <script src="resources/js/vendor/modernizr-3.5.0.min.js"></script>
    <!-- Jquery, Popper, Bootstrap -->
    <script src="resources/js/vendor/jquery-1.12.4.min.js"></script>
    <script src="resources/js/popper.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <!-- Jquery Mobile Menu -->
    <script src="resources/js/jquery.slicknav.min.js"></script>

    <!-- Jquery Slick , Owl-Carousel Plugins -->
    <script src="resources/js/owl.carousel.min.js"></script>
    <script src="resources/js/slick.min.js"></script>
    <script src="resources/js/price_rangs.js"></script>

    <!-- One Page, Animated-HeadLin -->
    <script src="resources/js/wow.min.js"></script>
    <script src="resources/js/animated.headline.js"></script>
    <script src="resources/js/jquery.magnific-popup.js"></script>

    <!-- Scrollup, nice-select, sticky -->
    <script src="resources/js/jquery.scrollUp.min.js"></script>
    <script src="resources/js/jquery.nice-select.min.js"></script>
    <script src="resources/js/jquery.sticky.js"></script>

    <!-- contact js -->
    <script src="resources/js/contact.js"></script>
    <script src="resources/js/jquery.form.js"></script>
    <script src="resources/js/jquery.validate.min.js"></script>
    <script src="resources/js/mail-script.js"></script>
    <script src="resources/js/jquery.ajaxchimp.min.js"></script>

    <!-- Jquery Plugins, main Jquery -->
    <script src="resources/js/plugins.js"></script>
    <script src="resources/js/main.js"></script>
</body>
</html>