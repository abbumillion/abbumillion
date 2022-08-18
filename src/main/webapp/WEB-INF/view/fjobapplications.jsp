<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Job Applications</title>
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
<jsp:include page="templates/freelancerheader.jsp"/>
<section>
    <div class="card">
        <div class="card-header">
            <h4>My Job Applications</h4>
        </div>
        <c:forEach var="ja" items="${jobaaplications}">
        <!-- single-job-content -->
        <div class="single-job-items mb-30">
            <div class="job-items">
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
                                    <p class="text-muted mb-0">${ja.getApplicationDate()}</p>
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
                                    <p class="text-muted mb-0">${ja.getFreelancer().getUser().getFullName()}&nbsp;</p>
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